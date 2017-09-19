package com.cts.service;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.cts.dao.IUserOperations;
import com.cts.dao.IUserValidations;
import com.cts.dao.OlympicDaoImplementation;
import com.cts.dao.OlympicUserDaoImplementation;
import com.cts.exception.OlympicException;
import com.cts.pojo.OlympicDataPojo;
import com.cts.pojo.OlympicHost;
import com.cts.pojo.OlympicUserPojo;
import com.cts.pojo.SearchFilter;
@Service("olympicService")
public class OlympicService {

	public String registerDetails(OlympicUserPojo olympicObject) throws OlympicException
	{
		IUserValidations user=new OlympicUserDaoImplementation();
		String result=user.registerLoading(olympicObject);
		return result;
	}
	public boolean validateUser(OlympicUserPojo olympicObject) throws OlympicException
	{
		IUserValidations user=new OlympicUserDaoImplementation();
		boolean result=user.userValidation(olympicObject);
		return result;
	}
	public List<OlympicUserPojo>displayUsers()
	{
		IUserValidations user=new OlympicUserDaoImplementation();
		return user.displayUsers();
	}
	public boolean approveUser(String[] users)
	{
		IUserValidations user=new OlympicUserDaoImplementation();
		boolean result=user.approveUser(users);
		return result;
	}
	
	
	public boolean upload( List<OlympicDataPojo> file) throws OlympicException
	{
		IUserOperations user=new OlympicDaoImplementation();
		boolean result=user.uploadData(file);
		return result;
	}
	public List<OlympicHost> retrieveHost()
	{
		IUserOperations user=new OlympicDaoImplementation();
		List<OlympicHost>hostList=user.retrieveHostList();
		return hostList;
	}
	public Set<String> retrieveAthleteList()
	{
		IUserOperations user=new OlympicDaoImplementation();
		Set<String>athleteList=user.retrieveAthleteList();
		return athleteList;
	}
	public Set<String> retrieveSport()
	{
		IUserOperations user=new OlympicDaoImplementation();
		Set<String>sportList=user.retrieveSportList();
		return sportList;
	}
	public Set<String> retrieveDiscipline(String sport)
	{
		IUserOperations user=new OlympicDaoImplementation();
		Set<String>disciplineList=user.retrieveDisciplineList(sport);
		return disciplineList;
	}
	public Set<String> retrieveEvent(String sport,String discipline)
	{
		IUserOperations user=new OlympicDaoImplementation();
		Set<String>eventList=user.retrieveEventList(sport,discipline);
		return eventList;
	}
	public boolean insertRecord(OlympicDataPojo record) throws OlympicException
	{
		IUserOperations user=new OlympicDaoImplementation();
		boolean result;
		result=user.insertUserRecord(record);
		return result;
	}
	public List<OlympicDataPojo> displayRecord(OlympicDataPojo record)
	{
		IUserOperations user=new OlympicDaoImplementation();
		List<OlympicDataPojo> result;
		result=user.displayUserRecord(record);
		return result;
	}
	public List<OlympicDataPojo> updateRecord(OlympicDataPojo record,String name,String operation) throws OlympicException
	{
		IUserOperations user=new OlympicDaoImplementation();
		return user.updateRecords(record,name,operation);
	}
	public List<OlympicDataPojo> searchFilterRecord(SearchFilter record)
	{
		IUserOperations user=new OlympicDaoImplementation();
		return user.searchFilterList(record);
		
	}
	public boolean filterDisplay(List<OlympicDataPojo> filteredData) throws OlympicException {
		IUserOperations user=new OlympicDaoImplementation();
		return user.filterDisplayRecord(filteredData);
		
	}
}
