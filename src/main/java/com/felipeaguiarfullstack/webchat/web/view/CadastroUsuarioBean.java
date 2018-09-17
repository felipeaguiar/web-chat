package com.felipeaguiarfullstack.webchat.web.view;

import java.io.Serializable;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;

import com.felipeaguiarfullstack.webchat.web.model.Usuario;
import com.felipeaguiarfullstack.webchat.web.repository.UsuarioRepository;

@Named
public class CadastroUsuarioBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	private Usuario usuario = new Usuario();
	
	public String cadastrar() {
		usuarioRepository.save(usuario);
		usuario = new Usuario();
		return "login?faces-redirect=true";
		
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
