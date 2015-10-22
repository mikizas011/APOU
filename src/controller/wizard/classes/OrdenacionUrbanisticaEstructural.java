package controller.wizard.classes;

//	Se utiliza en la fase 3

public class OrdenacionUrbanisticaEstructural {

	private String nombre;
	private int superficie, idOrdenacionUrbanisticaEstructural;
	
	public OrdenacionUrbanisticaEstructural(int idOUE, String nombre, int superficie) {
		super();
		this.idOrdenacionUrbanisticaEstructural = idOUE;
		this.nombre = nombre;
		this.superficie = superficie;
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getSuperficie() {
		return superficie;
	}
	public void setSuperficie(int superficie) {
		this.superficie = superficie;
	}

	public int getIdOrdenacionUrbanisticaEstructural() {
		return idOrdenacionUrbanisticaEstructural;
	}

	public void setIdOrdenacionUrbanisticaEstructural(
			int idOrdenacionUrbanisticaEstructural) {
		this.idOrdenacionUrbanisticaEstructural = idOrdenacionUrbanisticaEstructural;
	}
	
}
