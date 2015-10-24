package model.category;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Statement;

import controller.wizard.classes.Plan;
import model.Dao;

public class DaoProgram {

	private Dao dao;
	
		public DaoProgram(Dao dao){
			this.dao = dao;
		}
	
		public ArrayList<Plan> getPlanes(String userName) throws SQLException{
			
			ArrayList<Plan> planes = new ArrayList<Plan>();
			
			String sql = "SELECT * FROM plan WHERE usuario = '"+userName+"'";
			
			PreparedStatement statement = (PreparedStatement) dao.getConection().prepareStatement(sql);

			ResultSet rs = statement.executeQuery();

			
			while(rs.next()){
				planes.add(new Plan(rs.getInt("id_plan"), rs.getString("denominacion"), rs.getString("numero_sector"), rs.getString("nombre_sector"), "", 0.0, 0.0, rs.getDate("fecha_creacion"), rs.getDate("fecha_ultima_modificacion"), rs.getString("idioma"), rs.getInt("fase"), rs.getString("municipio"), rs.getDouble("superficie")));
			}

			return planes;
			
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
		
		
	}
