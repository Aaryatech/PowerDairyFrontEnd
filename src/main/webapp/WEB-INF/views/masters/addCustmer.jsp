

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
					action="${pageContext.request.contextPath}/insertCustomer">
					<input type="hidden" name="mod_ser" id="mod_ser"
						value="search_result">

					
						<div class="col-md -3">
							
								<div class="col1title" align="left"><h3>Add Customer</h3></div>
								 
						</div>
						
					<div class="colOuter">
						<div class="col-md-2">
							<div class="col1title" align="left">Customer Name*: </div>
						</div>
						<div class="col-md-3">
							<input id="custName" class="form-control"
								placeholder="Customer Name" value="${customer.custName}" style="text-align: left;" name="custName" type="text" required>
								<input id="custId" class="form-control"
								  name="custId" value="${customer.custId}" type="hidden" >

						</div>
						<div class="col-md-1">
							 
						</div>

						<div class="col-md-2">
							<div class="col1title" align="left">Customer Address*: </div>
						</div>
						<div class="col-md-3">
							<input id="custAdd" class="form-control" style="text-align: left;"
								placeholder="Customer Address" value="${customer.custAddress}" name="custAdd" type="text" required>

						</div>
					 
					</div>
					
					<div class="colOuter">
						
						

						<div class="col-md-2">
							<div class="col1title" align="left">Customer Type*: </div>
						</div>
						<div class="col-md-3">
							<select class="selectpicker" data-live-search="true" title="Please Select" 
							name="custType" id="custType" required>
							<c:forEach items="${customerTypeList}" var="customerTypeList"> 
							<c:choose>
								<c:when test="${customerTypeList.custTypeId==customer.custType}">
								<option value="${customerTypeList.custTypeId}" selected><c:out value="${customerTypeList.custTypeName}"/></option>
								</c:when>
								<c:otherwise>
								<option value="${customerTypeList.custTypeId}" ><c:out value="${customerTypeList.custTypeName}"/></option>
								</c:otherwise>
							</c:choose> 
							</c:forEach>
							 
							 
						 </select>

						</div>
						<div class="col-md-1"> </div>
						<div class="col-md-2">
							<div class="col1title" align="left">Mobile No*: </div>
						</div>
						<div class="col-md-3">
							<input id="mob" class="form-control"
								placeholder="Mobile No" name="mob" style="text-align: left;" value="${customer.custMobNo}" pattern="^\d{10}$" type="text" required>

						</div>
				 
					</div>
					
					<div class="colOuter">
						<div class="col-md-2">
							<div class="col1title" align="left">Landline No: </div>
						</div>
						<div class="col-md-3">
							<input id="landlineNo" class="form-control"
								placeholder="Landline No" name="landlineNo" style="text-align: left;" value="${customer.custLandlineNo}" pattern="^\d{10}$" type="text" >

						</div>
						<div class="col-md-1">
							 
						</div>

						<div class="col-md-2">
							<div class="col1title" align="left">E-Mail*: </div>
						</div>
						<div class="col-md-3">
							<input id="email" class="form-control"
								placeholder="Email" name="email" type="email" style="text-align: left;" value="${customer.custEmailId}" required>

						</div>
				 
					</div>
					
					<div class="colOuter">
						<div class="col-md-2">
							<div class="col1title" align="left">Customer Root*: </div>
						</div>
						<div class="col-md-3">
							<input id="root" class="form-control"
								placeholder="Customer Root" name="root" style="text-align: left;" value="${customer.custRoot}" type="text" required>

						</div>
						<div class="col-md-1">
							 
						</div>

						<div class="col-md-2">
							<div class="col1title" align="left">Account No*: </div>
						</div>
						<div class="col-md-3">
							<input id="accNo" class="form-control"
								placeholder="Account No" name="accNo" style="text-align: left;" value="${customer.custBankAccNo}" pattern="\d+" type="text" required>

						</div>
				 
					</div>
					
					<div class="colOuter">
						<div class="col-md-2">
							<div class="col1title" align="left">IFSC Code*: </div>
						</div>
						<div class="col-md-3">
							<input id="ifsc" class="form-control"
								placeholder="Ifsc Code" name="ifsc" style="text-align: left;" value="${customer.custBankIfsc}" type="text" required>

						</div>
						<div class="col-md-1">
							 
						</div>

						<div class="col-md-2">
							<div class="col1title" align="left">Bank Name*: </div>
						</div>
						<div class="col-md-3">
							<input id="bankName" class="form-control"
								placeholder="Bank Name" name="bankName" style="text-align: left;" value="${customer.custBankName}" type="text" required>

						</div>
				 
					</div>
					
					<div class="colOuter">
						<div class="col-md-2">
							<div class="col1title" align="left">Pan No*: </div>
						</div>
						<div class="col-md-3">
							<input id="panNo" class="form-control"
								placeholder="Pan No" name="panNo" style="text-align: left;" value="${customer.panNo}" type="text" required>

						</div>
						<div class="col-md-1">
							 
						</div>

						<div class="col-md-2">
							<div class="col1title" align="left">FSSAI No* </div>
						</div>
						<div class="col-md-3">
							<input id="fssaiNo" class="form-control"
								placeholder="FSSAI No" name="fssaiNo" style="text-align: left;" value="${customer.fssaiNo}" type="text" required>

						</div>
				 
					</div> 
					
					<div class="colOuter">
						<div class="col-md-2">
							<div class="col1title" align="left">GST No*: </div>
						</div>
						<div class="col-md-3">
							<input id="gstnNo" class="form-control"
								placeholder="GSTN No" name="gstnNo" style="text-align: left;" value="${customer.custGstNo}" type="text" required>

						</div>
						<div class="col-md-1">
							 
						</div>

						<div class="col-md-2">
							<div class="col1title" align="left">Cap*: </div>
						</div>
						<div class="col-md-3">
							<input id="cap" class="form-control"
								placeholder="Cap" name="cap" value="${customer.custCap}" style="text-align: left;" pattern="[+-]?([0-9]*[.])?[0-9]+" type="text" required>

						</div>
				 
					</div>
					
					<div class="colOuter">
						<div class="col-md-2">
							<div class="col1title" align="left">Customer Reference*: </div>
						</div>
						<div class="col-md-3">
							<input id="custRef" class="form-control"
								placeholder="Customer Reference" value="${customer.custReference}"   style="text-align: left;" name="custRef" type="text" required>

						</div>
						<div class="col-md-1">
							 
						</div>

						<div class="col-md-2">
							<div class="col1title" align="left">Ref. Mobile No*: </div>
						</div>
						<div class="col-md-3">
							<input id="refMo" class="form-control"
								placeholder="Ref. Mobile No" name="refMo"  style="text-align: left;" value="${customer.refMobNo}" pattern="^\d{10}$" type="text" required>

						</div>
				 
					</div> 
					
						<div class="colOuter">
						<div class="col-md-2">
							<div class="col1title" align="left">Opening Cap*: </div>
						</div>
						<div class="col-md-3">
							<input id="openigCap" class="form-control"
								placeholder="Opening Cap" name="openigCap" style="text-align: left;" value="${customer.cratesOpBal}" type="number" required>

						</div>
						<div class="col-md-1">
							 
						</div>

						<div class="col-md-2">
							<div class="col1title" align="left">Credit Cap </div>
						</div>
						<div class="col-md-3">
							<input id="creditCap" class="form-control"
								placeholder="Creadit Cap" name="creditCap" style="text-align: left;" value="${customer.cratesCap}" type="number" required>

						</div>
				 
					</div> 
					
					<div class="colOuter">
						 
						<div class="col-md-2">
							<div class="col1title" align="left">Rate Type*: </div>
						</div>
						<div class="col-md-3">
							<select class="selectpicker " data-live-search="true" title="Please Select" 
							name="rsHeaderId" id="rsHeaderId" required>
						 <c:forEach items="${rsHeaderList}" var="rsHeaderList"> 
							<c:choose>
								<c:when test="${rsHeaderList.rsHeaderId==customer.rsHeaderId}">
								<option value="${rsHeaderList.rsHeaderId}" selected><c:out value="${rsHeaderList.rsName}"/></option> 
								</c:when>
								<c:otherwise>
								<option value="${rsHeaderList.rsHeaderId}" ><c:out value="${rsHeaderList.rsName}"/></option> 
								</c:otherwise>
							</c:choose> 
							</c:forEach>
							 
						 </select> 
						</div>
						 <div class="col-md-1"> </div>
						 
						  <div class="col-md-2">
							<div class="col1title" align="left">Select Vehicle*: </div>
						</div>
						<div class="col-md-3">
						
					   									 
					 	<select class="selectpicker" data-live-search="true" title="Please Select" 
							name="vehId" id="vehId" required> 
							 <c:forEach items="${vehicleList}" var="vehicleList"> 
							<c:choose>
								<c:when test="${vehicleList.vehId==customer.vehId}">
								<option value="${vehicleList.vehId}" selected><c:out value="${vehicleList.vehName}"/></option>  
								</c:when>
								<c:otherwise>
								<option value="${vehicleList.vehId}" ><c:out value="${vehicleList.vehName}"/></option>  
								</c:otherwise>
							</c:choose> 
							</c:forEach>
							 
							 
							 
						 </select>

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
