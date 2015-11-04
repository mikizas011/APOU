package controller.plan.comprobadores;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;











import controller.errores.SQLError;
import controller.wizard.classes.P2unidadEjecucion;
import controller.wizard.classes.ParcelaAportada;
import controller.wizard.classes.phases.Phase;
import controller.wizard.classes.phases.Phase1;
import controller.wizard.classes.phases.Phase2;

public class ComprobarFase2 extends CerrarFase{

	Phase2 p;
	
	public ComprobarFase2(HttpServletRequest request,
			HttpServletResponse response) {
		super(request, response);
		// TODO Auto-generated constructor stub
	}
	@Override
	ArrayList<String> checkData() {
		// TODO Auto-generated method stub

		ArrayList<String> cf = new ArrayList<String>();
		
		boolean errorSup = false;
		boolean errorNull = false;
		
		for (Entry<String, String[]> entry : request.getParameterMap().entrySet()) {

			if(entry.getKey().contains("superficie")){
				if(!isPositive(request.getParameter(entry.getKey()))){
					errorSup = true;
				}
			}
			else if(entry.getKey().contains("propietario")){
				if(isNull(request.getParameter(entry.getKey()))){
					errorNull = true;
				}
			}
			else if(entry.getKey().contains("servidumbre")){
				if(!isPositive(request.getParameter(entry.getKey()))){
					errorSup = true;
				}
			}
			else if(entry.getKey().contains("taludes")){
				if(!isPositive(request.getParameter(entry.getKey()))){
					errorSup = true;
				}
			}
				
			
		}
		
		if(errorSup){
			cf.add("Las superficies tienen que ser positivas.");
		}
		if(errorNull){
			cf.add("Los propietarios tienen que tener un nombre.");
		}
		
		return cf;
		
		
	
		
	}


	@Override
	void updateIncorrectPhase(Phase p) {

		
		try {
			
			dao.getWizard().updatePhase2((Phase2) p);
			dao.getWizard().setPhaseIncorrect(p.getIdPlan(), 2);

		} catch (SQLException e) {
			new SQLError(request, response, e);
		}
		
	}

	@Override
	void update(Phase p) {
		
		try {
			
			dao.getWizard().updatePhase2((Phase2) p);
			dao.getWizard().setPhaseCorrect(p.getIdPlan(), 2);

		} catch (SQLException e) {
			new SQLError(request, response, e);
		}		
	}

	@Override
	Phase loadedPhase() {
		// TODO Auto-generated method stub
		try {
			
			p = dao.getWizard().getPhase2((Integer)request.getSession().getAttribute("idPlan"));

			for(Entry <Integer, P2unidadEjecucion> entry : p.getMap().entrySet()){
				
				String a = entry.getValue().getDenominacion() + ":idUe";
				String b = request.getParameter(a);
				
				//Hacemos el parseint para que coincida con la estructura int P2ue del hashmap
				P2unidadEjecucion p2 = p.getMap().get(Integer.parseInt(b));
				
				
				for(int i = 0; i < entry.getValue().getParcelas().size(); i++){
					
					String value = entry.getValue().getDenominacion() + "PA" + (i+1);
					
					
					p2.getParcelas().get(i).setDominio(
											request.getParameter(
													entry.getValue().
													getParcelas().get(i).getDenominacion()+":dominio"));
					
					p2.getParcelas().get(i).setSuperficie(Double.parseDouble(request.getParameter(entry.getValue().getParcelas().get(i).getDenominacion()+":superficie")));
					
					p2.getParcelas().get(i).setPropietario(request.getParameter(entry.getValue().getParcelas().get(i).getDenominacion()+":propietario"));
					
				}
				
				p2.setSuperficieServidumbre(Integer.parseInt(request.getParameter(entry.getValue().getDenominacion() + ":servidumbre")));
				p2.setSuperficieTaludes(Integer.parseInt(request.getParameter(entry.getValue().getDenominacion() + ":taludes")));
				
				
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
			
			p = dao.getWizard().getPhase2((Integer)request.getSession().getAttribute("idPlan"));

			for(Entry <Integer, P2unidadEjecucion> entry : p.getMap().entrySet()){
				
				String a = entry.getValue().getDenominacion() + ":idUe";
				String b = request.getParameter(a);
				
				//Hacemos el parseint para que coincida con la estructura int P2ue del hashmap
				P2unidadEjecucion p2 = p.getMap().get(Integer.parseInt(b));
				
				
				for(int i = 0; i < entry.getValue().getParcelas().size(); i++){
					
					String value = entry.getValue().getDenominacion() + "PA" + (i+1);
					
					
					p2.getParcelas().get(i).setDominio(
											request.getParameter(
													entry.getValue().
													getParcelas().get(i).getDenominacion()+":dominio"));
					
					if( (!isPositive(request.getParameter(value + ":superficie"))) ){
						p2.getParcelas().get(i).setSuperficie(0);
					}
					else{
						p2.getParcelas().get(i).setSuperficie(Double.parseDouble(request.getParameter(entry.getValue().getParcelas().get(i).getDenominacion()+":superficie")));
					}
					
					if( (isNull(request.getParameter(value + ":propietario"))) ){
						p2.getParcelas().get(i).setPropietario("");
					}
					else{
						p2.getParcelas().get(i).setPropietario(request.getParameter(entry.getValue().getParcelas().get(i).getDenominacion()+":propietario"));
					}
					
				}
				
				if(!isPositive(request.getParameter(entry.getValue().getDenominacion() + ":servidumbre"))){
					p2.setSuperficieServidumbre(0);
				}
				else{
					p2.setSuperficieServidumbre(Integer.parseInt(request.getParameter(entry.getValue().getDenominacion() + ":servidumbre")));
				}
				
				if(!isPositive(request.getParameter(entry.getValue().getDenominacion() + ":taludes"))){
					p2.setSuperficieTaludes(0);
				}
				else{
					p2.setSuperficieServidumbre(Integer.parseInt(request.getParameter(entry.getValue().getDenominacion() + ":taludes")));
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
			Phase2 fase = (Phase2) pa;
			String msgServidumbres = "La suma de superficies de una unidad de ejecución tiene que ser mayor que la superficie de suelo con servidumbres sectoriales de esa misma unidad de ejecución.";
			double superficieSector = 0;
			
			for(P2unidadEjecucion p2 : fase.getMap().values()){
				double superficieUnidadEjecucion = 0;
				for(ParcelaAportada pap : p2.getParcelas()){
					superficieUnidadEjecucion += pap.getSuperficie();
				}
				if(p2.getSuperficieServidumbre() > superficieUnidadEjecucion && !msg.contains(msgServidumbres)){
					msg.add(msgServidumbres);
				}
				superficieSector += superficieUnidadEjecucion;
			}
			
			double superficieSectorF1 = dao.getWizard().getSuperficieSector(pa.getIdPlan());
			
			if(superficieSector != superficieSectorF1){
				msg.add("La suma de superficies de cada parcela aportada tiene que ser igual a la superficie del sector definida en la fase 1 (" + (int)superficieSectorF1 + " metros cuadrados).");
			}
			
		} catch (SQLException e) {
			new SQLError(request, response, e);
		}
		
		
		return msg;
	}
	
}
