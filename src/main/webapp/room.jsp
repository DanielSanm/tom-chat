<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<html>
<link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;500;700&display=swap" rel="stylesheet">
<title>Room 1 - Tom Chat</title>
<style>

	* {
	    font-family: 'Roboto', sans-serif;
	    font-size: 16px;
	    line-height: 1.5;
	    color: #333;
	}
	
	#container {
		display: flex;
		flex-direction: column;
		align-items: center;
		justify-content: end;
		max-width: 50vw;
		margin: 0 auto;
		border: 2px solid gray;
		height: 75vh;
		padding: 2%;
		border-radius: 10px;
		
	}
	
	#text-box {
		width: 100%;
		padding: 6px;
		border-radius: 5px;
		margin-top: 10px;
	}
	
	#balloon-container {
		display: flex;
		width: 100%;
		flex-direction: column;
		align-self: flex-start;
		overflow: scroll;
    	overflow-x: hidden;
	}
	
	.balloon {
		background: #C8C8C8;
		padding: 20px;
		margin: 5px;
		border-radius: 10px;
		
		align-self: flex-start;
	}
</style>
<body>
	<div id="container">
		
		<div id="balloon-container">
			<div class="balloon">
				Hello world!
				Hello world!
				Hello world!
				v
				Hello world!
				v
				v
				Hello world!
				Hello world!
				Hello world!
				Hello world!
				Hello world!
				Hello world!
				
			</div>
			<div class="balloon">
				Hello world!
			</div>
			<div class="balloon">
				Hello world!
			</div>
			<div class="balloon">
				Hello world!
			</div>
			<div class="balloon">
				Hello world!
			</div>
			<div class="balloon">
				Hello world!
			</div>
			<div class="balloon">
				Hello world!
			</div>
			<div class="balloon">
				Hello world!
			</div>
			<div class="balloon">
				Hello world!
			</div>
			<div class="balloon">
				Hello world!
			</div>
		</div>
	
		
		<input placeholder="type something..." id="text-box" type="text" />
	</div>
</body>
</html>
