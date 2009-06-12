package persistence.DAO;

import beans.Foto;
import exceptions.FotoAlreadySavedException;
import exceptions.FotoNotFoundException;

public interface FotoDAO {
	
	public void saveFoto(Foto foto) throws FotoAlreadySavedException;
	
	public void removeFoto(Foto foto) throws FotoNotFoundException;
	
	public Foto getFoto(long id, String path) throws FotoNotFoundException;
	
	public void updateFoto(Foto oldPicture, Foto newPicture) throws FotoNotFoundException, FotoAlreadySavedException;
	
	public void removeAllFotos();

}
