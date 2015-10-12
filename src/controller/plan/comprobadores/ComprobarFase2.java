package controller.plan.comprobadores;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;







import controller.errores.SQLError;
import controller.wizard.classes.phases.Phase;
import controller.wizard.classes.phases.Phase1;
import controller.wizard.classes.phases.Phase2;
import controller.wizard.classes.u.P2unidadEjecucion;

public class ComprobarFase2 extends CerrarFase{

	Phase2 p;
	
	public ComprobarFase2(HttpServletRequest request,
			HttpServletResponse response) {
		super(request, response);
		// TODO Auto-generated constructor stub
	}

	@Override
	void update() {

		try {
			
			dao.getWizard().updatePhase2(p);
			dao.getWizard().setPhaseCorrect(p.getIdPlan(), 2);
			dao.close();

		} catch (SQLException e) {
			new SQLError(request, response, e);
		}

		
	}

	@Override
	ArrayList<String> checkPhase() {
		// TODO Auto-generated method stub
		
		try {

			ArrayList<String> cf = new ArrayList<String>();
			
			p = dao.getWizard().getPhase2((Integer)request.getSession().getAttribute("idPlan"));

			for(Entry <Integer, P2unidadEjecucion> entry : p.getMap().entrySet()){
				
				for(int i = 0; i < entry.getValue().getParcelas().size(); i++){
					
					String value = request.getParameter(entry.getValue().getDenominacion() + "PA" + (i+1));
					
					p.getMap().get(
							request.getParameter(
									entry.getValue().getDenominacion() + ":idUe"))
									.getParcelas().get(i).setDominio(
											request.getParameter(
													entry.getValue().
													getParcelas().get(i).getDenominacion()+":dominio"));
					
					if( (!isPositive(request.getParameter(value + ":superficie"))) ){
						if(!cf.contains("Las superficies tienen que ser positivas.\n")){
							cf.add("Las superficies tienen que ser positivas.\n");
						}
						p.getMap().get(request.getParameter(entry.getValue().getDenominacion() + ":idUe")).getParcelas().get(i).setSuperficie(0);
					}
					else{
						p.getMap().get(request.getParameter(entry.getValue().getDenominacion() + ":idUe")).getParcelas().get(i).setSuperficie(Double.parseDouble(request.getParameter(entry.getValue().getParcelas().get(i).getDenominacion()+":superficie")));
					}
					if( (isNull(request.getParameter(value + ":propietario"))) ){
						if(!cf.contains("Los propietarios tienen que tener una denominación entre 1 y 100 caracteres.\n")){
							cf.add("Los propietarios tienen que tener una denominación entre 1 y 100 caracteres.\n");
						}
						p.getMap().get(request.getParameter(entry.getValue().getDenominacion() + ":idUe")).getParcelas().get(i).setPropietario("");
					}
					else{
						p.getMap().get(request.getParameter(entry.getValue().getDenominacion() + ":idUe")).getParcelas().get(i).setDominio(request.getParameter(entry.getValue().getParcelas().get(i).getDenominacion()+":propietario"));
					}
					
				}
				
				if(!isPositive(request.getParameter(entry.getValue().getDenominacion() + ":servidumbre"))){
					if(!cf.contains("Las superficies tienen que ser positivas.\n")){
						cf.add("Las superficies tienen que ser positivas.\n");
					}
					p.getMap().get(request.getParameter(entry.getValue().getDenominacion() + ":idUe")).setSuperficieServidumbre(0);
				}
				else{
					p.getMap().get(request.getParameter(entry.getValue().getDenominacion() + ":idUe")).setSuperficieServidumbre(Integer.parseInt(request.getParameter(entry.getValue().getDenominacion() + "servidumbre")));
				}
				
			}
				
			
			return cf;
		
		} catch (SQLException e) {
			new SQLError(request, response, e);
			return null;
		}
	
		
	}

	@Override
	Phase retrieveIncorrectPhaseObject() {
		return p;
	}

	@Override
	Phase getUpdateableIncorrectPhase() {
		// TODO Auto-generated method stub
		return p;
	}

	@Override
	void updateIncorrectPhase(Phase p) {

		
		try {
			
			dao.getWizard().updatePhase2((Phase2) p);
			dao.getWizard().setPhaseIncorrect(p.getIdPlan(), 2);
			dao.close();

		} catch (SQLException e) {
			new SQLError(request, response, e);
		}
		
	}

}
