package controller.wizard.classes;

import java.io.Serializable;

public class Municipio implements Serializable{


	private static final long serialVersionUID = 1L;
	private String municipio;
	
	
	
	public Municipio(String municipio) {
		super();
		this.municipio = municipio;
	}



	public String getMunicipio() {
		return municipio;
	}



	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}
	

	
}
