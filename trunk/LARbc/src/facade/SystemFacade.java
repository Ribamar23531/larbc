package facade;

public interface SystemFacade {
	
//=== Administrator operations ===
	public void saveAdministrador(String login, String password, String name, String adminPassword);
	
	public void removeAdministrador(String login, String adminPassword);
	
	public void updateAdministrador(String login, String password, String name, String adminPassword);
	
	public void createCaso(String loginAdministrador, String state, String city, String neighborhood, int number,  
						   String street, String name, float builtArea, float totalArea, int garageSpace, 
						   int bedroom, int suite, int bathroom, String type, float price, int businessType);
	
//=== Photo operations ===
	public void saveFoto(long idCaso, String path);
	
	public void removeFoto(long idCaso, String path);
	
	
}
