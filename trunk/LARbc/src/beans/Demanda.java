package beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "demandas")
public class Demanda {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_demanda", updatable = false, nullable = false)
	private long idDemanda;
	@Column(updatable = true, nullable = true)
	private String estado;
	@Column(updatable = true, nullable = true)
	private String cidade;
	@Column(updatable = true, nullable = true)
	private String bairro;
	@Column(updatable = true, nullable = true)
	private String rua;
	@Column(updatable = true, nullable = true)
	private int numero;
	@Column(updatable = true, nullable = true)
	private String nome;
	@Column(updatable = true, nullable = true)
	private float areaConstruida;
	@Column(updatable = true, nullable = true)
	private float areaTotal;
	@Column(updatable = true, nullable = true)
	private int vagasGaragem;
	@Column(updatable = true, nullable = true)
	private int quartos;
	@Column(updatable = true, nullable = true)
	private int suites;
	@Column(updatable = true, nullable = true)
	private int banheiros;
	@Column(updatable = true, nullable = true)
	private String tipo;
	@Column(updatable = true, nullable = true)
	private float preco;
	@Column(updatable = true, nullable = true)
	private String nomeCliente;
	@Column(updatable = true, nullable = true)
	private String emailCliente;
	@Column(updatable = true, nullable = true)
	private String telefone;
	@Column(updatable = true, nullable = false)
	private boolean jahModerado;
	@Column(updatable = true, nullable = false)
	private int tipoNegocio;
	
	//construtor vazio necessario pelo hibernate
	public Demanda(){}
	
	
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
	public void setBanheiros(int bainheiros) {
		this.banheiros = bainheiros;
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
