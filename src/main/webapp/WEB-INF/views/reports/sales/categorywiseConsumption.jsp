<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>


<%-- <!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, minimum-scale=1, maximum-scale=1">

<title>Monginis</title>


<link
	href="${pageContext.request.contextPath}/resources/css/monginis.css"
	rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
<link href="${pageContext.request.contextPath}/resources/css/custom.css" rel="stylesheet" type="text/css"/>	
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">	
<link rel="icon"
	href="${pageContext.request.contextPath}/resources/images/feviconicon.png"
	type="image/x-icon" />
	
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>	
	
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/jquery-1.10.2.min.js"></script>

<!--rightNav-->
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/menuzord.js"></script>
	
<script type="text/javascript">
jQuery(document).ready(function(){
	jQuery("#menuzord").menuzord({
		align:"left"
	});
});
</script>
<!--rightNav-->


</head>
<body> --%>
<!--datepicker-->
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/jquery-ui.js"></script>
<script>
	$(function() {
		$("#todatepicker").datepicker({
			dateFormat : 'dd-mm-yy'
		});
	});
	$(function() {
		$("#fromdatepicker").datepicker({
			dateFormat : 'dd-mm-yy'
		});
	});
</script>
<!--datepicker-->

<c:url var="getBillWiseConsumption" value="/findBillWiseConsumption" />

<div class="sidebarOuter"></div>

<div class="wrapper">

	<!--topHeader-->

	<jsp:include page="/WEB-INF/views/include/logo.jsp">
		<jsp:param name="frDetails" value="${frDetails}" />

	</jsp:include>

	<!--topHeader-->

	<!--rightContainer-->
	<div class="fullGrid center">
		<!--fullGrid-->
		<div class="wrapperIn2">

			<!--leftNav-->

			<jsp:include page="/WEB-INF/views/include/left.jsp">
				<jsp:param name="myMenu" value="${menuList}" />

			</jsp:include>


			<!--leftNav-->
			<!--rightSidebar-->

			<!-- Place Actual content of page inside this div -->
			<div class="sidebarright">


				<div class="row">
					<div class="col-md-12">
						<h2 class="pageTitle">Billwise Consumption Report</h2>
					</div>
				</div>

				<div class="row">

					<div class="col-md-1 from_date">
						<h4 class="pull-left">From Date:-</h4>
					</div>
					<div class="col-md-2 ">
						<input id="fromdatepicker" class="texboxitemcode texboxcal"
							placeholder="DD-MM-YYYY" name="fromDate" type="text">
					</div>
					<div class="col-md-1">
						<h4 class="pull-left">To Date:-</h4>
					</div>
					<div class="col-md-2 ">
						<input id="todatepicker" class="texboxitemcode texboxcal"
							placeholder="DD-MM-YYYY" name="toDate" type="text">
					</div>
					<div class="col-md-1">
						<div class="col1title" align="left">Customer:</div>
					</div>
					<div class="col-md-3">
						<select class="form-control" title="Please Select" name="custId"
							id="custId" onchange="onCustomerChange(this.value)" required>
							<option value="0" selected>All</option>

							<c:forEach items="${customerList}" var="customerList">
								<option value="${customerList.custId}">${customerList.custName}</option>
							</c:forEach>
						</select>

					</div>
					<div class="col-md-2">
						<button class="btn search_btn pull-left"
							onclick="billWiseConsumptionReport()">Search</button>
						<%-- 		  &nbsp;&nbsp;&nbsp;   <a href='${pageContext.request.contextPath}/pdf?reportURL=showPurchaseBillwiseReportPdf' id="btn_pdf" class="btn search_btn" style="display: none">PDF</a>
 --%>
						<button class="btn btn-primary" value="PDF" id="PDFButton"
							onclick="genPdf()" disabled="disabled">PDF</button>

					</div>

				</div>

				<div class="row">
					<div class="clearfix"></div>


					<div id="table-scroll" class="table-scroll">
						<div id="faux-table" class="faux-table" aria="hidden">
							<table id="table_grid" class="main-table">
								<thead>
									<tr class="bgpink">

										<th class="col-sm-1">Sr.</th>
										<th class="col-md-1">Bill No.</th>
										<th class="col-md-1">Bill Date</th>
										<th class="col-md-1">Customer</th>
										<th class="col-md-1">Bill Amt</th>
										<th class="col-md-1">Collected Amt</th>
										<th class="col-md-1">Outstanding Amt</th>
										<th class="col-md-1">Paymode</th>
										<th class="col-md-1">Crates Total</th>
										<th class="col-md-1">Crates Received</th>
										<th class="col-md-1">Crates Outstanding</th>
										<th class="col-md-1">Total Kms</th>
										<th class="col-md-1">Remark</th>
									</tr>

								</thead>
								<tbody>
							</table>
						</div>
						<div class="table-wrap">
							<table id="table_grid" class="main-table">
								<thead>
									<tr class="bgpink">

										<th class="col-sm-1">Sr.No</th>
										<th class="col-md-1">Bill No.</th>
										<th class="col-md-1">Bill Date</th>
										<th class="col-md-1">Customer</th>
										<th class="col-md-1">Bill Amt</th>
										<th class="col-md-1">Collected Amt</th>
										<th class="col-md-1">Outstanding Amt</th>
										<th class="col-md-1">Paymode</th>
										<th class="col-md-1">Crates Total</th>
										<th class="col-md-1">Crates Received</th>
										<th class="col-md-1">Crates Outstanding</th>
										<th class="col-md-1">Total Kms</th>
										<th class="col-md-1">Remark</th>
									</tr>

								</thead>
								<tbody>
							</table>

						</div>

					</div>
					<!--table end-->
					<br>
					<div class="form-group" style="display: none;" id="range">



						<div class="col-sm-3  controls">
							<input type="button" id="expExcel" class="btn btn-primary"
								value="EXPORT TO Excel" onclick="exportToExcel();"
								disabled="disabled">
						</div>
					</div>
				</div>
			</div>




		</div>
		<!--rightSidebar-->

	</div>
	<!--fullGrid-->
</div>
<!--rightContainer-->

</div>
<!--wrapper-end-->

<!--easyTabs-->
<script src="${pageContext.request.contextPath}/resources/js/main.js"></script>
<!--easyTabs-->


<script type="text/javascript">
	function billWiseConsumptionReport() {
		alert("Heee");
		$('#table_grid td').remove();

		var isValid = validate();

		if (isValid) {

			//document.getElementById('btn_pdf').style.display = "block";
			var fromDate = document.getElementById("fromdatepicker").value;
			var toDate = document.getElementById("todatepicker").value;

			$
					.getJSON(
							'${getBillWiseConsumption}',
							{

								fromDate : fromDate,
								toDate : toDate,
								ajax : 'true',

							},
							function(data) {

								alert(data)
								var len = data.length;

								if (data == "") {
									document.getElementById("expExcel").disabled = true;
									document.getElementById("PDFButton").disabled = true;
								}
								document.getElementById("PDFButton").disabled = false;

								$('#table_grid td').remove();

								$
										.each(
												data,
												function(key,
														billWisePurchaseData) {

													document
															.getElementById("expExcel").disabled = false;
													document
															.getElementById("PDFButton").disabled = false;
													document
															.getElementById('range').style.display = 'block';

													var partyname = "GFPL";
													var gstNo = "#012";

													var tr = $('<tr></tr>');

													tr
															.append($(
																	'<td class="col-sm-1"></td>')
																	.html(
																			key + 1));

													tr
															.append($(
																	'<td class="col-md-2"></td>')
																	.html(
																			billWisePurchaseData.billId));
													tr
															.append($(
																	'<td class="col-md-1"></td>')
																	.html(
																			billWisePurchaseData.billDate));

													tr
															.append($(
																	'<td class="col-md-2"style="text-align:center"></td>')
																	.html(
																			billWisePurchaseData.custName));
													tr
															.append($(
																	'<td class="col-md-2"style="text-align:center"></td>')
																	.html(
																			billWisePurchaseData.grandTotal));
													tr
															.append($(
																	'<td class="col-md-1"style="text-align:center"></td>')
																	.html(
																			billWisePurchaseData.collectedAmt));

													tr
															.append($(
																	'<td class="col-md-1"style="text-align:center"></td>')
																	.html(
																			billWisePurchaseData.outstandingAmt));
													tr
															.append($(
																	'<td class="col-md-1"style="text-align:center"></td>')
																	.html(
																			billWisePurchaseData.collectionPaymode));
												
													tr
															.append($(
																	'<td class="col-md-2"style="text-align:center"></td>')
																	.html(
																			billWisePurchaseData.cratesOpBal));

													tr
															.append($(
																	'<td class="col-md-2"style="text-align:center"></td>')
																	.html(
																			billWisePurchaseData.cratesIssued));

													tr
															.append($(
																	'<td class="col-md-2"style="text-align:center"></td>')
																	.html(
																			billWisePurchaseData.cratesClBal));

													tr
															.append($(
																	'<td class="col-md-2"style="text-align:center"></td>')
																	.html(
																			billWisePurchaseData.cratesReceived));

													tr
															.append($(
																	'<td class="col-md-2"style="text-align:center"></td>')
																	.html(
																			billWisePurchaseData.remarks));

													$('#table_grid tbody')
															.append(tr);

												});
							}

					);
		}
	}
</script>
<script type="text/javascript">
	function validate() {

		var fromDate = $("#fromdatepicker").val();
		var toDate = $("#todatepicker").val();

		var isValid = true;

		if (fromDate == "" || fromDate == null) {

			isValid = false;
			alert("Please select From Date");
		} else if (toDate == "" || toDate == null) {

			isValid = false;
			alert("Please select To Date");
		}
		return isValid;

	}
</script>
<script>
	/*
	//  jquery equivalent
	jQuery(document).ready(function() {
	jQuery(".main-table").clone(true).appendTo('#table-scroll .faux-table').addClass('clone');
	jQuery(".main-table.clone").clone(true).appendTo('#table-scroll .faux-table').addClass('clone2'); 
	});
	 */
	(function() {
		var fauxTable = document.getElementById("faux-table");
		var mainTable = document.getElementById("table_grid");
		var clonedElement = table_grid.cloneNode(true);
		var clonedElement2 = table_grid.cloneNode(true);
		clonedElement.id = "";
		clonedElement2.id = "";
		fauxTable.appendChild(clonedElement);
		fauxTable.appendChild(clonedElement2);
	})();

	function exportToExcel() {

		window.open("${pageContext.request.contextPath}/exportToExcel");
		document.getElementById("expExcel").disabled = true;
	}
</script>
<script type="text/javascript">
	function genPdf() {
		var fromDate = document.getElementById("fromdatepicker").value;
		var toDate = document.getElementById("todatepicker").value;

		window
				.open('${pageContext.request.contextPath}/showBillwiseConsumptionPurchasePdf/'
						+ fromDate + '/' + toDate);

	}
</script>
</body>
</html>
