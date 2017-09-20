package com.cts.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.cts.constants.SqlConstants;
import com.cts.exception.OlympicException;
import com.cts.pojo.OlympicUserPojo;

public class OlympicUserDaoImplementation extends AbstractFactory implements IUserValidations{
	public static final Logger LOG=Logger.getLogger(OlympicUserDaoImplementation.class);
	public String registerLoading(OlympicUserPojo olympicObject) throws OlympicException {
		LOG.info("registration..");
		String result;
		SessionFactory factory=getFactory();
		Session session=factory.openSession();
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
			result="failure";
			throw new OlympicException("Registration Failed");
		}
		session.close();
		return result;
	}
	
	
	public boolean userValidation(OlympicUserPojo olympicObject) throws OlympicException
	{
		boolean result;
		LOG.info("user validation");
		SessionFactory factory=getFactory();
		Session session=factory.openSession();
		Transaction transaction=session.beginTransaction();
		Query query=session.createQuery(SqlConstants.fromUser);
		query.setParameter("username",olympicObject.getUsername());
		query.setParameter("password", olympicObject.getPassword());
		query.setParameter("status",1);
		try{
		OlympicUserPojo userObject=(OlympicUserPojo)query.uniqueResult();
		if(userObject==null)
		{
			result=false;
		}
		else
			result=true;
		}
		catch(Exception e)
		{
			result=false;
		session.close();
		factory.close();
		throw new OlympicException("User Not Available");
		}
		return result;
	}


	public boolean approveUser(String[] users) {
		// TODO Auto-generated method stub
		LOG.info("approving users");
		boolean result=false;
		SessionFactory factory=getFactory();
		Session session=factory.openSession();
		Transaction transaction=session.beginTransaction();
		for(String user:users)
		{
			Criteria criteria=session.createCriteria(OlympicUserPojo.class).add(Restrictions.eq("username", user));
			OlympicUserPojo olympicObj=(OlympicUserPojo)criteria.list().get(0);
			olympicObj.setStatus(1);
		}
		transaction.commit();
		result=true;
		return result;
	}

	public List<OlympicUserPojo> displayUsers() {
		LOG.info("display users");
		SessionFactory factory=getFactory();
		Session session=factory.openSession();
		Query query=session.createQuery(SqlConstants.fromUserPojo);
		List<OlympicUserPojo>users=query.list();
		return users;
	}


}
