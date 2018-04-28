

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

 

</head>
<body>

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
	<!-- <style>
table, th, td {
    border: 1px solid #9da88d;
}

</style> -->
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

					
						<div class="col-md -3">
							
								<div class="col1title" align="left"><h3>Credit Note Detail</h3></div>
						</div>
						
					<div class="colOuter">
						<div class="col-md-2">
							<div class="col1title" align="left">CRN No.: </div>
						</div>
						<div class="col-md-1" align="left">
							${crnHeader.crnId}

						</div>
						<div class="col-md-1">
							 
						</div>

						<div class="col-md-2">
							<div class="col1title" align="left">Date: </div>
						</div>
						<div class="col-md-1" align="left">
							${crnHeader.crnDate}

						</div>
					 
					</div>
					
					 
					
					<div class="colOuter">
						<div class="col-md-2" >
							<div class="col1title" align="left">Customer: </div>
						</div>
						<div class="col-md-1" align="left">
							${crnHeader.custName}

						</div>
						<div class="col-md-1">
							 
						</div>

						<div class="col-md-2">
							<div class="col1title" align="left">Remark: </div>
						</div>
						<div class="col-md-1" align="left">
							${crnHeader.remarks}
						</div>
				 
					</div>
				 
					
					<div id="table-scroll" class="table-scroll">
					<div id="faux-table" class="faux-table" aria="hidden"></div>
					<div class="table-wrap">
						<table   id="table_grid" class="main-table">

							<thead>
								<tr class="bgpink">
												<th class="col-sm-1">Sr no.</th>
													<th class="col-md-1">Batch No</th>
													<th class="col-md-2">Item Name</th>
													<th class="col-md-1">Leakage Qty</th>   
													<th class="col-md-1">Expire Qty</th>   
													
													<th class="col-md-1">Rate</th> 
													<th class="col-md-1">Total</th> 
																	</tr>
								 
							</thead>
							<tbody>

								<c:forEach items="${crnHeader.creditNoteDetailList}" var="creditNoteDetail"
									varStatus="count">
									<tr>
										 <td class="col-sm-1"><c:out value="${count.index+1}" /></td>
										  
										  <td class="col-md-1"><c:out
												value="${creditNoteDetail.batchId}" /></td>
												 
										<td class="col-md-1"><c:out
												value="${creditNoteDetail.itemName}" /></td>
										
										<td class="col-md-1" ><c:out
												value="${creditNoteDetail.leakageQty}" /></td>
												
										<td class="col-md-1" ><c:out
												value="${creditNoteDetail.expireQty}" /></td>
										<td class="col-md-1" ><c:out
												value="${creditNoteDetail.rate}" /></td>
											<td class="col-md-1" ><c:out
												value="${(creditNoteDetail.leakageQty+creditNoteDetail.expireQty)*creditNoteDetail.rate}" /></td>
									</tr>
								</c:forEach>
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


 

</body>
</html>
