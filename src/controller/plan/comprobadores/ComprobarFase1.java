package controller.plan.comprobadores;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.errores.SQLError;
import controller.wizard.classes.phases.Phase;
import controller.wizard.classes.phases.Phase1;

public class ComprobarFase1 extends CerrarFase{

	private HashMap<String, String> ues = new HashMap<String, String>();
	
	public ComprobarFase1(HttpServletRequest request,
			HttpServletResponse response) {
		super(request, response);
		// TODO Auto-generated constructor stub
	}

	
	@Override
	void updateIncorrectPhase(Phase p) {
		
		Phase1 p1 = (Phase1) p;
		
		try {
			
			dao.getWizard().updatePhase1(p1);
			dao.getWizard().setPhaseIncorrect(p.getIdPlan(), 1);

		} catch (SQLException e) {
			new SQLError(request, response, e);
		}
		
		
	}

	@Override
	ArrayList<String> checkPhase() {
		ArrayList<String> cf = new ArrayList<String>();
		
		if(isNull(request.getParameter("denominacion_plan"))){
			cf.add("El plan tiene que tener una denominación de entre 1 y 100 caracteres.");
		}
		if(isNull(request.getParameter("denominacion_sector"))){
			cf.add("El sector tiene que tener una denominación de entre 1 y 100 caracteres.\n");
		}
		if(!isPositive(request.getParameter("numero_sector"))){
			cf.add("El número de sector tiene que ser positivo.\n");
		}
		if(!isPositive(request.getParameter("superficie"))){
			cf.add("La superficie tiene que ser positiva.\n");
		}

		boolean existeUE = false;
		boolean errorParAp = false;
		
		
		
		for (Entry<String, String[]> entry : request.getParameterMap().entrySet()) {
			if(entry.getKey().indexOf("UE") != -1){
				existeUE = true;
				if(!isPositive(request.getParameter(entry.getKey()))){
					errorParAp = true;
				}
				ues.put(entry.getKey(), request.getParameter(entry.getKey()));
			}
		}	
		
		if(!existeUE){
			cf.add("Por lo menos tiene que haber una unidad de ejecución.\n");
		}
		if(errorParAp){
			cf.add("El número de parcelas aportadas por unidad de ejecución tiene que ser por lo menos de 1.\n");
		}

		
		return cf;

	}

	@Override
	void update(Phase p) {
		try {
			dao.getWizard().updatePhase1((Phase1)p);
			dao.getWizard().setPhaseCorrect(p.getIdPlan(), 1);
		} catch (SQLException e) {
			new SQLError(request, response, e);
		}		
	}

	@Override
	Phase loadedPhase() {
		Phase1 p = new Phase1((Integer)request.getSession().getAttribute("idPlan"), request.getParameter("denominacion_plan"), request.getParameter("denominacion_sector"), request.getParameter("numero_sector").toString(), request.getParameter("municipio"), request.getParameter("idioma"), request.getParameter("superficie"), null);
		p.setUes(ues);
		return p;
	}

	@Override
	Phase correctedPhase() {
		Phase1 p = new Phase1((Integer)request.getSession().getAttribute("idPlan"), request.getParameter("denominacion_plan"), request.getParameter("denominacion_sector"), request.getParameter("numero_sector"), request.getParameter("municipio"), request.getParameter("idioma"), request.getParameter("superficie"), null);

		try{
			if(!isPositive(p.getSuperficie())){
				p.setSuperficie(""+Integer.parseInt(p.getSuperficie()));
			}
		}
		catch(NumberFormatException e){
			p.setSuperficie("0");
		}
		
		for (Entry<String, String> entry : ues.entrySet()) {
			if(!isPositive(request.getParameter(entry.getKey()))){
				ues.put(entry.getKey(), "0");
			}
		}	

		p.setUes(ues);
		
		return p;
	}




}
