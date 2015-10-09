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
import controller.wizard.classes.Phase1;
import controller.wizard.classes.Plan;
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
				
				try {
					
					if(request.getParameter("fase") == null){
						fase = dao.getWizard().getFase(idPlan);
					}
					else{
						fase = Integer.parseInt(request.getParameter("fase"));
					}
					
					//Obtenemos las fases correctas
					boolean faseCorrecta[] = dao.getWizard().getFasesCorrectas(idPlan);
					String estadoFase[] = new String[20];
					
					for(int i = 0; i < 8; i++){
						
						if(faseCorrecta[i]){
							estadoFase[i] = "checked";
						}
						else{
							estadoFase[i] = "opened";
						}
						
					}
					
					if(fase < 8){
						estadoFase[fase] = "actual";
					}
					
					request.setAttribute("estadoFase", estadoFase);
					
					switch (fase) {
						case 1:	phase1(request, response, dao, idPlan); break;
						case 2:	phase2(request, response, dao, idPlan); break;
					}
					
				} catch (SQLException e) {
					new SQLError(request, response, e);
				}
				
				
				
			}
	
	}
	
	public void phase1(HttpServletRequest request, HttpServletResponse response, Dao dao, int idPlan) throws SQLException, ServletException, IOException{
		
		//Cargamos los municipios disponibles, y la información referente a la fase 1
		ArrayList<Municipio> municipios = dao.getWizard().getMunicipios();
		Phase1 p = dao.getWizard().getPhase1(idPlan);
		
		if(p.getDenominacionPlan() == null){
			p.setDenominacionPlan("P: " + new Date());
		}
		
		dao.close();
		
		
		request.setAttribute("municipios", municipios);
		request.setAttribute("phase1", p);
		
		request.getRequestDispatcher("/user_area/phases/phase1.jsp").forward(request, response);
		
	}
	
	public void phase2(HttpServletRequest request, HttpServletResponse response, Dao dao, int idPlan) throws SQLException, ServletException, IOException{
		
		request.getRequestDispatcher("/user_area/phases/phase2.jsp").forward(request, response);
		
	}

}
