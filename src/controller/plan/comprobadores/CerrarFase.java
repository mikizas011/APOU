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
	
	abstract ArrayList<String> checkPhase();
	abstract void update(Phase p);
	abstract void updateIncorrectPhase(Phase p);
	abstract Phase loadedPhase();
	abstract Phase correctedPhase();
	
	public void execute() throws ServletException, IOException, SQLException{
		
		Phase p = null;
		ArrayList<String> msg = checkPhase();
		if(msg.size() == 0){
			p = loadedPhase();
			update(p);
		}
		else{
			p = correctedPhase();
			updateIncorrectPhase(p);
		}
		
		attributeLoad(p, msg);
		
		dao.close();
		
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

	public void attributeLoad(Phase p, ArrayList<String> msg) throws SQLException{
		if(msg.size() == 0){
			request.setAttribute("id", request.getSession().getAttribute("idPlan"));
		}
		else{
			request.setAttribute("id", p.getIdPlan());
			request.setAttribute("msg", msg);
			request.setAttribute("phase", p);
		}
	}

}
