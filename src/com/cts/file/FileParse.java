package com.cts.file;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;

import com.cts.exception.OlympicException;
import com.cts.pojo.OlympicDataPojo;

public class FileParse {
	public static final Logger LOG=Logger.getLogger(FileParse.class);
	public List<OlympicDataPojo> parseData(String path) throws OlympicException {
		LOG.info("file parsing");
		List<OlympicDataPojo>fileData=new ArrayList<OlympicDataPojo>();
		try {
		BufferedReader bufferedReader=new BufferedReader(new FileReader(path));
		bufferedReader.readLine();
		String record;
	
			
			while((record=bufferedReader.readLine())!=null)
			{
				String[] records=record.split(",");
				OlympicDataPojo olympicData;
				if(records.length==10)
				{
					String firstName=records[4].replace("\"","");
					String lastName=records[5].replace("\"", "");
					String athlete=firstName+","+lastName;
					olympicData=new OlympicDataPojo(Integer.parseInt(records[0]),records[1].toUpperCase(),records[2].toUpperCase(),records[3].toUpperCase(),athlete.toUpperCase(),records[6].toUpperCase(),records[7].toUpperCase(),records[8].toUpperCase(),records[9].toUpperCase());
				}
				else
					olympicData=new OlympicDataPojo(Integer.parseInt(records[0]),records[1].toUpperCase(),records[2].toUpperCase(),records[3].toUpperCase(),records[4].toUpperCase(),records[5].toUpperCase(),records[6].toUpperCase(),records[7].toUpperCase(),records[8].toUpperCase());
				fileData.add(olympicData);
			}
			Set<String>s=new HashSet<String>();
			for(OlympicDataPojo odp:fileData)
			{
				s.add(odp.getAthlete());
			}
			LOG.info("upload done with ...."+s.size());
		
		bufferedReader.close();
		} catch (Exception e) {
			throw new OlympicException("Upload a valid file");
		}
		return fileData;
	}
}
