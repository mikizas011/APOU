<%
  if (request.getParameter("logoff") != null) {
    session.invalidate();
    response.sendRedirect("index.jsp");
    return;
  }
%>
<html>
<head>
<title>Protected Page for Examples</title>
</head>
<body bgcolor="white">

You are logged in as remote user <b><%= request.getRemoteUser() %></b>
in session <b><%= session.getId() %></b><br><br>

<%
  if (request.getUserPrincipal() != null) {
%>
    Your user principal name is
    <b><%= request.getUserPrincipal().getName() %></b><br><br>
<%
  } else {
%>
    No user principal could be identified.<br><br>
<%
  }
%>

<%
  String role = request.getParameter("role");
  if (role == null)
    role = "";
  if (role.length() > 0) {
    if (request.isUserInRole(role)) {
%>
      You have been granted role <b><%= role %></b><br><br>
<%
    } else {
%>
      You have <i>not</i> been granted role <b><%= role %></b><br><br>
<%
    }
  }
%>

To check whether your username has been granted a particular role,
enter it here:
<form method="GET" action='<%= response.encodeURL("index.jsp") %>'>
<input type="text" name="role" value="<%= role %>">
</form>
<br><br>

If you have configured this app for form-based authentication, you can log
off by clicking
<a href="Logout">logout</a>
<a href='<%= response.encodeURL("/index.jsp?logoff=true") %>'>here</a>.
This should cause you to be returned to the logon page after the redirect
that is performed.
	<form method="POST" action="Logout">
        
        <input type="submit"/>
        
    </form>
<form method="POST" action="Register" >
  <table border="0" cellspacing="5">
    <tr>
      <th align="right">Username:</th>
      <td align="left"><input type="text" name="user"></td>
    </tr>
    <tr>
      <th align="right">Password:</th>
      <td align="left"><input type="password" name="pass"></td>
    </tr>
    <tr>
      <td align="right"><input type="submit" value="Register"></td>
      <td align="left"><input type="reset"></td>
    </tr>
  </table>
</form>

</body>
</html>
