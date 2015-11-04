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
import controller.wizard.classes.phases.Phase8;
import controller.wizard.classes.phases.PhaseTexto;

public class ComprobarFaseTexto extends CerrarFase{

	PhaseTexto p;
	private int fase;
	
	public ComprobarFaseTexto(HttpServletRequest request,
			HttpServletResponse response, int fase) {
		super(request, response);
		this.fase = fase;
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
			
			dao.getWizard().updatePhaseTexto((PhaseTexto) p, fase);
			dao.getWizard().setPhaseCorrect(p.getIdPlan(), fase);

		} catch (SQLException e) {
			new SQLError(request, response, e);
		}			
	}

	@Override
	Phase loadedPhase() {
		return new PhaseTexto((Integer)request.getSession().getAttribute("idPlan"), request.getParameter("content"));
	}

	@Override
	Phase correctedPhase() {
		return null;
	}

	@Override
	ArrayList<String> checkPhase(ArrayList<String> msg, Phase pa) {
		return msg;
	}




	public int getFase() {
		return fase;
	}




	public void setFase(int fase) {
		this.fase = fase;
	}
	
}
