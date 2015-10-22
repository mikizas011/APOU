package pruebas;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import controller.wizard.classes.OrdenacionUrbanisticaEstructural;
import model.Dao;

public class prueba {

	public static void main(String[] args) throws SQLException{
	
		
		String a = "TIPO#1235:superficie";
			
		HashMap<Integer, OrdenacionUrbanisticaEstructural> update = new HashMap<Integer, OrdenacionUrbanisticaEstructural>();
		HashMap<String, OrdenacionUrbanisticaEstructural> insert = new HashMap<String, OrdenacionUrbanisticaEstructural>();
		
		if(a.contains("TIPO")){
			if(a.contains("#")){
				String key = a.substring(a.indexOf("TIPO"), a.indexOf(":"));
				if(insert.containsKey(key)){
					if(a.contains("superficie")){
						insert.get(key).setSuperficie(0);
					}
					else{
						insert.get(key).setNombre("");
					}
				}
				else{
					if(a.contains("superficie")){
						insert.put(key, new OrdenacionUrbanisticaEstructural(0, "", 0));
					}
					else{
						insert.put(key, new OrdenacionUrbanisticaEstructural(0, "", 0));
					}
					
				}
			}
			else{
				int key = Integer.parseInt(a.substring(a.indexOf("TIPO") + 4, a.indexOf(":")));
				
				if(update.containsKey(key)){
					if(a.contains("superficie")){
						update.get(key).setSuperficie(0);
					}
					else{
						update.get(key).setNombre("");
					}
				}
				else{
					if(a.contains("superficie")){
						update.put(key, new OrdenacionUrbanisticaEstructural(key, "", 0));
					}
					else{
						update.put(key, new OrdenacionUrbanisticaEstructural(key, "", 0));
					}
				}
			}

		}
		
		System.out.println(update);
		System.out.println(insert);
		
		
		
	}

}
