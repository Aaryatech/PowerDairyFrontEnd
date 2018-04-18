

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
	<c:url var="getCustomer" value="/getCustomer" />
	<c:url var="getVehicle" value="/getVehicle" />
	<c:url var="getBatchList" value="/getBatchList" />
	<c:url var="insertItemDetail" value="/insertItemDetail" />
    <c:url var="insertTempBill" value="/insertTempBill" />
	
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
							name="custId" id="custId"  onchange="onCustomerChange(this.value)" required>
							<c:forEach items="${customerList}" var="customerList">
								<option value="${customerList.custId}">${customerList.custName}</option>
							</c:forEach> 
						 </select>

						</div>
						
						<div class="col-md-1">
							 
						</div>

						<div class="col-md-2">
							<div class="col1title" align="left">Select Vehicle *: </div>
						</div>
						<div class="col-md-3">
							<select class="selectpicker" data-live-search="true" title="Select Vehicle" 
							name="vehId" id="vehId" onchange="onVehicleChange(this.value)" required> 
						<c:forEach items="${vehicleList}" var="vehicleList">
								<option value="${vehicleList.vehId}">${vehicleList.vehName}</option>
							</c:forEach> 
						 </select>

						</div>
					 
					</div> 
					
					<div class="colOuter">
						<div class="col-md-2">
							<div class="col1title" align="left">Creates Opening Qty *: </div>
						</div>
						<div class="col-md-3">
							<input id="cratesOpnQty" class="form-control" style="text-align: left;"
								placeholder="Creates Opening Qty" name="cratesOpnQty"   type="number" disabled>

						</div>
						
						<div class="col-md-1">
							 
						</div>

						<div class="col-md-2">
							<div class="col1title" align="left">Crates Issue Qty *: </div>
						</div>
						<div class="col-md-3">
							<input id="cratesIssueQty" class="form-control"
								placeholder="Issue Qty" name="cratesIssueQty" style="text-align: left;"  type="number" min="0" required>

						</div>
					 
					</div> 
					
					<div class="colOuter">
						<div class="col-md-2">
							<div class="col1title" align="left">Vehicle Out KM*: </div>
						</div>
						<div class="col-md-3">
							<input id="vehOutKm" class="form-control"
								placeholder="Vehicle Out KM" name="vehOutKm" style="text-align: left;" type="number" readonly>

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
															name="itemId" id="itemId" onchange="onItemChange(this.value)" > 
															<c:forEach items="${itemList}" var="item">
															<option value="${item.itemId}">${item.itemName}</option>
															</c:forEach>
															 </select> </td>
																		 	 
															
															<td class="col-md-2"><select class="selectpicker" data-live-search="true" title="Please Select" 
							name="batch_no" id="batch_no" required> 
							 
						 </select></td>
																			
																		<td class="col-md-2"><input id="qty" style="text-align: left;" class="form-control"
								placeholder="Qty" name="qty"   type="number"  ></td>
																			
																		  
																		 
	<td ><input type="button" class="btn additem_btn" value="Add Item" onclick="insertItem();"
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
													<th class="col-md-1">Rate</th>   
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
										<input type="hidden" class="form-control" id="totalSumText" name="totalSumText" id="">
									</div>

									<div class="col-md-4">
										<h4 class="col-md-7" style="margin-top: 5px">
											<b>Tax Total:-</b>
										</h4>

										<h4 class="col-md-5" id="taxTotal">00</h4>
										<input type="hidden" class="form-control" id="taxTotalText" name="taxtotalText">
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
								type="button" align="center" onclick="saveTempBill()">
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
function onCustomerChange(custId) {
 
	$.getJSON(
					'${getCustomer}',
					{
						custId : custId, 
						ajax : 'true'

					},
					function(data) { 
						
						document.getElementById("cratesOpnQty").value=data.cratesOpBal;
					
					});

 
	   

}
function onVehicleChange(vehId) {
	 
	$.getJSON(
					'${getVehicle}',
					{
						vehId : vehId, 
						ajax : 'true'

					},
					function(data) { 
						
						document.getElementById("vehOutKm").value=data.vehOpKms;
					
					});

}

function onItemChange(itemId)
{
	$.getJSON('${getBatchList}', {
		itemId : itemId,
		ajax : 'true' 
    }, function(data) {
	    var html;
		var len = data.length;
		for ( var i = 0; i < len; i++) {
			html += '<option value="' + data[i].batchNo +'">'
					+ data[i].batchNo+ '</option>';
		}
		html += '</option>';
		$('#batch_no').html(html);
		$('.selectpicker').selectpicker("refresh");
       });

}
function insertItem()
{
	var itemId = $("#itemId").val();
	var custId=$("#custId").val();
	var qty=$('#qty').val();
	var batchNo = $("#batch_no").val();
	$.getJSON('${insertItemDetail}', {
		
		custId : custId,
		itemId : itemId,
		qty:qty,
		batchNo:batchNo,		
		ajax : 'true',
	},  function(data) { 
 
		var len = data.length;

		$('#table_grid1 td').remove();
         var totamount=0;var tottax=0;var grandtotal=0;
		$.each(data,function(key, item) {

			var tr = $('<tr></tr>');

		  	tr.append($('<td></td>').html(key+1));

		  	tr.append($('<td></td>').html(item.batchNo));

		  	tr.append($('<td></td>').html(item.itemName));

		  	tr.append($('<td></td>').html(item.billQty));
		  	
		  	tr.append($('<td></td>').html(item.rate));
		  	var amount=item.rate*item.billQty;
		  	totamount=totamount+amount;
		  	tr.append($('<td></td>').html(amount));

		  	var taxPer=item.cgstPer+item.sgstPer;
		 
		  	tr.append($('<td></td>').html((taxPer).toFixed(2)));
		  	var taxAmt=(amount*taxPer)/100;
		  	tr.append($('<td></td>').html(taxAmt.toFixed(2)));
		 	tottax=tottax+taxAmt;
		 	
            var total=amount+taxAmt;
            grandtotal=grandtotal+total;
		  	tr.append($('<td></td>').html(total.toFixed(2)));

		 	tr.append($('<td></td>').html("<a href='#' class='action_btn' onclick=editItemDetail("+key+")> <abbr title='edit'> <i class='fa fa-edit  fa-lg' ></i></abbr> </a> <a href='#' class='action_btn'onclick=deleteItemDetail("+key+ ")><abbr title='Delete'><i class='fa fa-trash-o  fa-lg'></i></abbr></a>"));
		  
			$('#table_grid1 tbody').append(tr);
	 }); 
	
		document.getElementById("totalSum").innerHTML=totamount.toFixed(2);
		document.getElementById("taxTotal").innerHTML=tottax.toFixed(2);  
		document.getElementById("grandTotal").innerHTML=grandtotal.toFixed(2);
		document.getElementById("totalSumText").value=totamount.toFixed(2);
		document.getElementById("taxTotalText").value=tottax.toFixed(2);  
		document.getElementById("grandTotalText").value=grandtotal.toFixed(2);
		document.getElementById("qty").value=0;
		$('#batch_no').html("");
		$('.selectpicker').selectpicker("refresh");
		

	});
	
}
function saveTempBill()
{
	var custId=$("#custId").val();alert(custId)
	var vehId=$("#vehId").val();alert(vehId)
	var cratesIssueQty=$("#cratesIssueQty").val();alert(cratesIssueQty)
	var cratesOpnQty=$("#cratesOpnQty").val();alert(cratesOpnQty)
	
	var total=0; alert(total)
	$.getJSON('${insertTempBill}', {
		
		custId : custId,
		vehId:vehId,
		total:total,
		cratesOpnQty:cratesOpnQty,
		cratesIssueQty:cratesIssueQty,
		
		ajax : 'true',
	},  function(data) { 
 
		var len = data.length;

		$('#table_grid1 td').remove();
		$.each(data,function(key, item) {

			
	 }); 
	
		document.getElementById("totalSum").innerHTML=0;
		document.getElementById("taxTotal").innerHTML=0;  
		document.getElementById("grandTotal").innerHTML=0;
		document.getElementById("totalSumText").value=0;
		document.getElementById("taxTotalText").value=0;  
		document.getElementById("grandTotalText").value=0;
		document.getElementById("qty").value=0;
		$('#batch_no').html("");
		$('.selectpicker').selectpicker("refresh");
		

	});
	
}
function cancel1() {

    //alert("cancel");

	document.getElementById("batch_no").value="";  
	

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
