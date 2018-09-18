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

	public void login(String email, String cpf, String enderecoIp) {
		boolean primeiroLogin = true;
		
		if (primeiroLogin) {			
			emailService.enviar(email, enderecoIp);
		}
	}
	
	public void logout() {
		// TODO
	}
	
}
