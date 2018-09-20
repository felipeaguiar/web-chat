package com.felipeaguiarfullstack.webchat.web.view;

import java.io.Serializable;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;

import com.felipeaguiarfullstack.webchat.web.model.Usuario;
import com.felipeaguiarfullstack.webchat.web.service.UsuarioService;

@Named
public class UsuarioBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	UsuarioService usuarioService;
	
	private Usuario usuario = new Usuario();
	
	public String cadastrar() {
		usuarioService.cadastrar(usuario);
		
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
