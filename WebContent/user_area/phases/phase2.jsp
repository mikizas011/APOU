<%@page import="java.util.TreeSet"%>
<%@page import="java.util.SortedSet"%>
<%@page import="controller.wizard.classes.P2unidadEjecucion"%>
<%@page import="controller.wizard.classes.phases.Phase2"%>
<%@page import="java.util.Arrays"%>
<%@page import="com.mysql.fabric.xmlrpc.base.Array"%>
<%@page import="java.util.Map.Entry"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.Iterator"%>
<%@page import="controller.wizard.classes.Municipio"%>
<%@page import="java.util.ArrayList"%>
<%@page import="controller.wizard.classes.Plan"%>
<%@page import="controller.Configuracion"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<link rel="stylesheet" href="<%=Configuracion.getInstance().getRoot()%>css/style.css" type="text/css">
		<title>Parcelas aportadas</title>
		<script src="<%=Configuracion.getInstance().getRoot()%>js/jquery-1.11.1.js"></script>
		<script>
			function transferCallToServlet(fase, id)
			{
				document.requestForm.action = "<%=Configuracion.getInstance().getRoot() %>user_area/load_plan";
				document.requestForm.id.value = id;
				document.requestForm.fase.value = fase;
				document.requestForm.submit();
				
			}
		</script>
		
		
	</head>
	<body>
		
		<jsp:include page="/common/header.jsp" />
		<jsp:include page="/common/userHeader.jsp" />
		<div class="marginNavbarUser"></div>
		<div class = "contentWrapper white">
			<div id="body">
				
				<%String estadoFase[] = (String[])request.getAttribute("estadoFase");%>
				
				<div class="phase">
					<form name="requestForm" method="POST">
					<input type="hidden" name="fase" >
					<input type="hidden" name="id" >
						<div class="phases">
							<ul>
								<li class="<%=estadoFase[0] %>" title="Datos generales"><a onclick="transferCallToServlet(1, <%=request.getSession().getAttribute("idPlan")%>)">Fase 1</a></li>
								<li class="<%=estadoFase[1] %>" title="Parcelas aportadas"><a onclick="transferCallToServlet(2, <%=request.getSession().getAttribute("idPlan")%>)">Fase 2</a></li>
								<li class="<%=estadoFase[2] %>" title="Ordenación urbanística estructural"><a onclick="transferCallToServlet(3, <%=request.getSession().getAttribute("idPlan")%>)">Fase 3</a></li>
								<li class="<%=estadoFase[3] %>" title="Ordenación urbanística pormenorizada p.1."><a onclick="transferCallToServlet(4, <%=request.getSession().getAttribute("idPlan")%>)">Fase 4</a></li>
								<li class="<%=estadoFase[4] %>" title="Ordenación urbanística pormenorizada p.2."><a onclick="transferCallToServlet(5, <%=request.getSession().getAttribute("idPlan")%>)">Fase 5</a></li>
								<li class="<%=estadoFase[5] %>" title="Cálculo de la edificabilidad urbanística ponderada"><a onclick="transferCallToServlet(6, <%=request.getSession().getAttribute("idPlan")%>)">Fase 6</a></li>
								<li class="<%=estadoFase[6] %>" title="Edificabilidad urbanística correspondiente al ayuntamiento"><a onclick="transferCallToServlet(7, <%=request.getSession().getAttribute("idPlan")%>)">Fase 7</a></li>
								<li class="<%=estadoFase[7] %>" title="Red de sistemas locales"><a onclick="transferCallToServlet(8, <%=request.getSession().getAttribute("idPlan")%>)">Fase 8</a></li>
								<li class="closed" title="Edificios fuera de ordenación"><a onclick="transferCallToServlet(9, <%=request.getSession().getAttribute("idPlan")%>)">Fase 9</a></li>
								<li class="closed" title="Selección de leyes incluidas"><a onclick="transferCallToServlet(10, <%=request.getSession().getAttribute("idPlan")%>)">Fase 10</a></li>
								<li class="closed" title="Introducción"><a onclick="transferCallToServlet(11, <%=request.getSession().getAttribute("idPlan")%>)">Fase 11</a></li>
								<li class="closed" title="Análisis urbanístico del A.I.U. 21"><a onclick="transferCallToServlet(12, <%=request.getSession().getAttribute("idPlan")%>)">Fase 12</a></li>
								<li class="closed" title="Determinaciones de carácter estructural para el ámbito"><a onclick="transferCallToServlet(13, <%=request.getSession().getAttribute("idPlan")%>)">Fase 13</a></li>
								<li class="closed" title="Justificación de la conveniencia de la propuesta"><a onclick="transferCallToServlet(14, <%=request.getSession().getAttribute("idPlan")%>)">Fase 14</a></li>
								<li class="closed" title="Tramitación"><a onclick="transferCallToServlet(15, <%=request.getSession().getAttribute("idPlan")%>)">Fase 15</a></li>
								<li class="closed" title="Criterios y objetivos del plan"><a onclick="transferCallToServlet(16, <%=request.getSession().getAttribute("idPlan")%>)">Fase 16</a></li>
								<li class="closed" title="Descripción de la ordenación propuesta"><a onclick="transferCallToServlet(17, <%=request.getSession().getAttribute("idPlan")%>)">Fase 17</a></li>
								<li class="closed" title="Justificación y cumplimiento del planeamiento general y la legislación urbanística"><a onclick="transferCallToServlet(18, <%=request.getSession().getAttribute("idPlan")%>)">Fase 18</a></li>
								<li class="closed" title="Justificación del cumplimiento de las determinaciones de la ordenación territorial"><a onclick="transferCallToServlet(19, <%=request.getSession().getAttribute("idPlan")%>)">Fase 19</a></li>
								<li class="closed" title="Gestión y ejecución del plan parcial"><a onclick="transferCallToServlet(20, <%=request.getSession().getAttribute("idPlan")%>)">Fase 20</a></li>
							</ul>
						</div>
					</form>
					<div class="content">
						<% Phase2 p = (Phase2)request.getAttribute("phase2");%>
				
						<h1>PARCELAS APORTADAS</h1>

						<form method="POST" action="<%=Configuracion.getInstance().getRoot()%>user_area/check_phase" >
								
							<%

							SortedSet<Integer> keys = new TreeSet<Integer>(p.getMap().keySet());
							for(Integer key : keys){
								P2unidadEjecucion ue = p.getMap().get(key);
								
		
								%>
								
								<div class="unidadEjecucion">
								
									<h2><%=ue.getDenominacion() %></h2>
								
									<table>
									
										<tr>
											<th>PARCELA</th>
											<th>PROPIETARIO</th>
											<th>DOMINIO</th>
											<th>SUPERFICIE</th>											
										</tr>
										
										<%
											for(int i = 0; i < ue.getParcelas().size(); i++){%>
											
											<tr>
												<td><%=ue.getParcelas().get(i).getDenominacion() %></td>
												<td><input name="<%=ue.getParcelas().get(i).getDenominacion() + ":propietario" %>" type="text" value="<%=ue.getParcelas().get(i).getPropietario() %>" ></td>
												<td>
													<select name="<%=ue.getParcelas().get(i).getDenominacion()%>:dominio">
													
														<%
														if(ue.getParcelas().get(i).getDominio() != null){
															if(ue.getParcelas().get(i).getDominio().equals("Pública patrimonial")){ %>
															  	<option value="Pública patrimonial" selected>Pública patrimonial</option>
																<option value="Pública de cesión">Pública de cesión</option>
																<option value="Privada">Privada</option>
													  		<%} else if(ue.getParcelas().get(i).getDominio().equals("Pública de cesión")){ %>
															  	<option value="Pública patrimonial">Pública patrimonial</option>
																<option value="Pública de cesión" selected>Pública de cesión</option>
																<option value="Privada">Privada</option>
													  		<%}else{%>
													  			<option value="Pública patrimonial">Pública patrimonial</option>
																<option value="Pública de cesión">Pública de cesión</option>
																<option value="Privada" selected>Privada</option>
													  			
													  		<%}
															} else{%>
																<option value="Pública patrimonial" selected>Pública patrimonial</option>
																<option value="Pública de cesión">Pública de cesión</option>
																<option value="Privada">Privada</option>
														<%} %>
													
														
													</select>
												</td>
												<td><input name="<%=ue.getParcelas().get(i).getDenominacion() + ":superficie" %>" type="number" value="<%=ue.getParcelas().get(i).getSuperficie() %>" ></td>
											</tr>
												
											<%}%>
									
									</table>
									
									<table>
										<tr>
											<th>M2 DE SUPERFICIE DE SUELO CON SERVIDUMBRES SECTORIALES</th>
											<td><input name="<%=ue.getDenominacion() + ":servidumbre" %>" type="number" value="<%=ue.getSuperficieServidumbre() %>" ></td>
										</tr>
									</table>
									<input name="<%=ue.getDenominacion() + ":idUe" %>" type="hidden" value="<%=ue.getIdUnidadEjecucion() %>">
									
								
								</div>
								
								
														
	
							<%}%>
						
							<input type="hidden" name="phase" value="2">
					  		<input class="button" type="submit" value="Comprobar y guardar">
						
						</form>
						
						<%if(request.getAttribute("msg") != null){ 
							 ArrayList<String> msg = (ArrayList<String>)request.getAttribute("msg"); 
							 %>
							 <div class="errormsg">
							 <%
							 for(int i = 0; i < msg.size(); i++){
							 %>
								<p class="errorfont"><%=msg.get(i).toString() %></p>
							<%}%>
							 	</div>
						<%}%>
		
					    
					</div>
					<div class="clear"></div>
				</div>
				
				
		
			    
			    
		  	</div>
		</div>
		<jsp:include page="/common/footer.jsp" />
	
	</body>
</html>
