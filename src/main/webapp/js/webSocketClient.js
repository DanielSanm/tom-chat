let socket;
const ballonContainer = document.querySelector("#balloon-container");

function connect() {
	socket = new WebSocket("ws://10.100.101.12:8080/tomchat/websocket");

	socket.onmessage = (event) => {
		clearMessages();
		const messages = JSON.parse(event.data);
		for (const message of messages) {
			displayMessage(message);
		}
	};

	socket.onopen = () => {
		console.log("client connected");
	};

	socket.onclose = () => {
		console.log("client disconnected");
	};

	socket.onerror = () => {
		console.error("client error");
	};
}

function displayMessage(message) {
	const balloon = document.createElement("div");
	balloon.classList.add("balloon");
	balloon.innerText = message;
	ballonContainer.appendChild(balloon);
	ballonContainer.scrollTop = ballonContainer.scrollHeight;
}

function clearMessages() {
	ballonContainer.innerHTML = "";
}

document.querySelector("#text-box").addEventListener("keydown", (e) => {
	if (e.key === "Enter") {
		const message = e.target.value;

		if (message !== "") socket.send(message);

		e.target.value = "";
	}
});

window.onload = connect;
