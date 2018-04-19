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
public class ReturnManufactureController {
	
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

}
