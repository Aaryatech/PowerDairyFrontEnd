

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

 

</head>
<body>
 --%>
 
<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
<c:url var="getStockBetweenDate" value="/getStockBetweenDate"></c:url>
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
	<jsp:include page="/WEB-INF/views/include/logo.jsp"></jsp:include>

<!--topLeft-nav-->
<div class="sidebarOuter"></div>
<!--topLeft-nav-->

<!--wrapper-start-->
<div class="wrapper">

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
					action="${pageContext.request.contextPath}/stockDayEnd">
				
						<div class="col-md -3">
							
								<div class="col1title" align="left"><h3>Get Current Stock</h3></div>
								 
						</div>
			
						<div class="colOuter">
						<div class="col-md-2">
							<div class="col1title" align="left">Stock Date: </div>
						</div>
						<div class="col-md-3">
							<input id="date" class="form-control"
								placeholder="Stock Date" value="${stockDate}" name="date"  style="text-align: left;"  type="text" readonly="readonly">

						</div>
						
						<div class="col-md-1"></div>
						
						<div class="col-md-2">
							<div class="col1title" align="left">Customer Type*: </div>
						</div>
						<div class="col-md-3">
							<select class="selectpicker" data-live-search="true" onchange="unlockDiv();" title="Please Select" 
							name="custType" id="stockType" required>
							 
								<option value="1" selected>Get Current Stock</option>
							   <option value="2"  >Get Stock Between Date</option>
						 </select>

						</div>
					 
					</div>
					
					<div class="colOuter" id="selectFromDate" style="display: none">
						<div class="col-md-2" align="left">From:</div>
						<div class="col-md-2">
							<input id="datepicker"  placeholder="From Date" class="texboxitemcode texboxcal"
															name="fromDate" type="text"  >
						
						</div>
						<div class="col-md-1" align="left"></div>
						 
						<div class="col-md-1" align="left">TO:</div>
						<div class="col-md-2">
							<input id="datepicker1" class="texboxitemcode texboxcal"
								value="${toDate}" placeholder="To Date" name="toDate" type="text">
								 
						</div>
						
						<div class="col-md-1" align="left"></div>
						<div class="col-md-1">
							<button type="button" class="btn  buttonsaveorder"
								onclick="getBetweenDateStock();">Search</button>
						</div>
					 
					</div>
					
					<div id="table-scroll" class="table-scroll" id="currentStockTable">
									<div id="faux-table" class="faux-table" aria="hidden"></div>
									<div class="table-wrap table-wrap-custbill">
										<table id="table_grid1" class="main-table small-td">
											<thead>
												<tr class="bgpink">
								
									<th class="col-sm-1">Sr No</th>
									<th class="col-md-1">Item Name</th> 
									<th class="col-md-1">Opening Stock +</th>
									<th class="col-md-1">Total Purchase +</th>
									<th class="col-md-1">Total Sell -</th>
									<th class="col-md-1">Total Return +</th>
									<th class="col-md-1">Hub Return Qty -</th>
									<th class="col-md-1">=Closing Qty</th>
								
								</tr>
											</thead>
											<tbody>
											<c:forEach items="${currentStockList}" var="currentStockList"
									varStatus="count">
									<tr>
										 <td class="col-sm-1"><c:out value="${count.index+1}" /></td> 
										 <td class="col-md-3"><c:out value="${currentStockList.itemName}" /></td>
										 <td class="col-md-1"><c:out value="${currentStockList.openingStock}" /></td>
										 <td class="col-md-1"><c:out value="${currentStockList.totalPurchase}" /></td>
										 <td class="col-md-1"><c:out value="${currentStockList.totalSale}" /></td>
											<td class="col-md-1"><c:out value="${currentStockList.returnQty}" /></td>
											<td class="col-md-1"><c:out value="${currentStockList.hubReturnQty}" /></td>
											
											<td class="col-md-1" ><input id="closingQty${currentStockList.itemId}" class="form-control"
								placeholder="Qty"   name="closingQty${currentStockList.itemId}" value="${currentStockList.openingStock+currentStockList.totalPurchase-currentStockList.totalSale+currentStockList.returnQty-currentStockList.hubReturnQty}"   style="text-align: left;"  type="number"  readonly="readonly">		
	                                   
									</tr>
								</c:forEach>

											</tbody>

										</table>
									</div>
								</div>
								 <div class="col-md -3">
							
								<div class="col1title" align="left"><h3>Get Crates Current Stock</h3></div>
								 
						</div>
								<div id="table-scroll" class="table-scroll" id="currentStockTable">
									<div id="faux-table" class="faux-table" aria="hidden"></div>
									<div class="table-wrap table-wrap-custbill">
										<table id="table_grid" class="main-table small-td">
											<thead>
												<tr class="bgpink">
								 
									<th class="col-md-1">Opening Stock +</th>
									<th class="col-md-1">Total Received By Purchase+</th>
									<th class="col-md-1">Issue Qty -</th>
									<th class="col-md-1">Total Return By Customer+</th>
									<th class="col-md-1"> Return To MFG-</th>
									<th class="col-md-1">=Closing Qty</th>
								
								</tr>
											</thead>
											<tbody>
											 
									<tr>
										 
										 <td class="col-md-1"><c:out value="${getCratesStock.cratesOpQty}" /></td>
										 <td class="col-md-1"><c:out value="${getCratesStock.cratesReceivedQtyBypurchase}" /></td>
										 <td class="col-md-1"><c:out value="${getCratesStock.cratesIssued}" /></td>
										 <td class="col-md-1"><c:out value="${getCratesStock.cratesReceivedBycustomer}" /></td>
											<td class="col-md-1"><c:out value="${getCratesStock.cratesReturnQtyTomfg}" /></td>
											
											
											<td class="col-md-1" ><input id="closingCratesQty" class="form-control"
								placeholder="Qty"   name="closingCratesQty" value="${getCratesStock.cratesOpQty+getCratesStock.cratesReceivedQtyBypurchase-getCratesStock.cratesIssued+getCratesStock.cratesReceivedBycustomer-getCratesStock.cratesReturnQtyTomfg}"   style="text-align: left;"  type="number"  readonly="readonly">		
	                                   
									</tr>
								 

											</tbody>

										</table>
									</div>
								</div>
								  <%-- tr.append($('<td></td>').html('<input id="closingQty'+itemList.itemId+'" class="form-control"'+
												+'placeholder="Qty"   name="closingQty${currentStockList.itemId}" value="'+(itemList.openingStock+itemList.totalPurchase-itemList.totalSale+returnQty-itemList.hubReturnQty)+'" style="text-align: left;"  type="number"  readonly="readonly">'));  --%> 
									  	
								 
					  
	             <div class="colOuter">
						<div align="center">
						<c:choose>
							<c:when test="${stockDate==tommorowDate}">
							<input name="submit" class="buttonsaveorder" value="Day End" id="dayEndButton"
								type="submit" align="center"  disabled>
							</c:when>
							<c:otherwise>
							<input name="submit" class="buttonsaveorder" value="Day End" id="dayEndButton"
								type="submit" align="center"  >
							</c:otherwise>
						</c:choose>
							
						</div>
				 
					</div>
				</form>

				 
			</div>
		
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
<!-- <script>
function callMe(key) {
  
	var value = $("#qty"+key).val(); 
	alert(value);
	
}
</script> -->
<script>
function unlockDiv() {
	 
		var flag = $('#stockType').val();
		if(flag==2)
		{
			$('#selectFromDate').show(); 
		}
		else
			{
			window.location.href='${pageContext.request.contextPath}/getCurrentStock';
			
			}
		 
	 
}

function getBetweenDateStock()
{		 

	 
	var fromDate = $("#datepicker").val(); 
	var toDate = $("#datepicker1").val(); 
	var date = $("#date").val();  
	var valid = 0;
	
	if(fromDate=="")
		{
			alert("Enter From Date");
			valid=1;
		}
	else if(toDate=="" || compareDate()==false)
	{
		if(toDate=="")
			alert("Enter To Date");
	 
		valid=1;
	}
	 
	
	if(valid==0)
	{
		
	$
	.getJSON(
			'${getStockBetweenDate}',

			{
				fromDate : fromDate,
				toDate : toDate, 
				ajax : 'true',

			},
			function(data) {
				 
				 if(data=="")
					 {
					 alert("No Record Found");
					 }
				 else
					 {
					 
						 $("#dayEndButton").hide();  
						$('#table_grid1 td').remove();
						 
						  $.each(data,
								function(key, itemList) {
							  var totalclose = itemList.openingStock+itemList.totalPurchase-itemList.totalSale+itemList.returnQty-itemList.hubReturnQty; 
							  
									var tr = $('<tr></tr>');
										tr.append($('<td class="col-sm-1"></td>').html(key+1));
										tr.append($('<td class="col-md-3"></td>').html(itemList.itemName)); 
										tr.append($('<td class="col-md-1"></td>').html(itemList.openingStock)); 
									  	tr.append($('<td class="col-md-1"></td>').html(itemList.totalPurchase));
									  	tr.append($('<td class="col-md-1"></td>').html(itemList.totalSale));
									  	tr.append($('<td class="col-md-1"></td>').html(itemList.returnQty));
									  	tr.append($('<td class="col-md-1"></td>').html(itemList.hubReturnQty));  
									  
									  	tr.append($('<td></td>').html('<input id="closingQty'+itemList.itemId+'" class="form-control" placeholder="Qty"   name="closingQty'+itemList.itemId+'" value="'+totalclose+'" style="text-align: left;"  type="number"  readonly="readonly">'));
									  $('#table_grid1 tbody').append(tr);
								})  
					 }
				
				 
				
			});
	}
	
}

function compareDate() {
	 
	var date = $('#date').val();//delivery Date
	var toDate = $('#datepicker1').val();
	var fromDate = $('#datepicker').val();
	
	var isValid=false;
	
	var stockDate = date.split('-'); 
	var toDateValue = toDate.split('-');
	var fromDateValue = fromDate.split('-');
	
	var tDate=new Date();
	tDate.setFullYear(toDateValue[2],(toDateValue[1] - 1 ),toDateValue[0]);
	 
	var sDate=new Date();
	sDate.setFullYear(stockDate[2],(stockDate[1] - 1 ),stockDate[0]);
	
	var frDate=new Date();
	frDate.setFullYear(fromDateValue[2],(fromDateValue[1] - 1 ),fromDateValue[0]);
 
	if(sDate<=tDate || tDate<frDate) 
		{
			if(sDate<=tDate)
				alert("Enter To Date Befor The Stock Date ");
			else
				alert("Enter from Date Befor The To Date ");
		isValid=false; 
		}
		
	else
		{
		isValid = true; 
		}
		
 
       return isValid;
 
}
</script>
</body>
</html>
