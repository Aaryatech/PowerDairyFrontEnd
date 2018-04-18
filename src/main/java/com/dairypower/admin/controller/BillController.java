package com.dairypower.admin.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.dairypower.admin.common.Constants;
import com.dairypower.admin.model.BillDetail;
import com.dairypower.admin.model.BillHeader;
import com.dairypower.admin.model.Customer;
import com.dairypower.admin.model.GetItem;
import com.dairypower.admin.model.GetPoDetail;
import com.dairypower.admin.model.Info;
import com.dairypower.admin.model.RsDetail;
import com.dairypower.admin.model.Vehicle;

@Controller
@Scope("session")
public class BillController {
	
	RestTemplate rest = new RestTemplate();
	List<BillDetail> billDetailList=null;
	
	@RequestMapping(value = "/showAllTempAndSettleBill", method = RequestMethod.GET)
	public ModelAndView showAllTempAndSettleBill(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("bill/listOfAllBill");
		try
		{
			 Date date = new Date();
			 
			 SimpleDateFormat sf = new SimpleDateFormat("dd-MM-yyyy");
			 model.addObject("toDay", sf.format(date));
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}

		return model;
	}
	
	@RequestMapping(value = "/tempBill", method = RequestMethod.GET)
	public ModelAndView purchaseBill(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("bill/tempBill");
		try
		{
			billDetailList=new ArrayList<BillDetail>();
			List<Customer> customerList = rest.getForObject(Constants.url + "/master/getAllCustomer", List.class);
			 
			model.addObject("customerList", customerList);
			List<Vehicle> vehicleList =  rest.getForObject(Constants.url + "/master/getAllVehicles", List.class);
			model.addObject("vehicleList", vehicleList);
			

			List<GetItem> itemList =  rest.getForObject(Constants.url + "/master/getAllItems", List.class);
			model.addObject("itemList", itemList); 
		}catch(Exception e)
		{
			e.printStackTrace();
		}

		return model;
	}
	@RequestMapping(value = "/getCustomer", method = RequestMethod.GET)
	public @ResponseBody Customer getCustomer(HttpServletRequest request, HttpServletResponse response) {

		Customer customer=new Customer();
		try
		{
			int custId=Integer.parseInt(request.getParameter("custId"));
			System.err.println(custId);
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String,Object>();
			map.add("custId", custId); 
			 customer = rest.postForObject(Constants.url + "/master/getCustomerById",map, Customer.class);
		}
		catch(Exception e)
		{
		e.printStackTrace();
		}
		return customer;
	}
	
@RequestMapping(value = "/getVehicle", method = RequestMethod.GET)
public @ResponseBody Vehicle getVehicle(HttpServletRequest request, HttpServletResponse response) {

	Vehicle vehicleRes=new Vehicle();
	try {
		int vehId=Integer.parseInt(request.getParameter("vehId"));

	 MultiValueMap<String, Object> map = new LinkedMultiValueMap<String,Object>();
	 map.add("vehId", vehId);
	 vehicleRes = rest.postForObject(Constants.url + "/master/getVehicleById",map, Vehicle.class);
	}
	catch(Exception e)
	{
	e.printStackTrace();
	}
	return vehicleRes;
}
@RequestMapping(value = "/getBatchList", method = RequestMethod.GET) 
public @ResponseBody List<GetPoDetail> getPoDetailList(HttpServletRequest request, HttpServletResponse response) {

	List<GetPoDetail> poDetailList=new ArrayList<>();
	try {
		int itemId=Integer.parseInt(request.getParameter("itemId"));

		 MultiValueMap<String, Object> map = new LinkedMultiValueMap<String,Object>();
		 map.add("itemId", itemId);

		poDetailList = rest.postForObject(Constants.url + "/getPoDetails",map,List.class);
	}
	catch(Exception e)
	{
	e.printStackTrace();
	}
	return poDetailList;
}
@RequestMapping(value = "/insertItemDetail", method = RequestMethod.GET)
public @ResponseBody List<BillDetail> insertItemDetail(HttpServletRequest request, HttpServletResponse response) {
	
	try {
		
	int custId=Integer.parseInt(request.getParameter("custId"));
	System.out.println("custId"+custId);
		
	int itemId=Integer.parseInt(request.getParameter("itemId"));
	System.out.println("itemId"+itemId);
	
	int billQty=Integer.parseInt(request.getParameter("qty"));
	System.out.println("billQty"+billQty);

	String batchNo=request.getParameter("batchNo");
	 MultiValueMap<String, Object> map = new LinkedMultiValueMap<String,Object>();
	 map.add("itemId", itemId);
	 map.add("custId", custId);
	RsDetail rsDetail =  rest.postForObject(Constants.url + "/getRsData",map, RsDetail.class);
	 map = new LinkedMultiValueMap<String,Object>();
	 map.add("itemId", itemId);
	GetItem getItemRes =  rest.postForObject(Constants.url + "/master/getItemById",map, GetItem.class);
	BillDetail billDetail=new BillDetail();
	billDetail.setBillDetailId(0);
	billDetail.setBillTempId(0);
	billDetail.setItemId(itemId);
	billDetail.setItemName(getItemRes.getItemName());
	billDetail.setBillQty(billQty);
	billDetail.setBatchNo(batchNo);
	billDetail.setCgstPer(getItemRes.getCgstPer());
	billDetail.setIgstPer(getItemRes.getIgstPer());
	billDetail.setSgstPer(getItemRes.getSgstPer());
	billDetail.setRate(rsDetail.getRate());
	billDetail.setReturnQty(0);
	billDetail.setDistLeakageQty(0);

	billDetailList.add(billDetail);
	}
	catch (Exception e) {
		// TODO: handle exception
	}
	return billDetailList;

}
@RequestMapping(value = "/insertTempBill", method = RequestMethod.GET)
public @ResponseBody BillHeader insertTempBill(HttpServletRequest request, HttpServletResponse response) {
	
	RestTemplate restTemplate=new RestTemplate();
	
	System.out.println("Item  Detail Before Submit "+billDetailList.toString());
	BillHeader  billHeaderRes = null;
	BillHeader billHeader=new BillHeader();
	try
	{
		int custId=Integer.parseInt(request.getParameter("custId"));
		System.out.println("custId"+custId);
		
		int vehId=Integer.parseInt(request.getParameter("vehId"));
		System.out.println("vehId"+vehId);
		
		int cratesIssued=Integer.parseInt(request.getParameter("cratesIssueQty"));
		
		int cratesOpnQty=Integer.parseInt(request.getParameter("cratesOpnQty"));
		float total=Float.parseFloat(request.getParameter("total"));
		SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
		billHeader.setBillTempId(0);
		billHeader.setBillId(0);
		billHeader.setBillDate(df.format(new Date()));
		billHeader.setCollectedAmt(0);
		billHeader.setCollectionPaymode(0);
		billHeader.setCratesClBal(0);
		billHeader.setCratesIssued(cratesIssued);
		billHeader.setCratesOpBal(cratesOpnQty);
		billHeader.setCratesReceived(0);
		billHeader.setCustId(custId);
		billHeader.setIsSettled(0);
		billHeader.setOutstandingAmt(total);
		billHeader.setRemarks("");
		billHeader.setVehId(vehId);
		billHeader.setBillDetailList(billDetailList);
		
		
		billHeaderRes=restTemplate.postForObject(Constants.url+"/saveBill",billHeader,BillHeader.class);
	
	
	}
	catch(Exception e)
	{
		e.printStackTrace();

	}
	return billHeaderRes;
	
}
	@RequestMapping(value = "/approvedTempBill/{tempId}", method = RequestMethod.GET)
	public ModelAndView approvedTempBill(@PathVariable int tempId, HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("bill/billDetail");
		try
		{
			Date date = new Date();
			 
			 SimpleDateFormat sf = new SimpleDateFormat("dd-MM-yyyy");
			 model.addObject("date", sf.format(date)); 
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
	
	@RequestMapping(value = "/creditNote/{tempId}", method = RequestMethod.GET)
	public ModelAndView creditNote(@PathVariable int tempId, HttpServletRequest request, HttpServletResponse response) {

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
