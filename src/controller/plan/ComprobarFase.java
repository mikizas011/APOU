package controller.plan;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import model.Dao;
import controller.errores.SQLError;
import controller.wizard.classes.Municipio;
import controller.wizard.classes.Phase1;

/**
 * Servlet implementation class ComprobarFase
 */
@WebServlet("/user_area/check_phase")
public class ComprobarFase extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ComprobarFase() {
        super();
        // TODO Auto-generated constructor stub
    }


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

//	
//		for (Entry<String, String[]> entry : request.getParameterMap().entrySet()) {
//			System.out.println("Key : " + entry.getKey() + " Value : " + request.getParameter(entry.getKey()));
//		}		
//		
//		System.out.println("Para el plan de id: " + request.getSession().getAttribute("planId"));
		
		//Chequear si se valida.
		
		String aceptada = "";
		
		try{
			switch (Integer.parseInt(request.getParameter("phase"))) {
				case 1:	checkPhase1(request, response); break;	
				
			}
		}
		catch(SQLException e){
			new SQLError(request, response, e);
		}
		
		
	
	}
	
	public ArrayList<String> checkPhase1(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException{

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
		
		HashMap<String, String> ues = new HashMap<String, String>();
		
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
		
		if(cf.size() == 0){
			updatePhase1(request);
		}
		else{
			
			Phase1 p = new Phase1(-1, request.getParameter("denominacion_plan"), request.getParameter("denominacion_sector"), request.getParameter("numero_sector"), request.getParameter("municipio"), request.getParameter("idioma"), request.getParameter("superficie"), null);
			p.setUes(ues);
			
			Dao dao = new Dao();
			
			ArrayList<Municipio> municipios = dao.getWizard().getMunicipios();
			
			dao.close();
			
			request.setAttribute("msg", cf);
			request.setAttribute("municipios", municipios);
			request.setAttribute("phase1", p);
			request.setAttribute("id", request.getSession().getAttribute("id"));
			
			request.getRequestDispatcher("/user_area/phases/phase1.jsp").forward(request, response);
		}
		
		return cf;
	}
	
	public void updatePhase1(HttpServletRequest request){
		
	}
	
	public boolean isNull(String s){
		if(s.equals("") || s.toUpperCase().equals("NULL")){
			return true;
		}
		return false;
	}
	
	public boolean isPositive(String s){
		if(isNull(s)){
			return false;
		}
		else if((Double) Double.parseDouble(s) <= 0){
			return false;
		}
		return true;
	}


}
