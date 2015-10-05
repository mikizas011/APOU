package controller.wizard.classes;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;

public class Plan implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int idPlan, fase;
	private String denominacion, numero_sector, nombre_sector, usuario, idioma, municipio;
	private Double edifMaxSR, edifMaxBR, superficie;
	private Date fechaCreacion, fechaUltimaModificacion;
	private ArrayList<UnidadEjecucion> ues;
	
	
	public Plan(int idPlan, String denominacion, String usuario, Date fechaCreacion, String idioma, String municipio) {
		super();
		this.idPlan = idPlan;
		this.denominacion = denominacion;
		this.usuario = usuario;
		this.fechaCreacion = fechaCreacion;
		this.fechaUltimaModificacion = fechaCreacion;
		this.fase = 1;
		this.idioma = idioma;
		this.municipio = municipio;
	}
	
	
	
	public Plan(int idPlan, String denominacion,
			String numero_sector, String nombre_sector, String usuario,
			Double edifMaxSR, Double edifMaxBR, Date fechaCreacion, Date fechaUltimaModificacion, String idioma, int fase, String municipio, Double superficie) {
		super();
		this.idPlan = idPlan;
		this.denominacion = denominacion;
		this.numero_sector = numero_sector;
		this.nombre_sector = nombre_sector;
		this.usuario = usuario;
		this.edifMaxSR = edifMaxSR;
		this.edifMaxBR = edifMaxBR;
		this.fechaCreacion = fechaCreacion;
		this.fechaUltimaModificacion = fechaUltimaModificacion;
		this.fase = fase;
		this.idioma = idioma;
		this.municipio = municipio;
		this.superficie = superficie;

	}



	public int getIdPlan() {
		return idPlan;
	}
	public void setIdPlan(int idPlan) {
		this.idPlan = idPlan;
	}

	public String getDenominacion() {
		return denominacion;
	}
	public void setDenominacion(String denominacion) {
		this.denominacion = denominacion;
	}
	public String getNumero_sector() {
		return numero_sector;
	}
	public void setNumero_sector(String numero_sector) {
		this.numero_sector = numero_sector;
	}
	public String getNombre_sector() {
		return nombre_sector;
	}
	public void setNombre_sector(String nombre_sector) {
		this.nombre_sector = nombre_sector;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public Double getEdifMaxSR() {
		return edifMaxSR;
	}
	public void setEdifMaxSR(Double edifMaxSR) {
		this.edifMaxSR = edifMaxSR;
	}
	public Double getEdifMaxBR() {
		return edifMaxBR;
	}
	public void setEdifMaxBR(Double edifMaxBR) {
		this.edifMaxBR = edifMaxBR;
	}
	public int getFase() {
		return fase;
	}
	public void setFase(int fase) {
		this.fase = fase;
	}
	public String getIdioma() {
		return idioma;
	}
	public void setIdioma(String idioma) {
		this.idioma = idioma;
	}
	public java.sql.Date getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(java.sql.Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	public java.sql.Date getFechaUltimaModificacion() {
		return fechaUltimaModificacion;
	}
	public void setFechaUltimaModificacion(java.sql.Date fechaUltimaModificacion) {
		this.fechaUltimaModificacion = fechaUltimaModificacion;
	}
	public String getMunicipio() {
		return municipio;
	}
	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}



	public Double getSuperficie() {
		return superficie;
	}



	public void setSuperficie(Double superficie) {
		this.superficie = superficie;
	}



	public ArrayList<UnidadEjecucion> getUes() {
		return ues;
	}



	public void setUes(ArrayList<UnidadEjecucion> ues) {
		this.ues = ues;
	}
	
	
	
}
