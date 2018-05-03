<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script type="text/javascript"
	src="https://www.gstatic.com/charts/loader.js"></script>
<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
<style type="text/css">
table, th, td {
    border: 1px solid #9da88d;
}
</style>
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

<c:url var="getDateWiseConsumption" value="/findDatewiseConsumption" />

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
						<h2 class="pageTitle">Datewise Consumption Report</h2>
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
					
					<div class="col-md-3">
						<button class="btn search_btn pull-left"
							onclick="dateWiseConsumptionReport()">Search</button>
						<%-- 		  &nbsp;&nbsp;&nbsp;   <a href='${pageContext.request.contextPath}/pdf?reportURL=showPurchaseBillwiseReportPdf' id="btn_pdf" class="btn search_btn" style="display: none">PDF</a>
 --%>
						<button class="btn btn-primary" value="PDF" id="PDFButton"
							onclick="genPdf()" disabled="disabled">PDF</button>
						
 <button class="btn btn-primary"
							onclick="showChart()">Graph</button>
					</div>

				</div>

				<div class="row">
					<div class="clearfix"></div>


					<div id="table-scroll" class="table-scroll">
						<div id="faux-table" class="faux-table" aria="hidden">
							<table id="table_grid" class="main-table">
								<thead>
									<tr class="bgpink">
                                 	</tr>

								</thead>
								<tbody>
							</table>
						</div>
						<div class="table-wrap">
							<table id="table_grid" class="main-table">
								<thead>
									<tr class="bgpink">

										<th class="col-sm-1"style="text-align: center;">Sr.No</th>
										<th class="col-md-2"style="text-align: center;">Bill Date</th>
										<th class="col-md-1"style="text-align: center;">Bill Amt</th>
										<th class="col-md-1"style="text-align: center;">Collected Amt</th>
										<th class="col-md-1"style="text-align: center;">Outstanding Amt</th>
										<th class="col-md-1"style="text-align: center;">Crates Total</th>
										<th class="col-md-1"style="text-align: center;">Crates Received</th>
										<th class="col-md-1"style="text-align: center;">Crates Outstanding</th>
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

<div id="chart" style="display: none;"><br><br><br>
	<hr><div>
	 
			<div  id="chart_div" style="width:80%; height:300; float:right;" ></div> 
		 
			<div   id="Piechart" style="width:80%; height:300; float:left;" ></div> 
			</div>
			 
			<div class="colOuter" align="right" >
			
				<div   id="PieChart_div" style="width:80%; height:300;" align="center" ></div>
				
				<div id="chart_div" style="width: 80%; height: 300;" align="center"></div>
				</div>
				 
				</div>
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
	function dateWiseConsumptionReport() {
		$('#table_grid td').remove();

		var isValid = validate();

		if (isValid) {

			//document.getElementById('btn_pdf').style.display = "block";
			var fromDate = document.getElementById("fromdatepicker").value;
			var toDate = document.getElementById("todatepicker").value;

			$
					.getJSON(
							'${getDateWiseConsumption}',
							{

								fromDate : fromDate,
								toDate : toDate,
								ajax : 'true',

							},
							function(data) {

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
														bill) {

													document
															.getElementById("expExcel").disabled = false;
													document
															.getElementById("PDFButton").disabled = false;
													document
															.getElementById('range').style.display = 'block';


													var tr = $('<tr></tr>');

													tr
															.append($(
																	'<td class="col-md-1"></td>')
																	.html(
																			key + 1));

													tr
															.append($(
																	'<td class="col-md-1" style="text-align:center"></td>')
																	.html(bill.billDate));

													tr
															.append($(
																	'<td class="col-md-1"style="text-align:right"></td>')
																	.html(bill.grandTotal));
													tr
															.append($(
																	'<td class="col-md-1"style="text-align:right"></td>')
																	.html(bill.collectedAmt));

													tr
															.append($(
																	'<td class="col-md-1"style="text-align:right"></td>')
																	.html(
																			bill.outstandingAmt));
													tr
													.append($(
															'<td class="col-md-1"style="text-align:right"></td>')
															.html(bill.cratesIssued+bill.cratesOpBal));
													tr
															.append($(
																	'<td class="col-md-1"style="text-align:right"></td>')
																	.html(bill.cratesReceived));

													tr
															.append($(
																	'<td class="col-md-1"style="text-align:right"></td>')
																	.html((bill.cratesIssued+bill.cratesOpBal)-bill.cratesReceived));

				
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
				.open('${pageContext.request.contextPath}/showDatewiseConsumptionPdf/'
						+ fromDate + '/' + toDate);

	}
</script>

<script type="text/javascript">

function showChart(){
	
	$("#PieChart_div").empty();
	$("#chart_div").empty();
		document.getElementById('chart').style.display = "block";
		   document.getElementById("table_grid").style="display:none";
		 
		   document.getElementById('chart').style.display ="display:none";
		   document.getElementById("table_grid").style= "block"; 
			var isValid = validate();

			if (isValid) { 
				//document.getElementById('btn_pdf').style.display = "block";
				var fromDate = document.getElementById("fromdatepicker").value;
				var toDate = document.getElementById("todatepicker").value;
				$
						.getJSON(
								'${getDateWiseConsumption}',
								{

									fromDate : fromDate,
									toDate : toDate,
									ajax : 'true',

								},
								function(data) {
                      
							 if (data == "") {
									alert("No records found !!");
									//$('#loader').hide();

								}
							 var i=0;
							// $('#loader').hide();
							 google.charts.load('current', {'packages':['corechart', 'bar']});
							 google.charts.setOnLoadCallback(drawStuff);

							 function drawStuff() {
								 
								// alert("Inside DrawStuff");
 
							   var chartDiv = document.getElementById('chart_div');
							   document.getElementById("chart_div").style.border = "thin dotted red";
							   
							   
							   var PiechartDiv = document.getElementById('PieChart_div');
							   document.getElementById("PieChart_div").style.border = "thin dotted red";
							   
							   
						       var dataTable = new google.visualization.DataTable();
						       dataTable.addColumn('string', 'Date'); // Implicit data column.
						       dataTable.addColumn('number', 'Bill Amount');
						       dataTable.addColumn('number', 'Outstanding Amount');
						       dataTable.addColumn('number', 'Collected Amount');

						       
						       var piedataTable = new google.visualization.DataTable();
						       piedataTable.addColumn('string', 'Date'); // Implicit domain column.
						       piedataTable.addColumn('number', 'Bill Amount');
						       
						       
						   	$
							.each(
									data,
									function(key,
											bill) {                       


                                  var billdate=bill.billDate;
                                  var grandTotal=bill.grandTotal;
                                  var collectedAmt=bill.collectedAmt;
                                  var outstandingAmt=bill.outstandingAmt;
								   dataTable.addRows([
									 
									   
									   [billdate,grandTotal,outstandingAmt,collectedAmt],
									   
								           ]);
								   
								   
								   piedataTable.addRows([
									 
									   [billdate, grandTotal],
									   
								           ]);
								     }); // end of  $.each(data,function(key, report) {-- function

            // Instantiate and draw the chart.
          						    
 var materialOptions = {
          width: 500,
          chart: {
            title: 'Datewise Consumption  Report',
            subtitle: 'Quantity And Total',

          },
          series: {
            0: { axis: 'distance' }, // Bind series 0 to an axis named 'distance'.
            1: { axis: 'brightness' } // Bind series 1 to an axis named 'brightness'.
          },
          axes: {
            y: {
              distance: {label: 'Quantity'}, // Left y-axis.
              brightness: {side: 'right', label: 'Total'} // Right y-axis.
            }
          }
        };
						       
						       function drawMaterialChart() {
						           var materialChart = new google.charts.Bar(chartDiv);
						           
						          // alert("mater chart "+materialChart);
						           materialChart.draw(dataTable, google.charts.Bar.convertOptions(materialOptions));
						          // button.innerText = 'Change to Classic';
						          // button.onclick = drawClassicChart;
						         }
						       
						        var chart = new google.visualization.ColumnChart(
						                document.getElementById('chart_div'));
						        
						        var Piechart = new google.visualization.PieChart(
						                document.getElementById('PieChart_div'));
						       chart.draw(dataTable,
						          {title: 'Datewise Consumption Report'});
						       
						       
						       Piechart.draw(piedataTable,
								          {title: 'Datewise Consumption Report', is3D:true});
						      // drawMaterialChart();
							 };
										
							  	});
}
			}							</script>
</body>
</html>
