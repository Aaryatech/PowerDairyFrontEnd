package com.dairypower.admin.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Scope("session")
public class BillController {
	
	@RequestMapping(value = "/tempBill", method = RequestMethod.GET)
	public ModelAndView purchaseBill(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("bill/tempBill");
		try
		{
			 
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}

		return model;
	}
	
	@RequestMapping(value = "/approvedTempBill", method = RequestMethod.GET)
	public ModelAndView approvedTempBill(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("bill/billDetail");
		try
		{
			 
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}

		return model;
	}
	
	@RequestMapping(value = "/returnManf", method = RequestMethod.GET)
	public ModelAndView returnManf(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("bill/returnManf");
		try
		{
			 
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}

		return model;
	}
	
	@RequestMapping(value = "/creditNote", method = RequestMethod.GET)
	public ModelAndView creditNote(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("bill/creditNote");
		try
		{
			 
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}

		return model;
	}
	
	@RequestMapping(value = "/vehInfo", method = RequestMethod.GET)
	public ModelAndView vehInfo(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("bill/vehInfo");
		try
		{
			 
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}

		return model;
	}

}
