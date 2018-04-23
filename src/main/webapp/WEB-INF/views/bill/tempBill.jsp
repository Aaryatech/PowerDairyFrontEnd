

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

 

</head>
<body>


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
<c:url var="checkBalance" value="/checkPoBalance" />
<c:url var="editItemInSaleBill" value="/editItemInSaleBill"></c:url>
<c:url var="deleteItemInSaleBill" value="/deleteItemInSaleBill"></c:url>
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
					 <input  type="hidden" data-live-search="true"  
															name="index" id="index"  >
					 <input  type="hidden" data-live-search="true"  
															name="cratesCap" id="cratesCap"  >
					 <input  type="hidden" data-live-search="true"  
															name="custCap" id="custCap"  >	
					 <input  type="hidden" data-live-search="true"  
															name="crateStock" id="crateStock" value="${crateStock}" >
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
								placeholder="Issue Qty" name="cratesIssueQty" style="text-align: left;"  type="number" min="0" onchange="checkCratesStock(this.value)" required>

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
							<a href="${pageContext.request.contextPath}/adjustKm"> <input type="button" class="btn additem_btn" value="Adjust KM"  
												id="adjustKm"/></a>
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
																			
																		<td class="col-md-2"><input id="qty" style="text-align: center;" class="form-control" min="0"
								placeholder="Qty" name="qty"   type="number" value="0" onchange="checkBalance();" ></td>
																			
																		  
																		 
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
									<div class="table-wrap table-wrap-custbill">
										<table id="table_grid1" class="main-table small-td" border="1">
											<thead>
												<tr class="bgpink">
													<th class="col-sm-1" style="text-align:center;">Sr no.</th>
													<th class="col-md-1" style="text-align:center;">Batch No</th>
													<th class="col-md-2" style="text-align:center;">Item Name</th>
													<th class="col-md-1" style="text-align:center;">Qty</th>   
													<th class="col-md-1" style="text-align:center;">Rate</th>   
													<th class="col-md-1" style="text-align:center;">Amount</th>
													<th class="col-md-1" style="text-align:center;">Tax%</th>
													<th class="col-md-1" style="text-align:center;">Tax Amt</th>
													<th class="col-md-1" style="text-align:center;">Total</th>
													<th class="col-md-1" style="text-align:center;">Action</th>
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
						
						<c:choose>
							<c:when test="${today==stockDate}">
							<input name="submit" class="buttonsaveorder" value="Submit"
								type="button" align="center" onclick="saveTempBill()">
							</c:when>
							<c:otherwise>
							
							<input name="submit" class="buttonsaveorder" value="Submit"
								type="submit" align="center" disabled>
								Please Complete The Day End Process.....
							</c:otherwise>
						</c:choose>
						</div>
				 
					</div>
						<div align="center" id="loader" style="display: none">

					<span>
						<h4>
							<font color="#343690">Saving Your Bill,please wait...</font>
						</h4>
					</span> <span class="l-1"></span> <span class="l-2"></span> <span
						class="l-3"></span> <span class="l-4"></span> <span class="l-5"></span>
					<span class="l-6"></span>
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
						document.getElementById("cratesCap").value=data.cratesCap;
						document.getElementById("custCap").value=data.custCap;
						document.getElementById("vehId").value=data.vehId;
						$('.selectpicker').selectpicker("refresh");
						onVehicleChange(data.vehId);
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
						
						if(data.inKms==0)
							{
						document.getElementById("vehOutKm").value=data.vehOpKms;
							}
						else{
							document.getElementById("vehOutKm").value=data.inKms;
						}
					});

}

function onItemChange(itemId)
{
	//var firstBatch;
	$.getJSON('${getBatchList}', {
		itemId : itemId,
		ajax : 'true' 
    }, function(data) {
	    var html;
		var len = data.length;
		for ( var i = 0; i < len; i++) {
			if(i==0)
				{
			html += '<option value="'+ data[i].poDetailId +'"selected>'
					+ data[i].batchNo+ '(Avail.Qty:'+ data[i].balance+')'+'</option>';
				}
			else{
			html += '<option value="'+ data[i].poDetailId +'">'
					+ data[i].batchNo +'(Avail.Qty:'+ data[i].balance+')'+'</option>';
			}
		}
		html += '</option>';
		$('#batch_no').html(html);
		$('.selectpicker').selectpicker("refresh");
       });
	//alert(firstBatch)
	//document.getElementById("batch_no").selectedIndex=2;
	//$('.selectpicker').selectpicker("refresh");
}
function onItemChangeEdit(itemId,poDetailId)
{
	
	$.getJSON('${getBatchList}', {
		itemId : itemId,
		ajax : 'true' 
    }, function(data) {
	    var html;
		var len = data.length;
		for ( var i = 0; i < len; i++) {
			
			if(poDetailId==data[i].poDetailId)
				{
			html += '<option value="'+ data[i].poDetailId +'"selected>'
					+ data[i].batchNo +'(Avail.Qty:'+ data[i].balance+')'+'</option>';
				}
			else
				{
				html += '<option value="'+ data[i].poDetailId +'">'
				+ data[i].batchNo +'(Avail.Qty:'+ data[i].balance+')'+'</option>';
				}
		}
		html += '</option>';
		$('#batch_no').html(html);
		$('.selectpicker').selectpicker("refresh");
       });
	//alert(firstBatch)
	//document.getElementById("batch_no").selectedIndex=2;
	//$('.selectpicker').selectpicker("refresh");
}
function insertItem()
{
	var isValid=validation();
	if(isValid){ 
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

		  	tr.append($('<td style="text-align:center;"></td>').html(item.billQty));
		  	
		  	var taxPer=(item.cgstPer+item.sgstPer);

		  	var baseRate=(item.rate*100)/(100+taxPer);
		  	
		  	var taxableAmt=(baseRate*item.billQty);
		  	
		  	var cgstRs=(taxableAmt*item.cgstPer)/100;
			var sgstRs=(taxableAmt*item.sgstPer)/100;
			
			var totalTax=cgstRs+sgstRs;
			
		  	totamount=totamount+taxableAmt;
		  	tr.append($('<td style="text-align:right;"></td>').html((baseRate).toFixed(2)));

		  	tr.append($('<td style="text-align:right;"></td>').html((taxableAmt).toFixed(2)));

		 
		  	tr.append($('<td style="text-align:right;"></td>').html((taxPer).toFixed(2)));
		  
		  	tr.append($('<td style="text-align:right;"></td>').html((totalTax).toFixed(2)));
		 	tottax=tottax+totalTax;
		 	
           var total=taxableAmt+totalTax;
           grandtotal=grandtotal+total;
		  	tr.append($('<td style="text-align:right;"></td>').html((total).toFixed(2)));
//<a href='#' class='action_btn' onclick=editItemDetail("+key+")> <abbr title='edit'> <i class='fa fa-edit  fa-lg' ></i></abbr> </a>
		 	tr.append($('<td></td>').html(" <a href='#' class='action_btn'onclick=deleteItemDetail("+key+ ")><abbr title='Delete'><i class='fa fa-trash-o  fa-lg'></i></abbr></a>"));
		  
			$('#table_grid1 tbody').append(tr);
	 }); 
	
		document.getElementById("totalSum").innerHTML=totamount.toFixed(2);
		document.getElementById("taxTotal").innerHTML=tottax.toFixed(2);  
		document.getElementById("grandTotal").innerHTML=grandtotal.toFixed(2);
		document.getElementById("totalSumText").value=totamount.toFixed(2);
		document.getElementById("taxTotalText").value=tottax.toFixed(2);  
		document.getElementById("grandTotalText").value=grandtotal.toFixed(2);
		document.getElementById("qty").value=0;
		document.getElementById("itemId").value="";
		$('#batch_no').html("");
		$('.selectpicker').selectpicker("refresh");
		

	});
	 } 
	
}

function editItemDetail(key)
{
	    //alert("cancel");
		document.getElementById("index").value=key;

		$.getJSON(
						'${editItemInSaleBill}',
						{ 
							index : key, 
							ajax : 'true'

						},
						function(data) { 
							 
											document.getElementById("itemId").value=data.itemId;
										
											onItemChangeEdit(data.itemId,data.poDetailId);
											$('.selectpicker').selectpicker('refresh');
											//document.getElementById("batch_no").value=data.poDetailId; 
											//$('.selectpicker').selectpicker('refresh');
											document.getElementById("qty").value=data.billQty;
										/* 	document.getElementById("shortNo").value=data.shortNo;
											document.getElementById("extraNo").value=data.extraNo;
											document.getElementById("leakageQty").value=data.poLeakageQty;
											document.getElementById("datepicker1").value=data.mfgDate; */
 
						});

	
}
function deleteItemDetail(index) {

	$.getJSON(
					'${deleteItemInSaleBill}',
					{
						index : index, 
						ajax : 'true'

					},
					function(data) { 
						
						$('#table_grid1 td').remove();
				         var totamount=0;var tottax=0;var grandtotal=0;
						$.each(data,function(key, item) { 

							var tr = $('<tr></tr>');

						  	tr.append($('<td></td>').html(key+1));

						  	tr.append($('<td></td>').html(item.batchNo));

						  	tr.append($('<td></td>').html(item.itemName));

						  	tr.append($('<td style="text-align:center;"></td>').html(item.billQty));
						  	
						  	var taxPer=(item.cgstPer+item.sgstPer);

						  	var baseRate=(item.rate*100)/(100+taxPer);
						  	
						  	var taxableAmt=(baseRate*item.billQty);
						  	
						  	var cgstRs=(taxableAmt*item.cgstPer)/100;
							var sgstRs=(taxableAmt*item.sgstPer)/100;
							
							var totalTax=cgstRs+sgstRs;
							
						  	totamount=totamount+taxableAmt;
						  	tr.append($('<td style="text-align:right;"></td>').html((baseRate).toFixed(2)));

						  	tr.append($('<td style="text-align:right;"></td>').html((taxableAmt).toFixed(2)));

						 
						  	tr.append($('<td style="text-align:right;"></td>').html((taxPer).toFixed(2)));
						  
						  	tr.append($('<td style="text-align:right;"></td>').html((totalTax).toFixed(2)));
						 	tottax=tottax+totalTax;
						 	
				           var total=taxableAmt+totalTax;
				           grandtotal=grandtotal+total;
						  	tr.append($('<td style="text-align:right;"></td>').html((total).toFixed(2)));
//<a href='#' class='action_btn' onclick=editItemDetail("+key+")> <abbr title='edit'> <i class='fa fa-edit  fa-lg' ></i></abbr> </a>
						 	tr.append($('<td></td>').html(" <a href='#' class='action_btn'onclick=deleteItemDetail("+key+ ")><abbr title='Delete'><i class='fa fa-trash-o  fa-lg'></i></abbr></a>"));
						  
							$('#table_grid1 tbody').append(tr);
					 }); 
					
						document.getElementById("totalSum").innerHTML=totamount.toFixed(2);
						document.getElementById("taxTotal").innerHTML=tottax.toFixed(2);  
						document.getElementById("grandTotal").innerHTML=grandtotal.toFixed(2);
						document.getElementById("totalSumText").value=totamount.toFixed(2);
						document.getElementById("taxTotalText").value=tottax.toFixed(2);  
						document.getElementById("grandTotalText").value=grandtotal.toFixed(2);
						document.getElementById("qty").value=0;
						document.getElementById("itemId").value="";
						$('#batch_no').html("");
						$('.selectpicker').selectpicker("refresh");
					});

}
function saveTempBill()
{
	var cratesCap=parseInt($("#cratesCap").val());
	var custCap=$("#custCap").val();
	var custId=$("#custId").val();
	var vehId=$("#vehId").val();
	var cratesIssueQty=parseInt($("#cratesIssueQty").val());
	var cratesOpnQty=parseInt($("#cratesOpnQty").val());
	var totalCrates=cratesIssueQty+cratesOpnQty;
	var total=$("#grandTotalText").val();
	
	if(totalCrates<=cratesCap)
		{
	$('#loader').show();
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
		$('#loader').hide();

		$.each(data,function(key, item) {

			
	 }); 
		
		document.getElementById("custId").value="";
		document.getElementById("vehId").value="";
		document.getElementById("cratesIssueQty").value=0;
		document.getElementById("cratesOpnQty").value=0;
		document.getElementById("vehOutKm").value=0;
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
	else
		{
		 alert("You have exceeded limit of crates(LIMIT::"+cratesCap+" ) !!")
			document.getElementById("cratesIssueQty").value=0;

		}
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

function checkBalance() {
	
	var batchNo = document.getElementById("batch_no").value;
	var qty = document.getElementById("qty").value;
	
	//$('#loader').show();

	$.getJSON(
					'${checkBalance}',

					{
						 
						batchNo : batchNo,
						qty : qty,
						ajax : 'true'

					},
					function(flag) { 
						 
						 if(flag==0)
							 {
							 alert("Please Enter Qty less Than or equal to Batch Balance Qty ");
							 document.getElementById("qty").value = 0;
							 }
						 
					});


    

}
function validation() {
	
	var custId = $("#custId").val();
	var itemId = $("#itemId").val();
	var batchNo = $("#batch_no").val();
	var qty = $("#qty").val();

	var isValid = true;
	if (custId==0||custId=="") { 
		isValid = false;
		alert("Please Select Customer");
	} else if (itemId==0||itemId=="") {
		isValid = false;
		alert("Please Select Item ");
	} else if (batchNo==0||batchNo=="") {
		isValid = false;
		alert("Please Select Batch ");
	}else if (qty == ""||qty==0) {
		isValid = false;
		alert("Please Enter Valid Qty");
	}
	return isValid;
}

	</script>
<script type="text/javascript">
function checkCratesStock(issueCrates)
{
	var crateStock = $("#crateStock").val();
	
	if(crateStock<issueCrates)
		{
		  alert("You dont have " +issueCrates+" crates" );
		  document.getElementById("cratesIssueQty").value = "";
		}
	
}


</script>
</body>
</html>
