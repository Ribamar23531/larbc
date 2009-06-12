package persistence.DAO;

import org.hibernate.SessionFactory;

import persistence.hibernate.HibernateUtil;

public abstract class HibernateDAO {
	
	protected SessionFactory sf;
	protected String schema;
	
	public HibernateDAO(boolean testing){
		if(!testing){
			this.schema = HibernateUtil.getSchema();			
		}else{
			this.schema = HibernateUtil.getTesteSchema();
		}
		this.sf = HibernateUtil.getSessionFactory(testing);
	}

}
