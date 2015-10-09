package controller.wizard.classes.phases;

import java.util.ArrayList;
import java.util.HashMap;

public class Phase1 extends Phase{

	private String denominacionPlan, denominacionSector, numeroSector, Municipio, idioma, superficie;
	private HashMap<String, String> ues;
	
	public Phase1(int idPlan, String denominacionPlan,
			String denominacionSector, String numeroSector, String municipio,
			String idioma, String superficie, HashMap<String, String> ues) {
		super(idPlan);
		this.denominacionPlan = denominacionPlan;
		this.denominacionSector = denominacionSector;
		this.numeroSector = numeroSector;
		Municipio = municipio;
		this.idioma = idioma;
		this.superficie = superficie;
		this.ues = ues;
	}

	public String getDenominacionPlan() {
		return denominacionPlan;
	}
	public void setDenominacionPlan(String denominacionPlan) {
		this.denominacionPlan = denominacionPlan;
	}
	public String getDenominacionSector() {
		return denominacionSector;
	}
	public void setDenominacionSector(String denominacionSector) {
		this.denominacionSector = denominacionSector;
	}
	public String getNumeroSector() {
		return numeroSector;
	}
	public void setNumeroSector(String numeroSector) {
		this.numeroSector = numeroSector;
	}
	public String getMunicipio() {
		return Municipio;
	}
	public void setMunicipio(String municipio) {
		Municipio = municipio;
	}
	public String getIdioma() {
		return idioma;
	}
	public void setIdioma(String idioma) {
		this.idioma = idioma;
	}
	public String getSuperficie() {
		return superficie;
	}
	public void setSuperficie(String superficie) {
		this.superficie = superficie;
	}
	public HashMap<String, String> getUes() {
		return ues;
	}
	public void setUes(HashMap<String, String> ues) {
		this.ues = ues;
	}
	
	
	
}


