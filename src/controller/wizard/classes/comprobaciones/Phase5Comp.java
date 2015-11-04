package controller.wizard.classes.comprobaciones;

import controller.wizard.classes.phases.Phase2;

public class Phase5Comp {

	private double edifMaxSR, edifMaxBR, ocupacionEdificacion, factorEdificabilidadSuperficieNeta, factorOcupacionSuperficieNeta;

	private Phase2 fase2;
	
	
	
	public Phase5Comp(double edifMaxSR, double edifMaxBR,
			double ocupacionEdificacion, Phase2 fase2, double factorEdificabilidadSuperficieNeta, double factorOcupacionSuperficieNeta) {
		super();
		this.edifMaxSR = edifMaxSR;
		this.edifMaxBR = edifMaxBR;
		this.ocupacionEdificacion = ocupacionEdificacion;
		this.fase2 = fase2;
		this.factorEdificabilidadSuperficieNeta = factorEdificabilidadSuperficieNeta;
		this.factorOcupacionSuperficieNeta = factorOcupacionSuperficieNeta;
	}
	
	public double getEdifMaxSR() {
		return edifMaxSR;
	}
	public void setEdifMaxSR(double edifMaxSR) {
		this.edifMaxSR = edifMaxSR;
	}
	public double getEdifMaxBR() {
		return edifMaxBR;
	}
	public void setEdifMaxBR(double edifMaxBR) {
		this.edifMaxBR = edifMaxBR;
	}
	public double getOcupacionEdificacion() {
		return ocupacionEdificacion;
	}
	public void setOcupacionEdificacion(double ocupacionEdificacion) {
		this.ocupacionEdificacion = ocupacionEdificacion;
	}
	public Phase2 getFase2() {
		return fase2;
	}
	public void setFase2(Phase2 fase2) {
		this.fase2 = fase2;
	}
	

	public double getFactorOcupacionSuperficieNeta() {
		return factorOcupacionSuperficieNeta;
	}

	public void setFactorOcupacionSuperficieNeta(
			double factorOcupacionSuperficieNeta) {
		this.factorOcupacionSuperficieNeta = factorOcupacionSuperficieNeta;
	}

	public double getFactorEdificabilidadSuperficieNeta() {
		return factorEdificabilidadSuperficieNeta;
	}

	public void setFactorEdificabilidadSuperficieNeta(
			double factorEdificabilidadSuperficieNeta) {
		this.factorEdificabilidadSuperficieNeta = factorEdificabilidadSuperficieNeta;
	}


	
}
