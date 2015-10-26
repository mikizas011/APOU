package controller.plan;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Configuracion;
import controller.errores.SQLError;
import controller.wizard.classes.Plan;
import model.Dao;

/**
 * Servlet implementation class CargarPlanes
 */
@WebServlet("/user_area/load_plans")
public class CargarPlanes extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CargarPlanes() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Dao dao = new Dao();
		HttpSession sesion = request.getSession();

		try {
			ArrayList<Plan> planes = dao.getProgram().getPlanes(request.getUserPrincipal().getName());
			
			for(int i = 0; i< planes.size(); i++){
				if(planes.get(i).getDenominacion() == null){
					planes.get(i).setDenominacion("P: " + planes.get(i).getFechaCreacion());
				}
			}
			
			dao.close();
			dao = null;
			
			sesion.setAttribute("planes", planes);
			
			request.getRequestDispatcher("/user_area/plans.jsp").forward(request, response);
			
		} catch (SQLException e) {
			SQLError error = new SQLError(request, response, e);
		}

	
	}

}
