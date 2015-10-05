package controller;

import org.omg.CORBA.Request;

import com.mysql.fabric.Response;

public class Configuracion {

	private static Configuracion configuracion;
	private int idPlan;
	private int idUser;
	private String root ="http://localhost:8080/APOU/";
	
	
	private Configuracion(){}
	
	public static Configuracion getInstance(){
		if(configuracion == null){
			configuracion = new Configuracion();
		}
		return configuracion;
	}

	public int getIdPlan() {
		return idPlan;
	}

	public void setIdPlan(int idPlan) {
		this.idPlan = idPlan;
	}

	public int getIdUser() {
		return idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	public String getRoot() {
		return root;
	}

	public void setRoot(String root) {
		this.root = root;
	}

}
