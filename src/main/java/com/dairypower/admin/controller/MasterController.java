package com.dairypower.admin.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.dairypower.admin.common.Constants;
import com.dairypower.admin.model.Customer;
import com.dairypower.admin.model.CustomerType;
import com.dairypower.admin.model.Info;
import com.dairypower.admin.model.ItemCategory;
import com.dairypower.admin.model.RSHeader;
import com.dairypower.admin.model.Uom;
import com.dairypower.admin.model.Vehicle; 
 
 

@Controller
@Scope("session")
public class MasterController {
	
	
	RestTemplate rest = new RestTemplate();
	
	@RequestMapping(value = "/addCustomer", method = RequestMethod.GET)
	public ModelAndView addCustomer(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("masters/addCustmer");
		try
		{
			List<CustomerType> customerTypeList = rest.getForObject(Constants.url + "/master/getAllCustType", List.class);
			List<RSHeader> rsHeaderList = rest.getForObject(Constants.url + "/master/getAllRsHeader", List.class);
			
			model.addObject("customerTypeList", customerTypeList);
			model.addObject("rsHeaderList", rsHeaderList);
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}

		return model;
	}
	
	@RequestMapping(value = "/insertCustomer", method = RequestMethod.POST)
	public String insertCustomer(HttpServletRequest request, HttpServletResponse response) {

		 
		try
		{
			String custId = request.getParameter("custId");
			String custName = request.getParameter("custName"); 
			String custAdd = request.getParameter("custAdd");
			int  custType = Integer.parseInt(request.getParameter("custType"));
			String mob = request.getParameter("mob");
			String landlineNo = request.getParameter("landlineNo");
			String email = request.getParameter("email");
			String root = request.getParameter("root");
			String accNo = request.getParameter("accNo");
			String ifsc = request.getParameter("ifsc");
			String bankName = request.getParameter("bankName");
			String gstnNo = request.getParameter("gstnNo");
			float cap = Float.parseFloat(request.getParameter("cap")); 
			String custRef = request.getParameter("custRef");
			String refMo = request.getParameter("refMo");
			int  openigCap = Integer.parseInt(request.getParameter("openigCap")); 
			int  creditCap = Integer.parseInt(request.getParameter("creditCap"));  
			int  rsHeaderId = Integer.parseInt(request.getParameter("rsHeaderId"));
			int  vehId = Integer.parseInt(request.getParameter("vehId")); 
			 
			
			
			Customer insert = new Customer();
			if(custId==null || custId=="")
				insert.setCustId(0);
			else
				insert.setCustId(Integer.parseInt(custId));
			insert.setCustName(custName);
			insert.setCustType(custType);
			insert.setCustAddress(custAdd);
			insert.setCustLandlineNo(landlineNo);
			insert.setCustMobNo(mob);
			insert.setCustEmailId(email);
			insert.setCustRoot(root);
			insert.setCustBankAccNo(accNo);
			insert.setCustBankIfsc(ifsc);
			insert.setCustBankName(bankName);
			insert.setCustCap(cap);
			insert.setCustGstNo(gstnNo);
			insert.setCustReference(custRef);
			insert.setRefMobNo(refMo);
			insert.setRsHeaderId(rsHeaderId);
			insert.setCratesOpBal(openigCap);
			insert.setCratesCap(creditCap);
			insert.setVehId(vehId); 
			Info info = rest.postForObject(Constants.url + "postSuppllier",insert,
					Info.class);
			
			System.out.println("info " +  info); 
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}

		return "redirect:/addCustomer";
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
		List<ItemCategory> itemCategoryList =  rest.getForObject(Constants.url + "/master/getAllCategories", List.class);
		model.addObject("itemCategoryList", itemCategoryList);
		 
		return model;
	}
	
	@RequestMapping(value = "/insertCategory", method = RequestMethod.POST)
	public String insertCategory(HttpServletRequest request, HttpServletResponse response) {

		 
		try
		{
			String catId = request.getParameter("catId");
			String catName = request.getParameter("catName"); 
			  
			ItemCategory insert = new ItemCategory();
			if(catId==null || catId=="")
				insert.setCatId(0);
			else
				insert.setCatId(Integer.parseInt(catId));
			insert.setCatName(catName); 
			
			Info info = rest.postForObject(Constants.url + "/master/saveItemCategory",insert,
					Info.class);
			
			System.out.println("info " +  info); 
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}

		return "redirect:/addCategory";
	}
	
	@RequestMapping(value = "/deleteCategory/{catId}", method = RequestMethod.GET)
	public String deleteCategory(@PathVariable int catId, HttpServletRequest request, HttpServletResponse response) {

		 
		try
		{
			 MultiValueMap<String, Object> map = new LinkedMultiValueMap<String,Object>();
			 map.add("catId", catId);
			Info info = rest.postForObject(Constants.url + "/master/deleteItemCategory",map,
					Info.class);
			
			System.out.println("info " +  info); 
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}

		return "redirect:/addCategory";
	}
	
	
	@RequestMapping(value = "/addVehicle", method = RequestMethod.GET)
	public ModelAndView addVehicle(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("masters/addVehicle");
		
		List<Vehicle> vehicleList =  rest.getForObject(Constants.url + "/master/getAllVehicles", List.class);
		model.addObject("vehicleList", vehicleList);
		 
		return model;
	}
	
	@RequestMapping(value = "/insertVehicle", method = RequestMethod.POST)
	public String insertVehicle(HttpServletRequest request, HttpServletResponse response) {

		 
		try
		{
			String vehId = request.getParameter("vehId");
			String vehicleName = request.getParameter("vehicleName");  
			int vehType = Integer.parseInt(request.getParameter("vehType"));
			int vehOpeningKm = Integer.parseInt(request.getParameter("vehOpeningKm"));
			String openingKmDate = request.getParameter("openingKmDate");
			
			Vehicle insert = new Vehicle();
			if(vehId==null || vehId=="")
				insert.setVehId(0);
			else
				insert.setVehId(Integer.parseInt(vehId));
			insert.setVehName(vehicleName);
			insert.setVehType(vehType);
			insert.setVehOpKms(vehOpeningKm);
			insert.setVehOpKmsDate(openingKmDate); 
			Info info = rest.postForObject(Constants.url + "/master/saveVehicle",insert,
					Info.class);
			
			System.out.println("info " +  info); 
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}

		return "redirect:/addVehicle";
	}
	
	@RequestMapping(value = "/deleteVehicle/{vehId}", method = RequestMethod.GET)
	public String deleteVehicle(@PathVariable int vehId, HttpServletRequest request, HttpServletResponse response) {

		 
		try
		{
			 MultiValueMap<String, Object> map = new LinkedMultiValueMap<String,Object>();
			 map.add("vehId", vehId);
			Info info = rest.postForObject(Constants.url + "/master/deleteVehicle",map,
					Info.class);
			
			System.out.println("info " +  info); 
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}

		return "redirect:/addVehicle";
	}
	
	@RequestMapping(value = "/addUom", method = RequestMethod.GET)
	public ModelAndView addUom(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("masters/addUom");
		
		List<Uom> uomlist =  rest.getForObject(Constants.url + "/master/getAllUom", List.class);
		model.addObject("uomlist", uomlist);
		 
		return model;
	}
	
	@RequestMapping(value = "/deleteUom/{uomId}", method = RequestMethod.GET)
	public String deleteUom(@PathVariable int uomId, HttpServletRequest request, HttpServletResponse response) {

		 
		try
		{
			 MultiValueMap<String, Object> map = new LinkedMultiValueMap<String,Object>();
			 map.add("uomId", uomId);
			Info info = rest.postForObject(Constants.url + "/master/deleteUom",map,
					Info.class);
			
			System.out.println("info " +  info); 
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}

		return "redirect:/addUom";
	}
	
	@RequestMapping(value = "/insertUom", method = RequestMethod.POST)
	public String insertUom(HttpServletRequest request, HttpServletResponse response) {

		 
		try
		{
			String uomId = request.getParameter("uomId");
			String uomName = request.getParameter("uomName"); 
			String uomDesc = request.getParameter("uomDesc"); 
			 
			
			
			Uom insert = new Uom();
			if(uomId==null || uomId=="")
				insert.setUomId(0);
			else
				insert.setUomId(Integer.parseInt(uomId));
			insert.setUomName(uomName);
			insert.setUomDescription(uomDesc); 
			Info info = rest.postForObject(Constants.url + "/master/saveItemUom",insert,
					Info.class);
			
			System.out.println("info " +  info); 
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}

		return "redirect:/addUom";
	}
	
}
