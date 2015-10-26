package controller.wizard.classes.phases;

import java.util.HashMap;

import controller.wizard.classes.P5UnidadEjecucion;

public class Phase5 extends Phase{

	private HashMap<Integer, P5UnidadEjecucion> map;
	private HashMap<String, Integer> tipos;
	
	public Phase5(int idPlan, HashMap<Integer, P5UnidadEjecucion> map, HashMap<String, Integer> tipos) {
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

	public HashMap<String, Integer> getTipos() {
		return tipos;
	}

	public void setTipos(HashMap<String, Integer> tipos) {
		this.tipos = tipos;
	}

	
}
