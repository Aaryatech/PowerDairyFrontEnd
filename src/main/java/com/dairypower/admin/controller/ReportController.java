package com.dairypower.admin.controller;

import java.util.ArrayList;
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
import com.dairypower.admin.model.ItemWisePurchaseReport;
import com.dairypower.admin.model.VehicleWiseReport;

@Controller
public class ReportController {

	@RequestMapping(value = "/viewBillWisePurchaseReport", method = RequestMethod.GET)
	public ModelAndView viewBill(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("reports/purchase/billWisePurchaseReport");
		try {

		} catch (Exception e) {
			e.printStackTrace();

		}
		return model;

	}

	@RequestMapping(value = "/viewDateWisePurchaseReport", method = RequestMethod.GET)
	public ModelAndView viewDate(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("reports/purchase/dateWisePurchaseReport");
		try {

		} catch (Exception e) {
			e.printStackTrace();

		}
		return model;
	}

	@RequestMapping(value = "/viewItemWisePurchaseReport", method = RequestMethod.GET)
	public ModelAndView viewItem(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("reports/purchase/itemWisePurchaseReport");
		try {

		} catch (Exception e) {
			e.printStackTrace();

		}
		return model;
	}

	@RequestMapping(value = "/viewCustomerWiseConsumptionReport", method = RequestMethod.GET)
	public ModelAndView viewCustomer(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("reports/purchase/customerWiseConsumptionReport");
		try {

		} catch (Exception e) {
			e.printStackTrace();

		}
		return model;
	}

	@RequestMapping(value = "/viewVehicleWiseReport", method = RequestMethod.GET)
	public ModelAndView viewVehicle(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("reports/purchase/viewVehicleWiseReport");
		try {

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
		List<BillWisePurchaseReport> billWisePurchaseReport = null;
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

			billWisePurchaseReport = restTemplate.postForObject(Constants.url + "Test/getBillwisePurchase", map,
					List.class);

			System.out.println("billWisePurchaseReport" + billWisePurchaseReport.toString());
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}

		return billWisePurchaseReport;

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

	// ----------------------------------Bill Wise Purchase
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

	// ----------------------------------Bill Wise Purchase
	// Report---------------------------------------------
	@RequestMapping(value = "/findCustomerWiseConsumption", method = RequestMethod.GET)
	public @ResponseBody List<CustomerWiseConReport> getCustomerWiseConsumption(HttpServletRequest request,
			HttpServletResponse response) {
		List<CustomerWiseConReport> customerWiseConReport = null;
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

			customerWiseConReport = restTemplate.postForObject(Constants.url + "Test/getAllCustomerwiseReport", map,
					List.class);

			System.out.println("customerWiseConReport" + customerWiseConReport.toString());
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}

		return customerWiseConReport;

	}

	// ----------------------------------Bill Wise Purchase Report--------
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

			String tVehId = request.getParameter("tVehId");
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
