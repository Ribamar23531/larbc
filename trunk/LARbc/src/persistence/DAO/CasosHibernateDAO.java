package persistence.DAO;

import java.util.List;

import org.hibernate.Transaction;
import org.hibernate.classic.Session;

import beans.Administrador;
import beans.Caso;
import exceptions.CasoNotFoundException;

public class CasosHibernateDAO extends HibernateDAO implements CasoDAO{
	
	public CasosHibernateDAO(boolean testing){
		super(testing);
	}
	
	public CasosHibernateDAO(){
		super(false);
	}
	
	@Override
	public void saveCaso(Caso caso) {		
		Session session = sf.openSession();
		Transaction transaction = session.beginTransaction();		
		session.save(caso);
		transaction.commit();
		session.close();
		
	}
	
	@Override
	public void removeCaso(Caso caso) throws CasoNotFoundException {
		getCaso(caso.getIdCaso());//verifica se existe o caso a ser removido
		Session session = sf.openSession();
		Transaction transaction = session.beginTransaction();
		session.delete(caso);
		transaction.commit();
		session.close();
		
	}

	@Override
	public Caso getCaso(long idCaso) throws CasoNotFoundException {
		Session session = sf.openSession();
		Transaction transaction = session.beginTransaction();
		Caso caso = (Caso) session.get(Caso.class, new Long(idCaso));
		if(caso == null){
			throw new CasoNotFoundException();
		}
		transaction.commit();
		session.close();
		return caso;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Caso> getCasos(){
		Session session = sf.openSession();
		Transaction transaction = session.beginTransaction();
		List<Caso> demandas = session.createQuery("from " + Caso.class.getCanonicalName()).list();
		transaction.commit();
		session.close();		
		return demandas;
	}
	
	@Override
	public void updateCaso(Caso caso) throws CasoNotFoundException {
		getCaso(caso.getIdCaso());//verifica se existe o caso a ser removida
		Session session = sf.openSession();
		Transaction transaction = session.beginTransaction();
		session.update(caso);		
		transaction.commit();
		session.close();		
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public void removeAllCasos() {
		Session session = sf.openSession();
		Transaction transaction = session.beginTransaction();		
		List<Administrador> casos = session.createQuery("from " + Caso.class.getCanonicalName()).list();
		for (int i = 0; i < casos.size(); i++) {		
			session.delete(casos.get(i));
			transaction.commit();
			transaction = session.beginTransaction();
		}
		session.close();
		
	}	

}
