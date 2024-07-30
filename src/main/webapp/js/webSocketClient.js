let socket;
const ballonContainer = document.querySelector('#balloon-container')

function connect() {
	socket = new WebSocket("ws://10.100.101.12:8080/tomchat/websocket");

	socket.onmessage = (event) => {
		console.log(event.data);
	};

	socket.onopen = () => {

	};

	socket.onclose = () => {
		alert("client disconnected");
	};

	socket.onerror = () => {
		console.error("client error");
	};
}

function displayMessage(message) {
	const balloon = document.createElement('div')
	balloon.classList.add('balloon')
	balloon.innerText = message
	ballonContainer.appendChild(balloon)
	ballonContainer.scrollTop = ballonContainer.scrollHeight
}

document.querySelector("#text-box").addEventListener("keydown", (e) => {
	if (e.key === "Enter") {
		const message = e.target.value;

		if (message !== '') {
			socket.send(message);
			displayMessage(message)
			e.target.value = "";
		}

	}
});

window.onload = connect;
