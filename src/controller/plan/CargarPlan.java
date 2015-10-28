package controller.plan;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.KeyStore.Entry;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Configuracion;
import controller.errores.SQLError;
import controller.wizard.classes.Municipio;
import controller.wizard.classes.Plan;
import controller.wizard.classes.phases.Phase1;
import controller.wizard.classes.phases.Phase2;
import controller.wizard.classes.phases.Phase3;
import controller.wizard.classes.phases.Phase4;
import controller.wizard.classes.phases.Phase5;
import controller.wizard.classes.phases.Phase6;
import controller.wizard.classes.phases.Phase7;
import controller.wizard.classes.phases.Phase8;
import model.Dao;

/**
 * Servlet implementation class CargarPlan
 */
@WebServlet("/user_area/load_plan")
public class CargarPlan extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CargarPlan() {
        super();
        // TODO Auto-generated constructor stub
    }

	
    
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		
			//Obtenemos la id del plan que estamos editando
			int idPlan = -1;
			
			if(request.getParameter("id") == null){
				idPlan = Integer.parseInt((String) "" + request.getAttribute("id"));
			}
			else{
				idPlan = Integer.parseInt((String) "" + request.getParameter("id"));
			}
			
			
			
			if(idPlan != -1){
			
				request.getSession().setAttribute("idPlan", idPlan);
				
				//Obtenemos la fase en la que se encuentra el plan
				Dao dao = new Dao();
				
				int fase;
				int difFase = 0;
				
				
				try {
					int faseActual = dao.getWizard().getFase(idPlan);
					if(request.getParameter("fase") == null){
						fase = faseActual;
					}
					else{
						difFase = dao.getWizard().getFase(idPlan);
						fase = Integer.parseInt(request.getParameter("fase"));
						difFase = difFase - fase;
					}
					
					//Obtenemos las fases correctas
					boolean faseCorrecta[] = dao.getWizard().getFasesCorrectas(idPlan);
					
					String estadoFase[] = new String[8];
					
					for(int i = 0; i < fase + difFase; i++){
						
						
						if((fase + difFase) <= 8){
							if(faseCorrecta[i]){
								estadoFase[i] = "checked";
							}
							else{
								estadoFase[i] = "opened";
							}	
						}
						else{
							if(i < 8){
								estadoFase[i] = "checked";
							}
						}
						
									
					}
					
					for(int i = fase + difFase; i < 8; i++){
						estadoFase[i] = "closed";
					}
					
					if(fase < 9){
						estadoFase[fase-1] = "actual";
					}
					
					request.setAttribute("estadoFase", estadoFase);
					
					switch (fase) {
						case 1:	phase1(request, response, dao, idPlan, faseActual); break;
						case 2:	phase2(request, response, dao, idPlan, faseActual); break;
						case 3: phase3(request, response, dao, idPlan, faseActual); break;
						case 4: phase4(request, response, dao, idPlan, faseActual); break;
						case 5: phase5(request, response, dao, idPlan, faseActual); break;
						case 6: phase6(request, response, dao, idPlan, faseActual); break;
						case 7: phase7(request, response, dao, idPlan, faseActual); break;
						case 8: phase8(request, response, dao, idPlan, faseActual); break;
					}
					
				} catch (SQLException e) {
					new SQLError(request, response, e);
				}
				
				
				
			}
	
	}
	
	public void phase1(HttpServletRequest request, HttpServletResponse response, Dao dao, int idPlan, int faseActual) throws SQLException, ServletException, IOException{
		
		//Cargamos los municipios disponibles, y la información referente a la fase 1
		ArrayList<Municipio> municipios = dao.getWizard().getMunicipios();
		
		Phase1 p = null;
		
		if((ArrayList<String>) request.getAttribute("msg") != null){
			p = (Phase1) request.getAttribute("phase");
		}
		else{
			p = dao.getWizard().getPhase1(idPlan);
		}
		
		p.setFase(faseActual);
		
		if(p.getDenominacionPlan() == null){
			p.setDenominacionPlan("P: " + new Date());
		}
		
		dao.close();
		dao = null;
		
		
		request.setAttribute("municipios", municipios);
		request.setAttribute("phase1", p);
		
		request.getRequestDispatcher("/user_area/phases/phase1.jsp").forward(request, response);
		
	}
	
	public void phase2(HttpServletRequest request, HttpServletResponse response, Dao dao, int idPlan, int faseActual) throws SQLException, ServletException, IOException{
		
		
		
		Phase2 p = null;
		
		if((ArrayList<String>) request.getAttribute("msg") != null){
			p = (Phase2) request.getAttribute("phase");
		}
		else{
			p = dao.getWizard().getPhase2(idPlan);
		}
		
		p.setFase(faseActual);

		
		dao.close();
		dao = null;
		
		request.setAttribute("phase2", p);
		
		request.getRequestDispatcher("/user_area/phases/phase2.jsp").forward(request, response);
		
	}

	public void phase3(HttpServletRequest request, HttpServletResponse response, Dao dao, int idPlan, int faseActual) throws SQLException, ServletException, IOException{
		
		
		
		Phase3 p = null;
		
		if((ArrayList<String>) request.getAttribute("msg") != null){
			p = (Phase3) request.getAttribute("phase");
		}
		else{
			p = dao.getWizard().getPhase3(idPlan);
		}
		
		p.setFase(faseActual);

		
		dao.close();
		dao = null;
		
		request.setAttribute("phase3", p);
		
		request.getRequestDispatcher("/user_area/phases/phase3.jsp").forward(request, response);
		
	}
	
	public void phase4(HttpServletRequest request, HttpServletResponse response, Dao dao, int idPlan, int faseActual) throws SQLException, ServletException, IOException{
		
		
		
		Phase4 p = null;
		
		if((ArrayList<String>) request.getAttribute("msg") != null){
			p = (Phase4) request.getAttribute("phase");
		}
		else{
			p = dao.getWizard().getPhase4(idPlan);
		}
		
		p.setFase(faseActual);

		
		dao.close();
		dao = null;
		
		request.setAttribute("phase4", p);
		
		request.getRequestDispatcher("/user_area/phases/phase4.jsp").forward(request, response);
		
	}
	
	public void phase5(HttpServletRequest request, HttpServletResponse response, Dao dao, int idPlan, int faseActual) throws SQLException, ServletException, IOException{
		
		
		
		Phase5 p = null;
		
		if((ArrayList<String>) request.getAttribute("msg") != null){
			p = (Phase5) request.getAttribute("phase");
		}
		else{
			p = dao.getWizard().getPhase5(idPlan);
		}
		
		p.setFase(faseActual);

		
		dao.close();
		dao = null;
		
		request.setAttribute("phase5", p);
		
		request.getRequestDispatcher("/user_area/phases/phase5.jsp").forward(request, response);
		
	}
	
	public void phase6(HttpServletRequest request, HttpServletResponse response, Dao dao, int idPlan, int faseActual) throws SQLException, ServletException, IOException{
		
		
		
		Phase6 p = dao.getWizard().getPhase6(idPlan);
	
		p.setFase(faseActual);

		dao.close();
		dao = null;
		
		request.setAttribute("phase6", p);
		
		request.getRequestDispatcher("/user_area/phases/phase6.jsp").forward(request, response);
		
	}
	
	public void phase7(HttpServletRequest request, HttpServletResponse response, Dao dao, int idPlan, int faseActual) throws SQLException, ServletException, IOException{
		
		
		
		Phase7 p = null;
		
		if((ArrayList<String>) request.getAttribute("msg") != null){
			p = (Phase7) request.getAttribute("phase");
		}
		else{
			p = dao.getWizard().getPhase7(idPlan);
		}
		
		p.setFase(faseActual);

		
		dao.close();
		dao = null;
		
		request.setAttribute("phase7", p);
		
		request.getRequestDispatcher("/user_area/phases/phase7.jsp").forward(request, response);
		
	}
	
	public void phase8(HttpServletRequest request, HttpServletResponse response, Dao dao, int idPlan, int faseActual) throws SQLException, ServletException, IOException{
		
		
		
		Phase8 p = null;
		
		if((ArrayList<String>) request.getAttribute("msg") != null){
			p = (Phase8) request.getAttribute("phase");
		}
		else{
			p = dao.getWizard().getPhase8(idPlan);
		}
		
		p.setFase(faseActual);

		
		dao.close();
		dao = null;
		
		request.setAttribute("phase8", p);
		
		request.getRequestDispatcher("/user_area/phases/phase8.jsp").forward(request, response);
		
	}
}
