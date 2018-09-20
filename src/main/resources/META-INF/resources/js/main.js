'use strict';

var campoMensagem;
var chat;

var stompClient;
var username;

function enviarMensagem(event) {
    var messageContent = campoMensagem.value.trim();

    if(messageContent && stompClient) {

        var chatMessage = {
            sender: username,
            content: campoMensagem.value,
            type: 'CHAT'
        };

        stompClient.send("/app/chat.sendMessage", {}, JSON.stringify(chatMessage));
        campoMensagem.value = '';
    }
    event.preventDefault();
}


function onMessageReceived(payload) {
    var message = JSON.parse(payload.body);

    if(message.type === 'JOIN') {
        return;
    }

    if (message.type === 'LEAVE') {
        return;
    }

    var divMessage = document.createElement('div');

    var span = document.createElement('span');
    var usuarioText = document.createTextNode(message.sender + ': ');
    span.appendChild(usuarioText);

    var messageText = document.createTextNode(message.content);
    
    divMessage.appendChild(span);
    divMessage.appendChild(messageText);

    chat.appendChild(divMessage);
    chat.scrollTop = chat.scrollHeight;
}

function onConnected() {
    stompClient.subscribe('/topic/public', onMessageReceived);

    stompClient.send("/app/chat.addUser",
        {},
        JSON.stringify({sender: username, type: 'JOIN'})
    )
}

function onError(error) {
    alert('Erro ao conectar com o servidor');
}

window.onload = function() {
    campoMensagem = document.getElementById('campo-mensagem');
    chat = document.getElementById('chat');

    var botaoEnviar = document.getElementById('botao-enviar');
    botaoEnviar.addEventListener('click', enviarMensagem, true)

    username = document.getElementById('usuario').value.trim();

    if(username) {
        var socket = new SockJS('/ws');
        stompClient = Stomp.over(socket);

        stompClient.connect({}, onConnected, onError);
    }
}
