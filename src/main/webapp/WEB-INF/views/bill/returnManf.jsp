

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

 

</head>
<body>
 --%>

<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
<c:url var="getBatchListByitemId" value="/getBatchListByitemId"></c:url>
<c:url var="checkBalance" value="/checkBalance" />
<c:url var="addItemInReturnManufacture" value="/addItemInReturnManufacture" />
<c:url var="deleteItemInReturnManufacture" value="/deleteItemInReturnManufacture" />

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
	
	var c = document.getElementById("stockDate").value; 
	var toDateValue = c.split('-');
	var tDate=new Date();
	tDate.setFullYear(toDateValue[0],(toDateValue[1] - 1 ),toDateValue[2]); 
	var min = new Date(tDate.setDate(tDate.getDate())); 
	
	$("#fromdatepicker").datepicker({
		dateFormat : 'dd-mm-yy',
		minDate : min
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
					action="${pageContext.request.contextPath}/submitReturnManufacture">
					<input type="hidden" name="mod_ser" id="mod_ser"
						value="search_result">

					<div class="order-left"> 
								<h2 class="pageTitle"> Return To Manufacture </h2> 
								  
						</div>
						
						 <div class="col1title" align="right"> 
						<a href="${pageContext.request.contextPath}/manufactureReturnHistory"><input type="button" value="Return History" class="btn btn-info">
										</a>
					</div>
					
						 <div class="colOuter">
						  
						 <div class="col-md-2">
							<div class="col1title" align="left">Return Crates Qty*: </div>
						</div>
						<div class="col-md-3">
							<input id="cratesQty" class="form-control"
								placeholder="Return Crates Qty" name="cratesQty" style="text-align: left;"  type="number" onchange="checkCratesStock();"  required>
							<input id="crateStock"  
								placeholder="crateStock" value="${crateStock }" name="crateStock"    type="hidden"   >
						</div>
						<div class="col-md-1"></div>
						<div class="col-md-2">
							<div class="col1title" align="left">Remark : </div>
						</div>
						<div class="col-md-3">
							<input id="remark" class="form-control"
								placeholder="Remark" name="remark" style="text-align: left;"  type="text"  >
								<input id="stockDate"  value="${stockDate}" name="stockDate" style="text-align: left;"  type="hidden"  >

						</div>
 
					</div>
					<div class="colOuter">
						<div class="col-md-2">
							<div class="col1title" align="left">Date*: </div>
						</div>
						<div class="col-md-3">
							<input id="fromdatepicker"  placeholder="Date" style="text-align: left;" class="form-control" name="returnDate" type="text" required> 

						</div>
					</div>
					<div class="colOuter"></div>
					<div class="colOuter"></div>
						  
					<div class="colOuter">
						 
						<div class="col-md-2">
							<div class="col1title" align="left">Select Item *: </div>
						</div>
						<div class="col-md-3">
							<select class="selectpicker" data-live-search="true" title="Please Select" 
														onchange="getBatchListByitemId();"	name="itemId" id="itemId"   > 
														 
																<c:forEach items="${itemList}" var="itemList">
																	<option value="${itemList.itemId}">${itemList.itemName} &nbsp;&nbsp; ${itemList.itemCode}</option>
																
																</c:forEach>
															 </select>

						</div>
						<div class="col-md-1"> </div>
						
						<div class="col-md-2">
							<div class="col1title" align="left">Select Batch *: </div>
						</div>
						<div class="col-md-3">
							<select class="selectpicker" data-live-search="true" title="Please Select" 
															name="batchNo" id="batchNo"   > 
														 
																 
															 </select>

						</div>
						
				 
					</div>
					
					<div class="colOuter">
						 <div class="col-md-2">
							<div class="col1title" align="left">Return Qty*: </div>
						</div>
						<div class="col-md-3">
							<input id="qty" class="form-control"
								placeholder="Qty" name="qty" style="text-align: left;"  type="number" onchange="checkBalance();"  >

						</div>
						<div class="col-md-1"></div>
						  
 
					</div>
					
					<div class="colOuter">
						
					 
						
 
					</div>
					
				 
					 
					 <div class="colOuter"> 
					 <input type="button" class="btn additem_btn" value="Add Item" onclick="addItem();"
												id="b1"/>
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
													<th class="col-md-1">Return Qty</th>   
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
						<%-- <c:choose>
							<c:when test="${today==stockDate }"> --%>
							<input name="submit" id="submit" class="buttonsaveorder" value="Submit"
								type="submit" align="center" disabled>
							<%-- </c:when>
							<c:otherwise>
							
							<input name="submit" class="buttonsaveorder" value="Submit"
								type="submit" align="center" disabled>
								Please Complete The Day End Process.....
							</c:otherwise>
						</c:choose> --%>
							
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

function addItem( ) {
	 
	var itemId = $("#itemId").val(); 
	var itemName = $("#itemId option:selected").text();  
	var batchNo = $("#batchNo").val(); 
	var qty = $("#qty").val();
	 
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
	 
	 
	 
	
	 if(valid==0)
		 {
		 
		 
	
	$('#loader').show();

	$
			.getJSON(
					'${addItemInReturnManufacture}',

					{
						 
						 
						itemId : itemId,
						itemName : itemName, 
						batchNo : batchNo,
						qty : qty, 
						ajax : 'true'

					},
					function(data) { 
						
						$('#table_grid1 td').remove();
						if (data == "") {
							alert("No records found !!");
							document.getElementById("submit").disabled=true;
						}
						var grandTotal=0;
						$.each(
								data,
								function(key, itemList) {
									
									document.getElementById("submit").disabled=false; 
									var tr = $('<tr></tr>');
										tr.append($('<td class="col-sm-1"></td>').html(key+1));
										tr.append($('<td class="col-md-1"></td>').html(itemList.batchNo));
									  	tr.append($('<td class="col-md-2"></td>').html(itemList.itemName)); 
									  	tr.append($('<td class="col-md-1" ></td>').html(itemList.itemReturnQty));  
									  	tr.append($('<td></td>').html('<span class="glyphicon glyphicon-remove" onclick="deleteItem('+key+');""></span>')); 
										$('#table_grid1 tbody').append(tr);
										
										document.getElementById("itemId").value="";
										$('.selectpicker').selectpicker('refresh'); 
										document.getElementById("batchNo").value="";  
										document.getElementById("qty").value="";  
										 

								})
						 
								 
					});

 
		 }

}

function deleteItem(index) {
	 
	 
	 
	$('#loader').show();

	$
			.getJSON(
					'${deleteItemInReturnManufacture}',

					{
						 
						 
						index : index, 
						ajax : 'true'

					},
					function(data) { 
						
						$('#table_grid1 td').remove();
						if (data == "") {
							alert("No records found !!");
							document.getElementById("submit").disabled=true; 

						}
						var grandTotal=0;
						$.each(
								data,
								function(key, itemList) {
									
									document.getElementById("submit").disabled=false; 
									var tr = $('<tr></tr>');
										tr.append($('<td class="col-sm-1"></td>').html(key+1));
										tr.append($('<td class="col-md-1"></td>').html(itemList.batchNo));
									  	tr.append($('<td class="col-md-2"></td>').html(itemList.itemName)); 
									  	tr.append($('<td class="col-md-1" ></td>').html(itemList.itemReturnQty));  
									  	tr.append($('<td></td>').html('<span class="glyphicon glyphicon-remove" onclick="deleteItem('+key+');""></span>')); 
										$('#table_grid1 tbody').append(tr);
										
										document.getElementById("itemId").value="";
										$('.selectpicker').selectpicker('refresh'); 
										document.getElementById("batchNo").value="";  
										document.getElementById("qty").value="";  
										 

								})
						 
								 
					});
 
}

function getBatchListByitemId() {
 
	var itemId = document.getElementById("itemId").value;
	$('#loader').show();

	$
			.getJSON(
					'${getBatchListByitemId}',

					{
						 
						itemId : itemId, 
						ajax : 'true'

					},
					function(data) { 
						 
						var html;
						var len = data.length;
						for ( var i = 0; i < len; i++) {
							 
								html += '<option value="' + data[i].poDetailId + '">'
								+ data[i].batchNo + '&nbsp;&nbsp; &nbsp;Qty = '+data[i].balance+'</option>';
							 
							
						}
						html += '</option>';
						$('#batchNo').html(html);
						$('.selectpicker').selectpicker('refresh');
						 
					});

 
	   

}


function checkCratesStock() {
	
	var crateStock = parseInt(document.getElementById("crateStock").value);
	var cratesQty = parseInt(document.getElementById("cratesQty").value);
	
	if(cratesQty>crateStock) 
		{
		alert("Please You Dont Have Crates ");
		document.getElementById("cratesQty").value="";
		}

}

function checkBalance() {
	
	var batchNo = document.getElementById("batchNo").value;
	var qty = document.getElementById("qty").value;
	
	$('#loader').show();

	$
			.getJSON(
					'${checkBalance}',

					{
						 
						batchNo : batchNo,
						qty : qty,
						ajax : 'true'

					},
					function(flag) { 
						 
						 if(flag==0)
							 {
							 alert("Your Enter Qty Greater Than Batch Balance Qty ");
							 document.getElementById("qty").value = "";
							 }
						 
					});


    

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
