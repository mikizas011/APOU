package model.category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import controller.wizard.classes.Municipio;
import controller.wizard.classes.ParcelaAportada;
import controller.wizard.classes.Phase1;
import controller.wizard.classes.Plan;
import controller.wizard.classes.UnidadEjecucion;
import model.Dao;

public class DaoWizard {

	private Dao dao;
	
	public DaoWizard(Dao dao){
		this.dao = dao;
	}
	
	//NO CORRESPONDE A NINGUNA FASE
		
		//CARGAR PLAN
		
		public int getFase(int idPlan) throws SQLException{
			
			String sql = "SELECT fase FROM plan WHERE id_plan = "+idPlan;

			PreparedStatement statement = (PreparedStatement) dao.getConection().prepareStatement(sql);

			ResultSet rs = statement.executeQuery();
			
			if(rs.next()){
				return rs.getInt("fase");
			}
			
			return -1;
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
		
		sql = "SELECT denominacion, numero_parcelas_aportadas FROM unidad_ejecucion WHERE id_plan = "+idPlan;

		statement = (PreparedStatement) dao.getConection().prepareStatement(sql);
		
		rs = statement.executeQuery();
		
		while(rs.next()){
			System.out.println(rs.getString("denominacion"));
			ues.put(rs.getString("denominacion"), rs.getInt("numero_parcelas_aportadas")+"");
		}
		
		p.setUes(ues);
		
		return p;
		
		
		
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
	
	
	
	
	
	
}
