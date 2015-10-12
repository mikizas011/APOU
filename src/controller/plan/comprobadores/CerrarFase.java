package controller.plan.comprobadores;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.wizard.classes.phases.Phase;
import model.Dao;

public abstract class CerrarFase {

	public HttpServletRequest request;
	public HttpServletResponse response;
	public Dao dao;
	
	
	public CerrarFase(HttpServletRequest request, HttpServletResponse response){
		this.request = request;
		this.response = response;
		dao = new Dao();
	}
	
	abstract void update();
	abstract ArrayList<String> checkPhase();
	abstract Phase retrieveIncorrectPhaseObject();
	abstract Phase getUpdateableIncorrectPhase();
	abstract void updateIncorrectPhase(Phase p);
	
	public void execute() throws ServletException, IOException, SQLException{
		Phase p = null;
		ArrayList<String> msg = checkPhase();
		boolean isCorrect = false;
		if(msg.size() == 0){
			isCorrect = true;
		}
		if(isCorrect){
			update();
		}
		else{
			p = retrieveIncorrectPhaseObject();
			updateIncorrectPhase(getUpdateableIncorrectPhase());
		}
		attributeLoad(p, msg, isCorrect);
		
		request.getRequestDispatcher("/user_area/load_plan").forward(request, response);
	}
	
	
	
	public boolean isNull(String s){
		if(s.equals("") || s.toUpperCase().equals("NULL")){
			return true;
		}
		return false;
	}
	
	public boolean isPositive(String s){
		try{
			if(isNull(s)){
				return false;
			}
			else if((Double) Double.parseDouble(s) <= 0){
				return false;
			}
			return true;
		}
		catch(NumberFormatException e){
			return false;
		}
	}

	public void attributeLoad(Phase p, ArrayList<String> msg, boolean isCorrect) throws SQLException{
		if(isCorrect){
			request.setAttribute("id", request.getSession().getAttribute("idPlan"));
		}
		else{
			request.setAttribute("id", p.getIdPlan());
			request.setAttribute("msg", msg);
			request.setAttribute("phase", p);
		}
	}

}
