package com.dairypower.admin.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
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
import com.dairypower.admin.model.GetItem;
import com.dairypower.admin.model.GetUser;
import com.dairypower.admin.model.Info;
import com.dairypower.admin.model.Item;
import com.dairypower.admin.model.ItemCategory;
import com.dairypower.admin.model.RSHeader;
import com.dairypower.admin.model.RsDetail;
import com.dairypower.admin.model.Uom;
import com.dairypower.admin.model.User;
import com.dairypower.admin.model.UserType;
import com.dairypower.admin.model.Vehicle; 
 
 

@Controller
@Scope("session")
public class MasterController {
	
	SimpleDateFormat sf = new SimpleDateFormat("dd-MM-yyyy");
	SimpleDateFormat finalDate = new SimpleDateFormat("yyyy-MM-dd");
	RestTemplate rest = new RestTemplate();
	List<RsDetail> editRsDetailsList = new ArrayList<RsDetail>();
	
	@RequestMapping(value = "/addCustomer", method = RequestMethod.GET)
	public ModelAndView addCustomer(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("masters/addCustmer");
		try
		{
			List<CustomerType> customerTypeList = rest.getForObject(Constants.url + "/master/getAllCustType", List.class);
			List<RSHeader> rsHeaderList = rest.getForObject(Constants.url + "/master/getAllRsHeader", List.class);
			List<Vehicle> vehicleList =  rest.getForObject(Constants.url + "/master/getAllVehicles", List.class);
			model.addObject("vehicleList", vehicleList);
			
			model.addObject("customerTypeList", customerTypeList);
			model.addObject("rsHeaderList", rsHeaderList);
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}

		return model;
	}
	
	@RequestMapping(value = "/listOfCustomers", method = RequestMethod.GET)
	public ModelAndView listOfCustomers(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("masters/listOfCustomers");
		try
		{
			List<Customer> customerList = rest.getForObject(Constants.url + "/master/getAllCustomer", List.class);
		 
			model.addObject("customerList", customerList);
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}

		return model;
	}
	
	@RequestMapping(value = "/editCustomer/{custId}", method = RequestMethod.GET)
	public ModelAndView editCustomer(@PathVariable int custId, HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("masters/addCustmer");
		try
		{
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String,Object>();
			map.add("custId", custId); 
			Customer customer = rest.postForObject(Constants.url + "/master/getCustomerById",map, Customer.class);
			
			List<CustomerType> customerTypeList = rest.getForObject(Constants.url + "/master/getAllCustType", List.class);
			List<RSHeader> rsHeaderList = rest.getForObject(Constants.url + "/master/getAllRsHeader", List.class);
			List<Vehicle> vehicleList =  rest.getForObject(Constants.url + "/master/getAllVehicles", List.class);
			model.addObject("vehicleList", vehicleList);
			model.addObject("customer", customer);
			model.addObject("customerTypeList", customerTypeList);
			model.addObject("rsHeaderList", rsHeaderList);
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}

		return model;
	}
	
	@RequestMapping(value = "/deleteCustomer/{custId}", method = RequestMethod.GET)
	public String deleteCustomer(@PathVariable int custId, HttpServletRequest request, HttpServletResponse response) {

		 
		try
		{
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String,Object>();
			map.add("custId", custId); 
			Info info = rest.postForObject(Constants.url + "/master/deleteCustomer",map, Info.class);
			
			 System.out.println("Info " + info);
		}catch(Exception e)
		{
			e.printStackTrace();
		}

		return "redirect:/listOfCustomers";
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
			String panNo = request.getParameter("panNo");
			String fssaiNo = request.getParameter("fssaiNo");
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
			insert.setFssaiNo(fssaiNo);
			insert.setPanNo(panNo);
			insert.setCustGstNo(gstnNo);
			insert.setCustReference(custRef);
			insert.setRefMobNo(refMo);
			insert.setRsHeaderId(rsHeaderId);
			insert.setCratesOpBal(openigCap);
			insert.setCratesCap(creditCap);
			insert.setVehId(vehId); 
			Info info = rest.postForObject(Constants.url + "/master/saveCustomer",insert,
					Info.class);
			
			System.out.println("info " +  info); 
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}

		return "redirect:/listOfCustomers";
	}
	@RequestMapping(value = "/insertItem", method = RequestMethod.POST)
	public String insertItem(HttpServletRequest request, HttpServletResponse response) {

		 
		try
		{
			
			String itemId = request.getParameter("itemId");
			String itemName = request.getParameter("itemName"); 
			int itemCatId =Integer.parseInt(request.getParameter("catId"));
			String  itemCode = request.getParameter("itemCode");
			String hsnCode = request.getParameter("hsnCode");
			int minQty = Integer.parseInt(request.getParameter("min_qty"));
			int maxQty = Integer.parseInt(request.getParameter("max_qty"));
			int hubExpDays = Integer.parseInt( request.getParameter("hubExprDays"));
			int retailExpDays =Integer.parseInt(request.getParameter("retailExprDays"));
			float sgstPer = Float.parseFloat(request.getParameter("sgst"));
			float cgstPer = Float.parseFloat(request.getParameter("cgst"));
			float igstPer = Float.parseFloat(request.getParameter("igst"));
			
			int uomId =Integer.parseInt(request.getParameter("uomId"));
			float purchaseRate =Float.parseFloat(request.getParameter("purchaseRate"));
		
			
			Item item = new Item();
			if(itemId==null || itemId=="")
				item.setItemId(0);
			else
				item.setItemId(Integer.parseInt(itemId));

			item.setItemCatId(itemCatId);
			item.setItemName(itemName);
			item.setUomId(uomId);
			item.setPurchaseRate(purchaseRate);
			item.setMinQty(minQty);
			item.setMaxQty(maxQty);
			item.setHubExpDays(hubExpDays);
			item.setRetailExpDays(retailExpDays);
			item.setHsnCode(hsnCode);
			item.setSgstPer(sgstPer);
			item.setIgstPer(igstPer);
			item.setCgstPer(cgstPer);
			item.setIsUsed(0);
			item.setItemCode(itemCode);
			
			Info info = rest.postForObject(Constants.url + "/master/saveItem",item,
					Info.class);
			
			System.out.println("info " +  info); 
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}

		return "redirect:/showItemList";
	}
	@RequestMapping(value = "/addItem", method = RequestMethod.GET)
	public ModelAndView addItem(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("masters/addItem");
		try
		{
			List<ItemCategory> itemCategoryList =  rest.getForObject(Constants.url + "/master/getAllCategories", List.class);
			model.addObject("itemCategoryList", itemCategoryList);
			List<Uom> uomlist =  rest.getForObject(Constants.url + "/master/getAllUom", List.class);
			model.addObject("uomList", uomlist);

			
		}catch(Exception e)
		{
			e.printStackTrace();
		}

		return model;
	}
	@RequestMapping(value = "/showItemList", method = RequestMethod.GET)
	public ModelAndView showItemList(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("masters/itemList");
		try
		{

			List<GetItem> itemList =  rest.getForObject(Constants.url + "/master/getAllItems", List.class);
			model.addObject("itemList", itemList); 
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}

		return model;
	}
	@RequestMapping(value = "/addUser", method = RequestMethod.GET)
	public ModelAndView addUser(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("masters/addUser");
		 try
		 {
				List<GetUser> userlist =  rest.getForObject(Constants.url + "/master/getAllUsers", List.class);
                System.err.println(userlist.toString());
				model.addObject("userList", userlist); 
				List<UserType> userTypeList =  rest.getForObject(Constants.url + "/master/getAllUserType", List.class);
				model.addObject("userTypeList", userTypeList); 
		 }catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}
	
	@RequestMapping(value = "/addCategory", method = RequestMethod.GET)
	public ModelAndView addCategory(HttpServletRequest request, HttpServletResponse response) {
		
		
		ModelAndView model = new ModelAndView("masters/addCategory");
		try
		{
			List<ItemCategory> itemCategoryList =  rest.getForObject(Constants.url + "/master/getAllCategories", List.class);
			model.addObject("itemCategoryList", itemCategoryList);
		}catch(Exception e)
		{
			e.printStackTrace();
		}

		return model;
	}
	@RequestMapping(value = "/editCategory/{catId}", method = RequestMethod.GET)
	public ModelAndView editCategory(@PathVariable int catId,HttpServletRequest request, HttpServletResponse response) {
		
		
		ModelAndView model = new ModelAndView("masters/addCategory");
		try
		{
			 MultiValueMap<String, Object> map = new LinkedMultiValueMap<String,Object>();
			 map.add("catId", catId);
			ItemCategory catRes =  rest.postForObject(Constants.url + "/master/getCategoryById",map, ItemCategory.class);
			model.addObject("category", catRes);
			
			List<ItemCategory> itemCategoryList =  rest.getForObject(Constants.url + "/master/getAllCategories", List.class);
			model.addObject("itemCategoryList", itemCategoryList);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return model;
	}
	@RequestMapping(value = "/editUser/{userId}", method = RequestMethod.GET)
	public ModelAndView editUser(@PathVariable int userId,HttpServletRequest request, HttpServletResponse response) {
		
		
		ModelAndView model = new ModelAndView("masters/addUser");
		try
		{
			 MultiValueMap<String, Object> map = new LinkedMultiValueMap<String,Object>();
			 map.add("userId", userId);
			User userRes =  rest.postForObject(Constants.url + "/master/getUserById",map, User.class);
			model.addObject("user", userRes);
			List<GetUser> userList =  rest.getForObject(Constants.url + "/master/getAllUsers", List.class);

			model.addObject("userList", userList); 
			List<UserType> userTypeList =  rest.getForObject(Constants.url + "/master/getAllUserType", List.class);
			model.addObject("userTypeList", userTypeList); 
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return model;
	}
	@RequestMapping(value = "/editItem/{itemId}", method = RequestMethod.GET)
	public ModelAndView editItem(@PathVariable int itemId,HttpServletRequest request, HttpServletResponse response) {
		
		
		ModelAndView model = new ModelAndView("masters/addItem");
		try
		{
			 MultiValueMap<String, Object> map = new LinkedMultiValueMap<String,Object>();
			 map.add("itemId", itemId);
			GetItem getItemRes =  rest.postForObject(Constants.url + "/master/getItemById",map, GetItem.class);
			model.addObject("item", getItemRes);
			List<ItemCategory> itemCategoryList =  rest.getForObject(Constants.url + "/master/getAllCategories", List.class);
			model.addObject("itemCategoryList", itemCategoryList);
			List<Uom> uomlist =  rest.getForObject(Constants.url + "/master/getAllUom", List.class);
			model.addObject("uomList", uomlist);

		}catch(Exception e)
		{
			e.printStackTrace();
		}
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
	@RequestMapping(value = "/deleteItem/{itemId}", method = RequestMethod.GET)
	public String deleteItem(@PathVariable int itemId, HttpServletRequest request, HttpServletResponse response) {

		 
		try
		{
			 MultiValueMap<String, Object> map = new LinkedMultiValueMap<String,Object>();
			 map.add("itemId", itemId);
			Info info = rest.postForObject(Constants.url + "/master/deleteItem",map,
					Info.class);
			
			System.out.println("info " +  info); 
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}

		return "redirect:/showItemList";
	}
	@RequestMapping(value = "/deleteUser/{userId}", method = RequestMethod.GET)
	public String deleteUser(@PathVariable int userId, HttpServletRequest request, HttpServletResponse response) {

		 
		try
		{
			 MultiValueMap<String, Object> map = new LinkedMultiValueMap<String,Object>();
			 map.add("userId", userId);
			Info info = rest.postForObject(Constants.url + "/master/deleteUser",map,
					Info.class);
			
			System.out.println("info " +  info); 
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}

		return "redirect:/addUser";
	}
	
	
	@RequestMapping(value = "/addVehicle", method = RequestMethod.GET)
	public ModelAndView addVehicle(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("masters/addVehicle");
		try
		{
			List<Vehicle> vehicleList =  rest.getForObject(Constants.url + "/master/getAllVehicles", List.class);
			model.addObject("vehicleList", vehicleList);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		 
		return model;
	}
	@RequestMapping(value = "/editVehicle/{vehId}", method = RequestMethod.GET)
	public ModelAndView editVehicle(@PathVariable int vehId,HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("masters/addVehicle");
		try
		{
			SimpleDateFormat sf = new SimpleDateFormat("dd-MM-yyyy");
			SimpleDateFormat finalDate = new SimpleDateFormat("yyyy-MM-dd");
			 MultiValueMap<String, Object> map = new LinkedMultiValueMap<String,Object>();
			 map.add("vehId", vehId);
			 Vehicle vehicleRes = rest.postForObject(Constants.url + "/master/getVehicleById",map, Vehicle.class);
			List<Vehicle> vehicleList =  rest.getForObject(Constants.url + "/master/getAllVehicles", List.class);
			model.addObject("vehicleList", vehicleList);
			vehicleRes.setVehOpKmsDate(sf.format(finalDate.parse(vehicleRes.getVehOpKmsDate())));
			model.addObject("vehicle",vehicleRes);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		 
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
			
			
			
			Date date = sf.parse(openingKmDate);
			
			Vehicle insert = new Vehicle();
			if(vehId==null || vehId=="")
				insert.setVehId(0);
			else
				insert.setVehId(Integer.parseInt(vehId));
			insert.setVehName(vehicleName);
			insert.setVehType(vehType);
			insert.setVehOpKms(vehOpeningKm);
			insert.setVehOpKmsDate(finalDate.format(date)); 
			Info info = rest.postForObject(Constants.url + "/master/saveVehicle",insert,
					Info.class);
			
			System.out.println("info " +  info); 
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}

		return "redirect:/addVehicle";
	}
	@RequestMapping(value = "/insertUser", method = RequestMethod.POST)
	public String insertUser(HttpServletRequest request, HttpServletResponse response) {

		 
		try
		{
			String userId = request.getParameter("userId");
			String userName = request.getParameter("userName");  
			String password = request.getParameter("password"); 
			String userMobNo =request.getParameter("mob");
			int userTypeId = Integer.parseInt(request.getParameter("typeId"));
			
			User user = new User();
			if(userId==null || userId=="")
				user.setUserId(0);
			else
				user.setUserId(Integer.parseInt(userId));
		    user.setUserMobNo(userMobNo);
		    user.setUserName(userName); 
		    user.setUserTypeId(userTypeId);
		    user.setPassword(password);
		    user.setIsUsed(0);
		    
			Info info = rest.postForObject(Constants.url + "/master/saveUser",user,
					Info.class);
			
			System.out.println("info " +  info); 
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}

		return "redirect:/addUser";
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
		try
		{
			List<Uom> uomlist =  rest.getForObject(Constants.url + "/master/getAllUom", List.class);
			model.addObject("uomlist", uomlist);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		 
		return model;
	}
	@RequestMapping(value = "/editUom/{uomId}", method = RequestMethod.GET)
	public ModelAndView editUom(@PathVariable int uomId,HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("masters/addUom");
		try
		{
			 MultiValueMap<String, Object> map = new LinkedMultiValueMap<String,Object>();
			 map.add("uomId", uomId);
			Uom uomRes =  rest.postForObject(Constants.url + "/master/getUomById",map, Uom.class);
			model.addObject("uom", uomRes);
			List<Uom> uomlist =  rest.getForObject(Constants.url + "/master/getAllUom", List.class);
			model.addObject("uomlist", uomlist);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		 
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
	@RequestMapping(value = "/deleteRs/{rsHeaderId}", method = RequestMethod.GET)
	public String deleteRs(@PathVariable int rsHeaderId, HttpServletRequest request, HttpServletResponse response) {

		 
		try
		{
			 MultiValueMap<String, Object> map = new LinkedMultiValueMap<String,Object>();
			 map.add("rsHeaderId", rsHeaderId);
			Info info = rest.postForObject(Constants.url + "/master/deleteRsHeader",map,
					Info.class);
			
			System.out.println("info " +  info); 
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}

		return "redirect:/showRateList";
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
	@RequestMapping(value = "/addRateStructure", method = RequestMethod.GET)
	public ModelAndView addRateStructure(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("masters/addRs");
		try
		{	List<GetItem> itemList =  rest.getForObject(Constants.url + "/master/getAllItems", List.class);
	       	model.addObject("itemList", itemList); 
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		 
		return model;
	}
	@RequestMapping(value = "/showRateList", method = RequestMethod.GET)
	public ModelAndView showRateList(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("masters/rateStructureList");
		try
		{	
			List<RSHeader> rsList =  rest.getForObject(Constants.url + "/master/getAllRsHeader", List.class);
	       	model.addObject("rsList", rsList); 
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		 
		return model;
	}
	@RequestMapping(value = "/editRs/{rsHeaderId}", method = RequestMethod.GET)
	public ModelAndView editRs(@PathVariable int rsHeaderId,HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("masters/editRs");
		try
		{
			 MultiValueMap<String, Object> map = new LinkedMultiValueMap<String,Object>();
			 map.add("rsHeaderId", rsHeaderId);
			  RSHeader rsHeaderRes =  rest.postForObject(Constants.url + "/master/getRsHeader",map, RSHeader.class);
			  
			  RsDetail[] rsDetails  =  rest.postForObject(Constants.url + "/master/getAllRsDetails",map, RsDetail[].class);
			  
			  editRsDetailsList = new ArrayList<RsDetail>(Arrays.asList(rsDetails));
			 
			 
			 GetItem[] getItem =  rest.getForObject(Constants.url + "/master/getAllItems", GetItem[].class); 
			 List<GetItem> itemList = new ArrayList<GetItem>(Arrays.asList(getItem));
			 
		     
		     
		     for(int j = 0; j<itemList.size();j++)
	    	 { 
		    	 int flag = 0;
			     for(int i = 0 ; i<editRsDetailsList.size();i++)
			     {
			    	 if(itemList.get(j).getItemId()==editRsDetailsList.get(i).getItemId())
			    	 {
			    		 flag=1;
			    		 break;
			    	 }
		    	 }
			     if(flag==0)
			     {
			    	 RsDetail rsDetail = new RsDetail();
			    	 rsDetail.setItemId(itemList.get(j).getItemId());
			    	 rsDetail.setRsHeaderId(editRsDetailsList.get(0).getRsHeaderId());
			    	 editRsDetailsList.add(rsDetail);
			     }
		     }
		     
		     model.addObject("rsDetailsList", editRsDetailsList);
		     model.addObject("itemList", itemList);
		     model.addObject("rsHeaderRes", rsHeaderRes);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		 
		return model;
	}
	@RequestMapping(value = "/saveRs", method = RequestMethod.POST)
	public String saveRs(HttpServletRequest request, HttpServletResponse response) {

		try
		{
			String rsName = request.getParameter("rsName");
			
		    GetItem[] itemList =  rest.getForObject(Constants.url + "/master/getAllItems", GetItem[].class);
		    ArrayList<GetItem> allItems=new ArrayList<GetItem>(Arrays.asList(itemList));
		
		    RSHeader rsHeader=new RSHeader();
			rsHeader.setRsHeaderId(0);
		    rsHeader.setRsName(rsName);
		    rsHeader.setIsUsed(0);
		    List<RsDetail> rsDetails=new ArrayList<RsDetail>();
		    for(GetItem item:allItems)
		    {
				float rate = Float.parseFloat(request.getParameter("rate"+item.getItemId()));
		    	RsDetail rsDetailRes=new RsDetail();
		    	rsDetailRes.setRsDetailId(0);
		    	rsDetailRes.setRsHeaderId(0);
		    	rsDetailRes.setItemId(item.getItemId());
		    	rsDetailRes.setRate(rate);
		    	rsDetails.add(rsDetailRes);
		    }
		System.err.println(rsDetails.toString());
		rsHeader.setRsDetailList(rsDetails);
		RSHeader rsHeader2 = rest.postForObject(Constants.url + "/master/saveRs",rsHeader,
				RSHeader.class);
			
			System.out.println("info " +  rsHeader2); 
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}

		return "redirect:/showRateList";
	}
	@RequestMapping(value = "/saveRsDetails", method = RequestMethod.POST)
	public String saveRsDetails(HttpServletRequest request, HttpServletResponse response) {

		try
		{
			
			int rsHeaderId = Integer.parseInt(request.getParameter("rsHeaderId"));

			 /*MultiValueMap<String, Object> map = new LinkedMultiValueMap<String,Object>();
			 map.add("rsHeaderId", rsHeaderId);
			 
			 RsDetail[] rsDetailsList =  rest.postForObject(Constants.url + "/master/getAllRsDetails",map, RsDetail[].class);
			 ArrayList<RsDetail> rsDetails=new ArrayList<RsDetail>(Arrays.asList(rsDetailsList));
			 System.err.println("list:"+rsDetails.toString());*/
					 
		    for(int i=0;i<editRsDetailsList.size();i++)
		    {
				float rate = Float.parseFloat(request.getParameter("rate"+editRsDetailsList.get(i).getItemId()));
				editRsDetailsList.get(i).setRate(rate);
		    }
		   Info info = rest.postForObject(Constants.url + "/master/saveRsDetails",editRsDetailsList,Info.class);
			
			System.out.println("info " +  info.toString()); 
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}

		return "redirect:/showRateList";
	}
	
}
