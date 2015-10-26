package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import model.category.DaoLogin;
import model.category.DaoPrograma;
import model.category.DaoWizard;

public class Dao {

	private String server = "localhost";
	private String port = "3306";
	private String database = "apou_ddbb";
	private String userName = "apou";
	private String pass = "apouddbbpassword";
	
	private DaoLogin login;
	private DaoPrograma programa;
	private DaoWizard wizard;
	
	public Dao(){
		login = new DaoLogin(this);
		programa = new DaoPrograma(this);
		wizard = new DaoWizard(this);
	}

	public Connection getConection(){
		
		try{
			Class.forName("org.gjt.mm.mysql.Driver");
			return DriverManager.getConnection("jdbc:mysql://"+server+":"+port+"/"+database, userName, pass);	
		}
		catch (ClassNotFoundException e) {
			System.err.print("modelo - Dao - getConnection: " + e.getMessage());
			e.printStackTrace();
		} catch (SQLException e) {
			System.err.print("modelo - Dao - getConnection: " + e.getMessage());
		}
		
		return null;
		
	}
	
	public DaoLogin getLogin(){
		return this.login;
	}
	
	public DaoPrograma getPrograma(){
		return this.programa;
	}
	
	public DaoWizard getWizard(){
		return this.wizard;
	}
	
	public String getMessage(Exception e){
		if(e.getMessage().indexOf("Duplicate entry") != -1){
			return "Ya existe una entrada con este valor.";
		}
		else if(e.getMessage().indexOf("a foreign key constraint fails") != -1){
			return "Se ha producido un error interno.";
		}
		else{
			return e.getMessage();
		}
	}
	
	public void close(){
		try {
			this.getConection().close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
