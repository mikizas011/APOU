<%@tag description="Overall Page template" pageEncoding="UTF-8"%>
<%@attribute name="header" fragment="true" %>
<%@attribute name="footer" fragment="true" %>
<html>

	<head>
		<link rel="stylesheet" href="css/base.css" type="text/css">
	</head>
	<body>
		<div class="navbarWrapper">
			<div class="navbar">
				<div class="navbarHeader">
					<a id="logo" class="left" href="#">Asplur</a>
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
							<li><a class="button">Login</a></li>
							<li><a class="button">Registrate</a></li>
						</ul>
					</div>
					<div class="clear"></div>
				</div>
			</div>
		</div>
		<div class="marginNavbar"></div>
		<div class = "contentWrapper white">

		  	<div id="body">
		    	<jsp:doBody/>
		  	</div>
		  	<div id="footer">
		
			</div>
		</div>
 </body>
</html>