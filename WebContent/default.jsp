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
			    	
		  	</div>
		</div>
		<jsp:include page="/common/footer.jsp" />
	
	</body>
</html>