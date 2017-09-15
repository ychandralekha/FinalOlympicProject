package com.cts.dao;

import org.hibernate.SessionFactory;

import com.cts.util.SessionFactoryProvider;

public abstract class AbstractFactory {

	protected static SessionFactory getFactory() {
		return  SessionFactoryProvider.sessionFactory();
	}
}
