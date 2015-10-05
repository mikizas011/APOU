<%@page import="controller.Configuracion"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<link rel="stylesheet" href="<%=Configuracion.getInstance().getRoot()%>css/style.css" type="text/css">
		<title>Login</title>
		
	</head>
	<body>
		
		<jsp:include page="/common/header.jsp" />
		<div class="marginNavbar"></div>
		<div class = "contentWrapper white">
			<div id="body">
			    
		        <div class="loginWrapper">
		        
		        	<form method="POST" action="<%=Configuracion.getInstance().getRoot()%>j_security_check" >
		        		<input class="field" type="text" name="j_username" placeholder="User">
		        		<input class="field" type="password" name="j_password" placeholder="Password">
		        		<input class="button blue" type="submit" value="Login">
		        	</form>
		        	
		        	<div class="message">
						<p><a href="#">¿Olvidaste la contraseña?</a> o <a href="<%=Configuracion.getInstance().getRoot()%>register.jsp">registrate aquí</a>.</p>
					</div>
 		        	<%if(((String)request.getAttribute("javax.servlet.forward.request_uri")).indexOf("j_security_check") != -1){%>
	 		        	<div class="errormsg">
							<p class="errorfont">La contraseña o la dirección de correo electrónico no son correctas. ¡Inténtalo de nuevo!</p>
						</div>
 		        	<%}%>
 		        	<%if(request.getSession().getAttribute("mensaje") != null){%>
			        	<div class="errormsg">
							<p class="errorfont"><%= request.getSession().getAttribute("mensaje") %></p>
						</div>
		        	<%
		        		request.getSession().setAttribute("mensaje", null);
 		        	}%>
					
					
		        </div>
		        			    	
		  	</div>
		</div>
		<jsp:include page="/common/footer.jsp" />
	
	</body>
</html>