package com.dariotek.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class DAOAbstractClass {

	@Autowired
	SessionFactory sessionFactory;	
	
	public Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}
	
}
