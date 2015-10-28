package controller.wizard.classes;

import java.util.HashMap;

public class P567UnidadEjecucion {

	private String denominacion;
	private int idUnidadEjecucion, idPlan;
	private HashMap<Integer, ParcelaResultante> parcelas;
	
	
	
	
	
	public P567UnidadEjecucion(String denominacion, int idUnidadEjecucion, int idPlan) {
		super();
		this.denominacion = denominacion;
		this.idUnidadEjecucion = idUnidadEjecucion;
		this.idPlan = idPlan;
		this.parcelas = new HashMap<Integer, ParcelaResultante>();
	}
	
	
	public String getDenominacion() {
		return denominacion;
	}
	public void setDenominacion(String denominacion) {
		this.denominacion = denominacion;
	}
	public int getIdUnidadEjecucion() {
		return idUnidadEjecucion;
	}
	public void setIdUnidadEjecucion(int idUnidadEjecucion) {
		this.idUnidadEjecucion = idUnidadEjecucion;
	}
	public int getIdPlan() {
		return idPlan;
	}
	public void setIdPlan(int idPlan) {
		this.idPlan = idPlan;
	}
	public HashMap<Integer, ParcelaResultante> getParcelas() {
		return parcelas;
	}
	public void setParcelas(HashMap<Integer, ParcelaResultante> parcelas) {
		this.parcelas = parcelas;
	}
	
}

