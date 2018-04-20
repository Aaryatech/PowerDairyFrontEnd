

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

 

</head>
<body>
 --%>

<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
<c:url var="editFrSupplier" value="/editFrSupplier"></c:url>

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
					action="${pageContext.request.contextPath}/searchBills">
				

					<div class="order-left">
					<h2 class="pageTitle">All Bills </h2> 
				</div>
				 <div class="col1title" align="right"> 
						<a href="${pageContext.request.contextPath}/tempBill"><input type="button" value="Add New Bill" class="btn btn-info">
										</a>
					</div>
						 
						
						
					<div class="colOuter">
						<div class="col-md-1">
							<div class="col1title" align="left"> Bill Date: </div>
						</div>
						<div class="col-md-2">
								<input id="datepicker" value="${toDay}" placeholder="Bill Date" class="texboxitemcode texboxcal"
															name="billDate" type="text" required>
							
						</div>
						
						<div class="col-md-1"> 
						<input type="submit" class="btn additem_btn" value="Search" 
												id="b1"/>
						</div>
 					  
					</div> 
					</form>
					<c:set var="billAmt" value="0"/>
					<c:set var="outstandingAmt" value="0"/>
					<c:set var="outstandingCrates" value="0"/>
					<c:forEach items="${billHeaderList}" var="billHeader" varStatus="count">
					
						<c:set var="billAmt" value="${billAmt+billHeader.grandTotal}"/>
							<c:set var="outstandingAmt" value="${outstandingAmt+billHeader.outstandingAmt}"/>
					<c:set var="outstandingCrates" value="${outstandingCrates+((billHeader.cratesOpBal+billHeader.cratesIssued)-billHeaders.cratesReceived)}"/>
					</c:forEach>
					<c:forEach items="${billHeadersList}" var="billHeaders" varStatus="count">
						<c:set var="billAmt" value="${billAmt+billHeaders.grandTotal}"/>
							<c:set var="outstandingAmt" value="${outstandingAmt+billHeaders.outstandingAmt}"/>
					<c:set var="outstandingCrates" value="${outstandingCrates+((billHeaders.cratesOpBal+billHeaders.cratesIssued)-billHeaders.cratesReceived)}"/>
					</c:forEach>
					<div class="colOuter">
						<div class="col-md-3">
							<div class="col1title" align="left"><b>Bill Count (Generated/Pending) :</b> ${generated}/${pending}</div>
						</div>
						  
						<div class="col-md-3">
							<div class="col1title" align="left"><b>Total Bill AMT :</b> ${billAmt}</div>
						</div>
						<div class="col-md-3">
							<div class="col1title" align="left"><b>Total Outstanding AMT :</b>${outstandingAmt}</div>
						</div>
						
						<div class="col-md-3">
							<div class="col1title" align="left"><b>Total Outstanding Crates :</b> ${outstandingCrates}</div>
						</div>
					 
					</div> 
					
					 <div class="colOuter"> 
					</div>
					 <div class="colOuter"> 
					</div>
					 
					 
					
				 
					
				 
					 
				 
					 
					 <div class="colOuter"> 
					</div>
					<div id="table-scroll" class="table-scroll">
									<div id="faux-table" class="faux-table" aria="hidden"></div>
									<div class="table-wrap table-wrap-custbill">
										<table id="table_grid1" class="main-table small-td">
											<thead>
												<tr class="bgpink">
													<th class=col-md-1>Sr No </th>
													<th class=col-md-1>Bill Id </th>
													<th class="col-md-1">Customer Name </th>
													<th class="col-md-1">Vehicle Name</th>
													<th class="col-md-1">Total Km</th>   
													<th class="col-md-1">Bill Amount</th>
													<th class="col-md-1">Collection</th> 
													<th class="col-md-1">Action</th>
												</tr>
											</thead>
											<tbody>
										      <c:set var="cnt" value="0"/>
									 			<c:forEach items="${billHeaderList}" var="billHeader" varStatus="count">
												
												  <c:set var="cnt" value="${cnt+1}"/>
												 <tr>
												<td class="col-md-1"><c:out value="${cnt}" /></td> 
												<td class="col-md-1"><c:out value="-" /></td> 
												<td class="col-md-1"><c:out value="${billHeader.custName}" /></td> 
												<td class="col-md-1"><c:out value="${billHeader.vehName}" /></td> 
												<td class="col-md-1"><c:out value="-" /></td>
												<td class="col-md-1"><c:out value="${billHeader.grandTotal}" /></td>
												<td class="col-md-1"><c:out value="-" /></td>
												<td><a href="${pageContext.request.contextPath}/approvedTempBill/${billHeader.billTempId}">
												Details</a>
												
												<a href="${pageContext.request.contextPath}/creditNote/${billHeader.billTempId}">
												CRN</a></td>
												</tr>
												</c:forEach> 
												<c:forEach items="${billHeadersList}" var="billHeaders" varStatus="count">
												<tr  style="color: #fff; background: #72af73;">
												  <c:set var="cnt" value="${cnt+1}"/>
												<td class="col-md-1"><c:out value="${cnt}" /></td> 
												<td class="col-md-1"><c:out value="${billHeaders.billId}" /></td> 
												<td class="col-md-1"><c:out value="${billHeaders.custName}" /></td> 
												<td class="col-md-1"><c:out value="${billHeaders.vehName}" /></td> 
												<td class="col-md-1"><c:out value="-" /></td>
												<td class="col-md-1"><c:out value="${billHeaders.grandTotal}" /></td>
												<td class="col-md-1"><c:out value="${billHeaders.collectedAmt}" /></td>
												<td>
												
											<%-- 	<a href="${pageContext.request.contextPath}/approvedTempBill/${billHeaders.billTempId}">
												Details</a> --%>
												
												<a href="${pageContext.request.contextPath}/creditNote/${billHeaders.billTempId}">
												CRN</a></td>
												</tr>
												</c:forEach> 
											</tbody>

										</table>
									</div>
								</div>
					 
						 
					<!-- <div class="colOuter">
						<div align="center">
							<input name="submit" class="buttonsaveorder" value="Submit"
								type="submit" align="center">
								<input type="button" class="buttonsaveorder" value="Cancel" id="cancel" onclick="cancel1()" disabled>
						</div>
				 
					</div> -->
					
					 

				

				 
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
