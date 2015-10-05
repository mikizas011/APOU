package controller.wizard.classes;

public class TipoOrdenacionEstructural {

	private int idTipoOrdenacionEstructural, idPlan;
	private String denominacion;
	private double superficie;
	
	public TipoOrdenacionEstructural(String denominacion, double superficie){
		super();
		this.denominacion = denominacion;
		this.superficie = superficie;
	}
	
	
	
	public TipoOrdenacionEstructural(int idTipoOrdenacionEstructural,
			int idPlan, String denominacion, double superficie) {
		super();
		this.idTipoOrdenacionEstructural = idTipoOrdenacionEstructural;
		this.idPlan = idPlan;
		this.denominacion = denominacion;
		this.superficie = superficie;
	}



	public int getIdTipoOrdenacionEstructural() {
		return idTipoOrdenacionEstructural;
	}
	public void setIdTipoOrdenacionEstructural(int idTipoOrdenacionEstructural) {
		this.idTipoOrdenacionEstructural = idTipoOrdenacionEstructural;
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
	public double getSuperficie() {
		return superficie;
	}
	public void setSuperficie(double superficie) {
		this.superficie = superficie;
	}
	
	
	
	
}
