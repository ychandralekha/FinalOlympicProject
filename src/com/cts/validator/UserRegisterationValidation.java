package com.cts.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import com.cts.pojo.OlympicUserPojo;

public class UserRegisterationValidation {

	public boolean supports(Class<?> arg0) {
		return OlympicUserPojo.class.equals(arg0);
	}

	public void validate(Object arg0, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName",
				"error.firstName", "*Firstname is required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName",
				"error.lastName", "*Lastname is required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username",
				"error.username", "*UserName is required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "datePicker",
				"error.datePicker", "*Date of birth is required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email",
				"error.email", "*Email is required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "phoneNumber",
				"error.phoneNumber", "*PhoneNumber is required");
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
