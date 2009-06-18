package persistence.DAO;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;

import beans.Administrador;
import exceptions.AdministradorNotFoundException;
import exceptions.LoginAlreadyRegisteredException;

public class AdministradorHibernateDAO extends HibernateDAO implements AdministradorDAO{	
	
	public AdministradorHibernateDAO(boolean testing){
		super(testing);
	}

	public AdministradorHibernateDAO(){
		super(false);
	}
	
	@Override
	public void saveAdministrador(Administrador admin) throws LoginAlreadyRegisteredException {
		try {
			getAdministrador(admin.getLogin());
			throw new LoginAlreadyRegisteredException();
		} catch (AdministradorNotFoundException e) {}
		Session session = sf.openSession();
		Transaction transaction = session.beginTransaction();		
		session.save(admin);
		transaction.commit();
		session.close();
	}
	
	@Override
	public void removeAdministrador(Administrador admin) throws AdministradorNotFoundException {
		getAdministrador(admin.getLogin());//verifica se existe o administrador a ser removido
		Session session = sf.openSession();
		Transaction transaction = session.beginTransaction();
		session.delete(admin);
		transaction.commit();
		session.close();
		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Administrador getAdministrador(String login)
			throws AdministradorNotFoundException {
		
		Session session = sf.openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.getNamedQuery("administrador.login");
        query.setString("login", login);
        List<Administrador> administradores = query.list();
        if(administradores.size() == 0){
            throw new AdministradorNotFoundException();
        }
        transaction.commit();
        session.close();
        return administradores.get(0);
	}

	@Override
	public void updateAdministrador(Administrador admin)
			throws AdministradorNotFoundException {
		getAdministrador(admin.getLogin());//verifica se existe o administrador a ser removido
		Session session = sf.openSession();
		Transaction transaction = session.beginTransaction();
		session.update(admin);
		transaction.commit();
		session.close();
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public void removeAllAdministradores() {
		Session session = sf.openSession();
		Transaction transaction = session.beginTransaction();		
		List<Administrador> admins = session.createQuery("from " + Administrador.class.getCanonicalName()).list();
		for (int i = 0; i < admins.size(); i++) {		
			session.delete(admins.get(i));
			transaction.commit();
			transaction = session.beginTransaction();
		}
		session.close();
	}	

}
