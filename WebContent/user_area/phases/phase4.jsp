<%@page import="controller.wizard.classes.UnidadEjecucion"%>
<%@page import="java.util.TreeSet"%>
<%@page import="java.util.SortedSet"%>
<%@page import="controller.wizard.classes.OrdenacionUrbanisticaPormenorizada"%>
<%@page import="java.util.Map.Entry"%>
<%@page import="controller.wizard.classes.phases.Phase4"%>
<%@page import="java.util.ArrayList"%>
<%@page import="controller.Configuracion"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<link rel="stylesheet" href="<%=Configuracion.getInstance().getRoot()%>css/style.css" type="text/css">
		<title>Ordenación urbanística pormenorizada P.1.</title>
		<script src="<%=Configuracion.getInstance().getRoot()%>js/jquery-1.11.1.js"></script>
		<script type="text/javascript">
		    $(document).ready(function() {

		    	var tipoNumber = document.getElementById("tipos").rows.length -1;

		        $("#add").click(function() {
		        tipoNumber = tipoNumber +1;
		          $('#tipos tr:last').clone(true).insertAfter('#tipos tr:last');
		          $('#tipos tr:last').replaceWith('<tr><td><button type="button" class="erase">borrar</button></td><td><input name="TIPO#'+tipoNumber+':t:nombre" type="text"></td><td><input step="any" name="TIPO#'+tipoNumber+':n:srpb"  type="number"></td><td><input step="any" name="TIPO#'+tipoNumber+':n:srpp"  type="number"></td><td><input step="any" name="TIPO#'+tipoNumber+':n:br"  type="number"></td></tr>');
		          return false;
		        });
		       
		        $(document).on("click", ".erase", function() {
		        	$(this).parent().parent().remove();
			    });
		        
		    });
		</script>
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
				<% Phase4 p = (Phase4)request.getAttribute("phase4");%>
				
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
								<li class="closed" title="Edificios fuera de ordenación"><a <%if(p.getFase() >= 9){ %>onclick="transferCallToServlet(9, <%=request.getSession().getAttribute("idPlan")%>)"<%} %>>Fase 9</a></li>
								<li class="closed" title="Selección de leyes incluidas"><a <%if(p.getFase() >= 10){ %>onclick="transferCallToServlet(10, <%=request.getSession().getAttribute("idPlan")%>)"<%} %>>Fase 10</a></li>
								<li class="closed" title="Introducción"><a <%if(p.getFase() >= 11){ %>onclick="transferCallToServlet(11, <%=request.getSession().getAttribute("idPlan")%>)"<%} %>>Fase 11</a></li>
								<li class="closed" title="Análisis urbanístico del A.I.U. 21"><a <%if(p.getFase() >= 12){ %>onclick="transferCallToServlet(12, <%=request.getSession().getAttribute("idPlan")%>)"<%} %>>Fase 12</a></li>
								<li class="closed" title="Determinaciones de carácter estructural para el ámbito"><a <%if(p.getFase() >= 13){ %>onclick="transferCallToServlet(13, <%=request.getSession().getAttribute("idPlan")%>)"<%} %>>Fase 13</a></li>
								<li class="closed" title="Justificación de la conveniencia de la propuesta"><a <%if(p.getFase() >= 14){ %>onclick="transferCallToServlet(14, <%=request.getSession().getAttribute("idPlan")%>)"<%} %>>Fase 14</a></li>
								<li class="closed" title="Tramitación"><a <%if(p.getFase() >= 15){ %>onclick="transferCallToServlet(15, <%=request.getSession().getAttribute("idPlan")%>)"<%} %>>Fase 15</a></li>
								<li class="closed" title="Criterios y objetivos del plan"><a <%if(p.getFase() >= 16){ %>onclick="transferCallToServlet(16, <%=request.getSession().getAttribute("idPlan")%>)"<%} %>>Fase 16</a></li>
								<li class="closed" title="Descripción de la ordenación propuesta"><a <%if(p.getFase() >= 17){ %>onclick="transferCallToServlet(17, <%=request.getSession().getAttribute("idPlan")%>)"<%} %>>Fase 17</a></li>
								<li class="closed" title="Justificación y cumplimiento del planeamiento general y la legislación urbanística"><a <%if(p.getFase() >= 18){ %>onclick="transferCallToServlet(18, <%=request.getSession().getAttribute("idPlan")%>)"<%} %>>Fase 18</a></li>
								<li class="closed" title="Justificación del cumplimiento de las determinaciones de la ordenación territorial"><a <%if(p.getFase() >= 19){ %>onclick="transferCallToServlet(19, <%=request.getSession().getAttribute("idPlan")%>)"<%} %>>Fase 19</a></li>
								<li class="closed" title="Gestión y ejecución del plan parcial"><a <%if(p.getFase() >= 20){ %>onclick="transferCallToServlet(20, <%=request.getSession().getAttribute("idPlan")%>)"<%} %>>Fase 20</a></li>
							</ul>
						</div>
					</form>
					<div class="content">
		
						<h1>Ordenación urbanística pormenorizada P.1.</h1>
						<h2>Tipos de ordenación urbanística pormenorizados definidos para el proyecto</h2>
		
						<form method="POST" action="<%=Configuracion.getInstance().getRoot()%>user_area/check_phase" >
		
								  <table id="tipos">
					  				<tr>
					  					<th></th>
					  					<th>Denominación</th>
					  					<th title="Coeficiente sobre rasante planta baja">Coeficiente SRPB</th>
					  					<th title="Coeficiente sobre rasante planta primera">Coeficiente SRP1</th>
					  					<th title="Coeficiente bajo rasante">Coeficiente BR</th>
					  				</tr>
					  				
					  				
					  				<%if(p.getInsert().size() + p.getUpdate().size() > 0){
					  					
					  					for(Entry<Integer, OrdenacionUrbanisticaPormenorizada> entry : p.getUpdate().entrySet()){%>
					  						<tr>
						  						<td><button type="button" class="erase" >borrar</button></td>
						  						<td><input step="any"  name="TIPO<%=entry.getValue().getIdOrdenacionPormenorizada()%>:t:nombre" type="text" value="<%=entry.getValue().getDenominacion()%>"></td>
							  					<td><input step="any"  name="TIPO<%=entry.getValue().getIdOrdenacionPormenorizada()%>:n:srpb"  type="number" value="<%=entry.getValue().getCoefSRPB()%>" ></td>
							  					<td><input step="any"  name="TIPO<%=entry.getValue().getIdOrdenacionPormenorizada()%>:n:srpp"  type="number" value="<%=entry.getValue().getCoefSRPP()%>" ></td>
							  					<td><input step="any"  name="TIPO<%=entry.getValue().getIdOrdenacionPormenorizada()%>:n:br"  type="number" value="<%=entry.getValue().getCoefBR()%>" ></td>
					  						</tr>	
					  					<%}
					  					int num = 0;
					  					for(Entry<String, OrdenacionUrbanisticaPormenorizada> entry : p.getInsert().entrySet()){
					  					num++;%>
					  						<tr>
						  						<td><button type="button" class="erase" >borrar</button></td>
						  						<td><input step="any"  name="TIPO#<%=num%>:t:nombre" type="text" value="<%=entry.getValue().getDenominacion()%>"></td>
							  					<td><input step="any"  name="TIPO#<%=num%>:n:srpb"  type="number" value="<%=entry.getValue().getCoefSRPB()%>" ></td>
							  					<td><input step="any"  name="TIPO#<%=num%>:n:srpp"  type="number" value="<%=entry.getValue().getCoefSRPP()%>" ></td>
							  					<td><input step="any"  name="TIPO#<%=num%>:n:br"  type="number" value="<%=entry.getValue().getCoefBR()%>" ></td>
					  						</tr>	
				  						<%}
					  					
					  				}
					  				else{
					  					
					  					if(p.getTipos().size() == 0){%>
					  					<tr>
					  						<td><button type="button" class="erase">borrar</button></td>
						  					<td><input step="any"  name="TIPO#:t:nombre" type="text"></td>
						  					<td><input step="any"  name="TIPO#:n:srpb"  type="number"></td>
						  					<td><input step="any"  name="TIPO#:n:srpp"  type="number"></td>
						  					<td><input step="any"  name="TIPO#:n:br"  type="number"></td>
						  				</tr>
					  				<%} else{	
					  					
					  						for(int i = 0; i < p.getTipos().size(); i++){%>
					  							<tr>
							  						<td><button type="button" class="erase" >borrar</button></td>
							  						<td><input step="any"  name="TIPO<%=p.getTipos().get(i).getIdOrdenacionPormenorizada()%>:t:nombre" type="text" value="<%=p.getTipos().get(i).getDenominacion()%>"></td>
								  					<td><input step="any"  name="TIPO<%=p.getTipos().get(i).getIdOrdenacionPormenorizada()%>:n:srpb"  type="number" value="<%=p.getTipos().get(i).getCoefSRPB()%>" ></td>
								  					<td><input step="any"  name="TIPO<%=p.getTipos().get(i).getIdOrdenacionPormenorizada()%>:n:srpp"  type="number" value="<%=p.getTipos().get(i).getCoefSRPP()%>" ></td>
								  					<td><input step="any"  name="TIPO<%=p.getTipos().get(i).getIdOrdenacionPormenorizada()%>:n:br"  type="number" value="<%=p.getTipos().get(i).getCoefBR()%>" ></td>
						  						</tr>	
					  						<%}
			  				
					  					}
					  				}
					  				
					  				%>
	
					  			</table>
					  			<button type="button" id="add">Añadir tipo</button>
					  			
					  			
					  			<h2>Parcelas resultantes</h2>
					  			
					  			<table id="ues">
					  				<tr>
					  					<th>UE</th>
					  					<th>Número de parcelas resultantes</th>
					  				</tr>
					  			<%
									SortedSet<String> keys = new TreeSet<String>(p.getUes().keySet());
					  				for(String key : keys){
					  					UnidadEjecucion ue = p.getUes().get(key);%>
					  					<tr>
					  						<td><%=ue.getDenominacion() %></td>
					  						<td><input name="<%=ue.getDenominacion() %>" type="number" value="<%=ue.getNumeroParcelasResultantes()%>"></td>
					  					</tr>
					  					
					  				
					  				<%}
					  			 %>
					  			 </table>
					  			 
					  			<input type="hidden" name="phase" value="4">
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
