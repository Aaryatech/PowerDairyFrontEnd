

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

 

</head>
<body>
 --%>

<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
<c:url var="editFrSupplier" value="/editFrSupplier"></c:url>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.12.1/css/bootstrap-select.css" />
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.12.1/js/bootstrap-select.js"></script>
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

					
						<div class="order-left"> 
								<h2 class="pageTitle"> Purchase Bill</h2> 
								
								<%--  <div class="col1title" align="right"> 
						<a href="${pageContext.request.contextPath}/purchaseHistory"><input type="button" value="Purchase History" class="btn btn-info">
										</a>
					</div> --%>
								 
						</div>
						
						 <div class="col1title" align="right"> 
						<a href="${pageContext.request.contextPath}/purchaseHistory"><input type="button" value="Purchase History" class="btn btn-info">
										</a>
					</div>
					
					<div class="colOuter">
						 
					</div> 
						
					<div class="colOuter">
						<div class="col-md-2">
							<div class="col1title" align="left">Invoice No*: </div>
						</div>
						<div class="col-md-3">
							<input id="invoiceNo" class="form-control"
								style="text-align: left;" placeholder="Invoice No" name="invoiceNo" type="text" required>
							 

						</div>
						<div class="col-md-1">
							 
						</div>

						<div class="col-md-2">
							<div class="col1title" align="left">Invoice Date*: </div>
						</div>
						<div class="col-md-3">
							<input id="datepicker"  placeholder="Invoice Date" class="texboxitemcode texboxcal"
															name="invoiceDate" type="text" required>

						</div>
					 
					</div> 
					<div class="colOuter">
						<div class="col-md-2">
							<div class="col1title" align="left">Creates In Qty*: </div>
						</div>
						<div class="col-md-3">
							<input id="cratesReceivedQty" class="form-control"
								style="text-align: left;" placeholder="Creates In Qty" name="cratesReceivedQty" type="number" required>
							 

						</div>
						<div class="col-md-1">
							 
						</div>
						
						<div class="col-md-2">
							<div class="col1title" align="left">Remark: </div>
						</div>
						<div class="col-md-3">
							<input id="remark" class="form-control"
								style="text-align: left;" placeholder="Remark" name="remark" type="text"  >
							 

						</div>
 
					</div> 
					
					 <div class="colOuter"> 
					</div>
					 <div class="colOuter"> 
					</div>
					
					<div>
												<div class="shInnerwidth">
													<table width="100%" border="0" cellspacing="0"
														cellpadding="0" class="table">
														<tr>
															<td align="center" valign="middle" style="padding: 0px;">
																<table width="100%" border="0" cellspacing="0"
																	cellpadding="0">
																	<tr class="bgpink"> 
																		<td class="col-md-2">Item Name</td>
																		<td class="col-md-2" >Manufacture Date</td>
																		<td>Batch No</td>
																		<td>Qty</td>
																		<td>Short No</td>
																		<td>Extra No</td>
																		<td>Leakage No</td> 
																	</tr>
																	<tr>
																		 
																		<td class="col-md-2"><select class="selectpicker" data-live-search="true" title="Please Select" 
															name="itemId" id="itemId"  > 
																<option value="1">Milk</option>
																<option value="2">Dahi</option> 
															 </select> <input name="item_name1" id="item_name1"
																			type="hidden" value="" /></td>
																			
																			<td class="col-md-2"><input id="datepicker"  placeholder="Manufacture Date" class="texboxitemcode texboxcal"
															name="manufactureDate" type="text" required></td>
															
															<td><input id="batchNo" style="text-align: left;" class="form-control"
								placeholder="Batch No" name="batchNo"   type="text"  ></td>
																			
																		<td><input id="qty" style="text-align: left;" class="form-control"
								placeholder="Qty" name="qty"   type="number"  ></td>
																			
																		<td><input id="shortNo" style="text-align: left;" class="form-control"
								placeholder="Short No" name="shortNo"   type="number"  ></td>
								
																		<td><input id="extraNo" style="text-align: left;" class="form-control"
								placeholder="Extra No" name="extraNo"   type="number"  ></td>
								
																		<td><input id="leakageQty" style="text-align: left;" class="form-control"
								placeholder="Leakage Qty " name="leakageQty" type="number"  ></td>
																		
																		 
																		 <td ><input type="button" class="btn additem_btn" value="Add Item" onclick="addItem();"
												id="b1"/> </td>
												
												
												</tr>
												<tr>
												<td class="col-md-2"> </td>
															
															<td> </td>
																			
																		<td>Stock </td>
																			
																		<td>Qty</td>
								
																		<td>Total </td>
								
																		<td> </td>
																		
																		 
																		 <td > </td>
																	</tr>
																</table>
															</td>
														</tr>

													</table>
												</div>
											</div>
					 
					<!-- <div class="colOuter">
						 
						<div class="col-md-2">
							<div class="col1title" align="left">Select Item : </div>
						</div>
						<div class="col-md-3">
							<select class="selectpicker" data-live-search="true" title="Please Select" 
							name="itemId" id="itemId"  > 
							<option value="1">Milk</option>
							<option value="2">Dahi</option> 
						 </select>

						</div>
						<div class="col-md-1"> </div>
						<div class="col-md-2">
							<div class="col1title" align="left">Batch No : </div>
						</div>
						<div class="col-md-3">
							<input id="batchNo" style="text-align: left;" class="form-control"
								placeholder="Batch No" name="batchNo"   type="text"  >

						</div>
				 
					</div> -->
					
					<!-- <div class="colOuter">
						<div class="col-md-2">
							<div class="col1title" align="left">Qty: </div>
						</div>
						<div class="col-md-3">
							<input id="qty" style="text-align: left;" class="form-control"
								placeholder="Qty" name="qty"   type="number"  >

						</div>
						<div class="col-md-1">
							 
						</div>

						<div class="col-md-2">
							<div class="col1title" align="left">Short No: </div>
						</div>
						<div class="col-md-3">
							<input id="shortNo" style="text-align: left;" class="form-control"
								placeholder="Short No" name="shortNo"   type="number"  >

						</div>
				 
					</div> -->
					
					<!-- <div class="colOuter">
						<div class="col-md-2">
							<div class="col1title" align="left"> Extra No: </div>
						</div>
						<div class="col-md-3">
							<input id="extraNo" style="text-align: left;" class="form-control"
								placeholder="Extra No" name="extraNo"   type="number"  >

						</div>
						
						<div class="col-md-1">
							 
						</div>

						<div class="col-md-2">
							<div class="col1title" align="left"> Leakage Qty : </div>
						</div>
						<div class="col-md-3">
							<input id="leakageQty" style="text-align: left;" class="form-control"
								placeholder="Leakage Qty " name="leakageQty" type="number"  >

						</div>
					  
					</div> -->
					
					<!-- <div class="colOuter">
						<div class="col-md-2">
							<div class="col1title" align="left"> Manufacture Date: </div>
						</div>
						<div class="col-md-3">
							<input id="datepicker"  placeholder="Manufacture Date" class="texboxitemcode texboxcal"
															name="manufactureDate" type="text" required>

						</div>
						
						<div class="col-md-1"> 
						</div>
 
					</div> -->
					
				<!-- 	<div class="row">
									<div class="col-md-4">
										 
									</div>

									<div class="col-md-4">
										 
									</div>

									<div class="col-md-4">
										<h4 class="col-md-7" style="margin-top: 5px">
											<b>Grand Total:-</b>
										</h4>

										<h4 class="col-md-5" id="grandTotal">00</h4>
										<input type="hidden" class="form-control" id="grandTotalText" name="grandTotalText">
									</div>

									<div class="clearfix"></div>
									 
								</div> -->
					 
					<!--  <input type="button" class="buttonsaveorder" value="Add Item" id="addItem" onclick="addItem()"  > -->
					 
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
													<th class="col-md-1">Qty</th>   
													<th class="col-md-1">Amount</th>
													<th class="col-md-1">Tax%</th>
													<th class="col-md-1">Tax Amt</th>
													<th class="col-md-1">Total</th>
													<th class="col-md-1">Action</th>
												</tr>
											</thead>
											<tbody>

											</tbody>

										</table>
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
function edit(suppId) {
 
	  
	$('#loader').show();

	$
			.getJSON(
					'${editFrSupplier}',

					{
						 
						suppId : suppId, 
						ajax : 'true'

					},
					function(data) { 
						
						document.getElementById("suppId").value=data.suppId;
						document.getElementById("suppName").value=data.suppName;  
						document.getElementById("suppAdd").value=data.suppAddr;
						document.getElementById("city").value=data.suppCity;
						document.getElementById("mob").value=data.mobileNo;
						document.getElementById("email").value=data.email;
						document.getElementById("gstnNo").value=data.gstnNo;
						document.getElementById("panNo").value=data.panNo;
						document.getElementById("liceNo").value=data.suppFdaLic;
						document.getElementById("creditDays").value=data.suppCreditDays;
						document.getElementById("isSameState").value=data.isSameState; 
						document.getElementById("cancel").disabled=false;
					});

 
	   

}

function cancel1() {

    //alert("cancel");
	document.getElementById("suppId").value="";
	document.getElementById("suppName").value="";  
	document.getElementById("suppAdd").value="";
	document.getElementById("city").value="";
	document.getElementById("mob").value="";
	document.getElementById("email").value="";
	document.getElementById("gstnNo").value="";
	document.getElementById("panNo").value="";
	document.getElementById("liceNo").value="";
	document.getElementById("creditDays").value="";
	document.getElementById("isSameState").value=""; 
	document.getElementById("cancel").disabled=false;

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
