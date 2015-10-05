package controller.users;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Dao;

/**
 * Servlet implementation class Register
 */
@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Register() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Dao dao = new Dao();
		String user = request.getParameter("user");
		String mensaje = "";

		if(isAceptado(dao, user)){
			
			mensaje = dao.getLogin().addUser(user, request.getParameter("pass"));
			
		}
		else{
			mensaje = "No se ha aceptado el usuario.";
		}
		request.getSession().setAttribute("mensaje", mensaje);
		if(mensaje.indexOf("correctamente") != -1){
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
		else{
			request.getRequestDispatcher("register.jsp").forward(request, response);
		}
		
	}
	
	private boolean isAceptado(Dao dao, String user){
		return true;
	}

}
