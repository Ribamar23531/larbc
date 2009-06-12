package persistence.DAO;

import java.util.List;

import org.hibernate.Transaction;
import org.hibernate.classic.Session;

import beans.Administrador;
import exceptions.AdministradorNotFoundException;
import exceptions.LoginAlreadyRegisteredException;

public class AdministradorHibernateDAO extends HibernateDAO implements AdministradorDAO{	
	
	public AdministradorHibernateDAO(boolean testing){
		super(testing);
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
	
	@Override
	public Administrador getAdministrador(String login)
			throws AdministradorNotFoundException {
		Session session = sf.openSession();
		Transaction transaction = session.beginTransaction();
		String consulta = "SELECT * FROM " 
			+ this.schema + ".administradores a WHERE a.login = '" + login + "';";
		List<Object[]> admin = session.createSQLQuery(consulta).list();
		transaction.commit();
		session.close();
		if(admin.size() == 0){
			throw new AdministradorNotFoundException();
		}
		String log = (String) admin.get(0)[1];
		String pas = (String) admin.get(0)[2];
		String nome = (String) admin.get(0)[3];
		return new Administrador(log, nome, pas);
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
