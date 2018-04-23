package com.dairypower.admin;

import java.io.IOException;
import java.text.DateFormat; 
import java.util.Date; 
import java.util.Locale; 

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap; 
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.dairypower.admin.common.Constants;
import com.dairypower.admin.model.LoginResponse; 
 
 

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "login";
	}
	
	@RequestMapping("/loginProcess")
	public ModelAndView helloWorld(HttpServletRequest request, HttpServletResponse res) throws IOException {

		String name = request.getParameter("username");
		String password = request.getParameter("password");

		ModelAndView mav = new ModelAndView("login");

		res.setContentType("text/html"); 
		try {
			System.out.println("Login Process " + name);
			System.out.println("password " + password);

			if (name.equalsIgnoreCase("") || password.equalsIgnoreCase("") || name == null || password == null) {

				mav = new ModelAndView("login");
			} else {

				  
				 
				RestTemplate rest = new RestTemplate();
				MultiValueMap<String, Object> map = new LinkedMultiValueMap<String,Object>();
				map.add("userName", name);
				map.add("pass", password);
				LoginResponse loginResponse = rest.postForObject(Constants.url + "/master/loginResponse",map,
						LoginResponse.class);
				System.out.println("loginResponse" + loginResponse);
				 
				if (loginResponse.isError()==false) 
				{ 
					mav = new ModelAndView("home");
					HttpSession session = request.getSession();
					session.setAttribute("UserDetail", loginResponse); 
					//HttpSession session = request.getSession(); 
					LoginResponse login = (LoginResponse) session.getAttribute("UserDetail");
					System.out.println("userId " + login.getUser().getUserId());
					mav.addObject("login", login.getUser());
					 
				} else {

					
					mav = new ModelAndView("login"); 
					System.out.println("Invalid login credentials");

				}

			}
		} catch (Exception e) {
			System.out.println("HomeController Login API Excep:  " + e.getMessage());
		}

		return mav;

	}
	
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public ModelAndView home(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView model = new ModelAndView("home");
		logger.info("/ request mapping.");
		HttpSession session = request.getSession(); 
		LoginResponse login = (LoginResponse) session.getAttribute("UserDetail");
		System.out.println("userId " + login.getUser().getUserId());
		model.addObject("login", login.getUser());
		return model;

	}
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpSession session) {
		System.out.println("User Logout");

		session.invalidate();
		return "redirect:/";
	}
	
	/*@ExceptionHandler(LoginFailException.class)
	public String redirectToLogin() {
		System.out.println("HomeController Login Fail Excep:");

		return "login";
	}*/
	
	@RequestMapping(value = "/sessionTimeOut" , method = RequestMethod.GET)
	public ModelAndView displayLoginAgain(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("login");

		logger.info("/sessionTimeOut request mapping.");

		model.addObject("loginResponseMessage", "Session timeout ! Please login again . . .");

		return model;

	}
	
	/*@RequestMapping(value = "/loginProcess", method = RequestMethod.POST)
	public ModelAndView displayHomePage(HttpSession ses, HttpServletRequest request, HttpServletResponse response)
			throws ParseException {

		logger.info("/loginProcess request mapping.");

		ModelAndView model = new ModelAndView("login");

		HttpSession session = request.getSession();

		String frCode = request.getParameter("username");
		String frPassword = request.getParameter("password");

		// fr login
	 
		if(frCode.equals("Tester") && frPassword.equals("1234")) {

		  
		 
			 

			model = new ModelAndView("home");
			 
		}
		else {

			model.addObject("message", "login Failed");

		}
		return model;

	}*/
	
}
