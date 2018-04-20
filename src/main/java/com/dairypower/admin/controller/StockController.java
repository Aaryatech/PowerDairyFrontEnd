package com.dairypower.admin.controller;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
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
import com.dairypower.admin.model.StockDetail;
import com.dairypower.admin.model.StockHeader; 
 

@Controller
@Scope("session")
public class StockController {
	
	DecimalFormat df = new DecimalFormat("#.00");
	List<StockDetail> stockDetailList = new ArrayList<StockDetail>();
	StockHeader stockHeader = new StockHeader(); 
	StockHeader updateStatus = new StockHeader();
	List<GetItem> itemList = new ArrayList<GetItem>();
	List<GetCurrentStock> getCurrentStock = new ArrayList<GetCurrentStock>();
	
	@RequestMapping(value = "/showItemStock", method = RequestMethod.GET)
	public ModelAndView getSupplierList(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("stock/showItemStock");
		RestTemplate rest = new RestTemplate();
		try {
			 String date;
			 int cratesOpn=0;
			 
			  stockHeader = rest.getForObject(Constants.url + "/getStock",
					 StockHeader.class); 
			System.out.println("stockHeader " + stockHeader); 
			 stockDetailList = new ArrayList<StockDetail>();
			 GetItem[] Item =  rest.getForObject(Constants.url + "/master/getAllItems", GetItem[].class);
				itemList = new ArrayList<GetItem>(Arrays.asList(Item));
			
			if(stockHeader.getStockHeaderId()!=0)
			{
				System.out.println("data available");
				 
				MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
				map.add("stockId", stockHeader.getStockHeaderId());
				StockDetail[] itemStockDetail = rest.postForObject(Constants.url + "/getStockDetail",map,
						StockDetail[].class);
				stockDetailList = new ArrayList<StockDetail>(Arrays.asList(itemStockDetail));
				 
				date = stockHeader.getDate();
				cratesOpn = stockHeader.getCratesOpQty();
			}
			else
			{
				System.out.println("data not available");
				 
					for(int j=0;j<itemList.size();j++)
					{
						StockDetail stockDetail = new StockDetail();
						stockDetail.setItemId(itemList.get(j).getItemId());  
						stockDetailList.add(stockDetail);
					}
					 Date todaydate = new Date();
					 SimpleDateFormat sf = new SimpleDateFormat("dd-MM-yyyy");
					 date=sf.format(todaydate);
			}
			
			model.addObject("stockDetailList",stockDetailList);
			model.addObject("itemList",itemList);
			model.addObject("date",date);
			model.addObject("cratesOpn",cratesOpn);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		

		return model;
	}
	
	
	@RequestMapping(value = "/submitStock", method = RequestMethod.POST)
	public String submitStock(HttpServletRequest request, HttpServletResponse response) {

		 
		RestTemplate rest = new RestTemplate();
		try {
			 int cratesOpn = Integer.parseInt(request.getParameter("cratesOpn"));
			 
			 
			 for(int i=0;i<stockDetailList.size();i++)
			 {
				  
				stockDetailList.get(i).setOpStock(Integer.parseInt(request.getParameter("qty"+i))); 
				 
			 }
			 
			  if(stockHeader.getStockHeaderId()!=0)
			 {
				 stockHeader.setStockDetailList(stockDetailList);
				 stockHeader.setCratesOpQty(cratesOpn); 
				 StockHeader edit = rest.postForObject(Constants.url + "saveStock",stockHeader,
						 StockHeader.class); 
				 System.out.println("Edit "+edit);
			 }
			 else
			 {
				 
				 stockHeader = new StockHeader();
				 stockHeader.setDate(request.getParameter("date"));
				 stockHeader.setStockDetailList(stockDetailList); 
				 stockHeader.setCratesOpQty(cratesOpn); 
				 StockHeader insert = rest.postForObject(Constants.url + "saveStock",stockHeader,
						 StockHeader.class); 
				 System.out.println("insert "+insert);
			 } 
			 
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		

		return "redirect:/showItemStock";
	}
	
	@RequestMapping(value = "/getCurrentStock", method = RequestMethod.GET)
	public ModelAndView getCurrentStock(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("stock/getCurrentStock");
		RestTemplate rest = new RestTemplate();
		try {
			 
			 
			 updateStatus = rest.getForObject(Constants.url + "getStock",
					StockHeader.class); 
			 
			 
			 if(updateStatus.getStockHeaderId()!=0) 
			 { 
				 MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
				 map.add("date",DateConvertor.convertToYMD(updateStatus.getDate()));
				 GetCurrentStock[] currentStock = rest.postForObject(Constants.url + "getCurrentStock",map,
						  GetCurrentStock[].class); 
				  getCurrentStock = new ArrayList<GetCurrentStock>(Arrays.asList(currentStock));
				  
				  GetCratesStock getCratesStock = rest.postForObject(Constants.url + "getCratesStock",map,
						  GetCratesStock.class); 
				  
				 SimpleDateFormat sf = new SimpleDateFormat("dd-MM-yyyy");
					Date date = new Date();
					Calendar c = Calendar.getInstance();
					c.setTime(date);
					c.add(Calendar.DATE, 1);
					date=c.getTime();
					c.add(Calendar.DATE, -1);
					Date yesterDay=c.getTime();
				model.addObject("getCratesStock",getCratesStock);
				model.addObject("currentStockList",getCurrentStock); 
				model.addObject("stockDate",updateStatus.getDate());
				model.addObject("tommorowDate",sf.format(date));
				model.addObject("yesterDay",sf.format(yesterDay));
			 }
			 
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		

		return model;
	}
	
	
	@RequestMapping(value = "/stockDayEnd", method = RequestMethod.POST)
	public String StockDayEnd(HttpServletRequest request, HttpServletResponse response) {

		 
		RestTemplate rest = new RestTemplate();
		try {
			SimpleDateFormat sf = new SimpleDateFormat("dd-MM-yyyy");
			Date date = sf.parse(updateStatus.getDate());
			Calendar c = Calendar.getInstance();
			c.setTime(date);
			c.add(Calendar.DATE, 1);
			date=c.getTime();
			System.out.println(date);
			
			int cratesStock = Integer.parseInt(request.getParameter("closingCratesQty"));
			
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
			 map.add("stockId",updateStatus.getStockHeaderId());
			 StockDetail[] StockDetailList = rest.postForObject(Constants.url + "getStockDetailForUpdate",map,
					 StockDetail[].class);
			List<StockDetail> updateStockDetailList = new ArrayList<StockDetail>(Arrays.asList(StockDetailList));
			
			for(int i=0;i<updateStockDetailList.size();i++)
			 {
				updateStockDetailList.get(i).setClosingQty(Integer.parseInt(request.getParameter("closingQty"+updateStockDetailList.get(i).getItemId())));
				 
			 }
			
			updateStatus.setStatus(1);
			updateStatus.setStockDetailList(updateStockDetailList);
			updateStatus.setCratesCloseQty(cratesStock);
			
			StockHeader udateStockStatus = rest.postForObject(Constants.url + "saveStock",updateStatus,
					StockHeader.class); 
			
			 if(udateStockStatus!=null) 
			 {
				 StockHeader stockHeader = new StockHeader();
				 stockHeader.setDate(sf.format(date));
				 stockHeader.setCratesOpQty(cratesStock);
				 List<StockDetail> stockDetailList = new ArrayList<StockDetail>();
				 
				 
					
				 
				 for(int i=0;i<getCurrentStock.size();i++)
				 {
					 StockDetail stockDetail = new StockDetail();  
					 stockDetail.setOpStock(Integer.parseInt(request.getParameter("closingQty"+getCurrentStock.get(i).getItemId())));
					 stockDetail.setItemId(getCurrentStock.get(i).getItemId());
					 stockDetailList.add(stockDetail); 
				 }
				 
				
				 stockHeader.setStockDetailList(stockDetailList);
				 StockHeader insert = rest.postForObject(Constants.url + "saveStock",stockHeader,
						 StockHeader.class); 
				 System.out.println("insert "+insert);
				 
			 }
				
		 
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		

		return "redirect:/getCurrentStock";
	}
	
	@RequestMapping(value = "/getStockBetweenDate", method = RequestMethod.GET)
	@ResponseBody
	public List<GetCurrentStock> getStockBetweenDate(HttpServletRequest request, HttpServletResponse response) {

		List<GetCurrentStock> stockBetweenDate = new ArrayList<GetCurrentStock>();
		RestTemplate rest = new RestTemplate();
		try {
			 
			 
			 String fromDate = request.getParameter("fromDate");
			 String toDate = request.getParameter("toDate");
			 
			 
				 MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
				 
				 map.add("fromDate",DateConvertor.convertToYMD(fromDate));
				 map.add("toDate",DateConvertor.convertToYMD(toDate));
				 GetCurrentStock[] currentStock = rest.postForObject(Constants.url + "getStockBetweenDate",map,
						  GetCurrentStock[].class); 
				 stockBetweenDate = new ArrayList<GetCurrentStock>(Arrays.asList(currentStock));
				 
			 
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		

		return stockBetweenDate;
	}

}
