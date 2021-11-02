package hibAssignment;

import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {
	private static SessionFactory sessionFactory;
	public static SessionFactory getSessionFactory() {
		if (sessionFactory == null) {
			try {
				Configuration conf = new Configuration();
				
				Properties settings = new Properties();
				settings.put(Environment.DRIVER, "com.mysql.jdbc.Driver");
				settings.put(Environment.URL, "jdbc:mysql://localhost:3306/hibernate");
				settings.put(Environment.USER, "root");
				settings.put(Environment.PASS, "#g&C02232019");
				settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQLDialect");
				
				settings.put(Environment.SHOW_SQL, "true");
				
				settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
				
				settings.put(Environment.HBM2DDL_AUTO, "update");
				
				conf.setProperties(settings);
				
				conf.addAnnotatedClass(Item.class);
				
				ServiceRegistry serviceReg = new StandardServiceRegistryBuilder()
						.applySettings(conf.getProperties()).build();
				
				sessionFactory = conf.buildSessionFactory(serviceReg);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return sessionFactory;
	}

}
