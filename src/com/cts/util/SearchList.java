package com.cts.util;

import java.util.Map;

import com.cts.pojo.SearchFilter;

public class SearchList {
	public SearchFilter retrieveFilterObject(Map<String, String>record)
	{
		SearchFilter olympicRecord=new SearchFilter();
		if(record.get("startyear").isEmpty() || record.get("endyear").isEmpty())
		{
			System.out.println("year......");
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
		System.out.println(olympicRecord);
		return olympicRecord;
		
	}
}
