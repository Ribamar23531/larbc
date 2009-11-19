package persistence.DAO;

import java.sql.SQLException;
import java.util.List;

import persistence.util.Coordinates;
import beans.Caso;
import beans.Foto;
import exceptions.CasoNotFoundException;

public interface CasoDAO {
	
	public void saveCaso(Caso caso);
	
	public void removeCaso(Caso caso) throws CasoNotFoundException;
	
	public Caso getCaso(long idCaso) throws CasoNotFoundException;
	
	public List<Caso> getAllCasos();
	
	public List<Caso> getCasos(long adminId);
	
	public List<Foto> getFotos(Caso caso);
	
	public void updateCaso(Caso caso) throws CasoNotFoundException;
	
	public void removeAllCasos();

	public Coordinates getCasoLocation(long id) throws SQLException;

}
