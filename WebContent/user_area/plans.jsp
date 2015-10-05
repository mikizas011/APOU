<%@page import="controller.wizard.classes.Plan"%>
<%@page import="java.util.ArrayList"%>
<%@page import="controller.Configuracion"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<link rel="stylesheet" href="<%=Configuracion.getInstance().getRoot()%>css/style.css" type="text/css">
		<title>Welcome</title>
		<script>
			function transferCallToServlet(id)
			{
				document.requestForm.action = "<%=Configuracion.getInstance().getRoot() %>user_area/load_plan";
				document.requestForm.id.value=id;
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
			    	
			    <h1>PLANES DE ORDENACIÓN INDUSTRIAL PARCIAL</h1>
			    
			   
			    
			    <form name="requestForm" method="POST">
			    
				    <input type="hidden" name="id" >
				    
				    <% ArrayList<Plan> planes = (ArrayList<Plan>)request.getSession().getAttribute("planes"); 
				    
				    if(planes.size() == 0){%>
				    	
				    	<p>No existen planes contratados.</p>
				    	
				    <%}
				    
			    	for(int i = 0; i < planes.size(); i++){%>
			    		<div class="planWrapper" onclick="transferCallToServlet(<%=planes.get(i).getIdPlan() %>)">
			    			<h2><%=planes.get(i).getDenominacion().toUpperCase() %></h2>
			    			<p><%=planes.get(i).getNombre_sector() %> (#<%=planes.get(i).getNumero_sector() %>)</p>
							<table>
							  <tr>
							    <td><p><%=planes.get(i).getMunicipio() %></p></td>
							  </tr>
							  <tr>
							    <td><p>Fase #<%=planes.get(i).getFase() %></p></td>
							    <td><p>Creación: <%=planes.get(i).getFechaCreacion() %></p></td>
							  </tr>
							  <tr>
							    <td><p><%=planes.get(i).getIdioma() %></p></td>
							    <td><p>Última modificación: <%=planes.get(i).getFechaUltimaModificacion() %></p></td>
							  </tr>
							</table>
			    		</div>
			    	<%}%>
			    
			    </form>
			     
			    	
			    	
			    	
		  	</div>
		</div>
		<jsp:include page="/common/footer.jsp" />
	
	</body>
</html>
