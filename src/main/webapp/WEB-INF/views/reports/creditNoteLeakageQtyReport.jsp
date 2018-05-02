<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>

<!--topLeft-nav-->
<div class="sidebarOuter"></div>
<!--topLeft-nav-->

<!--wrapper-start-->
<div class="wrapper">

	<!--topHeader-->
	<c:url var="getCreditNoteLeakageQtyReport" value="/getCreditNoteLeakageQtyReport" /> 
	<jsp:include page="/WEB-INF/views/include/logo.jsp"></jsp:include>


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
			<div class="sidebarright">
				<div class="order-left">
					<h2 class="pageTitle">Purchase Bill History </h2>
					

				</div>
				<div class="col1title" align="right"> 
						<a href="${pageContext.request.contextPath}/purchaseBill"><input type="button" value="Purchase Bill" class="btn btn-info">
										</a>
										</div>
				<form id="validation-form">

					<div class="colOuter">
						<!-- copy div kalpesh -->

						<div class="col-md-2" align="left">From:</div>
						<div class="col-md-2">
							<input id="datepicker" class="texboxitemcode texboxcal"
								value="${fromDate}" name="from_Date" type="text">
								
								 
						</div>
						<div class="col-md-1" align="left"></div>
						 
						<div class="col-md-1" align="left">TO:</div>
						<div class="col-md-2">
							<input id="datepicker2" class="texboxitemcode texboxcal"
								value="${toDate}" name="to_Date" type="text">
								 
						</div>
						
						<div class="col-md-1" align="left"></div>
						<div class="col-md-1">
							<button type="button" class="btn  buttonsaveorder"
								onclick="serchOtherBill()">Search</button>
						</div>
					 

					</div>
					
					<div class="colOuter">
						   
						<button type="button" class="btn  buttonsaveorder"
							onclick="exportToExcel()">Export To Excel</button>
						  
					 
						<button type="button" class="btn  buttonsaveorder" value="PDF" id="PDFButton"
							onclick="genPdf()"  >PDF</button>
					 
					</div>
						



					<!--tabNavigation-->
					<div class="cd-tabs">
						<!--tabMenu-->

						<!--tabMenu-->


						<div id="table-scroll" class="table-scroll">
							<div id="faux-table" class="faux-table" aria="hidden"></div>
							<div class="table-wrap">
								<table id="table_grid" class="main-table">
									<thead>
										<tr class="bgpink">
										<th class="col-md-1">Sr No</th>
											<th class="col-md-1">Item Name </th>
											<th class="col-md-1">Customer Name</th>   
											<th class="col-md-1"  >Leakage Qty</th> 
											<th class="col-md-1"  >Expire Qty</th> 
										</tr>
									</thead>
									<tbody>
									
									<c:forEach items="${leakageQtyReportList}" var="leakageQtyReportList"
													varStatus="count">
													  
													<tr>
												     
														<td ><c:out value="${count.index+1}" /></td> 
														<td ><c:out value="${leakageQtyReportList.itemName}" /></td>
														<td ><c:out value="${leakageQtyReportList.custName}" /></td> 
														<td ><c:out value="${leakageQtyReportList.leakageQty}" /></td>
												 	 	<td  ><c:out value="${leakageQtyReportList.expireQty}" /> </td> 
													</tr>
																 
												</c:forEach> 
 
									</tbody>

								</table>
							</div>
						</div>



					</div>
					<!--tabNavigation-->


				</form>
			</div>

			<!--rightSidebar-->

		</div>
		<!--fullGrid-->
	</div>
	<!--rightContainer-->

</div>
<!--wrapper-end-->
 
  
<script type="text/javascript">
		function validate() {
		
			var fromDate =$("#datepicker").val();
			var toDate =$("#datepicker2").val();
			
			var headeIdText=$("#headeIdText").val();
			alert(headeIdText);
			
			var isValid = true;
			if(headeIdText =="" || headeIdText == null){
				 isValid = false;
			}
			else if (fromDate == "" || fromDate == null) {

				isValid = false;
				alert("Please select From Date");
			}
		 else if (toDate == "" || toDate == null) {

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

function serchOtherBill()
{
	var fromDate=document.getElementById("datepicker").value;
	var toDate=document.getElementById("datepicker2").value;  
	$
	.getJSON(
			'${getCreditNoteLeakageQtyReport}',

			{
				 
				fromDate : fromDate,
				toDate : toDate, 
				ajax : 'true'

			},
			function(data) {
				
				  	 
						 
				$('#table_grid td').remove(); 
				  
			  $.each(
							data,
							function(key, itemList) {
							

								var tr = $('<tr></tr>');
								 
								tr.append($('<td ></td>').html(key+1));
								tr.append($('<td ></td>').html(itemList.itemName));
							  	tr.append($('<td ></td>').html(itemList.custName));
							  	tr.append($('<td  ></td>').html(itemList.leakageQty));
							  	tr.append($('<td  ></td>').html(itemList.expireQty));  
							    $('#table_grid tbody').append(tr);

								 

							})  
					 
				
			});
	
	
}
function exportToExcel() {

	window.open("${pageContext.request.contextPath}/exportToExcel"); 
}

function genPdf() {

	var fromDate=document.getElementById("datepicker").value;
	var toDate=document.getElementById("datepicker2").value;

	window
			.open('${pageContext.request.contextPath}/showLeakageQtyReportPdf/'
					+ fromDate + '/' + toDate);

}
	</script>




</body>
</html>