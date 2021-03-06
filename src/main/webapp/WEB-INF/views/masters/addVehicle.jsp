

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
 

</head>
<body>
 --%>

<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
<c:url var="editFrSupplier" value="/editFrSupplier"></c:url>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.12.1/css/bootstrap-select.css" >
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
					action="${pageContext.request.contextPath}/insertVehicle">
					<input type="hidden" name="mod_ser" id="mod_ser"
						value="search_result">

					
						<div class="col-md -3">
							
								<div class="col1title" align="left"><h3>Add Vehicle</h3></div>
								 
						</div>
						
					<div class="colOuter">
						<div class="col-md-2">
							<div class="col1title" align="left">Vehicle No*: </div>
						</div>
						<div class="col-md-3">
							<input id="vehicleName" class="form-control"
								placeholder="Vehicle Name" style="text-align: left;" name="vehicleName" type="text" value="${vehicle.vehName}" required>
								<input id="vehId" class="form-control"
								  name="vehId"  type="hidden" value="${vehicle.vehId}" >

						</div>
						<div class="col-md-1"> </div>
						
						<div class="col-md-2">
							<div class="col1title" align="left">Select Type*: </div>
						</div>
						<div class="col-md-3">
							<select class="selectpicker" data-live-search="true" title="Please Select" class="form-control"
							name="vehType" id="vehType" required> 
							<c:choose>
							<c:when test="${vehicle.vehType==1}">
							<option value="1" selected>abc</option>
							<option value="2">pqr</option>
							</c:when>
							<c:when test="${vehicle.vehType==2}">
							<option value="1" >abc</option>
							<option value="2" selected>pqr</option>
							</c:when>
							<c:otherwise>
							<option value="1">abc</option>
							<option value="2">pqr</option>
							</c:otherwise>
							</c:choose>
							 
						 </select>

						</div>

						
					 
					</div>
					
					<div class="colOuter">
						
						<div class="col-md-2">
							<div class="col1title" align="left">Vehicle Opening Km*: </div>
						</div>
						<div class="col-md-3">
							<input id="vehOpeningKm" style="text-align: left;" class="form-control"
								placeholder="Vehicle Opening Km" name="vehOpeningKm" type="number"value="${vehicle.vehOpKms}" required>

						</div>

						
						<div class="col-md-1"> </div>
						
						<div class="col-md-2">
							<div class="col1title" align="left"> Opening Km Date*: </div>
						</div>
						<div class="col-md-3">
							<input id="datepicker" style="text-align: left;" class="texboxitemcode texboxcal"
								 placeholder="Opening Km Date" name="openingKmDate" type="text"value="${vehicle.vehOpKmsDate}" required>

						</div>
						
				 
					</div>
					  
					<div class="colOuter">
						<div align="center">
							<input name="submit" class="buttonsaveorder" value="Submit"
								type="submit" align="center">
								<!-- <input type="button" class="buttonsaveorder" value="Cancel" id="cancel" onclick="cancel1()" disabled> -->
						</div>
				 
					</div>
					
					 <div id="table-scroll" class="table-scroll">
									<div id="faux-table" class="faux-table" aria="hidden"></div>
									<div class="table-wrap table-wrap-custbill">
										<table id="table_grid1" class="main-table small-td">
											<thead>
												<tr class="bgpink">
													<th class="col-sm-1">Sr no.</th>
													<th class="col-md-1">Vehicle No</th> 
													<th class="col-md-1">Vehicle Type</th> 
													<th class="col-md-1">Vehicle Opening KM</th>
													<th class="col-md-1">Opening KM Date</th>
													<th class="col-md-1">Action</th>
												</tr>
											</thead>
											<tbody>
											
											<c:forEach items="${vehicleList}" var="vehicleList"
									varStatus="count">
									<tr>
										 <td class="col-md-1"><c:out value="${count.index+1}" /></td>
										  
											 
										 <td class="col-md-1"><c:out value="${vehicleList.vehName}" /></td>
										<td class="col-md-1"><c:out value="${vehicleList.vehType}" /></td>		 
										<td class="col-md-1"><c:out value="${vehicleList.vehOpKms}" /></td>	
										<td class="col-md-1">${vehicleList.vehOpKmsDate}<%-- <fmt:formatDate pattern = "dd-MM-yyyy"
																value="${vehicleList.vehOpKmsDate}"/> --%> </td>	
									 <td>
									 <a href="${pageContext.request.contextPath}/editVehicle/${vehicleList.vehId}"><abbr title="Edit"><i  class="fa fa-edit"></i></abbr></a> 
									 <a href="${pageContext.request.contextPath}/deleteVehicle/${vehicleList.vehId}"><abbr title="Delete"><i class="fa fa-trash"></i></abbr></a></td>
										 
									</tr>
								</c:forEach>

											</tbody>

										</table>
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
