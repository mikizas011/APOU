package model.category;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import controller.wizard.classes.Municipio;
import controller.wizard.classes.OrdenacionUrbanisticaEstructural;
import controller.wizard.classes.OrdenacionUrbanisticaPormenorizada;
import controller.wizard.classes.P2unidadEjecucion;
import controller.wizard.classes.P567UnidadEjecucion;
import controller.wizard.classes.ParcelaAportada;
import controller.wizard.classes.ParcelaResultante;
import controller.wizard.classes.UnidadEjecucion;
import controller.wizard.classes.phases.Phase1;
import controller.wizard.classes.phases.Phase2;
import controller.wizard.classes.phases.Phase3;
import controller.wizard.classes.phases.Phase4;
import controller.wizard.classes.phases.Phase5;
import controller.wizard.classes.phases.Phase6;
import controller.wizard.classes.phases.Phase7;
import controller.wizard.classes.phases.Phase8;
import model.Dao;

public class DaoWizard {

	private Dao dao;
	
	public DaoWizard(Dao dao){
		this.dao = dao;
	}
	
	//NO CORRESPONDE A NINGUNA FASE
		
		//CARGAR PLAN
		
		/** 
		 * @param idPlan id del plan.
		 * @return devuleve la fase en la que se encuntra un plan.
		 * @throws SQLException
		 */
		public int getFase(int idPlan) throws SQLException{
			
			String sql = "SELECT fase FROM plan WHERE id_plan = "+idPlan;

			PreparedStatement statement = (PreparedStatement) dao.getConection().prepareStatement(sql);

			ResultSet rs = statement.executeQuery();
			
			if(rs.next()){
				return rs.getInt("fase");
			}
			
			return -1;
		}
		

		/** Si se chequea una fase ya chequeada, cerrará todas las que estén por detrás de esta. Las de mismo nivel no
		 * @param plan id del plan
		 * @param fase fase que has chequeado
		 * @throws SQLException
		 */
		public void setPhaseCorrect(int plan, int fase) throws SQLException{
			
			String sql = "";
			
			switch (fase) {
				case 1: sql = "fase_1_correct = 1, fase_2_correct = 0, fase_3_correct = 0, fase_4_correct = 0, fase_5_correct = 0, fase_6_correct = 0, fase_7_correct = 0, fase_8_correct = 0"; break;
				case 2: sql = "fase_2_correct = 1, fase_4_correct = 0, fase_5_correct = 0, fase_6_correct = 0, fase_7_correct = 0, fase_8_correct = 0"; break;
				case 3: sql = "fase_3_correct = 1, fase_4_correct = 0, fase_5_correct = 0, fase_6_correct = 0, fase_7_correct = 0, fase_8_correct = 0"; break;
				case 4: sql = "fase_4_correct = 1, fase_5_correct = 0, fase_6_correct = 0, fase_7_correct = 0, fase_8_correct = 0"; break;
				case 5: sql = "fase_5_correct = 1, fase_6_correct = 1, fase_7_correct = 0, fase_8_correct = 0"; break;
				case 6: sql = "fase_6_correct = 1, fase_7_correct = 0, fase_8_correct = 0"; break;
				case 7: sql = "fase_7_correct = 1, fase_8_correct = 0"; break;
				case 8: sql = "fase_8_correct = 1"; break;
			}
			
			sql = "UPDATE plan SET " + sql + ", fase = ? WHERE id_plan = " + plan;
			
			PreparedStatement statement = (PreparedStatement) dao.getConection().prepareStatement(sql);
			
			statement.setInt(1, (fase+1));
			
			statement.execute();
			
		}
		
		public void setPhaseIncorrect(int plan, int fase) throws SQLException{
			
			String sql = "";
			
			switch (fase) {
				case 1: sql = "fase_1_correct = 0, fase_2_correct = 0, fase_3_correct = 0, fase_4_correct = 0, fase_5_correct = 0, fase_6_correct = 0, fase_7_correct = 0, fase_8_correct = 0"; break;
				case 2: sql = "fase_2_correct = 0, fase_4_correct = 0, fase_5_correct = 0, fase_6_correct = 0, fase_7_correct = 0, fase_8_correct = 0"; break;
				case 3: sql = "fase_3_correct = 0, fase_4_correct = 0, fase_5_correct = 0, fase_6_correct = 0, fase_7_correct = 0, fase_8_correct = 0"; break;
				case 4: sql = "fase_4_correct = 0, fase_5_correct = 0, fase_6_correct = 0, fase_7_correct = 0, fase_8_correct = 0"; break;
				case 5: sql = "fase_5_correct = 0, fase_6_correct = 0, fase_7_correct = 0, fase_8_correct = 0"; break;
				case 6: sql = "fase_6_correct = 0, fase_7_correct = 0, fase_8_correct = 0"; break;
				case 7: sql = "fase_7_correct = 0, fase_8_correct = 0"; break;
				case 8: sql = "fase_8_correct = 0"; break;
			}
			
			sql = "UPDATE plan SET " + sql + ", fase = ? WHERE id_plan = " + plan;
			
			PreparedStatement statement = (PreparedStatement) dao.getConection().prepareStatement(sql);
			
			statement.setInt(1, (fase));
			
			statement.execute();
			
		}
		

		/**
		 * @param plan Plan del que se quiere consultar las fases correctas.
		 * @return devuleve un array de booleanos con las fases que se consideran correctas.
		 * @throws SQLException 
		 */
		public boolean [] getFasesCorrectas(int plan) throws SQLException{
			boolean [] fases = {false, false, false, false, false, false, false, false};
			
			String sql = "SELECT fase_1_correct, fase_2_correct, fase_3_correct, fase_4_correct, fase_5_correct, fase_6_correct, fase_7_correct, fase_8_correct FROM plan WHERE id_plan = " + plan;
			
			PreparedStatement statement = (PreparedStatement) dao.getConection().prepareStatement(sql);

			ResultSet rs = statement.executeQuery();
			
			if(rs.next()){
				for(int i = 0; i < fases.length; i++){
					if(rs.getInt("fase_"+(i+1)+"_correct") == 1){
						fases[i] = true;
					}
				}
			}
			
			return fases;
		}
		

		
		
	
	
	//PHASE 1
	
	public Phase1 getPhase1(int idPlan) throws SQLException{
		
		String sql = "SELECT id_plan, denominacion, nombre_sector, numero_sector, municipio, idioma, superficie, id_marco_legal FROM plan WHERE id_plan = "+idPlan;

		PreparedStatement statement = (PreparedStatement) dao.getConection().prepareStatement(sql);

		ResultSet rs = statement.executeQuery();

		Phase1 p = null;
		
		if(rs.next()){
			p = new Phase1(rs.getInt("id_plan"), rs.getString("denominacion"), rs.getString("nombre_sector"), rs.getString("numero_sector"), rs.getString("municipio"), rs.getString("idioma"), rs.getDouble("superficie")+"", rs.getInt("id_marco_legal"));
		}
		
		HashMap<String, String> ues = new HashMap<String, String>();
		
		sql = "SELECT denominacion, numero_parcelas_aportadas FROM unidad_ejecucion WHERE id_plan = "+idPlan + " ORDER BY denominacion ASC";

		statement = (PreparedStatement) dao.getConection().prepareStatement(sql);
		
		rs = statement.executeQuery();
		
		while(rs.next()){
			ues.put(rs.getString("denominacion"), rs.getInt("numero_parcelas_aportadas")+"");
		}
		
		p.setUes(ues);
		
		HashMap<Integer, String> marcosLegales = new HashMap<Integer, String>();
		
		sql = "SELECT id_marco_legal, denominacion FROM marco_legal";

		statement = (PreparedStatement) dao.getConection().prepareStatement(sql);
		
		rs = statement.executeQuery();
		
		while(rs.next()){
			marcosLegales.put(rs.getInt("id_marco_legal"), rs.getString("denominacion"));
		}
		
		p.setMarcosLegales(marcosLegales);
		
		return p;
		
	}
	
	public void updatePhase1(Phase1 p) throws SQLException{
		
		//Modificar datos del plan
		String sql = "UPDATE plan SET denominacion = ?, nombre_sector = ?, numero_sector = ?, municipio = ?, idioma = ?, superficie = ?, id_marco_legal = ? WHERE id_plan = " + p.getIdPlan();
		
		PreparedStatement statement = (PreparedStatement) dao.getConection().prepareStatement(sql);
		
		statement.setString(1, p.getDenominacionPlan());
		statement.setString(2, p.getDenominacionSector());
		statement.setString(3, p.getNumeroSector());
		statement.setString(4, p.getMunicipio());
		statement.setString(5, p.getIdioma());
		statement.setDouble(6, Double.parseDouble(p.getSuperficie()));
		statement.setInt(7, p.getIdMarcoLegal());

		statement.execute();
		
		String notIn = "";
		
		
		//Para cada una de las unidades de ejecucion
		for (Entry<String, String> entry : p.getUes().entrySet()) {
			//String notIn que se utilizará después para eleiminar las entradas que hayan sido eliminadas por el usuario
			notIn += "'" + entry.getKey() + "', ";
		
			//Actualización o inserción de las unidades de ejecucion añadidas o modificadas por el usuario
			int parcelasAportadas = Integer.parseInt(entry.getValue());
			sql = "UPDATE unidad_ejecucion SET numero_parcelas_aportadas = "+parcelasAportadas+" WHERE id_plan="+p.getIdPlan()+" AND denominacion = '" + entry.getKey() +"'";
			statement = dao.getConection().prepareStatement(sql);
			int suc = statement.executeUpdate();
			
			if(suc == 0){
				sql = "INSERT INTO unidad_ejecucion (denominacion, numero_parcelas_aportadas, id_plan) values (?, ?, ?)";
				
				statement = (PreparedStatement) dao.getConection().prepareStatement(sql);
				
				statement.setString(1, entry.getKey());
								
				statement.setInt(2, parcelasAportadas);
				
				statement.setInt(3, p.getIdPlan());

				statement.execute();
			}
			
		}
		
		sql = "SELECT id_unidad_ejecucion FROM unidad_ejecucion WHERE id_plan = " + p.getIdPlan();

		ArrayList<Integer> idsUnidadesEjecucionAntiguas = new ArrayList<Integer>();
		
		PreparedStatement stmt = (PreparedStatement) dao.getConection().prepareStatement(sql);
		
		ResultSet rs = stmt.executeQuery();
		
		while(rs.next()){
			idsUnidadesEjecucionAntiguas.add(rs.getInt("id_unidad_ejecucion"));
		}
		
		if(notIn.length() > 2){
			notIn = notIn.substring(0, notIn.length() -2);
			
			sql = "DELETE FROM unidad_ejecucion WHERE denominacion NOT IN ("+notIn+") AND id_plan = " + p.getIdPlan();

			statement = dao.getConection().prepareStatement(sql);

			statement.execute();
		}
		
				
		//Renombramiento de las unidades de ejecución para ordenarlas
		
		sql = "SELECT id_unidad_ejecucion, numero_parcelas_aportadas, denominacion FROM unidad_ejecucion WHERE id_plan = " + p.getIdPlan();

		HashMap<String, UnidadEjecucion> ues = new HashMap<String, UnidadEjecucion>();
 		
		stmt = (PreparedStatement) dao.getConection().prepareStatement(sql);
		
		rs = stmt.executeQuery();
		
		while(rs.next()){

			ues.put(rs.getString("denominacion"), new UnidadEjecucion(rs.getInt("id_unidad_ejecucion"), rs.getInt("numero_parcelas_aportadas"), rs.getString("denominacion")));
		}
		
		sql = "SELECT id_dominio FROM dominio";
		stmt = (PreparedStatement) dao.getConection().prepareStatement(sql);
		rs = stmt.executeQuery();
		int dominioAux = -1;
		if(rs.next()){
			dominioAux = rs.getInt(1);
		}

		for (Entry<String, String> entry : p.getUes().entrySet()) {
			
			sql = "SELECT count(*) FROM parcela_aportada WHERE id_unidad_ejecucion = " + ues.get(entry.getKey()).getIdUnidadEjecucion();

			statement = (PreparedStatement) dao.getConection().prepareStatement(sql);
			rs = statement.executeQuery();
			
			if(rs.next()){
				int numeroParcelasAportadas = rs.getInt("count(*)");
			
				if(numeroParcelasAportadas > Integer.parseInt(entry.getValue())){
					//Si antes había más parcelas que ahora, tenemos que borrar parcelas de la base de datos.
					int parcelasABorrar = numeroParcelasAportadas - Integer.parseInt(entry.getValue());
					
					String in = "";
					
					for(int i = 0; i < parcelasABorrar; i++){
						in += "'"+ues.get(entry.getKey()).getDenominacion() + "PA" + (numeroParcelasAportadas - i) + "', ";
					}
					
					in = in.substring(0, in.length() -2);
					
					sql = "DELETE FROM parcela_aportada WHERE denominacion IN ("+in+") AND id_unidad_ejecucion = " + ues.get(entry.getKey()).getIdUnidadEjecucion();

					statement = dao.getConection().prepareStatement(sql);

					statement.execute();
					
				}
				else if(numeroParcelasAportadas < Integer.parseInt(entry.getValue())){
					//Si antes había menos parcelas que ahora, tenemos que insertar parcelas en la base de datos. 
					int parcelasAInsertar = Integer.parseInt(entry.getValue()) - numeroParcelasAportadas;

					for(int i = 1; i < parcelasAInsertar+1; i++){
						sql = "INSERT INTO parcela_aportada (denominacion, id_unidad_ejecucion, id_dominio) values (?, ?, ?)";
						statement = (PreparedStatement) dao.getConection().prepareStatement(sql);
						statement.setString(1, ues.get(entry.getKey()).getDenominacion() + "PA" + (numeroParcelasAportadas + i));		
						statement.setInt(2, ues.get(entry.getKey()).getIdUnidadEjecucion());
						statement.setInt(3,  dominioAux);
						statement.execute();
					}
						
				}
				
			}
		}

		
//FALTA EL RENOMBRAMIENTO

		
//		for(int i = 0; i < idsUnidadesEjecucion.size(); i++){
//			sql = "UPDATE unidad_ejecucion set denominacion = 'UE" + (i+1) + "' WHERE id_unidad_ejecucion = " + idsUnidadesEjecucion.get(i);  
//			statement = dao.getConection().prepareStatement(sql);
//			statement.executeUpdate();
//		}

		
		
		
		
	
	}
	
	public ArrayList<Municipio> getMunicipios() throws SQLException{
		
		ArrayList<Municipio> municipios = new ArrayList<Municipio>();
		
		String sql = "SELECT * FROM municipio";
		
		PreparedStatement statement = (PreparedStatement) dao.getConection().prepareStatement(sql);

		ResultSet rs = statement.executeQuery();

		while(rs.next()){
			municipios.add(new Municipio(rs.getString("municipio")));
		}
		
		return municipios;
		
	}
	
	//FIN PHASE 1
	
	//PHASE 2
	
	public Phase2 getPhase2(int idPlan) throws SQLException{
		
		String sql;
		PreparedStatement statement;
		ResultSet rs;
		
		
		//Obtenemos el numero de unidades de ejecución que hay en un plan y la información de cada una de ellas.
		sql = "SELECT id_unidad_ejecucion, denominacion, superficie_servidumbre, numero_parcelas_aportadas FROM unidad_ejecucion WHERE id_plan = " + idPlan;
		
		statement = (PreparedStatement) dao.getConection().prepareStatement(sql);
		
		rs = statement.executeQuery();
		
		HashMap<Integer, P2unidadEjecucion> map = new HashMap<Integer, P2unidadEjecucion>();
		while(rs.next()){
			map.put(rs.getInt("id_unidad_ejecucion"), new P2unidadEjecucion((int)rs.getDouble("superficie_servidumbre"), rs.getInt("id_unidad_ejecucion"), rs.getInt("numero_parcelas_aportadas"), rs.getString("denominacion")));
		}
		
		sql = "SELECT pa.id_parcela_aportada, pa.denominacion, pa.propietario, pa.superficie, pa.id_unidad_ejecucion, do.denominacion, do.id_dominio FROM parcela_aportada pa, unidad_ejecucion ue, dominio do WHERE pa.id_dominio = do.id_dominio AND pa.id_unidad_ejecucion = ue.id_unidad_ejecucion AND ue.id_plan = " + idPlan;

		statement = (PreparedStatement) dao.getConection().prepareStatement(sql);
		
		rs = statement.executeQuery();
		
		while(rs.next()){
			map.get(rs.getInt("pa.id_unidad_ejecucion")).getParcelas().add(new ParcelaAportada(rs.getInt("pa.id_parcela_aportada"), rs.getString("pa.denominacion"), rs.getString("pa.propietario"), rs.getString("do.denominacion"), rs.getInt("pa.id_unidad_ejecucion"), (int)rs.getDouble("pa.superficie"), rs.getInt("id_dominio") ));
		}
		
		Phase2 p = new Phase2(idPlan, map);
		
		return p;		
	}
	
	public void updatePhase2(Phase2 p) throws SQLException{
		
		String sql;
		PreparedStatement statement;
		ResultSet rs;
		
		sql = "SELECT id_dominio, denominacion FROM dominio";
		statement = (PreparedStatement) dao.getConection().prepareStatement(sql);
		rs = statement.executeQuery();
		
		HashMap<String, Integer> dominios = new HashMap<String, Integer>();
		
		while(rs.next()){
			dominios.put(rs.getString("denominacion"), rs.getInt("id_dominio"));
		}
		
		
		for(Entry <Integer, P2unidadEjecucion> entry : p.getMap().entrySet()){
			
			sql = "UPDATE unidad_ejecucion SET superficie_servidumbre = ? WHERE id_unidad_ejecucion = ?"; 
			statement = (PreparedStatement) dao.getConection().prepareStatement(sql);
			statement.setDouble(1, entry.getValue().getSuperficieServidumbre());		
			statement.setInt(2, entry.getKey());
			statement.execute();
			
			for(int i = 0; i < entry.getValue().getParcelas().size(); i++){
				
				sql = "UPDATE parcela_aportada SET superficie = ?, propietario = ?, id_dominio = ? WHERE id_parcela_aportada = ?";
				statement = (PreparedStatement) dao.getConection().prepareStatement(sql);
				statement.setDouble(1, entry.getValue().getParcelas().get(i).getSuperficie());		
				statement.setString(2, entry.getValue().getParcelas().get(i).getPropietario());
				statement.setInt(3, dominios.get(entry.getValue().getParcelas().get(i).getDominio()));
				statement.setInt(4, entry.getValue().getParcelas().get(i).getIdParcelaAportada());
				statement.execute();
			}
			
		}
		
	}
	
	//FIN FASE 2
	
	//PHASE 3
	
		public Phase3 getPhase3(int idPlan) throws SQLException{
			
			String sql;
			PreparedStatement statement;
			ResultSet rs;
			
			
			//Obtenemos todos los tipos de ordenacion urbanistica estructural (global) de un plan
			sql = "SELECT id_tipo_ordenacion_global, denominacion, superficie FROM tipo_ordenacion_global WHERE id_plan = " + idPlan;
			
			statement = (PreparedStatement) dao.getConection().prepareStatement(sql);
			
			rs = statement.executeQuery();
			
			ArrayList<OrdenacionUrbanisticaEstructural> tipos = new ArrayList<OrdenacionUrbanisticaEstructural>();
			while(rs.next()){
				tipos.add(new OrdenacionUrbanisticaEstructural(rs.getInt("id_tipo_ordenacion_global"), rs.getString("denominacion"), (int)rs.getDouble("superficie")));
			}
			
			int edifMaxSobreRasante = -1;
			int edifMaxBajoRasante = -1;
			
			sql = "SELECT edif_max_sr, edif_max_br FROM plan WHERE id_plan = " + idPlan;

			statement = (PreparedStatement) dao.getConection().prepareStatement(sql);
			
			rs = statement.executeQuery();
			
			if(rs.next()){
				edifMaxBajoRasante = rs.getInt("edif_max_br");
				edifMaxSobreRasante = rs.getInt("edif_max_sr");
			}

			Phase3 p = new Phase3(idPlan, tipos, edifMaxSobreRasante, edifMaxBajoRasante);
			
			return p;		
		}
		
		public void updatePhase3(Phase3 p) throws SQLException{
			
			String sql;
			PreparedStatement statement;
			
			sql = "UPDATE plan SET edif_max_sr = ?, edif_max_br = ? WHERE id_plan = ?"; 
			statement = (PreparedStatement) dao.getConection().prepareStatement(sql);
			statement.setDouble(1, p.getEdifMaxSobreRasante());		
			statement.setDouble(2, p.getEdifMaxBajoRasante());
			statement.setInt(3, p.getIdPlan());
			statement.execute();
			
			String notIn = "";
			
			for (Entry<Integer, OrdenacionUrbanisticaEstructural> entry : p.getUpdate().entrySet()) {
				notIn += entry.getValue().getIdOrdenacionUrbanisticaEstructural() + ", ";
				
				sql = "UPDATE tipo_ordenacion_global SET denominacion = ?, superficie = ? WHERE id_tipo_ordenacion_global = ?"; 
				statement = (PreparedStatement) dao.getConection().prepareStatement(sql);
				statement.setString(1, entry.getValue().getNombre());		
				statement.setDouble(2, entry.getValue().getSuperficie());
				statement.setInt(3, entry.getValue().getIdOrdenacionUrbanisticaEstructural());
				statement.execute();
				
			}
			
			if(notIn.length() > 2){
				notIn = notIn.substring(0, notIn.length() -2);
				sql = "DELETE FROM tipo_ordenacion_global WHERE id_tipo_ordenacion_global NOT IN ("+notIn+") AND id_plan = " + p.getIdPlan();
				statement = dao.getConection().prepareStatement(sql);
				statement.execute();
			}
			
			for (Entry<String, OrdenacionUrbanisticaEstructural> entry : p.getInsert().entrySet()) {
				
				sql = "INSERT INTO tipo_ordenacion_global (denominacion, superficie, id_plan) values (?, ?, ?)";
				statement = (PreparedStatement) dao.getConection().prepareStatement(sql);
				statement.setString(1, entry.getValue().getNombre());		
				statement.setDouble(2, entry.getValue().getSuperficie());
				statement.setInt(3, p.getIdPlan());
				statement.execute();
				
			}
			
			
		}

		//FIN FASE 3
	
		//FASE 4
		
		public Phase4 getPhase4(int idPlan) throws SQLException{
			
			String sql;
			PreparedStatement statement;
			ResultSet rs;
			
			
			//Obtenemos todos los tipos de ordenacion urbanistica pormenorizada de un plan
			sql = "SELECT id_tipo_ordenacion_pormenorizada, denominacion, coef_srpb, coef_srpp, coef_br FROM tipo_ordenacion_pormenorizada WHERE id_plan = " + idPlan;
			
			statement = (PreparedStatement) dao.getConection().prepareStatement(sql);
			
			rs = statement.executeQuery();
			
			ArrayList<OrdenacionUrbanisticaPormenorizada> tipos = new ArrayList<OrdenacionUrbanisticaPormenorizada>();
			while(rs.next()){
				tipos.add(new OrdenacionUrbanisticaPormenorizada(rs.getString("denominacion"), rs.getDouble("coef_srpb"), rs.getDouble("coef_srpp"), rs.getDouble("coef_br"), rs.getInt("id_tipo_ordenacion_pormenorizada")));
			}

			sql = "SELECT id_unidad_ejecucion, denominacion, numero_parcelas_resultantes FROM unidad_ejecucion WHERE id_plan = " + idPlan;
			
			statement = (PreparedStatement) dao.getConection().prepareStatement(sql);
			
			rs = statement.executeQuery();
			
			HashMap<String, UnidadEjecucion> ues = new HashMap<String, UnidadEjecucion>();
			
			while(rs.next()){
				ues.put(rs.getString("denominacion"), new UnidadEjecucion(rs.getInt("numero_parcelas_resultantes"), rs.getString("denominacion"), rs.getInt("id_unidad_ejecucion")));
			}
			
			Phase4 p = new Phase4(tipos, null, null, idPlan);
			p.setUes(ues);
			
			
			return p;		
		}
		
		public void updatePhase4(Phase4 p) throws SQLException{
			
			String sql;
			PreparedStatement statement;
			ResultSet rs;
			
			String notIn = "";
			
			for (Entry<Integer, OrdenacionUrbanisticaPormenorizada> entry : p.getUpdate().entrySet()) {
				notIn += entry.getValue().getIdOrdenacionPormenorizada() + ", ";
				
				sql = "UPDATE tipo_ordenacion_pormenorizada SET denominacion = ?, coef_srpb = ?, coef_srpp = ?, coef_br = ? WHERE id_tipo_ordenacion_pormenorizada = ?"; 
				statement = (PreparedStatement) dao.getConection().prepareStatement(sql);
				statement.setString(1, entry.getValue().getDenominacion());		
				statement.setDouble(2, entry.getValue().getCoefSRPB());
				statement.setDouble(3, entry.getValue().getCoefSRPP());
				statement.setDouble(4, entry.getValue().getCoefBR());
				statement.setInt(5, entry.getValue().getIdOrdenacionPormenorizada());
				statement.execute();
				
			}
			
			if(notIn.length() > 2){
				notIn = notIn.substring(0, notIn.length() -2);
				sql = "DELETE FROM tipo_ordenacion_pormenorizada WHERE id_tipo_ordenacion_pormenorizada NOT IN ("+notIn+") AND id_plan = " + p.getIdPlan();
				statement = dao.getConection().prepareStatement(sql);
				statement.execute();
			}
			
			for (Entry<String, OrdenacionUrbanisticaPormenorizada> entry : p.getInsert().entrySet()) {
				sql = "INSERT INTO tipo_ordenacion_pormenorizada (denominacion, coef_srpb, coef_srpp, coef_br, id_plan) values (?, ?, ?, ?, ?)";
				statement = (PreparedStatement) dao.getConection().prepareStatement(sql);
				statement.setString(1, entry.getValue().getDenominacion());		
				statement.setDouble(2, entry.getValue().getCoefSRPB());
				statement.setDouble(3, entry.getValue().getCoefSRPP());
				statement.setDouble(4, entry.getValue().getCoefBR());
				statement.setInt(5, p.getIdPlan());
				statement.execute();
				
			}
			
			//Recuperamos por cada unidad de ejecucion el numero de parcelas resultantes que había antes.
			for (Entry<String, UnidadEjecucion> entry : p.getUes().entrySet()) {
				sql = "SELECT count(*) FROM parcela_resultante WHERE id_unidad_ejecucion = " + entry.getValue().getIdUnidadEjecucion();

				statement = (PreparedStatement) dao.getConection().prepareStatement(sql);
				rs = statement.executeQuery();
				
				if(rs.next()){
					int numeroParcelasResultantes = rs.getInt("count(*)");
					
					if(numeroParcelasResultantes > entry.getValue().getNumeroParcelasResultantes()){
						//Si antes había más parcelas que ahora, tenemos que borrar parcelas de la base de datos.
						int parcelasABorrar = numeroParcelasResultantes - entry.getValue().getNumeroParcelasResultantes();
						
						String in = "";
						
						for(int i = 0; i < parcelasABorrar; i++){
							in += "'"+entry.getValue().getDenominacion() + "PR" + (numeroParcelasResultantes - i) + "', ";
						}
						
						in = in.substring(0, in.length() -2);
						
						sql = "DELETE FROM parcela_resultante WHERE denominacion IN ("+in+") AND id_unidad_ejecucion = " + entry.getValue().getIdUnidadEjecucion();

						statement = dao.getConection().prepareStatement(sql);

						statement.execute();
						
					}
					else if(numeroParcelasResultantes < entry.getValue().getNumeroParcelasResultantes()){
						//Si antes había menos parcelas que ahora, tenemos que insertar parcelas en la base de datos. 
						int parcelasAInsertar = entry.getValue().getNumeroParcelasResultantes() - numeroParcelasResultantes;

						for(int i = 1; i < parcelasAInsertar+1; i++){
							sql = "INSERT INTO parcela_resultante (denominacion, id_unidad_ejecucion) values (?, ?)";
							statement = (PreparedStatement) dao.getConection().prepareStatement(sql);
							statement.setString(1, entry.getValue().getDenominacion() + "PR" + (numeroParcelasResultantes + i));		
							statement.setInt(2, entry.getValue().getIdUnidadEjecucion());
							statement.execute();
						}
							
					}
					
					
				}
				
				sql = "UPDATE unidad_ejecucion SET numero_parcelas_resultantes = ? WHERE id_unidad_ejecucion = ?"; 
				statement = (PreparedStatement) dao.getConection().prepareStatement(sql);
				statement.setInt(1, entry.getValue().getNumeroParcelasResultantes());		
				statement.setInt(2, entry.getValue().getIdUnidadEjecucion());
				statement.execute();
				
			}
			
		}
		
		//FIN FASE 4
	
		//FASE 5
		
		public Phase5 getPhase5(int idPlan) throws SQLException{
			
			String sql;
			PreparedStatement statement;
			ResultSet rs;
			
			
			//Obtenemos el numero de unidades de ejecución que hay en un plan y la información de cada una de ellas.
			sql = "SELECT id_unidad_ejecucion, denominacion FROM unidad_ejecucion WHERE id_plan = " + idPlan;
			
			statement = (PreparedStatement) dao.getConection().prepareStatement(sql);
			
			rs = statement.executeQuery();
			
			HashMap<Integer, P567UnidadEjecucion> map = new HashMap<Integer, P567UnidadEjecucion>();
			
			while(rs.next()){
				map.put(rs.getInt("id_unidad_ejecucion"), new P567UnidadEjecucion(rs.getString("denominacion"), rs.getInt("id_unidad_ejecucion"), idPlan));
			}
			
			//Obtenemos los tipos de ordenacion pormenorizados
			sql = "SELECT id_tipo_ordenacion_pormenorizada, denominacion FROM tipo_ordenacion_pormenorizada WHERE id_plan = " + idPlan;
			
			statement = (PreparedStatement) dao.getConection().prepareStatement(sql);
			
			rs = statement.executeQuery();
			
			HashMap<Integer, String> tipos = new HashMap<Integer, String>();
			
			while(rs.next()){
				tipos.put(rs.getInt("id_tipo_ordenacion_pormenorizada"), rs.getString("denominacion"));
			}
			
			//Obtenemos las parcelas resultantes
			sql = "SELECT pr.id_unidad_ejecucion, pr.id_parcela_resultante, pr.denominacion, pr.superficie, pr.obr_a, pr.obr_n, pr.osr_a, pr.osr_n, pr.ebr_a, pr.ebr_n, pr.esrpb_a, pr.esrpb_n, pr.esrpp_a, pr.esrpp_n, pr.id_tipo_ordenacion_pormenorizada FROM parcela_resultante pr, unidad_ejecucion ue WHERE ue.id_unidad_ejecucion = pr.id_unidad_ejecucion AND ue.id_plan = " + idPlan;
			
			statement = (PreparedStatement) dao.getConection().prepareStatement(sql);
			
			rs = statement.executeQuery();
			
			while(rs.next()){
				map.get(rs.getInt("pr.id_unidad_ejecucion")).getParcelas().put(rs.getInt("pr.id_parcela_resultante"), new ParcelaResultante(rs.getInt("pr.id_parcela_resultante"), rs.getInt("pr.id_tipo_ordenacion_pormenorizada"), rs.getInt("pr.id_unidad_ejecucion"), rs.getString("pr.denominacion"), rs.getDouble("superficie"), rs.getDouble("obr_a"), rs.getDouble("obr_n"), rs.getDouble("osr_a"), rs.getDouble("osr_n"), rs.getDouble("ebr_a"), rs.getDouble("ebr_n"), rs.getDouble("esrpb_a"), rs.getDouble("esrpb_n"), rs.getDouble("esrpp_a"), rs.getDouble("esrpp_n"), tipos.get(rs.getInt("pr.id_tipo_ordenacion_pormenorizada"))));
			}
			
			Phase5 p = new Phase5(idPlan, map, tipos);
			
			
			return p;		
		}

		public void updatePhase5(Phase5 p) throws SQLException{
			
			String sql;
			PreparedStatement statement;
			
			for(Entry <Integer, P567UnidadEjecucion> entry : p.getMap().entrySet()){
				
				for(Entry <Integer, ParcelaResultante> pr : entry.getValue().getParcelas().entrySet()){

					sql = "UPDATE parcela_resultante SET id_tipo_ordenacion_pormenorizada = ?, superficie = ?, obr_a = ?, obr_n = ?, osr_a = ?, osr_n = ?, ebr_a = ?, ebr_n = ?, esrpb_a = ?, esrpb_n = ?, esrpp_a = ?, esrpp_n = ? WHERE id_parcela_resultante = ?";
					statement = (PreparedStatement) dao.getConection().prepareStatement(sql);
					statement.setInt(1, pr.getValue().getIdTipoOrdenacionPormenorizada());		
					statement.setDouble(2, pr.getValue().getSuperficie());
					statement.setDouble(3, pr.getValue().getObrA());
					statement.setDouble(4, pr.getValue().getObrN());
					statement.setDouble(5, pr.getValue().getOsrA());
					statement.setDouble(6, pr.getValue().getOsrN());
					statement.setDouble(7, pr.getValue().getEbrA());
					statement.setDouble(8, pr.getValue().getEbrN());
					statement.setDouble(9, pr.getValue().getEsrpbA());
					statement.setDouble(10, pr.getValue().getEsrpbN());
					statement.setDouble(11, pr.getValue().getEsrppA());
					statement.setDouble(12, pr.getValue().getEsrppN());
					statement.setInt(13, pr.getValue().getIdParcelaResultante());
					statement.execute();
					
				}

			
				
			}
			
		}
		
		//FIN FASE 5
		
		//FASE 6
		
				public Phase6 getPhase6(int idPlan) throws SQLException{
					
					String sql;
					PreparedStatement statement;
					ResultSet rs;
					
					
					//Obtenemos el numero de unidades de ejecución que hay en un plan y la información de cada una de ellas.
					sql = "SELECT id_unidad_ejecucion, denominacion FROM unidad_ejecucion WHERE id_plan = " + idPlan;
					
					statement = (PreparedStatement) dao.getConection().prepareStatement(sql);
					
					rs = statement.executeQuery();
					
					HashMap<Integer, P567UnidadEjecucion> map = new HashMap<Integer, P567UnidadEjecucion>();
					
					while(rs.next()){
						map.put(rs.getInt("id_unidad_ejecucion"), new P567UnidadEjecucion(rs.getString("denominacion"), rs.getInt("id_unidad_ejecucion"), idPlan));
					}
					
					//Obtenemos los tipos de ordenacion pormenorizados
					sql = "SELECT id_tipo_ordenacion_pormenorizada, denominacion, coef_srpb, coef_srpp, coef_br FROM tipo_ordenacion_pormenorizada WHERE id_plan = " + idPlan;
					
					statement = (PreparedStatement) dao.getConection().prepareStatement(sql);
					
					rs = statement.executeQuery();
					
					HashMap<Integer, OrdenacionUrbanisticaPormenorizada> tipos = new HashMap<Integer, OrdenacionUrbanisticaPormenorizada>();
					
					while(rs.next()){
						tipos.put(rs.getInt("id_tipo_ordenacion_pormenorizada"), new OrdenacionUrbanisticaPormenorizada(rs.getString("denominacion"), rs.getDouble("coef_srpb"), rs.getDouble("coef_srpp"), rs.getDouble("coef_br"), rs.getInt("id_tipo_ordenacion_pormenorizada")));
					}
					
					//Obtenemos las parcelas resultantes
					sql = "SELECT pr.id_unidad_ejecucion, pr.denominacion, pr.id_parcela_resultante, pr.id_tipo_ordenacion_pormenorizada, pr.ebr_n, pr.esrpb_n, pr.esrpp_n, pr.porcentaje_ayuntamiento FROM parcela_resultante pr, unidad_ejecucion ue WHERE ue.id_unidad_ejecucion = pr.id_unidad_ejecucion AND ue.id_plan = " + idPlan;
										
					statement = (PreparedStatement) dao.getConection().prepareStatement(sql);
					
					rs = statement.executeQuery();
					
					while(rs.next()){
						map.get(rs.getInt("pr.id_unidad_ejecucion")).getParcelas().put(rs.getInt("pr.id_parcela_resultante"), new ParcelaResultante(rs.getInt("pr.id_parcela_resultante"), rs.getString("pr.denominacion"), rs.getInt("pr.id_tipo_ordenacion_pormenorizada"), rs.getInt("pr.id_unidad_ejecucion"), rs.getDouble("pr.ebr_n"), rs.getDouble("pr.esrpb_n"), rs.getDouble("pr.esrpp_n"), rs.getDouble("porcentaje_ayuntamiento")));
					}
					
					Phase6 p = new Phase6(idPlan, map, tipos);
										
					return p;		
				}
				
				//FIN FASE 6
				
				//FASE 7
				
				public Phase7 getPhase7(int idPlan) throws SQLException{
					
					String sql;
					PreparedStatement statement;
					ResultSet rs;
					
					
					//Obtenemos el numero de unidades de ejecución que hay en un plan y la información de cada una de ellas.
					sql = "SELECT id_unidad_ejecucion, denominacion FROM unidad_ejecucion WHERE id_plan = " + idPlan;
					
					statement = (PreparedStatement) dao.getConection().prepareStatement(sql);
					
					rs = statement.executeQuery();
					
					HashMap<Integer, P567UnidadEjecucion> map = new HashMap<Integer, P567UnidadEjecucion>();
					
					while(rs.next()){
						map.put(rs.getInt("id_unidad_ejecucion"), new P567UnidadEjecucion(rs.getString("denominacion"), rs.getInt("id_unidad_ejecucion"), idPlan));
					}
					
					//Obtenemos los tipos de ordenacion pormenorizados
					sql = "SELECT id_tipo_ordenacion_pormenorizada, denominacion, coef_srpb, coef_srpp, coef_br FROM tipo_ordenacion_pormenorizada WHERE id_plan = " + idPlan;
					
					statement = (PreparedStatement) dao.getConection().prepareStatement(sql);
					
					rs = statement.executeQuery();
					
					HashMap<Integer, OrdenacionUrbanisticaPormenorizada> tipos = new HashMap<Integer, OrdenacionUrbanisticaPormenorizada>();
					
					while(rs.next()){
						tipos.put(rs.getInt("id_tipo_ordenacion_pormenorizada"), new OrdenacionUrbanisticaPormenorizada(rs.getString("denominacion"), rs.getDouble("coef_srpb"), rs.getDouble("coef_srpp"), rs.getDouble("coef_br"), rs.getInt("id_tipo_ordenacion_pormenorizada")));
					}
					
					//Obtenemos las parcelas resultantes
					sql = "SELECT pr.id_unidad_ejecucion, pr.denominacion, pr.id_parcela_resultante, pr.id_tipo_ordenacion_pormenorizada, pr.ebr_n, pr.esrpb_n, pr.esrpp_n, pr.porcentaje_ayuntamiento FROM parcela_resultante pr, unidad_ejecucion ue WHERE ue.id_unidad_ejecucion = pr.id_unidad_ejecucion AND ue.id_plan = " + idPlan;
										
					statement = (PreparedStatement) dao.getConection().prepareStatement(sql);
					
					rs = statement.executeQuery();
					
					while(rs.next()){
						map.get(rs.getInt("pr.id_unidad_ejecucion")).getParcelas().put(rs.getInt("pr.id_parcela_resultante"), new ParcelaResultante(rs.getInt("pr.id_parcela_resultante"), rs.getString("pr.denominacion"), rs.getInt("pr.id_tipo_ordenacion_pormenorizada"), rs.getInt("pr.id_unidad_ejecucion"), rs.getDouble("pr.ebr_n"), rs.getDouble("pr.esrpb_n"), rs.getDouble("pr.esrpp_n"), rs.getDouble("porcentaje_ayuntamiento")));
					}
					
					sql = "SELECT ml.participacion_ayuntamiento FROM marco_legal ml, plan p WHERE p.id_marco_legal = ml.id_marco_legal AND p.id_plan = " + idPlan;
					
					statement = (PreparedStatement) dao.getConection().prepareStatement(sql);
					
					rs = statement.executeQuery();
					
					double participacionAyuntamiento = 0.0;
					
					if(rs.next()){
						participacionAyuntamiento = rs.getDouble("ml.participacion_ayuntamiento");	
					}
					
					Phase7 p = new Phase7(idPlan, map, tipos, participacionAyuntamiento);
										
					return p;		
				}
				
				public void updatePhase7(Phase7 p) throws SQLException{
					
					String sql;
					PreparedStatement statement;
					
					for(Entry <Integer, HashMap<Integer, ParcelaResultante>> entry : p.getParcelas().entrySet()){
						for(Entry <Integer, ParcelaResultante> pr : entry.getValue().entrySet()){

							sql = "UPDATE parcela_resultante SET porcentaje_ayuntamiento = ? WHERE id_parcela_resultante = ?";  
							statement = (PreparedStatement) dao.getConection().prepareStatement(sql);
							statement.setDouble(1, pr.getValue().getPorcentajeAyuntamiento());
							statement.setInt(2, pr.getValue().getIdParcelaResultante());
							statement.execute();
						}
					}
		
				}
					
				//FIN FASE 7
				
				//FASE 8
				
				public Phase8 getPhase8(int idPlan) throws SQLException{
					
					String sql;
					PreparedStatement statement;
					ResultSet rs;
					
					
					//Obtenemos el numero de unidades de ejecución que hay en un plan y la información de cada una de ellas.
					sql = "SELECT id_unidad_ejecucion, denominacion, superficie_espacios_libres, superficie_equipamientos, superficie_red_viaria, numero_plazas_aparcamiento FROM unidad_ejecucion WHERE id_plan = " + idPlan;
					
					statement = (PreparedStatement) dao.getConection().prepareStatement(sql);
					
					rs = statement.executeQuery();
					
					HashMap<Integer, UnidadEjecucion> map = new HashMap<Integer, UnidadEjecucion>();
					
					while(rs.next()){
						map.put(rs.getInt("id_unidad_ejecucion"), new UnidadEjecucion(rs.getDouble("superficie_espacios_libres"), rs.getDouble("superficie_equipamientos"), rs.getDouble("superficie_red_viaria"), rs.getInt("numero_plazas_aparcamiento"), rs.getString("denominacion"), rs.getInt("id_unidad_ejecucion")));
					}
					
					Phase8 p = new Phase8(idPlan, map);
										
					return p;		
				}
				
				public void updatePhase8(Phase8 p) throws SQLException{
					
					String sql;
					PreparedStatement statement;
					
					for(Entry <Integer, UnidadEjecucion> entry : p.getUes().entrySet()){

						sql = "UPDATE unidad_ejecucion SET superficie_espacios_libres = ?, superficie_equipamientos = ?, superficie_red_viaria = ?, numero_plazas_aparcamiento = ? WHERE id_unidad_ejecucion = ?";  
						statement = (PreparedStatement) dao.getConection().prepareStatement(sql);
						statement.setDouble(1, entry.getValue().getSuperficieEspaciosLibres());
						statement.setDouble(2, entry.getValue().getSuperficieEquipamientos());
						statement.setDouble(3, entry.getValue().getSuperficieRedViaria());
						statement.setInt(4, entry.getValue().getNumeroPlazasAparcamiento());
						statement.setInt(5, entry.getValue().getIdUnidadEjecucion());
						statement.execute();
						
					}
		
				}
				
				//FIN FASE 8
}
