package persistence.DAO;

import java.util.List;

import beans.Caso;
import exceptions.CasoNotFoundException;

public interface CasoDAO {
	
	public void saveCaso(Caso caso);
	
	public void removeCaso(Caso caso) throws CasoNotFoundException;
	
	public Caso getCaso(long idCaso) throws CasoNotFoundException;
	
	public List<Caso> getCasos();
	
	public void updateCaso(Caso caso) throws CasoNotFoundException;
	
	public void removeAllCasos();

}
