package controller.plan.comprobadores;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.errores.SQLError;
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
	
	abstract ArrayList<String> checkData();
	abstract ArrayList<String> checkPhase(ArrayList<String> msg, Phase pa);
	abstract void update(Phase p);
	abstract void updateIncorrectPhase(Phase p);
	abstract Phase loadedPhase();
	abstract Phase correctedPhase();
	
	
	public void execute() throws ServletException, IOException, SQLException{
		
		Phase p = null;
		ArrayList<String> msg = checkData();
		
		if(msg.size() == 0){
			p = loadedPhase();
		}
		else{
			p = correctedPhase();
		}

		msg = checkPhase(msg, p);

		if(msg.size() == 0){
			update(p);
		}
		else{
			updateIncorrectPhase(p);
		}
		
		updateLastModDate(p.getIdPlan());
		
		attributeLoad(p, msg);
		
		dao.close();
		dao = null;
		
		request.getRequestDispatcher("/user_area/load_plan").forward(request, response);
		
	}
	
	public String checkNull(String s){
		if(s.equals("") || s.toUpperCase().equals("NULL")){
			return "";
		}
		return s;
	}
	
	public boolean isNull(String s){
		if(s.equals("") || s.toUpperCase().equals("NULL")){
			return true;
		}
		return false;
	}
	
	public double checkPositive(String s){
		try{
			if(isNull(s)){
				return 0.0;
			}
			else if((Double) Double.parseDouble(s) < 0){
				return 0.0;
			}
			return (Double) Double.parseDouble(s);
		}
		catch(NumberFormatException e){
			return 0.0;
		}
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
	
	public boolean isPositive0Included(String s){
		try{
			if(isNull(s)){
				return false;
			}
			else if((Double) Double.parseDouble(s) < 0){
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
	
	public void updateLastModDate(int idPlan){
		try {
			dao.getProgram().updateLastModDate(idPlan);
		} catch (SQLException e) {
			new SQLError(request, response, e);
		}
	}

}
