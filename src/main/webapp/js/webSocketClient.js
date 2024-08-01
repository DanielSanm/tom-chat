let socket
const ballonContainer = document.querySelector("#balloon-container")

function connect() {
	socket = new WebSocket("ws://10.100.101.12:8080/tomchat/websocket")

	socket.onmessage = (event) => {
		const message = JSON.parse(event.data.replace(/^"|"$/g, '').replace(/\\"/g, '"'))
		switch (message.type) {
			case 'message':
				displayMessage(message.content)
				break;
			case 'history':
				clearMessages()
				for (const text of message.content) {
					displayMessage(text)
				}
				break;
			case 'client-count':
				document.querySelector('#user-count').textContent = message.content
				break;
			default:
				break;
		}
	}

	socket.onopen = () => {
		console.log("client connected")
	}

	socket.onclose = () => {
		console.log("client disconnected")
	}

	socket.onerror = () => {
		console.error("client error")
	}	
}


function displayMessage(message) {
	const balloon = document.createElement("div")
	balloon.classList.add("balloon")
	balloon.innerText = message
	ballonContainer.appendChild(balloon)
	ballonContainer.scrollTop = ballonContainer.scrollHeight
}

function clearMessages() {
	ballonContainer.innerHTML = ""
}

function clock() {
	setInterval(() => {
		const date = new Date()
		document.querySelector("header #date").textContent = `${date.toLocaleDateString()} ${date.toLocaleTimeString()}`
	}, 1000)
}

clock()

document.querySelector("#text-box").addEventListener("keydown", (e) => {
	if (e.key === "Enter") {
		const message = e.target.value
		
		if (message !== "") socket.send(message)

		e.target.value = ""
	}
})

window.onload = connect
