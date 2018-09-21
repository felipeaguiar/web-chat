package com.felipeaguiarfullstack.webchat.web.service;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.felipeaguiarfullstack.webchat.web.model.ChatMessage;
import com.felipeaguiarfullstack.webchat.web.model.Palavra;
import com.felipeaguiarfullstack.webchat.web.repository.PalavraRepository;

@Service
public class ChatService {
	
	@Autowired
	private PalavraRepository palavraRepository;
	
	private List<Palavra> palavras;
	
	@PostConstruct
	public void onCreate() {
		palavras = palavraRepository.findAll();
	}
	
	public void verificarPalavraRestrita(ChatMessage chatMessage) {
		palavras.forEach((p) -> {
			String s = chatMessage.getContent().replaceAll("(?i)" + p.getValor(), "****");
			chatMessage.setContent(s);
		});
	}

}
