package com.cts.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class OlympicDataController {
@RequestMapping(value="/userLogin", method = RequestMethod.POST)
public String userPage(HttpServletRequest request,HttpServletResponse response)
{
	String page=request.getParameter("radio");
	String pageDirect="";
	if(page.equalsIgnoreCase("addRecord"))
	pageDirect="AddingPage";
	else if(page.equalsIgnoreCase("updateRecord"))
		pageDirect= "UpdatePage";
	else if(page.equalsIgnoreCase("searchFilter"))
		pageDirect= "SearchFilter";
	return pageDirect;
}
@RequestMapping(value="/adminWelcome",method=RequestMethod.POST)
public String adminPage(HttpServletRequest request,HttpServletResponse response)
{
	String page=request.getParameter("radioSelect");
	String pageDirect="";
	if(page.equalsIgnoreCase("uploadData"))
		pageDirect="AdminUpload";
	else if(page.equalsIgnoreCase("approveUsers"))
		pageDirect="AdminApprovalPage";
	return pageDirect;
}

//@RequestMapping(value="/UserLogin",method=RequestMethod.GET)
//public ModelAndView registerUser(){  
//   return new ModelAndView("UserLogin","UserLogin",new OlympicUserPojo());  
//}  
}
