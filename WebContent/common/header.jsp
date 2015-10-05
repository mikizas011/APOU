<%@page import="controller.Configuracion"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<link rel="stylesheet" href="<%=Configuracion.getInstance().getRoot()%>css/base.css" type="text/css">
	</head>
	<body>
	
			<div class="navbarWrapper">
				<div class="navbar">
					<div class="navbarHeader">
						<a id="logo" class="left" href="/APOU/index.jsp">Asplur</a>
					</div>
					<div class="slidemenu">
						<div class="menu">
							<ul>
								<li><a href="#">Inicio</a></li>
								<li><a href="#">Qué es Asplur</a></li>
								<li><a href="#">Servicios</a></li>
								<li><a href="#">FAQs</a></li>
							</ul>
						</div>
						
						<div class="userbox">
							<ul>
								<% if(request.getUserPrincipal() == null){%>
									<li><a class="button" href="<%=Configuracion.getInstance().getRoot()%>user_area/index.jsp">Login</a></li>
									<li><a class="button" href="<%=Configuracion.getInstance().getRoot()%>register.jsp">Regístrate</a></li>								
								<%} else{ %>
									<li><a class="button" href="<%=Configuracion.getInstance().getRoot()%>Logout">Logout</a></li>
									<li><a class="button" href="<%=Configuracion.getInstance().getRoot()%>user_area/index.jsp">Panel de <%=request.getUserPrincipal().getName() %></a></li>
								<%}%>
							</ul>
						</div>
						
						<div class="language">
							<p>
								<a href="#">eu</a>
								|
								<a href="#">es</a>
							</p>
						</div>
						
						<div class="clear"></div>
					</div>
				</div>
			</div>

	</body>
</html>