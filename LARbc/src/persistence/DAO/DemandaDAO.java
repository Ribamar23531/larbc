package persistence.DAO;

import java.util.List;

import beans.Demanda;
import exceptions.DemandaNotFoundException;

public interface DemandaDAO {
	
	public void saveDemanda(Demanda demanda);
	
	public void removeDemanda(Demanda demanda) throws DemandaNotFoundException;
	
	public List<Demanda> getDemandas();
	
	public Demanda getDemanda(long idDemanda) throws DemandaNotFoundException;
	
	public void updateDemanda(Demanda demanda) throws DemandaNotFoundException;
	
	public void removeAllDemandas();

}
