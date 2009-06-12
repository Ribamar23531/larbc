package persistencia.DAO;

import java.util.List;

import modelo.Administrador;

import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;

import persistencia.hibernate.HibernateUtil;
import exceptions.AdministradorNaoEncontradoException;
import exceptions.LoginJaRegistradoException;

public class AdministradorHibernateDAO implements AdministradorDAO{
	
	private SessionFactory sf;
	private String schema;
	
	public AdministradorHibernateDAO(boolean testando){
		if(!testando){
			this.schema = HibernateUtil.getSchema();			
		}else{
			this.schema = HibernateUtil.getTesteSchema();
		}
		this.sf = HibernateUtil.getSessionFactory(testando);
	}	
	
	@Override
	public void gravarAdministrador(Administrador admin) throws LoginJaRegistradoException {
		try {
			getAdministrador(admin.getLogin());
			throw new LoginJaRegistradoException();
		} catch (AdministradorNaoEncontradoException e) {}
		Session session = sf.openSession();
		Transaction transaction = session.beginTransaction();		
		session.save(admin);
		transaction.commit();
		session.close();
	}
	
	@Override
	public void removerAdministrador(Administrador admin)
			throws AdministradorNaoEncontradoException {
		getAdministrador(admin.getLogin());//verifica se existe o administrador a ser removido
		Session session = sf.openSession();
		Transaction transaction = session.beginTransaction();
		session.delete(admin);		
		transaction.commit();
		session.close();
		
	}
	
	@Override
	public Administrador getAdministrador(String login)
			throws AdministradorNaoEncontradoException {
		Session session = sf.openSession();
		Transaction transaction = session.beginTransaction();
		String consulta = "SELECT * FROM " 
			+ this.schema + ".administradores a WHERE a.login = '" + login + "';";
		List<Object[]> admin = session.createSQLQuery(consulta).list();
		transaction.commit();
		session.close();
		if(admin.size() == 0){
			throw new AdministradorNaoEncontradoException();
		}
		String log = (String) admin.get(0)[1];
		String pas = (String) admin.get(0)[2];
		String nome = (String) admin.get(0)[3];
		return new Administrador(log, nome, pas);
	}

	@Override
	public void atualizarAdministrador(Administrador admin)
			throws AdministradorNaoEncontradoException {
		getAdministrador(admin.getLogin());//verifica se existe o administrador a ser removido
		Session session = sf.openSession();
		Transaction transaction = session.beginTransaction();
		session.update(admin);
		transaction.commit();
		session.close();
		
	}

	@Override
	public void apagarTodosAdministradores() {
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
