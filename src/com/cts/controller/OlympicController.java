package com.cts.controller;

//import org.springframework.beans.factory.annotation.Autowired;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
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

import com.cts.exception.OlympicException;
import com.cts.pojo.OlympicUserPojo;
import com.cts.service.OlympicService;
import com.cts.validator.UserRegisterationValidation;
import com.cts.validator.UserValidation;

@Controller
public class OlympicController {
	public static final Logger LOG=Logger.getLogger(OlympicController.class);
	
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
			BindingResult result, ModelMap model,HttpServletRequest request) {
		HttpSession session=request.getSession();
		LOG.info("Login Page");
		userValidate.validate(user, result);
		if (result.hasErrors()) {
			return "Login";
		}
		session.setAttribute("userName", user.getUsername());
		if(user.getUsername().equalsIgnoreCase("ycl") && user.getPassword().equalsIgnoreCase("1234"))
			return "AdminWelcomePage";
		else
		{
			boolean userResult;
			try {
				userResult = olympicService.validateUser(user);
				if(userResult==false)
				{
				request.setAttribute("error","User Doesn't Exist");
				model.clear();
				model.addAttribute("user",new OlympicUserPojo());
				return "Login";
				}
				else if(userResult==true)
					return "UserLogin";
				} catch (OlympicException e) {
				request.setAttribute("error",e);
			}
		}
		return "UserLogin";
	}
	@RequestMapping(value="/Register",method=RequestMethod.GET)
	public ModelAndView registerUser(){  
		LOG.info("Register Page");
       return new ModelAndView("RegisterPage","register",new OlympicUserPojo());  
   }  
	@RequestMapping(value= "/OlympicControllerRegister",method = RequestMethod.POST)
	public String validateUserRegisteration(@ModelAttribute("register")OlympicUserPojo register,BindingResult result, ModelMap model,HttpServletRequest request)
	{
		LOG.info("Validating user registration");
		userRegistertaionValidate.validate(register, result);
		if(result.hasErrors())
		{
			return "RegisterPage";
		}
		
		try {
		
			olympicService.registerDetails(register);
		
		} catch (OlympicException e) {
			request.setAttribute("error", e);
		}
		model.addAttribute("user", new OlympicUserPojo());
		model.addAttribute("register",register);
		return "Login";
	}
	@RequestMapping(value="/Login",method=RequestMethod.GET)
	public String logOutUser(Model model,HttpServletRequest request){
		model.addAttribute("user", new OlympicUserPojo());
		HttpSession session=request.getSession();
		session.removeAttribute("userName");
       return "Login";  
   }
	@RequestMapping(value="UserLogin",method=RequestMethod.GET)
	public String backToUserLogin()
	{
	       return "UserLogin"; 
	}
	@RequestMapping(value="AdminWelcomePage",method=RequestMethod.GET)
	public String backToAdminLogin()
	{
	       return "AdminWelcomePage"; 
	}
}

