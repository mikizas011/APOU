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
import controller.plan.comprobadores.ComprobarFase1;
import controller.plan.comprobadores.ComprobarFase2;
import controller.plan.comprobadores.ComprobarFase3;
import controller.plan.comprobadores.ComprobarFase4;
import controller.plan.comprobadores.ComprobarFase5;
import controller.plan.comprobadores.ComprobarFase6;
import controller.plan.comprobadores.ComprobarFase7;
import controller.wizard.classes.Municipio;
import controller.wizard.classes.phases.Phase1;

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
		
		//Chequear si se valida.
		try {
			switch (Integer.parseInt(request.getParameter("phase"))) {
				case 1:	new ComprobarFase1(request, response).execute(); break;	
				case 2: new ComprobarFase2(request, response).execute(); break;
				case 3: new ComprobarFase3(request, response).execute(); break;
				case 4: new ComprobarFase4(request, response).execute(); break;
				case 5: new ComprobarFase5(request, response).execute(); break;
				case 6: new ComprobarFase6(request, response).execute(); break;
				case 7: new ComprobarFase7(request, response).execute(); break;
//				case 8: new ComprobarFase8(request, response).execute(); break;
			}
		} catch (SQLException e) {
			new SQLError(request, response, e);
		}
		
		
		
		
	
	}


}
