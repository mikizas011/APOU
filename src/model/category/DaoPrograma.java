package model.category;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import com.mysql.jdbc.SQLError;
import com.mysql.jdbc.Statement;

import controller.Configuracion;
import controller.wizard.classes.EdificabilidadUnidadEjecucion;
import controller.wizard.classes.Municipio;
import controller.wizard.classes.ParcelaResultante;
import controller.wizard.classes.Plan;
import controller.wizard.classes.TipoOrdenacionEstructural;
import controller.wizard.classes.TipoOrdenacionPormenorizada;
import controller.wizard.classes.UnidadEjecucion;
import controller.wizard.classes.u.ParcelaAportada;
import model.Dao;

public class DaoPrograma {

	private Dao dao;
	
	public DaoPrograma(Dao dao){
		this.dao = dao;
	}
	
	public int newPlan(String usuario) throws SQLException{
		String ret = "";
		
			
		String sql = "INSERT INTO plan (usuario, fecha_creacion, fecha_ultima_modificacion, fase) values (?, ?, ?, ?)";
		
		PreparedStatement statement = (PreparedStatement) dao.getConection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		
		long date = System.currentTimeMillis();
		
		statement.setString(1, usuario);
		
		statement.setDate(2, new Date(date));
		
		statement.setDate(3, new Date(date));

		statement.setInt(4, 1);

		statement.execute();

		ResultSet rs = statement.getGeneratedKeys();
	    rs.next();
	    int id = rs.getInt(1);
	    
		dao.getConection().close();
			
		return id;
		
		
	}
	
	//FASE 0
	
	public String newPlan(String planName, String municipio, String usuario) {
		
		
		
		try {
			String sql = "INSERT INTO plan (denominacion, id_municipio, usuario) values (?, ?, ?)";
			
			PreparedStatement statement = (PreparedStatement) dao.getConection().prepareStatement(sql);
			
			statement.setString(1, planName);
			
			statement.setInt(2, Integer.parseInt(municipio));
			
			statement.setString(3, usuario);

			
			statement.execute();

			dao.getConection().close();
			
			return "Plan añadido con exito.";
			
		} catch (SQLException e) {
			return "Ha habido un problema creando el plan.";
		}
		
		
	}
	
	//FASE 1
	
	public void fillPhase1(int idMunicipio, String denominacionPlan, String numeroSector, String nombreSector, ArrayList<UnidadEjecucion> unidadesEjecucion) throws SQLException{
		

		String sql = "UPDATE plan SET denominacion = '"+denominacionPlan+"', numero_sector = '"+numeroSector+"', nombre_sector = '"+nombreSector+"', id_municipio = "+idMunicipio+" WHERE id_plan = " + Configuracion.getInstance().getIdPlan();
		
		

		PreparedStatement statement = dao.getConection().prepareStatement(sql);

		statement.execute();
		
		for(int i = 0; i < unidadesEjecucion.size(); i++){
			UnidadEjecucion ue = unidadesEjecucion.get(i);
			newUnidadEjecucion(ue.getDenominacion(), ue.getNumeroParcelasAportadas());
		}
		
		dao.getConection().close();
		
	}
	
	public void newUnidadEjecucion(String denominacion, int numero_parcelas) throws SQLException{

		String sql = "INSERT INTO unidad_ejecucion (denominacion, numero_parcelas_aportadas, id_plan) values (?, ?, ?)";

		PreparedStatement statement = (PreparedStatement) dao.getConection().prepareStatement(sql);
		
		statement.setString(1, denominacion);
		statement.setInt(2, numero_parcelas);
		statement.setInt(3, Configuracion.getInstance().getIdPlan());
		
		statement.execute();
	}
	
	//FIN FASE 1
	
	//FASE 2
	
	public void fillPhase2(ArrayList<UnidadEjecucion> unidadesEjecucion) throws SQLException{
		
		
		
		for(int i = 0; i < unidadesEjecucion.size();i++){
			for(int e = 0; e < unidadesEjecucion.get(i).getParcelasAportadas().size(); e++){
				newParcelaAportada(unidadesEjecucion.get(i).getParcelasAportadas().get(e));
			}			
			
			String sql = "UPDATE unidad_ejecucion SET superficie_servidumbre = "+unidadesEjecucion.get(i).getSuperficieServidumbre()+" WHERE id_unidad_ejecucion = " + unidadesEjecucion.get(i).getIdUnidadEjecucion();
			
			PreparedStatement statement = dao.getConection().prepareStatement(sql);

			statement.execute();
		}
		
		dao.getConection().close();

		
	}
	
	public void newParcelaAportada(ParcelaAportada parcela) throws SQLException{
		
		String sql = "INSERT INTO parcela_aportada (denominacion, propietario, superficie, id_unidad_ejecucion, id_dominio) values (?, ?, ?, ?, ?)";
		
		PreparedStatement statement = (PreparedStatement) dao.getConection().prepareStatement(sql);
		
		statement.setString(1, parcela.getDenominacion());
		statement.setString(2, parcela.getPropietario());
		statement.setDouble(3, parcela.getSuperficie());
		statement.setInt(4, parcela.getIdUnidadEjecucion());
		statement.setInt(5, parcela.getIdDominio());
		
		statement.execute();

		
	} 
	
	

	
	//FIN FASE 2
	
	//FASE 3
	
	public void fillPhase3(ArrayList<TipoOrdenacionEstructural> tiposOrdenacionEstructural, double edificabilidadMaximaSobreRasante, double edificabilidadMaximaBajoRasante) throws SQLException{
		
		

		for(int i = 0; i < tiposOrdenacionEstructural.size(); i++){
			newTipoOrdenacionEstructural(tiposOrdenacionEstructural.get(i));
		}
		
		String sql = "UPDATE plan SET edif_max_sr = "+edificabilidadMaximaSobreRasante+", edif_max_br = " + edificabilidadMaximaBajoRasante + " WHERE id_plan = " + Configuracion.getInstance().getIdPlan();
		
		PreparedStatement statement = dao.getConection().prepareStatement(sql);

		statement.execute();
		
		dao.getConection().close();

	}
	
	public void newTipoOrdenacionEstructural(TipoOrdenacionEstructural tipo) throws SQLException{
		
		String sql = "INSERT INTO tipo_ordenacion_global (denominacion, superficie, id_plan) values (?, ?, ?)";
		
		PreparedStatement statement = (PreparedStatement) dao.getConection().prepareStatement(sql);
		
		statement.setString(1, tipo.getDenominacion());
		statement.setDouble(2, tipo.getSuperficie());
		statement.setInt(3, Configuracion.getInstance().getIdPlan());

		
		statement.execute();

		
	} 
	
	
	//FIN FASE 3
	
	//FASE 4
	
	public void fillPhase4(ArrayList<TipoOrdenacionPormenorizada> tiposOrdenacionPormenorizada, ArrayList<UnidadEjecucion> unidadesEjecucion) throws SQLException{
		
		

		for(int i = 0; i < tiposOrdenacionPormenorizada.size(); i++){
			newTipoOrdenacionPormenorizada(tiposOrdenacionPormenorizada.get(i));
		}
		
		for(int i = 0; i < unidadesEjecucion.size(); i++){
			
			String sql = "UPDATE unidad_ejecucion SET numero_parcelas_resultantes = "+unidadesEjecucion.get(i).getNumeroParcelasResultantes() + " WHERE id_unidad_ejecucion = " + unidadesEjecucion.get(i).getIdUnidadEjecucion();
			
			PreparedStatement statement = dao.getConection().prepareStatement(sql);

			statement.execute();
			
		}
		
		dao.getConection().close();

	}
	
	public void newTipoOrdenacionPormenorizada(TipoOrdenacionPormenorizada tipo) throws SQLException{
		
		String sql = "INSERT INTO tipo_ordenacion_pormenorizada (denominacion, coef_srpb, coef_srpp, coef_br, id_plan) values (?, ?, ?, ?, ?)";
		
		PreparedStatement statement = (PreparedStatement) dao.getConection().prepareStatement(sql);
		
		statement.setString(1, tipo.getDenominacion());
		statement.setDouble(2, tipo.getCoefSRPB());
		statement.setDouble(3, tipo.getCoefSRPP());
		statement.setDouble(4, tipo.getCoefBR());
		statement.setInt(5, Configuracion.getInstance().getIdPlan());

		
		statement.execute();

	}

	//FIN FASE 4
	
	//FASE 5
	
	public void fillPhase5(ArrayList<UnidadEjecucion> unidadesEjecucion) throws SQLException{
		
		
		
		for(int i = 0; i < unidadesEjecucion.size();i++){
			for(int e = 0; e < unidadesEjecucion.get(i).getParcelasResultantes().size(); e++){
				newParcelaResultante(unidadesEjecucion.get(i).getParcelasResultantes().get(e));
			}			
		}
		
		dao.getConection().close();

	}
	
	public void newParcelaResultante(ParcelaResultante parcela) throws SQLException{
		
		String sql = "INSERT INTO parcela_resultante (denominacion, superficie, obr_a, obr_n, osr_a, osr_n, ebr_a, ebr_n, esrpb_a, esrpb_n, esrpp_a, esrpp_n, id_tipo_ordenacion_pormenorizada, id_unidad_ejecucion) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		PreparedStatement statement = (PreparedStatement) dao.getConection().prepareStatement(sql);
		
		statement.setString(1, parcela.getDenominacion());
		statement.setDouble(2, parcela.getSuperficie());
		statement.setDouble(3, parcela.getObrA());
		statement.setDouble(4, parcela.getObrN());
		statement.setDouble(5, parcela.getOsrA());
		statement.setDouble(6, parcela.getOsrN());
		statement.setDouble(7, parcela.getEbrA());
		statement.setDouble(8, parcela.getEbrN());
		statement.setDouble(9, parcela.getEsrpbA());
		statement.setDouble(10, parcela.getEsrpbN());
		statement.setDouble(11, parcela.getEsrppA());
		statement.setDouble(12, parcela.getEsrppN());
		statement.setInt(13, parcela.getIdTipoOrdenacionPormenorizada());
		statement.setInt(14, parcela.getIdUnidadEjecucion());
		statement.execute();

	}
	
	//FIN FASE 5
	
	//FASE 6

	
	public ArrayList<TipoOrdenacionPormenorizada> getTiposOrdenacionPormenorizadas() throws SQLException{
		
		ArrayList<TipoOrdenacionPormenorizada> tipos = new ArrayList<TipoOrdenacionPormenorizada>();
		
		String sql = "SELECT * FROM tipo_ordenacion_pormenorizada WHERE id_plan = " + Configuracion.getInstance().getIdPlan();
		
		PreparedStatement statement = (PreparedStatement) dao.getConection().prepareStatement(sql);

		ResultSet rs = statement.executeQuery();

		while(rs.next()){
			tipos.add(new TipoOrdenacionPormenorizada(rs.getInt("id_tipo_ordenacion_pormenorizada"), Configuracion.getInstance().getIdPlan(), rs.getString("denominacion"), rs.getDouble("coef_srpb"), rs.getDouble("coef_srpp"), rs.getDouble("coef_br")));
		}
		
		dao.getConection().close();
		
		return tipos;
		
	}
	

	
	public TipoOrdenacionPormenorizada calcularEdificabilidadesDeTipoPormenorizadoPorUnidadEjecucion(TipoOrdenacionPormenorizada tipo, int idUnidadEjecucion) throws SQLException{

				
		String sql = "SELECT SUM(ebr_n) FROM parcela_resultante WHERE id_tipo_ordenacion_pormenorizada = " + tipo.getIdTipoOrdenacionPormenorizada() + " AND id_unidad_ejecucion = " + idUnidadEjecucion;
		
		PreparedStatement statement = (PreparedStatement) dao.getConection().prepareStatement(sql);

		ResultSet rs = statement.executeQuery();
		
		while(rs.next()){
			tipo.setEdifBRPond((rs.getDouble(1) * tipo.getCoefBR()));
		}
		
		sql = "SELECT SUM(esrpb_n) FROM parcela_resultante WHERE id_tipo_ordenacion_pormenorizada = " + tipo.getIdTipoOrdenacionPormenorizada() + " AND id_unidad_ejecucion = " + idUnidadEjecucion;
		
		statement = (PreparedStatement) dao.getConection().prepareStatement(sql);

		rs = statement.executeQuery();

		while(rs.next()){
			tipo.setEdifSRPBPond((rs.getDouble(1) * tipo.getCoefSRPB()));

		}

		sql = "SELECT SUM(esrpp_n) FROM parcela_resultante WHERE id_tipo_ordenacion_pormenorizada = " + tipo.getIdTipoOrdenacionPormenorizada() + " AND id_unidad_ejecucion = " + idUnidadEjecucion;
		
		statement = (PreparedStatement) dao.getConection().prepareStatement(sql);

		rs = statement.executeQuery();

		while(rs.next()){
			tipo.setEdifSRPPPond((rs.getDouble(1) * tipo.getCoefSRPP()));

		}

		return tipo;
		
	}
	
	
	
	//FIN FASE 6
	
	//FASE 7
	
	public ParcelaResultante modificarPorcentajeAyuntamientoAParcela(ParcelaResultante parcela, double porcentaje) throws SQLException{
		
		String sql = "UPDATE parcela_resultante SET porcentaje_ayuntamiento = "+porcentaje + " WHERE id_parcela_resultante = " + parcela.getIdParcelaResultante();
		
		PreparedStatement statement = dao.getConection().prepareStatement(sql);

		statement.execute();
		
		dao.getConection().close();
		
		parcela.setPorcentajeAyuntamiento(porcentaje);
		
		return parcela;
	}
	
	public double calcularEdificabilidadCorrespondienteAyuntamientoPorUnidadEjecucion(int idUnidadEjecucion) throws SQLException{
		
		double edificabilidad = 0;
		
		ArrayList<ParcelaResultante> parcelas = cargarParcelasResultantesAUnidadDeEjecucion(idUnidadEjecucion);
		
		HashMap<Integer, TipoOrdenacionPormenorizada> tipos = getTiposOrdenacionPormenorizadasHM();
		
		for(int i = 0; i < parcelas.size(); i++){
			if(parcelas.get(i).getPorcentajeAyuntamiento() > 0){
				double parcelaPonderada = 0;
				parcelaPonderada += ( (parcelas.get(i).getEbrN()) * (tipos.get(parcelas.get(i).getIdTipoOrdenacionPormenorizada()).getCoefBR()) );
				parcelaPonderada += ( (parcelas.get(i).getEsrpbN()) * (tipos.get(parcelas.get(i).getIdTipoOrdenacionPormenorizada()).getCoefSRPB()) );
				parcelaPonderada += ( (parcelas.get(i).getEsrppN()) * (tipos.get(parcelas.get(i).getIdTipoOrdenacionPormenorizada()).getCoefSRPP()) );
				edificabilidad += ( parcelaPonderada * parcelas.get(i).getPorcentajeAyuntamiento() );
			}
			
		}
		
		return edificabilidad;
		
	}
	
	//FIN FASE 7
	
	//FASE 8
	
	
	
	//FIN FASE 8
	
	//FASE 9
	
	public void fillPhase9(ArrayList<UnidadEjecucion> unidadesEjecucion) throws SQLException{

		for(int i = 0; i < unidadesEjecucion.size();i++){
			
			String sql = "UPDATE unidad_ejecucion SET superficie_espacios_libres = "+unidadesEjecucion.get(i).getSuperficieEspaciosLibres()+", superficie_equipamientos = "+unidadesEjecucion.get(i).getSuperficieEquipamientos()+", superficie_red_viaria = "+unidadesEjecucion.get(i).getSuperficieRedViaria()+", numero_plazas_aparcamiento = "+unidadesEjecucion.get(i).getNumeroPlazasAparcamiento()+" WHERE id_unidad_ejecucion = " + unidadesEjecucion.get(i).getIdUnidadEjecucion();
			
			PreparedStatement statement = dao.getConection().prepareStatement(sql);

			statement.execute();
			
		}
		
		dao.getConection().close();

	}
	
	//FIN FASE 9
	
	//FASE 10
	
	public void fillPhase10(ArrayList<Integer> normativas) throws SQLException{
		
		for(int i = 0; i < normativas.size(); i++){
			newNormativaPlan(normativas.get(i));
		}
		dao.getConection().close();
		
	}
	
	public void newNormativaPlan(int idNormativa) throws SQLException{

		String sql = "INSERT INTO normativa_plan (id_normativa, id_plan) values (?, ?)";

		PreparedStatement statement = (PreparedStatement) dao.getConection().prepareStatement(sql);
		
		statement.setInt(1, idNormativa);
		statement.setInt(2, Configuracion.getInstance().getIdPlan());
		
		statement.execute();
	}
	
	//FIN FASE 10
	
	//OTRAS OPERACIONES COMUNES
	
	public ArrayList<UnidadEjecucion> getUnidadesEjecucion(int idPlan) throws SQLException{
		
		ArrayList<UnidadEjecucion> unidadesEjecucion = new ArrayList<UnidadEjecucion>();
		
		String sql = "SELECT * FROM unidad_ejecucion WHERE id_plan = " + idPlan;
		
		PreparedStatement statement = (PreparedStatement) dao.getConection().prepareStatement(sql);

		ResultSet rs = statement.executeQuery();

		while(rs.next()){
			unidadesEjecucion.add(new UnidadEjecucion(rs.getInt("id_unidad_ejecucion"), rs.getInt("numero_parcelas_aportadas"), rs.getInt("numero_parcelas_resultantes"), rs.getInt("id_plan"), rs.getString("denominacion"), rs.getDouble("superficie_servidumbre"), rs.getDouble("superficie_espacios_libres"), rs.getDouble("superficie_equipamientos"), rs.getDouble("superficie_red_viaria"), rs.getInt("numero_plazas_aparcamiento")));
		}
		
		return unidadesEjecucion;
		
	}
	
	public ArrayList<UnidadEjecucion> getUnidadesEjecucionConParcelasAportadas(int idPlan) throws SQLException{

		

		ArrayList<UnidadEjecucion> unidadesEjecucion = getUnidadesEjecucion(idPlan);
		for(int i = 0; i < unidadesEjecucion.size(); i++){
			unidadesEjecucion.get(i).setParcelasAportadas(cargarParcelasAportadasAUnidadDeEjecucion(unidadesEjecucion.get(i).getIdUnidadEjecucion()));
		}
		dao.getConection().close();
		return unidadesEjecucion;
	}
	
	public ArrayList<UnidadEjecucion> getUnidadesEjecucionConParcelasResultantes (int idPlan) throws SQLException{

		

		ArrayList<UnidadEjecucion> unidadesEjecucion = getUnidadesEjecucion(idPlan);
		
		for(int i = 0; i < unidadesEjecucion.size(); i++){
			unidadesEjecucion.get(i).setParcelasResultantes(cargarParcelasResultantesAUnidadDeEjecucion(unidadesEjecucion.get(i).getIdUnidadEjecucion()));
		}
		dao.getConection().close();
		return unidadesEjecucion;
	}
	
	public ArrayList<UnidadEjecucion> getUnidadesEjecucionConParcelasResultantesAportadas (int idPlan) throws SQLException{

		

		ArrayList<UnidadEjecucion> unidadesEjecucion = getUnidadesEjecucion(idPlan);
		for(int i = 0; i < unidadesEjecucion.size(); i++){
			unidadesEjecucion.get(i).setParcelasResultantes(cargarParcelasResultantesAUnidadDeEjecucion(unidadesEjecucion.get(i).getIdUnidadEjecucion()));
			unidadesEjecucion.get(i).setParcelasAportadas(cargarParcelasAportadasAUnidadDeEjecucion(unidadesEjecucion.get(i).getIdUnidadEjecucion()));
		}
		dao.getConection().close();
		return unidadesEjecucion;
	}
	
	public ArrayList<ParcelaAportada> cargarParcelasAportadasAUnidadDeEjecucion(int idUe) throws SQLException{
		
		String sql = "SELECT * FROM parcela_aportada WHERE id_unidad_ejecucion = " + idUe;
		
		PreparedStatement statement = (PreparedStatement) dao.getConection().prepareStatement(sql);
		
		ResultSet rs = statement.executeQuery();
		
		ArrayList<ParcelaAportada> parcelas = new ArrayList<ParcelaAportada>();
		
		while(rs.next()){
			
			parcelas.add(new ParcelaAportada(rs.getString("denominacion"), rs.getString("propietario"), rs.getInt("id_dominio"), idUe, rs.getInt("id_parcela_aportada"), rs.getDouble("superficie")));
			
		}
		
		return parcelas;
		
	}
	
	public ArrayList<ParcelaResultante> cargarParcelasResultantesAUnidadDeEjecucion(int idUe) throws SQLException{
		
		String sql = "SELECT * FROM parcela_resultante WHERE id_unidad_ejecucion = " + idUe;
		
		PreparedStatement statement = (PreparedStatement) dao.getConection().prepareStatement(sql);
		
		ResultSet rs = statement.executeQuery();
		
		ArrayList<ParcelaResultante> parcelas = new ArrayList<ParcelaResultante>();
		
		while(rs.next()){
			
			parcelas.add(new ParcelaResultante(rs.getInt("id_parcela_resultante"), rs.getInt("id_tipo_ordenacion_pormenorizada"), idUe, rs.getString("denominacion"), rs.getDouble("superficie"), rs.getDouble("obr_a"), rs.getDouble("obr_n"), rs.getDouble("osr_a"), rs.getDouble("osr_n"), rs.getDouble("ebr_a"), rs.getDouble("ebr_n"), rs.getDouble("esrpb_a"), rs.getDouble("esrpb_n"), rs.getDouble("esrpp_a"), rs.getDouble("esrpp_n"), rs.getDouble("porcentaje_ayuntamiento")));
			
		}
		
		return parcelas;
		
	}
	
	public HashMap<Integer, TipoOrdenacionPormenorizada> getTiposOrdenacionPormenorizadasHM() throws SQLException{
		
		ArrayList<TipoOrdenacionPormenorizada> tipos = getTiposOrdenacionPormenorizadas();
		
		HashMap<Integer, TipoOrdenacionPormenorizada> map = new HashMap<Integer, TipoOrdenacionPormenorizada>();
		
		for(int i = 0; i < tipos.size(); i++){
			map.put(tipos.get(i).getIdTipoOrdenacionPormenorizada(), tipos.get(i));
		}
				
		return map;	
	}
	

	
	public ArrayList<Plan> getPlanes(String userName) throws SQLException{
		
		ArrayList<Plan> planes = new ArrayList<Plan>();
		
		String sql = "SELECT * FROM plan WHERE usuario = '"+userName+"'";
		
		PreparedStatement statement = (PreparedStatement) dao.getConection().prepareStatement(sql);

		ResultSet rs = statement.executeQuery();

		
		while(rs.next()){
			planes.add(new Plan(rs.getInt("id_plan"), rs.getString("denominacion"), rs.getString("numero_sector"), rs.getString("nombre_sector"), "", 0.0, 0.0, rs.getDate("fecha_creacion"), rs.getDate("fecha_ultima_modificacion"), rs.getString("idioma"), rs.getInt("fase"), rs.getString("municipio"), rs.getDouble("superficie")));
		}

		
		dao.getConection().close();
		
		return planes;
		
	}
	
	public Plan getPlan(int id) throws SQLException{
		
		Plan plan = null;
		
		String sql = "SELECT * FROM plan WHERE id_plan = '"+id+"'";
		
		PreparedStatement statement = (PreparedStatement) dao.getConection().prepareStatement(sql);

		ResultSet rs = statement.executeQuery();

		
		if(rs.next()){
			plan = new Plan(rs.getInt("id_plan"), rs.getString("denominacion"), rs.getString("numero_sector"), rs.getString("nombre_sector"), "", 0.0, 0.0, rs.getDate("fecha_creacion"), rs.getDate("fecha_ultima_modificacion"), rs.getString("idioma"), rs.getInt("fase"), rs.getString("municipio"), rs.getDouble("superficie"));
		}

		dao.getConection().close();
		
		return plan;
		
	}
	
	public void pruebaIn() throws SQLException{
		
		
		ArrayList<String> ues = new ArrayList<String>();
		
		ues.add("UE1");
		ues.add("UE2");
		ues.add("UE3");
		
		for(int i = 0; i < ues.size(); i++){
			
			System.out.println(ues.get(i));
			
				String sql = "UPDATE unidad_ejecucion SET numero_parcelas_aportadas = 6 WHERE id_plan=15 AND denominacion = '" + ues.get(i)+"'";
				PreparedStatement statement;
				statement = dao.getConection().prepareStatement(sql);
				int suc = statement.executeUpdate();
				
				if(suc == 0){
					sql = "INSERT INTO unidad_ejecucion (denominacion, numero_parcelas_aportadas, id_plan) values (?, ?, ?)";
					
					statement = (PreparedStatement) dao.getConection().prepareStatement(sql);
					
					statement.setString(1, ues.get(i));
									
					statement.setInt(2, 6);
					
					statement.setInt(3, 15);
	
					statement.execute();
				}

				

			

			
			
		}
		
		
		
	}
	
	
	
	//FIN OTRAS OPERACIONES COMUNES

	
}

