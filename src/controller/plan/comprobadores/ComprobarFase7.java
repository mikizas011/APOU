package controller.plan.comprobadores;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.errores.SQLError;
import controller.wizard.classes.OrdenacionUrbanisticaEstructural;
import controller.wizard.classes.P567UnidadEjecucion;
import controller.wizard.classes.ParcelaResultante;
import controller.wizard.classes.UnidadEjecucion;
import controller.wizard.classes.phases.Phase;
import controller.wizard.classes.phases.Phase2;
import controller.wizard.classes.phases.Phase3;
import controller.wizard.classes.phases.Phase5;
import controller.wizard.classes.phases.Phase7;

public class ComprobarFase7 extends CerrarFase{

	Phase7 p;
	
	public ComprobarFase7(HttpServletRequest request,
			HttpServletResponse response) {
		super(request, response);
		// TODO Auto-generated constructor stub
	}


	@Override
	ArrayList<String> checkData() {
		// TODO Auto-generated method stub
		ArrayList<String> cf = new ArrayList<String>();
		boolean errorPorcentaje = false;
		
		
		for (Entry<String, String[]> entry : request.getParameterMap().entrySet()) {

			if(entry.getKey().indexOf("porcentaje") != -1){
				
				if(!isBetween01(request.getParameter(entry.getKey()))){
					errorPorcentaje = true;
				}
			}
		}

		if(errorPorcentaje){
			cf.add("Los porcentajes tienen que tener un valor entre 0 y 1.");
		}
		return cf;
	}

	@Override
	void updateIncorrectPhase(Phase p) {
		try {
			
			dao.getWizard().updatePhase7((Phase7) p);
			dao.getWizard().setPhaseIncorrect(p.getIdPlan(), 7);

		} catch (SQLException e) {
			new SQLError(request, response, e);
		}		
	}

	@Override
	void update(Phase p) {
		try {
			
			dao.getWizard().updatePhase7((Phase7) p);
			dao.getWizard().setPhaseCorrect(p.getIdPlan(), 7);

		} catch (SQLException e) {
			new SQLError(request, response, e);
		}			
	}

	@Override
	Phase loadedPhase() {
		try {
			p = dao.getWizard().getPhase7((Integer)request.getSession().getAttribute("idPlan"));
			
			for (Entry<String, String[]> entry : request.getParameterMap().entrySet()) {
				
				String itKey = entry.getKey();
				
				if(itKey.contains("PR")){

						int idPR = Integer.parseInt(itKey.substring(itKey.indexOf("PR") + 2, itKey.indexOf(":")));
						
						for(Entry <Integer, HashMap<Integer, ParcelaResultante>> ues : p.getParcelas().entrySet())
						
							if(ues.getValue().containsKey(idPR)){
								ues.getValue().get(idPR).setPorcentajeAyuntamiento(Double.parseDouble(request.getParameter(itKey)));
								break;
							}
				}
				
			}
		

			return p;
		
		} catch (SQLException e) {
			new SQLError(request, response, e);
			return null;
		}
	}

	@Override
	Phase correctedPhase() {
		try {
			p = dao.getWizard().getPhase7((Integer)request.getSession().getAttribute("idPlan"));
			
			for (Entry<String, String[]> entry : request.getParameterMap().entrySet()) {
				
				String itKey = entry.getKey();
				
				if(itKey.contains("PR")){

						int idPR = Integer.parseInt(itKey.substring(itKey.indexOf("PR") + 2, itKey.indexOf(":")));
						
						for(Entry <Integer, HashMap<Integer, ParcelaResultante>> ues : p.getParcelas().entrySet())
						
							if(ues.getValue().containsKey(idPR)){
								if(!isBetween01(request.getParameter(itKey))){
									ues.getValue().get(idPR).setPorcentajeAyuntamiento(0.0);
								}
								else{
									ues.getValue().get(idPR).setPorcentajeAyuntamiento(Double.parseDouble(request.getParameter(itKey)));
								}
								break;
							}
				}
				
			}
		

			return p;
		
		} catch (SQLException e) {
			new SQLError(request, response, e);
			return null;
		}
	}

	@Override
	ArrayList<String> checkPhase(ArrayList<String> msg, Phase pa) {
		Phase7 p = (Phase7) pa;
		
		
		for(Entry <Integer, HashMap<Integer, ParcelaResultante>> entry : p.getParcelas().entrySet()){
			
			UnidadEjecucion ue = p.getUes().get(entry.getKey());
			
			double minimo = ue.getEdificabilidadPonderada() * p.getParticipacionAyuntamiento();
			double ayuntaUE = 0;
			
			for(Entry <Integer, ParcelaResultante> pr : entry.getValue().entrySet()){
				ayuntaUE += pr.getValue().getEdificabilidadPonderada() * pr.getValue().getPorcentajeAyuntamiento();
			}
			
			if(ayuntaUE < minimo){
				msg.add("La participación de la comunidad de las plusvalías en la unidad de ejecucion (" + ue.getDenominacion() + ") debería ser como mínimo de " + minimo + " metros cuadrados. Considerando el reparto de parcelas que se muestra en pantalla, se le está asignando una edificabilidad de " + ayuntaUE + " metros cuadrados." );
			}
			
		}
		
		
		
		return msg;
	}
	
	public boolean isBetween01(String v){
		try{
			if(isNull(v)){
				return false;
			}
			else {
				 Double value = (Double) Double.parseDouble(v);
				 if((value > 1)||(value < 0)){
					 return false;
				 }
			}
			return true;
		}
		catch(NumberFormatException e){
			return false;
		}
	}
	
}
