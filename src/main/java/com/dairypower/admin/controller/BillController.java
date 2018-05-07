package com.dairypower.admin.controller;

import java.awt.Dimension;
import java.awt.Insets;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.zefer.pd4ml.PD4Constants;
import org.zefer.pd4ml.PD4ML;
import org.zefer.pd4ml.PD4PageMark;

import com.dairypower.admin.common.Constants;
import com.dairypower.admin.common.DateConvertor;
import com.dairypower.admin.model.BillDetail;
import com.dairypower.admin.model.BillHeader;
import com.dairypower.admin.model.BillHeaderReport;
import com.dairypower.admin.model.BillPayment;
import com.dairypower.admin.model.CreditNoteDetail;
import com.dairypower.admin.model.CreditNoteHeader;
import com.dairypower.admin.model.CrnHeader;
import com.dairypower.admin.model.Currency;
import com.dairypower.admin.model.Customer;
import com.dairypower.admin.model.GetBillHeader;
import com.dairypower.admin.model.GetBillHeaders;
import com.dairypower.admin.model.GetCratesStock;
import com.dairypower.admin.model.GetItem;
import com.dairypower.admin.model.GetPoDetail;
import com.dairypower.admin.model.Info;
import com.dairypower.admin.model.LoginResponse;
import com.dairypower.admin.model.PoDetail;
import com.dairypower.admin.model.ReturnStockUpdate;
import com.dairypower.admin.model.RsDetail;
import com.dairypower.admin.model.StockHeader;
import com.dairypower.admin.model.TSetting;
import com.dairypower.admin.model.TVehicle;
import com.dairypower.admin.model.Vehicle;
import com.dairypower.admin.model.VehicleRes;

@Controller
@Scope("session")
public class BillController {
	
	RestTemplate rest = new RestTemplate();
	List<BillDetail> billDetailList=null;
	List<GetPoDetail> poDetailList=new ArrayList<GetPoDetail>();
	List<CrnHeader>  getCrnHeaderList=new ArrayList<CrnHeader>();

	@RequestMapping(value = "/showAllTempAndSettleBill", method = RequestMethod.GET)
	public ModelAndView showAllTempAndSettleBill(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("bill/listOfAllBill");
		try
		{
			 Date date = new Date();
			 
			 SimpleDateFormat sf = new SimpleDateFormat("dd-MM-yyyy");
			 model.addObject("toDay", sf.format(date));
			 SimpleDateFormat sf1 = new SimpleDateFormat("yyyy-MM-dd");
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String,Object>();
			map.add("date", sf1.format(date));
			map.add("isSettled", 0);
			GetBillHeaders[] headerList =  rest.postForObject(Constants.url + "/getSettledBills",map, GetBillHeaders[].class);
            ArrayList<GetBillHeaders> billHeaderList=new ArrayList<GetBillHeaders>(Arrays.asList(headerList));
			model.addObject("billHeaderList", billHeaderList);
			 map = new LinkedMultiValueMap<String,Object>();
			map.add("date", sf1.format(date));
			map.add("isSettled",1);
			GetBillHeaders[] headersList =  rest.postForObject(Constants.url + "/getSettledBills",map, GetBillHeaders[].class);
            ArrayList<GetBillHeaders> billHeadersList=new ArrayList<GetBillHeaders>(Arrays.asList(headersList));
            model.addObject("billHeadersList", billHeadersList);
            
            map = new LinkedMultiValueMap<String,Object>();
			map.add("date", sf1.format(date));
            BillHeader[] allBills =  rest.postForObject(Constants.url + "/getAllBillHeader",map, BillHeader[].class);
            ArrayList<BillHeader> billsList=new ArrayList<BillHeader>(Arrays.asList(allBills));
           
            int outstandingCrates=0;
            float outstandingAmt=0;
            float unsettledOutstandingAmt=0;

            
            if(!billHeaderList.isEmpty())
            {
            	for(int i=0;i<billHeaderList.size();i++)
            	{
            		unsettledOutstandingAmt=unsettledOutstandingAmt+(billHeaderList.get(i).getOutstandingAmt());
            		System.err.println(unsettledOutstandingAmt);
            		
            	}
            }
            if(!billsList.isEmpty())
            {
            	for(int i=0;i<billsList.size();i++)
            	{
            		
            		if(billsList.get(i).getIsSettled()==1)
            		{
            			outstandingCrates=outstandingCrates+(billsList.get(i).getCratesClBal());
            			outstandingAmt=outstandingAmt+(billsList.get(i).getOutstandingAmt());

            		}else
            		{
            			outstandingCrates=outstandingCrates+(billsList.get(i).getCratesOpBal()+billsList.get(i).getCratesIssued());
            			outstandingAmt=outstandingAmt+(billsList.get(i).getOutstandingAmt());
            		}
            	}
            }
            outstandingCrates=outstandingCrates+0;
            model.addObject("outstandingCrates", outstandingCrates);
            model.addObject("unsettledOutstandingAmt", unsettledOutstandingAmt);
            model.addObject("outstandingAmount", outstandingAmt);
            model.addObject("pending", billHeaderList.size());
            model.addObject("generated", billHeadersList.size());
		}catch(Exception e)
		{
			e.printStackTrace();
		}

		return model;
	}
	@RequestMapping(value = "/searchBills", method = RequestMethod.POST)
	public ModelAndView searchBills(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("bill/listOfAllBill");
		try
		{			
			SimpleDateFormat sf1 = new SimpleDateFormat("yyyy-MM-dd");

			String date=request.getParameter("billDate");
		    Date date1=new SimpleDateFormat("dd-MM-yyyy").parse(date);  

			 model.addObject("toDay", date);
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String,Object>();
			map.add("date", sf1.format(date1));
			map.add("isSettled", 0);
			GetBillHeaders[] headerList =  rest.postForObject(Constants.url + "/getSettledBills",map, GetBillHeaders[].class);
            ArrayList<GetBillHeaders> billHeaderList=new ArrayList<GetBillHeaders>(Arrays.asList(headerList));
			model.addObject("billHeaderList", billHeaderList);
			 map = new LinkedMultiValueMap<String,Object>();
			map.add("date", sf1.format(date1));
			map.add("isSettled",1);
			GetBillHeaders[] headersList =  rest.postForObject(Constants.url + "/getSettledBills",map, GetBillHeaders[].class);
            ArrayList<GetBillHeaders> billHeadersList=new ArrayList<GetBillHeaders>(Arrays.asList(headersList));
            model.addObject("billHeadersList", billHeadersList);
            
            model.addObject("pending", billHeaderList.size());
            model.addObject("generated", billHeadersList.size());
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
			
			GetPoDetail[]	poDetailListRes=  rest.getForObject(Constants.url + "/getPoDetailsList", GetPoDetail[].class);
			poDetailList=new ArrayList<GetPoDetail>(Arrays.asList(poDetailListRes));
			
			for(int i=0;i<poDetailList.size();i++)
			{
				Date date1=new SimpleDateFormat("dd-MM-yyyy").parse(poDetailList.get(i).getMfgDate());  
			    SimpleDateFormat dmyFormat = new SimpleDateFormat("yyyy-MM-dd");
			    poDetailList.get(i).setMfgDate(dmyFormat.format(date1));
			}
			
			StockHeader stockHeader = rest.getForObject(Constants.url + "getStock",
					StockHeader.class); 
			 MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			 map.add("date",stockHeader.getDate());
			 
			  GetCratesStock getCratesStock = rest.postForObject(Constants.url + "getCratesStock",map,
					  GetCratesStock.class); 
			  model.addObject("stockDate", stockHeader.getDate());
			  
			  int totalCrates = getCratesStock.getCratesOpQty()+getCratesStock.getCratesReceivedQtyBypurchase()-getCratesStock.getCratesIssued()+
					  getCratesStock.getCratesReceivedBycustomer()-getCratesStock.getCratesReturnQtyTomfg();
			  model.addObject("crateStock", totalCrates);
			  
			  SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
				Date today = new Date();
				model.addObject("today", sf.format(today));

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
public @ResponseBody VehicleRes getVehicle(HttpServletRequest request, HttpServletResponse response) {

	VehicleRes vehicleRes=new VehicleRes();
	try {
		int vehId=Integer.parseInt(request.getParameter("vehId"));

	 MultiValueMap<String, Object> map = new LinkedMultiValueMap<String,Object>();
	 map.add("vehId", vehId);
	 vehicleRes = rest.postForObject(Constants.url + "/master/getCalVehicleKm",map, VehicleRes.class);
	}
	catch(Exception e)
	{
	e.printStackTrace();
	}
	return vehicleRes;
}
@RequestMapping(value = "/getBatchList", method = RequestMethod.GET) 
public @ResponseBody List<GetPoDetail> getPoDetailList(HttpServletRequest request, HttpServletResponse response) {

	List<GetPoDetail> poDetails=new ArrayList<>();
	try {
		int itemId=Integer.parseInt(request.getParameter("itemId"));
		// MultiValueMap<String, Object> map = new LinkedMultiValueMap<String,Object>();
		 //map.add("itemId", itemId);
		//poDetailListRes = rest.postForObject(Constants.url + "/getPoDetails",map,List.class);
		if(!poDetailList.isEmpty())
		{
			for(int i=0;i<poDetailList.size();i++)
			{
				if(itemId==poDetailList.get(i).getItemId())
				{
					poDetails.add(poDetailList.get(i));
				}
				
			}
		}
	}
	catch(Exception e)
	{
	e.printStackTrace();
	}
	return poDetails;
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

	int batchNo=Integer.parseInt(request.getParameter("batchNo"));

	
	 MultiValueMap<String, Object> map = new LinkedMultiValueMap<String,Object>();
	 map.add("itemId", itemId);
	 map.add("custId", custId);
	RsDetail rsDetail =  rest.postForObject(Constants.url + "/getRsData",map, RsDetail.class);
	 map = new LinkedMultiValueMap<String,Object>();
	 map.add("itemId", itemId);
	GetItem getItemRes =  rest.postForObject(Constants.url + "/master/getItemById",map, GetItem.class);
	
	GetPoDetail getPoDetail=new GetPoDetail();
	for(int i=0;i<poDetailList.size();i++)
	{
		if(poDetailList.get(i).getPoDetailId()==batchNo)
		{
			getPoDetail=poDetailList.get(i);
			int balanceQty=poDetailList.get(i).getBalance();
			poDetailList.get(i).setBalance(balanceQty-billQty);
		}
	}
	
	BillDetail billDetail=new BillDetail();
	billDetail.setBillDetailId(0);
	billDetail.setBillTempId(0);
	billDetail.setItemId(itemId);
	billDetail.setItemName(getItemRes.getItemName());
	billDetail.setBillQty(billQty);
	billDetail.setBatchNo(getPoDetail.getBatchNo());
	billDetail.setCgstPer(getItemRes.getCgstPer());
	billDetail.setIgstPer(getItemRes.getIgstPer());
	billDetail.setSgstPer(getItemRes.getSgstPer());
	billDetail.setRate(rsDetail.getRate());
	billDetail.setReturnQty(0);
	billDetail.setDistLeakageQty(0);
    billDetail.setPoDetailId(batchNo);
	billDetailList.add(billDetail);
	System.err.println("billDetailListbillDetailList"+billDetailList.toString());
	}
	catch (Exception e) {
		e.printStackTrace();
	}
	return billDetailList;

}
@RequestMapping(value = "/editItemInSaleBill", method = RequestMethod.GET)
@ResponseBody
public BillDetail editItemInSaleBill(HttpServletRequest request, HttpServletResponse response) {

	BillDetail billDetail = new BillDetail();
	try
	{
		 int index  = Integer.parseInt(request.getParameter("index"));
		 billDetail = billDetailList.get(index);
		
	}catch(Exception e)
	{
		e.printStackTrace();
	}

	return billDetail;
} 
@RequestMapping(value = "/deleteItemInSaleBill", method = RequestMethod.GET)
@ResponseBody
public List<BillDetail> deleteItemInSaleBill(HttpServletRequest request, HttpServletResponse response) {

	 
	try
	{
		 int index  = Integer.parseInt(request.getParameter("index"));
		 BillDetail billDetail=billDetailList.get(index);
		 for(int i=0;i<poDetailList.size();i++)
			{
				if(poDetailList.get(i).getPoDetailId()==billDetail.getPoDetailId())
				{
					int balanceQty=poDetailList.get(i).getBalance();
					poDetailList.get(i).setBalance(balanceQty+billDetail.getBillQty());
				}
			}
		 billDetailList.remove(index);
		
	}catch(Exception e)
	{
		e.printStackTrace();
	}

	return billDetailList;
}

@RequestMapping(value = "/checkPoBalance", method = RequestMethod.GET)
@ResponseBody
public int checkPoBalance(HttpServletRequest request, HttpServletResponse response) {

	int flag = 0;
	try
	{
		int detailNo = Integer.parseInt(request.getParameter("batchNo"));
		int qty = Integer.parseInt(request.getParameter("qty"));
		
		for(int i = 0 ; i<poDetailList.size();i++)
		{
			if(detailNo==poDetailList.get(i).getPoDetailId() && qty<=poDetailList.get(i).getBalance())
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
@RequestMapping(value = "/insertTempBill", method = RequestMethod.GET)
public @ResponseBody BillHeader insertTempBill(HttpServletRequest request, HttpServletResponse response) {
	
	RestTemplate restTemplate=new RestTemplate();
	HttpSession session = request.getSession(); 
	LoginResponse login = (LoginResponse) session.getAttribute("UserDetail"); 
	
	System.out.println("Item  Detail Before Submit "+billDetailList.toString());
	BillHeader  billHeaderRes = null;
	BillHeader billHeader=new BillHeader();
	try
	{
		int custId=Integer.parseInt(request.getParameter("custId"));
		System.out.println("custId"+custId);
		
		int vehId=Integer.parseInt(request.getParameter("vehId"));
		System.out.println("vehId"+vehId);
		int outKm=Integer.parseInt(request.getParameter("vehOutKm"));
		int cratesIssued=Integer.parseInt(request.getParameter("cratesIssueQty"));
		
		int cratesOpnQty=Integer.parseInt(request.getParameter("cratesOpnQty"));
		float total=Float.parseFloat(request.getParameter("total"));
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
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
		billHeader.setGrandTotal(total);
		billHeader.setVehId(vehId);
		billHeader.setBillDetailList(billDetailList);
		
		System.err.println("billHeader:"+billHeader.toString());
		billHeaderRes=restTemplate.postForObject(Constants.url+"/saveBill",billHeader,BillHeader.class);
		System.err.println("poDetailList:"+poDetailList.toString());
	   
		if(billHeaderRes!=null)
		{
			 List<PoDetail> poListRes=restTemplate.postForObject(Constants.url+"/updatePoDetailList", poDetailList, List.class);
		    	System.err.println("poListRes:"+poListRes.toString());
		    	
			Date date = new Date();
			String currdDate= new SimpleDateFormat("yyyy-MM-dd").format(date);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		    String strDateTime = sdf.format(date);
		    
			TVehicle tVehicleRes=new TVehicle();
			tVehicleRes.settVehId(0);
			tVehicleRes.setVehId(vehId);
			tVehicleRes.setBillTempId(billHeaderRes.getBillTempId());
			tVehicleRes.setInKms(outKm);
			tVehicleRes.setOutKm(outKm);
			tVehicleRes.setDate(currdDate);
			tVehicleRes.setDatetime(strDateTime);
			tVehicleRes.setEntryBy(login.getUser().getIsUsed());
			tVehicleRes.setRemark("");
			tVehicleRes.setDriverName("");
			
			Info info=restTemplate.postForObject(Constants.url+"/master/saveTVehicle",tVehicleRes,Info.class);

		}
		
		billDetailList=new ArrayList<>();
	}
	catch(Exception e)
	{
		e.printStackTrace();

	}
	return billHeaderRes;
	
}
	@RequestMapping(value = "/approvedTempBill/{billTempId}", method = RequestMethod.GET)
	public ModelAndView approvedTempBill(@PathVariable int billTempId, HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("bill/billDetail");
		try
		{
			 int outKm=0;
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String,Object>();
			map.add("billTempId", billTempId);
			GetBillHeader header =  rest.postForObject(Constants.url + "/getBillHeaderDetails",map, GetBillHeader.class);
			System.err.println("header"+header.toString());
			model.addObject("billHeader", header);
			model.addObject("billDetails", header.getBillDetailList());
			map = new LinkedMultiValueMap<String,Object>();
			map.add("custId", header.getCustId()); 
			Customer customer = rest.postForObject(Constants.url + "/master/getCustomerById",map, Customer.class);
			model.addObject("customer",customer);
			try {
			model.addObject("keySize", header.getBillDetailList().size());
			}catch (Exception e) {
				e.printStackTrace();
			}
			try {
				map = new LinkedMultiValueMap<String,Object>();
				map.add("billTempId",billTempId); 
				VehicleRes vehicleRes=rest.postForObject(Constants.url + "/master/findOutKm",map, VehicleRes.class);
				outKm=vehicleRes.getVehOpKms();
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			List<Currency> currencyList =  rest.getForObject(Constants.url + "/master/getAllCurrency", List.class);
			model.addObject("currencyList",currencyList);
			model.addObject("currencyListSize", currencyList.size());
			model.addObject("outKm", outKm);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return model;
	}
	@RequestMapping(value = "/settledBills/{billTempId}", method = RequestMethod.GET)
	public ModelAndView settledBills(@PathVariable int billTempId, HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("bill/settledBillDetails");
		try
		{
			 
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String,Object>();
			map.add("billTempId", billTempId);
			GetBillHeader header =  rest.postForObject(Constants.url + "/getBillHeaderDetails",map, GetBillHeader.class);
			System.err.println("header"+header.toString());
			model.addObject("billHeader", header);
			model.addObject("billDetails", header.getBillDetailList());
			map = new LinkedMultiValueMap<String,Object>();
			map.add("custId", header.getCustId()); 
			Customer customer = rest.postForObject(Constants.url + "/master/getCustomerById",map, Customer.class);
			model.addObject("customer",customer);
			try {
			model.addObject("keySize", header.getBillDetailList().size());
			}catch (Exception e) {
				// TODO: handle exception
			}
			List<Currency> currencyList =  rest.getForObject(Constants.url + "/master/getAllCurrency", List.class);
			model.addObject("currencyList",currencyList);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return model;
	}
	@RequestMapping(value = "/approveBill", method = RequestMethod.POST)
	public String approveBill(HttpServletRequest request, HttpServletResponse response) {

		try {
			
			int billTempId=Integer.parseInt(request.getParameter("billTempId"));
			float collectedAmt=Float.parseFloat(request.getParameter("collectedAmt"));
			int cratesReceived=Integer.parseInt(request.getParameter("recCreatesQty"));
			int cratesBalQty=Integer.parseInt(request.getParameter("cratesBalQty"));
			float outstandingAmt=Float.parseFloat(request.getParameter("outstandingAmt"));
			String remarks=request.getParameter("remark");
			int vehInKm=Integer.parseInt(request.getParameter("vehInKm"));
			int payMode=Integer.parseInt(request.getParameter("payMode"));
			float grandTotal=Float.parseFloat(request.getParameter("grandTotalText"));

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String,Object>();
			map.add("billTempId", billTempId);
			GetBillHeader header =  rest.postForObject(Constants.url + "/getBillHeaderDetails",map, GetBillHeader.class);
			//System.err.println("header"+header.toString());
			Date date1=new SimpleDateFormat("dd-MM-yyyy").parse(header.getBillDate());  
		    SimpleDateFormat dmyFormat = new SimpleDateFormat("yyyy-MM-dd");
		    header.setBillDate(dmyFormat.format(date1));
			
			Currency[] currencyList =  rest.getForObject(Constants.url + "/master/getAllCurrency", Currency[].class);
			ArrayList<Currency> currencyListRes=new ArrayList<Currency>(Arrays.asList(currencyList));
			
			map = new LinkedMultiValueMap<String,Object>();
			map.add("settingKey", "bill_id");
			TSetting tSettingRes=rest.postForObject(Constants.url + "/getSettingValue",map, TSetting.class);
			int billId=tSettingRes.getSettingValue()+1;
			
		    List<CreditNoteDetail> creditNoteDetail=new ArrayList<>();//
			Date date = new Date();
			String currDate= new SimpleDateFormat("yyyy-MM-dd").format(date);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
			
			map = new LinkedMultiValueMap<String,Object>();
			map.add("settingKey", "crn_no");
			TSetting tSetting=rest.postForObject(Constants.url + "/getSettingValue",map, TSetting.class);
			int crnNo=tSetting.getSettingValue()+1;
		    String strDateTime = sdf.format(date);
		    List<ReturnStockUpdate> stkUpdateList= new ArrayList<ReturnStockUpdate>();
		    String poDetailIdList="";
		    
			if(!header.getBillDetailList().isEmpty())
			{
				boolean flag=false;
				CreditNoteHeader creditNoteHeader=new CreditNoteHeader();
				creditNoteHeader.setCrnHeaderId(0);
				creditNoteHeader.setCrnDate(currDate);
				creditNoteHeader.setCrnDatetime(strDateTime);
				creditNoteHeader.setCrnId(""+crnNo);
				creditNoteHeader.setCustId(header.getCustId());
				creditNoteHeader.setRemarks("Credit Note of Distribution Leakage");
				creditNoteHeader.setBillTempId(billTempId);
				
				float total=0;
				
			for(int i=0;i<header.getBillDetailList().size();i++)
			{
				
				int returnQty=Integer.parseInt(request.getParameter("returnQty"+i));
				int distLeakageQty=Integer.parseInt(request.getParameter("leakageQty"+i));
				if((returnQty+distLeakageQty)<=header.getBillDetailList().get(i).getBillQty())
				{
					header.getBillDetailList().get(i).setReturnQty(returnQty);
					header.getBillDetailList().get(i).setDistLeakageQty(distLeakageQty);
				}
				if(distLeakageQty>0)
				{
					flag=true;
					CreditNoteDetail creditNoteDetails=new CreditNoteDetail();
					creditNoteDetails.setCrnDetailId(0);
					creditNoteDetails.setCrnHeaderId(0);
					creditNoteDetails.setBatchId(Integer.parseInt(header.getBillDetailList().get(i).getBatchNo()));
					creditNoteDetails.setItemId(header.getBillDetailList().get(i).getItemId());
					creditNoteDetails.setScrapType(1);
					creditNoteDetails.setLeakageQty(distLeakageQty);
					creditNoteDetails.setExpireQty(0);
					creditNoteDetails.setPackDate(""+header.getBillDetailList().get(i).getBillDetailId());
					creditNoteDetails.setRate(header.getBillDetailList().get(i).getRate());
					creditNoteDetail.add(creditNoteDetails);
					total=total+(header.getBillDetailList().get(i).getRate()*distLeakageQty);
				}
				if(returnQty>0)
				{
					
					ReturnStockUpdate stk=new ReturnStockUpdate();
					stk.setPoDetailId(header.getBillDetailList().get(i).getPoDetailId());
					poDetailIdList=poDetailIdList+header.getBillDetailList().get(i).getPoDetailId()+",";
					stk.setReturnQty(returnQty);
					stkUpdateList.add(stk);
				}
				
			}
			creditNoteHeader.setCreditNoteDetailList(creditNoteDetail);
            creditNoteHeader.setGrandTotal(total);
			if(flag==true)
			{
	     	CreditNoteHeader crNote = rest.postForObject(Constants.url + "/saveCreditNote",creditNoteHeader,
						CreditNoteHeader.class);
		     map= new LinkedMultiValueMap<String,Object>();
		     map.add("settingValue",crnNo);
		     map.add("settingKey","crn_no");
		     Info settingRes=rest.postForObject(Constants.url+"/updateSetingValue",map,Info.class);
			}
			
			}
			header.setBillId(billId);
			header.setCollectedAmt(collectedAmt);
			header.setOutstandingAmt(outstandingAmt);
			header.setCratesReceived(cratesReceived);
			header.setCratesClBal(cratesBalQty);
			header.setCollectionPaymode(payMode);
			header.setRemarks(remarks);
			header.setGrandTotal(grandTotal);
			header.setIsSettled(1);
			List<BillPayment> billPaymentList=new ArrayList<BillPayment>();
			
			if(payMode==2)
			{
				
				for(int j=0;j<currencyListRes.size();j++)
				{
					
					float currValue=Float.parseFloat(request.getParameter("currValue"+j));
					int qty=Integer.parseInt(request.getParameter("qty"+j));
					
					if(qty>0)
					{
						BillPayment billPayment=new BillPayment();
						billPayment.setPayId(0);
						billPayment.setBillId(billId);
						billPayment.setCurrId((int)currValue);
						billPayment.setTranId("0");
						billPayment.setBankName("");
						billPayment.setChequeAmt(0);
						billPayment.setChequeNo("0");
						billPayment.setQty(qty);
						billPayment.setTotalAmt(qty*currValue);
						billPayment.setPayMode(payMode);
						billPaymentList.add(billPayment);
					}
				}

				
				
			}
			else if(payMode==1)
			{
				String chequeNo=request.getParameter("checkNo");
				String checkDate=request.getParameter("checkDate");
				Date date3=new SimpleDateFormat("dd-MM-yyyy").parse(checkDate);  
 
				
				float chequeAmt=Float.parseFloat(request.getParameter("checkAmt"));
				String bankName=request.getParameter("bankName");
				BillPayment billPayment=new BillPayment();
				billPayment.setPayId(0);
				billPayment.setBillId(billId);
				billPayment.setCurrId(0);
				billPayment.setTranId("0");
				billPayment.setChequeNo(chequeNo);
				billPayment.setBankName(bankName);
				billPayment.setPayMode(payMode);
				billPayment.setChequeAmt(chequeAmt);
				billPayment.setQty(0);
				billPayment.setTotalAmt(chequeAmt);
				billPayment.setChequeDate(dmyFormat.format(date1));
				billPaymentList.add(billPayment);
				
			}
			else if(payMode==3)
			{
				String chequeNo=request.getParameter("checkNo");
				String checkDate=request.getParameter("checkDate");
				Date date2=new SimpleDateFormat("dd-MM-yyyy").parse(checkDate);  
			    
				float chequeAmt=Float.parseFloat(request.getParameter("checkAmt"));
				String bankName=request.getParameter("bankName");
				BillPayment billPayment=new BillPayment();
				billPayment.setPayId(0);
				billPayment.setBillId(billId);
				billPayment.setCurrId(0);
				billPayment.setTranId("0");
				billPayment.setChequeNo(chequeNo);
				billPayment.setBankName(bankName);
				billPayment.setPayMode(payMode);
				billPayment.setChequeAmt(chequeAmt);
				billPayment.setChequeDate(dmyFormat.format(date1));

				billPayment.setQty(0);
				billPayment.setTotalAmt(chequeAmt);
				billPaymentList.add(billPayment);
				for(int j=0;j<currencyListRes.size();j++)
				{
					
					float currValue=Float.parseFloat(request.getParameter("currValue"+j));
					int qty=Integer.parseInt(request.getParameter("qty"+j));
					
					if(qty>0)
					{
						BillPayment billPayment1=new BillPayment();
						billPayment1.setPayId(0);
						billPayment1.setBillId(billId);
						billPayment1.setCurrId((int)currValue);
						billPayment1.setTranId("0");
						billPayment1.setBankName("");
						billPayment1.setChequeAmt(0);
						billPayment1.setChequeNo("0");
						billPayment1.setQty(qty);
						billPayment1.setTotalAmt(qty*currValue);
						billPayment1.setPayMode(payMode);
						billPaymentList.add(billPayment1);
					}
				}
				
			}
			
		    map = new LinkedMultiValueMap<String,Object>();
			map.add("custId", header.getCustId()); 
			Customer customer = rest.postForObject(Constants.url + "/master/getCustomerById",map, Customer.class);
			customer.setOutstandingAmt(outstandingAmt);
			customer.setCratesOpBal(cratesBalQty);
			System.err.println("customer"+customer.toString());
			System.err.println("header"+header.toString());
			System.err.println("hebillPaymentListader"+billPaymentList.toString());
				
		BillHeader	billHeaderRes=rest.postForObject(Constants.url+"/saveBill",header,BillHeader.class);
        if(billHeaderRes!=null)
        {
          if(!stkUpdateList.isEmpty())	
          {
        	  
        	  poDetailIdList=poDetailIdList.substring(0,poDetailIdList.length()-1);
        	  map= new LinkedMultiValueMap<String,Object>();
 		      map.add("poIdList",poDetailIdList);
        	 PoDetail[] poDetails=rest.postForObject(Constants.url+"/getPoDetailsBalance",map,PoDetail[].class);
        	  ArrayList<PoDetail> poDetailListRes=new ArrayList<PoDetail>(Arrays.asList(poDetails));
        	  
        	  for(int i=0;i<stkUpdateList.size();i++)
        	  {
        		  for(int j=0;j<poDetailListRes.size();j++)
            	  {
        			  if(stkUpdateList.get(i).getPoDetailId()==poDetailListRes.get(j).getPoDetailId())
        			  {
        				  int balance= poDetailListRes.get(j).getBalance();
        				  poDetailListRes.get(j).setBalance(balance+stkUpdateList.get(i).getReturnQty());
        			  }
        			  
            	  }
        	  }
        		for(int i=0;i<poDetailListRes.size();i++)
    			{
    				Date date3=new SimpleDateFormat("dd-MM-yyyy").parse(poDetailListRes.get(i).getMfgDate());  
    				poDetailListRes.get(i).setMfgDate(dmyFormat.format(date3));
    			}
        	  
        	  System.err.println("PO Detail List with updated Balance:"+poDetailListRes.toString());
        	  
        	  List<PoDetail> poListRes=rest.postForObject(Constants.url+"/updatePoDetailList", poDetailListRes, List.class);
		    	System.err.println("poListRes:"+poListRes.toString());
        	  
          }
        	
        	
		Info info = rest.postForObject(Constants.url + "/master/saveCustomer",customer,
				Info.class);
		
		Info billPaymentRes=rest.postForObject(Constants.url+"/master/insertBillPayment",billPaymentList,Info.class);
		
		map = new LinkedMultiValueMap<String,Object>();
		map.add("billTempId", billHeaderRes.getBillTempId()); 
		map.add("vehInKm",vehInKm); 
		Info infoRes=rest.postForObject(Constants.url+"/master/updateTvehicleKm",map,Info.class);
		
		map = new LinkedMultiValueMap<String,Object>();
		map.add("settingValue",billId);
		map.add("settingKey","bill_id");
		Info settingRes=rest.postForObject(Constants.url+"/updateSetingValue",map,Info.class);

        }
			
	} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/showAllTempAndSettleBill";
	}
	
	@RequestMapping(value = "/creditNote/{billTempId}", method = RequestMethod.GET)
	public ModelAndView creditNote(@PathVariable int billTempId, HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("bill/creditNote");
		try
		{
			
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String,Object>();
			map.add("billTempId", billTempId);
			GetBillHeader header =  rest.postForObject(Constants.url + "/getBillHeaderDetails",map, GetBillHeader.class);
			System.err.println("header"+header.toString());
			model.addObject("billHeader", header);
			model.addObject("billDetails", header.getBillDetailList());
			try {
				model.addObject("keySize", header.getBillDetailList().size());
				}catch (Exception e) {
					// TODO: handle exception
				}
			map = new LinkedMultiValueMap<String,Object>();
			map.add("settingKey", "crn_no");
			TSetting tSettingRes=rest.postForObject(Constants.url + "/getSettingValue",map, TSetting.class);
			int crnNo=tSettingRes.getSettingValue()+1;
			model.addObject("crnNo", crnNo);
		}catch(Exception e)
		{
			e.printStackTrace();
		}

		return model;
	}
	@RequestMapping(value = "/saveCreditNote", method = RequestMethod.POST)
	public String saveCreditNote(HttpServletRequest request, HttpServletResponse response) {

		try {
			
			int custId=Integer.parseInt(request.getParameter("custId"));
			int billTempId=Integer.parseInt(request.getParameter("billTempId"));
			int billId=Integer.parseInt(request.getParameter("billId"));
			int creditNo=Integer.parseInt(request.getParameter("creditNo"));
			String remark=request.getParameter("remark");
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String,Object>();
			map.add("billTempId", billTempId);
			GetBillHeader header =  rest.postForObject(Constants.url + "/getBillHeaderDetails",map, GetBillHeader.class);
			System.err.println("header"+header.toString());
			Date date = new Date();
			String currDate= new SimpleDateFormat("yyyy-MM-dd").format(date);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		    String strDateTime = sdf.format(date);
		    List<CreditNoteDetail> creditNoteDetail=new ArrayList<>();
		  
			if(!header.getBillDetailList().isEmpty()) {
				  boolean flag=false;
				
				CreditNoteHeader creditNoteHeader=new CreditNoteHeader();
				creditNoteHeader.setCrnHeaderId(0);
				creditNoteHeader.setCrnDate(currDate);
				creditNoteHeader.setCrnDatetime(strDateTime);
				creditNoteHeader.setCrnId(""+creditNo);
				creditNoteHeader.setCustId(custId);
				creditNoteHeader.setRemarks(remark);
				creditNoteHeader.setBillTempId(billTempId);
				
				float grandTotal=0;
				for(int i=0;i<header.getBillDetailList().size();i++)
				{
					int expireQty=Integer.parseInt(request.getParameter("expireQty"+i));
					int leakageQty=Integer.parseInt(request.getParameter("leakageQty"+i));
					if(expireQty>0||leakageQty>0)
					{
						flag=true;
						CreditNoteDetail creditNoteDetails=new CreditNoteDetail();
						creditNoteDetails.setCrnDetailId(0);
						creditNoteDetails.setCrnHeaderId(0);
						creditNoteDetails.setBatchId(Integer.parseInt(header.getBillDetailList().get(i).getBatchNo()));
						creditNoteDetails.setItemId(header.getBillDetailList().get(i).getItemId());
						creditNoteDetails.setScrapType(2);
						creditNoteDetails.setExpireQty(expireQty);
						creditNoteDetails.setLeakageQty(leakageQty);
						creditNoteDetails.setPackDate(""+header.getBillDetailList().get(i).getBillDetailId());
						creditNoteDetails.setRate(header.getBillDetailList().get(i).getRate());
						creditNoteDetail.add(creditNoteDetails);
						
						float total=(header.getBillDetailList().get(i).getRate())*(expireQty+leakageQty);
						grandTotal=grandTotal+total;
					}
					
					
				}
				creditNoteHeader.setCreditNoteDetailList(creditNoteDetail);
				creditNoteHeader.setGrandTotal(grandTotal);
				if(flag==true)
				{
			CreditNoteHeader crNote = rest.postForObject(Constants.url + "/saveCreditNote",creditNoteHeader,
							CreditNoteHeader.class);
			
			map = new LinkedMultiValueMap<String,Object>();
			map.add("billTempId", billTempId);
			Info info=rest.postForObject(Constants.url + "/updateCrnGenerated",map, Info.class);
			
			 map= new LinkedMultiValueMap<String,Object>();
			 map.add("settingValue",creditNo);
			 map.add("settingKey","crn_no");
			 Info settingRes=rest.postForObject(Constants.url+"/updateSetingValue",map,Info.class);
				}
			}
		}
		catch (Exception e) {
			
		}
		return "redirect:/showAllTempAndSettleBill";
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
	@RequestMapping(value = "/creditNotes", method = RequestMethod.GET)
	public ModelAndView creditNotes(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("bill/creditNotes");
		try
		{
			 Date date = new Date(); 
			 SimpleDateFormat show = new SimpleDateFormat("dd-MM-yyyy");
			 SimpleDateFormat consume = new SimpleDateFormat("yyyy-MM-dd");
			 
			 MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
			 map.add("fromDate",consume.format(date));
			 map.add("toDate",consume.format(date));
			  getCrnHeaderList =  rest.postForObject(Constants.url + "/getAllCrn",map, List .class);
			 model.addObject("fromDate", show.format(date));
			 model.addObject("toDate", show.format(date));
			 model.addObject("crnHeaderList", getCrnHeaderList);
			 
		}catch(Exception e)
		{
			e.printStackTrace();
		}

		return model;
	}
	@RequestMapping(value = "/getCrnbetweenDate", method = RequestMethod.GET)
	@ResponseBody
	public List<CrnHeader> getCrnbetweenDate(HttpServletRequest request, HttpServletResponse response) {

	
		try
		{
			String fromDate = request.getParameter("fromDate");
			String toDate = request.getParameter("toDate");
			
			 MultiValueMap<String, Object> map = new LinkedMultiValueMap<>(); 
			 map.add("fromDate",DateConvertor.convertToYMD(fromDate));
			 map.add("toDate",DateConvertor.convertToYMD(toDate));
			 getCrnHeaderList =  rest.postForObject(Constants.url + "/getAllCrn",map, List .class);
			 
		}catch(Exception e)
		{
			e.printStackTrace();
		}

		return getCrnHeaderList;
	}
	@RequestMapping(value = "/crnDetails/{crnHeaderId}", method = RequestMethod.GET)
	public ModelAndView crnDetails(@PathVariable int crnHeaderId, HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("bill/creditNoteDetails");
		try
		{
			 MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
			 map.add("crnHeaderId",crnHeaderId); 
			  CrnHeader   crnHeaderRes=  rest.postForObject(Constants.url + "/getCrnById",map, CrnHeader.class);
			
			 model.addObject("crnHeader",crnHeaderRes); 
			 
		}catch(Exception e)
		{
			e.printStackTrace();
		}

		return model;
	}

	@RequestMapping(value = "pdf/showBillsPdf/{billTempId}", method = RequestMethod.GET)
	public ModelAndView showBillsPdf(@PathVariable("billTempId")String[] billTempIds,HttpServletRequest request, HttpServletResponse response) {
      System.out.println("IN Show bill PDF Method :/showBillPdf");
		ModelAndView model = new ModelAndView("bill/allBillPdf");
   		
   		try {
   			RestTemplate rest = new RestTemplate();
			String strBillTempIds=new String();
			for(int i=0;i<billTempIds.length;i++)
			{
				strBillTempIds=strBillTempIds+","+billTempIds[i];
			}
			strBillTempIds=strBillTempIds.substring(1);
			
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("billTempIds",strBillTempIds);
			BillHeaderReport[] billHeaderRes = rest.postForObject(Constants.url + "/findBillsById",map,BillHeaderReport[].class);
			ArrayList<BillHeaderReport> billHeaders = new ArrayList<BillHeaderReport>(Arrays.asList(billHeaderRes));
   			
   		model.addObject("billHeaderList", billHeaders);
   		}
   		catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}
	private Dimension format = PD4Constants.A4;
	private boolean landscapeValue = false;
	private int topValue = 8;
	private int leftValue = 0;
	private int rightValue = 0;
	private int bottomValue = 8;
	private String unitsValue = "m";
	private String proxyHost = "";
	private int proxyPort = 0;

	private int userSpaceWidth = 750;
	private static int BUFFER_SIZE = 1024;
	
	@RequestMapping(value = "/pdf", method = RequestMethod.GET)
	public void showPDF(HttpServletRequest request, HttpServletResponse response) {

		String url = request.getParameter("url");
		System.out.println("URL " + url);
		// http://monginis.ap-south-1.elasticbeanstalk.com
	    File f = new File("/report.pdf");
		//File f = new File("/home/ats-11/pdf/ordermemo221.pdf");
		//File f = new File("/Users/MIRACLEINFOTAINMENT/ATS/uplaods/reports/ordermemo221.pdf");

		System.out.println("I am here " + f.toString());
		try {
			runConverter(Constants.ReportURL + url, f, request, response);
			System.out.println("Come on lets get ");
		} catch (IOException e) {
			// TODO Auto-generated catch block

			System.out.println("Pdf conversion exception " + e.getMessage());
		}

		// get absolute path of the application
		ServletContext context = request.getSession().getServletContext();
		String appPath = context.getRealPath("");
		String filename = "ordermemo221.pdf";
		 String filePath = "/report.pdf";
		//String filePath = "/home/ats-11/pdf/ordermemo221.pdf";
		//String filePath = "/Users/MIRACLEINFOTAINMENT/ATS/uplaods/reports/ordermemo221.pdf";

		// construct the complete absolute path of the file
		String fullPath = appPath + filePath;
		File downloadFile = new File(filePath);
		FileInputStream inputStream = null;
		try {
			inputStream = new FileInputStream(downloadFile);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			// get MIME type of the file
			String mimeType = context.getMimeType(fullPath);
			if (mimeType == null) {
				// set to binary type if MIME mapping not found
				mimeType = "application/pdf";
			}
			System.out.println("MIME type: " + mimeType);

			String headerKey = "Content-Disposition";

			// response.addHeader("Content-Disposition", "attachment;filename=report.pdf");
			response.setContentType("application/pdf");

			// get output stream of the response
			OutputStream outStream;

			outStream = response.getOutputStream();

			byte[] buffer = new byte[BUFFER_SIZE];
			int bytesRead = -1;

			// write bytes read from the input stream into the output stream

			while ((bytesRead = inputStream.read(buffer)) != -1) {
				outStream.write(buffer, 0, bytesRead);
			}

			inputStream.close();
			outStream.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void runConverter(String urlstring, File output, HttpServletRequest request, HttpServletResponse response)
			throws IOException {

		if (urlstring.length() > 0) {
			if (!urlstring.startsWith("http://") && !urlstring.startsWith("file:")) {
				urlstring = "http://" + urlstring;
			}
			System.out.println("PDF URL " + urlstring);
			java.io.FileOutputStream fos = new java.io.FileOutputStream(output);

			PD4ML pd4ml = new PD4ML();

			try {

				PD4PageMark footer = new PD4PageMark();  
				footer.setPageNumberTemplate("page $[page] of $[total]");  
				footer.setTitleAlignment(PD4PageMark.LEFT_ALIGN);  
				footer.setPageNumberAlignment(PD4PageMark.RIGHT_ALIGN);  
				footer.setInitialPageNumber(1);  
				footer.setFontSize(8);  
				footer.setAreaHeight(15); 
			
				pd4ml.setPageFooter(footer);

			} catch (Exception e) {
				System.out.println("Pdf conversion method excep " + e.getMessage());
			}
			try {
				pd4ml.setPageSize(landscapeValue ? pd4ml.changePageOrientation(format) : format);
			} catch (Exception e) {
				System.out.println("Pdf conversion ethod excep " + e.getMessage());
			}

			if (unitsValue.equals("mm")) {
				pd4ml.setPageInsetsMM(new Insets(topValue, leftValue, bottomValue, rightValue));
			} else {
				pd4ml.setPageInsets(new Insets(topValue, leftValue, bottomValue, rightValue));
			}

			pd4ml.setHtmlWidth(userSpaceWidth);

			
			

			pd4ml.render(urlstring, fos);

		}
	}
}
