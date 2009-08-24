package persistence.hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

import beans.Administrador;
import beans.Caso;
import beans.Demanda;
import beans.Foto;


public class HibernateConfig {
	
	private static boolean testing = false;
	private static SessionFactory sessionFactory = null;	
	private static String currentSchema;	
	
	private static void configure(){
		try {
			AnnotationConfiguration config = new AnnotationConfiguration();			
			config.setProperty("hibernate.dialect",	"org.hibernate.dialect.PostgreSQLDialect");
			config.setProperty("hibernate.connection.driver_class",	"org.postgresql.Driver");
			config.setProperty("hibernate.connection.url", "jdbc:postgresql://localhost:5432/larbc_db");
			if(!testing){
				currentSchema = "larbcschema";
				config.setProperty("hibernate.default_schema", currentSchema);				
			}else{				
				currentSchema = "larbctestschema";
				config.setProperty("hibernate.default_schema", currentSchema);
			}
			config.setProperty("hibernate.connection.username", "larbc");
			config.setProperty("hibernate.connection.password", "123456");
			config.setProperty("hibernate.connection.pool_size", "1");
			config.setProperty("hibernate.connection.autocommit", "true");
			config.setProperty("hibernate.cache.provider_class", "org.hibernate.cache.NoCacheProvider");
			config.setProperty("hibernate.hbm2ddl.auto", "update");
			config.setProperty("hibernate.show_sql", "true");
			config.setProperty("hibernate.transaction.factory_class", "org.hibernate.transaction.JDBCTransactionFactory");
			config.setProperty("hibernate.current_session_context_class", "thread");

			// Mapped classes
			
			config.addAnnotatedClass(Administrador.class);
			config.addAnnotatedClass(Foto.class);
			config.addAnnotatedClass(Demanda.class);
			config.addAnnotatedClass(Caso.class);			
			sessionFactory = config.buildSessionFactory();
			createGeometryColumn(currentSchema, sessionFactory);
		} catch (Throwable ex) {
			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}
	
	private static void createGeometryColumn(String currentSchema, SessionFactory sf){
		String sqlQuery = "SELECT AddGeometryColumn('" + currentSchema + "','casos','location',-1,'POINT',2);";
		try{
			sf.openSession().createSQLQuery(sqlQuery).list();			
		}catch(Exception e){}
	}
	
	public static SessionFactory getSessionFactory(boolean testando) {
		if(sessionFactory == null){
			testing = testando;
			configure();
		}
		return sessionFactory;
	}	

	public static String getCurrentSchema(){
		return currentSchema;
	}
	
}