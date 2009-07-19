package persistence.DAO;

import java.util.List;

import org.hibernate.Transaction;
import org.hibernate.classic.Session;

import beans.SystemPassword;

public class SystemPasswordHibernateDAO extends HibernateDAO implements SystemPasswordDAO{
	
	
	public SystemPasswordHibernateDAO(boolean testing) {
		super(testing);
		if(!alreadyThereIsPassword()){
			setPassword("admin");
		}
	}
	
	public SystemPasswordHibernateDAO(){
		super(false);
		if(!alreadyThereIsPassword()){
			setPassword("admin");
		}
	}

	@Override
	public String getPassword(){
		if(!alreadyThereIsPassword()){
			setPassword("admin");			
		}
		Session session = sf.openSession();
		Transaction transaction = session.beginTransaction();
		SystemPassword rp = (SystemPassword) session.get(SystemPassword.class, new Long(1));		
		transaction.commit();
		session.close();
		return rp.getPasswd();
	}

	@Override
	public void setPassword(String password) {
		if(!alreadyThereIsPassword()){
			savePassword(password);
		}else{
			updatePassword(password);
		}
		
	}

	private void updatePassword(String password) {
		SystemPassword rp = new SystemPassword();
		rp.setId(1);
		rp.setPasswd(password);
		Session session = sf.openSession();
		Transaction transaction = session.beginTransaction();
		session.update(rp);
		transaction.commit();
		session.close();		
		
	}

	private void savePassword(String password) {
		SystemPassword rp = new SystemPassword();
		rp.setId(1);
		rp.setPasswd(password);
		Session session = sf.openSession();
		Transaction transaction = session.beginTransaction();		
		session.save(rp);
		transaction.commit();
		session.close();
		
	}

	@SuppressWarnings("unchecked")
	private boolean alreadyThereIsPassword() {
		Session session = sf.openSession();
		Transaction transaction = session.beginTransaction();
		List<SystemPassword> passwordList = session.createQuery("from " + SystemPassword.class.getCanonicalName()).list();
		transaction.commit();
		session.close();
		return passwordList.size() > 0;
	}	

	@SuppressWarnings("unchecked")
	@Override
	public void resetPassword() {
		Session session = sf.openSession();
		Transaction transaction = session.beginTransaction();
		List<SystemPassword> passwordList = session.createQuery("from " + SystemPassword.class.getCanonicalName()).list();
		if(passwordList.size() > 0){
			session.delete(passwordList.get(0));			
		}
		transaction.commit();
		session.close();
		
	}

}
