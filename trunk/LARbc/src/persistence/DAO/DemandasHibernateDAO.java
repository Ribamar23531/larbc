package persistence.DAO;

import java.util.List;

import org.hibernate.Transaction;
import org.hibernate.classic.Session;

import beans.Demanda;
import exceptions.DemandaNotFoundException;

public class DemandasHibernateDAO extends HibernateDAO implements DemandaDAO{
	
	public DemandasHibernateDAO(boolean testing){
		super(testing);
	}
	
	public DemandasHibernateDAO(){
		super(false);
	}
	
	@Override
	public Demanda getDemanda(long idDemanda) throws DemandaNotFoundException {
		Session session = sf.openSession();
		Transaction transaction = session.beginTransaction();
		Demanda demanda = (Demanda) session.get(Demanda.class, new Long(idDemanda));
		if(demanda == null){
			throw new DemandaNotFoundException();
		}
		transaction.commit();
		session.close();
		return demanda;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Demanda> getDemandas(){
		Session session = sf.openSession();
		Transaction transaction = session.beginTransaction();
		List<Demanda> demandas = session.createQuery("from " + Demanda.class.getCanonicalName()).list();
		transaction.commit();
		session.close();		
		return demandas;
	}
	
	@Override
	public void saveDemanda(Demanda demanda) {
		Session session = sf.openSession();
		Transaction transaction = session.beginTransaction();		
		session.save(demanda);
		transaction.commit();
		session.close();
		
	}

	@Override
	public void removeAllDemandas() {
		List<Demanda> demandas = getDemandas();
		for (Demanda demanda : demandas) {
			try {
				removeDemanda(demanda);
			} catch (DemandaNotFoundException e) {				
			}
		}
		
	}

	@Override
	public void removeDemanda(Demanda demanda) throws DemandaNotFoundException {
		getDemanda(demanda.getIdDemanda());//verifica se existe o caso a ser removida
		Session session = sf.openSession();
		Transaction transaction = session.beginTransaction();
		session.delete(demanda);
		transaction.commit();
		session.close();		
	}	

	@Override
	public void updateDemanda(Demanda demanda) throws DemandaNotFoundException {
		getDemanda(demanda.getIdDemanda());//verifica se existe o caso a ser removida
		Session session = sf.openSession();
		Transaction transaction = session.beginTransaction();
		session.update(demanda);
		transaction.commit();
		session.close();		
	}	

}
