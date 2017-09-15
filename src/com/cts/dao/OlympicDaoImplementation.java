package com.cts.dao;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.cts.pojo.OlympicAthlete;
import com.cts.pojo.OlympicDataPojo;
import com.cts.pojo.OlympicEventDiscipline;
import com.cts.pojo.OlympicHost;
import com.cts.pojo.OlympicUserPojo;
import com.cts.pojo.SearchFilter;
import com.cts.util.PojoToPojo;

public class OlympicDaoImplementation extends AbstractFactory {

	public static String registerLoading(OlympicUserPojo olympicObject) {
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
			e.printStackTrace();
		}
		return result;
	}
	
	
	public static boolean userValidation(OlympicUserPojo olympicObject)
	{
		boolean result;

		SessionFactory factory=getFactory();
		Session session=factory.openSession();
		Transaction transaction=session.beginTransaction();
		Query query=session.createQuery("from OlympicUserPojo where username= :username");
		query.setParameter("username",olympicObject.getUsername());
		try{
		OlympicUserPojo userObject=(OlympicUserPojo)query.uniqueResult();
		
		System.out.println(userObject);
		if(userObject.getStatus()==0)
		result=false;
		else
			result=true;
		}
		catch(Exception e)
		{
			result=false;
		session.close();
		factory.close();
		
		}
		return result;
	}


	public static boolean approveUser(String[] users) {
		// TODO Auto-generated method stub
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

	public static List<OlympicUserPojo> displayUsers() {
		SessionFactory factory=getFactory();
		Session session=factory.openSession();
		Query query=session.createQuery("from OlympicUserPojo where status=0");
		List<OlympicUserPojo>users=query.list();
		return users;
	}


	public static boolean uploadData( List<OlympicDataPojo> file) {
		
		Set<String>eventList=new HashSet<String>();
		Set<String>hostList=new HashSet<String>();
		SessionFactory factory=getFactory();
		Session session=factory.openSession();
		PojoToPojo copynewPojo=new PojoToPojo();

		for(OlympicDataPojo record :file)
		{
			Transaction transaction1=session.beginTransaction();
			OlympicAthlete athlete=new OlympicAthlete();
			OlympicEventDiscipline event=new OlympicEventDiscipline();
			OlympicHost host=new OlympicHost();
	
			copynewPojo.copyObject(record,host);
			copynewPojo.copyObject(record, event);
			copynewPojo.copyObject(record, athlete);
			
			
			int eventSize=eventList.size();
			int hostSize=hostList.size();
			
			eventList.add(event.getDiscipline()+" "+event.getEvent()+" "+event.getSport());
			hostList.add(host.getCity()+" "+host.getYear());
			
			System.out.println(eventList.size());
			
			if(eventList.size()==eventSize)
			{
				System.out.println("event.....");
				Criteria criteria=session.createCriteria(OlympicEventDiscipline.class).add(Restrictions.eq("sport",record.getSport())).add(Restrictions.eq("discipline",record.getDiscipline())).add(Restrictions.eq("event", record.getEvent()));
				OlympicEventDiscipline event1=(OlympicEventDiscipline) criteria.list().get(0);
				System.out.println(event1.getEventId()+"  "+event1.getDiscipline());
				event1.getAthleteList().add(athlete);
				athlete.setEventObject(event1);
				System.out.println(athlete.getAthlete());
			}
			else
			{
				event.getAthleteList().add(athlete);
				session.save(event);
				athlete.setEventObject(event);
				System.out.println(athlete.getAthlete());
			}
			if(hostList.size()==hostSize)
			{
				System.out.println("host....");
				Criteria criteria=session.createCriteria(OlympicHost.class).add(Restrictions.eq("year",host.getYear())).add(Restrictions.eq("city",host.getCity()));
				host=(OlympicHost) criteria.list().get(0);
				host.getAthleteList().add(athlete);
				athlete.setHostObject(host);
				System.out.println(host.getYear());
			}
			else
			{
				host.getAthleteList().add(athlete);
				session.save(host);
				athlete.setHostObject(host);
				System.out.println(athlete.getAthlete());
				System.out.println(host.getCity());
			}
			session.save(athlete);
			transaction1.commit();
		}
		
		
		session.close();
		return false;
	}


	public static List<OlympicHost> retrieveHostList() {
		
		SessionFactory factory=getFactory();
		Session session=factory.openSession();
		Query query=session.createQuery("from OlympicHost");
		List<OlympicHost>hostList=query.list();
		return hostList;
	}
	public static Set<String> retrieveAthleteList()
	{
		SessionFactory factory=getFactory();
		Session session=factory.openSession();
		Query query=session.createQuery("select country from OlympicAthlete");
		List<String>athleteList=query.list();
		Set<String>countryList=new HashSet<String>();
		
		countryList.addAll(athleteList);
		return countryList;
	}
	public static Set<String> retrieveSportList() {
		SessionFactory factory=getFactory();
		Session session=factory.openSession();
		Query query=session.createQuery("select sport from OlympicEventDiscipline");
		List<String>sportList=query.list();
		Set<String>sportSet=new HashSet<String>();
		sportSet.addAll(sportList);
		return sportSet;
	}
	public static Set<String> retrieveDisciplineList(String sport) {
		SessionFactory factory=getFactory();
		Session session=factory.openSession();
		Query query=session.createQuery("select discipline from OlympicEventDiscipline where sport= :sport");
		query.setParameter("sport", sport);
		List<String>disciplineList=query.list();
		Set<String>disciplineSet=new HashSet<String>();
		disciplineSet.addAll(disciplineList);
		System.out.println(disciplineSet.size());
		return disciplineSet;
	}
	public static Set<String> retrieveEventList(String sport,String discipline) {
		System.out.println("sport disp entered");
		SessionFactory factory=getFactory();
		Session session=factory.openSession();
		Query query=session.createQuery("select event from OlympicEventDiscipline where sport= :sport and discipline=:discipline");
		query.setParameter("sport", sport);
		query.setParameter("discipline", discipline);
		List<String>eventList=query.list();
		Set<String>eventSet=new HashSet<String>();
		eventSet.addAll(eventList);
		System.out.println(eventSet.size());
		return eventSet;
	}


	public static boolean insertUserRecord(OlympicDataPojo record) {
		SessionFactory factory=getFactory();
		Session session=factory.openSession();
		OlympicAthlete athlete=new OlympicAthlete();
		PojoToPojo copyObject=new PojoToPojo();
		copyObject.copyObject(record, athlete);
		OlympicEventDiscipline event=new OlympicEventDiscipline();
		copyObject.copyObject(record, event);
		OlympicHost host=new OlympicHost();
		System.out.println(record);

		
		Query query=session.createQuery("select eventId from OlympicEventDiscipline where sport=:sport and discipline=:discipline and event=:event");
		query.setParameter("sport",record.getSport());
		query.setParameter("discipline", record.getDiscipline());
		query.setParameter("event", record.getEvent());
		int eventId=(int)query.uniqueResult();
		event.setEventId(eventId);
		host.setYear(record.getYear());
		athlete.setEventObject(event);
		athlete.setHostObject(host);
		event.getAthleteList().add(athlete);
		host.getAthleteList().add(athlete);
		athlete.setDisplay("1");
		session.save(athlete);
		Transaction transaction=session.beginTransaction();
		transaction.commit();
		
		
		return true;
	}


	public static List<OlympicDataPojo> displayUserRecord(OlympicDataPojo record) {
		SessionFactory factory=getFactory();
		Session session=factory.openSession();
		OlympicAthlete athlete=new OlympicAthlete();
		PojoToPojo copyObject=new PojoToPojo();
		copyObject.copyObject(record, athlete);
		OlympicEventDiscipline event=new OlympicEventDiscipline();
		copyObject.copyObject(record, event);
		OlympicHost host=new OlympicHost();
		
		Query query=session.createQuery("select eventId from OlympicEventDiscipline where sport=:sport and discipline=:discipline and event=:event");
		query.setParameter("sport",record.getSport());
		query.setParameter("discipline", record.getDiscipline());
		query.setParameter("event", record.getEvent());
		int eventId=(int)query.uniqueResult();
		
		query=session.createQuery("select athlete from OlympicAthlete where eventId=:eventId and country=:country and gender=:gender and medal=:medal and year=:year and display=:display");
		query.setParameter("eventId", eventId);
		query.setParameter("country", record.getCountry());
		query.setParameter("gender", record.getGender());
		query.setParameter("medal", record.getMedal());
		query.setParameter("year", record.getYear());
		query.setParameter("display","1");
		List<String>athleteName=query.list();
		
		query=session.createQuery("select city from OlympicHost where year=:year");
		query.setParameter("year",record.getYear());
		String city=(String)query.uniqueResult();
		List<OlympicDataPojo>displayList=new ArrayList<OlympicDataPojo>();
		
		for(String name:athleteName)
		{
			OlympicDataPojo display=new OlympicDataPojo();
			copyObject.copyObject(record, display);
			display.setAthlete(name);
			display.setCity(city);
			displayList.add(display);
			
		}
		return displayList;
	}

	public static List<OlympicDataPojo> updateRecords(OlympicDataPojo record, String name,String operation) {
		SessionFactory factory=getFactory();
		Session session=factory.openSession();
		OlympicAthlete athlete=new OlympicAthlete();
		PojoToPojo copyObject=new PojoToPojo();
		copyObject.copyObject(record, athlete);
		OlympicEventDiscipline event=new OlympicEventDiscipline();
		copyObject.copyObject(record, event);
		OlympicHost host=new OlympicHost();
		
		System.out.println(name+" : "+operation);
		Query query=session.createQuery("select eventId from OlympicEventDiscipline where sport=:sport and discipline=:discipline and event=:event");
		query.setParameter("sport",record.getSport());
		query.setParameter("discipline", record.getDiscipline());
		query.setParameter("event", record.getEvent());
		int eventId=(int)query.uniqueResult();
		if(operation.equalsIgnoreCase("delete"))
		{
			query=session.createQuery("update OlympicAthlete set display=:display where athlete=:athlete and eventId=:eventId and country=:country and gender=:gender and medal=:medal and year=:year");
			query.setParameter("display","0");
			query.setParameter("athlete",name);
			query.setParameter("eventId", eventId);
			query.setParameter("country", record.getCountry());
			query.setParameter("gender", record.getGender());
			query.setParameter("medal", record.getMedal());
			query.setParameter("year", record.getYear());
			query.executeUpdate();
			//session.save(athlete);
			Transaction transaction=session.beginTransaction();
			transaction.commit();
		}
		else
		{
			query=session.createQuery("update OlympicAthlete set athlete=:athlete where athlete=:oldathlete and eventId=:eventId and country=:country and gender=:gender and medal=:medal and year=:year and display=:display");
			query.setParameter("athlete",operation);
			query.setParameter("oldathlete", name);
			query.setParameter("eventId", eventId);
			query.setParameter("country", record.getCountry());
			query.setParameter("gender", record.getGender());
			query.setParameter("medal", record.getMedal());
			query.setParameter("year", record.getYear());
			query.setParameter("display","1");
			query.executeUpdate();
			Transaction transaction=session.beginTransaction();
			transaction.commit();
		}
		
		query=session.createQuery("from OlympicAthlete where eventId=:eventId and country=:country and gender=:gender and medal=:medal and year=:year and display=:display");
		query.setParameter("eventId", eventId);
		query.setParameter("country", record.getCountry());
		query.setParameter("gender", record.getGender());
		query.setParameter("medal", record.getMedal());
		query.setParameter("year", record.getYear());
		query.setParameter("display","1");
		List<OlympicAthlete>athleteList=query.list();
		List<OlympicDataPojo>athleteName=new ArrayList<OlympicDataPojo>();
		for(OlympicAthlete olympicAthlete:athleteList)
		{
			record.setAthlete(olympicAthlete.getAthlete());
			athleteName.add(record);
		}
		athleteName.forEach(System.out::println);
		
		return athleteName;
	}


	public static List<OlympicDataPojo> searchFilterList(SearchFilter record) {
		SessionFactory factory=getFactory();
		Session session=factory.openSession();
		OlympicAthlete athlete=new OlympicAthlete();
		PojoToPojo copyObject=new PojoToPojo();
		copyObject.copyObject(record, athlete);
		OlympicEventDiscipline event=new OlympicEventDiscipline();
		copyObject.copyObject(record, event);
		OlympicHost host=new OlympicHost();
		
	

Criteria cr = session.createCriteria(OlympicAthlete.class,"athlete").createAlias("athlete.eventObject","event");
		
		if((record.getStartYear()!=0)&&(record.getEndYear()!=0))
		{
			cr.add(Restrictions.between("hostObject.year", record.getStartYear(), record.getEndYear()));
		}
	
			if(!(record.getCountry()).isEmpty())
			{
				cr.add(Restrictions.like("country", record.getCountry()+"%"));
			}
			
			if(!(record.getAthlete()).isEmpty())
			{
				cr.add(Restrictions.like("athlete", record.getAthlete()+"%"));
			}
			if(!(record.getSport()).isEmpty())
			{
				cr.add(Restrictions.like("event.sport", record.getSport()+"%"));
			}
			if(!(record.getGender()).isEmpty())
			{
				cr.add(Restrictions.like("athlete.gender", record.getGender()+"%"));
			}
			System.out.println(record.getSortSelect());
			if(!record.getSortSelect().equalsIgnoreCase("year"))
			{
				    cr.addOrder(Order.asc(record.getSortSelect()));
			}
			else
			{
				cr.createCriteria("hostObject").addOrder(Order.asc("year"));
			}
			//System.out.println("query.... "+cr.list().size());
			List<OlympicAthlete> results = cr.list();
			
			List<OlympicDataPojo>finalResult=new ArrayList<OlympicDataPojo>();
			for(OlympicAthlete search:results)
			{
				OlympicDataPojo olympicObject=new OlympicDataPojo();
				olympicObject.setYear(search.getHostObject().getYear());
				olympicObject.setCity(search.getHostObject().getCity());
				olympicObject.setSport(search.getEventObject().getSport());
				olympicObject.setDiscipline(search.getEventObject().getDiscipline());
				olympicObject.setAthlete(search.getAthlete());
				olympicObject.setCountry(search.getCountry());
				olympicObject.setGender(search.getGender());
				olympicObject.setEvent(search.getEventObject().getEvent());
				olympicObject.setMedal(search.getMedal());
				finalResult.add(olympicObject);
			}
		return finalResult;
	}

		public static boolean filterDisplayRecord(List<OlympicDataPojo>filteredData)
	    {
		   boolean result=false;
	    	try
	    	{
	    		FileWriter fw=new FileWriter("D:\\FilteredOlympicData.txt");    
		           for(OlympicDataPojo record:filteredData)
		           {
		        	   String readRecord=record.getYear()+","+record.getCity()+","+record.getSport()+","+record.getDiscipline()+","+record.getAthlete()+","+record.getCountry()+","+record.getGender()+","+record.getEvent()+","+record.getMedal();
		        	   fw.write(readRecord);    
		           }
		           result=true;
		           fw.close();
	    	}
	    	catch(Exception e){System.out.println(e);}   
	    	System.out.println("Success...");
	        return result;
	    }
}
