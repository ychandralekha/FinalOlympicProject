package com.cts.service;

import org.springframework.stereotype.Service;

import com.cts.dao.OlympicDaoImplementation;
import com.cts.pojo.OlympicUserPojo;
@Service("olympicService")
public class OlympicService {

	public String registerDetails(OlympicUserPojo olympicObject)
	{
		String result=OlympicDaoImplementation.registerLoading(olympicObject);
		return result;
	}
}
