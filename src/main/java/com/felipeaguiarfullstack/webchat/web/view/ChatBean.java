package com.felipeaguiarfullstack.webchat.web.view;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.felipeaguiarfullstack.webchat.web.model.Usuario;
import com.felipeaguiarfullstack.webchat.web.service.UsuarioService;

@Named
@RequestScoped
public class ChatBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private String nomeUsuario;

	@Autowired
	UsuarioService usuarioService;

	@PostConstruct
	public void inicializar() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null) {
			Usuario usuario = usuarioService.iniciarChat(authentication.getName(), getEnderecoIp());		
			nomeUsuario = usuario.getNome();
		}
	}
	
	private String getEnderecoIp() {
		String enderecoIp = getRequest().getHeader("X-FORWARDED-FOR");
		if (enderecoIp == null) {
			enderecoIp = getRequest().getRemoteAddr();
		}
		
		return enderecoIp;
	}

	public HttpServletRequest getRequest() {
		FacesContext context = FacesContext.getCurrentInstance();
		return (HttpServletRequest) context.getExternalContext().getRequest();
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

}
