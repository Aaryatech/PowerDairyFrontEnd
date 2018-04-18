

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
					 
					</div>
					<div id="table-scroll" class="table-scroll">
									<div id="faux-table" class="faux-table" aria="hidden"></div>
									<div class="table-wrap table-wrap-custbill">
										<table id="table_grid1" class="main-table small-td">
											<thead>
												<tr class="bgpink">
								
									<th class="col-sm-1">Sr No</th>
									<th class="col-md-1">Item Name</th> 
									<th class="col-md-1">Opening Stock</th>
									<th class="col-md-1">Total Purchase</th>
									<th class="col-md-1">Total Sell</th>
									<th class="col-md-1">Total Return</th>
									<th class="col-md-1">Hub Return Qty</th>
									<th class="col-md-1">Closing Qty</th>
								
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
								placeholder="Qty"   name="closingQty${currentStockList.itemId}" value="${currentStockList.openingStock+currentStockList.totalPurchase-currentStockList.totalSale+currentStockList.returnQty-currentStockList.hubReturnQty}"   style="text-align: left;"  type="number"  required="required">		
	                                   
									</tr>
								</c:forEach>

											</tbody>

										</table>
									</div>
								</div>
					  
	             <div class="colOuter">
						<div align="center">
						<c:choose>
							<c:when test="${stockDate==tommorowDate}">
							<input name="submit" class="buttonsaveorder" value="Day End"
								type="submit" align="center"  disabled>
							</c:when>
							<c:otherwise>
							<input name="submit" class="buttonsaveorder" value="Day End"
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

 

</body>
</html>
