package controller.wizard.classes;

import java.sql.SQLException;

import model.Dao;

public class OrdenacionUrbanisticaPormenorizada {

	private int idOrdenacionPormenorizada, idPlan;
	private String denominacion;
	private double coefSRPB, coefSRPP, coefBR, edifBRPond, edifSRPBPond, edifSRPPPond;
	
	
	
	public OrdenacionUrbanisticaPormenorizada(String denominacion, double coefSRPB,
			double coefSRPP, double coefBR, int idOrdenacionPormenorizada) {
		super();
		this.denominacion = denominacion;
		this.coefSRPB = coefSRPB;
		this.coefSRPP = coefSRPP;
		this.coefBR = coefBR;
		this.idOrdenacionPormenorizada = idOrdenacionPormenorizada;
	}




	@Override
	public String toString() {
		return "TipoOrdenacionPormenorizada [denominacion=" + denominacion
				+ ", edifSRPB=" + edifSRPBPond + ", edifSRPP=" + edifSRPPPond
				+ ", edifBR=" + edifBRPond + ", edificabilidadPonderada="
				+ (edifBRPond + edifSRPBPond + edifSRPPPond) + "]";
	}
	




	public int getIdOrdenacionPormenorizada() {
		return idOrdenacionPormenorizada;
	}
	public void setIdOrdenacionPormenorizada(int idTipoOrdenacionPormenorizada) {
		this.idOrdenacionPormenorizada = idTipoOrdenacionPormenorizada;
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
	public double getCoefSRPB() {
		return coefSRPB;
	}
	public void setCoefSRPB(double coefSRPB) {
		this.coefSRPB = coefSRPB;
	}
	public double getCoefSRPP() {
		return coefSRPP;
	}
	public void setCoefSRPP(double coefSRPP) {
		this.coefSRPP = coefSRPP;
	}
	public double getCoefBR() {
		return coefBR;
	}
	public void setCoefBR(double coefBR) {
		this.coefBR = coefBR;
	}
	public double getEdifPond() {
		return (edifBRPond + edifSRPBPond + edifSRPPPond);
	}
	public double getEdifBRPond() {
		return edifBRPond;
	}
	public void setEdifBRPond(double edifBRPond) {
		this.edifBRPond = edifBRPond;
	}
	public double getEdifSRPBPond() {
		return edifSRPBPond;
	}
	public void setEdifSRPBPond(double edifSRPBPond) {
		this.edifSRPBPond = edifSRPBPond;
	}
	public double getEdifSRPPPond() {
		return edifSRPPPond;
	}
	public void setEdifSRPPPond(double edifSRPPPond) {
		this.edifSRPPPond = edifSRPPPond;
	}

	
}
