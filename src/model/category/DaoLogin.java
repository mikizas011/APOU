package model.category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Dao;

public class DaoLogin {

	private Dao dao;
	
	public DaoLogin(Dao dao){
		this.dao = dao;
	}
	
	public String addUser(String userName, String password){
		
		Connection c = null;
		try{
			
			c = dao.getConection();
			
			c.setAutoCommit(false);
			
			//Metemos el usuario en la base de datos
			String sql = "INSERT INTO usuario (usuario, pass) values (?, ?)";
			
			PreparedStatement statement = (PreparedStatement) dao.getConection().prepareStatement(sql);
			
			statement.setString(1, userName);
			statement.setString(2, password);
			
			statement.executeUpdate();

			
			//Le asignamos el rol de usuario
			sql = "INSERT INTO usuario_rol (usuario, rol) values (?, ?)";
			
			statement = (PreparedStatement) dao.getConection().prepareStatement(sql);
			
			statement.setString(1, userName);
			statement.setString(2, "usuario");
			
			statement.executeUpdate();
			
			c.commit();
			
			dao.getConection().close();
			
			return "Usuario añadido correctamente.";
		}
		catch(SQLException e){
			if(c != null){
				try {
					c.rollback();
					c.close();
				} catch (SQLException ex1) {
					return dao.getMessage(e);
				}
			}
			return dao.getMessage(e);
		} 

	}
	
	

	
	
}
