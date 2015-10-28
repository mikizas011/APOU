package controller.wizard.classes.phases;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import controller.wizard.classes.OrdenacionUrbanisticaPormenorizada;
import controller.wizard.classes.P567UnidadEjecucion;
import controller.wizard.classes.ParcelaResultante;

public class Phase6 extends Phase{

	private HashMap<String, HashMap<String, Double>> edificabilidadesPonderadas;
	
	public Phase6(int idPlan, HashMap<Integer, P567UnidadEjecucion> map, HashMap<Integer, OrdenacionUrbanisticaPormenorizada> tipos) {
		super(idPlan);

		edificabilidadesPonderadas = new HashMap<String, HashMap<String,Double>>(); 

		
		for(Entry <Integer, P567UnidadEjecucion> entry : map.entrySet()){
			
			for(Entry <Integer, ParcelaResultante> pr : entry.getValue().getParcelas().entrySet()){
				
				if(!edificabilidadesPonderadas.containsKey(entry.getValue().getDenominacion())){
					edificabilidadesPonderadas.put(entry.getValue().getDenominacion(), new HashMap<String, Double>());
				}
				
				OrdenacionUrbanisticaPormenorizada tipo = tipos.get(pr.getValue().getIdTipoOrdenacionPormenorizada());
				double edif = (tipo.getCoefBR() * pr.getValue().getEbrN()) + (tipo.getCoefSRPB() * pr.getValue().getEsrpbN()) + (tipo.getCoefSRPP() * pr.getValue().getEsrppN());
				
				if(!edificabilidadesPonderadas.get(entry.getValue().getDenominacion()).containsKey(tipo.getDenominacion())){
					edificabilidadesPonderadas.get(entry.getValue().getDenominacion()).put(tipo.getDenominacion(), 0.0);
				}
				
				double previo = edificabilidadesPonderadas.get(entry.getValue().getDenominacion()).get(tipo.getDenominacion());
				
				edificabilidadesPonderadas.get(entry.getValue().getDenominacion()).put(tipo.getDenominacion(), (previo + edif));
				
			}	
			
			for(Entry <Integer, OrdenacionUrbanisticaPormenorizada> top : tipos.entrySet()){
				if(!edificabilidadesPonderadas.get(entry.getValue().getDenominacion()).containsKey(top.getValue().getDenominacion())) {
					edificabilidadesPonderadas.get(entry.getValue().getDenominacion()).put(top.getValue().getDenominacion(), 0.0);
				}
			}
			
		}
		
		
		
		
	}

	public HashMap<String, HashMap<String, Double>> getEdificabilidadesPonderadas() {
		return edificabilidadesPonderadas;
	}

	public void setEdificabilidadesPonderadas(
			HashMap<String, HashMap<String, Double>> edificabilidadesPonderadas) {
		this.edificabilidadesPonderadas = edificabilidadesPonderadas;
	}

	


	
}
