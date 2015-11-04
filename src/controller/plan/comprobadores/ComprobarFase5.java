package controller.plan.comprobadores;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.errores.SQLError;
import controller.wizard.classes.OrdenacionUrbanisticaEstructural;
import controller.wizard.classes.P2unidadEjecucion;
import controller.wizard.classes.P567UnidadEjecucion;
import controller.wizard.classes.ParcelaAportada;
import controller.wizard.classes.ParcelaResultante;
import controller.wizard.classes.comprobaciones.Phase5Comp;
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
				
				if(!isPositive0Included(request.getParameter(entry.getKey()))){
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
		
		try {
			Phase5Comp p5 = dao.getWizard().getPhase5Comp(pa.getIdPlan());
			Phase5 fase = (Phase5) pa;
			
			double superficieSinServidumbres = 0;
			double superficieTaludes = 0;

			for(P2unidadEjecucion ue : p5.getFase2().getMap().values()){
				for(ParcelaAportada pap : ue.getParcelas()){
					superficieSinServidumbres += pap.getSuperficie();
				}
				superficieSinServidumbres -= ue.getSuperficieServidumbre();
				superficieTaludes += ue.getSuperficieTaludes();

			}
			
			double ocupacionSRAN = 0;
			double edificabilidadSRAN = 0;
			double edificabilidadBRAN = 0;
			
			for(P567UnidadEjecucion p567 : fase.getMap().values()){
				for(ParcelaResultante pr: p567.getParcelas().values()){
					ocupacionSRAN += pr.getOsrA() + pr.getOsrN();
					edificabilidadBRAN += pr.getEbrA() + pr.getEbrN();
					edificabilidadSRAN += pr.getEsrpbA() + pr.getEsrpbN() + pr.getEsrppA() + pr.getEsrppN();
				}
			}
			

			
			
			if(!((p5.getOcupacionEdificacion() * superficieSinServidumbres) <= ocupacionSRAN)){
				msg.add("La suma de las ocupaciones sobre rasante actuales y nuevas ("+(int)ocupacionSRAN+" m2) tienen que ser superior al " + p5.getOcupacionEdificacion()*100 + " % de la suma de las superficies de las parcelas aportadas menos las servidumbres sectoriales de cada unidad de ejecución ("+((int)(p5.getOcupacionEdificacion() * superficieSinServidumbres))+" m2) especificadas en la fase 2.");
			}
			
			if(!(edificabilidadBRAN <= p5.getEdifMaxBR())){
				msg.add("La suma de edificabilidades actuales y nuevas bajo rasante tiene que ser inferior o igual a la máxima establecida en la fase 3.");
			}
			
			if(!(edificabilidadSRAN <= p5.getEdifMaxSR())){
				msg.add("La suma de edificabilidades actuales y nuevas sobre rasante tiene que ser inferior o igual a la máxima establecida en la fase 3.");
			}

			if(!(((superficieSinServidumbres - superficieTaludes) * p5.getFactorOcupacionSuperficieNeta()) >= ocupacionSRAN)){
				msg.add("La ocupación sobre rasante ("+ocupacionSRAN+") debe ser menor o igual al " + p5.getFactorOcupacionSuperficieNeta()*100 + " % de la superficie del sector menos las servidumbres sectoriales y los taludes ("+(superficieSinServidumbres - superficieTaludes)+").");
			}
			
			if(!(((superficieSinServidumbres - superficieTaludes) * p5.getFactorEdificabilidadSuperficieNeta()) >= edificabilidadSRAN)){
				msg.add("La edificabilidad sobre rasante ("+edificabilidadSRAN+") debe ser menor o igual al " + p5.getFactorEdificabilidadSuperficieNeta()*100 + " % de la superficie del sector menos las servidumbres sectoriales y los taludes ("+(superficieSinServidumbres - superficieTaludes)+").");
			}
			
		} catch (SQLException e) {
			new SQLError(request, response, e);
		}
		
		return msg;
	}
	
}
