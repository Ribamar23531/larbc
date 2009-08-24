package beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

@Entity
@Table(name = "administradores")
@NamedQueries( {@NamedQuery(name = "administrador.login", query = "select a from Administrador a where a.login = :login"),
				@NamedQuery(name = "getRoots", query = "select a from Administrador a where a.isRoot = :true")})
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
	@Column(updatable = true, nullable = false)
	private String isRoot;
	
	//construtor vazio necessario pelo hibernate
	public Administrador(){}
	
	public Administrador(String login, String password, String nome){
		this.login = login;
		this.password = password;		
		this.nome = nome;
		this.isRoot = "false";
	}
	
	public Administrador(String login, String password, String nome, String rootValue){
		this.login = login;
		this.password = password;		
		this.nome = nome;
		this.isRoot = rootValue;
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
	
	public boolean equals(Object obj) {
		Administrador anotherOne = (Administrador) obj;
		return this.getIdAdministrador() == anotherOne.getIdAdministrador();
//		return this.getLogin().equals(anotherOne.getLogin()) && this.getPassword().equals(anotherOne.getPassword()) && this.getNome().equals(anotherOne.getNome());
	}

	public void setIsRoot(String isRoot) {
		this.isRoot = isRoot;
	}

	public String getIsRoot() {
		return isRoot;
	}
	
	public boolean isRoot(){
		return isRoot.equals("true");
	}	

}
