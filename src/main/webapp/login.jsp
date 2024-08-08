<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login - Tom Chat</title>
</head>
<body>
	<form method="post" action="tomchat/LoginServlet">
		<input type="text" name="username" placeholder="Username"> <input
			type="password" name="password" placeholder="Password">
		<button type="submit">Login</button>
	</form>
	
	<%
		String errorMessage = (String) request.getAttribute("errorMessage");
		if (errorMessage != null) {
	%>
			<p style="color: red;" id="error-message"><%=errorMessage%></p>
	<%
		}
	%>
</body>
</html>