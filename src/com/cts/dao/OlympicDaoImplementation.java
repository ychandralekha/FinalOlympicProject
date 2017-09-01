package com.cts.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import com.cts.pojo.OlympicUserPojo;
import com.cts.util.PojoToPojo;
import com.cts.util.SessionFactoryProvider;

public class OlympicDaoImplementation {
//@Autowired
//SessionFactoryProvider sessionFactory;
//
//@Autowired
//PojoToPojo copynewPojo;
	public static String registerLoading(OlympicUserPojo olympicObject) {
		//SessionFactory SF=sessionFactory.sessionFactory();
		String result;
		SessionFactoryProvider sessionfact=new SessionFactoryProvider();
		SessionFactory factory=sessionfact.sessionFactory();
		Session session=factory.openSession();
//		OlympicUserPojo olympicUser = new OlympicUserPojo();
//		try {
//			PojoToPojo copynewPojo=new PojoToPojo();
//			copynewPojo.copyObject(olympicObject, provider);
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		} 
		olympicObject.setStatus(0);
		olympicObject.setDisplay(1);
		olympicObject.setRole("user");
		
		Transaction transaction =session.beginTransaction();
		session.save(olympicObject);

		
		try{
		transaction.commit();
		result="success";
		}catch(Exception e){
			olympicObject.setUsername("");
			session.close();
			factory.close();
			result="failure";
			e.printStackTrace();
		}
		session.close();
		factory.close();
		return result;
	}
	
	
	public static String userValidation(OlympicUserPojo olympicObject)
	{
		String result="";
		SessionFactoryProvider sessionfact=new SessionFactoryProvider();
		SessionFactory factory=sessionfact.sessionFactory();
		Session session=factory.openSession();
		Transaction transaction=session.beginTransaction();
		session.save(olympicObject);
		transaction.commit();
		session.close();
		factory.close();
		return result;
	}

}
