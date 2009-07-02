package persistence.DAO;


public interface SystemPasswordDAO {
	
	public void setPassword(String password);
	
	public void resetPassword();
	
	public String getPassword();

}
