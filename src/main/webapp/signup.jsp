<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Sign Up - Tom Chat</title>
<style>
* {
	margin: 0;
	padding: 0;
	box-sizing: border-box;
}

#container {
	display: flex;
	flex-direction: column;
	max-width: 50vw;
	height: 90vh;
	margin: 0 auto;
	
}
</style>
</head>
<body>
	<main id="container">
        <form method="post" action="signup">
		<input type="text" name="firstname" placeholder="First name">
		<input type="text" name="lastname" placeholder="Last name"> 
        <input type="text" name="nickname" placeholder="Nickname"> 
        <input type="date" name="birthdate" placeholder="Birth date"> 
        <input type="text" name="email" placeholder="Email"> 
        <input type="text" name="phone" placeholder="Phone"> 
        <input type="password" name="password" placeholder="Password">

		<button type="submit">Login</button>
	</form>
    </main>

</body>
</html>