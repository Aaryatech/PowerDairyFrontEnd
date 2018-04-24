package com.dairypower.admin.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
import com.dairypower.admin.model.BillWisePurchaseReport;
import com.dairypower.admin.model.CustomerWiseConReport;
import com.dairypower.admin.model.DateWisePurchaseReport;
import com.dairypower.admin.model.ExportToExcel;
import com.dairypower.admin.model.ItemWisePurchaseReport;
import com.dairypower.admin.model.VehicleWiseReport;
import com.dairypower.admin.model.Vehicle;

@Controller
public class ReportController {

	@RequestMapping(value = "/viewBillWisePurchaseReport", method = RequestMethod.GET)
	public ModelAndView viewBill(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("reports/purchase/billWisePurchaseReport");
		return model;

	}

	@RequestMapping(value = "/viewDateWisePurchaseReport", method = RequestMethod.GET)
	public ModelAndView viewDate(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("reports/purchase/dateWisePurchaseReport");

		return model;
	}

	@RequestMapping(value = "/viewItemWisePurchaseReport", method = RequestMethod.GET)
	public ModelAndView viewItem(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("reports/purchase/itemWisePurchaseReport");

		return model;
	}

	@RequestMapping(value = "/viewCustomerWiseConsumptionReport", method = RequestMethod.GET)
	public ModelAndView viewCustomer(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("reports/purchase/customerWiseConsumptionReport");

		return model;
	}

	@RequestMapping(value = "/viewVehicleWiseReport", method = RequestMethod.GET)
	public ModelAndView Vehicle(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("reports/purchase/viewVehicleWiseReport");
		RestTemplate restTemplate = new RestTemplate();
		try {

			List<Vehicle> vehicleList = restTemplate.getForObject(Constants.url + "/master/getAllVehicles", List.class);
			System.out.println("Assign Project" + vehicleList.toString());
			System.out.println("vehicleList " + vehicleList);
			model.addObject("vehicleList", vehicleList);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}

	// ----------------------------------Bill Wise Purchase
	// Report---------------------------------------------
	@RequestMapping(value = "/findBillWisePurchase", method = RequestMethod.GET)
	public @ResponseBody List<BillWisePurchaseReport> getBillWisePurchase(HttpServletRequest request,
			HttpServletResponse response) {
		List<BillWisePurchaseReport> billWisePurchaseReportList = null;
		try {
			System.out.println("in method");
			String fromDate = request.getParameter("fromDate");
			System.out.println("fromDate" + fromDate);

			String toDate = request.getParameter("toDate");
			System.out.println("toDate" + toDate);

			RestTemplate restTemplate = new RestTemplate();

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

			map.add("fromDate", DateConvertor.convertToYMD(fromDate));
			map.add("toDate", DateConvertor.convertToYMD(toDate));

			BillWisePurchaseReport[] billWisePurchaseList = restTemplate.postForObject(Constants.url + "Test/getBillwisePurchase", map,
					BillWisePurchaseReport[].class);

			 billWisePurchaseReportList=new ArrayList<BillWisePurchaseReport>(Arrays.asList(billWisePurchaseList));
			System.out.println("billWisePurchaseReport" + billWisePurchaseReportList.toString());
			
			
			// export to excel

			List<ExportToExcel> exportToExcelList = new ArrayList<ExportToExcel>();

			ExportToExcel expoExcel = new ExportToExcel();
			List<String> rowData = new ArrayList<String>();


			 rowData.add("Sr. No"); 
			rowData.add("PO No.");
			rowData.add("PO Date");
			rowData.add("Total Amt");
			rowData.add("Crates Received");
			rowData.add("Po Remark");
			

			expoExcel.setRowData(rowData);
			exportToExcelList.add(expoExcel);
			int cnt=1;
			for (int i = 0; i < billWisePurchaseReportList.size(); i++) {
				expoExcel = new ExportToExcel();
				rowData = new ArrayList<String>();
                cnt=cnt+i;
				 rowData.add(""+(cnt)); 
				rowData.add("" + billWisePurchaseReportList.get(i).getPoId());
				rowData.add("" + billWisePurchaseReportList.get(i).getPoDate());
				rowData.add("" + billWisePurchaseReportList.get(i).getPoTotal());
				rowData.add("" + billWisePurchaseReportList.get(i).getCratesReceivedQty());
				rowData.add("" + billWisePurchaseReportList.get(i).getPoRemarks());
			
				expoExcel.setRowData(rowData);
				exportToExcelList.add(expoExcel);

			}

			HttpSession session = request.getSession();
			session.setAttribute("exportExcelList", exportToExcelList);
			session.setAttribute("excelName", "BillwisePurchaseList");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}

		return billWisePurchaseReportList;

	}

	// ----------------------------------Date Wise Purchase
	// Report---------------------------------------------
	@RequestMapping(value = "/findDateWisePurchase", method = RequestMethod.GET)
	public @ResponseBody List<DateWisePurchaseReport> findDateWisePurchase(HttpServletRequest request,
			HttpServletResponse response) {
		List<DateWisePurchaseReport> dateWisePurchaseReport = null;
		try {
			System.out.println("in method");
			String fromDate = request.getParameter("fromDate");
			System.out.println("fromDate" + fromDate);

			String toDate = request.getParameter("toDate");
			System.out.println("toDate" + toDate);

			RestTemplate restTemplate = new RestTemplate();

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

			map.add("fromDate", DateConvertor.convertToYMD(fromDate));
			map.add("toDate", DateConvertor.convertToYMD(toDate));

			dateWisePurchaseReport = restTemplate.postForObject(Constants.url + "Test/getDatewisePurchase", map,
					List.class);

			System.out.println("dateWisePurchaseReport" + dateWisePurchaseReport.toString());
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}

		return dateWisePurchaseReport;

	}

	// ----------------------------------Item Wise Purchase
	// Report---------------------------------------------
	@RequestMapping(value = "/findItemWisePurchase", method = RequestMethod.GET)
	public @ResponseBody List<ItemWisePurchaseReport> getItemWisePurchase(HttpServletRequest request,
			HttpServletResponse response) {
		List<ItemWisePurchaseReport> itemWisePurchaseReport = null;
		try {
			System.out.println("in method");
			String fromDate = request.getParameter("fromDate");
			System.out.println("fromDate" + fromDate);

			String toDate = request.getParameter("toDate");
			System.out.println("toDate" + toDate);

			RestTemplate restTemplate = new RestTemplate();

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

			map.add("fromDate", DateConvertor.convertToYMD(fromDate));
			map.add("toDate", DateConvertor.convertToYMD(toDate));

			itemWisePurchaseReport = restTemplate.postForObject(Constants.url + "Test/getItemwisePurchase", map,
					List.class);

			System.out.println("billWisePurchaseReport" + itemWisePurchaseReport.toString());
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}

		return itemWisePurchaseReport;

	}

	// ---------------------------------Customer Wise Consumption Report
	// Report---------------------------------------------
	@RequestMapping(value = "/findCustomerWiseConsumption", method = RequestMethod.GET)
	public @ResponseBody List<CustomerWiseConReport> getCustomerWiseConsumption(HttpServletRequest request,
			HttpServletResponse response) {
		List<CustomerWiseConReport> customerWiseConReportList = null;
		try {
			System.out.println("in method");
			String fromDate = request.getParameter("fromDate");
			System.out.println("fromDate" + fromDate);

			String toDate = request.getParameter("toDate");
			System.out.println("toDate" + toDate);

			RestTemplate restTemplate = new RestTemplate();

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

			map.add("fromDate", DateConvertor.convertToYMD(fromDate));
			map.add("toDate", DateConvertor.convertToYMD(toDate));

			CustomerWiseConReport[] cutwiseCons = restTemplate.postForObject(Constants.url + "Test/getAllCustomerwiseReport", map,
					CustomerWiseConReport[].class);
			

			customerWiseConReportList=new ArrayList<CustomerWiseConReport>(Arrays.asList(cutwiseCons));
			
			
System.out.println("new List cust wise consumption " +customerWiseConReportList.toString());
			// export to excel

			List<ExportToExcel> exportToExcelList = new ArrayList<ExportToExcel>();

			ExportToExcel expoExcel = new ExportToExcel();
			List<String> rowData = new ArrayList<String>();
			
			
			
			
			 rowData.add("Sr. No"); 
				rowData.add("Customer Name");
				rowData.add("Bill Amount");
				rowData.add("Received Amount");
				rowData.add("OS Amount");
				rowData.add("Payment Mode");
				rowData.add("Crates OP");
				rowData.add("Crates Issued");
				rowData.add("Crates CL");
				rowData.add("Crates OS");
				rowData.add("Out Km");
				


			expoExcel.setRowData(rowData);
			exportToExcelList.add(expoExcel);
			int cnt=1;
			for (int i = 0; i < customerWiseConReportList.size(); i++) {
				expoExcel = new ExportToExcel();
				rowData = new ArrayList<String>();
                cnt=cnt+i;
				 rowData.add(""+(cnt)); 
				rowData.add("" + customerWiseConReportList.get(i).getCustName());
				rowData.add("" + customerWiseConReportList.get(i).getGrandTotal());
				rowData.add("" + customerWiseConReportList.get(i).getCollectedAmt());
				rowData.add("" + customerWiseConReportList.get(i).getOutstandingAmt());
				rowData.add("" + customerWiseConReportList.get(i).getCollectionPaymode());
			
				expoExcel.setRowData(rowData);
				exportToExcelList.add(expoExcel);

			}

			HttpSession session = request.getSession();
			session.setAttribute("exportExcelList", exportToExcelList);
			session.setAttribute("excelName", "BillwisePurchaseList");

			System.out.println("customerWiseConReportList" + customerWiseConReportList.toString());
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}

		return customerWiseConReportList;

	}

	// ----------------------------------Vehicle Wise Report--------
	@RequestMapping(value = "/findVehicleWiseWiseReport", method = RequestMethod.GET)
	public @ResponseBody List<VehicleWiseReport> getVehicleWise(HttpServletRequest request,
			HttpServletResponse response) {
		List<VehicleWiseReport> vehicleWiseReport = null;
		try {
			System.out.println("in method");
			String fromDate = request.getParameter("fromDate");
			System.out.println("fromDate" + fromDate);

			String toDate = request.getParameter("toDate");
			System.out.println("toDate" + toDate);

			String tVehId = request.getParameter("vehId");
			System.out.println("tVehId" + tVehId);

			RestTemplate restTemplate = new RestTemplate();

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

			map.add("fromDate", DateConvertor.convertToYMD(fromDate));
			map.add("toDate", DateConvertor.convertToYMD(toDate));

			map.add("tVehId", tVehId);

			vehicleWiseReport = restTemplate.postForObject(Constants.url + "Test/getAllVehiclewiseReport", map,
					List.class);

			System.out.println("vehicleWiseReport" + vehicleWiseReport.toString());
			
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}

		return vehicleWiseReport;

	}

}
