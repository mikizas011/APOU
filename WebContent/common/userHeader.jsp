<%@page import="controller.Configuracion"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<link rel="stylesheet" href="<%=Configuracion.getInstance().getRoot()%>css/base.css" type="text/css">
		<script src="<%=Configuracion.getInstance().getRoot()%>js/jquery-1.11.1.js"></script>
		<script>
			$(document).ready(function(){
			    $(".userNavbarWrapper").slideDown('slow');
			});
		</script>
	</head>
	<body>
	
			<div class="userNavbarWrapper">
				<div class="userNavbar">
					
					<div class="slidemenu">
						<div class="menu">
							<ul>
								<li><a href="<%=Configuracion.getInstance().getRoot()%>user_area/index.jsp">Panel de usuario</a></li>
								<li><a href="<%=Configuracion.getInstance().getRoot()%>user_area/load_plans">Planes</a></li>
								<li><a href="<%=Configuracion.getInstance().getRoot()%>user_area/hire_plan_0.jsp">Contratar plan</a></li>
								<li><a href="#">Comprar licencia</a></li>
								<li><a href="#">Configuración</a></li>
							</ul>
						</div>
						<div class="clear"></div>
					</div>
				</div>
			</div>

	</body>
</html>