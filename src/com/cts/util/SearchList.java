package com.cts.util;

import java.util.Map;

import org.apache.log4j.Logger;

import com.cts.pojo.SearchFilter;

public class SearchList {
	public static final Logger LOG=Logger.getLogger(SearchList.class);
	public SearchFilter retrieveFilterObject(Map<String, String>record)
	{
		LOG.info("search filter map to pojo..");
		SearchFilter olympicRecord=new SearchFilter();
		if(record.get("startyear").isEmpty() || record.get("endyear").isEmpty())
		{
			olympicRecord.setStartYear(0);
			olympicRecord.setEndYear(0);
		}
		else
		{
			olympicRecord.setStartYear(Integer.parseInt(record.get("startyear")));
			olympicRecord.setEndYear(Integer.parseInt(record.get("endyear")));
		}
		olympicRecord.setAthlete(record.get("athlete"));
		olympicRecord.setSport(record.get("sport"));
		olympicRecord.setCountry(record.get("country"));
		olympicRecord.setGender(record.get("gender"));
		olympicRecord.setSortSelect(record.get("sortingSelect"));
		return olympicRecord;
		
	}
}
