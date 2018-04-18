

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

<!--topLeft-nav-->
<div class="sidebarOuter"></div>
<!--topLeft-nav-->

<!--wrapper-start-->
<div class="wrapper">

	<jsp:include page="/WEB-INF/views/include/logo.jsp"></jsp:include>

	<!--rightContainer-->
	<div class="fullGrid center">
		<!--fullGrid-->
		<div class="wrapperIn2">

			<!--leftNav-->

			<jsp:include page="/WEB-INF/views/include/left.jsp">
				<jsp:param name="myMenu" value="${menuList}" />
			</jsp:include>


		 
			<div class="sidebarright">
				
						<div class="col-md -3">
							
								<div class="col1title" align="left"><h3>Item List</h3></div>
								<div class="col1title" align="right"> 
						<a href="${pageContext.request.contextPath}/addItem"><input type="button" value="Add New Item" class="btn btn-info">
										</a>
								 </div>
						</div>
						  
					
					 <div id="table-scroll" class="table-scroll">
									<div id="faux-table" class="faux-table" aria="hidden"></div>
									<div class="table-wrap table-wrap-custbill">
										<table id="table_grid1" class="main-table small-td">
											<thead>
												<tr class="bgpink">
													<th class="col-sm-1">Sr no.</th>
													<th class="col-md-1">Item Name</th> 
													<th class="col-md-1">HSN Code</th>
													<th class="col-md-1">UOM</th> 
													<th class="col-md-1">Category</th> 
													<th class="col-md-1">Purchase Rate</th>
													<th class="col-md-1">Min Qty</th>
													<th class="col-md-1">Max Qty</th>
													<th class="col-md-1">Hub Expiry Days</th>
													<th class="col-md-1">Retail Expiry Days</th>
													<th class="col-md-1">Action</th>
												</tr>
											</thead>
											<tbody>
											
											<c:forEach items="${itemList}" var="item"
									varStatus="count">
									<tr>
										 <td class="col-md-1"><c:out value="${count.index+1}" /></td>
										   
										<td class="col-md-1"><c:out value="${item.itemName}" /></td>		 
										<td class="col-md-1"><c:out value="${item.hsnCode}" /></td>	
										<td class="col-md-1"><c:out value="${item.uomName}" /></td>	
										<td class="col-md-1"><c:out value="${item.catName}" /></td>	
										<td class="col-md-1"><c:out value="${item.purchaseRate}" /></td>	
										<td class="col-md-1"><c:out value="${item.minQty}" /></td>	
										<td class="col-md-1"><c:out value="${item.maxQty}" /></td>	
										<td class="col-md-1"><c:out value="${item.hubExpDays}" /></td>	
										<td class="col-md-1"><c:out value="${item.retailExpDays}" /></td>	
									 <td><a href="${pageContext.request.contextPath}/editItem/${item.itemId}"><abbr title="Edit"><i   class="fa fa-edit"></i> </abbr></a>
									 <a href="${pageContext.request.contextPath}/deleteItem/${item.itemId}"><abbr title="Delete"><i  class="fa fa-trash"></i></abbr></a>
									 </td>
										 
									</tr>
								</c:forEach>

											</tbody>

										</table>
									</div>
								</div>


				 
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


</body>
</html>
