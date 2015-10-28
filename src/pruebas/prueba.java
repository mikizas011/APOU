package pruebas;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import controller.wizard.classes.OrdenacionUrbanisticaEstructural;
import model.Dao;

public class prueba {

	public static void main(String[] args) throws SQLException{
	
		Dao dao = new Dao();
		
		dao.getWizard().getPhase6(15);
	
	}

}
