package com.googlecode.projeto1.client.beans;

import java.io.Serializable;

/**
 * @author Alcione Pinheiro
 * @author Diego Rodrigues
 * @author João Felipe
 * @version LARbc 1.0
 */
public class AdminBean implements Serializable{

	private static final long serialVersionUID = 1L;
	private long idAdministrador;
	private String login;
	private String password;
	private String nome;
	private boolean isRoot;
	
	public AdminBean(){}
	
	public long getIdAdministrador() {
		return idAdministrador;
	}
	
	public void setIdAdministrador(long idAdministrador) {
		this.idAdministrador = idAdministrador;
	}
	
	public String getLogin() {
		return login;
	}
	
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setIsRoot(boolean isRoot) {
		this.isRoot = isRoot;
	}

	public boolean getIsRoot() {
		return isRoot;
	}	
	
}
