<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Sign In - Tom Chat</title>
</head>
<body>
	<form method="post" action="signin">
		<input type="text" name="nickname" placeholder="Nickname"> <input
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