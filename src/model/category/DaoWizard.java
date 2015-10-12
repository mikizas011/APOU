package model.category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import controller.Configuracion;
import controller.wizard.classes.Municipio;
import controller.wizard.classes.Plan;
import controller.wizard.classes.UnidadEjecucion;
import controller.wizard.classes.phases.Phase1;
import controller.wizard.classes.phases.Phase2;
import controller.wizard.classes.u.P2unidadEjecucion;
import controller.wizard.classes.u.ParcelaAportada;
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
		
		String sql = "SELECT id_plan, denominacion, nombre_sector, numero_sector, municipio, idioma, superficie FROM plan WHERE id_plan = "+idPlan;

		PreparedStatement statement = (PreparedStatement) dao.getConection().prepareStatement(sql);

		ResultSet rs = statement.executeQuery();

		Phase1 p = null;
		
		if(rs.next()){
			p = new Phase1(rs.getInt("id_plan"), rs.getString("denominacion"), rs.getString("nombre_sector"), rs.getString("numero_sector"), rs.getString("municipio"), rs.getString("idioma"), rs.getDouble("superficie")+"", null);
		}
		
		HashMap<String, String> ues = new HashMap<String, String>();
		
		sql = "SELECT denominacion, numero_parcelas_aportadas FROM unidad_ejecucion WHERE id_plan = "+idPlan + " ORDER BY denominacion ASC";

		statement = (PreparedStatement) dao.getConection().prepareStatement(sql);
		
		rs = statement.executeQuery();
		
		while(rs.next()){
			ues.put(rs.getString("denominacion"), rs.getInt("numero_parcelas_aportadas")+"");
		}
		
		p.setUes(ues);
		
		return p;
		
	}
	
	public void updatePhase1(Phase1 p) throws SQLException{
		
		//Modificar datos del plan
		String sql = "UPDATE plan SET denominacion = ?, nombre_sector = ?, numero_sector = ?, municipio = ?, idioma = ?, superficie = ? WHERE id_plan = " + p.getIdPlan();
		
		PreparedStatement statement = (PreparedStatement) dao.getConection().prepareStatement(sql);
		
		statement.setString(1, p.getDenominacionPlan());
		statement.setString(2, p.getDenominacionSector());
		statement.setString(3, p.getNumeroSector());
		statement.setString(4, p.getMunicipio());
		statement.setString(5, p.getIdioma());
		statement.setDouble(6, Double.parseDouble(p.getSuperficie()));

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
		
		sql = "SELECT id_unidad_ejecucion FROM unidad_ejecucion WHERE id_plan = " + p.getIdPlan();

		ArrayList<Integer> idsUnidadesEjecucion = new ArrayList<Integer>();
		
		stmt = (PreparedStatement) dao.getConection().prepareStatement(sql);
		
		rs = stmt.executeQuery();
		
		while(rs.next()){
			idsUnidadesEjecucion.add(rs.getInt("id_unidad_ejecucion"));
		}
		
		for(int i = 0; i < idsUnidadesEjecucion.size(); i++){
			sql = "UPDATE unidad_ejecucion set denominacion = 'UE" + (i+1) + "' WHERE id_unidad_ejecucion = " + idsUnidadesEjecucion.get(i);  
			statement = dao.getConection().prepareStatement(sql);
			statement.executeUpdate();
		}
		
		for(int i = 0; i < idsUnidadesEjecucionAntiguas.size(); i++){
			
			if(!idsUnidadesEjecucion.contains(idsUnidadesEjecucionAntiguas.get(i))){
				//BORRAR TODAS LAS PARCELAS APORTADAS DE LA UD EJECUCION GETI
			}
			else{
				//CHEQUEAR SI SE HA MODIFICADO EL NUMERO DE PARCELAS
				
				
			}
			
		}
		
		
		
	
	}
	
	public ArrayList<Municipio> getMunicipios() throws SQLException{
		
		ArrayList<Municipio> municipios = new ArrayList<Municipio>();
		
		String sql = "SELECT * FROM municipio";
		
		PreparedStatement statement = (PreparedStatement) dao.getConection().prepareStatement(sql);

		ResultSet rs = statement.executeQuery();

		while(rs.next()){
			municipios.add(new Municipio(rs.getString("municipio")));
		}
		
		dao.getConection().close();
		
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
		
		sql = "SELECT pa.id_parcela_aportada, pa.denominacion, pa.propietario, pa.superficie, pa.id_unidad_ejecucion, pa.dominio FROM parcela_aportada pa, unidad_ejecucion ue WHERE pa.id_unidad_ejecucion = ue.id_unidad_ejecucion AND ue.id_plan = " + idPlan;

		statement = (PreparedStatement) dao.getConection().prepareStatement(sql);
		
		rs = statement.executeQuery();
		
		while(rs.next()){
			map.get(rs.getInt("pa.id_unidad_ejecucion")).getParcelas().add(new ParcelaAportada(rs.getString("pa.denominacion"), rs.getString("pa.propietario"), rs.getString("pa.dominio"), rs.getInt("pa.id_unidad_ejecucion"), rs.getInt("pa.id_parcela_aportada"), (int)rs.getDouble("pa.superficie")));
		}
		
		for (Entry<Integer, P2unidadEjecucion> entry : map.entrySet()) {
			
			P2unidadEjecucion ue = entry.getValue();
			
			while(ue.getNumeroParecelasAportadas() > ue.getParcelas().size()){
				ue.getParcelas().add(new ParcelaAportada(ue.getDenominacion() + "PA" + (ue.getParcelas().size()+1), "", "", ue.getIdUnidadEjecucion(), 0));
			}
			
			while(ue.getNumeroParecelasAportadas() < ue.getParcelas().size()){
				ue.getParcelas().remove((ue.getParcelas().size()-1));
			}

		}
		
		Phase2 p = new Phase2(idPlan, map);
		
		return p;		
	}
	
	public void updatePhase2(Phase2 p) throws SQLException{
		
		String sql;
		PreparedStatement statement;
		ResultSet rs;
		
		for(Entry <Integer, P2unidadEjecucion> entry : p.getMap().entrySet()){
			
			sql = "UPDATE unidad_ejecucion SET superficie_servidumbre = ? WHERE id_unidad_ejecucion = ?"; 
			statement = (PreparedStatement) dao.getConection().prepareStatement(sql);
			statement.setDouble(1, entry.getValue().getSuperficieServidumbre());		
			statement.setInt(2, entry.getKey());
			statement.execute();
			
			for(int i = 0; i < entry.getValue().getParcelas().size(); i++){
				
				sql = "UPDATE parcela_aportada SET superficie = ?, propietario = ?, dominio = ? WHERE id_parcela_aportada = ?";
				statement = (PreparedStatement) dao.getConection().prepareStatement(sql);
				statement.setDouble(1, entry.getValue().getParcelas().get(i).getSuperficie());		
				statement.setString(2, entry.getValue().getParcelas().get(i).getPropietario());
				statement.setString(3, entry.getValue().getParcelas().get(i).getDominio());
				statement.setInt(4, entry.getValue().getParcelas().get(i).getIdParcelaAportada());
				statement.execute();
			}
			
		}
		
	}
	
	
}
