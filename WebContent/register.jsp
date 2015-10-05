<%@page import="controller.Configuracion"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<link rel="stylesheet" href="<%=Configuracion.getInstance().getRoot()%>css/style.css" type="text/css">
		<title>Default</title>
		
	</head>
	<body>
		
		<jsp:include page="/common/header.jsp" />
		<div class="marginNavbar"></div>
		<div class = "contentWrapper white">
			<div id="body">
			
				<div class="registerWrapper">
		        
		        	<form method="POST" action="<%=Configuracion.getInstance().getRoot()%>Register" >
		        		<input class="field" type="text" name="user" placeholder="User">
		        		<input class="field" type="password" name="pass" placeholder="Password">
		        		<input class="field" type="password" name="pass2" placeholder="Repeat password">
		        		<input class="field" type="text" name="email" placeholder="Email">
		        		<input class="field" type="text" name="email2" placeholder="Repeat email">
		        		<div class="termsWrapper">
			        		<label><input type="checkbox" name="termsandconditions" >Acepto los <a href="#">términos y condiciones</a>.</label> 
		        		</div>
		        		<input class="button blue" type="submit" value="Register">
		        	</form>
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