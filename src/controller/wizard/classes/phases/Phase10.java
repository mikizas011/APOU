package controller.wizard.classes.phases;

import java.util.HashMap;

import controller.wizard.classes.Normativa;

public class Phase10 extends Phase{
	private HashMap<Integer, Normativa> todas;
	private String municipio;
	

	public Phase10(int idPlan, HashMap<Integer, Normativa> todas, String municipio) {
		super(idPlan);
		this.todas = todas;
		this.setMunicipio(municipio);
	}


	public HashMap<Integer, Normativa> getTodas() {
		return todas;
	}


	public void setTodas(HashMap<Integer, Normativa> todas) {
		this.todas = todas;
	}


	public String getMunicipio() {
		return municipio;
	}


	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

	
	
	
}
