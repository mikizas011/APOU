package controller.wizard.classes.comprobaciones;

import controller.wizard.classes.phases.Phase5;

public class Phase8Comp {

	private double superficieTaludesSector, superficieServidumbre, factorOcupacionSuperficieNeta, factorEdificabilidadSuperficieNeta, factorInteresSocial, factorPlazasAparcamientoM2, factorEquipamientosSector, factorDotacionPublica, factorVerdeDotacionPublica, superficieSector;
	private Phase5 fase5;
	
	
	
	public Phase8Comp(double superficieTaludesSector, double superficieServidumbre,
			double factorOcupacionSuperficieNeta,
			double factorEdificabilidadSuperficieNeta,
			double factorInteresSocial, double factorPlazasAparcamientoM2,
			double factorEquipamientosSector, double factorDotacionPublica,
			double factorVerdeDotacionPublica, double superficieSector,
			Phase5 fase5) {
		super();
		this.superficieTaludesSector = superficieTaludesSector;
		this.factorOcupacionSuperficieNeta = factorOcupacionSuperficieNeta;
		this.factorEdificabilidadSuperficieNeta = factorEdificabilidadSuperficieNeta;
		this.factorInteresSocial = factorInteresSocial;
		this.factorPlazasAparcamientoM2 = factorPlazasAparcamientoM2;
		this.factorEquipamientosSector = factorEquipamientosSector;
		this.factorDotacionPublica = factorDotacionPublica;
		this.factorVerdeDotacionPublica = factorVerdeDotacionPublica;
		this.superficieSector = superficieSector;
		this.fase5 = fase5;
		this.setSuperficieServidumbre(superficieServidumbre);
	}
	
	public double getSuperficieTaludesSector() {
		return superficieTaludesSector;
	}
	public void setSuperficieTaludesSector(double superficieTaludesSector) {
		this.superficieTaludesSector = superficieTaludesSector;
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
	public double getFactorInteresSocial() {
		return factorInteresSocial;
	}
	public void setFactorInteresSocial(double factorInteresSocial) {
		this.factorInteresSocial = factorInteresSocial;
	}
	public double getFactorPlazasAparcamientoM2() {
		return factorPlazasAparcamientoM2;
	}
	public void setFactorPlazasAparcamientoM2(double factorPlazasAparcamientoM2) {
		this.factorPlazasAparcamientoM2 = factorPlazasAparcamientoM2;
	}
	public double getFactorEquipamientosSector() {
		return factorEquipamientosSector;
	}
	public void setFactorEquipamientosSector(double factorEquipamientosSector) {
		this.factorEquipamientosSector = factorEquipamientosSector;
	}
	public double getFactorDotacionPublica() {
		return factorDotacionPublica;
	}
	public void setFactorDotacionPublica(double factorDotacionPublica) {
		this.factorDotacionPublica = factorDotacionPublica;
	}
	public double getFactorVerdeDotacionPublica() {
		return factorVerdeDotacionPublica;
	}
	public void setFactorVerdeDotacionPublica(double factorVerdeDotacionPublica) {
		this.factorVerdeDotacionPublica = factorVerdeDotacionPublica;
	}
	public double getSuperficieSector() {
		return superficieSector;
	}
	public void setSuperficieSector(double superficieSector) {
		this.superficieSector = superficieSector;
	}
	public Phase5 getFase5() {
		return fase5;
	}
	public void setFase5(Phase5 fase5) {
		this.fase5 = fase5;
	}

	public double getSuperficieServidumbre() {
		return superficieServidumbre;
	}

	public void setSuperficieServidumbre(double superficieServidumbre) {
		this.superficieServidumbre = superficieServidumbre;
	}
	
}
