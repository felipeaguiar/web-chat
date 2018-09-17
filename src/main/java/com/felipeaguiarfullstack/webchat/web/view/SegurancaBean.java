package com.felipeaguiarfullstack.webchat.web.view;

import java.io.Serializable;

import javax.inject.Named;

@Named
public class SegurancaBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String email;
	private String cpf;
	
	public String login() {
		return "chat?faces-redirect=true";
	}
	
	public String logout() {
		return "login?faces-redirect=true";
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
	
	
	
}
