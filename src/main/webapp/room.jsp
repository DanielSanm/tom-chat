<%@ page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<html>
<link
	href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;500;700&display=swap"
	rel="stylesheet">
<title>Room 1 - Tom Chat</title>
<style>
* {
	font-family: 'Roboto', sans-serif;
	font-size: 16px;
	line-height: 1.5;
	color: #333;
	list-style: none;
	text-decoration: none;
	box-sizing: border-box;
}

#container {
	display: flex;
	flex-direction: column;
	align-items: center;
	max-width: 50vw;
	margin: 0 auto;
	border: 2px solid #D3D3D3;
	height: 90vh;
	padding: 15px 25px 25px 25px;
	border-radius: 10px;
}

header {
	display: flex;
	width: 100%;
	flex-direction: row;
	justify-content: space-between;
	align-items: center;
	margin-bottom: 5px;
}

header #logo {
	flex: 1;
	text-align: left;
}

header #logo #tomcat-img {
	width: 3rem;
}

header #clients-counter {
	flex: 1;
	text-align: center;
}

header #date {
	flex: 1;
	text-align: right;
}

#balloon-container {
	display: flex;
	width: 100%;
	height: 100%;
	flex-direction: column;
	align-items: flex-start;
	overflow-y: auto;
	overflow-x: hidden;
	z-index: 2;
}

#balloon-container:before {
	content: '';
	width: 100%;
	height: 100%;
	position: absolute;
    top: 0;
    left: 0;
	opacity: 0.35;
	background-image: url('assets/apache_tomcat_logo.svg');
	background-repeat: no-repeat;
	background-position: center;
	background-size: 30%;
	z-index: 1;
}

#text-box {
	width: 100%;
	padding: 6px;
	border: 2px solid #D3D3D3;
	border-radius: 5px;
	margin-top: 10px;
	border-radius: 5px;
	z-index: 2;
}

.balloon {
	display: flex;
	flex-direction: column;
	background: #C8C8C8;
	padding: 12px;
	margin: 5px;
	border-radius: 10px;
	align-self: flex-start;
	z-index: 2;
}

.balloon #client-id {
	font-size: 0.75rem;
	
}

.balloon #text {
	font-weight: 500;
}

.balloon #datetime {
	font-size: 0.75rem;
	text-align: end;
}
</style>
<body>
	<main id="container">
		<header>
			<div id="logo">
				<img id="tomcat-img" src="assets/apache_tomcat_logo.svg" /> 
				<span><strong>Tom Chat</strong></span>
			</div>
			<div id="clients-counter">
				<span id="gif-light"><img src="assets/green_light.gif" width="15px" /></span>
				Online users: <span id="user-count">1</span>
			</div>
			<div id="date"></div>
		</header>

		<section id="balloon-container">
			<!-- <div class="balloon">
				<span id="client-id">Client 01</span>
				<span id="text">ola tudo bem!</span>
				<span id="datetime">02/08 11:50</span>
			</div> -->
		</section>

		<input placeholder="type something..." id="text-box" type="text" />
		<script type="text/javascript" src="js/libs/luxon.min.js"></script>
		<script type="text/javascript" src="js/webSocketClient.js"></script>
	</main>
</body>
</html>
