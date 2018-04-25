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
	@RequestMapping(value = "/showBillwisePurchasePdf", method = RequestMethod.GET)
	public void showBillwisePurchasePdf(HttpServletRequest request, HttpServletResponse response)
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
		String timeStamp = dateFormat.format(cal.getTime());
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
			table.setWidths(new float[] { 1.4f,3.7f, 2.8f,2.8f,3.2f,3.5f,2.9f,3.3f,2.9f,3.2f});
			Font headFont = new Font(FontFamily.TIMES_ROMAN, 12, Font.NORMAL, BaseColor.BLACK);
			Font headFont1 = new Font(FontFamily.HELVETICA, 12, Font.BOLD, BaseColor.BLACK);
			headFont1.setColor(BaseColor.WHITE);
			Font f = new Font(FontFamily.TIMES_ROMAN, 12.0f, Font.UNDERLINE, BaseColor.BLUE);

			PdfPCell hcell=new PdfPCell();
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
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell.setPaddingRight(2);
				  cell.setPadding(3);
				table.addCell(cell);

			
				cell = new PdfPCell(new Phrase(bill.getPoId(), headFont));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setPaddingRight(2);
				  cell.setPadding(3);
				table.addCell(cell);
				
				cell = new PdfPCell(new Phrase(""+bill.getPoTotal(), headFont));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				cell.setPaddingRight(2);
				  cell.setPadding(3);
				table.addCell(cell);
				
				cell = new PdfPCell(new Phrase(""+bill.getCratesReceivedQty(), headFont));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setPaddingRight(2);
				  cell.setPadding(3);
				table.addCell(cell);
				
				cell = new PdfPCell(new Phrase(""+bill.getShortNo(), headFont));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setPaddingRight(2);
				  cell.setPadding(3);
				table.addCell(cell);
				
				cell = new PdfPCell(new Phrase(""+bill.getExtraNo(), headFont));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setPaddingRight(2);
				  cell.setPadding(3);
				table.addCell(cell);
				
				cell = new PdfPCell(new Phrase(""+bill.getPoLeakageQty(), headFont));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setPaddingRight(2);
				  cell.setPadding(3);
				table.addCell(cell);
				
				cell = new PdfPCell(new Phrase(""+bill.getUserName(), headFont));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setPaddingRight(2);
				  cell.setPadding(3);
				table.addCell(cell);
				
				cell = new PdfPCell(new Phrase(""+bill.getPoRemarks(), headFont));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setPaddingRight(2);
				cell.setPadding(3);
				table.addCell(cell);
			
			}
			document.open();
			Paragraph company = new Paragraph(
					"Billwise Purchase Report\n",
					f);
			company.setAlignment(Element.ALIGN_CENTER);
			document.add(company);
			document.add(new Paragraph(" "));
		
			DateFormat DF = new SimpleDateFormat("dd-MM-yyyy");
			String reportDate = DF.format(new Date());

			document.add(new Paragraph(" Date: " + reportDate));
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
