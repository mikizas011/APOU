<%@page import="java.util.Map.Entry"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.Iterator"%>
<%@page import="controller.wizard.classes.Phase1"%>
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
		<title>Phase 1</title>
		<script src="<%=Configuracion.getInstance().getRoot()%>js/jquery-1.11.1.js"></script>
		<script type="text/javascript">
		    $(document).ready(function() {
		        $("#add").click(function() {
		          $('#ues tr:last').clone(true).insertAfter('#ues tr:last');
		          var ueNumber = document.getElementById("ues").rows.length -1;
		          $('#ues tr:last').replaceWith('<tr><td><button type="button" class="erase" id="bUE'+ueNumber+'">borrar</button></td><td>UE'+ueNumber+'</td><td><input name="UE'+ueNumber+'" type="number" ></td></tr>');
		          return false;
		        });
		        $(".erase").click(function() {
		        	$(this).parent().parent().remove();
			    });
		        
		        
		        
		        
		    });
		</script>
		
		
	</head>
	<body>
		
		<jsp:include page="/common/header.jsp" />
		<jsp:include page="/common/userHeader.jsp" />
		<div class="marginNavbarUser"></div>
		<div class = "contentWrapper white">
			<div id="body">
				
				<div class="phase">
					<div class="phases">
						<ul>
							<li class="actual" title="Datos generales"><a href="#">Fase 1</a></li>
							<li class="closed" title="Parcelas aportadas"><a href="">Fase 2</a></li>
							<li class="closed" title="Ordenaci�n urban�stica estructural"><a href="#">Fase 3</a></li>
							<li class="closed" title="Ordenaci�n urban�stica pormenorizada p.1."><a href="#">Fase 4</a></li>
							<li class="closed" title="Ordenaci�n urban�stica pormenorizada p.2."><a href="#">Fase 5</a></li>
							<li class="closed" title="C�lculo de la edificabilidad urban�stica ponderada"><a href="#">Fase 6</a></li>
							<li class="closed" title="Edificabilidad urban�stica correspondiente al ayuntamiento"><a href="#">Fase 7</a></li>
							<li class="closed" title="Edificios fuera de ordenaci�n"><a href="#">Fase 8</a></li>
							<li class="closed" title="Red de sistemas locales"><a href="#">Fase 9</a></li>
							<li class="closed" title="Selecci�n de leyes incluidas"><a href="#">Fase 10</a></li>
							<li class="closed" title="Introducci�n"><a href="#">Fase 11</a></li>
							<li class="closed" title="An�lisis urban�stico del A.I.U. 21"><a href="#">Fase 12</a></li>
							<li class="closed" title="Determinaciones de car�cter estructural para el �mbito"><a href="#">Fase 13</a></li>
							<li class="closed" title="Justificaci�n de la conveniencia de la propuesta"><a href="#">Fase 14</a></li>
							<li class="closed" title="Tramitaci�n"><a href="#">Fase 15</a></li>
							<li class="closed" title="Criterios y objetivos del plan"><a href="#">Fase 16</a></li>
							<li class="closed" title="Descripci�n de la ordenaci�n propuesta"><a href="#">Fase 17</a></li>
							<li class="closed" title="Justificaci�n y cumplimiento del planeamiento general y la legislaci�n urban�stica"><a href="#">Fase 18</a></li>
							<li class="closed" title="Justificaci�n del cumplimiento de las determinaciones de la ordenaci�n territorial"><a href="#">Fase 19</a></li>
							<li class="closed" title="Gesti�n y ejecuci�n del plan parcial"><a href="#">Fase 20</a></li>
						</ul>
					</div>
					<div class="content">
						<% ArrayList<Municipio> municipios = (ArrayList<Municipio>)request.getAttribute("municipios"); %>
						<% Phase1 p = (Phase1)request.getAttribute("phase1");%>
						
						
						<h1>DATOS GENERALES</h1>
						<h2>DEL PLAN PARCIAL</h2>
						<form method="POST" action="<%=Configuracion.getInstance().getRoot()%>user_area/check_phase" >
							<p>Denominaci�n del plan parcial</p>
							<input type="text" name="denominacion_plan" value="<%=p.getDenominacionPlan() %>">
							<p>Denominaci�n del sector (n�mero sector)</p>
							<input type="text" name="denominacion_sector" value="<%=p.getDenominacionSector() %>">
							<input type="number" name="numero_sector" value="<%=p.getNumeroSector() %>">
							<p>Municipio</p>
							<select name="municipio">
							  	<%for(int i = 0; i < municipios.size(); i++){%>
							  		<option value="<%=municipios.get(i).getMunicipio()%>" <%
							  		if(p.getMunicipio() != null){
							  			if(p.getMunicipio().equals(municipios.get(i).getMunicipio())) {
							  				%>
							  				selected
							  				<%
							  			}
							  		}
							  		%>><%=municipios.get(i).getMunicipio() %></option>
							  	<%} %>
				  			</select>
				  			<p>Superficie del �mbito</p>
							<input type="number" name="superficie" value="<%=p.getSuperficie() %>">
				  			<p>Idioma</p>
							<select name="idioma">
								
								<%
								if(p.getIdioma() != null){
									if(p.getIdioma().equals("Castellano")){ %>
									  	<option value="Castellano" selected>Castellano</option>
									  	<option value="Euskara">Euskara</option>
							  		<%} else{ %>
									  	<option value="Castellano">Castellano</option>
									  	<option value="Euskara" selected>Euskara</option>
							  		<%} %>
								<%} else{%>
									<option value="Castellano">Castellano</option>
									<option value="Euskara">Euskara</option>
								<%} %>
				  			</select>
				  			
				  			<h2>UNIDADES DE EJECUCI�N</h2>
				  			
				  			<table id="ues">
				  				<tr>
				  					<th></th>
				  					<th>Nombre</th>
				  					<th>N�mero de parcelas</th>
				  				</tr>
				  				
				  				
				  				<%if(p.getUes().size() == 0){%>
				  					<tr>
				  						<td><button type="button" class="erase" id="bUE1">borrar</button></td>
					  					<td>UE1</td>
						  				<td><input name="UE1" type="number" ></td>
					  				</tr>
				  				<%} else{	
				  					
					  					for (Entry<String, String> e : p.getUes().entrySet()) {%>
	
											<tr>
						  						<td><button type="button" class="erase" id="b<%=e.getKey()%>">borrar</button></td>
						  						<td><%=e.getKey() %></td>
							  					<td><input name="<%=e.getKey()%>" type="number" value="<%=e.getValue()%>" ></td>
						  					</tr>	
					  						
					  					<%}
				  								  				
				  					}%>
				  				
				  				
				  			</table>
				  			<button type="button" id="add">A�adir UE</button>
				  			
				  			<input type="hidden" name="phase" value="1">
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
