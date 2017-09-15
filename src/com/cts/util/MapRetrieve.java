package com.cts.util;

import java.util.Map;

import com.cts.pojo.OlympicDataPojo;

public class MapRetrieve {
public OlympicDataPojo retrieveObject(Map<String, String>record)
{
	System.out.println("hey....");
	OlympicDataPojo olympicRecord=new OlympicDataPojo();
	olympicRecord.setCity(record.get("city"));
	olympicRecord.setYear(Integer.parseInt(record.get("year")));
	olympicRecord.setEvent(record.get("event"));
	olympicRecord.setSport(record.get("sport"));
	olympicRecord.setDiscipline(record.get("discipline"));
	olympicRecord.setCountry(record.get("country"));
	olympicRecord.setGender(record.get("gender"));
	olympicRecord.setMedal(record.get("medal"));
	System.out.println(olympicRecord);
	return olympicRecord;
	
}
}
