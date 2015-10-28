package controller.wizard.classes.phases;

import java.util.HashMap;
import java.util.Map.Entry;

import controller.wizard.classes.OrdenacionUrbanisticaPormenorizada;
import controller.wizard.classes.P567UnidadEjecucion;
import controller.wizard.classes.ParcelaResultante;
import controller.wizard.classes.UnidadEjecucion;

public class Phase7 extends Phase{

	private HashMap<Integer, HashMap<Integer, ParcelaResultante>> parcelas;
	private HashMap<Integer, UnidadEjecucion> ues;
	private double participacionAyuntamiento;
	
		public Phase7(int idPlan, HashMap<Integer, P567UnidadEjecucion> map, HashMap<Integer, OrdenacionUrbanisticaPormenorizada> tipos, double participacionAyuntamiento) {
			super(idPlan);
			this.setParticipacionAyuntamiento(participacionAyuntamiento);
			parcelas = new HashMap<Integer, HashMap<Integer,ParcelaResultante>>();
			ues = new HashMap<Integer, UnidadEjecucion>();
			for(Entry <Integer, P567UnidadEjecucion> entry : map.entrySet()){
				
				for(Entry <Integer, ParcelaResultante> pr : entry.getValue().getParcelas().entrySet()){
					
					if(!parcelas.containsKey(entry.getValue().getIdUnidadEjecucion())){
						parcelas.put(entry.getValue().getIdUnidadEjecucion(), new HashMap<Integer, ParcelaResultante>());
						ues.put(entry.getValue().getIdUnidadEjecucion(), new UnidadEjecucion(entry.getValue().getDenominacion(), entry.getValue().getIdUnidadEjecucion(), 0));
					}
					
					OrdenacionUrbanisticaPormenorizada tipo = tipos.get(pr.getValue().getIdTipoOrdenacionPormenorizada());
					double edif = (tipo.getCoefBR() * pr.getValue().getEbrN()) + (tipo.getCoefSRPB() * pr.getValue().getEsrpbN()) + (tipo.getCoefSRPP() * pr.getValue().getEsrppN());
					
					parcelas.get(entry.getValue().getIdUnidadEjecucion()).put(pr.getValue().getIdParcelaResultante(), new ParcelaResultante(pr.getValue().getIdParcelaResultante(), pr.getValue().getDenominacion(), edif, pr.getValue().getPorcentajeAyuntamiento()));
					
					UnidadEjecucion aux = ues.get(entry.getValue().getIdUnidadEjecucion());
					aux.setEdificabilidadPonderada(aux.getEdificabilidadPonderada() + edif);
					
				}	
				
			}
			
			
		
		
		
		
		}

		public HashMap<Integer, HashMap<Integer, ParcelaResultante>> getParcelas() {
			return parcelas;
		}

		public void setParcelas(
				HashMap<Integer, HashMap<Integer, ParcelaResultante>> parcelas) {
			this.parcelas = parcelas;
		}

		public HashMap<Integer, UnidadEjecucion> getUes() {
			return ues;
		}

		public void setUes(HashMap<Integer, UnidadEjecucion> ues) {
			this.ues = ues;
		}

		public double getParticipacionAyuntamiento() {
			return participacionAyuntamiento;
		}

		public void setParticipacionAyuntamiento(double participacionAyuntamiento) {
			this.participacionAyuntamiento = participacionAyuntamiento;
		}



	
	
		
		
		
	}

	


	

