package persistence.DAO;

import org.hibernate.SessionFactory;

import persistence.hibernate.HibernateConfig;

public abstract class HibernateDAO {
	
	protected SessionFactory sf;
	protected String schema;
	
	public HibernateDAO(boolean testing){
		if(!testing){
			this.schema = HibernateConfig.getSchema();			
		}else{
			this.schema = HibernateConfig.getTesteSchema();
		}
		this.sf = HibernateConfig.getSessionFactory(testing);
	}

}
