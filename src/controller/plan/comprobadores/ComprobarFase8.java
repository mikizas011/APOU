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
import controller.wizard.classes.comprobaciones.Phase8Comp;
import controller.wizard.classes.phases.Phase;
import controller.wizard.classes.phases.Phase2;
import controller.wizard.classes.phases.Phase3;
import controller.wizard.classes.phases.Phase5;
import controller.wizard.classes.phases.Phase7;
import controller.wizard.classes.phases.Phase8;

public class ComprobarFase8 extends CerrarFase{

	Phase8 p;
	
	public ComprobarFase8(HttpServletRequest request,
			HttpServletResponse response) {
		super(request, response);
		// TODO Auto-generated constructor stub
	}


	@Override
	ArrayList<String> checkData() {
		// TODO Auto-generated method stub
		ArrayList<String> cf = new ArrayList<String>();
		boolean errorSuperficie = false;
		boolean errorAparcamientos = false;
		
		
		for (Entry<String, String[]> entry : request.getParameterMap().entrySet()) {

			if(entry.getKey().indexOf("EL") != -1){
				if(!isPositive(request.getParameter(entry.getKey()))){
					errorSuperficie = true;
				}
			}
			else if(entry.getKey().indexOf("EQ") != -1){
				if(!isPositive(request.getParameter(entry.getKey()))){
					errorSuperficie = true;
				}
			}
			else if(entry.getKey().indexOf("RV") != -1){
				if(!isPositive(request.getParameter(entry.getKey()))){
					errorSuperficie = true;
				}
			}
			else if(entry.getKey().indexOf("TO") != -1){
				if(!isPositive(request.getParameter(entry.getKey()))){
					errorAparcamientos = true;
				}
			}
		}

		if(errorSuperficie){
			cf.add("Las superficies tienen que ser positivas.");
		}
		if(errorAparcamientos){
			cf.add("El n�mero de aparcamientos tiene que ser positivo.");
		}
		return cf;
	}

	@Override
	void updateIncorrectPhase(Phase p) {
		try {
			
			dao.getWizard().updatePhase8((Phase8) p);
			dao.getWizard().setPhaseIncorrect(p.getIdPlan(), 8);

		} catch (SQLException e) {
			new SQLError(request, response, e);
		}		
	}

	@Override
	void update(Phase p) {
		try {
			
			dao.getWizard().updatePhase8((Phase8) p);
			dao.getWizard().setPhaseCorrect(p.getIdPlan(), 8);

		} catch (SQLException e) {
			new SQLError(request, response, e);
		}			
	}

	@Override
	Phase loadedPhase() {
		try {
			p = dao.getWizard().getPhase8((Integer)request.getSession().getAttribute("idPlan"));
			
			for (Entry<String, String[]> entry : request.getParameterMap().entrySet()) {
				
				String itKey = entry.getKey();
				
				if(itKey.contains("EL")){
					int idPR = Integer.parseInt(itKey.substring(itKey.indexOf("EL") + 2, itKey.indexOf(":")));
					p.getUes().get(idPR).setSuperficieEspaciosLibres(Double.parseDouble(request.getParameter(itKey)));
				}
				else if(itKey.contains("EQ")){
					int idPR = Integer.parseInt(itKey.substring(itKey.indexOf("EQ") + 2, itKey.indexOf(":")));
					p.getUes().get(idPR).setSuperficieEquipamientos(Double.parseDouble(request.getParameter(itKey)));
				}
				else if(itKey.contains("RV")){
					int idPR = Integer.parseInt(itKey.substring(itKey.indexOf("RV") + 2, itKey.indexOf(":")));
					p.getUes().get(idPR).setSuperficieRedViaria(Double.parseDouble(request.getParameter(itKey)));
				}
				else if(itKey.contains("TO")){
					int idPR = Integer.parseInt(itKey.substring(itKey.indexOf("TO") + 2, itKey.indexOf(":")));
					p.getUes().get(idPR).setNumeroPlazasAparcamiento(Integer.parseInt(request.getParameter(itKey)));
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
			p = dao.getWizard().getPhase8((Integer)request.getSession().getAttribute("idPlan"));
			
			for (Entry<String, String[]> entry : request.getParameterMap().entrySet()) {
				
				String itKey = entry.getKey();
				
				if(itKey.contains("EL")){
					int idPR = Integer.parseInt(itKey.substring(itKey.indexOf("EL") + 2, itKey.indexOf(":")));
					p.getUes().get(idPR).setSuperficieEspaciosLibres(checkPositive(request.getParameter(itKey)));
				}
				else if(itKey.contains("EQ")){
					int idPR = Integer.parseInt(itKey.substring(itKey.indexOf("EQ") + 2, itKey.indexOf(":")));
					p.getUes().get(idPR).setSuperficieEquipamientos(checkPositive(request.getParameter(itKey)));
				}
				else if(itKey.contains("RV")){
					int idPR = Integer.parseInt(itKey.substring(itKey.indexOf("RV") + 2, itKey.indexOf(":")));
					p.getUes().get(idPR).setSuperficieRedViaria(checkPositive(request.getParameter(itKey)));
				}
				else if(itKey.contains("TO")){
					int idPR = Integer.parseInt(itKey.substring(itKey.indexOf("TO") + 2, itKey.indexOf(":")));
					p.getUes().get(idPR).setNumeroPlazasAparcamiento((int)checkPositive(request.getParameter(itKey)));
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
		
		try {
			Phase8 fase = (Phase8) pa;
			Phase8Comp p8 = dao.getWizard().getPhase8Comp(pa.getIdPlan());
			
			double ocupacionSRNA = 0;
			double edificabilidadSRBRNA = 0;
			
			for(P567UnidadEjecucion p567 : p8.getFase5().getMap().values()){
				for(ParcelaResultante pr : p567.getParcelas().values()){
					ocupacionSRNA += pr.getOsrT();
					edificabilidadSRBRNA += pr.getEsrppT() + pr.getEsrpbT();
				}
			}
			
			double equipamientos = 0;
			int numPlazas = 0;
			double sistemasLocales = 0;
			double zonasVerdes = 0;
			
			for(UnidadEjecucion ue : fase.getUes().values()){
				equipamientos += ue.getSuperficieEquipamientos();
				numPlazas += ue.getNumeroPlazasAparcamiento();
				sistemasLocales += ue.getSuperficieEquipamientos() + ue.getSuperficieEspaciosLibres() + ue.getSuperficieRedViaria();
				zonasVerdes += ue.getSuperficieEspaciosLibres();
			}
			
			if(!((edificabilidadSRBRNA * p8.getFactorInteresSocial()) <= equipamientos )){
				msg.add("La suma de equipamientos del sector tiene que ser mayor o igual que el "+p8.getFactorInteresSocial()*100+" % de la suma de las edificabilidades sobre rasante del sector establecidas en la fase 5.");
			}
			if(!((edificabilidadSRBRNA * p8.getFactorPlazasAparcamientoM2()) <= numPlazas )){
				msg.add("Tiene que haber un m�nimo de " + (p8.getFactorPlazasAparcamientoM2()*100) + " plazas de aparcamiento por cada 100 metros cuadrados construidos ("+edificabilidadSRBRNA+").");
			}
			if(!((p8.getSuperficieSector() * p8.getFactorEquipamientosSector()) >= equipamientos)){
				msg.add("La superficie destinada a equipamientos tiene que ser mayor o igual que el " + p8.getFactorEquipamientosSector() + " % de la superficie del sector.");
			}
			if(!(sistemasLocales >= p8.getSuperficieSector() * p8.getFactorDotacionPublica())){
				msg.add("La superficie de los sistemas locales tiene que ser superior o igual que el " + p8.getFactorDotacionPublica()*100 + " % de la superficie del sector.");
			}
			if(!(zonasVerdes >= p8.getFactorVerdeDotacionPublica() * sistemasLocales)){
				msg.add("Las zonas verdes ("+zonasVerdes+") tienen que suponer un " + p8.getFactorVerdeDotacionPublica()*100 + " % de los sitemas locales ("+sistemasLocales+")."); 
			}

		
		} catch (SQLException e) {
			new SQLError(request, response, e);
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
