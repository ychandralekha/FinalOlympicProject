package com.cts.controller;

//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.cts.pojo.OlympicUserPojo;
import com.cts.service.OlympicService;
import com.cts.validator.UserRegisterationValidation;
import com.cts.validator.UserValidation;

@Controller
public class OlympicController {
	@Autowired
	UserValidation userValidate;
	
	@Autowired
	UserRegisterationValidation userRegistertaionValidate;
	
	@Autowired
	OlympicService olympicService;
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.addValidators(userValidate);
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String userForm(Model model) {
		model.addAttribute("user", new OlympicUserPojo());
		return "Login";
	}
	@RequestMapping(value = "/OlympicController", method = RequestMethod.POST)
	public String validateUser(@ModelAttribute("user") OlympicUserPojo user,
			BindingResult result, ModelMap model) {
		userValidate.validate(user, result);
		if (result.hasErrors()) {
			return "Login";
		}
		if(user.getUsername().equalsIgnoreCase("ycl") && user.getPassword().equalsIgnoreCase("1234"))
			return "AdminWelcomePage";
		else
		{
			//
			return "UserLogin";
		}
	}
	@RequestMapping(value="/Register",method=RequestMethod.GET)
	public ModelAndView registerUser(){  
       return new ModelAndView("RegisterPage","register",new OlympicUserPojo());  
   }  
	@RequestMapping(value= "/OlympicControllerRegister",method = RequestMethod.POST)
	public String validateUserRegisteration(@ModelAttribute("register")OlympicUserPojo register,BindingResult result, ModelMap model)
	{
		userRegistertaionValidate.validate(register, result);
		if(result.hasErrors())
		{
			return "RegisterPage";
		}
		String registerResult=olympicService.registerDetails(register);
		System.out.println(registerResult);
		model.addAttribute("user", new OlympicUserPojo());
		model.addAttribute("register",register);
		return "Login";
	}
}

