package com.dairypower.admin.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
 

@Controller
@Scope("session")
public class MasterController {
	
	
	
	@RequestMapping(value = "/addCustomer", method = RequestMethod.GET)
	public ModelAndView addCustomer(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("masters/addCustmer");
		try
		{
			 
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}

		return model;
	}
	
	@RequestMapping(value = "/addItem", method = RequestMethod.GET)
	public ModelAndView addItem(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("masters/addItem");
		try
		{
			 
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}

		return model;
	}
	
	@RequestMapping(value = "/addUser", method = RequestMethod.GET)
	public ModelAndView addUser(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("masters/addUser");
		 
		return model;
	}
	
	@RequestMapping(value = "/addCategory", method = RequestMethod.GET)
	public ModelAndView addCategory(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("masters/addCategory");
		 
		return model;
	}
	
	@RequestMapping(value = "/addVehicle", method = RequestMethod.GET)
	public ModelAndView addVehicle(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("masters/addVehicle");
		 
		return model;
	}
	
	@RequestMapping(value = "/addUom", method = RequestMethod.GET)
	public ModelAndView addUom(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("masters/addUom");
		 
		return model;
	}
	
}
