package controller.wizard.classes.u;

public class ParcelaAportada {

	private String denominacion, propietario, dominio;
	private int idUnidadEjecucion, idParcelaAportada;
	private double superficie;
	
	
	
	public ParcelaAportada(String denominacion, String propietario,
			String dominio, int idUnidadEjecucion, double superficie) {
		super();
		this.denominacion = denominacion;
		this.propietario = propietario;
		this.setDominio(dominio);
		this.idUnidadEjecucion = idUnidadEjecucion;
		this.superficie = superficie;
	}
	
	
	
	public ParcelaAportada(String denominacion, String propietario,
			String dominio, int idUnidadEjecucion, int idParcelaAportada,
			double superficie) {
		super();
		this.denominacion = denominacion;
		this.propietario = propietario;
		this.setDominio(dominio);
		this.idUnidadEjecucion = idUnidadEjecucion;
		this.idParcelaAportada = idParcelaAportada;
		this.superficie = superficie;
	}



	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return denominacion + " [prop: " + propietario + ", sup: " + superficie + "]";
	}


	public String getDenominacion() {
		return denominacion;
	}
	public void setDenominacion(String denominacion) {
		this.denominacion = denominacion;
	}
	public String getPropietario() {
		return propietario;
	}
	public void setPropietario(String propietario) {
		this.propietario = propietario;
	}

	public int getIdUnidadEjecucion() {
		return idUnidadEjecucion;
	}
	public void setIdUnidadEjecucion(int idUnidadEjecucion) {
		this.idUnidadEjecucion = idUnidadEjecucion;
	}
	public double getSuperficie() {
		return superficie;
	}
	public void setSuperficie(double superficie) {
		this.superficie = superficie;
	}
	public int getIdParcelaAportada() {
		return idParcelaAportada;
	}
	public void setIdParcelaAportada(int idParcelaAportada) {
		this.idParcelaAportada = idParcelaAportada;
	}



	public String getDominio() {
		return dominio;
	}



	public void setDominio(String dominio) {
		this.dominio = dominio;
	}
	
}