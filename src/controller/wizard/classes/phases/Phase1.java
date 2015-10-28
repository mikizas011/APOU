package controller.wizard.classes.phases;

import java.util.HashMap;

public class Phase1 extends Phase{

	private String denominacionPlan, denominacionSector, numeroSector, Municipio, idioma, superficie;
	private HashMap<String, String> ues;
	private HashMap<Integer, String> marcosLegales;
	private int idMarcoLegal;
	
	public Phase1(int idPlan, String denominacionPlan,
			String denominacionSector, String numeroSector, String municipio,
			String idioma, String superficie, int idMarcoLegal) {
		super(idPlan);
		this.denominacionPlan = denominacionPlan;
		this.denominacionSector = denominacionSector;
		this.numeroSector = numeroSector;
		Municipio = municipio;
		this.idioma = idioma;
		this.superficie = superficie;
		this.setIdMarcoLegal(idMarcoLegal);
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

	public HashMap<Integer, String> getMarcosLegales() {
		return marcosLegales;
	}

	public void setMarcosLegales(HashMap<Integer, String> marcosLegales) {
		this.marcosLegales = marcosLegales;
	}

	public int getIdMarcoLegal() {
		return idMarcoLegal;
	}

	public void setIdMarcoLegal(int idMarcoLegal) {
		this.idMarcoLegal = idMarcoLegal;
	}
	
	
	
}


