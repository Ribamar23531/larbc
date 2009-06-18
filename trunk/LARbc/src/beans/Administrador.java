package beans;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

@Entity
@Table(name = "administradores")
@NamedQueries( { @NamedQuery(name = "administrador.login", query = "select a from Administrador a where a.login = :login") })
public class Administrador {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_administrador", updatable = false, nullable = false)
	private long idAdministrador;
	@Column(updatable = true, nullable = false)
	private String login;
	@Column(name = "senha", updatable = true, nullable = false)
	private String password;
	@Column(updatable = true, nullable = false)
	private String nome;
	
	@OneToMany(mappedBy="inseridoPor", fetch = FetchType.LAZY)
	@Cascade(CascadeType.ALL)
	private List<Caso> casos;
	
	//construtor vazio necessario pelo hibernate
	public Administrador(){
		
	}
	
	public Administrador(String login, String password, String nome){
		this.login = login;
		this.password = password;
		this.nome = nome;
		this.casos = new ArrayList<Caso>();
	}
	
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

	public void setCasos(List<Caso> casos) {
		this.casos = casos;
	}

	public List<Caso> getCasos() {
		return casos;
	}
	
	public void addCaso(Caso caso){
		this.casos.add(caso);
	}
	
	public void removeCaso(Caso caso){
		int i = 0;
		for (Caso c : casos) {
			if(c.getIdCaso() == caso.getIdCaso()){
				this.casos.remove(i);
			}
			i++;
		}
	}

}
