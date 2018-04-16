

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
								<h2 class="pageTitle">Bill</h2> 
								
								 
						</div>
						
						 <div class="col1title" align="right"> 
						<a href="${pageContext.request.contextPath}/showAllTempAndSettleBill"><input type="button" value="All Bills" class="btn btn-info">
										</a>
					</div>

					
						<!-- <div class="col-md -3">
							
								<div class="col1title" align="left"><h3>Bill</h3></div>
								 
						</div> -->
						
					<div class="colOuter">
						<div class="col-md-2">
							<div class="col1title" align="left">Select Customer *: </div>
						</div>
						<div class="col-md-3">
							<select class="selectpicker" data-live-search="true" title="Please Select" 
							name="custId" id="custId" required> 
							<option value="1">Mahesh</option>
							<option value="2">Sachin</option> 
						 </select>

						</div>
						
						<div class="col-md-1">
							 
						</div>

						<div class="col-md-2">
							<div class="col1title" align="left">Select Vehicle *: </div>
						</div>
						<div class="col-md-3">
							<select class="selectpicker" data-live-search="true" title="Please Select" 
							name="vehId" id="vehId" required> 
							<option value="1">MH-15-1889</option>
							<option value="2">MH-15-1772</option> 
						 </select>

						</div>
					 
					</div> 
					
					<div class="colOuter">
						<div class="col-md-2">
							<div class="col1title" align="left">Creates Opening Qty *: </div>
						</div>
						<div class="col-md-3">
							<input id="opnQty" class="form-control" style="text-align: left;"
								placeholder="Creates Opening Qty" name="opnQty"   type="number" disabled>

						</div>
						
						<div class="col-md-1">
							 
						</div>

						<div class="col-md-2">
							<div class="col1title" align="left">Issue Qty *: </div>
						</div>
						<div class="col-md-3">
							<input id="issueQty" class="form-control"
								placeholder="Issue Qty" name="issueQty" style="text-align: left;"  type="number" required>

						</div>
					 
					</div> 
					
					<div class="colOuter">
						<div class="col-md-2">
							<div class="col1title" align="left">Vehicle Out KM*: </div>
						</div>
						<div class="col-md-3">
							<input id="opnQty" class="form-control"
								placeholder="Vehicle Out KM" name="opnQty" style="text-align: left;" type="number" disabled>

						</div>
						
						<div class="col-md-1">
							 <input type="button" class="btn additem_btn" value="Adjust KM" onclick="addItem();"
												id="b2"/>
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
																		<td class="col-md-2">Batch No</td>
																		<td class="col-md-2">Qty</td>
																		 
																	</tr>
																	<tr>
																		 
																		<td class="col-md-2"><select class="selectpicker" data-live-search="true" title="Please Select" 
															name="itemId" id="itemId"  > 
																<option value="1">Milk</option>
																<option value="2">Dahi</option> 
															 </select> <input name="item_name1" id="item_name1"
																			type="hidden" value="" /></td>
																		 	 
															
															<td class="col-md-2"><select class="selectpicker" data-live-search="true" title="Please Select" 
							name="batchId" id="batchId" required> 
							 
						 </select></td>
																			
																		<td class="col-md-2"><input id="qty" style="text-align: left;" class="form-control"
								placeholder="Qty" name="qty"   type="number"  ></td>
																			
																		  
																		 
																		 <td ><input type="button" class="btn additem_btn" value="Add Item" onclick="addItem();"
												id="b1"/> </td>
												
												
												</tr>
												 
																</table>
															</td>
														</tr>

													</table>
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
					  <div class="row">
									<div class="col-md-4">
										<h4 class="col-md-7">
											<b>Amount:-</b>
										</h4>
										<h4 class="col-md-5" id="totalSum">00</h4>
										<input type="hidden" class="form-control" id="totalSumText" name="totalSumText">
									</div>

									<div class="col-md-4">
										<h4 class="col-md-7" style="margin-top: 5px">
											<b>Tax Total:-</b>
										</h4>

										<h4 class="col-md-5" id="taxtotal">00</h4>
										<input type="hidden" class="form-control" id="taxtotalText" name="taxtotalText">
									</div>

									<div class="col-md-4">
										<h4 class="col-md-7" style="margin-top: 5px">
											<b>Grand Total:-</b>
										</h4>

										<h4 class="col-md-5" id="grandTotal">00</h4>
										<input type="hidden" class="form-control" id="grandTotalText" name="grandTotalText">
									</div>

									<div class="clearfix"></div>
									 
								</div>
						 
					<div class="colOuter">
						<div align="center">
							<input name="submit" class="buttonsaveorder" value="Submit"
								type="submit" align="center">
								<!-- <input type="button" class="buttonsaveorder" value="Cancel" id="cancel" onclick="cancel1()" disabled> -->
						</div>
				 
					</div>
					
					<%-- <div id="table-scroll" class="table-scroll">
					<div id="faux-table" class="faux-table" aria="hidden"></div>
					<div class="table-wrap">
						<table id="table_grid" class="main-table">

							<thead>
								<tr class="bgpink">
								
									<th class="col-sm-1">Sr No</th>
									<th class="col-md-1">Name</th> 
									<th class="col-md-1">Address</th>
									<th class="col-md-1">City</th> 
									<th class="col-md-1">Mobile</th>
									<th class="col-md-1">Email</th>
									<th class="col-md-1">Action</th>
								</tr>
							</thead>
							<tbody>

								<c:forEach items="${supplierList}" var="supplierList"
									varStatus="count">
									<tr>
										 <td class="col-sm-1"><c:out value="${count.index+1}" /></td>
										<td class="col-md-1"><c:out
												value="${supplierList.suppName}" /></td>
										<td class="col-md-1"><c:out
												value="${supplierList.suppAddr}" /></td>
										<td class="col-md-1"><c:out
												value="${supplierList.suppCity}" /></td>
										<td class="col-md-1"><c:out
												value="${supplierList.mobileNo}" /></td>
										<td class="col-md-1"><c:out
												value="${supplierList.email}" /></td>
										<td class="col-md-1"><div >
												<abbr title='Edit'><i onclick="edit(${supplierList.suppId})" class='fa fa-edit'></i> </abbr>
						<a href="${pageContext.request.contextPath}/deleteSupplier/${supplierList.suppId}" onClick="return confirm('Are you sure want to delete this record');"   >
						<abbr title='Delete'><i  class='fa fa-trash'></i></abbr></a>
												 
											</div></td>
									</tr>
								</c:forEach>
						</table>

					</div>
				</div> --%>

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
