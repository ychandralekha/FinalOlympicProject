package com.cts.dao;

import java.util.List;
import java.util.Set;

import com.cts.exception.OlympicException;
import com.cts.pojo.OlympicDataPojo;
import com.cts.pojo.OlympicHost;
import com.cts.pojo.SearchFilter;

public interface IUserOperations {
	public boolean uploadData( List<OlympicDataPojo> file) throws OlympicException;
	public List<OlympicHost> retrieveHostList();
	public Set<String> retrieveAthleteList();
	public Set<String> retrieveSportList();
	public Set<String> retrieveDisciplineList(String sport);
	public Set<String> retrieveEventList(String sport,String discipline);
	public boolean insertUserRecord(OlympicDataPojo record) throws OlympicException ;
	public List<OlympicDataPojo> displayUserRecord(OlympicDataPojo record) ;
	public List<OlympicDataPojo> updateRecords(OlympicDataPojo record, String name,String operation) throws OlympicException ;
	public List<OlympicDataPojo> searchFilterList(SearchFilter record);
	public boolean filterDisplayRecord(List<OlympicDataPojo>filteredData) throws OlympicException;
	
}
