package controller.plan.comprobadores;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.errores.SQLError;
import controller.wizard.classes.OrdenacionUrbanisticaPormenorizada;
import controller.wizard.classes.phases.Phase;
import controller.wizard.classes.phases.Phase4;

public class ComprobarFase4 extends CerrarFase{

	Phase4 p;
	
	public ComprobarFase4(HttpServletRequest request,
			HttpServletResponse response) {
		super(request, response);
		// TODO Auto-generated constructor stub
	}

	@Override
	ArrayList<String> checkData() {
		ArrayList<String> cf = new ArrayList<String>();
		
		boolean existeTOUP = false;
		boolean errorSuperficie = false;
		boolean errorNombre = false;
		boolean errorParcelas = false;
		
		for (Entry<String, String[]> entry : request.getParameterMap().entrySet()) {

			if(entry.getKey().indexOf("TIPO") != -1){
				existeTOUP = true;
				
				if(entry.getKey().contains(":n:")){
					if(!isPositive(request.getParameter(entry.getKey()))){
						errorSuperficie = true;
					}
				}
				else if(entry.getKey().contains(":t:")){
					if(isNull(request.getParameter(entry.getKey()))){
						errorNombre = true;
					}
				}


				
			}
			else if(entry.getKey().contains("UE")){
				if(!isPositive(request.getParameter(entry.getKey()))){
					errorParcelas = true;
				}
			
			}

		}
		
		if(!existeTOUP){
			cf.add("Por lo menos tiene que haber un tipo de ordenación urbanística pormenorizada.");
		}
		if(errorSuperficie){
			cf.add("Las superficies tienen que ser positivas.");
		}
		if(errorNombre){
			cf.add("Los tipos de ordenación urbanística pormenorizada tienen que tener un nombre.");
		}
		if(errorParcelas){
			cf.add("Las unidades de ejecución tienen que tener como mínimo una parcela resultante.");
		}
		
		return cf;
	}

	@Override
	void update(Phase p) {
		try {
			
			dao.getWizard().updatePhase4((Phase4) p);
			dao.getWizard().setPhaseCorrect(p.getIdPlan(), 4);

		} catch (SQLException e) {
			new SQLError(request, response, e);
		}		
	}

	@Override
	void updateIncorrectPhase(Phase p) {

		try {
			
			dao.getWizard().updatePhase4((Phase4) p);
			dao.getWizard().setPhaseIncorrect(p.getIdPlan(), 4);

		} catch (SQLException e) {
			new SQLError(request, response, e);
		}		
		
	}

	@Override
	Phase loadedPhase() {
		try {
			p = dao.getWizard().getPhase4((Integer)request.getSession().getAttribute("idPlan"));
			
			
			for (Entry<String, String[]> entry : request.getParameterMap().entrySet()) {
				
				String itKey = entry.getKey();
				
				if(itKey.contains("TIPO")){
					//TIPO RECIEN AÑADIDO
					if(itKey.contains("#")){
						String key = itKey.substring(itKey.indexOf("TIPO"), itKey.indexOf(":"));
						//ya se ha añadido algún dato de este TOUP
						if(p.getInsert().containsKey(key)){
							if(itKey.contains(":n:")){
								if(itKey.contains(":srpb")){
									p.getInsert().get(key).setCoefSRPB(Double.parseDouble(request.getParameter(itKey)));									}
								else if(itKey.contains(":srpp")){
									p.getInsert().get(key).setCoefSRPP(Double.parseDouble(request.getParameter(itKey)));
								}
								else if(itKey.contains(":br")){
									p.getInsert().get(key).setCoefBR(Double.parseDouble(request.getParameter(itKey)));
								}
							}
							else{
								p.getInsert().get(key).setDenominacion(request.getParameter(itKey));
							}
						}
						else{
							if(itKey.contains(":n:")){
								if(itKey.contains(":srpb")){
									p.getInsert().put(key, new OrdenacionUrbanisticaPormenorizada("", Double.parseDouble(request.getParameter(itKey)), 0, 0, 0));
								}
								else if(itKey.contains(":srpp")){
									p.getInsert().put(key, new OrdenacionUrbanisticaPormenorizada("", 0, Double.parseDouble(request.getParameter(itKey)), 0, 0));
								}
								else if(itKey.contains(":br")){
									p.getInsert().put(key, new OrdenacionUrbanisticaPormenorizada("", 0, 0, Double.parseDouble(request.getParameter(itKey)), 0));
								}
							}
							else{
								p.getInsert().put(key, new OrdenacionUrbanisticaPormenorizada(request.getParameter(itKey), 0, 0, 0, 0));
							}
							
						}
					}
					//TIPO QUE NO ACABA DE SER AÑADIDO
					else{
						int key = Integer.parseInt(itKey.substring(itKey.indexOf("TIPO") + 4, itKey.indexOf(":")));
						
						if(p.getUpdate().containsKey(key)){
							if(itKey.contains(":n:")){
								if(itKey.contains(":srpb")){
									p.getUpdate().get(key).setCoefSRPB(Double.parseDouble(request.getParameter(itKey)));									}
								else if(itKey.contains(":srpp")){
									p.getUpdate().get(key).setCoefSRPP(Double.parseDouble(request.getParameter(itKey)));
								}
								else if(itKey.contains(":br")){
									p.getUpdate().get(key).setCoefBR(Double.parseDouble(request.getParameter(itKey)));
								}
							}
							else{
								p.getUpdate().get(key).setDenominacion(request.getParameter(itKey));
							}
						}
						else{
							if(itKey.contains(":n:")){
								if(itKey.contains(":srpb")){
									p.getUpdate().put(key, new OrdenacionUrbanisticaPormenorizada("", Double.parseDouble(request.getParameter(itKey)), 0, 0, key));
								}
								else if(itKey.contains(":srpp")){
									p.getUpdate().put(key, new OrdenacionUrbanisticaPormenorizada("", 0, Double.parseDouble(request.getParameter(itKey)), 0, key));
								}
								else if(itKey.contains(":br")){
									p.getUpdate().put(key, new OrdenacionUrbanisticaPormenorizada("", 0, 0, Double.parseDouble(request.getParameter(itKey)), key));
								}
							}
							else{
								p.getUpdate().put(key, new OrdenacionUrbanisticaPormenorizada(request.getParameter(itKey), 0, 0, 0, key));
							}
						}
					}

				}
				else if(itKey.contains("UE")){
					p.getUes().get(itKey).setNumeroParcelasResultantes(Integer.parseInt(request.getParameter(itKey)));
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
			p = dao.getWizard().getPhase4((Integer)request.getSession().getAttribute("idPlan"));
		
			
			for (Entry<String, String[]> entry : request.getParameterMap().entrySet()) {
				
				String itKey = entry.getKey();
				
				if(itKey.contains("TIPO")){
					//TIPO RECIEN AÑADIDO
					if(itKey.contains("#")){
						String key = itKey.substring(itKey.indexOf("TIPO"), itKey.indexOf(":"));
						//ya se ha añadido algún dato de este TOUP
						if(p.getInsert().containsKey(key)){
							if(itKey.contains(":n:")){
								if(!isPositive(request.getParameter(itKey))){
									if(itKey.contains(":srpb")){
										p.getInsert().get(key).setCoefSRPB(0);
									}
									else if(itKey.contains(":srpp")){
										p.getInsert().get(key).setCoefSRPP(0);
									}
									else if(itKey.contains(":br")){
										p.getInsert().get(key).setCoefBR(0);
									}
								}
								else{
									if(itKey.contains(":srpb")){
										p.getInsert().get(key).setCoefSRPB(Double.parseDouble(request.getParameter(itKey)));									}
									else if(itKey.contains(":srpp")){
										p.getInsert().get(key).setCoefSRPP(Double.parseDouble(request.getParameter(itKey)));
									}
									else if(itKey.contains(":br")){
										p.getInsert().get(key).setCoefBR(Double.parseDouble(request.getParameter(itKey)));
									}
									
								}
							}
							else{
								if(isNull(request.getParameter(itKey))){
									p.getInsert().get(key).setDenominacion("");
								}
								else{
									p.getInsert().get(key).setDenominacion(request.getParameter(itKey));
								}
							}
						}
						else{
							if(itKey.contains(":n:")){
								
								if(!isPositive(request.getParameter(itKey))){
									
									p.getInsert().put(key, new OrdenacionUrbanisticaPormenorizada("", 0, 0, 0, 0));
								}
								else{
									if(itKey.contains(":srpb")){
										p.getInsert().put(key, new OrdenacionUrbanisticaPormenorizada("", Double.parseDouble(request.getParameter(itKey)), 0, 0, 0));
									}
									else if(itKey.contains(":srpp")){
										p.getInsert().put(key, new OrdenacionUrbanisticaPormenorizada("", 0, Double.parseDouble(request.getParameter(itKey)), 0, 0));
									}
									else if(itKey.contains(":br")){
										p.getInsert().put(key, new OrdenacionUrbanisticaPormenorizada("", 0, 0, Double.parseDouble(request.getParameter(itKey)), 0));
									}
								}
							}
							else{
								if(isNull(request.getParameter(itKey))){
									p.getInsert().put(key, new OrdenacionUrbanisticaPormenorizada("", 0, 0, 0, 0));
								}
								else{
									p.getInsert().put(key, new OrdenacionUrbanisticaPormenorizada(request.getParameter(itKey), 0, 0, 0, 0));
								}
							}
							
						}
					}
					//TIPO QUE NO ACABA DE SER AÑADIDO
					else{
						int key = Integer.parseInt(itKey.substring(itKey.indexOf("TIPO") + 4, itKey.indexOf(":")));
						
						if(p.getUpdate().containsKey(key)){
							if(itKey.contains(":n:")){
								if(!isPositive(request.getParameter(itKey))){
									if(itKey.contains(":srpb")){
										p.getUpdate().get(key).setCoefSRPB(0);
									}
									else if(itKey.contains(":srpp")){
										p.getUpdate().get(key).setCoefSRPP(0);
									}
									else if(itKey.contains(":br")){
										p.getUpdate().get(key).setCoefBR(0);
									}
								}
								else{
									if(itKey.contains(":srpb")){
										p.getUpdate().get(key).setCoefSRPB(Double.parseDouble(request.getParameter(itKey)));									}
									else if(itKey.contains(":srpp")){
										p.getUpdate().get(key).setCoefSRPP(Double.parseDouble(request.getParameter(itKey)));
									}
									else if(itKey.contains(":br")){
										p.getUpdate().get(key).setCoefBR(Double.parseDouble(request.getParameter(itKey)));
									}
								}
							}
							else{
								if(isNull(request.getParameter(itKey))){
									p.getUpdate().get(key).setDenominacion("");
								}
								else{
									p.getUpdate().get(key).setDenominacion(request.getParameter(itKey));
								}
							}
						}
						else{
							if(itKey.contains(":n:")){
								if(!isPositive(request.getParameter(itKey))){
									p.getUpdate().put(key, new OrdenacionUrbanisticaPormenorizada("", 0, 0, 0, key));
								}
								else{
									if(itKey.contains(":srpb")){
										p.getUpdate().put(key, new OrdenacionUrbanisticaPormenorizada("", Double.parseDouble(request.getParameter(itKey)), 0, 0, key));
									}
									else if(itKey.contains(":srpp")){
										p.getUpdate().put(key, new OrdenacionUrbanisticaPormenorizada("", 0, Double.parseDouble(request.getParameter(itKey)), 0, key));
									}
									else if(itKey.contains(":br")){
										p.getUpdate().put(key, new OrdenacionUrbanisticaPormenorizada("", 0, 0, Double.parseDouble(request.getParameter(itKey)), key));
									}
								}
							}
							else{
								if(isNull(request.getParameter(itKey))){
									p.getUpdate().put(key, new OrdenacionUrbanisticaPormenorizada("", 0, 0, 0, key));
								}
								else{
									p.getUpdate().put(key, new OrdenacionUrbanisticaPormenorizada(request.getParameter(itKey), 0, 0, 0, key));
								}
							}
						}
					}

				}
				else if(itKey.contains("UE")){
					if(!isPositive(request.getParameter(itKey))){
						p.getUes().get(itKey).setNumeroParcelasResultantes(0);
					}
					else{
						p.getUes().get(itKey).setNumeroParcelasResultantes(Integer.parseInt(request.getParameter(itKey)));
					}
				}

				
			}

			
			return p;
			
		} catch (SQLException e) {
			new SQLError(request, response, e);
			return null;
		}
		
		
	}

	@Override
	ArrayList<String> checkPhase(ArrayList<String> msg, Phase pa) {
		// TODO Auto-generated method stub
		return msg;
	}
	
}
