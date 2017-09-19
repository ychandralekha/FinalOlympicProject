package com.cts.dao;

import java.util.List;

import com.cts.exception.OlympicException;
import com.cts.pojo.OlympicUserPojo;

public interface IUserValidations {
	public String registerLoading(OlympicUserPojo olympicObject) throws OlympicException;
	public boolean userValidation(OlympicUserPojo olympicObject) throws OlympicException;
	public boolean approveUser(String[] users) ;
	public List<OlympicUserPojo> displayUsers();
}
