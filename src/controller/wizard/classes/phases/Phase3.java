package controller.wizard.classes.phases;

import java.util.ArrayList;
import java.util.HashMap;

import controller.wizard.classes.OrdenacionUrbanisticaEstructural;

public class Phase3 extends Phase{

	private int edifMaxSobreRasante, edifMaxBajoRasante;
	private ArrayList<OrdenacionUrbanisticaEstructural> tipos;
	private HashMap<Integer, OrdenacionUrbanisticaEstructural> update;
	private HashMap<String, OrdenacionUrbanisticaEstructural> insert;
	
	public Phase3(int idPlan, ArrayList<OrdenacionUrbanisticaEstructural> tipos, int edifMSR, int edifMBR) {
		super(idPlan);
		this.tipos = tipos;
		this.edifMaxBajoRasante = edifMBR;
		this.edifMaxSobreRasante = edifMSR;
		this.update = new HashMap<Integer, OrdenacionUrbanisticaEstructural>();
		this.insert = new HashMap<String, OrdenacionUrbanisticaEstructural>();
	}

	public int getEdifMaxSobreRasante() {
		return edifMaxSobreRasante;
	}

	public void setEdifMaxSobreRasante(int edifMaxSobreRasante) {
		this.edifMaxSobreRasante = edifMaxSobreRasante;
	}

	public int getEdifMaxBajoRasante() {
		return edifMaxBajoRasante;
	}

	public void setEdifMaxBajoRasante(int edifMaxBajoRasante) {
		this.edifMaxBajoRasante = edifMaxBajoRasante;
	}

	public ArrayList<OrdenacionUrbanisticaEstructural> getTipos() {
		return tipos;
	}

	public void setTipos(ArrayList<OrdenacionUrbanisticaEstructural> tipos) {
		this.tipos = tipos;
	}

	public HashMap<Integer, OrdenacionUrbanisticaEstructural> getUpdate() {
		return update;
	}

	public void setUpdate(HashMap<Integer, OrdenacionUrbanisticaEstructural> update) {
		this.update = update;
	}

	public HashMap<String, OrdenacionUrbanisticaEstructural> getInsert() {
		return insert;
	}

	public void setInsert(HashMap<String, OrdenacionUrbanisticaEstructural> insert) {
		this.insert = insert;
	}

	
	
}
