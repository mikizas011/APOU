package controller.plan.comprobadores;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.errores.SQLError;
import controller.wizard.classes.Normativa;
import controller.wizard.classes.OrdenacionUrbanisticaEstructural;
import controller.wizard.classes.P567UnidadEjecucion;
import controller.wizard.classes.ParcelaResultante;
import controller.wizard.classes.UnidadEjecucion;
import controller.wizard.classes.phases.Phase;
import controller.wizard.classes.phases.Phase10;
import controller.wizard.classes.phases.Phase2;
import controller.wizard.classes.phases.Phase3;
import controller.wizard.classes.phases.Phase5;
import controller.wizard.classes.phases.Phase7;
import controller.wizard.classes.phases.Phase8;
import controller.wizard.classes.phases.PhaseTexto;

public class ComprobarFase10 extends CerrarFase{

	Phase10 p;
	
	public ComprobarFase10(HttpServletRequest request,
			HttpServletResponse response) {
		super(request, response);
		// TODO Auto-generated constructor stub
	}




	@Override
	ArrayList<String> checkData() {
		return new ArrayList<String>();
	}

	@Override
	void updateIncorrectPhase(Phase p) {}

	@Override
	void update(Phase p) {
		try {
			
			dao.getWizard().updatePhase10((Phase10) p);
			dao.getWizard().setPhaseCorrect(p.getIdPlan(), 10);

		} catch (SQLException e) {
			new SQLError(request, response, e);
			e.printStackTrace();
		}			
	}

	@Override
	Phase loadedPhase() {
		
		try {
			p = dao.getWizard().getPhase10((Integer)request.getSession().getAttribute("idPlan"));

			for (Entry<Integer, Normativa> n : p.getTodas().entrySet()) {
				n.getValue().setSeleccionada(false);
			}
			
			for (Entry<String, String[]> entry : request.getParameterMap().entrySet()) {
	
				if(entry.getKey().indexOf("NO") != -1){
					if(request.getParameter(entry.getKey()).equals("on")){
						
						int idNO = Integer.parseInt(entry.getKey().substring(entry.getKey().indexOf("NO") + 2, entry.getKey().indexOf(":")));
						p.getTodas().get(idNO).setSeleccionada(true);
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
		return null;
	}

	@Override
	ArrayList<String> checkPhase(ArrayList<String> msg, Phase pa) {
		return msg;
	}
	
}
