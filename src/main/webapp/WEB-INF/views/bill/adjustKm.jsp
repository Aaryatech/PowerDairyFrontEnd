

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

 

</head>
<body>
 --%>

<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
<c:url var="getBatchListByitemId" value="/getBatchListByitemId"></c:url>
<c:url var="getVehicle" value="/getVehicle" />

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
					action="${pageContext.request.contextPath}/sumbitAdjustKm">
					<input type="hidden" name="mod_ser" id="mod_ser"
						value="search_result">

					<div class="order-left"> 
								<h2 class="pageTitle"> Adjust KM </h2> 
								  
						</div>
						
						 <div class="col1title" align="right"> 
						<a href="${pageContext.request.contextPath}/tempBill"><input type="button" value="Add Bill" class="btn btn-info">
										</a>
					</div>
						 
						  
					<div class="colOuter">
						 
						<div class="col-md-2">
							<div class="col1title" align="left">Select Vehicle *: </div>
						</div>
						<div class="col-md-3">
							<select class="selectpicker" data-live-search="true" title="Please Select" 
													onchange="onVehicleChange(this.value)"	 	name="vehId" id="vehId" required > 
														 
																<c:forEach items="${vehicleList}" var="vehicleList">
																	<option value="${vehicleList.vehId}">${vehicleList.vehName} </option>
																
																</c:forEach>
															 </select>

						</div>
						<div class="col-md-1"> </div>
						<div class="col-md-2">
							<div class="col1title" align="left">Vehicle Out KM*: </div>
						</div>
						<div class="col-md-3">
							<input id="vehOutKm" class="form-control"
								placeholder="Vehicle Out KM" name="vehOutKm" style="text-align: left;" type="number" readonly>

						</div>
					  
						
				 
					</div>
					
					<div class="colOuter">
					 <div class="col-md-2">
							<div class="col1title" align="left">In KM*: </div>
						</div>
						<div class="col-md-3">
							<input id="inKm" class="form-control"
								placeholder="In KM" name="inKm" style="text-align: left;" onchange="compareInAndOutKm()"  type="number"   required>

						</div>
						<div class="col-md-1"></div>
						 <div class="col-md-2">
							<div class="col1title" align="left">Driver Name*: </div>
						</div>
						<div class="col-md-3">
							<input id="driverName" class="form-control"
								placeholder="Driver Name" name="driverName" style="text-align: left;"  type="text"   required>

						</div>
						
						   
 
					</div>
					
					<div class="colOuter">
						  <div class="col-md-2">
							<div class="col1title" align="left">Remark : </div>
						</div>
						<div class="col-md-3">
							<input id="remark" class="form-control"
								placeholder="Remark" name="remark" style="text-align: left;"  type="text"  >

						</div>
					</div>
					
				 
					 
					 <div class="colOuter"> 
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
 
function compareInAndOutKm() {
	
	var vehOutKm = parseInt(document.getElementById("vehOutKm").value);
	var inKm = parseInt(document.getElementById("inKm").value);
	
	if(vehOutKm>=inKm) 
		{
		alert("You Enter Less than KM");
		document.getElementById("inKm").value="";
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
