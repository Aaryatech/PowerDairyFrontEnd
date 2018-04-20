

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

 

</head>
<body>

<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
<!--datepicker-->


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
				 
				<form name="save_rs" id="save_rs" method="post"
					action="${pageContext.request.contextPath}/saveRsDetails">
				
						<div class="col-md -3">
							
								<div class="col1title" align="left"><h3>Edit Rate</h3></div>
								 
						</div>
			
						<div class="colOuter">
						<div class="col-md-2">
							<div class="col1title" align="left">Rate Structure Name*: </div>
						</div>
						<div class="col-md-3">
						<input type="hidden" name="rsHeaderId" id="rsHeaderId" value="${rsHeaderRes.rsHeaderId}"/>
							<input id="rsName" class="form-control"
								placeholder="Rate Structure Name" name="rsName" style="text-align: left;"  type="text" value="${rsHeaderRes.rsName}" readonly>

						</div>
					 
					</div>
					 <div id="table-scroll" class="table-scroll">
					<div class="table-wrap">
						<table id="table_grid" class="main-table" border="1">

							<thead>
								<tr class="bgpink">
								
									<th class="col-sm-1">Sr No</th>
									<th class="col-md-1">Item Name</th> 
									<th class="col-md-1">Rate</th>
								
								</tr>
							</thead>
							<tbody>
                                <c:forEach items="${rsDetailsList}" var="rsDetailsList"> 
                               
								<c:forEach items="${itemList}" var="itemList"
									varStatus="count">
									 <c:choose>
                                <c:when test="${itemList.itemId==rsDetailsList.itemId}">
									<tr>
										 <td class="col-sm-1"><c:out value="${count.index+1}" /></td>
										
										<td class="col-md-3"><c:out
												value="${itemList.itemName}" /></td>
	                                    <td class="col-md-1"style="text-align: center;"><input type="number" style="text-align: right;" name="rate${itemList.itemId}" value="${rsDetailsList.rate}" id="rate${itemList.itemId}"/></td>
									</tr>
									</c:when>
                                </c:choose>
								</c:forEach>
								   
								 </c:forEach>
						</table>

					</div>
				</div><br>
	             <div class="colOuter">
						<div align="center">
							<input name="submit" class="buttonsaveorder" value="SAVE"
								type="submit" align="center">
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
