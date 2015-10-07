<%@page import="java.util.Arrays"%>
<%@page import="com.mysql.fabric.xmlrpc.base.Array"%>
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
				
				<div class="phase">
					<form name="requestForm" method="POST">
					<input type="hidden" name="fase" >
					<input type="hidden" name="id" >
						<div class="phases">
							<ul>
								<li class="checked" title="Datos generales"><a onclick="transferCallToServlet(1, <%=request.getSession().getAttribute("idPlan")%>)">Fase 1</a></li>
								<li class="actual" title="Parcelas aportadas"><a href="#">Fase 2</a></li>
								<li class="opened" title="Ordenación urbanística estructural"><a href="#">Fase 3</a></li>
								<li class="closed" title="Ordenación urbanística pormenorizada p.1."><a href="#">Fase 4</a></li>
								<li class="closed" title="Ordenación urbanística pormenorizada p.2."><a href="#">Fase 5</a></li>
								<li class="closed" title="Cálculo de la edificabilidad urbanística ponderada"><a href="#">Fase 6</a></li>
								<li class="closed" title="Edificabilidad urbanística correspondiente al ayuntamiento"><a href="#">Fase 7</a></li>
								<li class="closed" title="Edificios fuera de ordenación"><a href="#">Fase 8</a></li>
								<li class="closed" title="Red de sistemas locales"><a href="#">Fase 9</a></li>
								<li class="closed" title="Selección de leyes incluidas"><a href="#">Fase 10</a></li>
								<li class="closed" title="Introducción"><a href="#">Fase 11</a></li>
								<li class="closed" title="Análisis urbanístico del A.I.U. 21"><a href="#">Fase 12</a></li>
								<li class="closed" title="Determinaciones de carácter estructural para el ámbito"><a href="#">Fase 13</a></li>
								<li class="closed" title="Justificación de la conveniencia de la propuesta"><a href="#">Fase 14</a></li>
								<li class="closed" title="Tramitación"><a href="#">Fase 15</a></li>
								<li class="closed" title="Criterios y objetivos del plan"><a href="#">Fase 16</a></li>
								<li class="closed" title="Descripción de la ordenación propuesta"><a href="#">Fase 17</a></li>
								<li class="closed" title="Justificación y cumplimiento del planeamiento general y la legislación urbanística"><a href="#">Fase 18</a></li>
								<li class="closed" title="Justificación del cumplimiento de las determinaciones de la ordenación territorial"><a href="#">Fase 19</a></li>
								<li class="closed" title="Gestión y ejecución del plan parcial"><a href="#">Fase 20</a></li>
							</ul>
						</div>
					</form>
					<div class="content">
						
						
		
					    
					</div>
					<div class="clear"></div>
				</div>
				
				
		
			    
			    
		  	</div>
		</div>
		<jsp:include page="/common/footer.jsp" />
	
	</body>
</html>
