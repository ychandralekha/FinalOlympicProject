package com.cts.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.cts.pojo.OlympicUserPojo;

public class UserValidation implements Validator {

	public boolean supports(Class<?> arg0) {
		return OlympicUserPojo.class.equals(arg0);
	}

	public void validate(Object arg0, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username",
				"error.username", "*UserName is required");
		OlympicUserPojo userTo = (OlympicUserPojo) arg0;
		if (!errors.hasErrors()) {
			if (userTo.getUsername().length() < 3) {
				errors.rejectValue("username", "error.username",
						"Invalid User Name");
			}
		}
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password",
				"error.password", "*Password is required");

	}
}