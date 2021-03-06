package controller.wizard.classes;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Dao;

public class UnidadEjecucion {

	
	private int idUnidadEjecucion, numeroParcelasAportadas, numeroParcelasResultantes, idPlan, numeroPlazasAparcamiento;
	private String denominacion;
	private double superficieServidumbre, superficieEspaciosLibres, superficieEquipamientos, superficieRedViaria, edificabilidadPonderada;
	private ArrayList<ParcelaAportada> parcelasAportadas;
	private ArrayList<ParcelaResultante> parcelasResultantes;
	private ArrayList<OrdenacionUrbanisticaPormenorizada> tiposOrdenacionPormenorizada;
	
	public UnidadEjecucion(double superficieEspaciosLibres,
			double superficieEquipamiento, double superficieRedViaria,
			int plazasAparcamiento, String denominacion, int idUnidadEjecucion){
		
		this.superficieEspaciosLibres = superficieEspaciosLibres;
		this.superficieEquipamientos = superficieEquipamiento;
		this.superficieRedViaria = superficieRedViaria;
		this.numeroPlazasAparcamiento = plazasAparcamiento;
		this.denominacion = denominacion;
		this.setIdUnidadEjecucion(idUnidadEjecucion);
		
	}
	
	public UnidadEjecucion(String denominacion, int idUnidadEjecucion, double edificabilidadPonderada){
		super();
		this.denominacion = denominacion;
		this.idUnidadEjecucion = idUnidadEjecucion;
		this.edificabilidadPonderada = edificabilidadPonderada;
	}
	
	public UnidadEjecucion(int numeroParcelasResultantes, String denominacion, int idUnidadEjecucion){
		super();
		this.numeroParcelasResultantes = numeroParcelasResultantes;
		this.denominacion = denominacion;
		this.idUnidadEjecucion = idUnidadEjecucion;
		parcelasAportadas = new ArrayList<ParcelaAportada>();
	}
	
	public UnidadEjecucion(int idUnidadEjecucion, int numeroParcelasAportadas, String denominacion) {
		super();
		this.numeroParcelasAportadas = numeroParcelasAportadas;
		this.denominacion = denominacion;
		this.idUnidadEjecucion = idUnidadEjecucion;
		parcelasAportadas = new ArrayList<ParcelaAportada>();
		parcelasResultantes = new ArrayList<ParcelaResultante>();
	}
	
	
	
	public UnidadEjecucion(int idUnidadEjecucion, int numeroParcelasAportadas,
			int numeroParcelasResultantes, int idPlan, String denominacion,
			double superficieServidumbre, double superficieEspaciosLibres, double superficieEquipamientos,
			double superficieRedViaria, int numeroPlazasAparcamiento) throws SQLException {
		super();
		this.idUnidadEjecucion = idUnidadEjecucion;
		this.numeroParcelasAportadas = numeroParcelasAportadas;
		this.numeroParcelasResultantes = numeroParcelasResultantes;
		this.idPlan = idPlan;
		this.denominacion = denominacion;
		this.superficieServidumbre = superficieServidumbre;
		this.parcelasAportadas = new ArrayList<ParcelaAportada>();
		this.parcelasResultantes = new ArrayList<ParcelaResultante>();
		this.superficieEquipamientos = superficieEquipamientos;
		this.superficieEspaciosLibres = superficieEspaciosLibres;
		this.superficieRedViaria = superficieRedViaria;
		this.numeroPlazasAparcamiento = numeroPlazasAparcamiento;
	}



	@Override
	public String toString() {
		// TODO Auto-generated method stub
		String ret = denominacion + " [ID: " + idUnidadEjecucion + ", # parcelas aportadas: " + numeroParcelasAportadas + " = "+parcelasAportadas.size()+", SUP Serv.: " + superficieServidumbre + "]";
		
		if(parcelasAportadas.size() >= 1){
			ret += "\n";
			
			for(int i = 0; i < parcelasAportadas.size(); i++){
				ret += "    - " + parcelasAportadas.get(i).toString() + "\n";
			}
		}
		if(parcelasResultantes.size() >= 1){
			ret += "\n";
			
			for(int i = 0; i < parcelasResultantes.size(); i++){
				ret += "    - " + parcelasResultantes.get(i).toString() + "\n";
			}
		}
		
		return ret;
	}
	
	
	public int getIdUnidadEjecucion() {
		return idUnidadEjecucion;
	}
	public void setIdUnidadEjecucion(int idUnidadEjecucion) {
		this.idUnidadEjecucion = idUnidadEjecucion;
	}
	public int getNumeroParcelasAportadas() {
		return numeroParcelasAportadas;
	}
	public void setNumeroParcelasAportadas(int numeroParcelasAportadas) {
		this.numeroParcelasAportadas = numeroParcelasAportadas;
	}
	public int getNumeroParcelasResultantes() {
		return numeroParcelasResultantes;
	}
	public void setNumeroParcelasResultantes(int numeroParcelasResultantes) {
		this.numeroParcelasResultantes = numeroParcelasResultantes;
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
	public double getSuperficieServidumbre() {
		return superficieServidumbre;
	}
	public void setSuperficieServidumbre(double superficieServidumbre) {
		this.superficieServidumbre = superficieServidumbre;
	}
	public ArrayList<ParcelaAportada> getParcelasAportadas() {
		return parcelasAportadas;
	}
	public void setParcelasAportadas(ArrayList<ParcelaAportada> parcelasAportadas) {
		this.parcelasAportadas = parcelasAportadas;
	}
	public ArrayList<ParcelaResultante> getParcelasResultantes() {
		return parcelasResultantes;
	}

	public void setParcelasResultantes(ArrayList<ParcelaResultante> parcelasResultantes) {
		this.parcelasResultantes = parcelasResultantes;
	}
	
	

	public int getNumeroPlazasAparcamiento() {
		return numeroPlazasAparcamiento;
	}
	public void setNumeroPlazasAparcamiento(int numeroPlazasAparcamiento) {
		this.numeroPlazasAparcamiento = numeroPlazasAparcamiento;
	}
	public double getSuperficieEspaciosLibres() {
		return superficieEspaciosLibres;
	}
	public void setSuperficieEspaciosLibres(double superficieEspaciosLibres) {
		this.superficieEspaciosLibres = superficieEspaciosLibres;
	}
	public double getSuperficieEquipamientos() {
		return superficieEquipamientos;
	}
	public void setSuperficieEquipamientos(double superficieEquipamientos) {
		this.superficieEquipamientos = superficieEquipamientos;
	}
	public double getSuperficieRedViaria() {
		return superficieRedViaria;
	}
	public void setSuperficieRedViaria(double superficieRedViaria) {
		this.superficieRedViaria = superficieRedViaria;
	}
	public ArrayList<OrdenacionUrbanisticaPormenorizada> getTiposOrdenacionPormenorizada() {
		return tiposOrdenacionPormenorizada;
	}
	public void setTiposOrdenacionPormenorizada(
			ArrayList<OrdenacionUrbanisticaPormenorizada> tiposOrdenacionPormenorizada) {
		this.tiposOrdenacionPormenorizada = tiposOrdenacionPormenorizada;
	}

	public double getEdificabilidadPonderada() {
		return edificabilidadPonderada;
	}

	public void setEdificabilidadPonderada(double edificabilidadPonderada) {
		this.edificabilidadPonderada = edificabilidadPonderada;
	}

	@Override
	public boolean equals(Object arg0) {
		
		UnidadEjecucion aux = (UnidadEjecucion) arg0;
		
		if(aux.getIdUnidadEjecucion() == getIdUnidadEjecucion()){
			return true;
		}
		else{
			return false;
		}
		
	}
	
	
	
}
