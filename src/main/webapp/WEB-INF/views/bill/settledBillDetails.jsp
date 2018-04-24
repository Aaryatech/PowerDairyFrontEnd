

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<style type="text/css">
.table-scroll table {
    width:30%;
    /* margin: auto; */
    border-collapse: separate;
    border-spacing: 0;
}
</style>
 

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
					action="${pageContext.request.contextPath}/approveBill">
					<input type="hidden" name="billTempId" id="billTempId"
						value="${billHeader.billTempId}">

					
						<div class="col-md -3">
							
								<div class="col1title" align="left"><h3> Settled Bill</h3></div>
								 
						</div>
						
					<div class="colOuter">
						<div class="col-md-3">
							<div class="col1title" align="left"> Customer  : ${billHeader.custName} </div>
							
						</div>
						  
						<div class="col-md-1">
							 
						</div>

						<div class="col-md-3">
							<div class="col1title" align="left"> Vehicle No.:  ${billHeader.vehName} </div>
							
						</div>
						 
					 
						<div class="col-md-2">
							<div class="col1title" align="left"> Bill Date : ${billHeader.billDate}</div>
						</div>
						<div class="col-md-2">
							 

						</div>
					</div>
					   
					 <div class="colOuter"> 
					</div>
				                         	<c:set var="totalSum" value=""/>
											<c:set var="taxTotal" value=""/>
											<c:set var="grandTotal" value=""/>
					<div id="table-scroll" class="table-scroll">
									<div id="faux-table" class="faux-table" aria="hidden"></div>
									<div class="table-wrap table-wrap-custbill">
										<table id="table_grid1" class="main-table small-td">
											<thead>
												<tr class="bgpink">
													<th class="col-sm-1">Sr no.</th>
													<th class="col-md-1">Batch No</th>
													<th class="col-md-2">Item Name</th>
													<th class="col-md-1">Issue Qty</th> 
													<th class="col-md-1">Return Qty</th>  
													<th class="col-md-1">Leakage Qty</th> 
													<th class="col-md-1">Rate </th> 
													<th class="col-md-1">Amount</th>
													<th class="col-md-1">Tax%</th>
													<th class="col-md-1">Tax Amt</th>
													<th class="col-md-1">Total</th> 
												</tr>
											</thead>
											<tbody>
											
                                                  <c:forEach items="${billDetails}" var="billDetail" varStatus="count">
                                            <input type="hidden" name="keySize" id="keySize" value="${keySize}"/>
                                                  
                                                   <input type="hidden" name="billQty${count.index}" id="billQty${count.index}" value="${billDetail.billQty}"/>
												
												<input type="hidden" name="cgstPer${count.index}" id="cgstPer${count.index}" value="${billDetail.cgstPer}"/>
												<input type="hidden" name="rate${count.index}" id="rate${count.index}" value="${billDetail.rate}"/>
												<input type="hidden" name="sgstPer${count.index}" id="sgstPer${count.index}"value="${billDetail.sgstPer}"/>
                                                  <tr>
                                                  
												<td class="col-md-1"><c:out value="${count.index+1}" /></td> 
												<td class="col-md-1"><c:out value="${billDetail.batchNo}" /></td> 
												<td class="col-md-1"><c:out value="${billDetail.itemName}" /></td> 
												<td class="col-md-1"><c:out value="${billDetail.billQty}" /></td> 
												<td class="col-md-1"><c:out value="${billDetail.returnQty}" /></td>
												<td class="col-md-1"><c:out value="${billDetail.distLeakageQty}" /></td>
												 <c:set var="taxPer"   value="${billDetail.cgstPer+billDetail.sgstPer}"/>
												 
												 <c:set var="baseRate"   value="${(billDetail.rate*100)/(100 + taxPer)}"/>
												<td class="col-md-1" id="baseRate${count.index}" ><fmt:formatNumber type = "number" minFractionDigits = "2" maxFractionDigits = "2" value = "${baseRate}"/></td>
												 <c:set var="taxableAmt"   value="${baseRate*(billDetail.billQty-(billDetail.returnQty+billDetail.distLeakageQty))}"/>
												 	 <c:set var="cgstRs"   value="${(taxableAmt*billDetail.cgstPer)/100}"/>
												 	 <c:set var="sgstRs"   value="${(taxableAmt*billDetail.sgstPer)/100}"/>
												 	 <c:set var="totalTax"   value="${(cgstRs+sgstRs)}"/>
												
													<c:set var="totalSum" value="${totalSum+taxableAmt}"/>
													<c:set var="taxTotal" value="${taxTotal+totalTax}"/>
													<c:set var="tota" value="${taxableAmt+totalTax}"/>
													<c:set var="grandTotal" value="${grandTotal+tota}"/>
													<input type="hidden" name="taxAmtVar${count.index}" id="taxAmtVar${count.index}" value="${taxableAmt}"/>
													<input type="hidden" name="taxPerVar${count.index}" id="taxPerVar${count.index}" value="${taxPer}"/>
													<input type="hidden" name="totalTaxVar${count.index}" id="totalTaxVar${count.index}" value="${totalTax}"/>
													<input type="hidden" name="grandAmtVar${count.index}" id="grandAmtVar${count.index}" value="${taxableAmt+totalTax}"/>
												<td id="taxAmt${count.index}" ><fmt:formatNumber type = "number" minFractionDigits = "2" maxFractionDigits = "2" value = "${taxableAmt}"/></td>
												<td id="taxPer${count.index}" ><fmt:formatNumber type = "number" minFractionDigits = "2" maxFractionDigits = "2" value = "${taxPer}"/></td>
											    <td id="totalTax${count.index}" ><fmt:formatNumber type = "number" minFractionDigits = "2" maxFractionDigits = "2" value = "${totalTax}"/></td>
												<td id="grandAmt${count.index}" ><fmt:formatNumber type = "number" minFractionDigits = "2" maxFractionDigits = "2" value = "${taxableAmt+totalTax}"/></td>
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
										<h4 class="col-md-5" id="totalSum"><fmt:formatNumber type = "number" minFractionDigits = "2" maxFractionDigits = "2" value = "${totalSum}"/></h4>
										<input type="hidden" class="form-control" id="totalSumText" name="totalSumText"  value = "${totalSum}"/>
									</div>

									<div class="col-md-4">
										<h4 class="col-md-7" style="margin-top: 5px">
											<b>Tax Total:-</b>
										</h4>

										<h4 class="col-md-5" id="taxTotal"><fmt:formatNumber type = "number" minFractionDigits = "2" maxFractionDigits = "2" value = "${taxTotal}"/></h4>
										<input type="hidden" class="form-control" id="taxTotalText" name="taxTotalText" value="${taxTotal}">
									</div>

									<div class="col-md-4">
										<h4 class="col-md-7" style="margin-top: 5px">
											<b>Grand Total:-</b>
										</h4>

										<h4 class="col-md-5" id="grandTotal"><fmt:formatNumber type = "number" minFractionDigits = "2" maxFractionDigits = "2" value = "${grandTotal}"/></h4>
										<input type="hidden" class="form-control" id="grandTotalText" name="grandTotalText" value="${grandTotal}">
									</div>

									<div class="clearfix"></div>
									 
								</div>
								
								<div class="colOuter">
						 
					</div> 
					<div class="colOuter">
						 
					</div> 
					  
				 <div class="colOuter">
					  <div class="col-md-2">
							<div class="col1title" align="left"> Previous Out Standing Amt: </div>
						</div>
						<div class="col-md-3">
							 <input id="remAmt" style="text-align: right;" class="form-control"
								  name="remAmt"   type="number" value="${billHeader.collectedAmt+billHeader.outstandingAmt}" readonly> 
 

						</div>
							<div class="col-md-1"> 
						</div>
						<div class="col-md-2">
							<div class="col1title" align="left"> Collected Amt *: </div>
						</div>
						<div class="col-md-3">
							<input id="collectedAmt" style="text-align: right;" class="form-control"
								placeholder="Collected Amt" name="collectedAmt"  type="number" onblur="calRemainingAmt(this.value)" value="${billHeader.collectedAmt}" readonly>

						</div>
					 
 
					</div>
					
					<div class="colOuter">
					<div class="col-md-2">
							<div class="col1title" align="left"> Outstanding Amt *: </div>
						</div>
					<div class="col-md-3">
							 <input id="outstandingAmt" style="text-align: right;" class="form-control"
								placeholder="Outstanding Amt" name="outstandingAmt"   type="number" value="${billHeader.outstandingAmt}" readonly> 
   
						</div>
						<div class="col-md-1"> 
						</div>
						<div class="col-md-2">
							<div class="col1title" align="left">Received Creates Qty*: </div>
						</div>
						<div class="col-md-3">
							<input id="recCreatesQty" class="form-control" style="text-align: right;"
								placeholder="Received Creates Qty" name="recCreatesQty"   type="number"  onblur="calBalanceCrates(this.value)" value="${billHeader.cratesReceived}" readonly>

						</div>
						 
					</div> 
					
					<div class="colOuter">
						
						<div class="col-md-2">
							<div class="col1title" align="left"> Previous Outs Standing Crates: </div>
						</div>
					<div class="col-md-3">
							 <input id="cratesBal" style="text-align: right;" class="form-control"
								 name="cratesBal"   type="number" value="${customer.cratesOpBal+billHeader.cratesIssued}" readonly> 
   
						</div>
						<div class="col-md-1"> 
						</div>
						
						
						<div class="col-md-2">
							<div class="col1title" align="left">Creates Balance Qty*: </div>
						</div>
						<div class="col-md-3">
					<!-- 	Opening Qty + Issue Qty - Recieved Qty -->
							 <input class="form-control"
								placeholder="Creates Balance Qty" name="cratesBalQty" id="cratesBalQty" style="text-align: right;"   type="number" value="${billHeader.cratesClBal}" readonly> 
                            
						</div>
						
					
					 </div>
				 <div class="colOuter">
				 <div class="col-md-2">
							<div class="col1title" align="left"><!-- Vehicle In KM*: --></div>
						</div>
						<div class="col-md-3">
							<!-- <input id="vehInKm" class="form-control" style="text-align: right;"
								placeholder="Vehicle In KM" name="vehInKm"   type="number" readonly> -->

						</div> 
						
						<div class="col-md-1"> 
								</div>
						 <div class="col-md-2">
							<div class="col1title" align="left">Remark : </div>
						</div>
						<div class="col-md-3">
							<input id="remark" class="form-control" style="text-align: left;"
								placeholder="Remark" name="remark"   type="text" value="${billHeader.remarks}" readonly>
 
					</div>
						<div class="col-md-1"> 
						</div>
						
						

						</div>
						
						<div class="colOuter">
						<div class="col-md-2">
							<div class="col1title" align="left">Payment Mode*: </div>
						</div>
						<div class="col-md-3">
						<c:if test="${billHeader.collectionPaymode==1}">Check</c:if>
	                   <c:if test="${billHeader.collectionPaymode==2}">Cash</c:if>
		              <c:if test="${billHeader.collectionPaymode==3}">Cash and Check Both</c:if>
						</div>
						<div class="col-md-1"> 
								</div>
							    <div class="col-md-2">
									<div class="col1title" align="left">Check No : </div>
								</div>
								<div class="col-md-3">
								 
									 <input id="checkNo" class="form-control"
										placeholder="Check No" name="checkNo" style="text-align: left;"   type="number" readonly> 
		
								</div>
								 

						</div>
				<%--	<c:if test="${billHeader.collectionPaymode==1 or billHeader.collectionPaymode==3}">		<div class="colOuter">
						<div class="col-md-2">
									<div class="col1title" align="left">Check Amount: </div>
								</div>
								<div class="col-md-3">
								 
									 <input id="checkAmt" class="form-control"
										placeholder="Check Amount" name="checkAmt" type="text"  style="text-align: left;"  readonly> 
		
								</div>
								<div class="col-md-1"> 
								</div>
						<div class="col-md-2">
									<div class="col1title" align="left">Check Date: </div>
								</div>
								<div class="col-md-3">
								 
									 <input id="fromdatepicker" class="form-control"
										placeholder="Check Date" name="checkDate" type="text"  style="text-align: left;"  readonly/> 
		
								</div>
						
								
					</div>	
						<div class="colOuter">
						<div class="col-md-2">
									<div class="col1title" align="left">Bank Name: </div>
								</div>
								<div class="col-md-3">
								 
									 <input id="bankName" class="form-control"
										placeholder="Bank Name" name="bankName" style="text-align: left;"  readonly> 
		
								</div>	
								</div>	</c:if>
								   <c:if test="${billHeader.collectionPaymode==3 or billHeader.collectionPaymode==2}">
					<div id="table-scroll" class="table-scroll">
									<div id="faux-table" width=60% class="faux-table" aria="hidden"></div>
									<div class="table-wrap table-wrap-custbill">
										<table id="table_grid1" width="60%" class="main-table small-td">
											<thead>
												<tr class="bgpink">
													<th width="50px">Sr no.</th>
													<th width="70px">Currency</th>
													<th width="80px">Qty</th>
													<th width="80px">Total Value</th> 
												</tr>
											</thead>
											<tbody>
											 <c:forEach items="${currencyList}" var="currencyList" varStatus="count">
										<input id="currValue${count.index}" class="form-control" style="text-align: right;"
								 name="currValue${count.index}"   type="hidden"   value="${currencyList.currencyValue}">
											<tr>
												 <td >${count.index+1}</td>
													<td >${currencyList.description}</td>
													<td ><input id="qty${count.index}" class="form-control" style="text-align: center;width:90px" 
								 name="qty${count.index}"   type="number"  value="0" onchange="calCurrency(this.value,${count.index})" required>
</td>
													<td id="currencyTotal${count.index}">0
											
													</td> 
												
												</tr>	 	<input id="currTotal${count.index}" class="form-control" style="text-align: right;"
								 name="currTotal${count.index}"   type="hidden"  value="0">
                                           </c:forEach>
											</tbody>

										</table>
									</div>
								</div></c:if>
						<div class="colOuter">
						
						<div class="col-md-1"> 
						</div>
						 
					</div> --%>
						 
					<div class="colOuter">
						<div align="center">
							<!-- <input name="submit" class="buttonsaveorder" value="Submit"
								type="submit" align="center"> -->
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
 

function selectionCurrency() {
//alert("ala");
	var payMode = document.getElementById("payMode").value;
	//alert("payMode" + payMode);
    if(payMode==2)
    	{
    	document.getElementById("checkNo").readOnly=true;
    	 
    	$("#currencyTable").show();
    	}
    else
    	{
    	document.getElementById("checkNo").readOnly=false;
    	 
    	$("#currencyTable").hide();
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
<script type="text/javascript">
function onReturnQty(qty,key)
{
	var keysize=$("#keySize").val();
	var rate=parseFloat($("#rate"+key).val());
	var cgstPer=parseInt($("#cgstPer"+key).val());
	var sgstPer=parseInt($("#sgstPer"+key).val());
	var billQty=parseInt($("#billQty"+key).val());
	var returnQty=parseInt($("#returnQty"+key).val());
	var leakageQty=parseInt($("#leakageQty"+key).val());
	
	var actQty=(billQty-(returnQty+leakageQty));
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
	
	var outstandingAmt=parseFloat($("#remAmt").val());
	var collected=parseFloat($("#collectedAmt").val());
    var total=(outstandingAmt+grandTotal)-collected;
	document.getElementById("outstandingAmt").value=(total).toFixed(2);
}
function onLeakageQty(qty,key)
{
	var keysize=$("#keySize").val();

	var rate=parseFloat($("#rate"+key).val());
	var cgstPer=parseInt($("#cgstPer"+key).val());
	var sgstPer=parseInt($("#sgstPer"+key).val());
	var billQty=parseInt($("#billQty"+key).val());
	var returnQty=parseInt($("#returnQty"+key).val());
	var leakageQty=parseInt($("#leakageQty"+key).val());
	
	var actQty=(billQty-(returnQty+leakageQty));
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
	
	var outstandingAmt=parseFloat($("#remAmt").val());
	var collected=parseFloat($("#collectedAmt").val());
    var total=(outstandingAmt+grandTotal)-collected;
	document.getElementById("outstandingAmt").value=(total).toFixed(2);

}

function calRemainingAmt(collectedAmt)
{
	var grandTotal=parseFloat($("#grandTotalText").val());
	var outstandingAmt=parseFloat($("#remAmt").val());
    var total=(grandTotal+outstandingAmt)-collectedAmt;
    
	document.getElementById("outstandingAmt").value=(total).toFixed(2);
}
function calBalanceCrates(receivedCrates)
{
	var remCrates=parseInt($("#cratesBal").val());
	var totalBalance=(remCrates-receivedCrates);
	document.getElementById("cratesBalQty").value=totalBalance;
}
function calCurrency(qty,index)
{
	var currValue=parseInt($("#currValue"+index).val());
	var total=currValue*qty;
	$('#currencyTotal'+index).html(total);

	document.getElementById("currTotal"+index).value=total;
}
</script>
</body>
</html>
