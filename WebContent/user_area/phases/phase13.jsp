
<%@page import="controller.wizard.classes.phases.PhaseTexto"%>
<%@page import="controller.wizard.classes.phases.PhaseTexto"%>
<%@page import="controller.wizard.classes.phases.Phase8"%>
<%@page import="controller.wizard.classes.phases.Phase7"%>
<%@page import="controller.wizard.classes.ParcelaResultante"%>
<%@page import="java.util.ArrayList"%>
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
		<title>Ordenación urbanística pormenorizada P.2.</title>
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
				
				<%	String estadoFase[] = (String[])request.getAttribute("estadoFase");
					PhaseTexto p = (PhaseTexto)request.getAttribute("phase13");
				%>
				
				<div class="phase">
					<form name="requestForm" method="POST">
					<input type="hidden" name="fase" >
					<input type="hidden" name="id" >
						<div class="phases">
							<ul>
								<li class="<%=estadoFase[0] %>" title="Datos generales"><a <%if(p.getFase() >= 1){ %>onclick="transferCallToServlet(1, <%=request.getSession().getAttribute("idPlan")%>)" <%} %>>Fase 1</a></li>
								<li class="<%=estadoFase[1] %>" title="Parcelas aportadas"><a <%if(p.getFase() >= 2){ %>onclick="transferCallToServlet(2, <%=request.getSession().getAttribute("idPlan")%>)"<%} %>>Fase 2</a></li>
								<li class="<%=estadoFase[2] %>" title="Ordenación urbanística estructural"><a <%if(p.getFase() >= 3){ %>onclick="transferCallToServlet(3, <%=request.getSession().getAttribute("idPlan")%>)"<%} %>>Fase 3</a></li>
								<li class="<%=estadoFase[3] %>" title="Ordenación urbanística pormenorizada p.1."><a <%if(p.getFase() >= 4){ %>onclick="transferCallToServlet(4, <%=request.getSession().getAttribute("idPlan")%>)"<%} %>>Fase 4</a></li>
								<li class="<%=estadoFase[4] %>" title="Ordenación urbanística pormenorizada p.2."><a <%if(p.getFase() >= 5){ %>onclick="transferCallToServlet(5, <%=request.getSession().getAttribute("idPlan")%>)"<%} %>>Fase 5</a></li>
								<li class="<%=estadoFase[5] %>" title="Cálculo de la edificabilidad urbanística ponderada"><a <%if(p.getFase() >= 6){ %>onclick="transferCallToServlet(6, <%=request.getSession().getAttribute("idPlan")%>)"<%} %>>Fase 6</a></li>
								<li class="<%=estadoFase[6] %>" title="Edificabilidad urbanística correspondiente al ayuntamiento"><a <%if(p.getFase() >= 7){ %>onclick="transferCallToServlet(7, <%=request.getSession().getAttribute("idPlan")%>)"<%} %>>Fase 7</a></li>
								<li class="<%=estadoFase[7] %>" title="Red de sistemas locales"><a <%if(p.getFase() >= 8){ %>onclick="transferCallToServlet(8, <%=request.getSession().getAttribute("idPlan")%>)"<%} %>>Fase 8</a></li>
								<li class="opened" title="Edificios fuera de ordenación"><a <%if(p.getFase() >= 9){ %>onclick="transferCallToServlet(9, <%=request.getSession().getAttribute("idPlan")%>)"<%} %>>Fase 9</a></li>
								<li class="opened" title="Selección de leyes incluidas"><a <%if(p.getFase() >= 9){ %>onclick="transferCallToServlet(10, <%=request.getSession().getAttribute("idPlan")%>)"<%} %>>Fase 10</a></li>
								<li class="opened" title="Introducción"><a <%if(p.getFase() >= 9){ %>onclick="transferCallToServlet(11, <%=request.getSession().getAttribute("idPlan")%>)"<%} %>>Fase 11</a></li>
								<li class="opened" title="Análisis urbanístico del A.I.U. 21"><a <%if(p.getFase() >= 9){ %>onclick="transferCallToServlet(12, <%=request.getSession().getAttribute("idPlan")%>)"<%} %>>Fase 12</a></li>
								<li class="actual" title="Determinaciones de carácter estructural para el ámbito"><a <%if(p.getFase() >= 9){ %>onclick="transferCallToServlet(13, <%=request.getSession().getAttribute("idPlan")%>)"<%} %>>Fase 13</a></li>
								<li class="opened" title="Justificación de la conveniencia de la propuesta"><a <%if(p.getFase() >= 9){ %>onclick="transferCallToServlet(14, <%=request.getSession().getAttribute("idPlan")%>)"<%} %>>Fase 14</a></li>
								<li class="opened" title="Tramitación"><a <%if(p.getFase() >= 9){ %>onclick="transferCallToServlet(15, <%=request.getSession().getAttribute("idPlan")%>)"<%} %>>Fase 15</a></li>
								<li class="opened" title="Criterios y objetivos del plan"><a <%if(p.getFase() >= 9){ %>onclick="transferCallToServlet(16, <%=request.getSession().getAttribute("idPlan")%>)"<%} %>>Fase 16</a></li>
								<li class="opened" title="Descripción de la ordenación propuesta"><a <%if(p.getFase() >= 9){ %>onclick="transferCallToServlet(17, <%=request.getSession().getAttribute("idPlan")%>)"<%} %>>Fase 17</a></li>
								<li class="opened" title="Justificación y cumplimiento del planeamiento general y la legislación urbanística"><a <%if(p.getFase() >= 9){ %>onclick="transferCallToServlet(18, <%=request.getSession().getAttribute("idPlan")%>)"<%} %>>Fase 18</a></li>
								<li class="opened" title="Justificación del cumplimiento de las determinaciones de la ordenación territorial"><a <%if(p.getFase() >= 9){ %>onclick="transferCallToServlet(19, <%=request.getSession().getAttribute("idPlan")%>)"<%} %>>Fase 19</a></li>
								<li class="opened" title="Gestión y ejecución del plan parcial"><a <%if(p.getFase() >= 9){ %>onclick="transferCallToServlet(20, <%=request.getSession().getAttribute("idPlan")%>)"<%} %>>Fase 20</a></li>
							</ul>
						</div>
					</form>
					<div class="content">
				
						
						<h1>Determinaciones de carácter estructural para el ámbito</h1>
				
						<form method="POST" action="<%=Configuracion.getInstance().getRoot()%>user_area/check_phase" >
						
							<textarea name="content"><%=p.getText() %></textarea>
						
							<input type="hidden" name="phase" value="<%=p.getFase()%>">
					  		<input class="button" type="submit" value="Continuar">
						
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
