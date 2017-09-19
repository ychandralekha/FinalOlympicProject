package com.cts.util;

import java.util.Map;

import org.apache.log4j.Logger;

import com.cts.pojo.OlympicDataPojo;

public class MapRetrieve {
	public static final Logger LOG=Logger.getLogger(MapRetrieve.class);
public OlympicDataPojo retrieveObject(Map<String, String>record)
{
	LOG.info("map to pojo");
	OlympicDataPojo olympicRecord=new OlympicDataPojo();
	olympicRecord.setCity(record.get("city"));
	olympicRecord.setYear(Integer.parseInt(record.get("year")));
	olympicRecord.setEvent(record.get("event"));
	olympicRecord.setSport(record.get("sport"));
	olympicRecord.setDiscipline(record.get("discipline"));
	olympicRecord.setCountry(record.get("country"));
	olympicRecord.setGender(record.get("gender"));
	olympicRecord.setMedal(record.get("medal"));
	LOG.info("map object..."+olympicRecord);
	return olympicRecord;
	
}
}
