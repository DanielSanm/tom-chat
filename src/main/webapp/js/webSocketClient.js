let socket

async function connect() {

	const serverIp = await fetch('/tomchat/env')
		.then(response => response.json())
		.then(data => data.serverIp)
		.catch(error => {
			console.error(`Error: ' ${error}`)
			return 'localhost'
		})

	socket = new WebSocket(`ws://${serverIp}:8080/tomchat/websocket`)

	socket.onmessage = (event) => {
		const message = JSON.parse(event.data.replace(/^"|"$/g, '').replace(/\\"/g, '"'))
		switch (message.type) {
			case 'message-data':
				displayMessage(message)
				break;
			case 'history-list':
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

const ballonContainer = document.querySelector("#balloon-container")
let balloon

function formatISODate(isoString) {
    const date = new Date(isoString);

    const day = String(date.getDate()).padStart(2, '0');
    const month = String(date.getMonth() + 1).padStart(2, '0'); // January is 0!
    const hours = String(date.getHours()).padStart(2, '0');
    const minutes = String(date.getMinutes()).padStart(2, '0');

    return `${day}/${month} ${hours}:${minutes}`;
}

function displayMessage(message) {

	balloon = document.createElement("div")
	balloon.classList.add("balloon")

	appendBalloonItem("client-id", `Guest ${message.connectionId}`)
	appendBalloonItem("text", message.content)
	appendBalloonItem("datetime", formatISODate(message.dateTime))
	
	ballonContainer.appendChild(balloon)
	ballonContainer.scrollTop = ballonContainer.scrollHeight
}

function appendBalloonItem(id, text) {
	const span = document.createElement("span")
	span.setAttribute("id", id)
	span.innerText = text
	balloon.appendChild(span)
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
