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

public class ComprobarFase6 extends CerrarFase{

	Phase5 p;
	
	public ComprobarFase6(HttpServletRequest request,
			HttpServletResponse response) {
		super(request, response);
		// TODO Auto-generated constructor stub
	}


	@Override
	ArrayList<String> checkData() {
		return new ArrayList<String>();
	}

	@Override
	void updateIncorrectPhase(Phase p) {
		
	}

	@Override
	void update(Phase p) {
		try {
			
			dao.getWizard().setPhaseCorrect(p.getIdPlan(), 6);

		} catch (SQLException e) {
			new SQLError(request, response, e);
		}			
	}

	@Override
	Phase loadedPhase() {
		return new Phase((Integer)request.getSession().getAttribute("idPlan"));
	}

	@Override
	Phase correctedPhase() {
		return new Phase((Integer)request.getSession().getAttribute("idPlan"));
	}

	@Override
	ArrayList<String> checkPhase(ArrayList<String> msg, Phase pa) {
		// TODO Auto-generated method stub
		return msg;
	}
	
}
