package controller.plan.comprobadores;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.errores.SQLError;
import controller.wizard.classes.OrdenacionUrbanisticaEstructural;
import controller.wizard.classes.phases.Phase;
import controller.wizard.classes.phases.Phase2;
import controller.wizard.classes.phases.Phase3;

public class ComprobarFase3 extends CerrarFase{

	Phase3 p;
	
	public ComprobarFase3(HttpServletRequest request,
			HttpServletResponse response) {
		super(request, response);
		// TODO Auto-generated constructor stub
	}


	@Override
	ArrayList<String> checkPhase() {
		// TODO Auto-generated method stub
		ArrayList<String> cf = new ArrayList<String>();
		boolean existeTOUE = false;
		boolean errorSuperficie = false;
		boolean errorNombre = false;
		
		for (Entry<String, String[]> entry : request.getParameterMap().entrySet()) {

			if(entry.getKey().indexOf("TIPO") != -1){
				existeTOUE = true;
				
				if(entry.getKey().contains("superficie")){
					if(!isPositive(request.getParameter(entry.getKey()))){
						errorSuperficie = true;
					}
				}
				else if(entry.getKey().contains("nombre")){
					if(isNull(request.getParameter(entry.getKey()))){
						errorNombre = true;
					}
				}


				
			}
			else if(entry.getKey().contains("edifMax:")){
				if(!isPositive(request.getParameter(entry.getKey()))){
					errorSuperficie = true;
				}
			}
		}
		
		if(!existeTOUE){
			cf.add("Por lo menos tiene que haber un tipo de ordenación urbanística estructural.");
		}
		if(errorSuperficie){
			cf.add("Las superficies tienen que ser positivas.");
		}
		if(errorNombre){
			cf.add("Los tipos de ordenación urbanística estructural tienen que tener un nombre.");
		}
		
		return cf;
	}

	@Override
	void updateIncorrectPhase(Phase p) {
		try {
			
			dao.getWizard().updatePhase3((Phase3) p);
			dao.getWizard().setPhaseIncorrect(p.getIdPlan(), 3);

		} catch (SQLException e) {
			new SQLError(request, response, e);
		}		
	}

	@Override
	void update(Phase p) {
		try {
			
			dao.getWizard().updatePhase3((Phase3) p);
			dao.getWizard().setPhaseCorrect(p.getIdPlan(), 3);

		} catch (SQLException e) {
			new SQLError(request, response, e);
		}			
	}

	@Override
	Phase loadedPhase() {
		try {
			p = dao.getWizard().getPhase3((Integer)request.getSession().getAttribute("idPlan"));
			
			for (Entry<String, String[]> entry : request.getParameterMap().entrySet()) {
				
				String itKey = entry.getKey();
				
				if(itKey.contains("TIPO")){
					if(itKey.contains("#")){
						String key = itKey.substring(itKey.indexOf("TIPO"), itKey.indexOf(":"));
						if(p.getInsert().containsKey(key)){
							if(itKey.contains("superficie")){
								p.getInsert().get(key).setSuperficie(Integer.parseInt(request.getParameter(itKey)));
							}
							else{
								p.getInsert().get(key).setNombre(request.getParameter(itKey));
							}
						}
						else{
							if(itKey.contains("superficie")){
								p.getInsert().put(key, new OrdenacionUrbanisticaEstructural(0, "", Integer.parseInt(request.getParameter(itKey))));
							}
							else{
								p.getInsert().put(key, new OrdenacionUrbanisticaEstructural(0, request.getParameter(itKey), 0));
							}
							
						}
					}
					else{
						int key = Integer.parseInt(itKey.substring(itKey.indexOf("TIPO") + 4, itKey.indexOf(":")));
						
						if(p.getUpdate().containsKey(key)){
							if(itKey.contains("superficie")){
								p.getUpdate().get(key).setSuperficie(Integer.parseInt(request.getParameter(itKey)));
							}
							else{
								p.getUpdate().get(key).setNombre(request.getParameter(itKey));
							}
						}
						else{
							if(itKey.contains("superficie")){
								p.getUpdate().put(key, new OrdenacionUrbanisticaEstructural(key, "", Integer.parseInt(request.getParameter(itKey))));
							}
							else{
								p.getUpdate().put(key, new OrdenacionUrbanisticaEstructural(key, request.getParameter(itKey), 0));
							}
						}
					}

				}
				else if(itKey.contains("edifMax:bajo")){
					p.setEdifMaxBajoRasante(Integer.parseInt(request.getParameter(itKey)));
				}
				else if(itKey.contains("edifMax:sobre")){
					p.setEdifMaxSobreRasante(Integer.parseInt(request.getParameter(itKey)));
				}
				
			}

			return p;
		
		} catch (SQLException e) {
			new SQLError(request, response, e);
			return null;
		}
	}

	@Override
	Phase correctedPhase() {
		
		try {
			p = dao.getWizard().getPhase3((Integer)request.getSession().getAttribute("idPlan"));
			
			for (Entry<String, String[]> entry : request.getParameterMap().entrySet()) {
				
				String itKey = entry.getKey();
				
				if(itKey.contains("TIPO")){
					if(itKey.contains("#")){
						String key = itKey.substring(itKey.indexOf("TIPO"), itKey.indexOf(":"));
						if(p.getInsert().containsKey(key)){
							if(itKey.contains("superficie")){
								if(!isPositive(request.getParameter(itKey))){
									p.getInsert().get(key).setSuperficie(0);
								}
								else{
									p.getInsert().get(key).setSuperficie(Integer.parseInt(request.getParameter(itKey)));
								}
							}
							else{
								if(isNull(request.getParameter(itKey))){
									p.getInsert().get(key).setNombre("");
								}
								else{
									p.getInsert().get(key).setNombre(request.getParameter(itKey));
								}
							}
						}
						else{
							if(itKey.contains("superficie")){
								if(!isPositive(request.getParameter(itKey))){
									p.getInsert().put(key, new OrdenacionUrbanisticaEstructural(0, "", 0));
								}
								else{
									p.getInsert().put(key, new OrdenacionUrbanisticaEstructural(0, "", Integer.parseInt(request.getParameter(itKey))));
								}
							}
							else{
								if(isNull(request.getParameter(itKey))){
									p.getInsert().put(key, new OrdenacionUrbanisticaEstructural(0, "", 0));
								}
								else{
									p.getInsert().put(key, new OrdenacionUrbanisticaEstructural(0, request.getParameter(itKey), 0));
								}
							}
							
						}
					}
					else{
						int key = Integer.parseInt(itKey.substring(itKey.indexOf("TIPO") + 4, itKey.indexOf(":")));
						
						if(p.getUpdate().containsKey(key)){
							if(itKey.contains("superficie")){
								if(!isPositive(request.getParameter(itKey))){
									p.getUpdate().get(key).setSuperficie(0);
								}
								else{
									p.getUpdate().get(key).setSuperficie(Integer.parseInt(request.getParameter(itKey)));
								}
							}
							else{
								if(isNull(request.getParameter(itKey))){
									p.getUpdate().get(key).setNombre("");
								}
								else{
									p.getUpdate().get(key).setNombre(request.getParameter(itKey));
								}
							}
						}
						else{
							if(itKey.contains("superficie")){
								if(!isPositive(request.getParameter(itKey))){
									p.getUpdate().put(key, new OrdenacionUrbanisticaEstructural(key, "", 0));
								}
								else{
									p.getUpdate().put(key, new OrdenacionUrbanisticaEstructural(key, "", Integer.parseInt(request.getParameter(itKey))));
								}
							}
							else{
								if(isNull(request.getParameter(itKey))){
									p.getUpdate().put(key, new OrdenacionUrbanisticaEstructural(key, "", 0));
								}
								else{
									p.getUpdate().put(key, new OrdenacionUrbanisticaEstructural(key, request.getParameter(itKey), 0));
								}
							}
						}
					}

				}
				else if(itKey.contains("edifMax:bajo")){
					if(!isPositive(request.getParameter(itKey))){
						p.setEdifMaxBajoRasante(0);
					}
					else{
						p.setEdifMaxBajoRasante(Integer.parseInt(request.getParameter(itKey)));
					}
				}
				else if(itKey.contains("edifMax:sobre")){
					if(!isPositive(request.getParameter(itKey))){
						p.setEdifMaxSobreRasante(0);
					}
					else{
						p.setEdifMaxSobreRasante(Integer.parseInt(request.getParameter(itKey)));
					}
				}
				
			}

			
			return p;
		
		} catch (SQLException e) {
			new SQLError(request, response, e);
			return null;
		}
		
	}

}
