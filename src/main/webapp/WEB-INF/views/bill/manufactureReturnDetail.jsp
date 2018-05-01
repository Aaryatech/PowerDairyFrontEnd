

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

					<div class="order-left"> 
								<h2 class="pageTitle"> Manufacture Return Detail  </h2> 
								  
						</div>
						 
						
						 <div class="col1title" align="right"> 
						<a href="${pageContext.request.contextPath}/manufactureReturnHistory"><input type="button" value="Return History" class="btn btn-info">
										</a>
					</div>
					
					<div class="colOuter">
						</div>
						
					<div class="colOuter">
						 

						<div class="col-md-2">
							<div class="col1title" align="left">Date: </div>
						</div>
						<div class="col-md-2" align="left">
							${mfgReturnHeader.date}

						</div>
						
						<div class="col-md-1"> </div>
						
						<div class="col-md-2" >
							<div class="col1title" align="left">Creates In Qty: </div>
						</div>
						<div class="col-md-2" align="left">
							${mfgReturnHeader.cratesReturnQty}

						</div>
					 
					</div>
					
					 
					
					<div class="colOuter">
						 

						<div class="col-md-2">
							<div class="col1title" align="left">Remark: </div>
						</div>
						<div class="col-md-2" align="left">
							${mfgReturnHeader.remark}
						</div>
						
						<div class="col-md-1"> </div>
						
						<div class="col-md-2">
							<div class="col1title" align="left">Return By: </div>
						</div>
						<div class="col-md-2" align="left">
							${mfgReturnHeader.userName}
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
													<th class="col-md-1">Return Qty</th>  
												</tr>
								 
							</thead>
							<tbody>

								<c:forEach items="${mfgReturnDetailList}" var="mfgReturnDetailList"
									varStatus="count">
									<tr>
										 <td class="col-sm-1"><c:out value="${count.index+1}" /></td>
										  
										  <td class="col-md-1"><c:out
												value="${mfgReturnDetailList.batchNo}" /></td>
												 
										<td class="col-md-1"><c:out
												value="${mfgReturnDetailList.itemName}" /></td>
												   
										<td class="col-md-1" ><c:out
												value="${mfgReturnDetailList.itemReturnQty}" /></td>
										 
										 
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
