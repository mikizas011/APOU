package controller.wizard.classes.phases;

import java.util.ArrayList;
import java.util.HashMap;

import controller.wizard.classes.OrdenacionUrbanisticaPormenorizada;
import controller.wizard.classes.UnidadEjecucion;

public class Phase4 extends Phase{

	private ArrayList<OrdenacionUrbanisticaPormenorizada> tipos;
	private HashMap<Integer, OrdenacionUrbanisticaPormenorizada> update;
	private HashMap<String, OrdenacionUrbanisticaPormenorizada> insert;
	private HashMap<String, UnidadEjecucion> ues;
	
	
	
	public Phase4(
			ArrayList<OrdenacionUrbanisticaPormenorizada> tipos,
			HashMap<Integer, OrdenacionUrbanisticaPormenorizada> update,
			HashMap<String, OrdenacionUrbanisticaPormenorizada> insert, int idPlan) {
		super(idPlan);
		this.tipos = tipos;
		this.update = new HashMap<Integer, OrdenacionUrbanisticaPormenorizada>();
		this.insert = new HashMap<String, OrdenacionUrbanisticaPormenorizada>();
	}
	

	public ArrayList<OrdenacionUrbanisticaPormenorizada> getTipos() {
		return tipos;
	}
	public void setTipos(ArrayList<OrdenacionUrbanisticaPormenorizada> tipos) {
		this.tipos = tipos;
	}
	public HashMap<Integer, OrdenacionUrbanisticaPormenorizada> getUpdate() {
		return update;
	}
	public void setUpdate(
			HashMap<Integer, OrdenacionUrbanisticaPormenorizada> update) {
		this.update = update;
	}
	public HashMap<String, OrdenacionUrbanisticaPormenorizada> getInsert() {
		return insert;
	}
	public void setInsert(HashMap<String, OrdenacionUrbanisticaPormenorizada> insert) {
		this.insert = insert;
	}


	public HashMap<String, UnidadEjecucion> getUes() {
		return ues;
	}


	public void setUes(HashMap<String, UnidadEjecucion> ues) {
		this.ues = ues;
	}
	
	
}
