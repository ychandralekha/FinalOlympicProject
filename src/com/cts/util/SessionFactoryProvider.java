package com.cts.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Component;

@Component("sessionFactory")
public class SessionFactoryProvider {
	public static SessionFactory factory;
	private SessionFactoryProvider() {
	}
	public static  SessionFactory sessionFactory() {
		if(factory==null)
		{
		Configuration configuration = new Configuration().configure();
		StandardServiceRegistryBuilder  builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
	    factory = configuration.buildSessionFactory(builder.build());
	    
		}
		return factory;
	}

} 