package com.dairypower.admin.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.dairypower.admin.common.Constants;
import com.dairypower.admin.common.DateConvertor;
import com.dairypower.admin.model.GetCratesStock;
import com.dairypower.admin.model.GetCurrentStock;
import com.dairypower.admin.model.GetItem;
import com.dairypower.admin.model.GetMfgReturn;
import com.dairypower.admin.model.GetPoDetail;
import com.dairypower.admin.model.GetPoHeader;
import com.dairypower.admin.model.Info;
import com.dairypower.admin.model.MfgReturn;
import com.dairypower.admin.model.PoDetail;
import com.dairypower.admin.model.StockHeader;
import com.dairypower.admin.model.TVehicle;
import com.dairypower.admin.model.Vehicle;

@Controller
@Scope("session")
public class ReturnManufactureController {
	
	
	RestTemplate rest = new RestTemplate();
	List<PoDetail> getPoDetaillist = new ArrayList<>();
	
	@RequestMapping(value = "/returnManf", method = RequestMethod.GET)
	public ModelAndView returnManf(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("bill/returnManf");
		try
		{
			SimpleDateFormat sf = new SimpleDateFormat("dd-MM-yyyy");
			Date today = new Date();
			model.addObject("today", sf.format(today));
			
			StockHeader stockHeader = rest.getForObject(Constants.url + "getStock",
					StockHeader.class); 
			 MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			 map.add("date",DateConvertor.convertToYMD(stockHeader.getDate()));
			 
			  GetCratesStock getCratesStock = rest.postForObject(Constants.url + "getCratesStock",map,
					  GetCratesStock.class); 
			  model.addObject("stockDate", stockHeader.getDate());
			  int totalCrates = getCratesStock.getCratesOpQty()+getCratesStock.getCratesReceivedQtyBypurchase()-getCratesStock.getCratesIssued()+
					  getCratesStock.getCratesReceivedBycustomer()-getCratesStock.getCratesReturnQtyTomfg();
			  model.addObject("crateStock", totalCrates);
			
			List<GetItem> itemList = new ArrayList<GetItem>();
			GetItem[] Item =  rest.getForObject(Constants.url + "/master/getAllItems", GetItem[].class);
			itemList = new ArrayList<GetItem>(Arrays.asList(Item));
			model.addObject("itemList", itemList);
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}

		return model;
	}
	
	@RequestMapping(value = "/getBatchListByitemId", method = RequestMethod.GET)
	@ResponseBody
	public List<PoDetail> getBatchListByitemId(HttpServletRequest request, HttpServletResponse response) {

		  getPoDetaillist = new ArrayList<>();
		try
		{
			int itemId = Integer.parseInt(request.getParameter("itemId"));
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String,Object>();
			map.add("itemId", itemId);
			PoDetail[] getPoDetail =  rest.postForObject(Constants.url + "/getBatchListByitemId",map, PoDetail[].class);
			   getPoDetaillist = new ArrayList<PoDetail>(Arrays.asList(getPoDetail));
			 
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}

		return getPoDetaillist;
	}
	
	@RequestMapping(value = "/checkBalance", method = RequestMethod.GET)
	@ResponseBody
	public int checkBalance(HttpServletRequest request, HttpServletResponse response) {

		int flag = 0;
		try
		{
			int detailNo = Integer.parseInt(request.getParameter("batchNo"));
			int qty = Integer.parseInt(request.getParameter("qty"));
			
			for(int i = 0 ; i<getPoDetaillist.size();i++)
			{
				if(detailNo==getPoDetaillist.get(i).getPoDetailId() && qty<=getPoDetaillist.get(i).getBalance())
				{
					flag=1;
					break;
				}
			}
			
			System.out.println("flag " +  flag);
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}

		return flag;
	}
	
	@RequestMapping(value = "/submitReturnManufacture", method = RequestMethod.POST)
	public String submitReturnManufacture(HttpServletRequest request, HttpServletResponse response) {

		 
	 
		try {
			SimpleDateFormat sf = new SimpleDateFormat("dd-MM-yyyy");
			SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date = new Date();
			PoDetail  update = new PoDetail();
			
			
			int detailNo = Integer.parseInt(request.getParameter("batchNo"));
			int qty = Integer.parseInt(request.getParameter("qty"));
			int cratesQty = Integer.parseInt(request.getParameter("cratesQty"));
			int itemId = Integer.parseInt(request.getParameter("itemId"));
			String remark = request.getParameter("remark");
			
			MfgReturn mfgReturn = new MfgReturn();
			mfgReturn.setItemId(itemId);
			mfgReturn.setReturnQty(qty);
			mfgReturn.setCratesReturnQty(cratesQty);
			mfgReturn.setRemark(remark);
			mfgReturn.setDate(sf.format(date));
			mfgReturn.setDatetime(time.format(date));
			mfgReturn.setEntryBy(1);
			for(int i = 0 ; i<getPoDetaillist.size();i++)
			{
				if(detailNo==getPoDetaillist.get(i).getPoDetailId())
				{
					mfgReturn.setBatchId(getPoDetaillist.get(i).getBatchNo());
					update = getPoDetaillist.get(i);
					update.setBalance(getPoDetaillist.get(i).getBalance()-qty);
					break;
				}
			}
			
				 
				 Info info = rest.postForObject(Constants.url + "/master/saveMfgReturn",mfgReturn,
						 Info.class); 
				 
				 if(info.isError()==false)
				 {
					 PoDetail res = rest.postForObject(Constants.url + "updatePoDetail",update,
							 PoDetail.class); 
					 System.out.println("res " + res);
				 }
			 
			 
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		

		return "redirect:/returnManf";
	}
	
	@RequestMapping(value = "/manufactureReturnHistory", method = RequestMethod.GET)
	public ModelAndView purchaseHistory(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("bill/manufactureReturnHistory");
		try
		{
			 Date date = new Date(); 
			 SimpleDateFormat show = new SimpleDateFormat("dd-MM-yyyy");
			 SimpleDateFormat consume = new SimpleDateFormat("yyyy-MM-dd");
			 
			 MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
			 map.add("fromDate",consume.format(date));
			 map.add("toDate",consume.format(date));
			 List<GetMfgReturn>  getMfgReturnList =  rest.postForObject(Constants.url + "/mfgReturnRecordBetweenDate",map, List .class);
			 model.addObject("fromDate", show.format(date));
			 model.addObject("toDate", show.format(date));
			 model.addObject("getMfgReturnList", getMfgReturnList);
			 
		}catch(Exception e)
		{
			e.printStackTrace();
		}

		return model;
	}
	
	@RequestMapping(value = "/getMfgReturnRecordBetweenDate", method = RequestMethod.GET)
	@ResponseBody
	public List<GetMfgReturn> getMfgReturnRecordBetweenDate(HttpServletRequest request, HttpServletResponse response) {

		 List<GetMfgReturn>  getMfgReturnList = new ArrayList<GetMfgReturn>();
		try
		{
			String fromDate =  request.getParameter("fromDate") ;
			String toDate =  request.getParameter("toDate") ;
			
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String,Object>();
			map.add("fromDate", DateConvertor.convertToYMD(fromDate) );
			map.add("toDate", DateConvertor.convertToYMD(toDate));
			getMfgReturnList =  rest.postForObject(Constants.url + "/mfgReturnRecordBetweenDate",map, List.class);
			 
			 
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}

		return getMfgReturnList;
	}
	
	
	@RequestMapping(value = "/adjustKm", method = RequestMethod.GET)
	public ModelAndView adjustKm(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("bill/adjustKm");
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
	
	@RequestMapping(value = "/sumbitAdjustKm", method = RequestMethod.POST)
	public String sumbitAdjustKm(HttpServletRequest request, HttpServletResponse response) {

		 
	 
		try {
			SimpleDateFormat sf = new SimpleDateFormat("dd-MM-yyyy");
			SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date = new Date();
		  
			int vehId = Integer.parseInt(request.getParameter("vehId"));
			int inKm = Integer.parseInt(request.getParameter("inKm")); 
			String remark =   request.getParameter("remark") ;
			String driverName = request.getParameter("driverName");
			
			TVehicle tVehicle = new TVehicle();
			tVehicle.setVehId(vehId);
			tVehicle.setInKms(inKm);
			tVehicle.setEntryBy(1);
			tVehicle.setRemark(remark);
			tVehicle.setDriverName(driverName);
			tVehicle.setDate(sf.format(date));
			tVehicle.setDatetime(time.format(date));
			
		 Info info = rest.postForObject(Constants.url + "/master/saveTVehicle",tVehicle, Info.class);
		 
		 System.out.println("info " + info);
			 
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		

		return "redirect:/tempBill";
	}

}
