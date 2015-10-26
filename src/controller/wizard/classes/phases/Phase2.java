package controller.wizard.classes.phases;

import java.util.HashMap;
import java.util.Map.Entry;

import controller.wizard.classes.P2unidadEjecucion;

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

	@Override
	public String toString() {

		String a = "";
		
		for(Entry <Integer, P2unidadEjecucion> entry : map.entrySet()){
			a += "\n" + entry.getValue() + "\n";
		}
		
		
		return a;
	}
	
	
	
	

	
	


	
	
}
