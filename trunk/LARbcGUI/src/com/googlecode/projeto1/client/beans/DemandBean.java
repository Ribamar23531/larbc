package com.googlecode.projeto1.client.beans;

import java.io.Serializable;

/**
 * @author Alcione Pinheiro
 * @author Diego Rodrigues
 * @author João Felipe
 * @version LARbc 1.0
 */
public class DemandBean implements Serializable{
	private static final long serialVersionUID = 1L;
	private long idDemanda;
	private String estado;
	private String cidade;
	private String bairro;
	private String rua;
	private int numero;
	private String nome;
	private float areaConstruida;
	private float areaTotal;
	private int vagasGaragem;
	private int quartos;
	private int suites;
	private int banheiros;
	private String tipo;
	private float preco;
	private String nomeCliente;
	private String emailCliente;
	private String telefone;
	private boolean jahModerado;
	private int tipoNegocio;
	
	public DemandBean(){}
	
	public long getIdDemanda() {
		return idDemanda;
	}
	public void setIdDemanda(long idDemanda) {
		this.idDemanda = idDemanda;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getRua() {
		return rua;
	}
	public void setRua(String rua) {
		this.rua = rua;
	}
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public float getAreaConstruida() {
		return areaConstruida;
	}
	public void setAreaConstruida(float areaConstruida) {
		this.areaConstruida = areaConstruida;
	}
	public float getAreaTotal() {
		return areaTotal;
	}
	public void setAreaTotal(float areaTotal) {
		this.areaTotal = areaTotal;
	}
	public int getVagasGaragem() {
		return vagasGaragem;
	}
	public void setVagasGaragem(int vagasGaragem) {
		this.vagasGaragem = vagasGaragem;
	}
	public int getQuartos() {
		return quartos;
	}
	public void setQuartos(int quartos) {
		this.quartos = quartos;
	}
	public int getSuites() {
		return suites;
	}
	public void setSuites(int suites) {
		this.suites = suites;
	}
	public int getBanheiros() {
		return banheiros;
	}
	public void setBanheiros(int banheiros) {
		this.banheiros = banheiros;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public float getPreco() {
		return preco;
	}
	public void setPreco(float preco) {
		this.preco = preco;
	}
	public String getNomeCliente() {
		return nomeCliente;
	}
	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}
	public String getEmailCliente() {
		return emailCliente;
	}
	public void setEmailCliente(String emailCliente) {
		this.emailCliente = emailCliente;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public boolean isJahModerado() {
		return jahModerado;
	}
	public void setJahModerado(boolean jahModerado) {
		this.jahModerado = jahModerado;
	}
	public int getTipoNegocio() {
		return tipoNegocio;
	}
	public void setTipoNegocio(int tipoNegocio) {
		this.tipoNegocio = tipoNegocio;
	}
	
	
}
