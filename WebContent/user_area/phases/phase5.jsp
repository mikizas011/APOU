
<%@page import="controller.wizard.classes.ParcelaResultante"%>
<%@page import="java.util.ArrayList"%>
<%@page import="controller.wizard.classes.P5UnidadEjecucion"%>
<%@page import="java.util.TreeSet"%>
<%@page import="java.util.SortedSet"%>
<%@page import="controller.wizard.classes.phases.Phase5"%>
<%@page import="controller.wizard.classes.Plan"%>
<%@page import="controller.Configuracion"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<link rel="stylesheet" href="<%=Configuracion.getInstance().getRoot()%>css/style.css" type="text/css">
		<title>Ordenaci�n urban�stica pormenorizada P.2.</title>
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
								<li class="<%=estadoFase[2] %>" title="Ordenaci�n urban�stica estructural"><a onclick="transferCallToServlet(3, <%=request.getSession().getAttribute("idPlan")%>)">Fase 3</a></li>
								<li class="<%=estadoFase[3] %>" title="Ordenaci�n urban�stica pormenorizada p.1."><a onclick="transferCallToServlet(4, <%=request.getSession().getAttribute("idPlan")%>)">Fase 4</a></li>
								<li class="<%=estadoFase[4] %>" title="Ordenaci�n urban�stica pormenorizada p.2."><a onclick="transferCallToServlet(5, <%=request.getSession().getAttribute("idPlan")%>)">Fase 5</a></li>
								<li class="<%=estadoFase[5] %>" title="C�lculo de la edificabilidad urban�stica ponderada"><a onclick="transferCallToServlet(6, <%=request.getSession().getAttribute("idPlan")%>)">Fase 6</a></li>
								<li class="<%=estadoFase[6] %>" title="Edificabilidad urban�stica correspondiente al ayuntamiento"><a onclick="transferCallToServlet(7, <%=request.getSession().getAttribute("idPlan")%>)">Fase 7</a></li>
								<li class="<%=estadoFase[7] %>" title="Red de sistemas locales"><a onclick="transferCallToServlet(8, <%=request.getSession().getAttribute("idPlan")%>)">Fase 8</a></li>
								<li class="closed" title="Edificios fuera de ordenaci�n"><a onclick="transferCallToServlet(9, <%=request.getSession().getAttribute("idPlan")%>)">Fase 9</a></li>
								<li class="closed" title="Selecci�n de leyes incluidas"><a onclick="transferCallToServlet(10, <%=request.getSession().getAttribute("idPlan")%>)">Fase 10</a></li>
								<li class="closed" title="Introducci�n"><a onclick="transferCallToServlet(11, <%=request.getSession().getAttribute("idPlan")%>)">Fase 11</a></li>
								<li class="closed" title="An�lisis urban�stico del A.I.U. 21"><a onclick="transferCallToServlet(12, <%=request.getSession().getAttribute("idPlan")%>)">Fase 12</a></li>
								<li class="closed" title="Determinaciones de car�cter estructural para el �mbito"><a onclick="transferCallToServlet(13, <%=request.getSession().getAttribute("idPlan")%>)">Fase 13</a></li>
								<li class="closed" title="Justificaci�n de la conveniencia de la propuesta"><a onclick="transferCallToServlet(14, <%=request.getSession().getAttribute("idPlan")%>)">Fase 14</a></li>
								<li class="closed" title="Tramitaci�n"><a onclick="transferCallToServlet(15, <%=request.getSession().getAttribute("idPlan")%>)">Fase 15</a></li>
								<li class="closed" title="Criterios y objetivos del plan"><a onclick="transferCallToServlet(16, <%=request.getSession().getAttribute("idPlan")%>)">Fase 16</a></li>
								<li class="closed" title="Descripci�n de la ordenaci�n propuesta"><a onclick="transferCallToServlet(17, <%=request.getSession().getAttribute("idPlan")%>)">Fase 17</a></li>
								<li class="closed" title="Justificaci�n y cumplimiento del planeamiento general y la legislaci�n urban�stica"><a onclick="transferCallToServlet(18, <%=request.getSession().getAttribute("idPlan")%>)">Fase 18</a></li>
								<li class="closed" title="Justificaci�n del cumplimiento de las determinaciones de la ordenaci�n territorial"><a onclick="transferCallToServlet(19, <%=request.getSession().getAttribute("idPlan")%>)">Fase 19</a></li>
								<li class="closed" title="Gesti�n y ejecuci�n del plan parcial"><a onclick="transferCallToServlet(20, <%=request.getSession().getAttribute("idPlan")%>)">Fase 20</a></li>
							</ul>
						</div>
					</form>
					<div class="content">
						<% Phase5 p = (Phase5)request.getAttribute("phase5");%>
				
						
						<h1>Ordenaci�n urban�stica pormenorizada P.2.</h1>
						
						<form method="POST" action="<%=Configuracion.getInstance().getRoot()%>user_area/check_phase" >
								
							<%

							SortedSet<Integer> keys = new TreeSet<Integer>(p.getMap().keySet());
							for(Integer key : keys){
								P5UnidadEjecucion ue = p.getMap().get(key);
								%>
								
								<div class="unidadEjecucion">
								
									<h2><%=ue.getDenominacion() %></h2>
									
									<%
									SortedSet<Integer> keys2 = new TreeSet<Integer>(ue.getParcelas().keySet());
									for(Integer key2 : keys2){
										ParcelaResultante pr = ue.getParcelas().get(key2);
										%>
										<table>
											<tr>
												<td>Denominacion</td>
												<td><%=pr.getDenominacion() %></td>
											</tr>
											<tr>
												<td>Tipo de calificacion pormenorizada</td>
												<td>
												<select name="PR<%=pr.getIdParcelaResultante()%>:tipo">
													
												</select>
												</td>
											</tr>
											<tr>
												<td>Superficie parcela</td>
												<td><input name="PR<%=pr.getIdParcelaResultante()%>:superficie" type="number" value="<%=pr.getSuperficie()%>"></td>
											</tr>
											<tr>
												<td>Ocupaci�n bajo rasante actual</td>
												<td><input name="PR<%=pr.getIdParcelaResultante()%>:obr_a" type="number" value="<%=pr.getObrA()%>"></td>
											</tr>
											<tr>
												<td>Ocupaci�n bajo rasante nuevo</td>
												<td><input name="PR<%=pr.getIdParcelaResultante()%>:obr_n" type="number" value="<%=pr.getObrN()%>"></td>
											</tr>	
											<tr>
												<td>Ocupaci�n sobre rasante actual</td>
												<td><input name="PR<%=pr.getIdParcelaResultante()%>:osr_a" type="number" value="<%=pr.getOsrA()%>"></td>
											</tr>
											<tr>
												<td>Ocupaci�n sobre rasante nuevo</td>
												<td><input name="PR<%=pr.getIdParcelaResultante()%>:osr_n" type="number" value="<%=pr.getOsrN()%>"></td>
											</tr>
											<tr>
												<td>Edificabilidad bajo rasante actual</td>
												<td><input name="PR<%=pr.getIdParcelaResultante()%>:ebr_a" type="number" value="<%=pr.getEbrA()%>"></td>
											</tr>
											<tr>
												<td>Edificabilidad bajo rasante nuevo</td>
												<td><input name="PR<%=pr.getIdParcelaResultante()%>:ebr_n" type="number" value="<%=pr.getEbrN()%>"></td>
											</tr>
											<tr>
												<td>Edificabilidad sobre rasante planta baja actual</td>
												<td><input name="PR<%=pr.getIdParcelaResultante()%>:esrpb_a" type="number" value="<%=pr.getEsrpbA()%>"></td>
											</tr>
											<tr>
												<td>Edificabilidad sobre rasante planta baja nuevo</td>
												<td><input name="PR<%=pr.getIdParcelaResultante()%>:esrpb_n" type="number" value="<%=pr.getEsrpbN()%>"></td>
											</tr>
											<tr>
												<td>Edificabilidad sobre rasante planta primera actual</td>
												<td><input name="PR<%=pr.getIdParcelaResultante()%>:esrpp_a" type="number" value="<%=pr.getEsrppA()%>"></td>
											</tr>
											<tr>
												<td>Edificabilidad sobre rasante planta primera nuevo</td>
												<td><input name="PR<%=pr.getIdParcelaResultante()%>:esrpp_n" type="number" value="<%=pr.getEsrppN()%>"></td>
											</tr>
										</table>
									<%	
									}
									%>
								
								
								</div>
								
								
														
	
							<%}%>
						
							<input type="hidden" name="phase" value="5">
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