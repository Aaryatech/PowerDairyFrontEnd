

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
					action="${pageContext.request.contextPath}/insertUser">
				
						<div class="col-md -3">
							
								<div class="col1title" align="left"><h3>Add User</h3></div>
								 
						</div>
						
					<div class="colOuter">
						<div class="col-md-2">
							<div class="col1title" align="left">User Name*: </div>
						</div>
						<div class="col-md-3">
							<input id="userName" class="form-control"
								placeholder="Item Name" style="text-align: left;" name="userName" type="text" value="${user.userName}" required>
								<input id="userId" class="form-control"
								  name="userId"  type="hidden" value="${user.userId}" >

						</div>
						 </div>
						 
						 <div class="colOuter">
						<div class="col-md-2">
							<div class="col1title" align="left">Password*: </div>
						</div>
						<div class="col-md-3">
							<input id="password" class="form-control"
								placeholder="Password" style="text-align: left;" onchange="checkPassWord()" name="password" type="password" value="${user.password}" required>
								 

						</div>
						 </div>
						 
						  <div class="colOuter">
						<div class="col-md-2">
							<div class="col1title" align="left">Re Enter Password*: </div>
						</div>
						<div class="col-md-3">
							<input id="RePassword" class="form-control"
								placeholder="Password" style="text-align: left;" onchange="checkPassWord()" name="RePassword" type="password"   required>
								 

						</div>
						 </div>
						 
					<div class="colOuter">
						<div class="col-md-2">
							<div class="col1title" align="left">Mobile No*: </div>
						</div>
						<div class="col-md-3">
							<input id="mob" class="form-control"
								placeholder="Mobile No" name="mob" style="text-align: left;" pattern="^\d{10}$" type="text" value="${user.userMobNo}" required>

						</div>
					 
					</div>
					
					<div class="colOuter">
						 

						<div class="col-md-2">
							<div class="col1title" align="left">User Type*: </div>
						</div>
						<div class="col-md-3">
							<select class="selectpicker" data-live-search="true" title="Please Select" 
							name="typeId" id="typeId" required>
							<option value="">Select Option</option>
							<c:forEach items="${userTypeList}" var="userTypeList">
							<c:choose>
							<c:when test="${userTypeList.userTypeId==user.userTypeId}">
							<option value="${userTypeList.userTypeId}" selected>${userTypeList.userType}</option>
							</c:when>
							<c:otherwise>
						    <option value="${userTypeList.userTypeId}">${userTypeList.userType}</option>
							</c:otherwise></c:choose>
							</c:forEach>
						 </select>

						</div>
						<div class="col-md-1"> </div>
						 
					</div>
					<div class="colOuter">
						<div align="center">
							<input name="submit" class="buttonsaveorder" value="Submit"
								type="submit"  id="submit" align="center" disabled>
								<!-- <input type="button" class="buttonsaveorder" value="Cancel" id="cancel" onclick="cancel1()" disabled> -->
						</div>
				 
					</div>
					
					 <div id="table-scroll" class="table-scroll">
					<div id="faux-table" class="faux-table" aria="hidden"></div>
					<div class="table-wrap">
						<table id="table_grid" class="main-table">

							<thead>
								<tr class="bgpink">
								
									<th class="col-sm-1">Sr No</th>
									<th class="col-md-1">User Name</th> 
									<th class="col-md-1">Mobile</th>
									<th class="col-md-1">User Type</th>
									<th class="col-md-1">Action</th>
								</tr>
							</thead>
							<tbody>

								<c:forEach items="${userList}" var="userList"
									varStatus="count">
									<tr>
										 <td class="col-sm-1"><c:out value="${count.index+1}" /></td>
										<td class="col-md-1"><c:out
												value="${userList.userName}" /></td>
										<td class="col-md-1"><c:out
												value="${userList.userMobNo}" /></td>
										<td class="col-md-1"><c:out
												value="${userList.userType}" /></td>
										<td class="col-md-1"><div >
						<a href="${pageContext.request.contextPath}/editUser/${userList.userId}"><abbr title='Edit'><i class='fa fa-edit'></i> </abbr></a>
						<a href="${pageContext.request.contextPath}/deleteUser/${userList.userId}" onClick="return confirm('Are you sure want to delete this record');"  >
						<abbr title='Delete'><i  class='fa fa-trash'></i></abbr></a>
												 
											</div></td>
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


<script>
 

function checkPassWord() {

    //alert("cancel");
	var password = document.getElementById("password").value ;
	var RePassword = document.getElementById("RePassword").value ; 
	
	if(password!="" && RePassword!="")
		{
			if(password==RePassword)
				{
				document.getElementById("submit").disabled=false;
				}
			else
				{
				alert("Password is Not Match "); 
				document.getElementById("submit").disabled=true;
				}
		}

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
