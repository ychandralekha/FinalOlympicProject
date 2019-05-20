package com.cts.dao;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.cts.constants.SqlConstants;
import com.cts.exception.OlympicException;
import com.cts.pojo.OlympicAthlete;
import com.cts.pojo.OlympicDataPojo;
import com.cts.pojo.OlympicEventDiscipline;
import com.cts.pojo.OlympicHost;
import com.cts.pojo.SearchFilter;
import com.cts.util.PojoToPojo;

public class OlympicDaoImplementation extends AbstractFactory implements IUserOperations{
 
	public static final Logger LOG=Logger.getLogger(OlympicDaoImplementation.class);
	public boolean uploadData( List<OlympicDataPojo> file) throws OlympicException {
		
		Set<String>eventList=new HashSet<String>();
		Set<String>hostList=new HashSet<String>();
		SessionFactory factory=getFactory();
		Session session=factory.openSession();
		PojoToPojo copynewPojo=new PojoToPojo();
		try{
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
			
			
			if(eventList.size()==eventSize)
			{
				
				Criteria criteria=session.createCriteria(OlympicEventDiscipline.class).add(Restrictions.eq("sport",record.getSport())).add(Restrictions.eq("discipline",record.getDiscipline())).add(Restrictions.eq("event", record.getEvent()));
				OlympicEventDiscipline event1=(OlympicEventDiscipline) criteria.list().get(0);
				
				event1.getAthleteList().add(athlete);
				athlete.setEventObject(event1);
			
			}
			else
			{
				event.getAthleteList().add(athlete);
				session.save(event);
				athlete.setEventObject(event);
				
			}
			if(hostList.size()==hostSize)
			{
				
				Criteria criteria=session.createCriteria(OlympicHost.class).add(Restrictions.eq("year",host.getYear())).add(Restrictions.eq("city",host.getCity()));
				host=(OlympicHost) criteria.list().get(0);
				host.getAthleteList().add(athlete);
				athlete.setHostObject(host);
			
			}
			else
			{
				host.getAthleteList().add(athlete);
				session.save(host);
				athlete.setHostObject(host);
			
			}
			session.save(athlete);
			transaction1.commit();
		}
		}
		catch(Exception e)
		{
			LOG.info("Upload Fail");
			throw new OlympicException("Upload Fail");
		}
		
		session.close();
		return false;
	}


	public List<OlympicHost> retrieveHostList() {
		LOG.info("retriving host list");
		SessionFactory factory=getFactory();
		Session session=factory.openSession();
		Query query=session.createQuery(SqlConstants.olympicHost);
		List<OlympicHost>hostList=query.list();
		return hostList;
	}
	public Set<String> retrieveAthleteList()
	{
		LOG.info("retriving athlete list");
		SessionFactory factory=getFactory();
		Session session=factory.openSession();
		Query query=session.createQuery(SqlConstants.selectCountryQuery);
		List<String>athleteList=query.list();
		Set<String>countryList=new HashSet<String>();
		
		countryList.addAll(athleteList);
		return countryList;
	}
	public Set<String> retrieveSportList() {
		LOG.info("retriving sport list");
		SessionFactory factory=getFactory();
		Session session=factory.openSession();
		Query query=session.createQuery(SqlConstants.selectSportEventDiscipline);
		List<String>sportList=query.list();
		Set<String>sportSet=new HashSet<String>();
		sportSet.addAll(sportList);
		return sportSet;
	}
	public Set<String> retrieveDisciplineList(String sport) {
		LOG.info("retriving discipline list");
		SessionFactory factory=getFactory();
		Session session=factory.openSession();
		Query query=session.createQuery(SqlConstants.selectDiscipline);
		query.setParameter("sport", sport);
		List<String>disciplineList=query.list();
		Set<String>disciplineSet=new HashSet<String>();
		disciplineSet.addAll(disciplineList);
		return disciplineSet;
	}
	public Set<String> retrieveEventList(String sport,String discipline) {
		LOG.info("retriving event list");
		SessionFactory factory=getFactory();
		Session session=factory.openSession();
		Query query=session.createQuery(SqlConstants.selectEvent);
		query.setParameter("sport", sport);
		query.setParameter("discipline", discipline);
		List<String>eventList=query.list();
		Set<String>eventSet=new HashSet<String>();
		eventSet.addAll(eventList);
		return eventSet;
	}


	public boolean insertUserRecord(OlympicDataPojo record) throws OlympicException {
		LOG.info("adding record");
		SessionFactory factory=getFactory();
		Session session=factory.openSession();
		OlympicAthlete athlete=new OlympicAthlete();
		PojoToPojo copyObject=new PojoToPojo();
		copyObject.copyObject(record, athlete);
		OlympicEventDiscipline event=new OlympicEventDiscipline();
		copyObject.copyObject(record, event);
		OlympicHost host=new OlympicHost();

		try
		{
		Query query=session.createQuery(SqlConstants.selectEventId);
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
		}
		catch(Exception e)
		{
			throw new OlympicException("Adding record failed!");
		}
		
		return true;
	}


	public List<OlympicDataPojo> displayUserRecord(OlympicDataPojo record) {
		LOG.info("display user record");
		SessionFactory factory=getFactory();
		Session session=factory.openSession();
		OlympicAthlete athlete=new OlympicAthlete();
		PojoToPojo copyObject=new PojoToPojo();
		copyObject.copyObject(record, athlete);
		OlympicEventDiscipline event=new OlympicEventDiscipline();
		copyObject.copyObject(record, event);
		OlympicHost host=new OlympicHost();
		
		Query query=session.createQuery(SqlConstants.selectEventId);
		query.setParameter("sport",record.getSport());
		query.setParameter("discipline", record.getDiscipline());
		query.setParameter("event", record.getEvent());
		int eventId=(int)query.uniqueResult();
		
		query=session.createQuery(SqlConstants.selectAthlete);
		query.setParameter("eventId", eventId);
		query.setParameter("country", record.getCountry());
		query.setParameter("gender", record.getGender());
		query.setParameter("medal", record.getMedal());
		query.setParameter("year", record.getYear());
		query.setParameter("display","1");
		List<String>athleteName=query.list();
		
		query=session.createQuery(SqlConstants.selectCity);
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

	public List<OlympicDataPojo> updateRecords(OlympicDataPojo record, String name,String operation) throws OlympicException {
		LOG.info("update the records");
		SessionFactory factory=getFactory();
		Session session=factory.openSession();
		OlympicAthlete athlete=new OlympicAthlete();
		PojoToPojo copyObject=new PojoToPojo();
		copyObject.copyObject(record, athlete);
		OlympicEventDiscipline event=new OlympicEventDiscipline();
		copyObject.copyObject(record, event);
		OlympicHost host=new OlympicHost();
		try{
			
		Query query=session.createQuery(SqlConstants.selectEventId);
		query.setParameter("sport",record.getSport());
		query.setParameter("discipline", record.getDiscipline());
		query.setParameter("event", record.getEvent());
		int eventId=(int)query.uniqueResult();
		if(operation.equalsIgnoreCase("delete"))
		{
			query=session.createQuery(SqlConstants.updateAthlete);
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
			query=session.createQuery(SqlConstants.updateSetAthlete);
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
		
		query=session.createQuery(SqlConstants.fromAthlete);
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
			OlympicDataPojo records=new OlympicDataPojo();
			System.out.println(record);
			copyObject.copyObject(record, records);
			records.setAthlete(olympicAthlete.getAthlete());
			athleteName.add(records);
		}
		return athleteName;
	}
	catch(Exception e)
	{
		throw new OlympicException("Update record failed!");
	}
	}


	public List<OlympicDataPojo> searchFilterList(SearchFilter record) {
		LOG.info("search and filter to get a list");
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
			cr.add(Restrictions.eq("athlete.display", "1"));
			if(!record.getSortSelect().equalsIgnoreCase("year"))
			{
				    cr.addOrder(Order.asc(record.getSortSelect()));
			}
			else
			{
				cr.createCriteria("hostObject").addOrder(Order.asc("year"));
			}
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
		public boolean filterDisplayRecord(List<OlympicDataPojo>filteredData) throws OlympicException
	    {
			LOG.info("download the file");
		   boolean result=false;
	    	try
	    	{
	    		FileWriter fw=new FileWriter("D:\\FilteredOlympicData.txt");    
		           for(OlympicDataPojo record:filteredData)
		           {
		        	   String readRecord=record.getYear()+","+record.getCity()+","+record.getSport()+","+record.getDiscipline()+","+record.getAthlete()+","+record.getCountry()+","+record.getGender()+","+record.getEvent()+","+record.getMedal();
		        	   fw.write(readRecord);    
		        	   fw.write(System.getProperty("line.separator"));
		           }
		           result=true;
		           fw.close();
	    	}
	    	catch(Exception e){  
	    		LOG.info("download failed");
	    		throw new OlympicException("Download Failed");}   
	        return result;
	      
	    }
}
