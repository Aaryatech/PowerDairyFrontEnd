

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

 

</head>
<body>
 --%>

<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
<c:url var="editFrSupplier" value="/editFrSupplier"></c:url>

<!--datepicker-->
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/jquery-ui.js"></script>
<script>
		$(function() {
			$("#fromdatepicker").datepicker({
				dateFormat : 'dd-mm-yy'
			});
		});
		$(function() {
			$("#todatepicker").datepicker({
				dateFormat : 'dd-mm-yy'
			});
		});
	</script>
<!--datepicker-->

<!--topLeft-nav-->
<div class="sidebarOuter"></div>
<!--topLeft-nav-->

<!--wrapper-start-->
<div class="wrapper">

	<!--topHeader-->
	<c:url var="findAddOnRate" value="/getAddOnRate" />
	<c:url var="findItemsByCatId" value="/getFlavourBySpfId" />
	<c:url var="findAllMenus" value="/getAllTypes" />
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


		 
			<div class="sidebarright">
				 
				<form name="frm_search" id="frm_search" method="post"
					action="${pageContext.request.contextPath}/insertSupplier">
					<input type="hidden" name="mod_ser" id="mod_ser"
						value="search_result">

					
						<div class="col-md -3">
							
								<div class="col1title" align="left"><h3> Approve Bill</h3></div>
								 
						</div>
						
					<div class="colOuter">
						<div class="col-md-3">
							<div class="col1title" align="left"> Customer  : Mahesh </div>
							
						</div>
						  
						<div class="col-md-1">
							 
						</div>

						<div class="col-md-3">
							<div class="col1title" align="left"> Vehicle No.: MH-15-1772 </div>
							
						</div>
						  
					</div> 
					
					 <div class="colOuter"> 
					  
					 
						<div class="col-md-2">
							<div class="col1title" align="left"> Bill Date : ${date}</div>
						</div>
						<div class="col-md-2">
							 

						</div>
					</div>
					   
					 <div class="colOuter"> 
					</div>
					<div id="table-scroll" class="table-scroll">
									<div id="faux-table" class="faux-table" aria="hidden"></div>
									<div class="table-wrap table-wrap-custbill">
										<table id="table_grid1" class="main-table small-td">
											<thead>
												<tr class="bgpink">
													<th class="col-sm-1">Sr no.</th>
													<th class="col-md-1">Batch No</th>
													<th class="col-md-2">Item Name</th>
													<th class="col-md-1">Temp Qty</th> 
													<th class="col-md-1">Return Qty</th>  
													<th class="col-md-1">Leakage Qty</th> 
													<th class="col-md-1">Rate </th> 
													<th class="col-md-1">Amount</th>
													<th class="col-md-1">Tax%</th>
													<th class="col-md-1">Tax Amt</th>
													<th class="col-md-1">Total</th> 
												</tr>
											</thead>
											<tbody>
											
											<tr>
												<td class="col-md-1"><c:out value="${1}" /></td> 
												<td class="col-md-1"><c:out value="1" /></td> 
												<td class="col-md-1"><c:out value="Milk" /></td> 
												<td class="col-md-1"><c:out value="90" /></td> 
												<td class="col-md-1"><input type="text" min="0" max="500"
																			class="form-control" name="discPer" id="discPer" value="0"
																			onkeypress="onQty(event,1)"
																			oninput="validity.valid||(value='');"></td>
												<td class="col-md-1"><input type="text" min="0" max="500"
																			class="form-control" name="discPer" id="discPer" value="0"
																			onkeypress="onQty(event,1)"
																			oninput="validity.valid||(value='');"></td>
												<td class="col-md-1">50</td>
												
												<td><h4 id="discAmt${1}" >4500</h4></td>
												
												<td><h4 id="discAmt${1}" >12</h4></td>
												<td><h4 id="discAmt${1}" >540</h4></td>
												<td><h4 id="discAmt${1}" >5040</h4></td>
												 
												</tr>

											</tbody>

										</table>
									</div>
								</div>
								
								
								
								<div class="row">
									<div class="col-md-4">
										<h4 class="col-md-7">
											<b>Amount:-</b>
										</h4>
										<h4 class="col-md-5" id="totalSum">4500</h4>
										<input type="hidden" class="form-control" id="totalSumText" name="totalSumText">
									</div>

									<div class="col-md-4">
										<h4 class="col-md-7" style="margin-top: 5px">
											<b>Tax Total:-</b>
										</h4>

										<h4 class="col-md-5" id="taxtotal">540</h4>
										<input type="hidden" class="form-control" id="taxtotalText" name="taxtotalText">
									</div>

									<div class="col-md-4">
										<h4 class="col-md-7" style="margin-top: 5px">
											<b>Grand Total:-</b>
										</h4>

										<h4 class="col-md-5" id="grandTotal">5040</h4>
										<input type="hidden" class="form-control" id="grandTotalText" name="grandTotalText">
									</div>

									<div class="clearfix"></div>
									 
								</div>
								
								<div class="colOuter">
						 
					</div> 
					<div class="colOuter">
						 
					</div> 
					  
					  <div class="colOuter">
						<div class="col-md-2">
							<div class="col1title" align="left"> Collected Amt *: </div>
						</div>
						<div class="col-md-3">
							<input id="collectAmt" style="text-align: left;" class="form-control"
								placeholder="Collected Amt" name="collectAmt"   type="number" required>

						</div>
						<div class="col-md-1"> 
						</div>
						
						<div class="col-md-2">
							<div class="col1title" align="left"> Outstanding Amt *: </div>
						</div>
						<div class="col-md-3">
						Previous Out Standing + Current Out Standing
							<!-- <input id="outstandingAmt" style="text-align: left;" class="form-control"
								placeholder="Outstanding Amt" name="outstandingAmt"   type="number" disabled> -->

						</div>
						 
 
					</div>
					
					<div class="colOuter">
						
						<div class="col-md-2">
							<div class="col1title" align="left">Received Creates Qty*: </div>
						</div>
						<div class="col-md-3">
							<input id="recCreatesQty" class="form-control" style="text-align: left;"
								placeholder="Received Creates Qty" name="recCreatesQty"   type="number" required>

						</div>
						
						<div class="col-md-1"> 
						</div>
						<div class="col-md-2">
							<div class="col1title" align="left">Creates Balance Qty*: </div>
						</div>
						<div class="col-md-3">
						Opening Qty + Issue Qty - Recieved Qty
							<!-- <input id="balQty" class="form-control"
								placeholder="Balance Qty" name="balQty" style="text-align: left;"  type="number" disabled> -->

						</div>
						
					
					 </div>
					<div class="colOuter">
					<div class="col-md-2">
							<div class="col1title" align="left">Vehicle In KM*: </div>
						</div>
						<div class="col-md-3">
							<input id="vehInKm" class="form-control" style="text-align: left;"
								placeholder="Vehicle In KM" name="vehInKm"   type="number" required>

						</div>
						
						<div class="col-md-1"> 
								</div>
						 <div class="col-md-2">
							<div class="col1title" align="left">Remark : </div>
						</div>
						<div class="col-md-3">
							<input id="remark" class="form-control" style="text-align: left;"
								placeholder="Remark" name="remark"   type="text" required>
 
					</div>
						<div class="col-md-1"> 
						</div>
						
						

						</div>
						
						<div class="colOuter">
						<div class="col-md-2">
							<div class="col1title" align="left">Payment Mode*: </div>
						</div>
						<div class="col-md-3">
							<select class="form-control" onchange="selectionCurrency()" data-live-search="true" title="Please Select" 
							name="payMode" id="payMode" required> 
							<option value="1">Check</option> 
							<option value="2">Cash</option>
							
						 </select>

						</div>
						<div class="col-md-1"> 
								</div>
							<div class="col-md-2">
									<div class="col1title" align="left">Check No : </div>
								</div>
								<div class="col-md-3">
								 
									 <input id="checkNo" class="form-control"
										placeholder="Check No" name="checkNo" style="text-align: left;"  type="text" required> 
		
								</div>
								 

						</div>
 				<div id="currencyTable" style="display: none">
					<div id="table-scroll" class="table-scroll">
									<div id="faux-table" width=100% class="faux-table" aria="hidden"></div>
									<div class="table-wrap table-wrap-custbill">
										<table id="table_grid1" width=100% class="main-table small-td">
											<thead>
												<tr class="bgpink">
													<th width="10">Sr no.</th>
													<th width="10">Currency</th>
													<th width="10">Qty</th>
													<th width="10">Total Value</th> 
												</tr>
											</thead>
											<tbody>
											
											<tr>
												 
												 
												</tr>

											</tbody>

										</table>
									</div>
								</div>
					</div>
					<div class="colOuter">
						
						<div class="col-md-1"> 
						</div>
						 
					</div>
						 
					<div class="colOuter">
						<div align="center">
							<input name="submit" class="buttonsaveorder" value="Submit"
								type="submit" align="center">
								<!-- <input type="button" class="buttonsaveorder" value="Cancel" id="cancel" onclick="cancel1()" disabled> -->
						</div>
				 
					</div>
					
					 

				</form>

				 
			</div>
			<!--tabNavigation-->
			<!--<div class="order-btn"><a href="#" class="saveOrder">SAVE ORDER</a></div>-->
			<%-- <div class="order-btn textcenter">
						<a
							href="${pageContext.request.contextPath}/showBillDetailProcess/${billNo}"
							class="buttonsaveorder">VIEW DETAILS</a>
						<!--<input name="" class="buttonsaveorder" value="EXPORT TO EXCEL" type="button">-->
					</div> --%>


		</div>
		<!--rightSidebar-->

	</div>
	<!--fullGrid-->
</div>
<!--rightContainer-->

</div>
<!--wrapper-end-->
<!--easyTabs-->
<!--easyTabs-->
<script src="${pageContext.request.contextPath}/resources/js/main.js"></script>
<!--easyTabs-->


<script>
 

function selectionCurrency() {
//alert("ala");
	var payMode = document.getElementById("payMode").value;
	//alert("payMode" + payMode);
    if(payMode==2)
    	{
    	document.getElementById("checkNo").disabled=true;
    	 
    	$("#currencyTable").show();
    	}
    else
    	{
    	document.getElementById("checkNo").disabled=false;
    	 
    	$("#currencyTable").hide();
    	}
	  
	 

}
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


	</script>

</body>
</html>
