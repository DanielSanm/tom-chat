let socket

async function connect() {

	const serverIp = await fetch('/tomchat/env')
		.then(response => response.json())
		.then(data => data.serverIp)
		.catch(error => {
			console.error('Error: ' + error)
			return 'localhost'
		})

	socket = new WebSocket(`ws://${serverIp}:8080/tomchat/websocket`)

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

const { DateTime } = luxon
const ballonContainer = document.querySelector("#balloon-container")
let balloon

function displayMessage(message) {

	balloon = document.createElement("div")
	balloon.classList.add("balloon")

	appendBalloonItem("client-id", `Guest ${message.clientId}`)
	appendBalloonItem("text", message.text)

	const timeZoneMatch = message.datetime.match(/\[(.*?)\]$/);
	const timeZone = timeZoneMatch ? timeZoneMatch[1] : 'UTC';
	// Removendo a parte do fuso horário para parsear a string ISO
	const isoString = message.datetime.replace(/\[.*?\]$/, '');
	// Parse the ZonedDateTime string using Luxon
	const zonedDateTime = DateTime.fromISO(isoString, { zone: timeZone }).toFormat('dd/MM HH:mm');
	
	appendBalloonItem("datetime", zonedDateTime)

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
