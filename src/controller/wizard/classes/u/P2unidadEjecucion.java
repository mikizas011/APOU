package controller.wizard.classes.u;

import java.util.ArrayList;

public class P2unidadEjecucion {

	private int superficieServidumbre, idUnidadEjecucion, numeroParecelasAportadas;
	private ArrayList<ParcelaAportada> parcelas;
	private String denominacion;
	
	

	
	public P2unidadEjecucion(int superficieServidumbre, int idUnidadEjecucion,
			int numeroParecelasAportadas,
			String denominacion) {
		super();
		this.superficieServidumbre = superficieServidumbre;
		this.idUnidadEjecucion = idUnidadEjecucion;
		this.numeroParecelasAportadas = numeroParecelasAportadas;
		this.parcelas = new ArrayList<ParcelaAportada>();
		this.denominacion = denominacion;
	}
	public int getSuperficieServidumbre() {
		return superficieServidumbre;
	}
	public void setSuperficieServidumbre(int superficieServidumbre) {
		this.superficieServidumbre = superficieServidumbre;
	}
	public ArrayList<ParcelaAportada> getParcelas() {
		return parcelas;
	}
	public void setParcelas(ArrayList<ParcelaAportada> parcelas) {
		this.parcelas = parcelas;
	}


	public int getIdUnidadEjecucion() {
		return idUnidadEjecucion;
	}


	public void setIdUnidadEjecucion(int idUnidadEjecucion) {
		this.idUnidadEjecucion = idUnidadEjecucion;
	}


	public int getNumeroParecelasAportadas() {
		return numeroParecelasAportadas;
	}


	public void setNumeroParecelasAportadas(int numeroParecelasAportadas) {
		this.numeroParecelasAportadas = numeroParecelasAportadas;
	}


	public String getDenominacion() {
		return denominacion;
	}


	public void setDenominacion(String denominacion) {
		this.denominacion = denominacion;
	}
	
	
	
}
