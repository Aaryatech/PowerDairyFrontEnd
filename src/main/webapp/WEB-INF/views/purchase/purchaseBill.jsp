

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

 

</head>
<body>
 --%>

<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
<c:url var="addItemInPurchaseBill" value="/addItemInPurchaseBill"></c:url>
<c:url var="editItemInPurchaseBill" value="/editItemInPurchaseBill"></c:url>
<c:url var="deleteItemInPurchaseBill" value="/deleteItemInPurchaseBill"></c:url>
<c:url var="getCurrentStockByItemId" value="/getCurrentStockByItemId"></c:url>


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
			$("#datepicker").datepicker({
				dateFormat : 'dd-mm-yy'
			});
		});
		$(function() {
			$("#datepicker1").datepicker({
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
					action="${pageContext.request.contextPath}/insertPurchaseBill">
				 
					
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
					<div class="row">
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
									 
								</div>
						
					<div class="colOuter">
						<div class="col-md-2">
							<div class="col1title" align="left">PO No*: </div>
						</div>
						<div class="col-md-3">
							<input id="invoiceNo" class="form-control"
								style="text-align: left;" placeholder="PO No" name="invoiceNo" type="number" required>
							 

						</div>
						<div class="col-md-1">
							 
						</div>

						<div class="col-md-2">
							<div class="col1title" align="left">PO Date*: </div>
						</div>
						<div class="col-md-3">
							<input id="datepicker"  placeholder="PO Date" class="texboxitemcode texboxcal"
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
							 <input  type="hidden" data-live-search="true"  
															name="index" id="index"  >
						</div>
						
						<div class="col-md-2">
							<div class="col1title" align="left">Remark: </div>
						</div>
						<div class="col-md-3">
							<input id="remark" class="form-control"
								style="text-align: left;" placeholder="Remark" name="remark" type="text"  >
								
							<input id="stockDate" value="${stockDate}" name="stockDate" type="hidden"  >
							 

						</div>
 
					</div> 
					
					 <div class="colOuter"> 
					 <input id="itemQty"  
								  name="itemQty" value="0" type="hidden"  >
					 
					<input id="itemTotalStock"  
								  name="itemTotalStock" value="0" type="hidden"  >
					<input id="itemStock"  
								  name="itemStock" value="0" type="hidden"  >
								  
								  
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
																		 
																		<td class="col-md-2"><select class="selectpicker" data-live-search="true" onchange="getCurrentStockByItem()" title="Please Select" 
															name="itemId" id="itemId"  > 
														 
																<c:forEach items="${itemList}" var="itemList">
																	<option value="${itemList.itemId}">${itemList.itemName} &nbsp;&nbsp; ${itemList.itemCode}</option>
																
																</c:forEach>
															 </select> <input name="item_name1" id="item_name1"
																			type="hidden" value="" /></td>
																			
																			<td class="col-md-2"><input id="datepicker1"  placeholder="Manufacture Date" class="texboxitemcode texboxcal"
															name="manufactureDate" type="text"  ></td>
															
															<td><input id="batchNo" style="text-align: left;" class="form-control"
								placeholder="Batch No" name="batchNo"   type="text"  ></td>
																			
																		<td><input id="qty" style="text-align: left;" onchange="addQtyInStock();" class="form-control"
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
																		<td  >Stock Calculation</td>
																		
																		<td id="stock">Stock </td>
																			
																		<td id="enterQty">Qty</td>
								
																		<td id="totalStock">Total </td>
								
																		<td> </td>
																		
																		 
																		 <td > </td>
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
													<th class="col-md-1">MGF Date</th> 
													<th class="col-md-1">Qty</th>   
													<th class="col-md-1">Rate</th> 
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
						
						<c:choose>
							<c:when test="${today==stockDate }">
							<input name="submit" class="buttonsaveorder" value="Submit"
								type="submit" align="center">
							</c:when>
							<c:otherwise>
							
							<input name="submit" class="buttonsaveorder" value="Submit"
								type="submit" align="center" disabled>
								Please Complete The Day End Process.....
							</c:otherwise>
						</c:choose>
							 
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
function addItem( ) {
 
	var itemId = $("#itemId").val(); 
	var itemName = $("#itemId option:selected").text();  
	var batchNo = $("#batchNo").val(); 
	var qty = $("#qty").val();
	var shortNo = $("#shortNo").val();
	var extraNo = $("#extraNo").val();
	var leakageQty = $("#leakageQty").val();
	var mfgDate = $("#datepicker1").val();
	var index = $("#index").val(); 
	var valid=0;
	    
	if (itemId=="" || isNaN(itemId)) {

		 
		alert("Select Item ");
		valid=1;

	}
	if (batchNo==""  ) {

		 
		alert("Enter Batch No ");
		valid=1;

	}
	if (qty=="" || isNaN(qty) || qty<1) {

		 
		alert("Enter Recieved Qty");
		valid=1;

	}
	 if (shortNo=="" || isNaN(shortNo) ) {

		 
		alert("Enter Short No ");
		valid=1;

	}
	if (extraNo=="" || isNaN(extraNo)) {

		 
		alert("Enter Extra No ");
		valid=1;

	}
	 
	if (leakageQty=="" || isNaN(leakageQty)) {

		 
		alert("Enter Leakage Qty");
		valid=1;

	}
	
	 if(valid==0)
		 {
		 
		 
	
	$('#loader').show();

	$
			.getJSON(
					'${addItemInPurchaseBill}',

					{
						 
						 
						itemId : itemId,
						itemName : itemName, 
						batchNo : batchNo,
						qty : qty,
						shortNo : shortNo,
						extraNo : extraNo,
						leakageQty : leakageQty,
						mfgDate : mfgDate,
						index : index,
						ajax : 'true'

					},
					function(data) { 
						
						$('#table_grid1 td').remove();
						if (data == "") {
							alert("No records found !!");

						}
						var grandTotal=0;
						$.each(
								data,
								function(key, itemList) {
									
									 
									var tr = $('<tr></tr>');
										tr.append($('<td class="col-sm-1"></td>').html(key+1));
										tr.append($('<td class="col-md-1"></td>').html(itemList.batchNo));
									  	tr.append($('<td class="col-md-2"></td>').html(itemList.itemName));
									  	tr.append($('<td class="col-md-1" ></td>').html(itemList.mfgDate));
									  	tr.append($('<td class="col-md-1" ></td>').html(itemList.itemQty));
									  	tr.append($('<td class="col-md-1" ></td>').html(itemList.rate));   
									  	 tr.append($('<td class="col-md-1" ></td>').html((itemList.itemQty*itemList.rate).toFixed(2))); 
									  	grandTotal = parseFloat(grandTotal) + parseFloat((itemList.itemQty*itemList.rate).toFixed(2));
									  	tr.append($('<td></td>').html('<span class="glyphicon glyphicon-edit" onclick="edit('+key+');"></span> <span class="glyphicon glyphicon-remove" onclick="deleteItem('+key+');""></span>')); 
										$('#table_grid1 tbody').append(tr);
										
										document.getElementById("itemId").value="";
										$('.selectpicker').selectpicker('refresh');
										document.getElementById("index").value="";
										document.getElementById("batchNo").value="";  
										document.getElementById("qty").value="";
										document.getElementById("shortNo").value="";
										document.getElementById("extraNo").value="";
										document.getElementById("leakageQty").value="";
										document.getElementById("datepicker1").value="";
										 document.getElementById("itemQty").value=0; 
										  $("#enterQty").html(0);

								})
						 $("#grandTotal").html(grandTotal);
								 
					});

 
		 }

}

function edit(index) {

    //alert("cancel");
	document.getElementById("index").value=index;
	$('#loader').show();

	$
			.getJSON(
					'${editItemInPurchaseBill}',

					{
						 
						 
						index : index, 
						ajax : 'true'

					},
					function(data) { 
						
						  
										document.getElementById("itemId").value=data.itemId;
										$('.selectpicker').selectpicker('refresh');
										document.getElementById("batchNo").value=data.batchNo;  
										document.getElementById("qty").value=data.itemQty;
										document.getElementById("shortNo").value=data.shortNo;
										document.getElementById("extraNo").value=data.extraNo;
										document.getElementById("leakageQty").value=data.poLeakageQty;
										document.getElementById("datepicker1").value=data.mfgDate;

							  
						 
					});

}

function deleteItem(index) {

   
	$('#loader').show();

	$
			.getJSON(
					'${deleteItemInPurchaseBill}',

					{
						 
						 
						index : index, 
						ajax : 'true'

					},
					function(data) { 
						
						  
						$('#table_grid1 td').remove();
						if (data == "") {
							alert("No records found !!");

						}
						var grandTotal=0;
						$.each(
								data,
								function(key, itemList) {
									
									 
									var tr = $('<tr></tr>');
										tr.append($('<td class="col-sm-1"></td>').html(key+1));
										tr.append($('<td class="col-md-1"></td>').html(itemList.batchNo));
									  	tr.append($('<td class="col-md-2"></td>').html(itemList.itemName));
									  	tr.append($('<td class="col-md-1" ></td>').html(itemList.mfgDate));
									  	tr.append($('<td class="col-md-1" ></td>').html(itemList.itemQty));
									  	tr.append($('<td class="col-md-1" ></td>').html(itemList.rate));   
									  	 tr.append($('<td class="col-md-1" ></td>').html((itemList.itemQty*itemList.rate).toFixed(2))); 
									 	grandTotal = parseFloat(grandTotal) + parseFloat((itemList.itemQty*itemList.rate).toFixed(2));
									  	tr.append($('<td></td>').html('<span class="glyphicon glyphicon-edit" onclick="edit('+key+');"></span> <span class="glyphicon glyphicon-remove" onclick="deleteItem('+key+');""></span>')); 
										$('#table_grid1 tbody').append(tr);
										
										document.getElementById("itemId").value="";
										$('.selectpicker').selectpicker('refresh');
										document.getElementById("index").value="";
										document.getElementById("batchNo").value="";  
										document.getElementById("qty").value="";
										document.getElementById("shortNo").value="";
										document.getElementById("extraNo").value="";
										document.getElementById("leakageQty").value="";
										document.getElementById("datepicker1").value="";

								})
						

								 $("#grandTotal").html(grandTotal);
						 
					});

}

function getCurrentStockByItem() {

	var itemId = $("#itemId").val();
	var stockDate = $("#stockDate").val();
	var itemQty = parseInt($("#itemQty").val());
	
	$('#loader').show();

	$
			.getJSON(
					'${getCurrentStockByItemId}',

					{
						 
						 
						itemId : itemId, 
						stockDate : stockDate,
						ajax : 'true'

					},
					function(data) { 
						
						  var total = parseInt(data.openingStock+data.totalPurchase-data.totalSale+data.returnQty-data.hubReturnQty);
						  var totalStock = total+itemQty;
						  document.getElementById("itemQty").value=itemQty;  
							document.getElementById("itemStock").value=total;
							document.getElementById("itemTotalStock").value=totalStock;
							document.getElementById("itemQty").value=itemQty;
							
							  $("#stock").html(total);
								$("#enterQty").html(itemQty);  
								$("#totalStock").html(totalStock);
								
						 
					});

}

  function addQtyInStock() {

	 
	var qty = parseInt($("#qty").val()); 
	var itemStock = parseInt($("#itemStock").val());
	var itemTotalStock = $("#itemTotalStock").val();
	 
							 
								$("#enterQty").html(qty);  
								var totalStock =  parseInt(itemStock+qty);
								$("#totalStock").html(totalStock);
								document.getElementById("itemQty").value=qty;
			 
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
