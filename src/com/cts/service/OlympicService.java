package com.cts.service;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.cts.dao.OlympicDaoImplementation;
import com.cts.pojo.OlympicDataPojo;
import com.cts.pojo.OlympicHost;
import com.cts.pojo.OlympicUserPojo;
import com.cts.pojo.SearchFilter;
@Service("olympicService")
public class OlympicService {

	public String registerDetails(OlympicUserPojo olympicObject)
	{
		String result=OlympicDaoImplementation.registerLoading(olympicObject);
		return result;
	}
	public boolean validateUser(OlympicUserPojo olympicObject)
	{
		boolean result=OlympicDaoImplementation.userValidation(olympicObject);
		return result;
	}
	public List<OlympicUserPojo>displayUsers()
	{
		return OlympicDaoImplementation.displayUsers();
	}
	public boolean approveUser(String[] users)
	{
		boolean result=OlympicDaoImplementation.approveUser(users);
		return result;
	}
	public boolean upload( List<OlympicDataPojo> file)
	{
		boolean result=OlympicDaoImplementation.uploadData(file);
		return result;
	}
	public List<OlympicHost> retrieveHost()
	{
		List<OlympicHost>hostList=OlympicDaoImplementation.retrieveHostList();
		return hostList;
	}
	public Set<String> retrieveAthleteList()
	{
		Set<String>athleteList=OlympicDaoImplementation.retrieveAthleteList();
		return athleteList;
	}
	public Set<String> retrieveSport()
	{
		Set<String>sportList=OlympicDaoImplementation.retrieveSportList();
		return sportList;
	}
	public Set<String> retrieveDiscipline(String sport)
	{
		Set<String>disciplineList=OlympicDaoImplementation.retrieveDisciplineList(sport);
		return disciplineList;
	}
	public Set<String> retrieveEvent(String sport,String discipline)
	{
		Set<String>eventList=OlympicDaoImplementation.retrieveEventList(sport,discipline);
		return eventList;
	}
	public boolean insertRecord(OlympicDataPojo record)
	{
		boolean result;
		result=OlympicDaoImplementation.insertUserRecord(record);
		return result;
	}
	public List<OlympicDataPojo> displayRecord(OlympicDataPojo record)
	{
		List<OlympicDataPojo> result;
		result=OlympicDaoImplementation.displayUserRecord(record);
		return result;
	}
	public List<OlympicDataPojo> updateRecord(OlympicDataPojo record,String name,String operation)
	{
		return OlympicDaoImplementation.updateRecords(record,name,operation);
	}
	public List<OlympicDataPojo> searchFilterRecord(SearchFilter record)
	{
		return OlympicDaoImplementation.searchFilterList(record);
		
	}
	public boolean filterDisplay(List<OlympicDataPojo> filteredData) {
		return OlympicDaoImplementation.filterDisplayRecord(filteredData);
		
	}
}
