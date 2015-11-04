package controller.wizard.classes;

public class Normativa {

	private String nombre;
	private boolean seleccionada;
	private int idNormativa;
	
	
	
	public Normativa(String nombre, int idNormativa) {
		super();
		this.nombre = nombre;
		this.idNormativa = idNormativa;
		this.seleccionada = false;
		
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public boolean isSeleccionada() {
		return seleccionada;
	}
	public void setSeleccionada(boolean estaSeleccionada) {
		this.seleccionada = estaSeleccionada;
	}
	public int getIdNormativa() {
		return idNormativa;
	}
	public void setIdNormativa(int idNormativa) {
		this.idNormativa = idNormativa;
	}
	
}
