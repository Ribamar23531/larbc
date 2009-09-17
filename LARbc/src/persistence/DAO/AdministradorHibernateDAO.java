package persistence.DAO;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;

import beans.Administrador;
import exceptions.AdministradorNotFoundException;
import exceptions.LoginAlreadyRegisteredException;
import exceptions.PermissionDeniedException;

public class AdministradorHibernateDAO extends HibernateDAO implements AdministradorDAO{	
	
	public AdministradorHibernateDAO(boolean testing){
		super(testing);
		createRootIfNeeded();
	}	

	public AdministradorHibernateDAO(){
		super(false);
		createRootIfNeeded();
	}
	
	@SuppressWarnings("unchecked")
	private List<Administrador> getRoots(){
		Session session = sf.openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.getNamedQuery("getRoots");
        query.setString("true", "true");
        List<Administrador> roots = query.list();
        transaction.commit();
        session.close();
        return roots;
	}	
	
	@Override
	public void createRootIfNeeded() {		
        List<Administrador> roots = getRoots();
        if(roots.size() == 0){
            try {
				saveAdministrador(new Administrador("root", "root", "Root", "true"));
			} catch (LoginAlreadyRegisteredException e) {}
        }        
		
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
	public void removeAdministrador(Administrador admin) throws AdministradorNotFoundException, PermissionDeniedException {
		getAdministrador(admin.getLogin());//verifica se existe o administrador a ser removido
		if(admin.isRoot()){
			List<Administrador> roots = getRoots();
			if(roots.size() == 1){
				throw new PermissionDeniedException();
			}
		}
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
        transaction.commit();
        session.close();
        if(administradores.size() == 0){
            throw new AdministradorNotFoundException();
        }
        return administradores.get(0);
	}
	
	@Override
	public Administrador getAdministrador(long idAdmin)	throws AdministradorNotFoundException {
		Session session = sf.openSession();
		Transaction transaction = session.beginTransaction();
		Administrador admin = (Administrador) session.get(Administrador.class, new Long(idAdmin));
		if(admin == null){
			throw new AdministradorNotFoundException();
		}
		transaction.commit();
		session.close();
		return admin;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Administrador> getAdministradores() {
		Session session = sf.openSession();
		Transaction transaction = session.beginTransaction();
		List<Administrador> administradores = session.createQuery("from " + Administrador.class.getCanonicalName()).list();
		transaction.commit();
		session.close();		
		return administradores;
	}	

	@Override
	public void updateAdministrador(Administrador admin)
			throws AdministradorNotFoundException, PermissionDeniedException {
//		getAdministrador(admin.getLogin());//verifica se existe o administrador a ser removido
		if(!admin.isRoot()){
			List<Administrador> roots = getRoots();
			if(roots.size() == 1 && roots.get(0).getIdAdministrador() == admin.getIdAdministrador()){
				throw new PermissionDeniedException();
			}
		}
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

	@SuppressWarnings("unchecked")
	@Override
	public void removeNotRoots() {
		Session session = sf.openSession();
		Transaction transaction = session.beginTransaction();		
		List<Administrador> admins = session.createQuery("from " + Administrador.class.getCanonicalName()).list();
		for (int i = 0; i < admins.size(); i++) {	
			Administrador admin = admins.get(i);
			if(!admin.isRoot()){
				session.delete(admin);
				transaction.commit();
				transaction = session.beginTransaction();				
			}
		}
		session.close();
		
	}

}
