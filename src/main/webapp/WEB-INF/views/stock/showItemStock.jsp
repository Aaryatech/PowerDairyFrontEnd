

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
					action="${pageContext.request.contextPath}/submitStock">
				
						<div class="col-md -3">
							
								<div class="col1title" align="left"><h3>Insert Stock</h3></div>
								 
						</div>
			
						<div class="colOuter">
						<div class="col-md-2">
							<div class="col1title" align="left">Stock Date: </div>
						</div>
						<div class="col-md-3">
							<input id="date" class="form-control"
								placeholder="Stock Date" value="${date}" name="date"  style="text-align: left;"  type="text" readonly="readonly">

						</div>
						<div class="col-md-1"></div>
						<div class="col-md-2">
							<div class="col1title" align="left">Crates Opening: </div>
						</div>
						<div class="col-md-3">
							<input id="cratesOpn" class="form-control"
								placeholder="cratesOpn" value="${cratesOpn}" name="cratesOpn"  style="text-align: left;"  type="text"  required="required">

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
									<th class="col-md-1">Rate</th>
								
								</tr>
											</thead>
											<tbody>
											<c:forEach items="${stockDetailList}" var="stockDetailList"
									varStatus="count">
									<tr>
										 <td class="col-sm-1"><c:out value="${count.index+1}" /></td>
										
										<c:forEach items="${itemList}" var="itemList" >
										<c:choose>
											<c:when test="${stockDetailList.itemId==itemList.itemId}">
											<td class="col-md-3"><c:out value="${itemList.itemName}" /></td>
											</c:when>
										</c:choose>
										
												</c:forEach>
												
	                                    <td class="col-md-1" ><input id="qty${count.index}" class="form-control"
								placeholder="Qty"   name="qty${count.index}" value="${stockDetailList.closingQty}"   style="text-align: left;"  type="number"  required="required">
									</tr>
								</c:forEach>

											</tbody>

										</table>
									</div>
								</div>
					  
	             <div class="colOuter">
						<div align="center">
							<input name="submit" class="buttonsaveorder" value="Submit"
								type="submit" align="center"  >
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
