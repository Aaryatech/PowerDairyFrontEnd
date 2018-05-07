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
	<c:url var="getPurchaseBillbetweenDate" value="/getPurchaseBillbetweenDate" /> 
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
								
								<input id="toDay" class="texboxitemcode texboxcal"
								value="${stockDate}" name="toDay" type="hidden">
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
											<th class="col-md-1">Invoice No</th>
											<th class="col-md-1">Date</th>   
											<th class="col-md-1"  >Grand Total</th> 
											<th class="col-md-1"  >Remark</th>  
											<th class="col-md-1"  >Action</th> 
										</tr>
									</thead>
									<tbody>
									
									<c:forEach items="${getPoHeaderList}" var="getPoHeaderList"
													varStatus="count">
													  
													<tr>
												     
														<td ><c:out value="${count.index+1}" /></td> 
														<td ><c:out value="${getPoHeaderList.poId}" /></td>
														<td ><c:out value="${getPoHeaderList.poDate}" /></td> 
														<td ><c:out value="${getPoHeaderList.poTotal}" /></td>
												 	 	<td  ><c:out value="${getPoHeaderList.poRemarks}" /> </td>
												 	 	 <td  ><a href="${pageContext.request.contextPath}/editPurchaseBill/${getPoHeaderList.poHeaderId}" class="action_btn" ><span class="fa fa-edit"></span></a> <a href="${pageContext.request.contextPath}/purchaseHeaderWithDetail/${getPoHeaderList.poHeaderId}" class="action_btn" ><abbr title="Details"><i class="fa fa-list"></i></abbr></a>
												 	 	 <a href="${pageContext.request.contextPath}/deletePurchaseBill/${getPoHeaderList.poHeaderId}" class="action_btn" ><span class="fa fa-trash-o" ></span></a>  
												 	 	  </td>
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
	var toDay=document.getElementById("toDay").value; 
	$
	.getJSON(
			'${getPurchaseBillbetweenDate}',

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
								tr.append($('<td ></td>').html(itemList.poId));
							  	tr.append($('<td ></td>').html(itemList.poDate));
							  	tr.append($('<td  ></td>').html(itemList.poTotal));
							  	tr.append($('<td  ></td>').html(itemList.poRemarks));
							  	//alert("stockDate " + toDay);
							  	//alert("itemList.poDate " + itemList.poDate);
							  	if(compareDate(toDay,itemList.poDate)==false)
						  		{
						  		tr.append($('<td></td>').html('<a href="${pageContext.request.contextPath}/purchaseHeaderWithDetail/'+itemList.poHeaderId+'" class="action_btn" ><abbr title="Details"><i class="fa fa-list"></i></abbr></a> '));
						  		}
						  	else
						  		{
						  		tr.append($('<td></td>').html('<a href="${pageContext.request.contextPath}/editPurchaseBill/'+itemList.poHeaderId+'" class="action_btn" ><span class="fa fa-edit"></span></a> <a href="${pageContext.request.contextPath}/purchaseHeaderWithDetail/'+itemList.poHeaderId+'" class="action_btn" ><abbr title="Details"><i class="fa fa-list"></i></abbr></a> <a href="${pageContext.request.contextPath}/deletePurchaseBill/'+itemList.poHeaderId+'" class="action_btn" ><span class="fa fa-trash-o" ></span></a> '));
						  		}
							  
							  	
							    $('#table_grid tbody').append(tr);

								 

							})  
					 
				
			});
	
	
}

function compareDate(toDay,poDate) {
	 
	 
	//alert(toDay,poDate);
	var isValid=false;
	
	var stockDate = toDay.split('-'); 
	var toDateValue = poDate.split('-');
	//var fromDateValue = fromDate.split('-');
	
	var tDate=new Date();
	tDate.setFullYear(toDateValue[2],(toDateValue[1] - 1 ),toDateValue[0]);
	 
	var sDate=new Date();
	sDate.setFullYear(stockDate[2],(stockDate[1] - 1 ),stockDate[0]);
	
	/* var frDate=new Date();
	frDate.setFullYear(fromDateValue[2],(fromDateValue[1] - 1 ),fromDateValue[0]); */
 
	if(sDate<=tDate) 
		{
			 
		isValid=true; 
		}
		
	else
		{
		isValid = false; 
		}
		
 //alert(isValid);
       return isValid;
 
}

	</script>




</body>
</html>