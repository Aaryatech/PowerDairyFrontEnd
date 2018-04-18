

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
					action="${pageContext.request.contextPath}/insertItem">
					<input type="hidden" name="mod_ser" id="mod_ser"
						value="search_result">

					
						<div class="col-md -3">
							
								<div class="col1title" align="left"><h3>Add Item</h3></div>
								 
						</div>
						
					<div class="colOuter">
						<div class="col-md-2">
							<div class="col1title" align="left">Item Name*: </div>
						</div>
						<div class="col-md-3">
							<input id="itemName" style="text-align: left;" class="form-control"
								placeholder="Item Name" name="itemName" type="text" value="${item.itemName}" required>
								<input id="itemId" class="form-control"
								  name="itemId"  type="hidden" value="${item.itemId}">

						</div>
						<div class="col-md-1"> </div>
						
						<div class="col-md-2">
							<div class="col1title" align="left">Select Category*: </div>
						</div>
						<div class="col-md-3">
							<select class="selectpicker" data-live-search="true" title="Select Category" 
							name="catId" id="catId" required>
							<c:forEach items="${itemCategoryList}" var="cat">
							 <c:choose>
							 <c:when test="${cat.catId==item.itemCatId}">
							 <option value="${cat.catId}" selected>${cat.catName}</option>
							 </c:when>
							 <c:otherwise>
							  <option value="${cat.catId}">${cat.catName}</option>
							 </c:otherwise>
							 </c:choose>
							</c:forEach>
							 </select>

						</div>
					</div>
					
					<div class="colOuter">
						
						<div class="col-md-2">
							<div class="col1title" align="left">Item Code*: </div>
						</div>
						<div class="col-md-3">
							<input id="itemCode" style="text-align: left;" class="form-control"
								placeholder="Item Code" name="itemCode" type="text" value="${item.itemCode}" required>

						</div>

						
						<div class="col-md-1"> </div>
						
						<div class="col-md-2">
							<div class="col1title" align="left"> HSN Code*: </div>
						</div>
						<div class="col-md-3">
							<input id="hsnCode" style="text-align: left;" class="form-control"
								placeholder="HSN Code" name="hsnCode" type="text" value="${item.hsnCode}" required>

						</div>
						
				 
					</div>
					
					<div class="colOuter">
					
					<div class="col-md-2">
							<div class="col1title" align="left">Minimum Stock Qty*: </div>
						</div>
						<div class="col-md-3">
							<input id="min_qty" class="form-control" style="text-align: left;"
								placeholder="Minimum Stock Qty" pattern="[+-]?([0-9]*[.])?[0-9]+" name="min_qty" type="text" value="${item.minQty}" required>

						</div>
						<div class="col-md-1"> </div>
						<div class="col-md-2">
							<div class="col1title" align="left">Maximum Stock Qty*: </div>
						</div>
						<div class="col-md-3">
							<input id="max_qty" class="form-control" style="text-align: left;"
								placeholder="Maximum Stock Qty" pattern="[+-]?([0-9]*[.])?[0-9]+" name="max_qty" type="text" value="${item.maxQty}" required>

						</div>
						 
				 
					</div>
					
					<div class="colOuter">
					<div class="col-md-2">
							<div class="col1title" align="left">Hub Expiry Days*: </div>
						</div>
						<div class="col-md-3">
							<input id="hubExprDays" style="text-align: left;" class="form-control"
								placeholder="Hub Expiry Days" name="hubExprDays"  value="${item.hubExpDays}" type="number" required>

						</div>
						<div class="col-md-1"> </div>
						<div class="col-md-2">
							<div class="col1title" align="left">Retail Expiry Days*: </div>
						</div>
						<div class="col-md-3">
							<input id="retailExprDays" style="text-align: left;" class="form-control"
								placeholder="Retail Expiry Days" name="retailExprDays"   value="${item.retailExpDays}"  type="number" required>

						</div>
						
 
				 
					</div>
					
					<div class="colOuter">
					
					<div class="col-md-2">
							<div class="col1title" align="left">SGST %*: </div>
						</div>
						<div class="col-md-3">
							<input id="sgst" class="form-control" style="text-align: left;"
								placeholder="SGST" pattern="[+-]?([0-9]*[.])?[0-9]+" name="sgst" value="${item.sgstPer}" type="text" required>

						</div>
						<div class="col-md-1"> </div>
						<div class="col-md-2">
							<div class="col1title" align="left">CGST %*: </div>
						</div>
						<div class="col-md-3">
							<input id="cgst" class="form-control" style="text-align: left;"
								placeholder="CGST" pattern="[+-]?([0-9]*[.])?[0-9]+" name="cgst" type="text" value="${item.cgstPer}" required>

						</div>
						 
				 
					</div>
					
					<div class="colOuter">
						<div class="col-md-2">
							<div class="col1title" align="left">IGST %*: </div>
						</div>
						<div class="col-md-3">
							<input id="igst" class="form-control"
								placeholder="IGST" style="text-align: left;" pattern="[+-]?([0-9]*[.])?[0-9]+" name="igst" value="${item.igstPer}" type="text" required>

						</div>
						<div class="col-md-1"> </div>

						<div class="col-md-2">
							<div class="col1title" align="left">Select UOM*: </div>
						</div>
						<div class="col-md-3">
							<select class="selectpicker" data-live-search="true" title="Please Select" 
							name="uomId" id="uomId" required>
						 	<c:forEach items="${uomList}" var="uom">
							 <c:choose>
							 <c:when test="${uom.uomId==item.uomId}">
							 <option value="${uom.uomId}" selected>${uom.uomName}</option>
							 </c:when>
							 <c:otherwise>
							  <option value="${uom.uomId}">${uom.uomName}</option>
							 </c:otherwise>
							 </c:choose>
							</c:forEach>
						 </select>

						</div>
				 
					</div>
					
					<div class="colOuter">
						<div class="col-md-2">
							<div class="col1title" align="left">Purchase Rate*: </div>
						</div>
						<div class="col-md-3">
							<input id="purchaseRate" class="form-control"
								placeholder="Purchase Rate" style="text-align: left;" pattern="[+-]?([0-9]*[.])?[0-9]+" name="purchaseRate" type="text" value="${item.purchaseRate}" required>

						</div>
						<div class="col-md-1"> </div>

						 
				 
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
