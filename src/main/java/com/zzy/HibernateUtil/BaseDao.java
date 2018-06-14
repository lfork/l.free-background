package com.zzy.HibernateUtil;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class BaseDao {

	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public Session getSession(){
		return sessionFactory.getCurrentSession();
	}
	
}
