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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.dairypower.admin.common.Constants;
import com.dairypower.admin.common.DateConvertor;
import com.dairypower.admin.model.BillDetail;
import com.dairypower.admin.model.BillHeader;
import com.dairypower.admin.model.BillPayment;
import com.dairypower.admin.model.CreditNoteDetail;
import com.dairypower.admin.model.CreditNoteHeader;
import com.dairypower.admin.model.Currency;
import com.dairypower.admin.model.Customer;
import com.dairypower.admin.model.GetBillHeader;
import com.dairypower.admin.model.GetCratesStock;
import com.dairypower.admin.model.GetItem;
import com.dairypower.admin.model.GetPoDetail;
import com.dairypower.admin.model.Info;
import com.dairypower.admin.model.PoDetail;
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
			GetBillHeader[] headerList =  rest.postForObject(Constants.url + "/getSettledBills",map, GetBillHeader[].class);
            ArrayList<GetBillHeader> billHeaderList=new ArrayList<GetBillHeader>(Arrays.asList(headerList));
			model.addObject("billHeaderList", billHeaderList);
			 map = new LinkedMultiValueMap<String,Object>();
			map.add("date", sf1.format(date));
			map.add("isSettled",1);
			GetBillHeader[] headersList =  rest.postForObject(Constants.url + "/getSettledBills",map, GetBillHeader[].class);
            ArrayList<GetBillHeader> billHeadersList=new ArrayList<GetBillHeader>(Arrays.asList(headersList));
            model.addObject("billHeadersList", billHeadersList);
            
            map = new LinkedMultiValueMap<String,Object>();
			map.add("date", sf1.format(date));
            BillHeader[] allBills =  rest.postForObject(Constants.url + "/getAllBillHeader",map, BillHeader[].class);
            ArrayList<BillHeader> billsList=new ArrayList<BillHeader>(Arrays.asList(allBills));
           
            int outstandingCrates=0;
            if(!billsList.isEmpty())
            {
            	for(int i=0;i<billsList.size();i++)
            	{
            		if(billsList.get(i).getIsSettled()==1)
            		{
            			outstandingCrates=outstandingCrates+(billsList.get(i).getCratesClBal());
            		}else
            		{
            			outstandingCrates=outstandingCrates+(billsList.get(i).getCratesOpBal()+billsList.get(i).getCratesIssued());
            		}
            	}
            }
            outstandingCrates=outstandingCrates+0;
            model.addObject("outstandingCrates", outstandingCrates);
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
			GetBillHeader[] headerList =  rest.postForObject(Constants.url + "/getSettledBills",map, GetBillHeader[].class);
            ArrayList<GetBillHeader> billHeaderList=new ArrayList<GetBillHeader>(Arrays.asList(headerList));
			model.addObject("billHeaderList", billHeaderList);
			 map = new LinkedMultiValueMap<String,Object>();
			map.add("date", sf1.format(date1));
			map.add("isSettled",1);
			GetBillHeader[] headersList =  rest.postForObject(Constants.url + "/getSettledBills",map, GetBillHeader[].class);
            ArrayList<GetBillHeader> billHeadersList=new ArrayList<GetBillHeader>(Arrays.asList(headersList));
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
			  
			  SimpleDateFormat sf = new SimpleDateFormat("dd-MM-yyyy");
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
		billHeader.setGrandTotal(total);
		billHeader.setVehId(vehId);
		billHeader.setBillDetailList(billDetailList);
		
		System.err.println("billHeader:"+billHeader.toString());
		billHeaderRes=restTemplate.postForObject(Constants.url+"/saveBill",billHeader,BillHeader.class);
		System.err.println("poDetailList:"+poDetailList.toString());
	    List<PoDetail> poListRes=restTemplate.postForObject(Constants.url+"/updatePoDetailList", poDetailList, List.class);
    	System.err.println("poListRes:"+poListRes.toString());
		if(billHeaderRes!=null)
		{
			Date date = new Date();
			String currdDate= new SimpleDateFormat("dd-MM-yyyy").format(date);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		    String strDateTime = sdf.format(date);
		    
			TVehicle tVehicleRes=new TVehicle();
			tVehicleRes.settVehId(0);
			tVehicleRes.setVehId(vehId);
			tVehicleRes.setBillTempId(billHeaderRes.getBillTempId());
			tVehicleRes.setInKms(0);
			tVehicleRes.setDate(currdDate);
			tVehicleRes.setDatetime(strDateTime);
			tVehicleRes.setEntryBy(1);
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
			
			Currency[] currencyList =  rest.getForObject(Constants.url + "/master/getAllCurrency", Currency[].class);
			ArrayList<Currency> currencyListRes=new ArrayList<Currency>(Arrays.asList(currencyList));
			
			map = new LinkedMultiValueMap<String,Object>();
			map.add("settingKey", "bill_id");
			TSetting tSettingRes=rest.postForObject(Constants.url + "/getSettingValue",map, TSetting.class);
			int billId=tSettingRes.getSettingValue()+1;
			
		    List<CreditNoteDetail> creditNoteDetail=new ArrayList<>();//
			Date date = new Date();
			String currDate= new SimpleDateFormat("dd-MM-yyyy").format(date);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
			
			map = new LinkedMultiValueMap<String,Object>();
			map.add("settingKey", "crn_no");
			TSetting tSetting=rest.postForObject(Constants.url + "/getSettingValue",map, TSetting.class);
			int crnNo=tSetting.getSettingValue()+1;
		    String strDateTime = sdf.format(date);
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
				creditNoteHeader.setScrapType(2);
				creditNoteHeader.setBillTempId(billTempId);
				
			for(int i=0;i<header.getBillDetailList().size();i++)
			{
				
				int returnQty=Integer.parseInt(request.getParameter("returnQty"+i));
				int distLeakageQty=Integer.parseInt(request.getParameter("leakageQty"+i));
				if((returnQty+distLeakageQty)<=header.getBillDetailList().get(i).getBillQty())
				{
					header.getBillDetailList().get(i).setReturnQty(returnQty);
					header.getBillDetailList().get(i).setDistLeakageQty(distLeakageQty);
				}
				if(distLeakageQty!=0)
				{
					flag=true;
					CreditNoteDetail creditNoteDetails=new CreditNoteDetail();
					creditNoteDetails.setCrnDetailId(0);
					creditNoteDetails.setCrnHeaderId(0);
					creditNoteDetails.setBatchId(Integer.parseInt(header.getBillDetailList().get(i).getBatchNo()));
					creditNoteDetails.setItemId(header.getBillDetailList().get(i).getItemId());
					creditNoteDetails.setQty(distLeakageQty);
					creditNoteDetails.setPackDate(currDate);
					creditNoteDetails.setRate(header.getBillDetailList().get(i).getRate());
					creditNoteDetail.add(creditNoteDetails);
				}
			}
			creditNoteHeader.setCreditNoteDetailList(creditNoteDetail);

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
						billPayment.setQty(qty);
						billPayment.setTotalAmt(qty*currValue);
						billPaymentList.add(billPayment);
					}
				}

				
				
			}
			else if(payMode==1)
			{
				String checkNo=request.getParameter("checkNo");
				BillPayment billPayment=new BillPayment();
				billPayment.setPayId(0);
				billPayment.setBillId(billId);
				billPayment.setCurrId(0);
				billPayment.setTranId(checkNo);
				billPayment.setQty(0);
				billPayment.setTotalAmt(0);
				billPaymentList.add(billPayment);
				
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
			String currDate= new SimpleDateFormat("dd-MM-yyyy").format(date);
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
				creditNoteHeader.setScrapType(1);
				creditNoteHeader.setBillTempId(billTempId);
				
				for(int i=0;i<header.getBillDetailList().size();i++)
				{
					int expireQty=Integer.parseInt(request.getParameter("expireQty"+i));
					int leakageQty=Integer.parseInt(request.getParameter("leakageQty"+i));
					if(expireQty!=0)
					{
						flag=true;
						CreditNoteDetail creditNoteDetails=new CreditNoteDetail();
						creditNoteDetails.setCrnDetailId(0);
						creditNoteDetails.setCrnHeaderId(0);
						creditNoteDetails.setBatchId(Integer.parseInt(header.getBillDetailList().get(i).getBatchNo()));
						creditNoteDetails.setItemId(header.getBillDetailList().get(i).getItemId());
						creditNoteDetails.setQty(expireQty);
						creditNoteDetails.setPackDate(currDate);
						creditNoteDetails.setRate(header.getBillDetailList().get(i).getRate());
						creditNoteDetail.add(creditNoteDetails);
					}
					if(leakageQty!=0)
					{
						flag=true;
						CreditNoteDetail creditNoteDetails=new CreditNoteDetail();
						creditNoteDetails.setCrnDetailId(0);
						creditNoteDetails.setCrnHeaderId(0);
						creditNoteDetails.setBatchId(Integer.parseInt(header.getBillDetailList().get(i).getBatchNo()));
						creditNoteDetails.setItemId(header.getBillDetailList().get(i).getItemId());
						creditNoteDetails.setQty(leakageQty);
						creditNoteDetails.setPackDate(currDate);

						creditNoteDetails.setRate(header.getBillDetailList().get(i).getRate());
						creditNoteDetail.add(creditNoteDetails);
					}
					
				}
				creditNoteHeader.setCreditNoteDetailList(creditNoteDetail);
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

}
