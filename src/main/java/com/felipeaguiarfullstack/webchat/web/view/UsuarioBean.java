package com.felipeaguiarfullstack.webchat.web.view;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.felipeaguiarfullstack.webchat.web.model.Usuario;
import com.felipeaguiarfullstack.webchat.web.service.UsuarioService;

@Named
@RequestScoped
public class UsuarioBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private String email;
	private String cpf;

	private Usuario usuario = new Usuario();

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private AuthenticationManager authenticationManager;

	public String cadastrar() {
		usuarioService.cadastrar(usuario);

		usuario = new Usuario();
		return "login?faces-redirect=true";

	}

	public String login() {
		try {
			UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(email, cpf);
			Authentication auth = authenticationManager.authenticate(token);
			SecurityContextHolder.getContext().setAuthentication(auth);
			
			return "chat?faces-redirect=true";
		} catch (Exception e) {
			falhaNoLogin();
			return null;
		}
	}

	public void falhaNoLogin() {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_FATAL, "Erro!", "Usuário ou Senha incorretos"));
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
