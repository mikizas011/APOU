package controller.wizard.classes.phases;

import java.util.HashMap;

import controller.wizard.classes.P5UnidadEjecucion;

public class Phase5 extends Phase{

	private HashMap<Integer, P5UnidadEjecucion> map;
	private HashMap<Integer, String> tipos;
	
	public Phase5(int idPlan, HashMap<Integer, P5UnidadEjecucion> map, HashMap<Integer, String> tipos) {
		super(idPlan);
		this.setMap(map);
		this.setTipos(tipos);
	}

	public HashMap<Integer, P5UnidadEjecucion> getMap() {
		return map;
	}

	public void setMap(HashMap<Integer, P5UnidadEjecucion> map) {
		this.map = map;
	}

	public HashMap<Integer, String> getTipos() {
		return tipos;
	}

	public void setTipos(HashMap<Integer, String> tipos) {
		this.tipos = tipos;
	}

	
}
