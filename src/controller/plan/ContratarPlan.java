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

import model.Dao;
import controller.errores.SQLError;
import controller.wizard.classes.Municipio;

/**
 * Servlet implementation class ContratarPlan
 */
@WebServlet("/user_area/hire_new_plan")
public class ContratarPlan extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ContratarPlan() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		if(nuevoPlanAceptado()){
			
			String msg = "";
			HttpSession sesion = request.getSession();
			
			try {
				Dao dao = new Dao();

				int id = dao.getProgram().newPlan(request.getUserPrincipal().getName());
				
				dao.getConection().close();
				
				request.setAttribute("id", id);
				
				dao.close();
				
				request.getRequestDispatcher("/user_area/load_plan").forward(request, response);
				
				
			} catch (SQLException e) {
				new SQLError(request, response, e);
			}
			
			
			
			
			
		}
	
	}
	
	public boolean nuevoPlanAceptado(){
		return true;
	}

}
