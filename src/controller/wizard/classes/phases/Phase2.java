package controller.wizard.classes.phases;

import java.util.HashMap;

import controller.wizard.classes.u.P2unidadEjecucion;

public class Phase2 extends Phase{

	private HashMap<Integer, P2unidadEjecucion> map;
	
	public Phase2(int idPlan, HashMap<Integer, P2unidadEjecucion> map) {
		super(idPlan);
		this.map = map;
	}

	public HashMap<Integer, P2unidadEjecucion> getMap() {
		return map;
	}

	public void setMap(HashMap<Integer, P2unidadEjecucion> map) {
		this.map = map;
	}
	
	

	
	


	
	
}
