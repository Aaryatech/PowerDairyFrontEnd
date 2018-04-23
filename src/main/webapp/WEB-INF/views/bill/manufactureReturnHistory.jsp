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
	<c:url var="getMfgReturnRecordBetweenDate" value="/getMfgReturnRecordBetweenDate" /> 
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
					<h2 class="pageTitle">Manufacture History </h2>
					

				</div>
				<div class="col1title" align="right"> 
						<a href="${pageContext.request.contextPath}/returnManf"><input type="button" value="Return To Manufacture" class="btn btn-info">
										</a>
										</div>
				<form id="validation-form">

					<div class="colOuter">
						<!-- copy div kalpesh -->

						<div class="col-md-2" align="left">From:</div>
						<div class="col-md-2">
							<input id="datepicker" class="texboxitemcode texboxcal"
								value="${fromDate}" name="from_Date" type="text">
								
								<input id="toDay" class="texboxitemcode texboxcal"
								value="${fromDate}" name="toDay" type="hidden">
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
										<th class="col-md-1">Item Name</th> 
											<th class="col-md-1">Date</th> 
											<th class="col-md-1">Batch No</th>   
											
											<th class="col-md-1">Return Qty</th>  
											<th class="col-md-1">Crates Return Qty</th> 
										</tr>
									</thead>
									<tbody>
									
									<c:forEach items="${getMfgReturnList}" var="getMfgReturnList"
													varStatus="count">
													  
													<tr>
												     
														<td class="col-sm-1"><c:out value="${count.index+1}" /></td> 
														<td  class="col-md-2"><c:out value="${getMfgReturnList.itemName}" /> </td>
														<td class="col-md-1"><c:out value="${getMfgReturnList.date}" /></td> 
														<td class="col-md-1"><c:out value="${getMfgReturnList.batchId}" /></td> 
												 	 	<td class="col-md-1" ><c:out value="${getMfgReturnList.itemReturnQy}" /> </td>
												 	 	<td class="col-md-1" ><c:out value="${getMfgReturnList.cratesReturnQty}" /> </td>
												 	 	 
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
			'${getMfgReturnRecordBetweenDate}',

			{
				 
				fromDate : fromDate,
				toDate : toDate,  
			
				ajax : 'true'

			},
			function(data) {
				
				  	 
						  
				$('#table_grid td').remove(); 
				 
			  $.each( data,
							function(key, itemList) {
							 
								 
								var tr = $('<tr></tr>');
								 
								tr.append($('<td class="col-sm-1"></td>').html(key+1));
								tr.append($('<td class="col-md-2"></td>').html(itemList.itemName)); 
							  	tr.append($('<td class="col-md-1"></td>').html(itemList.date));
							  	tr.append($('<td class="col-md-1"></td>').html(itemList.batchId)); 
							  	tr.append($('<td class="col-md-1"></td>').html(itemList.itemReturnQy)); 
							  	tr.append($('<td class="col-md-1"></td>').html(itemList.cratesReturnQty)); 
							   
							    $('#table_grid tbody').append(tr);

								 

							})  
					 
				
			});
	
	
}

	</script>




</body>
</html>