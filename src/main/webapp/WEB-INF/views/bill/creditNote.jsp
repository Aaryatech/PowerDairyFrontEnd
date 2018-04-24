

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

 

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
					action="${pageContext.request.contextPath}/saveCreditNote">
				<input type="hidden" name="billTempId" id="billTempId"
						value="${billHeader.billTempId}">
						<input type="hidden" name="custId" id="custId"
						value="${billHeader.custId}">
						<div class="col-md -3">
							
								<div class="col1title" align="left"><h3> Credit Note </h3></div>
								 
						</div>
						
					<div class="colOuter">
						<div class="col-md-2">
							<div class="col1title" align="left"> Customer Name : </div>
						</div>
						<div class="col-md-3">
							${billHeader.custName}

						</div>
						
						<div class="col-md-1">
							 
						</div>

						<div class="col-md-2">
							<div class="col1title" align="left">Bill No : </div>
						</div>
						<div class="col-md-3">
							<input id="creditNo" class="form-control"
								placeholder="Bill No" name="billId"  style="text-align: left;" type="text" value="${billHeader.billId}" required readonly>

						</div>
					 
					</div> 
					
					<div class="colOuter">
						<div class="col-md-2">
							<div class="col1title" align="left">Credit No : </div>
						</div>
						<div class="col-md-3">
							<input id="creditNo" class="form-control"
								placeholder="Credit No" name="creditNo" style="text-align: left;"  type="number" value="${crnNo}" readonly>

						</div>
						
						<div class="col-md-1"> 
						</div>
 
					</div> 
					
					 <div class="colOuter"> 
					</div>
					 <div class="colOuter"> 
					</div>
					 
					 
					 <div class="colOuter"> 
					</div>
						                    <c:set var="totalSum" value=""/>
											<c:set var="taxTotal" value=""/>
											<c:set var="grandTotal" value=""/>
					<div id="table-scroll" class="table-scroll">
									
									<div class="table-wrap table-wrap-custbill">
										<table id="table_grid1" class="main-table small-td">
											<thead>
												<tr class="bgpink">
													<th class="col-sm-1">Sr no.</th>
													<th class="col-md-1">Batch No</th>
													<th class="col-md-2">Item Name</th>
													<th class="col-md-1">Bill Qty</th>   
													<th class="col-md-1">Expire Qty </th>
													<th class="col-md-1">Leakage Qty</th>
													<th class="col-md-1">Rate</th>
													<th class="col-md-1">Amount</th>
													<th class="col-md-1">Tax %</th>
													<th class="col-md-1">Tax Amt</th>
													<th class="col-md-1">Bill Total</th>
												
												</tr>
											</thead>
											<tbody>
										
                                     <c:forEach items="${billDetails}" var="billDetail" varStatus="count">
                                      <input type="hidden" name="keySize" id="keySize" value="${keySize}"/>
                                                  
                                                   <input type="hidden" name="billQty${count.index}" id="billQty${count.index}" value="${billDetail.billQty-(billDetail.returnQty+billDetail.distLeakageQty)}"/>
												
												<input type="hidden" name="cgstPer${count.index}" id="cgstPer${count.index}" value="${billDetail.cgstPer}"/>
												<input type="hidden" name="rate${count.index}" id="rate${count.index}" value="${billDetail.rate}"/>
												<input type="hidden" name="sgstPer${count.index}" id="sgstPer${count.index}"value="${billDetail.sgstPer}"/>
                                                  <tr>
                                                  
												<td class="col-md-1"><c:out value="${count.index+1}" /></td> 
												<td class="col-md-1"><c:out value="${billDetail.batchNo}" /></td> 
												<td class="col-md-1"><c:out value="${billDetail.itemName}" /></td> 
												<td class="col-md-1"><c:out value="${billDetail.billQty-(billDetail.returnQty+billDetail.distLeakageQty)}" /></td> 
												<td class="col-md-1"><input type="text" min="0" max="500"
																			class="form-control" name="expireQty${count.index}" id="expireQty${count.index}" value="0"
																			onblur="onExpireQty(this.value,${count.index})"
																			oninput="validity.valid||(value='');"></td>
												<td class="col-md-1"><input type="text" min="0" max="500"
																			class="form-control" name="leakageQty${count.index}" id="leakageQty${count.index}" value="0"
																			onblur="onLeakageQty(this.value,${count.index})"
																			oninput="validity.valid||(value='');"></td>
												 <c:set var="taxPer"   value="${billDetail.cgstPer+billDetail.sgstPer}"/>
												 	<input type="hidden" name="taxAmtVar${count.index}" id="taxAmtVar${count.index}" value="0"/>
													<input type="hidden" name="taxPerVar${count.index}" id="taxPerVar${count.index}" value="0"/>
													<input type="hidden" name="totalTaxVar${count.index}" id="totalTaxVar${count.index}" value="0"/>
													<input type="hidden" name="grandAmtVar${count.index}" id="grandAmtVar${count.index}" value="0"/>
												
												 <c:set var="baseRate"   value="${(billDetail.rate*100)/(100 + taxPer)}"/>
												<td class="col-md-1" id="baseRate${count.index}" ><fmt:formatNumber type = "number" minFractionDigits = "2" maxFractionDigits = "2" value = "${baseRate}"/></td>
												
												<td id="taxAmt${count.index}" >0</td>
												<td id="taxPer${count.index}" >0</td>
											    <td id="totalTax${count.index}" >0</td>
												<td id="grandAmt${count.index}" >0</td>
												</tr>
                                                 
                                                  </c:forEach>
											</tbody>

										</table>
									</div>
								</div>
					  <div class="row">
									<div class="col-md-4">
										<h4 class="col-md-7">
											<b>Amount:-</b>
										</h4>
										<h4 class="col-md-5" id="totalSum">0</h4>
										<input type="hidden" class="form-control" id="totalSumText" name="totalSumText">
									</div>

									<div class="col-md-4">
										<h4 class="col-md-7" style="margin-top: 5px">
											<b>Tax Total:-</b>
										</h4>

										<h4 class="col-md-5" id="taxTotal">0</h4>
										<input type="hidden" class="form-control" id="taxTotalText" name="taxTotalText">
									</div>

									<div class="col-md-4">
										<h4 class="col-md-7" style="margin-top: 5px">
											<b>Grand Total:-</b>
										</h4>

										<h4 class="col-md-5" id="grandTotal">0</h4>
										<input type="hidden" class="form-control" id="grandTotalText" name="grandTotalText">
									</div></div>  
							
				  <div class="row"><div class="col-md-2"><b>Remark</b></div>
							<div class="col-md-3">									
						<input id="remark" class="form-control"
								placeholder="Remark" name="remark"  style="text-align: left;" type="text"  required>
							</div>		
								 
						</div>
						 
					<div class="colOuter">
						<div align="center">
							<input name="submit" class="buttonsaveorder" value="Submit"
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



<script type="text/javascript">
function onExpireQty(qty,key)
{
	var billQty=parseInt($("#billQty"+key).val());
	var expireQty=parseInt($("#expireQty"+key).val());
	var leakageQty=parseInt($("#leakageQty"+key).val());
	if((expireQty+leakageQty)<=billQty)
		{
	var keysize=$("#keySize").val();
	var rate=parseFloat($("#rate"+key).val());
	var cgstPer=parseInt($("#cgstPer"+key).val());
	var sgstPer=parseInt($("#sgstPer"+key).val());
	
	
	var actQty=((expireQty+leakageQty));
	var taxPer=(cgstPer+sgstPer);
  	var baseRate=(rate*100)/(100+taxPer);
  	var taxableAmt=(baseRate*actQty);
  	var cgstRs=(taxableAmt*cgstPer)/100;
	var sgstRs=(taxableAmt*sgstPer)/100;
	var totalTax=cgstRs+sgstRs;
	var grandAmt=totalTax+taxableAmt;
	$('#baseRate'+key).html(baseRate.toFixed(2));
	$('#taxAmt'+key).html(taxableAmt.toFixed(2));
	$('#taxPer'+key).html(taxPer.toFixed(2));
	$('#totalTax'+key).html(totalTax.toFixed(2));
	$('#grandAmt'+key).html(grandAmt.toFixed(2)); 
	
	$('#taxAmtVar'+key).val(taxableAmt.toFixed(2));
	$('#taxPerVar'+key).val(taxPer.toFixed(2));
	$('#totalTaxVar'+key).val(totalTax.toFixed(2));
	$('#grandAmtVar'+key).val(grandAmt.toFixed(2)); 
	
	var totSum=0;var taxTot=0;var grandTotal=0;
	for(var i=0;i<keysize;i++)
		{
		var totAmt=parseFloat($("#taxAmtVar"+i).val());
		var totTax=parseFloat($("#totalTaxVar"+i).val());
		var grandTot=parseFloat($("#grandAmtVar"+i).val());
		totSum=totSum+totAmt;
		taxTot=taxTot+totTax;
		grandTotal=grandTotal+grandTot;
		}
	
	$('#totalSum').html(totSum.toFixed(2)); 
	document.getElementById("totalSumText").value=(totSum).toFixed(2);
	
	$('#taxTotal').html(taxTot.toFixed(2)); 
	document.getElementById("taxTotalText").value=(taxTot).toFixed(2);
	
	$('#grandTotal').html(grandTotal.toFixed(2));
	document.getElementById("grandTotalText").value=(grandTotal).toFixed(2);
		}
	else
		{
		  alert("Your entered Qty is Greator than Available Qty");
		  document.getElementById("expireQty"+key).value=0;
		
		}
}
function onLeakageQty(qty,key)
{
	
	var billQty=parseInt($("#billQty"+key).val());
	var expireQty=parseInt($("#expireQty"+key).val());
	var leakageQty=parseInt($("#leakageQty"+key).val());
	if((expireQty+leakageQty)<=billQty)
	{
	var keysize=$("#keySize").val();

	var rate=parseFloat($("#rate"+key).val());
	var cgstPer=parseInt($("#cgstPer"+key).val());
	var sgstPer=parseInt($("#sgstPer"+key).val());
	
	
	var actQty=((expireQty+leakageQty));
	var taxPer=(cgstPer+sgstPer);
  	var baseRate=(rate*100)/(100+taxPer);
  	var taxableAmt=(baseRate*actQty);
  	var cgstRs=(taxableAmt*cgstPer)/100;
	var sgstRs=(taxableAmt*sgstPer)/100;
	var totalTax=cgstRs+sgstRs;
	var grandAmt=totalTax+taxableAmt;
	$('#baseRate'+key).html(baseRate.toFixed(2));
	$('#taxAmt'+key).html(taxableAmt.toFixed(2));
	$('#taxPer'+key).html(taxPer.toFixed(2));
	$('#totalTax'+key).html(totalTax.toFixed(2));
	$('#grandAmt'+key).html(grandAmt.toFixed(2)); 
	
	$('#taxAmtVar'+key).val(taxableAmt.toFixed(2));
	$('#taxPerVar'+key).val(taxPer.toFixed(2));
	$('#totalTaxVar'+key).val(totalTax.toFixed(2));
	$('#grandAmtVar'+key).val(grandAmt.toFixed(2)); 
	
 	var totSum=0;var taxTot=0;var grandTotal=0;
	for(var i=0;i<keysize;i++)
		{
		var totAmt=parseFloat($("#taxAmtVar"+i).val());
		var totTax=parseFloat($("#totalTaxVar"+i).val());
		var grandTot=parseFloat($("#grandAmtVar"+i).val());
		totSum=totSum+totAmt;
		taxTot=taxTot+totTax;
		grandTotal=grandTotal+grandTot;
		}
	
	$('#totalSum').html(totSum.toFixed(2)); 
	document.getElementById("totalSumText").value=(totSum).toFixed(2);
	
	$('#taxTotal').html(taxTot.toFixed(2)); 
	document.getElementById("taxTotalText").value=(taxTot).toFixed(2);
	
	$('#grandTotal').html(grandTotal.toFixed(2));
	document.getElementById("grandTotalText").value=(grandTotal).toFixed(2);
	
	}
	else
		{
		  alert("Your entered Qty is Greator than Available Qty");
		  document.getElementById("leakageQty"+key).value=0;
		
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
