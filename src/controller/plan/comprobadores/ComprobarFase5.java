package controller.plan.comprobadores;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.errores.SQLError;
import controller.wizard.classes.OrdenacionUrbanisticaEstructural;
import controller.wizard.classes.P567UnidadEjecucion;
import controller.wizard.classes.ParcelaResultante;
import controller.wizard.classes.phases.Phase;
import controller.wizard.classes.phases.Phase2;
import controller.wizard.classes.phases.Phase3;
import controller.wizard.classes.phases.Phase5;

public class ComprobarFase5 extends CerrarFase{

	Phase5 p;
	
	public ComprobarFase5(HttpServletRequest request,
			HttpServletResponse response) {
		super(request, response);
		// TODO Auto-generated constructor stub
	}


	@Override
	ArrayList<String> checkData() {
		// TODO Auto-generated method stub
		ArrayList<String> cf = new ArrayList<String>();
		boolean errorSuperficie = false;
		
		
		for (Entry<String, String[]> entry : request.getParameterMap().entrySet()) {

			if(entry.getKey().indexOf("PR") != -1){
				
				if(!isPositive(request.getParameter(entry.getKey()))){
					errorSuperficie = true;
				}

			}
		}

		if(errorSuperficie){
			cf.add("Las superficies tienen que ser positivas.");
		}

		
		return cf;
	}

	@Override
	void updateIncorrectPhase(Phase p) {
		try {
			
			dao.getWizard().updatePhase5((Phase5) p);
			dao.getWizard().setPhaseIncorrect(p.getIdPlan(), 5);

		} catch (SQLException e) {
			new SQLError(request, response, e);
		}		
	}

	@Override
	void update(Phase p) {
		try {
			
			dao.getWizard().updatePhase5((Phase5) p);
			dao.getWizard().setPhaseCorrect(p.getIdPlan(), 5);

		} catch (SQLException e) {
			new SQLError(request, response, e);
		}			
	}

	@Override
	Phase loadedPhase() {
		try {
			p = dao.getWizard().getPhase5((Integer)request.getSession().getAttribute("idPlan"));
			
			for (Entry<String, String[]> entry : request.getParameterMap().entrySet()) {
				
				String itKey = entry.getKey();
				
				if(itKey.contains("PR")){

						int idPR = Integer.parseInt(itKey.substring(itKey.indexOf("PR") + 2, itKey.indexOf(":")));
						int idUE = Integer.parseInt(itKey.substring(itKey.indexOf("UE") + 2, itKey.length()));
						
						if(p.getMap().containsKey(idUE)){
							ParcelaResultante aux = p.getMap().get(idUE).getParcelas().get(idPR);
							if(itKey.contains("tipo")){aux.setIdTipoOrdenacionPormenorizada(Integer.parseInt(request.getParameter(itKey)));}
							else if(itKey.contains("superficie")){aux.setSuperficie(Double.parseDouble(request.getParameter(itKey)));}
							else if(itKey.contains("obr_a")){aux.setObrA(Double.parseDouble(request.getParameter(itKey)));}
							else if(itKey.contains("obr_n")){aux.setObrN(Double.parseDouble(request.getParameter(itKey)));}
							else if(itKey.contains("osr_a")){aux.setOsrA(Double.parseDouble(request.getParameter(itKey)));}
							else if(itKey.contains("osr_n")){aux.setOsrN(Double.parseDouble(request.getParameter(itKey)));}
							else if(itKey.contains("ebr_a")){aux.setEbrA(Double.parseDouble(request.getParameter(itKey)));}
							else if(itKey.contains("ebr_n")){aux.setEbrN(Double.parseDouble(request.getParameter(itKey)));}
							else if(itKey.contains("esrpb_a")){aux.setEsrpbA(Double.parseDouble(request.getParameter(itKey)));}
							else if(itKey.contains("esrpb_n")){aux.setEsrpbN(Double.parseDouble(request.getParameter(itKey)));}
							else if(itKey.contains("esrpp_a")){aux.setEsrppA(Double.parseDouble(request.getParameter(itKey)));}
							else if(itKey.contains("esrpp_n")){aux.setEsrppN(Double.parseDouble(request.getParameter(itKey)));}
							
							
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
			p = dao.getWizard().getPhase5((Integer)request.getSession().getAttribute("idPlan"));
			
			for (Entry<String, String[]> entry : request.getParameterMap().entrySet()) {
				
				String itKey = entry.getKey();
				
				if(itKey.contains("PR")){


					
						int idPR = Integer.parseInt(itKey.substring(itKey.indexOf("PR") + 2, itKey.indexOf(":")));
						int idUE = Integer.parseInt(itKey.substring(itKey.indexOf("UE") + 2, itKey.length()));
						
						if(p.getMap().containsKey(idUE)){
							ParcelaResultante aux = p.getMap().get(idUE).getParcelas().get(idPR);
							if(itKey.contains("tipo")){aux.setIdTipoOrdenacionPormenorizada(Integer.parseInt(request.getParameter(itKey)));}
							else if(itKey.contains("superficie")){aux.setSuperficie(checkPositive(request.getParameter(itKey)));}
							else if(itKey.contains("obr_a")){aux.setObrA(checkPositive(request.getParameter(itKey)));}
							else if(itKey.contains("obr_n")){aux.setObrN(checkPositive(request.getParameter(itKey)));}
							else if(itKey.contains("osr_a")){aux.setOsrA(checkPositive(request.getParameter(itKey)));}
							else if(itKey.contains("osr_n")){aux.setOsrN(checkPositive(request.getParameter(itKey)));}
							else if(itKey.contains("ebr_a")){aux.setEbrA(checkPositive(request.getParameter(itKey)));}
							else if(itKey.contains("ebr_n")){aux.setEbrN(checkPositive(request.getParameter(itKey)));}
							else if(itKey.contains("esrpb_a")){aux.setEsrpbA(checkPositive(request.getParameter(itKey)));}
							else if(itKey.contains("esrpb_n")){aux.setEsrpbN(checkPositive(request.getParameter(itKey)));}
							else if(itKey.contains("esrpp_a")){aux.setEsrppA(checkPositive(request.getParameter(itKey)));}
							else if(itKey.contains("esrpp_n")){aux.setEsrppN(checkPositive(request.getParameter(itKey)));}

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
		// TODO Auto-generated method stub
		return msg;
	}
	
}
