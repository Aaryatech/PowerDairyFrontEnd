package com.dairypower.admin.controller;

import java.io.BufferedInputStream;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
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
import com.dairypower.admin.model.BillWisePurchaseReport;
import com.dairypower.admin.model.BillwiseConsumption;
import com.dairypower.admin.model.CatwiseConsumption;
import com.dairypower.admin.model.CatwiseReport;
import com.dairypower.admin.model.Customer;
import com.dairypower.admin.model.CustomerWiseConReport;
import com.dairypower.admin.model.DateWisePurchaseReport;
import com.dairypower.admin.model.DatewiseSaleReport;
import com.dairypower.admin.model.ExportToExcel;
import com.dairypower.admin.model.ItemCategory;
import com.dairypower.admin.model.ItemWisePurchaseReport;
import com.dairypower.admin.model.VehicleWiseReport;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.dairypower.admin.model.Vehicle;

@Controller
public class ReportController {

	List<BillWisePurchaseReport> billWisePurchaseReportList = null;

	List<BillwiseConsumption> billwiseConsumptionList = null;

	List<CustomerWiseConReport> customerWiseConReportList = null;
	
	List<CatwiseConsumption> catwiseConsumptionList = null;

	List<DateWisePurchaseReport> dateWisePurchaseReport = null;

	List<ItemWisePurchaseReport> itemWisePurchaseReport = null;

	List<VehicleWiseReport> vehicleWiseReport = null;
	
	List<CatwiseReport> catwiseReportList=null;
	
	List<DatewiseSaleReport> datewiseConsumptionList=null;

	@RequestMapping(value = "/viewBillWisePurchaseReport", method = RequestMethod.GET)
	public ModelAndView viewBill(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("reports/purchase/billWisePurchaseReport");
		return model;

	}

	@RequestMapping(value = "/viewBillWiseConsumptionReport", method = RequestMethod.GET)
	public ModelAndView viewBillConPur(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("reports/sales/billwiseConsumption");
		RestTemplate rest = new RestTemplate();

		 List<Customer> customerList = rest.getForObject(Constants.url + "/master/getAllCustomer", List.class);
  		 
		    model.addObject("customerList", customerList);
		return model;

	}
	
	@RequestMapping(value = "/viewDatewiseConsumptionReport", method = RequestMethod.GET)
	public ModelAndView viewDatewiseConsumptionReport(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("reports/sales/datewiseConsumption");
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
	
	
	@RequestMapping(value = "/viewCategoryWiseConsumptionReport", method = RequestMethod.GET)
	public ModelAndView viewCatiseCon(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("reports/sales/categorywiseConsumption");
		try {
			RestTemplate restTemplate = new RestTemplate();

			List<ItemCategory> itemCategoryList =  restTemplate.getForObject(Constants.url + "/master/getAllCategories", List.class);
			model.addObject("catList", itemCategoryList);
			
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		return model;

	}
	@RequestMapping(value = "/viewCategoryWiseReport", method = RequestMethod.GET)
	public ModelAndView viewCategoryWiseReport(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("reports/sales/categoryWiseReport");
		try {
			RestTemplate restTemplate = new RestTemplate();

			List<ItemCategory> itemCategoryList =  restTemplate.getForObject(Constants.url + "/master/getAllCategories", List.class);
			model.addObject("catList", itemCategoryList);
			
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		return model;

	}


	@RequestMapping(value = "/viewCustomerWiseConsumptionReport", method = RequestMethod.GET)
	public ModelAndView viewCustomer(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("reports/purchase/customerWiseConsumptionReport");
           try
           {	RestTemplate rest = new RestTemplate();

        	   List<Customer> customerList = rest.getForObject(Constants.url + "/master/getAllCustomer", List.class);
      		 
   			    model.addObject("customerList", customerList);
           }catch (Exception e) {
			e.printStackTrace();
		}
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

			BillWisePurchaseReport[] billWisePurchaseList = restTemplate
					.postForObject(Constants.url + "Test/getBillwisePurchase", map, BillWisePurchaseReport[].class);

			billWisePurchaseReportList = new ArrayList<BillWisePurchaseReport>(Arrays.asList(billWisePurchaseList));
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
			int cnt = 1;
			for (int i = 0; i < billWisePurchaseReportList.size(); i++) {
				expoExcel = new ExportToExcel();
				rowData = new ArrayList<String>();
				cnt = cnt + i;
				rowData.add("" + (cnt));
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

	@RequestMapping(value = "/showBillwisePurchasePdf/{fromDate}/{toDate}", method = RequestMethod.GET)
	public void showBillwisePurchasePdf(@PathVariable("fromDate") String fromDate,
			@PathVariable("toDate") String toDate,HttpServletRequest request, HttpServletResponse response)
			throws FileNotFoundException {
		BufferedOutputStream outStream = null;
		System.out.println("Inside Pdf showBillwisePurchasePdf");

		List<BillWisePurchaseReport> billwisePoList = billWisePurchaseReportList;

		// moneyOutList = prodPlanDetailList;
		Document document = new Document(PageSize.A4);
		// ByteArrayOutputStream out = new ByteArrayOutputStream();

		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		Calendar cal = Calendar.getInstance();

		System.out.println("time in Gen Bill PDF ==" + dateFormat.format(cal.getTime()));
		String FILE_PATH = Constants.REPORT_SAVE;
		File file = new File(FILE_PATH);

		PdfWriter writer = null;

		FileOutputStream out = new FileOutputStream(FILE_PATH);
		try {
			writer = PdfWriter.getInstance(document, out);
		} catch (DocumentException e) {

			e.printStackTrace();
		}

		PdfPTable table = new PdfPTable(10);
		try {
			System.out.println("Inside PDF Table try");
			table.setWidthPercentage(100);
			table.setWidths(new float[] { 1.4f, 3.7f, 2.8f, 2.8f, 3.2f, 3.5f, 2.9f, 3.3f, 2.9f, 3.2f });
			Font headFont = new Font(FontFamily.TIMES_ROMAN, 12, Font.NORMAL, BaseColor.BLACK);
			Font headFont1 = new Font(FontFamily.HELVETICA, 12, Font.BOLD, BaseColor.BLACK);
			headFont1.setColor(BaseColor.WHITE);
			Font f = new Font(FontFamily.TIMES_ROMAN, 12.0f, Font.UNDERLINE, BaseColor.BLUE);

			PdfPCell hcell = new PdfPCell();
			hcell.setBackgroundColor(BaseColor.PINK);

			hcell.setPadding(3);
			hcell = new PdfPCell(new Phrase("Sr.No.", headFont1));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			hcell.setBackgroundColor(BaseColor.PINK);

			table.addCell(hcell);

			hcell = new PdfPCell(new Phrase("PO Date", headFont1));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			hcell.setBackgroundColor(BaseColor.PINK);

			table.addCell(hcell);

			hcell = new PdfPCell(new Phrase("Po. No", headFont1));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			hcell.setBackgroundColor(BaseColor.PINK);

			table.addCell(hcell);

			hcell = new PdfPCell(new Phrase("PO Amt", headFont1));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			hcell.setBackgroundColor(BaseColor.PINK);

			table.addCell(hcell);

			hcell = new PdfPCell(new Phrase("Crates Received", headFont1));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			hcell.setBackgroundColor(BaseColor.PINK);

			table.addCell(hcell);

			hcell = new PdfPCell(new Phrase("Short No", headFont1));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			hcell.setBackgroundColor(BaseColor.PINK);

			table.addCell(hcell);

			hcell = new PdfPCell(new Phrase("Extra No", headFont1));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			hcell.setBackgroundColor(BaseColor.PINK);

			table.addCell(hcell);

			hcell = new PdfPCell(new Phrase("PO Leakage Qty", headFont1));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			hcell.setBackgroundColor(BaseColor.PINK);

			table.addCell(hcell);

			hcell = new PdfPCell(new Phrase("User", headFont1));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			hcell.setBackgroundColor(BaseColor.PINK);

			table.addCell(hcell);

			hcell = new PdfPCell(new Phrase("Po Remark", headFont1));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			hcell.setBackgroundColor(BaseColor.PINK);

			table.addCell(hcell);

			int index = 0;
			for (BillWisePurchaseReport bill : billwisePoList) {
				index++;
				PdfPCell cell;

				cell = new PdfPCell(new Phrase(String.valueOf(index), headFont));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setPadding(3);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase(bill.getPoDate(), headFont));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setPaddingRight(2);
				cell.setPadding(3);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase(bill.getPoId(), headFont));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell.setPaddingRight(2);
				cell.setPadding(3);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase("" + bill.getPoTotal(), headFont));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				cell.setPaddingRight(2);
				cell.setPadding(3);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase("" + bill.getCratesReceivedQty(), headFont));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				cell.setPaddingRight(2);
				cell.setPadding(3);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase("" + bill.getShortNo(), headFont));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				cell.setPaddingRight(2);
				cell.setPadding(3);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase("" + bill.getExtraNo(), headFont));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				cell.setPaddingRight(2);
				cell.setPadding(3);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase("" + bill.getPoLeakageQty(), headFont));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				cell.setPaddingRight(2);
				cell.setPadding(3);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase("" + bill.getUserName(), headFont));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setPaddingRight(2);
				cell.setPadding(3);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase("" + bill.getPoRemarks(), headFont));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell.setPaddingRight(2);
				cell.setPadding(3);
				table.addCell(cell);

			}
			document.open();
			Paragraph name = new Paragraph("Dairy Power\n", f);
			name.setAlignment(Element.ALIGN_CENTER);
			document.add(name);
			document.add(new Paragraph(" "));
			Paragraph company = new Paragraph("Bill wise Purchase Report\n", f);
			company.setAlignment(Element.ALIGN_CENTER);
			document.add(company);
			document.add(new Paragraph(" "));

			DateFormat DF = new SimpleDateFormat("dd-MM-yyyy");
			String reportDate = DF.format(new Date());
			Paragraph p1 = new Paragraph("From Date:" + fromDate + "  To Date:" + toDate, headFont);
			p1.setAlignment(Element.ALIGN_CENTER);
			document.add(p1);
			document.add(new Paragraph("\n"));
			document.add(table);

			int totalPages = writer.getPageNumber();

			System.out.println("Page no " + totalPages);

			document.close();
			// Atul Sir code to open a Pdf File
			if (file != null) {

				String mimeType = URLConnection.guessContentTypeFromName(file.getName());

				if (mimeType == null) {

					mimeType = "application/pdf";

				}

				response.setContentType(mimeType);

				response.addHeader("content-disposition", String.format("inline; filename=\"%s\"", file.getName()));

				response.setContentLength((int) file.length());

				InputStream inputStream = new BufferedInputStream(new FileInputStream(file));

				try {
					FileCopyUtils.copy(inputStream, response.getOutputStream());
				} catch (IOException e) {
					System.out.println("Excep in Opening a Pdf File");
					e.printStackTrace();
				}
			}

		} catch (DocumentException ex) {

			System.out.println("Pdf Generation Error: BOm Prod  View Prod" + ex.getMessage());

			ex.printStackTrace();

		}

	}
	@RequestMapping(value = "/findDatewiseConsumption", method = RequestMethod.GET)
	public @ResponseBody List<DatewiseSaleReport> findDatewiseConsumption(HttpServletRequest request,
			HttpServletResponse response) {
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

			DatewiseSaleReport[] datewiseConsumption = restTemplate
					.postForObject(Constants.url + "Test/getAllDatewiseConsumption", map, DatewiseSaleReport[].class);

			datewiseConsumptionList = new ArrayList<DatewiseSaleReport>(Arrays.asList(datewiseConsumption));
			System.out.println("datewiseConsumptionList" + datewiseConsumptionList.toString());

			// export to excel

			List<ExportToExcel> exportToExcelList = new ArrayList<ExportToExcel>();

			ExportToExcel expoExcel = new ExportToExcel();
			List<String> rowData = new ArrayList<String>();

			rowData.add("Sr. No");
			rowData.add("Bill Date");
			rowData.add("Bill Amount");
			rowData.add("Collected Amount");
			rowData.add("OS Amount");
			rowData.add("Crates Total");
			rowData.add("Crates Received");
			rowData.add("Crates OS");

			expoExcel.setRowData(rowData);
			exportToExcelList.add(expoExcel);
			int cnt = 1;
			for (int i = 0; i < datewiseConsumptionList.size(); i++) {
				expoExcel = new ExportToExcel();
				rowData = new ArrayList<String>();
				cnt = cnt + i;
				rowData.add("" + (cnt));
				rowData.add("" + datewiseConsumptionList.get(i).getBillDate());
				rowData.add("" + datewiseConsumptionList.get(i).getGrandTotal());
				rowData.add("" + datewiseConsumptionList.get(i).getCollectedAmt());
				rowData.add("" + datewiseConsumptionList.get(i).getOutstandingAmt());
				rowData.add(datewiseConsumptionList.get(i).getCratesOpBal()+datewiseConsumptionList.get(i).getCratesIssued()+"");
				rowData.add("" + datewiseConsumptionList.get(i).getCratesReceived());
			    int osCrates=(datewiseConsumptionList.get(i).getCratesOpBal()+datewiseConsumptionList.get(i).getCratesIssued())-datewiseConsumptionList.get(i).getCratesReceived();
				rowData.add("" + osCrates);

			

				expoExcel.setRowData(rowData);
				exportToExcelList.add(expoExcel);

			}

			HttpSession session = request.getSession();
			session.setAttribute("exportExcelList", exportToExcelList);
			session.setAttribute("excelName", "DateWiseConsumptionList");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}

		return datewiseConsumptionList;

	}
	@RequestMapping(value = "/showDatewiseConsumptionPdf/{fromDate}/{toDate}", method = RequestMethod.GET)
	public void showDatewiseConsumptionPdf(@PathVariable("fromDate") String fromDate,
			@PathVariable("toDate") String toDate, HttpServletRequest request, HttpServletResponse response)
			throws FileNotFoundException {
		BufferedOutputStream outStream = null;
		System.out.println("Inside Pdf showDatewiseConsumptionPdf");

		// moneyOutList = prodPlanDetailList;
		Document document = new Document(PageSize.A4);
		// ByteArrayOutputStream out = new ByteArrayOutputStream();

		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		Calendar cal = Calendar.getInstance();

		System.out.println("time in Gen Bill PDF ==" + dateFormat.format(cal.getTime()));
		String FILE_PATH = Constants.REPORT_SAVE;
		File file = new File(FILE_PATH);

		PdfWriter writer = null;

		FileOutputStream out = new FileOutputStream(FILE_PATH);
		try {
			writer = PdfWriter.getInstance(document, out);
		} catch (DocumentException e) {

			e.printStackTrace();
		}

		PdfPTable table = new PdfPTable(8);
		try {
			System.out.println("Inside PDF Table try");
			table.setWidthPercentage(100);
			table.setWidths(
					new float[] { 2.4f,5.0f,5.2f,5.2f,5.2f,5.2f, 5.4f,5.3f});
			Font headFont = new Font(FontFamily.TIMES_ROMAN, 12, Font.NORMAL, BaseColor.BLACK);
			Font headFont1 = new Font(FontFamily.HELVETICA, 12, Font.BOLD, BaseColor.BLACK);
			headFont1.setColor(BaseColor.WHITE);
			Font f = new Font(FontFamily.TIMES_ROMAN, 12.0f, Font.UNDERLINE, BaseColor.BLUE);

			PdfPCell hcell = new PdfPCell();
			hcell.setBackgroundColor(BaseColor.PINK);

			hcell.setPadding(3);
			hcell = new PdfPCell(new Phrase("Sr.No.", headFont1));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			hcell.setBackgroundColor(BaseColor.PINK);

			table.addCell(hcell);


			hcell = new PdfPCell(new Phrase("Bill Date", headFont1));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			hcell.setBackgroundColor(BaseColor.PINK);

			table.addCell(hcell);

			hcell = new PdfPCell(new Phrase("Bill Amount", headFont1));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			hcell.setBackgroundColor(BaseColor.PINK);

			table.addCell(hcell);

			hcell = new PdfPCell(new Phrase("Collected Amount", headFont1));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			hcell.setBackgroundColor(BaseColor.PINK);

			table.addCell(hcell);

			hcell = new PdfPCell(new Phrase("OS Amount", headFont1));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			hcell.setBackgroundColor(BaseColor.PINK);

			table.addCell(hcell);

			hcell = new PdfPCell(new Phrase("Crates Total", headFont1));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			hcell.setBackgroundColor(BaseColor.PINK);

			table.addCell(hcell);

			hcell = new PdfPCell(new Phrase("Crates Recieved", headFont1));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			hcell.setBackgroundColor(BaseColor.PINK);

			table.addCell(hcell);

			hcell = new PdfPCell(new Phrase("Crates Outstanding", headFont1));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			hcell.setBackgroundColor(BaseColor.PINK);

			table.addCell(hcell);


			int index = 0;
			for (DatewiseSaleReport bill : datewiseConsumptionList) {
				index++;
				PdfPCell cell;

				cell = new PdfPCell(new Phrase(String.valueOf(index), headFont));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setPadding(3);	cell.setPaddingRight(2);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase(bill.getBillDate(), headFont));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setPaddingRight(2);
				cell.setPadding(3);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase("" + bill.getGrandTotal(), headFont));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				cell.setPaddingRight(2);
				cell.setPadding(3);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase("" + bill.getCollectedAmt(), headFont));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				cell.setPaddingRight(2);
				cell.setPadding(3);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase("" + bill.getOutstandingAmt(), headFont));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				cell.setPaddingRight(2);
				cell.setPadding(3);
				table.addCell(cell);
             
				cell = new PdfPCell(new Phrase(bill.getCratesOpBal()+bill.getCratesIssued()+"", headFont));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				cell.setPaddingRight(2);
				cell.setPadding(3);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase("" + bill.getCratesReceived() , headFont));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				cell.setPaddingRight(2);
				cell.setPadding(3);
				table.addCell(cell);
               
				int osCrates=(bill.getCratesOpBal()+bill.getCratesIssued())-bill.getCratesReceived();

				cell = new PdfPCell(new Phrase("" + osCrates, headFont));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				cell.setPaddingRight(2);
				cell.setPadding(3);
				table.addCell(cell);

			}
			document.open();
			Paragraph name = new Paragraph("Dairy Power\n", f);
			name.setAlignment(Element.ALIGN_CENTER);
			document.add(name);
			document.add(new Paragraph(" "));
			Paragraph company = new Paragraph("Datewise Consumption Report\n", f);
			company.setAlignment(Element.ALIGN_CENTER);
			document.add(company);
			document.add(new Paragraph(" "));

			DateFormat DF = new SimpleDateFormat("dd-MM-yyyy");
			String reportDate = DF.format(new Date());
			Paragraph p1 = new Paragraph("From Date:" + fromDate + "  To Date:" + toDate, headFont);
			p1.setAlignment(Element.ALIGN_CENTER);
			document.add(p1);
			document.add(new Paragraph("\n"));
			document.add(table);

			int totalPages = writer.getPageNumber();

			System.out.println("Page no " + totalPages);

			document.close();
			// Atul Sir code to open a Pdf File
			if (file != null) {

				String mimeType = URLConnection.guessContentTypeFromName(file.getName());

				if (mimeType == null) {

					mimeType = "application/pdf";

				}

				response.setContentType(mimeType);

				response.addHeader("content-disposition", String.format("inline; filename=\"%s\"", file.getName()));

				response.setContentLength((int) file.length());

				InputStream inputStream = new BufferedInputStream(new FileInputStream(file));

				try {
					FileCopyUtils.copy(inputStream, response.getOutputStream());
				} catch (IOException e) {
					System.out.println("Excep in Opening a Pdf File");
					e.printStackTrace();
				}
			}

		} catch (DocumentException ex) {

			System.out.println("Pdf Generation Error: BOm Prod  View Prod" + ex.getMessage());

			ex.printStackTrace();

		}

	}
	// -----------------------Bill Wise Consumption
	// Report-----------
	@RequestMapping(value = "/findBillWiseConsumption", method = RequestMethod.GET)
	public @ResponseBody List<BillwiseConsumption> getBillWiseConsumptionPurchase(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			System.out.println("in method");
			String fromDate = request.getParameter("fromDate");
			System.out.println("fromDate" + fromDate);

			String toDate = request.getParameter("toDate");
			System.out.println("toDate" + toDate);
			
			int custId = Integer.parseInt(request.getParameter("custId"));

			RestTemplate restTemplate = new RestTemplate();

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

			map.add("fromDate", DateConvertor.convertToYMD(fromDate));
			map.add("toDate", DateConvertor.convertToYMD(toDate));
			map.add("custId",custId);

			BillwiseConsumption[] billWiseConsumption = restTemplate
					.postForObject(Constants.url + "Test/getAllBillwiseConsumption", map, BillwiseConsumption[].class);

			billwiseConsumptionList = new ArrayList<BillwiseConsumption>(Arrays.asList(billWiseConsumption));
			System.out.println("billwiseConsumptionList" + billwiseConsumptionList.toString());

			// export to excel

			List<ExportToExcel> exportToExcelList = new ArrayList<ExportToExcel>();

			ExportToExcel expoExcel = new ExportToExcel();
			List<String> rowData = new ArrayList<String>();

			rowData.add("Sr. No");
			rowData.add("Bill No.");
			rowData.add("Bill Date");
			rowData.add("Customer Name");
			rowData.add("Bill Amount");
			rowData.add("Collected Amount");
			rowData.add("OS Amount");
			rowData.add("Payment Mode");
			rowData.add("Crates Total");
			rowData.add("Crates Received");
			rowData.add("Crates OS");
			rowData.add("Total Kms");
			rowData.add("Remark");

			expoExcel.setRowData(rowData);
			exportToExcelList.add(expoExcel);
			int cnt = 1;
			for (int i = 0; i < billwiseConsumptionList.size(); i++) {
				expoExcel = new ExportToExcel();
				rowData = new ArrayList<String>();
				cnt = cnt + i;
				rowData.add("" + (cnt));
				rowData.add("" + billwiseConsumptionList.get(i).getBillId());
				rowData.add("" + billwiseConsumptionList.get(i).getBillDate());
				rowData.add("" + billwiseConsumptionList.get(i).getCustName());
				rowData.add("" + billwiseConsumptionList.get(i).getGrandTotal());
				rowData.add("" + billwiseConsumptionList.get(i).getCollectedAmt());
				rowData.add("" + billwiseConsumptionList.get(i).getOutstandingAmt());
				if(billwiseConsumptionList.get(i).getCollectionPaymode()==0)
				{
					rowData.add("NA");

				}
				else if(billwiseConsumptionList.get(i).getCollectionPaymode()==1)
				{
					rowData.add("Cheque");

				}else if(billwiseConsumptionList.get(i).getCollectionPaymode()==2)
				{
					rowData.add("Cash");

				}else if(billwiseConsumptionList.get(i).getCollectionPaymode()==3)
				{
					rowData.add("Cash & Cheque");

				}
				rowData.add(billwiseConsumptionList.get(i).getCratesOpBal()+billwiseConsumptionList.get(i).getCratesIssued()+"");
				rowData.add("" +billwiseConsumptionList.get(i).getCratesReceived() );
				rowData.add("" + billwiseConsumptionList.get(i).getCratesClBal());
				rowData.add("" + 0);
				rowData.add("" + billwiseConsumptionList.get(i).getRemarks());

				expoExcel.setRowData(rowData);
				exportToExcelList.add(expoExcel);

			}

			HttpSession session = request.getSession();
			session.setAttribute("exportExcelList", exportToExcelList);
			session.setAttribute("excelName", "BillwiseConsumptionList");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}

		return billwiseConsumptionList;

	}

	@RequestMapping(value = "/showBillwiseConsumptionPdf/{fromDate}/{toDate}", method = RequestMethod.GET)
	public void showBillwiseConsumptionPdf(@PathVariable("fromDate") String fromDate,
			@PathVariable("toDate") String toDate, HttpServletRequest request, HttpServletResponse response)
			throws FileNotFoundException {
		BufferedOutputStream outStream = null;
		System.out.println("Inside Pdf showBillwiseConsumptionPdf");

		// moneyOutList = prodPlanDetailList;
		Document document = new Document(PageSize.A4);
		// ByteArrayOutputStream out = new ByteArrayOutputStream();

		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		Calendar cal = Calendar.getInstance();

		System.out.println("time in Gen Bill PDF ==" + dateFormat.format(cal.getTime()));
		String FILE_PATH = Constants.REPORT_SAVE;
		File file = new File(FILE_PATH);

		PdfWriter writer = null;

		FileOutputStream out = new FileOutputStream(FILE_PATH);
		try {
			writer = PdfWriter.getInstance(document, out);
		} catch (DocumentException e) {

			e.printStackTrace();
		}

		PdfPTable table = new PdfPTable(13);
		try {
			System.out.println("Inside PDF Table try");
			table.setWidthPercentage(100);
			table.setWidths(
					new float[] { 2.4f,5.0f,5.2f,5.2f,5.2f,5.2f, 5.4f,5.3f, 5.3f,5.2f,5.2f, 5.2f,4f });
			Font headFont = new Font(FontFamily.TIMES_ROMAN, 12, Font.NORMAL, BaseColor.BLACK);
			Font headFont1 = new Font(FontFamily.HELVETICA, 12, Font.BOLD, BaseColor.BLACK);
			headFont1.setColor(BaseColor.WHITE);
			Font f = new Font(FontFamily.TIMES_ROMAN, 12.0f, Font.UNDERLINE, BaseColor.BLUE);

			PdfPCell hcell = new PdfPCell();
			hcell.setBackgroundColor(BaseColor.PINK);

			hcell.setPadding(3);
			hcell = new PdfPCell(new Phrase("Sr.No.", headFont1));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			hcell.setBackgroundColor(BaseColor.PINK);

			table.addCell(hcell);

			hcell = new PdfPCell(new Phrase("Bill No", headFont1));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			hcell.setBackgroundColor(BaseColor.PINK);

			table.addCell(hcell);

			hcell = new PdfPCell(new Phrase("Bill Date", headFont1));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			hcell.setBackgroundColor(BaseColor.PINK);

			table.addCell(hcell);

			
			hcell = new PdfPCell(new Phrase("Customer Name", headFont1));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			hcell.setBackgroundColor(BaseColor.PINK);

			table.addCell(hcell);
			hcell = new PdfPCell(new Phrase("Bill Amount", headFont1));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			hcell.setBackgroundColor(BaseColor.PINK);

			table.addCell(hcell);

			hcell = new PdfPCell(new Phrase("Collected Amount", headFont1));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			hcell.setBackgroundColor(BaseColor.PINK);

			table.addCell(hcell);

			hcell = new PdfPCell(new Phrase("OS Amount", headFont1));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			hcell.setBackgroundColor(BaseColor.PINK);

			table.addCell(hcell);

			hcell = new PdfPCell(new Phrase("Payment Mode", headFont1));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			hcell.setBackgroundColor(BaseColor.PINK);

			table.addCell(hcell);

			hcell = new PdfPCell(new Phrase("Crates Total", headFont1));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			hcell.setBackgroundColor(BaseColor.PINK);

			table.addCell(hcell);

			hcell = new PdfPCell(new Phrase("Crates Recieved", headFont1));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			hcell.setBackgroundColor(BaseColor.PINK);

			table.addCell(hcell);

			hcell = new PdfPCell(new Phrase("Crates Outstanding", headFont1));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			hcell.setBackgroundColor(BaseColor.PINK);

			table.addCell(hcell);

		

			hcell = new PdfPCell(new Phrase("Total KM", headFont1));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			hcell.setBackgroundColor(BaseColor.PINK);

			table.addCell(hcell);

			hcell = new PdfPCell(new Phrase("Remark", headFont1));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			hcell.setBackgroundColor(BaseColor.PINK);

			table.addCell(hcell);

			int index = 0;
			for (BillwiseConsumption bill : billwiseConsumptionList) {
				index++;
				PdfPCell cell;

				cell = new PdfPCell(new Phrase(String.valueOf(index), headFont));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setPadding(3);	cell.setPaddingRight(2);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase("" + bill.getBillId(), headFont));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setPaddingRight(2);
				cell.setPadding(3);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase(bill.getBillDate(), headFont));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setPaddingRight(2);
				cell.setPadding(3);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase(bill.getCustName(), headFont));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell.setPaddingRight(2);
				cell.setPadding(3);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase("" + bill.getGrandTotal(), headFont));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				cell.setPaddingRight(2);
				cell.setPadding(3);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase("" + bill.getCollectedAmt(), headFont));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				cell.setPaddingRight(2);
				cell.setPadding(3);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase("" + bill.getOutstandingAmt(), headFont));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				cell.setPaddingRight(2);
				cell.setPadding(3);
				table.addCell(cell);
                String payMode="";
				if(bill.getCollectionPaymode()==0)
				{
					payMode="NA";
				}
				else if(bill.getCollectionPaymode()==1)
				{
					payMode="Cheque";
				}else if(bill.getCollectionPaymode()==2)
				{
					payMode="Cash";
				}else if(bill.getCollectionPaymode()==3)
				{
					payMode="Cheque & Cash";
				}
				cell = new PdfPCell(new Phrase(payMode , headFont));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setPaddingRight(2);
				cell.setPadding(3);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase(bill.getCratesOpBal()+bill.getCratesIssued()+"", headFont));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				cell.setPaddingRight(2);
				cell.setPadding(3);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase("" + bill.getCratesReceived() , headFont));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				cell.setPaddingRight(2);
				cell.setPadding(3);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase("" + bill.getCratesClBal(), headFont));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				cell.setPaddingRight(2);
				cell.setPadding(3);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase("0", headFont));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setPaddingRight(2);
				cell.setPadding(3);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase("" + bill.getRemarks(), headFont));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell.setPaddingRight(2);
				cell.setPadding(3);
				table.addCell(cell);

			}
			document.open();
			Paragraph name = new Paragraph("Dairy Power\n", f);
			name.setAlignment(Element.ALIGN_CENTER);
			document.add(name);
			document.add(new Paragraph(" "));
			Paragraph company = new Paragraph("Bill wise Consumption Report\n", f);
			company.setAlignment(Element.ALIGN_CENTER);
			document.add(company);
			document.add(new Paragraph(" "));

			DateFormat DF = new SimpleDateFormat("dd-MM-yyyy");
			String reportDate = DF.format(new Date());
			Paragraph p1 = new Paragraph("From Date:" + fromDate + "  To Date:" + toDate, headFont);
			p1.setAlignment(Element.ALIGN_CENTER);
			document.add(p1);
			document.add(new Paragraph("\n"));
			document.add(table);

			int totalPages = writer.getPageNumber();

			System.out.println("Page no " + totalPages);

			document.close();
			// Atul Sir code to open a Pdf File
			if (file != null) {

				String mimeType = URLConnection.guessContentTypeFromName(file.getName());

				if (mimeType == null) {

					mimeType = "application/pdf";

				}

				response.setContentType(mimeType);

				response.addHeader("content-disposition", String.format("inline; filename=\"%s\"", file.getName()));

				response.setContentLength((int) file.length());

				InputStream inputStream = new BufferedInputStream(new FileInputStream(file));

				try {
					FileCopyUtils.copy(inputStream, response.getOutputStream());
				} catch (IOException e) {
					System.out.println("Excep in Opening a Pdf File");
					e.printStackTrace();
				}
			}

		} catch (DocumentException ex) {

			System.out.println("Pdf Generation Error: BOm Prod  View Prod" + ex.getMessage());

			ex.printStackTrace();

		}

	}

	// ----------------------------------Date Wise Purchase
	// Report---------------------------------------------
	@RequestMapping(value = "/findDateWisePurchase", method = RequestMethod.GET)
	public @ResponseBody List<DateWisePurchaseReport> findDateWisePurchase(HttpServletRequest request,
			HttpServletResponse response) {

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

			DateWisePurchaseReport[] dateWisePurchaseList = restTemplate
					.postForObject(Constants.url + "Test/getDatewisePurchase", map, DateWisePurchaseReport[].class);

			dateWisePurchaseReport = new ArrayList<DateWisePurchaseReport>(Arrays.asList(dateWisePurchaseList));

			System.out.println("dateWisePurchaseReport" + dateWisePurchaseList.toString());

			// export to excel

			List<ExportToExcel> exportToExcelList = new ArrayList<ExportToExcel>();

			ExportToExcel expoExcel = new ExportToExcel();
			List<String> rowData = new ArrayList<String>();

			rowData.add("Sr. No");
			rowData.add("PO No.");
			rowData.add("PO Date");
			rowData.add("PO Day");
			rowData.add("Total Amount");
			rowData.add("Crates Received");

			expoExcel.setRowData(rowData);
			exportToExcelList.add(expoExcel);
			int cnt = 1;
			for (int i = 0; i < dateWisePurchaseReport.size(); i++) {
				expoExcel = new ExportToExcel();
				rowData = new ArrayList<String>();
				cnt = cnt + i;
				rowData.add("" + (cnt));
				rowData.add("" + dateWisePurchaseReport.get(i).getPoHeaderId());
				rowData.add("" + dateWisePurchaseReport.get(i).getPoDate());
				rowData.add("" + dateWisePurchaseReport.get(i).getDay());

				rowData.add("" + dateWisePurchaseReport.get(i).getPoTotal());
				rowData.add("" + dateWisePurchaseReport.get(i).getCratesReceivedQty());

				expoExcel.setRowData(rowData);
				exportToExcelList.add(expoExcel);

			}

			HttpSession session = request.getSession();
			session.setAttribute("exportExcelList", exportToExcelList);
			session.setAttribute("excelName", "DateWisePurchaseReport");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}

		return dateWisePurchaseReport;

	}

	@RequestMapping(value = "/showPurchaseDatewiseReportPdf/{fromDate}/{toDate}", method = RequestMethod.GET)
	public void showPurchaseDatewiseReportPdf(@PathVariable("fromDate") String fromDate,
			@PathVariable("toDate") String toDate, HttpServletRequest request, HttpServletResponse response)
			throws FileNotFoundException {

		BufferedOutputStream outStream = null;
		System.out.println("Inside Pdf shoDatewisePurchasePdf");

		// moneyOutList = prodPlanDetailList;
		Document document = new Document(PageSize.A4);
		// ByteArrayOutputStream out = new ByteArrayOutputStream();

		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		Calendar cal = Calendar.getInstance();

		System.out.println("time in Gen Bill PDF ==" + dateFormat.format(cal.getTime()));
		String FILE_PATH = Constants.REPORT_SAVE;
		File file = new File(FILE_PATH);

		PdfWriter writer = null;

		FileOutputStream out = new FileOutputStream(FILE_PATH);
		try {
			writer = PdfWriter.getInstance(document, out);
		} catch (DocumentException e) {

			e.printStackTrace();
		}

		PdfPTable table = new PdfPTable(6);
		try {
			System.out.println("Inside PDF Table try");
			table.setWidthPercentage(100);
			table.setWidths(new float[] { 1.4f, 3f, 2.8f, 2.8f, 3.2f, 3.5f });
			Font headFont = new Font(FontFamily.TIMES_ROMAN, 12, Font.NORMAL, BaseColor.BLACK);
			Font headFont1 = new Font(FontFamily.HELVETICA, 12, Font.BOLD, BaseColor.BLACK);
			headFont1.setColor(BaseColor.WHITE);
			Font f = new Font(FontFamily.TIMES_ROMAN, 12.0f, Font.UNDERLINE, BaseColor.BLUE);

			PdfPCell hcell = new PdfPCell();
			hcell.setBackgroundColor(BaseColor.PINK);

			hcell.setPadding(3);
			hcell = new PdfPCell(new Phrase("Sr.No.", headFont1));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			hcell.setBackgroundColor(BaseColor.PINK);

			table.addCell(hcell);

			hcell = new PdfPCell(new Phrase("PO No", headFont1));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			hcell.setBackgroundColor(BaseColor.PINK);

			table.addCell(hcell);

			hcell = new PdfPCell(new Phrase("PO Date", headFont1));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			hcell.setBackgroundColor(BaseColor.PINK);

			table.addCell(hcell);

			hcell = new PdfPCell(new Phrase("PO Day", headFont1));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			hcell.setBackgroundColor(BaseColor.PINK);

			table.addCell(hcell);

			hcell = new PdfPCell(new Phrase("Total Amount", headFont1));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			hcell.setBackgroundColor(BaseColor.PINK);

			table.addCell(hcell);

			hcell = new PdfPCell(new Phrase("Crates Received", headFont1));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			hcell.setBackgroundColor(BaseColor.PINK);

			table.addCell(hcell);

			int index = 0;
			for (DateWisePurchaseReport bill : dateWisePurchaseReport) {
				index++;
				PdfPCell cell;

				cell = new PdfPCell(new Phrase(String.valueOf(index), headFont));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setPadding(3);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase(" " + bill.getPoHeaderId(), headFont));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell.setPaddingRight(2);
				cell.setPadding(3);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase("" + bill.getPoDate(), headFont));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setPaddingRight(2);
				cell.setPadding(3);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase("" + bill.getDay(), headFont));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell.setPaddingRight(2);
				cell.setPadding(3);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase("" + bill.getPoTotal(), headFont));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				cell.setPaddingRight(2);
				cell.setPadding(3);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase("" + bill.getCratesReceivedQty(), headFont));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				cell.setPaddingRight(2);
				cell.setPadding(3);
				table.addCell(cell);

			}
			document.open();
			Paragraph name = new Paragraph("Dairy Power\n", f);
			name.setAlignment(Element.ALIGN_CENTER);
			document.add(name);
			document.add(new Paragraph(" "));
			Paragraph company = new Paragraph("Date wise Purchase Report\n", f);
			company.setAlignment(Element.ALIGN_CENTER);
			document.add(company);
			document.add(new Paragraph(" "));

			DateFormat DF = new SimpleDateFormat("dd-MM-yyyy");
			String reportDate = DF.format(new Date());
			Paragraph p1 = new Paragraph("From Date:" + fromDate + "  To Date:" + toDate, headFont);
			p1.setAlignment(Element.ALIGN_CENTER);
			document.add(p1);
			document.add(new Paragraph("\n"));
			document.add(table);

			int totalPages = writer.getPageNumber();

			System.out.println("Page no " + totalPages);

			document.close();
			// Atul Sir code to open a Pdf File
			if (file != null) {

				String mimeType = URLConnection.guessContentTypeFromName(file.getName());

				if (mimeType == null) {

					mimeType = "application/pdf";

				}

				response.setContentType(mimeType);

				response.addHeader("content-disposition", String.format("inline; filename=\"%s\"", file.getName()));

				response.setContentLength((int) file.length());

				InputStream inputStream = new BufferedInputStream(new FileInputStream(file));

				try {
					FileCopyUtils.copy(inputStream, response.getOutputStream());
				} catch (IOException e) {
					System.out.println("Excep in Opening a Pdf File");
					e.printStackTrace();
				}
			}

		} catch (DocumentException ex) {

			System.out.println("Pdf Generation Error: BOm Prod  View Prod" + ex.getMessage());

			ex.printStackTrace();

		}

	}

	// ----------------------------------Item Wise Purchase
	// Report---------------------------------------------
	@RequestMapping(value = "/findItemWisePurchase", method = RequestMethod.GET)
	public @ResponseBody List<ItemWisePurchaseReport> getItemWisePurchase(HttpServletRequest request,
			HttpServletResponse response) {

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

			ItemWisePurchaseReport[] itemWisePurchaseReportList = restTemplate
					.postForObject(Constants.url + "Test/getItemwisePurchase", map, ItemWisePurchaseReport[].class);
			itemWisePurchaseReport = new ArrayList<ItemWisePurchaseReport>(Arrays.asList(itemWisePurchaseReportList));

			System.out.println("itemWisePurchaseReport" + itemWisePurchaseReport.toString());

			// export to excel

			List<ExportToExcel> exportToExcelList = new ArrayList<ExportToExcel>();

			ExportToExcel expoExcel = new ExportToExcel();
			List<String> rowData = new ArrayList<String>();

			rowData.add("Sr. No");
			rowData.add("Item No");
			rowData.add("Item Name");
			rowData.add("Total");

			expoExcel.setRowData(rowData);
			exportToExcelList.add(expoExcel);
			int cnt = 1;
			for (int i = 0; i < itemWisePurchaseReport.size(); i++) {
				expoExcel = new ExportToExcel();
				rowData = new ArrayList<String>();
				cnt = cnt + i;
				rowData.add("" + (cnt));
				rowData.add("" + itemWisePurchaseReport.get(i).getItemId());
				rowData.add("" + itemWisePurchaseReport.get(i).getItemName());
				rowData.add("" + itemWisePurchaseReport.get(i).getTotal());

				expoExcel.setRowData(rowData);
				exportToExcelList.add(expoExcel);

			}

			HttpSession session = request.getSession();
			session.setAttribute("exportExcelList", exportToExcelList);
			session.setAttribute("excelName", "ItemWisePurchaseReport");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}

		return itemWisePurchaseReport;

	}

	@RequestMapping(value = "/showPurchaseItemwiseReportPdf/{fromDate}/{toDate}", method = RequestMethod.GET)
	public void showPurchaseItemwiseReportPdf(@PathVariable("fromDate") String fromDate,
			@PathVariable("toDate") String toDate, HttpServletRequest request, HttpServletResponse response)
			throws FileNotFoundException {

		BufferedOutputStream outStream = null;
		System.out.println("Inside Pdf shoItemwisePurchasePdf");

		// moneyOutList = prodPlanDetailList;
		Document document = new Document(PageSize.A4);
		// ByteArrayOutputStream out = new ByteArrayOutputStream();

		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		Calendar cal = Calendar.getInstance();

		System.out.println("time in Gen Bill PDF ==" + dateFormat.format(cal.getTime()));
		String FILE_PATH = Constants.REPORT_SAVE;
		File file = new File(FILE_PATH);

		PdfWriter writer = null;

		FileOutputStream out = new FileOutputStream(FILE_PATH);
		try {
			writer = PdfWriter.getInstance(document, out);
		} catch (DocumentException e) {

			e.printStackTrace();
		}

		PdfPTable table = new PdfPTable(4);
		try {
			System.out.println("Inside PDF Table try");
			table.setWidthPercentage(100);
			table.setWidths(new float[] { 1.4f, 1.7f, 3.8f, 2.4f });
			Font headFont = new Font(FontFamily.TIMES_ROMAN, 12, Font.NORMAL, BaseColor.BLACK);
			Font headFont1 = new Font(FontFamily.HELVETICA, 12, Font.BOLD, BaseColor.BLACK);
			headFont1.setColor(BaseColor.WHITE);
			Font f = new Font(FontFamily.TIMES_ROMAN, 12.0f, Font.UNDERLINE, BaseColor.BLUE);

			PdfPCell hcell = new PdfPCell();
			hcell.setBackgroundColor(BaseColor.PINK);

			hcell.setPadding(3);
			hcell = new PdfPCell(new Phrase("Sr.No.", headFont1));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			hcell.setBackgroundColor(BaseColor.PINK);

			table.addCell(hcell);

			hcell = new PdfPCell(new Phrase("Item No", headFont1));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			hcell.setBackgroundColor(BaseColor.PINK);

			table.addCell(hcell);

			hcell = new PdfPCell(new Phrase("Item Name", headFont1));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			hcell.setBackgroundColor(BaseColor.PINK);

			table.addCell(hcell);

			hcell = new PdfPCell(new Phrase("Total", headFont1));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			hcell.setBackgroundColor(BaseColor.PINK);

			table.addCell(hcell);

			int index = 0;
			for (ItemWisePurchaseReport bill : itemWisePurchaseReport) {
				index++;
				PdfPCell cell;

				cell = new PdfPCell(new Phrase(String.valueOf(index), headFont));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setPadding(3);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase(" " + bill.getItemId(), headFont));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell.setPaddingRight(2);
				cell.setPadding(3);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase("" + bill.getItemName(), headFont));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell.setPaddingRight(2);
				cell.setPadding(3);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase("" + bill.getTotal(), headFont));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				cell.setPaddingRight(2);
				cell.setPadding(3);
				table.addCell(cell);

			}
			document.open();
			Paragraph name = new Paragraph("Dairy Power\n", f);
			name.setAlignment(Element.ALIGN_CENTER);
			document.add(name);
			document.add(new Paragraph(" "));
			Paragraph company = new Paragraph("Item wise Purchase Report\n", f);
			company.setAlignment(Element.ALIGN_CENTER);
			document.add(company);
			document.add(new Paragraph(" "));

			DateFormat DF = new SimpleDateFormat("dd-MM-yyyy");
			String reportDate = DF.format(new Date());
			Paragraph p1 = new Paragraph("From Date:" + fromDate + "  To Date:" + toDate, headFont);
			p1.setAlignment(Element.ALIGN_CENTER);
			document.add(p1);
			document.add(new Paragraph("\n"));
			document.add(table);

			int totalPages = writer.getPageNumber();

			System.out.println("Page no " + totalPages);

			document.close();
			// Atul Sir code to open a Pdf File
			if (file != null) {

				String mimeType = URLConnection.guessContentTypeFromName(file.getName());

				if (mimeType == null) {

					mimeType = "application/pdf";

				}

				response.setContentType(mimeType);

				response.addHeader("content-disposition", String.format("inline; filename=\"%s\"", file.getName()));

				response.setContentLength((int) file.length());

				InputStream inputStream = new BufferedInputStream(new FileInputStream(file));

				try {
					FileCopyUtils.copy(inputStream, response.getOutputStream());
				} catch (IOException e) {
					System.out.println("Excep in Opening a Pdf File");
					e.printStackTrace();
				}
			}

		} catch (DocumentException ex) {

			System.out.println("Pdf Generation Error: BOm Prod  View Prod" + ex.getMessage());

			ex.printStackTrace();

		}

	}
	
	@RequestMapping(value = "/findCategoryWiseReport", method = RequestMethod.GET)
	public @ResponseBody List<CatwiseReport> findCategoryWiseReport(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			System.out.println("in method");
			String fromDate = request.getParameter("fromDate");
			System.out.println("fromDate" + fromDate);

			String toDate = request.getParameter("toDate");
			System.out.println("toDate" + toDate);
			
			String catId = request.getParameter("catId");
			System.out.println("catId" + catId);

			RestTemplate restTemplate = new RestTemplate();

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

			map.add("fromDate", DateConvertor.convertToYMD(fromDate));
			map.add("toDate", DateConvertor.convertToYMD(toDate));
			map.add("catId",catId);

			CatwiseReport[] catwiseConsumption = restTemplate
					.postForObject(Constants.url + "Test/getAllCatwiseReport", map, CatwiseReport[].class);

			catwiseReportList = new ArrayList<CatwiseReport>(Arrays.asList(catwiseConsumption));
			System.out.println("catwiseReportList" + catwiseReportList.toString());

			// export to excel

			List<ExportToExcel> exportToExcelList = new ArrayList<ExportToExcel>();

			ExportToExcel expoExcel = new ExportToExcel();
			List<String> rowData = new ArrayList<String>();

			rowData.add("Sr. No");
			rowData.add("Category Name");
			rowData.add("Qty");
			rowData.add("Taxable Amt");
			rowData.add("Tax Amount");
			rowData.add("Total Amount");
		
		

			expoExcel.setRowData(rowData);
			exportToExcelList.add(expoExcel);
			int cnt = 1;
			for (int i = 0; i < catwiseReportList.size(); i++) {
				expoExcel = new ExportToExcel();
				rowData = new ArrayList<String>();
				cnt = cnt + i;
				rowData.add("" + (cnt));
				rowData.add("" + catwiseReportList.get(i).getCatName());
				
				int qty=catwiseReportList.get(i).getBillQty()-(catwiseReportList.get(i).getReturnQty()+catwiseReportList.get(i).getLeakageQty());
				float taxPer=catwiseReportList.get(i).getCgstPer()+catwiseReportList.get(i).getSgstPer();
				float baseRate=( catwiseReportList.get(i).getRate()*100)/(100+taxPer);
				float taxableAmt=(baseRate*qty);
				float cgstRs=(taxableAmt*catwiseReportList.get(i).getCgstPer())/100;
				float sgstRs=(taxableAmt*catwiseReportList.get(i).getSgstPer())/100;
				float totalTax=cgstRs+sgstRs;
				rowData.add("" + qty);
				rowData.add("" + taxableAmt);
				rowData.add("" + totalTax);

				rowData.add(totalTax+taxableAmt+"");
			
			

				expoExcel.setRowData(rowData);
				exportToExcelList.add(expoExcel);

			}

			HttpSession session = request.getSession();
			session.setAttribute("exportExcelList", exportToExcelList);
			session.setAttribute("excelName", "CategoryConsumptionList");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}

		return catwiseReportList;

	}
	
	
	
	// -----------------------Category Wise Consumption
		// Report-----------
		@RequestMapping(value = "/findCategoryWiseConsumption", method = RequestMethod.GET)
		public @ResponseBody List<CatwiseConsumption> getCatWiseConsumptionPurchase(HttpServletRequest request,
				HttpServletResponse response) {
			try {
				System.out.println("in method");
				String fromDate = request.getParameter("fromDate");
				System.out.println("fromDate" + fromDate);

				String toDate = request.getParameter("toDate");
				System.out.println("toDate" + toDate);
				
				String catId = request.getParameter("catId");
				System.out.println("catId" + catId);

				RestTemplate restTemplate = new RestTemplate();

				MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

				map.add("fromDate", DateConvertor.convertToYMD(fromDate));
				map.add("toDate", DateConvertor.convertToYMD(toDate));
				map.add("catId",catId);

				CatwiseConsumption[] catwiseConsumption = restTemplate
						.postForObject(Constants.url + "Test/getAllCatwiseConsumption", map, CatwiseConsumption[].class);

				catwiseConsumptionList = new ArrayList<CatwiseConsumption>(Arrays.asList(catwiseConsumption));
				System.out.println("catwiseConsumptionList" + catwiseConsumptionList.toString());

				// export to excel

				List<ExportToExcel> exportToExcelList = new ArrayList<ExportToExcel>();

				ExportToExcel expoExcel = new ExportToExcel();
				List<String> rowData = new ArrayList<String>();

				rowData.add("Sr. No");
				rowData.add("Item  Name.");
				rowData.add("Qty");
				rowData.add("Return Qty");
				rowData.add("Dist Leakage Qty");
				rowData.add("Rate");
				rowData.add("Tax Amount");
				rowData.add("Tax %");
				rowData.add("Taxable Amount");
				rowData.add("Total Amount");
			
			

				expoExcel.setRowData(rowData);
				exportToExcelList.add(expoExcel);
				int cnt = 1;
				for (int i = 0; i < catwiseConsumptionList.size(); i++) {
					expoExcel = new ExportToExcel();
					rowData = new ArrayList<String>();
					cnt = cnt + i;
					rowData.add("" + (cnt));
					rowData.add("" + catwiseConsumptionList.get(i).getItemName());
					
					int qty=catwiseConsumptionList.get(i).getBillQty()-(catwiseConsumptionList.get(i).getReturnQty()+catwiseConsumptionList.get(i).getDistLeakageQty());
					float taxPer=catwiseConsumptionList.get(i).getCgstPer()+catwiseConsumptionList.get(i).getSgstPer();
					float baseRate=( catwiseConsumptionList.get(i).getRate()*100)/(100+taxPer);
					float taxableAmt=(baseRate*qty);
					float cgstRs=(taxableAmt*catwiseConsumptionList.get(i).getCgstPer())/100;
					float sgstRs=(taxableAmt*catwiseConsumptionList.get(i).getSgstPer())/100;
					float totalTax=cgstRs+sgstRs;
					rowData.add("" +catwiseConsumptionList.get(i).getBillQty());
					rowData.add("" + catwiseConsumptionList.get(i).getReturnQty());
					rowData.add("" + catwiseConsumptionList.get(i).getDistLeakageQty());
					rowData.add("" + baseRate);
					rowData.add("" + totalTax);
					rowData.add("" + taxPer);
					rowData.add("" + taxableAmt);
					rowData.add(totalTax+taxableAmt+"");
				
				

					expoExcel.setRowData(rowData);
					exportToExcelList.add(expoExcel);

				}

				HttpSession session = request.getSession();
				session.setAttribute("exportExcelList", exportToExcelList);
				session.setAttribute("excelName", "CatwiseConsumptionList");
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println(e.getMessage());
			}

			return catwiseConsumptionList;

		}
		@RequestMapping(value = "/showCategoryWiseReportPdf/{fromDate}/{toDate}", method = RequestMethod.GET)
		public void showCategoryWiseReportPdf(@PathVariable("fromDate") String fromDate,
				@PathVariable("toDate") String toDate, HttpServletRequest request, HttpServletResponse response)
				throws FileNotFoundException {
			BufferedOutputStream outStream = null;

			List<CatwiseReport> catwiseConsumption = catwiseReportList;

			// moneyOutList = prodPlanDetailList;
			Document document = new Document(PageSize.A4);
			// ByteArrayOutputStream out = new ByteArrayOutputStream();

			DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
			Calendar cal = Calendar.getInstance();

			System.out.println("time in Gen Bill PDF ==" + dateFormat.format(cal.getTime()));
			String FILE_PATH = Constants.REPORT_SAVE;
			File file = new File(FILE_PATH);

			PdfWriter writer = null;

			FileOutputStream out = new FileOutputStream(FILE_PATH);
			try {
				writer = PdfWriter.getInstance(document, out);
			} catch (DocumentException e) {

				e.printStackTrace();
			}

			PdfPTable table = new PdfPTable(6);
			try {
				System.out.println("Inside PDF Table try");
				table.setWidthPercentage(100);
				table.setWidths(new float[] { 1.4f, 3.7f, 2.8f, 2.8f, 2.8f, 3.2f });
				Font headFont = new Font(FontFamily.TIMES_ROMAN, 12, Font.NORMAL, BaseColor.BLACK);
				Font headFont1 = new Font(FontFamily.HELVETICA, 14, Font.BOLD, BaseColor.BLACK);
				headFont1.setColor(BaseColor.WHITE);
				Font f = new Font(FontFamily.TIMES_ROMAN, 12.0f, Font.UNDERLINE, BaseColor.BLUE);

				PdfPCell hcell = new PdfPCell();
				hcell.setBackgroundColor(BaseColor.PINK);

				hcell.setPadding(3);
				hcell = new PdfPCell(new Phrase("Sr.No.", headFont1));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				hcell.setBackgroundColor(BaseColor.PINK);

				table.addCell(hcell);

				hcell = new PdfPCell(new Phrase("Category Name", headFont1));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				hcell.setBackgroundColor(BaseColor.PINK);

				table.addCell(hcell);

				hcell = new PdfPCell(new Phrase("Qty", headFont1));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				hcell.setBackgroundColor(BaseColor.PINK);

				table.addCell(hcell);

				hcell = new PdfPCell(new Phrase("Taxable Amt", headFont1));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				hcell.setBackgroundColor(BaseColor.PINK);

				table.addCell(hcell);

				hcell = new PdfPCell(new Phrase("Tax Amt", headFont1));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				hcell.setBackgroundColor(BaseColor.PINK);

				table.addCell(hcell);


				hcell = new PdfPCell(new Phrase("Total Amount", headFont1));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				hcell.setBackgroundColor(BaseColor.PINK);

				table.addCell(hcell);


				int index = 0;
				for (CatwiseReport bill : catwiseConsumption) {
					index++;
					System.err.println(index);
					PdfPCell cell;

					cell = new PdfPCell(new Phrase(String.valueOf(index), headFont));
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell.setPadding(3);
					table.addCell(cell);

					cell = new PdfPCell(new Phrase(bill.getCatName(), headFont));
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_LEFT);
					cell.setPaddingRight(2);
					cell.setPadding(3);
					table.addCell(cell);

					int qty=bill.getBillQty()-(bill.getReturnQty()+bill.getLeakageQty());
					float taxPer=bill.getCgstPer()+bill.getSgstPer();
					float baseRate=( bill.getRate()*100)/(100+taxPer);
					float taxableAmt=(baseRate*qty);
					float cgstRs=(taxableAmt*bill.getCgstPer())/100;
					float sgstRs=(taxableAmt*bill.getSgstPer())/100;
					float totalTax=cgstRs+sgstRs;
					
					cell = new PdfPCell(new Phrase("" +qty, headFont));
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
					cell.setPaddingRight(2);
					cell.setPadding(3);
					table.addCell(cell);

					cell = new PdfPCell(new Phrase("" + taxableAmt, headFont));
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
					cell.setPaddingRight(2);
					cell.setPadding(3);
					table.addCell(cell);

					cell = new PdfPCell(new Phrase("" +totalTax, headFont));
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell.setPaddingRight(2);
					cell.setPadding(3);
					table.addCell(cell);
				

					cell = new PdfPCell(new Phrase(totalTax+taxableAmt+"", headFont));
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell.setPaddingRight(2);
					cell.setPadding(3);
					table.addCell(cell);


				}
				document.open();
				Paragraph name = new Paragraph("Dairy Power\n", f);
				name.setAlignment(Element.ALIGN_CENTER);
				document.add(name);
				document.add(new Paragraph(" "));
				Paragraph company = new Paragraph("Category wise Consumption Report\n", f);
				company.setAlignment(Element.ALIGN_CENTER);
				document.add(company);
				document.add(new Paragraph(" "));

				DateFormat DF = new SimpleDateFormat("dd-MM-yyyy");
				String reportDate = DF.format(new Date());
				Paragraph p1 = new Paragraph("From Date:" + fromDate + "  To Date:" + toDate, headFont);
				p1.setAlignment(Element.ALIGN_CENTER);
				document.add(p1);
				document.add(new Paragraph("\n"));
				document.add(table);

				int totalPages = writer.getPageNumber();

				System.out.println("Page no " + totalPages);

				document.close();
				// Atul Sir code to open a Pdf File
				if (file != null) {

					String mimeType = URLConnection.guessContentTypeFromName(file.getName());

					if (mimeType == null) {

						mimeType = "application/pdf";

					}

					response.setContentType(mimeType);

					response.addHeader("content-disposition", String.format("inline; filename=\"%s\"", file.getName()));

					response.setContentLength((int) file.length());

					InputStream inputStream = new BufferedInputStream(new FileInputStream(file));

					try {
						FileCopyUtils.copy(inputStream, response.getOutputStream());
					} catch (IOException e) {
						System.out.println("Excep in Opening a Pdf File");
						e.printStackTrace();
					}
				}

			} catch (DocumentException ex) {

				System.out.println("Pdf Generation Error: BOm Prod  View Prod" + ex.getMessage());

				ex.printStackTrace();

			}

		}
		@RequestMapping(value = "/showCategoryWiseItemReportPdf/{fromDate}/{toDate}", method = RequestMethod.GET)
		public void showCategoryWiseItemReportPdf(@PathVariable("fromDate") String fromDate,
				@PathVariable("toDate") String toDate, HttpServletRequest request, HttpServletResponse response)
				throws FileNotFoundException {
			BufferedOutputStream outStream = null;
			System.out.println("Inside Pdf showBillwisePurchasePdf");

			List<CatwiseConsumption> catwiseConsumption = catwiseConsumptionList;

			// moneyOutList = prodPlanDetailList;
			Document document = new Document(PageSize.A4);
			// ByteArrayOutputStream out = new ByteArrayOutputStream();

			DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
			Calendar cal = Calendar.getInstance();

			System.out.println("time in Gen Bill PDF ==" + dateFormat.format(cal.getTime()));
			String FILE_PATH = Constants.REPORT_SAVE;
			File file = new File(FILE_PATH);

			PdfWriter writer = null;

			FileOutputStream out = new FileOutputStream(FILE_PATH);
			try {
				writer = PdfWriter.getInstance(document, out);
			} catch (DocumentException e) {

				e.printStackTrace();
			}

			PdfPTable table = new PdfPTable(10);
			try {
				System.out.println("Inside PDF Table try");
				table.setWidthPercentage(100);
				table.setWidths(new float[] { 1.4f, 3.7f, 2.8f, 2.8f, 2.8f, 3.2f, 3.5f, 2.9f, 3.3f,3.0f });
				Font headFont = new Font(FontFamily.TIMES_ROMAN, 12, Font.NORMAL, BaseColor.BLACK);
				Font headFont1 = new Font(FontFamily.HELVETICA, 12, Font.BOLD, BaseColor.BLACK);
				headFont1.setColor(BaseColor.WHITE);
				Font f = new Font(FontFamily.TIMES_ROMAN, 12.0f, Font.UNDERLINE, BaseColor.BLUE);

				PdfPCell hcell = new PdfPCell();
				hcell.setBackgroundColor(BaseColor.PINK);

				hcell.setPadding(3);
				hcell = new PdfPCell(new Phrase("Sr.No.", headFont1));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				hcell.setBackgroundColor(BaseColor.PINK);

				table.addCell(hcell);

				hcell = new PdfPCell(new Phrase("Item Name", headFont1));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				hcell.setBackgroundColor(BaseColor.PINK);

				table.addCell(hcell);

				hcell = new PdfPCell(new Phrase("Qty", headFont1));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				hcell.setBackgroundColor(BaseColor.PINK);

				table.addCell(hcell);

				hcell = new PdfPCell(new Phrase("Return Qty", headFont1));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				hcell.setBackgroundColor(BaseColor.PINK);

				table.addCell(hcell);

				hcell = new PdfPCell(new Phrase("Dist Leakage Qty", headFont1));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				hcell.setBackgroundColor(BaseColor.PINK);

				table.addCell(hcell);


				hcell = new PdfPCell(new Phrase("Rate", headFont1));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				hcell.setBackgroundColor(BaseColor.PINK);

				table.addCell(hcell);

				hcell = new PdfPCell(new Phrase("Taxable Amount", headFont1));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				hcell.setBackgroundColor(BaseColor.PINK);

				table.addCell(hcell);

				hcell = new PdfPCell(new Phrase("Tax %", headFont1));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				hcell.setBackgroundColor(BaseColor.PINK);

				table.addCell(hcell);

				hcell = new PdfPCell(new Phrase("Tax Amount", headFont1));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				hcell.setBackgroundColor(BaseColor.PINK);

				table.addCell(hcell);
				

				hcell = new PdfPCell(new Phrase("Total Amount", headFont1));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				hcell.setBackgroundColor(BaseColor.PINK);

				table.addCell(hcell);


				int index = 0;
				for (CatwiseConsumption bill : catwiseConsumption) {
					index++;
					System.err.println(index);
					PdfPCell cell;

					cell = new PdfPCell(new Phrase(String.valueOf(index), headFont));
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell.setPadding(3);
					table.addCell(cell);

					cell = new PdfPCell(new Phrase(bill.getItemName(), headFont));
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_LEFT);
					cell.setPaddingRight(2);
					cell.setPadding(3);
					table.addCell(cell);

					cell = new PdfPCell(new Phrase("" + bill.getBillQty(), headFont));
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell.setPaddingRight(2);
					cell.setPadding(3);
					table.addCell(cell);

					cell = new PdfPCell(new Phrase("" + bill.getReturnQty(), headFont));
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
					cell.setPaddingRight(2);
					cell.setPadding(3);
					table.addCell(cell);

					cell = new PdfPCell(new Phrase("" + bill.getDistLeakageQty(), headFont));
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell.setPaddingRight(2);
					cell.setPadding(3);
					table.addCell(cell);
					int qty=bill.getBillQty()-(bill.getReturnQty()+bill.getDistLeakageQty());
					float taxPer=bill.getCgstPer()+bill.getSgstPer();
					float baseRate=( bill.getRate()*100)/(100+taxPer);
					float taxableAmt=(baseRate*qty);
					float cgstRs=(taxableAmt*bill.getCgstPer())/100;
					float sgstRs=(taxableAmt*bill.getSgstPer())/100;
					float totalTax=cgstRs+sgstRs;

					cell = new PdfPCell(new Phrase("" + baseRate, headFont));
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell.setPaddingRight(2);
					cell.setPadding(3);
					table.addCell(cell);

					cell = new PdfPCell(new Phrase("" + taxableAmt, headFont));
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell.setPaddingRight(2);
					cell.setPadding(3);
					table.addCell(cell);

					cell = new PdfPCell(new Phrase("" +taxPer, headFont));
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell.setPaddingRight(2);
					cell.setPadding(3);
					table.addCell(cell);

					cell = new PdfPCell(new Phrase(""+totalTax, headFont));
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell.setPaddingRight(2);
					cell.setPadding(3);
					table.addCell(cell);
					cell = new PdfPCell(new Phrase(totalTax+taxableAmt+"", headFont));
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell.setPaddingRight(2);
					cell.setPadding(3);
					table.addCell(cell);


				}
				document.open();
				Paragraph name = new Paragraph("Dairy Power\n", f);
				name.setAlignment(Element.ALIGN_CENTER);
				document.add(name);
				document.add(new Paragraph(" "));
				Paragraph company = new Paragraph("Category wise Item Consumption Report\n", f);
				company.setAlignment(Element.ALIGN_CENTER);
				document.add(company);
				document.add(new Paragraph(" "));

				DateFormat DF = new SimpleDateFormat("dd-MM-yyyy");
				String reportDate = DF.format(new Date());
				Paragraph p1 = new Paragraph("From Date:" + fromDate + "  To Date:" + toDate, headFont);
				p1.setAlignment(Element.ALIGN_CENTER);
				document.add(p1);
				document.add(new Paragraph("\n"));
				document.add(table);

				int totalPages = writer.getPageNumber();

				System.out.println("Page no " + totalPages);

				document.close();
				// Atul Sir code to open a Pdf File
				if (file != null) {

					String mimeType = URLConnection.guessContentTypeFromName(file.getName());

					if (mimeType == null) {

						mimeType = "application/pdf";

					}

					response.setContentType(mimeType);

					response.addHeader("content-disposition", String.format("inline; filename=\"%s\"", file.getName()));

					response.setContentLength((int) file.length());

					InputStream inputStream = new BufferedInputStream(new FileInputStream(file));

					try {
						FileCopyUtils.copy(inputStream, response.getOutputStream());
					} catch (IOException e) {
						System.out.println("Excep in Opening a Pdf File");
						e.printStackTrace();
					}
				}

			} catch (DocumentException ex) {

				System.out.println("Pdf Generation Error: BOm Prod  View Prod" + ex.getMessage());

				ex.printStackTrace();

			}

		}

	// ---------------------------------Customer Wise Consumption Report
	// Report---------------------------------------------
	@RequestMapping(value = "/findCustomerWiseConsumption", method = RequestMethod.GET)
	public @ResponseBody List<CustomerWiseConReport> getCustomerWiseConsumption(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			System.out.println("in method");
			String fromDate = request.getParameter("fromDate");
			System.out.println("fromDate" + fromDate);

			String toDate = request.getParameter("toDate");
			System.out.println("toDate" + toDate);
			
			String custId = request.getParameter("custId");
			System.out.println("custId" + custId);

			RestTemplate restTemplate = new RestTemplate();

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

			map.add("fromDate", DateConvertor.convertToYMD(fromDate));
			map.add("toDate", DateConvertor.convertToYMD(toDate));
			map.add("custId",custId);
			CustomerWiseConReport[] cutwiseCons = restTemplate
					.postForObject(Constants.url + "Test/getAllCustomerwiseReport", map, CustomerWiseConReport[].class);

			customerWiseConReportList = new ArrayList<CustomerWiseConReport>(Arrays.asList(cutwiseCons));

			System.out.println("new List cat wise consumption " + customerWiseConReportList.toString());
			// export to excel

			List<ExportToExcel> exportToExcelList = new ArrayList<ExportToExcel>();

			ExportToExcel expoExcel = new ExportToExcel();
			List<String> rowData = new ArrayList<String>();

			rowData.add("Sr. No");
			rowData.add("Customer Name");
			rowData.add("Bill Amount");
			rowData.add("Received Amount");
			rowData.add("OutStanding Amount");
			rowData.add("Crates Opening Balance");
			rowData.add("Crates Issued");
			rowData.add("Crates Recieved");
			rowData.add("Crates Outstanding");

			
			expoExcel.setRowData(rowData);
			exportToExcelList.add(expoExcel);
			int cnt = 1;
			for (int i = 0; i < customerWiseConReportList.size(); i++) {
				expoExcel = new ExportToExcel();
				rowData = new ArrayList<String>();
				cnt = cnt + i;
				rowData.add("" + (cnt));
				rowData.add("" + customerWiseConReportList.get(i).getCustName());
				rowData.add("" + customerWiseConReportList.get(i).getGrandTotal());
				rowData.add("" + customerWiseConReportList.get(i).getCollectedAmt());
				rowData.add("" + customerWiseConReportList.get(i).getOutstandingAmt());
			
				rowData.add("" + customerWiseConReportList.get(i).getCratesOpBal());
				rowData.add("" + customerWiseConReportList.get(i).getCratesIssued());
				rowData.add("" + customerWiseConReportList.get(i).getCratesReceived());
				rowData.add((customerWiseConReportList.get(i).getCratesIssued()+customerWiseConReportList.get(i).getCratesOpBal())-customerWiseConReportList.get(i).getCratesReceived()+"");
	

				expoExcel.setRowData(rowData);
				exportToExcelList.add(expoExcel);

			}

			HttpSession session = request.getSession();
			session.setAttribute("exportExcelList", exportToExcelList);
			session.setAttribute("excelName", "CustomerWiseConReportList");

			System.out.println("customerWiseConReportList" + customerWiseConReportList.toString());
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}

		return customerWiseConReportList;

	}

	@RequestMapping(value = "/showCustomerwiseConsumptionReportPdf/{fromDate}/{toDate}", method = RequestMethod.GET)
	public void showCustomerwiseConsumptionReportPdf(@PathVariable("fromDate") String fromDate,
			@PathVariable("toDate") String toDate, HttpServletRequest request, HttpServletResponse response)
			throws FileNotFoundException {
		BufferedOutputStream outStream = null;
		System.out.println("Inside Pdf showBillwisePurchasePdf");

		List<CustomerWiseConReport> customerWiseConReport = customerWiseConReportList;

		// moneyOutList = prodPlanDetailList;
		Document document = new Document(PageSize.A4);
		// ByteArrayOutputStream out = new ByteArrayOutputStream();

		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		Calendar cal = Calendar.getInstance();

		System.out.println("time in Gen Bill PDF ==" + dateFormat.format(cal.getTime()));
		String FILE_PATH = Constants.REPORT_SAVE;
		File file = new File(FILE_PATH);

		PdfWriter writer = null;

		FileOutputStream out = new FileOutputStream(FILE_PATH);
		try {
			writer = PdfWriter.getInstance(document, out);
		} catch (DocumentException e) {

			e.printStackTrace();
		}

		PdfPTable table = new PdfPTable(9);
		try {
			System.out.println("Inside PDF Table try");
			table.setWidthPercentage(100);
			table.setWidths(new float[] { 1.4f, 3.7f, 2.8f, 2.8f, 2.8f, 3.2f, 3.5f, 2.9f, 3.3f });
			Font headFont = new Font(FontFamily.TIMES_ROMAN, 12, Font.NORMAL, BaseColor.BLACK);
			Font headFont1 = new Font(FontFamily.HELVETICA, 12, Font.BOLD, BaseColor.BLACK);
			headFont1.setColor(BaseColor.WHITE);
			Font f = new Font(FontFamily.TIMES_ROMAN, 12.0f, Font.UNDERLINE, BaseColor.BLUE);

			PdfPCell hcell = new PdfPCell();
			hcell.setBackgroundColor(BaseColor.PINK);

			hcell.setPadding(3);
			hcell = new PdfPCell(new Phrase("Sr.No.", headFont1));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			hcell.setBackgroundColor(BaseColor.PINK);

			table.addCell(hcell);

			hcell = new PdfPCell(new Phrase("Customer", headFont1));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			hcell.setBackgroundColor(BaseColor.PINK);

			table.addCell(hcell);

			hcell = new PdfPCell(new Phrase("Bill Amount", headFont1));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			hcell.setBackgroundColor(BaseColor.PINK);

			table.addCell(hcell);

			hcell = new PdfPCell(new Phrase("Received Amount", headFont1));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			hcell.setBackgroundColor(BaseColor.PINK);

			table.addCell(hcell);

			hcell = new PdfPCell(new Phrase("OS Amount", headFont1));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			hcell.setBackgroundColor(BaseColor.PINK);

			table.addCell(hcell);


			hcell = new PdfPCell(new Phrase("Crates OP", headFont1));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			hcell.setBackgroundColor(BaseColor.PINK);

			table.addCell(hcell);

			hcell = new PdfPCell(new Phrase("Crates Issued", headFont1));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			hcell.setBackgroundColor(BaseColor.PINK);

			table.addCell(hcell);

			hcell = new PdfPCell(new Phrase("Crates Received", headFont1));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			hcell.setBackgroundColor(BaseColor.PINK);

			table.addCell(hcell);

			hcell = new PdfPCell(new Phrase("Crates OS", headFont1));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			hcell.setBackgroundColor(BaseColor.PINK);

			table.addCell(hcell);


			int index = 0;
			for (CustomerWiseConReport bill : customerWiseConReportList) {
				index++;
				System.err.println(index);
				PdfPCell cell;

				cell = new PdfPCell(new Phrase(String.valueOf(index), headFont));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setPadding(3);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase(bill.getCustName(), headFont));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell.setPaddingRight(2);
				cell.setPadding(3);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase("" + bill.getGrandTotal(), headFont));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setPaddingRight(2);
				cell.setPadding(3);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase("" + bill.getCollectedAmt(), headFont));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				cell.setPaddingRight(2);
				cell.setPadding(3);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase("" + bill.getOutstandingAmt(), headFont));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setPaddingRight(2);
				cell.setPadding(3);
				table.addCell(cell);


				cell = new PdfPCell(new Phrase("" + bill.getCratesOpBal(), headFont));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setPaddingRight(2);
				cell.setPadding(3);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase("" + bill.getCratesIssued(), headFont));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setPaddingRight(2);
				cell.setPadding(3);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase("" + bill.getCratesReceived(), headFont));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setPaddingRight(2);
				cell.setPadding(3);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase((bill.getCratesIssued()+bill.getCratesOpBal())-bill.getCratesReceived()+"", headFont));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setPaddingRight(2);
				cell.setPadding(3);
				table.addCell(cell);


			}
			document.open();
			Paragraph name = new Paragraph("Dairy Power\n", f);
			name.setAlignment(Element.ALIGN_CENTER);
			document.add(name);
			document.add(new Paragraph(" "));
			Paragraph company = new Paragraph("Customer wise Consumption Report\n", f);
			company.setAlignment(Element.ALIGN_CENTER);
			document.add(company);
			document.add(new Paragraph(" "));

			DateFormat DF = new SimpleDateFormat("dd-MM-yyyy");
			String reportDate = DF.format(new Date());
			Paragraph p1 = new Paragraph("From Date:" + fromDate + "  To Date:" + toDate, headFont);
			p1.setAlignment(Element.ALIGN_CENTER);
			document.add(p1);
			document.add(new Paragraph("\n"));
			document.add(table);

			int totalPages = writer.getPageNumber();

			System.out.println("Page no " + totalPages);

			document.close();
			// Atul Sir code to open a Pdf File
			if (file != null) {

				String mimeType = URLConnection.guessContentTypeFromName(file.getName());

				if (mimeType == null) {

					mimeType = "application/pdf";

				}

				response.setContentType(mimeType);

				response.addHeader("content-disposition", String.format("inline; filename=\"%s\"", file.getName()));

				response.setContentLength((int) file.length());

				InputStream inputStream = new BufferedInputStream(new FileInputStream(file));

				try {
					FileCopyUtils.copy(inputStream, response.getOutputStream());
				} catch (IOException e) {
					System.out.println("Excep in Opening a Pdf File");
					e.printStackTrace();
				}
			}

		} catch (DocumentException ex) {

			System.out.println("Pdf Generation Error: BOm Prod  View Prod" + ex.getMessage());

			ex.printStackTrace();

		}

	}

	// ----------------------------------Vehicle Wise Report--------
	@RequestMapping(value = "/findVehicleWiseWiseReport", method = RequestMethod.GET)
	public @ResponseBody List<VehicleWiseReport> getVehicleWise(HttpServletRequest request,
			HttpServletResponse response) {

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

			VehicleWiseReport[] vehicleWiseReportList = restTemplate
					.postForObject(Constants.url + "Test/getAllVehiclewiseReport", map, VehicleWiseReport[].class);
			vehicleWiseReport = new ArrayList<VehicleWiseReport>(Arrays.asList(vehicleWiseReportList));

			System.out.println("new List cust wise consumption " + vehicleWiseReportList.toString());
			// export to excel

			List<ExportToExcel> exportToExcelList = new ArrayList<ExportToExcel>();

			ExportToExcel expoExcel = new ExportToExcel();
			List<String> rowData = new ArrayList<String>();

			rowData.add("Sr. No");
			rowData.add("Vehicle No");
			rowData.add("Customer Name");
			rowData.add("OP KM");
			rowData.add("CL KM");
			rowData.add("Total KM");

			expoExcel.setRowData(rowData);
			exportToExcelList.add(expoExcel);
			int cnt = 1;
			for (int i = 0; i < vehicleWiseReport.size(); i++) {
				expoExcel = new ExportToExcel();
				rowData = new ArrayList<String>();
				cnt = cnt + i;
				rowData.add("" + (cnt));
				rowData.add("" + vehicleWiseReport.get(i).getVehName());
				rowData.add("" + vehicleWiseReport.get(i).getCustName());
				rowData.add("" + vehicleWiseReport.get(i).getOutKm());
				rowData.add("" + vehicleWiseReport.get(i).getInKms());
				rowData.add("" + vehicleWiseReport.get(i).getTotalKm());

				expoExcel.setRowData(rowData);
				exportToExcelList.add(expoExcel);

			}

			HttpSession session = request.getSession();
			session.setAttribute("exportExcelList", exportToExcelList);
			session.setAttribute("excelName", "VehicleWiseReportList");

			System.out.println("VehicleWiseReport" + vehicleWiseReport.toString());
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return vehicleWiseReport;

	}

	@RequestMapping(value = "/showVehiclewiseReportPdf/{fromDate}/{toDate}", method = RequestMethod.GET)
	public void showVehiclewiseReportPdf(@PathVariable("fromDate") String fromDate,
			@PathVariable("toDate") String toDate, HttpServletRequest request, HttpServletResponse response)
			throws FileNotFoundException {
		BufferedOutputStream outStream = null;
		System.out.println("Inside Pdf showBillwisePurchasePdf");

		// moneyOutList = prodPlanDetailList;
		Document document = new Document(PageSize.A4);
		// ByteArrayOutputStream out = new ByteArrayOutputStream();

		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		Calendar cal = Calendar.getInstance();

		System.out.println("time in Gen Bill PDF ==" + dateFormat.format(cal.getTime()));
		String FILE_PATH = Constants.REPORT_SAVE;
		File file = new File(FILE_PATH);

		PdfWriter writer = null;

		FileOutputStream out = new FileOutputStream(FILE_PATH);
		try {
			writer = PdfWriter.getInstance(document, out);
		} catch (DocumentException e) {

			e.printStackTrace();
		}

		PdfPTable table = new PdfPTable(6);
		try {
			System.out.println("Inside PDF Table try");
			table.setWidthPercentage(100);
			table.setWidths(new float[] { 1.4f, 3.7f, 2.8f, 2.8f, 3.2f, 3.3f });
			Font headFont = new Font(FontFamily.TIMES_ROMAN, 12, Font.NORMAL, BaseColor.BLACK);
			Font headFont1 = new Font(FontFamily.HELVETICA, 12, Font.BOLD, BaseColor.BLACK);
			headFont1.setColor(BaseColor.WHITE);
			Font f = new Font(FontFamily.TIMES_ROMAN, 12.0f, Font.UNDERLINE, BaseColor.BLUE);

			PdfPCell hcell = new PdfPCell();
			hcell.setBackgroundColor(BaseColor.PINK);

			hcell.setPadding(3);
			hcell = new PdfPCell(new Phrase("Sr.No.", headFont1));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			hcell.setBackgroundColor(BaseColor.PINK);

			table.addCell(hcell);

			hcell = new PdfPCell(new Phrase("Vehicle Id", headFont1));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			hcell.setBackgroundColor(BaseColor.PINK);

			table.addCell(hcell);

			hcell = new PdfPCell(new Phrase("Customer Name", headFont1));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			hcell.setBackgroundColor(BaseColor.PINK);

			table.addCell(hcell);

			hcell = new PdfPCell(new Phrase("OP KM", headFont1));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			hcell.setBackgroundColor(BaseColor.PINK);

			table.addCell(hcell);

			hcell = new PdfPCell(new Phrase("CL KM", headFont1));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			hcell.setBackgroundColor(BaseColor.PINK);

			table.addCell(hcell);

			hcell = new PdfPCell(new Phrase("Total KM", headFont1));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			hcell.setBackgroundColor(BaseColor.PINK);

			table.addCell(hcell);

			int index = 0;
			for (VehicleWiseReport bill : vehicleWiseReport) {
				index++;
				PdfPCell cell;

				cell = new PdfPCell(new Phrase(String.valueOf(index), headFont));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setPadding(3);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase("" + bill.getVehName(), headFont));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setPaddingRight(2);
				cell.setPadding(3);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase(bill.getCustName(), headFont));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell.setPaddingRight(2);
				cell.setPadding(3);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase("" + bill.getOutKm(), headFont));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setPaddingRight(2);
				cell.setPadding(3);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase("" + bill.getInKms(), headFont));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				cell.setPaddingRight(2);
				cell.setPadding(3);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase("" + bill.getTotalKm(), headFont));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setPaddingRight(2);
				cell.setPadding(3);
				table.addCell(cell);

			}
			document.open();
			Paragraph name = new Paragraph("Dairy Power\n", f);
			name.setAlignment(Element.ALIGN_CENTER);
			document.add(name);
			document.add(new Paragraph(" "));
			Paragraph company = new Paragraph("Vehicle wise Consumption Report\n", f);
			company.setAlignment(Element.ALIGN_CENTER);
			document.add(company);
			document.add(new Paragraph(" "));

			DateFormat DF = new SimpleDateFormat("dd-MM-yyyy");
			String reportDate = DF.format(new Date());
			Paragraph p1 = new Paragraph("From Date:" + fromDate + "  To Date:" + toDate, headFont);
			p1.setAlignment(Element.ALIGN_CENTER);
			document.add(p1);
			document.add(new Paragraph("\n"));
			document.add(table);

			int totalPages = writer.getPageNumber();

			System.out.println("Page no " + totalPages);

			document.close();
			// Atul Sir code to open a Pdf File
			if (file != null) {

				String mimeType = URLConnection.guessContentTypeFromName(file.getName());

				if (mimeType == null) {

					mimeType = "application/pdf";

				}

				response.setContentType(mimeType);

				response.addHeader("content-disposition", String.format("inline; filename=\"%s\"", file.getName()));

				response.setContentLength((int) file.length());

				InputStream inputStream = new BufferedInputStream(new FileInputStream(file));

				try {
					FileCopyUtils.copy(inputStream, response.getOutputStream());
				} catch (IOException e) {
					System.out.println("Excep in Opening a Pdf File");
					e.printStackTrace();
				}
			}

		} catch (DocumentException ex) {

			System.out.println("Pdf Generation Error: BOm Prod  View Prod" + ex.getMessage());

			ex.printStackTrace();

		}

	}

}
