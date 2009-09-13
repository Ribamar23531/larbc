package persistence.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import persistence.hibernate.HibernateConfig;

public class ConnectionFactory {
    
    //Atributos de ConnectionFactory
    static int connCount = 0;
    
    public static Connection newConnection() throws SQLException {
        try {
            Class.forName( "org.postgresql.Driver" ).newInstance();
            String username = HibernateConfig.getUserName();
            String password = HibernateConfig.getPassword();
            
            // Abre a conex�o com o banco
            Connection c = null;
            Properties connInfo = new Properties();
            if ( username != null && password != null ) {
                connInfo.put("user", username);
                connInfo.put("password", password);
            }
            c = DriverManager.getConnection(HibernateConfig.getUrlDb(), connInfo);            
            
            return c;
        } catch ( Exception e ) {        	
            throw new SQLException(e.getMessage());
        }
    }
    
}
