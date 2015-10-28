package controller.wizard.classes.phases;

import java.util.HashMap;
import controller.wizard.classes.UnidadEjecucion;

public class Phase8 extends Phase{

	private HashMap<Integer, UnidadEjecucion> ues;

	public Phase8(int idPlan, HashMap<Integer, UnidadEjecucion> ues) {
		super(idPlan);
		this.ues = ues;
	}

	public HashMap<Integer, UnidadEjecucion> getUes() {
		return ues;
	}

	public void setUes(HashMap<Integer, UnidadEjecucion> ues) {
		this.ues = ues;
	}

	
		
}

	


	

