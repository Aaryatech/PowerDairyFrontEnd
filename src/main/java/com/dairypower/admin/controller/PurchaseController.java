package com.dairypower.admin.controller;

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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.dairypower.admin.common.Constants;
import com.dairypower.admin.common.DateConvertor;
import com.dairypower.admin.model.GetItem;
import com.dairypower.admin.model.GetPoDetail;
import com.dairypower.admin.model.GetPoHeader;
import com.dairypower.admin.model.Item;
import com.dairypower.admin.model.PoDetail;
import com.dairypower.admin.model.PoHeader;
import com.dairypower.admin.model.Vehicle; 

@Controller
@Scope("session")
public class PurchaseController {
	
	RestTemplate rest = new RestTemplate();
	List<GetPoDetail> addItemInPurchaseBill = new ArrayList<GetPoDetail>();
	List<GetPoDetail> editPurchaseBillList = new ArrayList<GetPoDetail>();
	GetPoHeader   editPoHeader = new GetPoHeader();
	List<GetItem> itemList = new ArrayList<GetItem>();
	
	@RequestMapping(value = "/purchaseBill", method = RequestMethod.GET)
	public ModelAndView purchaseBill(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("purchase/purchaseBill");
		try
		{
			addItemInPurchaseBill = new ArrayList<GetPoDetail>();
			GetItem[] Item =  rest.getForObject(Constants.url + "/master/getAllItems", GetItem[].class);
			itemList = new ArrayList<GetItem>(Arrays.asList(Item));
			model.addObject("itemList", itemList);
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}

		return model;
	}
	
	@RequestMapping(value = "/addItemInPurchaseBill", method = RequestMethod.GET)
	@ResponseBody
	public List<GetPoDetail> addItemInPurchaseBill(HttpServletRequest request, HttpServletResponse response) {

		
		try
		{
			 String index = request.getParameter("index");
			 int itemId  = Integer.parseInt(request.getParameter("itemId"));
			 String itemName = request.getParameter("itemName");
			 String batchNo = request.getParameter("batchNo");
			 String mfgDate = request.getParameter("mfgDate");
			 int qty  = Integer.parseInt(request.getParameter("qty"));
			 int shortNo  = Integer.parseInt(request.getParameter("shortNo"));
			 int extraNo  = Integer.parseInt(request.getParameter("extraNo"));
			 int leakageQty  = Integer.parseInt(request.getParameter("leakageQty"));
			 
			 
			 if(index==null || index=="")
			 {
				 System.out.println("add new Item" + index);
				 GetPoDetail getPoDetail = new GetPoDetail();
				 getPoDetail.setItemId(itemId); 
				 getPoDetail.setBatchNo(batchNo);
				 getPoDetail.setItemQty(qty);
				 getPoDetail.setBalance(qty);
				 getPoDetail.setShortNo(shortNo);
				 getPoDetail.setExtraNo(extraNo);
				 getPoDetail.setPoLeakageQty(leakageQty);
				 getPoDetail.setMfgDate(mfgDate);
				 for(int i=0;i<itemList.size();i++)
				 {
					 if(itemList.get(i).getItemId()==itemId)
					 {
						 getPoDetail.setItemName(itemList.get(i).getItemName());
						 getPoDetail.setRate(itemList.get(i).getPurchaseRate());
						 break;
					 }
				 }
				 addItemInPurchaseBill.add(getPoDetail);
			 }
			 else
			 {
				 
				 int key = Integer.parseInt(index);
				 System.out.println("edit Item" + key);
				 addItemInPurchaseBill.get(key).setItemId(itemId); 
				 addItemInPurchaseBill.get(key).setBatchNo(batchNo);
				 addItemInPurchaseBill.get(key).setItemQty(qty);
				 addItemInPurchaseBill.get(key).setBalance(qty);
				 addItemInPurchaseBill.get(key).setShortNo(shortNo);
				 addItemInPurchaseBill.get(key).setExtraNo(extraNo);
				 addItemInPurchaseBill.get(key).setPoLeakageQty(leakageQty);
				 addItemInPurchaseBill.get(key).setMfgDate(mfgDate);
				 for(int i=0;i<itemList.size();i++)
				 {
					 if(itemList.get(i).getItemId()==itemId)
					 {
						 addItemInPurchaseBill.get(key).setItemName(itemList.get(i).getItemName());
						 addItemInPurchaseBill.get(key).setRate(itemList.get(i).getPurchaseRate());
						 break;
					 }
				 }
			 }
			 
			 
			 
			 
			 System.out.println("addItemInPurchaseBill " + addItemInPurchaseBill);
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}

		return addItemInPurchaseBill;
	}
	
	@RequestMapping(value = "/editItemInPurchaseBill", method = RequestMethod.GET)
	@ResponseBody
	public GetPoDetail editItemInPurchaseBill(HttpServletRequest request, HttpServletResponse response) {

		GetPoDetail getPoDetail = new GetPoDetail();
		try
		{
			 int index  = Integer.parseInt(request.getParameter("index"));
			 getPoDetail = addItemInPurchaseBill.get(index);
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}

		return getPoDetail;
	}
	
	@RequestMapping(value = "/deleteItemInPurchaseBill", method = RequestMethod.GET)
	@ResponseBody
	public List<GetPoDetail> deleteItemInPurchaseBill(HttpServletRequest request, HttpServletResponse response) {

		 
		try
		{
			 int index  = Integer.parseInt(request.getParameter("index"));
			 addItemInPurchaseBill.remove(index);
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}

		return addItemInPurchaseBill;
	}
	
	@RequestMapping(value = "/insertPurchaseBill", method = RequestMethod.POST)
	public String insertPurchaseBill(HttpServletRequest request, HttpServletResponse response) {

	 
		SimpleDateFormat sf = new SimpleDateFormat("dd-MM-yyyy");
		SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		
		
		
		try {
				  
				String invoiceNo = request.getParameter("invoiceNo");
				String invoiceDate = request.getParameter("invoiceDate");
				String remark = request.getParameter("remark");  
				int recievedCreates = Integer.parseInt(request.getParameter("cratesReceivedQty"));
				PoHeader insert = new PoHeader();
				insert.setPoDate(invoiceDate);
				insert.setPoId(Integer.parseInt(invoiceNo));
				insert.setPoRemarks(remark);
				insert.setCratesRecievedQty(recievedCreates);
				insert.setPoDatetime(time.format(date));
				
				List<PoDetail> poDetailList = new ArrayList<>();
				float poTotal = 0;
				for(int i=0;i<addItemInPurchaseBill.size();i++)
				{
					PoDetail poDetail = new PoDetail();
					poDetail.setBatchNo(addItemInPurchaseBill.get(i).getBatchNo());
					poDetail.setItemId(addItemInPurchaseBill.get(i).getItemId());
					poDetail.setItemQty(addItemInPurchaseBill.get(i).getItemQty()); 
					poDetail.setBalance(addItemInPurchaseBill.get(i).getItemQty());
					poDetail.setRate(addItemInPurchaseBill.get(i).getRate());
					poDetail.setShortNo(addItemInPurchaseBill.get(i).getShortNo());
					poDetail.setExtraNo(addItemInPurchaseBill.get(i).getExtraNo());
					poDetail.setPoLeakageQty(addItemInPurchaseBill.get(i).getPoLeakageQty());
					poDetail.setMfgDate(addItemInPurchaseBill.get(i).getMfgDate());
					poDetail.setPackingDate("");
					poDetailList.add(poDetail);
					float total = poDetail.getRate()*poDetail.getItemQty(); 
					poTotal = poTotal + total;
				}
				insert.setPoTotal(poTotal);
				insert.setPoDetailList(poDetailList);
				insert.setUserId(1);
				PoHeader  poHeader =  rest.postForObject(Constants.url + "/savePo",insert, PoHeader .class);
				
				System.out.println("PoHeader " + poHeader);
				
				
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		

		return "redirect:/purchaseHistory";
	}
	
	@RequestMapping(value = "/purchaseHistory", method = RequestMethod.GET)
	public ModelAndView purchaseHistory(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("purchase/purchaseHistory");
		try
		{
			 Date date = new Date(); 
			 SimpleDateFormat show = new SimpleDateFormat("dd-MM-yyyy");
			 SimpleDateFormat consume = new SimpleDateFormat("yyyy-MM-dd");
			 
			 MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
			 map.add("fromDate",consume.format(date));
			 map.add("toDate",consume.format(date));
			 List<GetPoHeader>  getPoHeaderList =  rest.postForObject(Constants.url + "/getPoHeaderDetailsBetweenDate",map, List .class);
			 model.addObject("fromDate", show.format(date));
			 model.addObject("toDate", show.format(date));
			 model.addObject("getPoHeaderList", getPoHeaderList);
			 
		}catch(Exception e)
		{
			e.printStackTrace();
		}

		return model;
	}
	
	@RequestMapping(value = "/getPurchaseBillbetweenDate", method = RequestMethod.GET)
	@ResponseBody
	public List<GetPoHeader> getPurchaseBillbetweenDate(HttpServletRequest request, HttpServletResponse response) {

		List<GetPoHeader>  getPoHeaderList = new ArrayList<>();
		try
		{
			String fromDate = request.getParameter("fromDate");
			String toDate = request.getParameter("toDate");
			
			 MultiValueMap<String, Object> map = new LinkedMultiValueMap<>(); 
			 map.add("fromDate",DateConvertor.convertToYMD(fromDate));
			 map.add("toDate",DateConvertor.convertToYMD(toDate));
			  getPoHeaderList =  rest.postForObject(Constants.url + "/getPoHeaderDetailsBetweenDate",map, List .class);
			 
		}catch(Exception e)
		{
			e.printStackTrace();
		}

		return getPoHeaderList;
	}
	
	@RequestMapping(value = "/purchaseHeaderWithDetail/{poHeaderId}", method = RequestMethod.GET)
	public ModelAndView purchaseHeaderWithDetail(@PathVariable int poHeaderId, HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("purchase/purchaseHeaderWithDetail");
		try
		{
			  
			 MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
			 map.add("poHeaderId",poHeaderId); 
			  GetPoHeader   getPoHeader =  rest.postForObject(Constants.url + "/getPoHeaderDetails",map, GetPoHeader .class);
			 model.addObject("getPoHeader", getPoHeader); 
			 
		}catch(Exception e)
		{
			e.printStackTrace();
		}

		return model;
	}
	
	@RequestMapping(value = "/editPurchaseBill/{poHeaderId}", method = RequestMethod.GET)
	public ModelAndView editPurchaseBill(@PathVariable int poHeaderId, HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("purchase/editPurchaseBill");
		try
		{
			  
			 MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
			 map.add("poHeaderId",poHeaderId); 
			   editPoHeader =  rest.postForObject(Constants.url + "/getPoHeaderDetails",map, GetPoHeader .class);
			 model.addObject("getPoHeader", editPoHeader); 
			 
			 GetItem[] Item =  rest.getForObject(Constants.url + "/master/getAllItems", GetItem[].class);
				itemList = new ArrayList<GetItem>(Arrays.asList(Item));
				model.addObject("itemList", itemList);
			 
			   editPurchaseBillList = editPoHeader.getPoDetailList();
			 
		}catch(Exception e)
		{
			e.printStackTrace();
		}

		return model;
	}
	
	@RequestMapping(value = "/addItemInEditPurchaseBill", method = RequestMethod.GET)
	@ResponseBody
	public List<GetPoDetail> addItemInEditPurchaseBill(HttpServletRequest request, HttpServletResponse response) {

		
		try
		{
			 String index = request.getParameter("index");
			 int itemId  = Integer.parseInt(request.getParameter("itemId"));
			 String itemName = request.getParameter("itemName");
			 String batchNo = request.getParameter("batchNo");
			 String mfgDate = request.getParameter("mfgDate");
			 int qty  = Integer.parseInt(request.getParameter("qty"));
			 int shortNo  = Integer.parseInt(request.getParameter("shortNo"));
			 int extraNo  = Integer.parseInt(request.getParameter("extraNo"));
			 int leakageQty  = Integer.parseInt(request.getParameter("leakageQty"));
			 
			 
			 if(index==null || index=="")
			 {
				 System.out.println("add new Item" + index);
				 GetPoDetail getPoDetail = new GetPoDetail();
				 getPoDetail.setItemId(itemId); 
				 getPoDetail.setBatchNo(batchNo);
				 getPoDetail.setItemQty(qty);
				 getPoDetail.setBalance(qty);
				 getPoDetail.setShortNo(shortNo);
				 getPoDetail.setExtraNo(extraNo);
				 getPoDetail.setPoLeakageQty(leakageQty);
				 getPoDetail.setMfgDate(mfgDate);
				 for(int i=0;i<itemList.size();i++)
				 {
					 if(itemList.get(i).getItemId()==itemId)
					 {
						 getPoDetail.setItemName(itemList.get(i).getItemName());
						 getPoDetail.setRate(itemList.get(i).getPurchaseRate());
						 break;
					 }
				 }
				 editPurchaseBillList.add(getPoDetail);
			 }
			 else
			 {
				 
				 int key = Integer.parseInt(index); 
				 System.out.println("edit Item" + key);
				 editPurchaseBillList.get(key).setItemId(itemId); 
				 editPurchaseBillList.get(key).setBatchNo(batchNo);
				 editPurchaseBillList.get(key).setItemQty(qty);
				 editPurchaseBillList.get(key).setBalance(qty);
				 editPurchaseBillList.get(key).setShortNo(shortNo);
				 editPurchaseBillList.get(key).setExtraNo(extraNo);
				 editPurchaseBillList.get(key).setPoLeakageQty(leakageQty);
				 editPurchaseBillList.get(key).setMfgDate(mfgDate);
				 for(int i=0;i<itemList.size();i++)
				 {
					 if(itemList.get(i).getItemId()==itemId)
					 {
						 editPurchaseBillList.get(key).setItemName(itemList.get(i).getItemName());
						 editPurchaseBillList.get(key).setRate(itemList.get(i).getPurchaseRate());
						 break;
					 }
				 }
			 }
			 
			 
			 
			 
			 System.out.println("editPurchaseBillList " + editPurchaseBillList);
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}

		return editPurchaseBillList;
	}
	
	@RequestMapping(value = "/editItemInEditPurchaseBill", method = RequestMethod.GET)
	@ResponseBody
	public GetPoDetail editItemInEditPurchaseBill(HttpServletRequest request, HttpServletResponse response) {

		GetPoDetail getPoDetail = new GetPoDetail();
		try
		{
			 int index  = Integer.parseInt(request.getParameter("index"));
			 getPoDetail = editPurchaseBillList.get(index);
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}

		return getPoDetail;
	}
	
	@RequestMapping(value = "/deleteItemInEditPurchaseBill", method = RequestMethod.GET)
	@ResponseBody
	public List<GetPoDetail> deleteItemInEditPurchaseBill(HttpServletRequest request, HttpServletResponse response) {

		 
		try
		{
			 int index  = Integer.parseInt(request.getParameter("index"));
			if( editPurchaseBillList.get(index).getPoDetailId() != 0)
				editPurchaseBillList.get(index).setIsUsed(1);
			else
				editPurchaseBillList.remove(index);
				
		}catch(Exception e)
		{
			e.printStackTrace();
		}

		return editPurchaseBillList;
	}
	
	@RequestMapping(value = "/submitEditPurchaseBill", method = RequestMethod.POST)
	public String submitEditPurchaseBill(HttpServletRequest request, HttpServletResponse response) {

	 
		SimpleDateFormat sf = new SimpleDateFormat("dd-MM-yyyy");
		SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		
		
		
		try {
				  
				String invoiceNo = request.getParameter("invoiceNo");
				String invoiceDate = request.getParameter("invoiceDate");
				String remark = request.getParameter("remark");  
				int recievedCreates = Integer.parseInt(request.getParameter("cratesReceivedQty"));
				PoHeader insert = new PoHeader();
				insert.setPoHeaderId(editPoHeader.getPoHeaderId());
				insert.setPoDate(invoiceDate);
				insert.setPoId(Integer.parseInt(invoiceNo));
				insert.setPoRemarks(remark);
				insert.setCratesRecievedQty(recievedCreates);
				insert.setPoDatetime(time.format(date));
				
				List<PoDetail> poDetailList = new ArrayList<>();
				float poTotal = 0;
				for(int i=0;i<editPurchaseBillList.size();i++)
				{
					PoDetail poDetail = new PoDetail();
					poDetail.setPoDetailId(editPurchaseBillList.get(i).getPoDetailId());
					poDetail.setPoHeaderId(editPurchaseBillList.get(i).getPoHeaderId());
					poDetail.setBatchNo(editPurchaseBillList.get(i).getBatchNo());
					poDetail.setItemId(editPurchaseBillList.get(i).getItemId());
					poDetail.setItemQty(editPurchaseBillList.get(i).getItemQty());
					poDetail.setBalance(editPurchaseBillList.get(i).getBalance());
					poDetail.setRate(editPurchaseBillList.get(i).getRate());
					poDetail.setShortNo(editPurchaseBillList.get(i).getShortNo());
					poDetail.setExtraNo(editPurchaseBillList.get(i).getExtraNo());
					poDetail.setPoLeakageQty(editPurchaseBillList.get(i).getPoLeakageQty());
					poDetail.setMfgDate(editPurchaseBillList.get(i).getMfgDate());
					poDetail.setPackingDate("");
					poDetail.setIsUsed(editPurchaseBillList.get(i).getIsUsed());
					poDetailList.add(poDetail);
					if(poDetail.getIsUsed()==0)
					{
						float total = poDetail.getRate()*poDetail.getItemQty(); 
						poTotal = poTotal + total;
					}
				}
				insert.setPoTotal(poTotal);
				insert.setPoDetailList(poDetailList);
				insert.setUserId(1);
				PoHeader  poHeader =  rest.postForObject(Constants.url + "/savePo",insert, PoHeader .class);
				
				System.out.println("PoHeader " + poHeader);
				
				
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		

		return "redirect:/purchaseHistory";
	}

}
