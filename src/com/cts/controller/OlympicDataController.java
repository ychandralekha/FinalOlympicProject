package com.cts.controller;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cts.file.FileParse;
import com.cts.pojo.OlympicDataPojo;
import com.cts.pojo.OlympicUserPojo;
import com.cts.pojo.SearchFilter;
import com.cts.service.OlympicService;
import com.cts.util.MapRetrieve;
import com.cts.util.SearchList;

@Controller
public class OlympicDataController {

@RequestMapping(value="/userLogin", method = RequestMethod.POST)
public String userPage(HttpServletRequest request,HttpServletResponse response,ModelMap model)
{
	
	String page=request.getParameter("radio");
	String pageDirect="";
	OlympicService olympicService=new OlympicService();
	model.addAttribute("host",olympicService.retrieveHost());
	model.addAttribute("countryList",olympicService.retrieveAthleteList());
	model.addAttribute("sportList",olympicService.retrieveSport());
	model.addAttribute("add", new OlympicDataPojo());
	if(page.equalsIgnoreCase("addRecord"))
	{
		pageDirect="AddingPage";
	}
	else if(page.equalsIgnoreCase("updateRecord"))
	{
		pageDirect= "UpdatePage";
	}
	else if(page.equalsIgnoreCase("searchFilter"))
	{
		pageDirect= "SearchFilter";
	}
	return pageDirect;
}

@RequestMapping(value="/adminWelcome",method=RequestMethod.POST)
public ModelAndView registerUser(@RequestParam("radioSelect")String pageDirect,ModelMap model){  
	OlympicService olympicService=new OlympicService();
	if(pageDirect.equals("uploadData"))
	{
   return new ModelAndView("AdminUpload","",new OlympicUserPojo());  
	}
	else
	{
		List<OlympicUserPojo>userList=olympicService.displayUsers();
		 return new ModelAndView("AdminApprovalPage","list",userList);  
	}
}  

@RequestMapping(value= "/adminApproval",method = RequestMethod.POST)
public String validateUserRegisteration(@RequestParam("approve")String[] approveUser,ModelMap model)
{
	OlympicService olympicService=new OlympicService();
	String pageDirect="";
	boolean result=olympicService.approveUser(approveUser);
	if(result==true)
		pageDirect="AdminApprovalPage";
	else
		pageDirect="AdminWelcomePage";
	
	return pageDirect;
}

@RequestMapping(value = "/uploadfile", method = RequestMethod.POST)
public String handleFileUpload(HttpServletRequest request,
        @RequestParam("file") String fileUpload) throws Exception {
		FileParse file=new FileParse();
		 List<OlympicDataPojo>list=file.parseData(fileUpload);
		OlympicService olympicService=new OlympicService();
		olympicService.upload(list);
			return "AdminWelcomePage";
    }
@RequestMapping(value = "/addingPage", method = RequestMethod.POST)
public String afterPopulating(@ModelAttribute("add")OlympicDataPojo olympicData)
{
	
	System.out.println("add page");
	System.out.println(olympicData);
	OlympicService olympicService=new OlympicService();
	olympicService.insertRecord(olympicData);
	return "UserLogin";
	
} 

@RequestMapping(value = "/updatePage", method = RequestMethod.POST)
public String updateOperation(@ModelAttribute("add")OlympicDataPojo olympicData,ModelMap model)
{
	OlympicService olympicService=new OlympicService();
	model.addAttribute("host",olympicService.retrieveHost());
	model.addAttribute("countryList",olympicService.retrieveAthleteList());
	model.addAttribute("sportList",olympicService.retrieveSport());
	model.addAttribute("add", new OlympicDataPojo());
	System.out.println("update page");
	System.out.println(olympicData);
	List<OlympicDataPojo>disp=olympicService.displayRecord(olympicData);
	//OlympicAthletes athletes=new OlympicAthletes();
	//athletes.setAthletes(disp);
	disp.forEach(System.out::println);
	model.addAttribute("displayList",disp);
	return "UpdatePage";	
} 

@RequestMapping(value="getdiscipline",method=RequestMethod.GET)
public @ResponseBody List<String> populateDiscipline(@RequestParam("sportName")String sport)
{
	OlympicService olympicService=new OlympicService();
	List<String>disciplineList=new ArrayList<String>();
	disciplineList.addAll(olympicService.retrieveDiscipline(sport));
	return disciplineList;
}
@RequestMapping(value="getevent",method=RequestMethod.GET)
public @ResponseBody List<String> populateEvent(@RequestParam("sportName")String sport,@RequestParam("disciplineName")String discipline)
{
	System.out.println("controller part"+sport+discipline);
	OlympicService olympicService=new OlympicService();
	List<String>eventList=new ArrayList<String>();
	eventList.addAll(olympicService.retrieveEvent(sport,discipline));
	return eventList;
}

@RequestMapping(value = "/editDeletePage", method = RequestMethod.POST)
public String editDeleteOperation(ModelMap model,@RequestParam Map<String, String> record)
{
	System.out.println("...."+record);
	MapRetrieve mapData=new MapRetrieve();
	OlympicDataPojo olympicData=new OlympicDataPojo();
	olympicData=mapData.retrieveObject(record);
	System.out.println(olympicData);
	OlympicService olympicService=new OlympicService();
	model.addAttribute("host",olympicService.retrieveHost());
	model.addAttribute("countryList",olympicService.retrieveAthleteList());
	model.addAttribute("sportList",olympicService.retrieveSport());
	System.out.println("edit delete page");
	String oldathlete;
	oldathlete=record.get("edit");
	String deleteAthlete=record.get("delete");
	List<OlympicDataPojo>updatedList;

	if(oldathlete!=null && !oldathlete.isEmpty())
	{
		String newAthlete=record.get(oldathlete);
		updatedList=olympicService.updateRecord(olympicData, oldathlete, newAthlete);
	}
	else
	{
		updatedList=olympicService.updateRecord(olympicData, deleteAthlete, "delete");
	}
	model.addAttribute("displayList",updatedList);
	model.addAttribute("add", new OlympicDataPojo());
	return "UpdatePage";	
} 
@RequestMapping(value = "/searchFilter", method = RequestMethod.POST)
public String searchRecord(ModelMap model,@RequestParam Map<String, String> record,HttpServletRequest request)
{
	model.addAttribute("add", new OlympicDataPojo());
	OlympicService olympicService=new OlympicService();
	SearchList searchList=new SearchList();
	SearchFilter filterRecord=searchList.retrieveFilterObject(record);
	model.addAttribute("fullQuery",olympicService.searchFilterRecord(filterRecord));
	return "DownloadFilteredRecord";
}
@RequestMapping(value="/download", method = RequestMethod.POST)
public String downloadRecord(@RequestParam("fullQuery")List<OlympicDataPojo>filteredData)
{
	OlympicService olympicService=new OlympicService();
	olympicService.filterDisplay(filteredData);
    return "DownloadFilteredRecord";
	
}
}
