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
public class PurchaseController {
	
	@RequestMapping(value = "/purchaseBill", method = RequestMethod.GET)
	public ModelAndView purchaseBill(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("purchase/purchaseBill");
		try
		{
			 
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}

		return model;
	}
	
	@RequestMapping(value = "/purchaseHistory", method = RequestMethod.GET)
	public ModelAndView purchaseHistory(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("purchase/purchaseHistory");
		try
		{
			 
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}

		return model;
	}

}
