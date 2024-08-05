<%@ page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<html>
<link
	href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;500;700&display=swap"
	rel="stylesheet">
<link rel="stylesheet" type="text/css" href="css/style.css">
<title>Room 1 - Tom Chat</title>
<body>
	<main id="container">
		<header>
			<div id="logo">
				<img id="tomcat-img" src="assets/apache_tomcat_logo.svg" /> <span><strong>Tom
						Chat</strong></span>
			</div>
			<div id="clients-counter">
				<span id="gif-light"><img src="assets/green_light.gif"
					width="15px" /></span> Online users: <span id="user-count">0</span>
			</div>
			<div id="date"></div>
		</header>

		<section id="balloon-container"></section>

		<input placeholder="type something..." id="text-box" type="text" />
		<script type="text/javascript" src="js/webSocketClient.js"></script>
	</main>
</body>
</html>
