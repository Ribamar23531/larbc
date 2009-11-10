package persistence.hibernate.hibernateDAO;

import org.hibernate.SessionFactory;

import persistence.hibernate.HibernateConfig;

public abstract class HibernateDAO {
	
	protected SessionFactory sf;
	protected String schema;
	
	public HibernateDAO(boolean testing){
		this.schema = HibernateConfig.getCurrentSchema();		
		this.sf = HibernateConfig.getSessionFactory(testing);
	}

}
