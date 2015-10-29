package controller.wizard.classes;

public class ParcelaResultante {

	private int idParcelaResultante, idTipoOrdenacionPormenorizada, idUnidadEjecucion;
	private String denominacion, tipoOrdenacionPormenorizada;
	private double superficie, obrA, obrN, osrA, osrN, ebrA, ebrN, esrpbA, esrpbN, esrppA, esrppN, porcentajeAyuntamiento, edificabilidadPonderada;
	
	
	
	public ParcelaResultante(int idParcelaResultante, String denominacion){
		this.idParcelaResultante = idParcelaResultante;
		this.denominacion = denominacion;
	}
	
	public ParcelaResultante(int idParcelaResultante, String denominacion, double edificabilidadPonderada, double porcentajeAyuntamiento){
		this.idParcelaResultante = idParcelaResultante;
		this.denominacion = denominacion;
		this.edificabilidadPonderada = edificabilidadPonderada;
		this.porcentajeAyuntamiento = porcentajeAyuntamiento;
	}
	
	public ParcelaResultante(int idTipoOrdenacionPormenorizada,
			int idUnidadEjecucion, int numeroUnidadEjecucion, int numeroParcela, double superficie,
			double obrA, double obrN, double osrA, double osrN, double ebrA,
			double ebrN, double esrpbA, double esrpbN, double esrppA,
			double esrppN) {
		super();
		this.idTipoOrdenacionPormenorizada = idTipoOrdenacionPormenorizada;
		this.idUnidadEjecucion = idUnidadEjecucion;
		this.denominacion = "P.UE" + numeroUnidadEjecucion + "." + numeroParcela;
		this.superficie = superficie;
		this.obrA = obrA;
		this.obrN = obrN;
		this.osrA = osrA;
		this.osrN = osrN;
		this.ebrA = ebrA;
		this.ebrN = ebrN;
		this.esrpbA = esrpbA;
		this.esrpbN = esrpbN;
		this.esrppA = esrppA;
		this.esrppN = esrppN;
	}


	
	public ParcelaResultante(int idParcelaResultante, String denominacion,
			int idTipoOrdenacionPormenorizada, int idUnidadEjecucion,
			double ebrN, double esrpbN, double esrppN, double porcentajeAyuntamiento) {
		super();
		this.idParcelaResultante = idParcelaResultante;
		this.idTipoOrdenacionPormenorizada = idTipoOrdenacionPormenorizada;
		this.idUnidadEjecucion = idUnidadEjecucion;
		this.ebrN = ebrN;
		this.esrpbN = esrpbN;
		this.esrppN = esrppN;
		this.denominacion =denominacion;
		this.porcentajeAyuntamiento = porcentajeAyuntamiento;
	}



	public ParcelaResultante(int idParcelaResultante,
			int idTipoOrdenacionPormenorizada, int idUnidadEjecucion,
			String denominacion, double superficie, double obrA, double obrN,
			double osrA, double osrN, double ebrA, double ebrN, double esrpbA,
			double esrpbN, double esrppA, double esrppN, String tipoOrdenacionPormenorizada) {
		super();
		this.idParcelaResultante = idParcelaResultante;
		this.idTipoOrdenacionPormenorizada = idTipoOrdenacionPormenorizada;
		this.idUnidadEjecucion = idUnidadEjecucion;
		this.denominacion = denominacion;
		this.superficie = superficie;
		this.obrA = obrA;
		this.obrN = obrN;
		this.osrA = osrA;
		this.osrN = osrN;
		this.ebrA = ebrA;
		this.ebrN = ebrN;
		this.esrpbA = esrpbA;
		this.esrpbN = esrpbN;
		this.esrppA = esrppA;
		this.esrppN = esrppN;
		this.tipoOrdenacionPormenorizada = tipoOrdenacionPormenorizada;
	}
	
	public ParcelaResultante(int idParcelaResultante,
			int idTipoOrdenacionPormenorizada, int idUnidadEjecucion,
			String denominacion, double superficie, double obrA, double obrN,
			double osrA, double osrN, double ebrA, double ebrN, double esrpbA,
			double esrpbN, double esrppA, double esrppN, double porcentajeAyuntamiento) {
		super();
		this.idParcelaResultante = idParcelaResultante;
		this.idTipoOrdenacionPormenorizada = idTipoOrdenacionPormenorizada;
		this.idUnidadEjecucion = idUnidadEjecucion;
		this.denominacion = denominacion;
		this.superficie = superficie;
		this.obrA = obrA;
		this.obrN = obrN;
		this.osrA = osrA;
		this.osrN = osrN;
		this.ebrA = ebrA;
		this.ebrN = ebrN;
		this.esrpbA = esrpbA;
		this.esrpbN = esrpbN;
		this.esrppA = esrppA;
		this.esrppN = esrppN;
		this.porcentajeAyuntamiento = porcentajeAyuntamiento;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return denominacion + " [TOP: " + idTipoOrdenacionPormenorizada + ", sup: " + superficie + "]";
	}
	

	public int getIdParcelaResultante() {
		return idParcelaResultante;
	}
	public void setIdParcelaResultante(int idParcelaResultante) {
		this.idParcelaResultante = idParcelaResultante;
	}
	public int getIdTipoOrdenacionPormenorizada() {
		return idTipoOrdenacionPormenorizada;
	}
	public void setIdTipoOrdenacionPormenorizada(int idTipoOrdenacionPormenorizada) {
		this.idTipoOrdenacionPormenorizada = idTipoOrdenacionPormenorizada;
	}
	public String getDenominacion() {
		return denominacion;
	}
	public void setDenominacion(String denominacion) {
		this.denominacion = denominacion;
	}
	public double getSuperficie() {
		return superficie;
	}
	public void setSuperficie(double superficie) {
		this.superficie = superficie;
	}
	public double getObrA() {
		return obrA;
	}
	public void setObrA(double obrA) {
		this.obrA = obrA;
	}
	public double getObrN() {
		return obrN;
	}
	public void setObrN(double obrN) {
		this.obrN = obrN;
	}
	public double getOsrA() {
		return osrA;
	}
	public void setOsrA(double osrA) {
		this.osrA = osrA;
	}
	public double getOsrN() {
		return osrN;
	}
	public void setOsrN(double osrN) {
		this.osrN = osrN;
	}
	public double getEbrA() {
		return ebrA;
	}
	public void setEbrA(double ebrA) {
		this.ebrA = ebrA;
	}
	public double getEbrN() {
		return ebrN;
	}
	public void setEbrN(double ebrN) {
		this.ebrN = ebrN;
	}
	public double getEsrpbA() {
		return esrpbA;
	}
	public void setEsrpbA(double esrpbA) {
		this.esrpbA = esrpbA;
	}
	public double getEsrpbN() {
		return esrpbN;
	}
	public void setEsrpbN(double esrpbN) {
		this.esrpbN = esrpbN;
	}
	public double getEsrppA() {
		return esrppA;
	}
	public void setEsrppA(double esrppA) {
		this.esrppA = esrppA;
	}
	public double getEsrppN() {
		return esrppN;
	}
	public void setEsrppN(double esrppN) {
		this.esrppN = esrppN;
	}
	public int getIdUnidadEjecucion() {
		return idUnidadEjecucion;
	}
	public void setIdUnidadEjecucion(int idUnidadEjecucion) {
		this.idUnidadEjecucion = idUnidadEjecucion;
	}
	public double getPorcentajeAyuntamiento() {
		return porcentajeAyuntamiento;
	}
	public void setPorcentajeAyuntamiento(double porcentajeAyuntamiento) {
		this.porcentajeAyuntamiento = porcentajeAyuntamiento;
	}


	public String getTipoOrdenacionPormenorizada() {
		return tipoOrdenacionPormenorizada;
	}


	public void setTipoOrdenacionPormenorizada(String tipoOrdenacionPormenorizada) {
		this.tipoOrdenacionPormenorizada = tipoOrdenacionPormenorizada;
	}

	public double getEdificabilidadPonderada() {
		return edificabilidadPonderada;
	}

	public void setEdificabilidadPonderada(double edificabilidadPonderada) {
		this.edificabilidadPonderada = edificabilidadPonderada;
	}
	
	
}
