package com.felipeaguiarfullstack.webchat.web.view;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import com.felipeaguiarfullstack.webchat.web.service.UsuarioService;

@Named
public class SegurancaBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private String email;
	private String cpf;

	private String nomeUsuario;

	@Autowired
	UsuarioService usuarioService;

	@PostConstruct
	public void inicializar() {
		nomeUsuario = "Felipe";
	}

	public String login() {

		String enderecoIp = getRequest().getHeader("X-FORWARDED-FOR");
		if (enderecoIp == null) {
			enderecoIp = getRequest().getRemoteAddr();
		}

		try {
			usuarioService.login(email, cpf, enderecoIp);
			return "chat?faces-redirect=true";
		} catch (Exception e) {
			return null;
		}
	}

	public String logout() {
		usuarioService.logout();
		return "login?faces-redirect=true";
	}

	public HttpServletRequest getRequest() {
		FacesContext context = FacesContext.getCurrentInstance();
		return (HttpServletRequest) context.getExternalContext().getRequest();
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

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

}
