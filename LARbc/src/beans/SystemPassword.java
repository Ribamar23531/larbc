package beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class SystemPassword {
	
	
	@Id	
	@Column(name = "id_password", updatable = false, nullable = false)	
	private long id;
	private String passwd;
	
	public SystemPassword(){}

	public void setId(long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public String getPasswd() {
		return passwd;
	}	

}
