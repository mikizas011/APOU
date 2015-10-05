package controller.errores;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SQLError {

	public SQLError(HttpServletRequest request, HttpServletResponse response, SQLException e){
		e.printStackTrace();
	}
	
}
