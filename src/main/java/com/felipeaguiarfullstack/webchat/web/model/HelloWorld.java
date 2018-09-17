package com.felipeaguiarfullstack.webchat.web.model;

import javax.inject.Named;

@Named
public class HelloWorld {

	private String primeiroNome = "Felipe";
	private String ultimoNome = "Aguiar";

	public String getPrimeiroNome() {
		return primeiroNome;
	}

	public void setPrimeiroNome(String primeiroNome) {
		this.primeiroNome = primeiroNome;
	}

	public String getUltimoNome() {
		return ultimoNome;
	}

	public void setUltimoNome(String ultimoNome) {
		this.ultimoNome = ultimoNome;
	}

	public String ola() {
		return "Olá " + primeiroNome + " " + ultimoNome + "!";
	}
}