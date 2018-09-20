package com.felipeaguiarfullstack.webchat.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.felipeaguiarfullstack.webchat.web.model.Usuario;
import com.felipeaguiarfullstack.webchat.web.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private EmailService emailService;
	
	public void cadastrar(Usuario usuario) {
		usuarioRepository.save(usuario);
	}

	public Usuario iniciarChat(String email, String enderecoIp) {
		Usuario usuario = usuarioRepository.findByEmail(email).get();
		
		if (usuario.getPrimeiroLogin()) {			
			emailService.enviar(email, enderecoIp);
			
			usuario.setPrimeiroLogin(false);
			usuarioRepository.save(usuario);
		}
		
		return usuario;
	}
}
