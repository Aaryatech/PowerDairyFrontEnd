

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
							
								<div class="col1title" align="left"><h3> Approve Bill</h3></div>
								 
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
												<td class="col-md-1"><input type="text" min="0" max="500"
																			class="form-control" name="returnQty${count.index}" id="returnQty${count.index}" value="0"
																			onblur="onReturnQty(this.value,${count.index})"
																			oninput="validity.valid||(value='');"></td>
												<td class="col-md-1"><input type="text" min="0" max="500"
																			class="form-control" name="leakageQty${count.index}" id="leakageQty${count.index}" value="0"
																			onblur="onLeakageQty(this.value,${count.index})"
																			oninput="validity.valid||(value='');"></td>
												 <c:set var="taxPer"   value="${billDetail.cgstPer+billDetail.sgstPer}"/>
												 
												 <c:set var="baseRate"   value="${(billDetail.rate*100)/(100 + taxPer)}"/>
												<td class="col-md-1" id="baseRate${count.index}" ><fmt:formatNumber type = "number" minFractionDigits = "2" maxFractionDigits = "2" value = "${baseRate}"/></td>
												 <c:set var="taxableAmt"   value="${baseRate*billDetail.billQty}"/>
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
								  name="remAmt"   type="number" value="${customer.outstandingAmt}" readonly> 
 

						</div>
							<div class="col-md-1"> 
						</div>
						<div class="col-md-2">
							<div class="col1title" align="left"> Collected Amt *: </div>
						</div>
						<div class="col-md-3">
							<input id="collectedAmt" style="text-align: right;" class="form-control"
								placeholder="Collected Amt" name="collectedAmt"  value="0" type="number" onchange="calRemainingAmt(this.value)" required readonly>

						</div>
					 
 
					</div>
					
					<div class="colOuter">
					<div class="col-md-2">
							<div class="col1title" align="left"> Outstanding Amt *: </div>
						</div>
					<div class="col-md-3">
							 <input id="outstandingAmt" style="text-align: right;" class="form-control"
								placeholder="Outstanding Amt" name="outstandingAmt"   type="number" value="${customer.outstandingAmt+grandTotal}" readonly> 
   
						</div>
						<div class="col-md-1"> 
						</div>
						<div class="col-md-2">
							<div class="col1title" align="left">Received Creates Qty*: </div>
						</div>
						<div class="col-md-3">
							<input id="recCreatesQty" class="form-control" style="text-align: right;"
								placeholder="Received Creates Qty" name="recCreatesQty"   type="number"  value="0" onblur="calBalanceCrates(this.value)" required>

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
								placeholder="Creates Balance Qty" name="cratesBalQty" id="cratesBalQty" style="text-align: right;"   type="number" value="${customer.cratesOpBal+billHeader.cratesIssued}" readonly> 
                            
						</div>
						
					
					 </div>
					<div class="colOuter">
					<div class="col-md-2">
							<div class="col1title" align="left">Vehicle Out KM*: </div>
						</div>
						<div class="col-md-3">
							<input id="vehInKm" class="form-control" style="text-align: right;"
								placeholder="Vehicle Out KM" name="vehOutKm"   type="number" value="${outKm}"  readonly>

						</div>
							<div class="col-md-1"> 
						</div>
					<div class="col-md-2">
							<div class="col1title" align="left">Vehicle In KM*: </div>
						</div>
						<div class="col-md-3">
							<input id="vehInKm" class="form-control" style="text-align: right;"
								placeholder="Vehicle In KM" name="vehInKm"   type="number"  required>

						</div>
							
						
					
						
						</div>
						
						<div class="colOuter">
						<div class="col-md-2">
							<div class="col1title" align="left">Payment Mode*: </div>
						</div>
						<div class="col-md-3">
							<select class="form-control" onchange="selectionCurrency()" data-live-search="true" title="Please Select" 
							name="payMode" id="payMode">
							<option value="0">NA</option>  
							<option value="1">Check</option> 
							<option value="2">Cash</option>
							<option value="3">Check And Cash Both</option>
							
						 </select>

						</div>
						<div class="col-md-1"> 
								</div>
							    <div class="col-md-2">
									<div class="col1title" align="left">Cheque No : </div>
								</div>
								<div class="col-md-3">
								 
									 <input id="checkNo" class="form-control"
										placeholder="Cheque No" name="checkNo" style="text-align: left;" value="0"  type="number" required readOnly> 
		
								</div>
								 

						</div>
						<div class="colOuter">
						<div class="col-md-2">
									<div class="col1title" align="left">Cheque Amount: </div>
								</div>
								<div class="col-md-3">
								 
									 <input id="checkAmt" class="form-control"
										placeholder="Cheque Amount" name="checkAmt" type="text"  style="text-align: right;" value="0" onchange="calculateCollectedAmt(this.value) "required readOnly > 
		
								</div>
								<div class="col-md-1"> 
								</div>
						<div class="col-md-2">
									<div class="col1title" align="left">Cheque Date: </div>
								</div>
								<div class="col-md-3">
								 
									 <input id="fromdatepicker" class="form-control"
										placeholder="Cheque Date" name="checkDate" type="text"  style="text-align: left;"  required readOnly> 
		
								</div>
						
								
					</div>	
						<div class="colOuter">
						<div class="col-md-2">
									<div class="col1title" align="left">Bank Name: </div>
								</div>
								<div class="col-md-3">
								 
									 <input id="bankName" class="form-control"
										placeholder="Bank Name" name="bankName" style="text-align: left;"  required readOnly> 
		
								</div>	
									<div class="col-md-1"> 
								</div>
					 <div class="col-md-2">
							<div class="col1title" align="left">Remark : </div>
						</div>
						<div class="col-md-3">
							<input id="remark" class="form-control" style="text-align: left;"
								placeholder="Remark" name="remark"   type="text" value="NA">
 
					</div>
								</div>	
 				<div id="currencyTable" style="display: none">
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
											<input type="hidden" name="totCurrAmt" id="totCurrAmt" value="0"/>
											<input type="hidden" name="currencyListSize" id="currencyListSize" value="${currencyListSize}"/>
											 <c:forEach items="${currencyList}" var="currencyList" varStatus="count">
										<input id="currValue${count.index}" class="form-control" style="text-align: right;"
								 name="currValue${count.index}"   type="hidden"   value="${currencyList.currencyValue}">
											<tr>
												 <td >${count.index+1}</td>
													<td >${currencyList.description}</td>
													<td ><input id="qty${count.index}" class="form-control" style="text-align: center;width:90px" 
								 name="qty${count.index}"   type="number" min="0" value="0" onchange="calCurrency(this.value,${count.index})" required>
</td>
													<td id="currencyTotal${count.index}">0
											
													</td> 
												
												</tr>	 	<input id="currTotal${count.index}" class="form-control" style="text-align: right;"
								 name="currTotal${count.index}"   type="hidden"  value="0">
                                           </c:forEach>
											</tbody>

										</table>
									</div>
								</div>
					
					<div class="colOuter">
					
					
					<div class="col-md-7"> 
								</div>
							    <div class="col-md-2">
									<div class="col1title" align="left">Total Currency Cash: </div>
								</div>
								<div class="col-md-3">
								 
									 <input id="cash" class="form-control"
										placeholder="Currency Cash" name="cash" style="text-align: right;" value="0"  type="number" readOnly> 
		
								</div>
								
				
					</div>	 </div>
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
 
function calculateCollectedAmt(val)
{
	var payMode = document.getElementById("payMode").value;
	var	checkAmt=parseInt($("#checkAmt").val());
	    if(payMode==1)
		{
	    	document.getElementById("collectedAmt").value=(checkAmt);
	    	calRemainingAmt(checkAmt);
		}
	    else if(payMode==3)
		{
		  var totCurrAmt=parseInt($("#totCurrAmt").val());
		  document.getElementById("collectedAmt").value=(checkAmt+totCurrAmt);
		  calRemainingAmt(checkAmt+totCurrAmt);
		}
	
	
	
}
function selectionCurrency() {
//alert("ala");
	var payMode = document.getElementById("payMode").value;
	//alert("payMode" + payMode);
	  if(payMode==0)
    	{
		  document.getElementById("collectedAmt").value=0;
		  calRemainingAmt(0);
	    	document.getElementById("checkAmt").value=0;
	    	var currencyListSize=parseInt($("#currencyListSize").val());
	    	for(var i=0;i<currencyListSize;i++)
			{
	    		  document.getElementById("qty"+i).value=0;
	    		  document.getElementById("currTotal"+i).value=0;
	    		  $('#currencyTotal'+i).html(0);
			}
	    	document.getElementById("totCurrAmt").value=0;
	    	document.getElementById("cash").value=0;	
		  document.getElementById("checkNo").readOnly=true;
	    	document.getElementById("fromdatepicker").readOnly=true;
	    	document.getElementById("checkAmt").readOnly=true;
	    	document.getElementById("bankName").readOnly=true;
	    	
	    	$("#currencyTable").hide();
    	}
	  else if(payMode==2)
    	{
			document.getElementById("collectedAmt").value=0;
			calRemainingAmt(0);
	    	document.getElementById("checkAmt").value=0;
	    	var currencyListSize=parseInt($("#currencyListSize").val());
	    	for(var i=0;i<currencyListSize;i++)
			{
	    		  document.getElementById("qty"+i).value=0;
	    		  document.getElementById("currTotal"+i).value=0;
	    		  $('#currencyTotal'+i).html(0);
			}
	    	document.getElementById("totCurrAmt").value=0;
	    	document.getElementById("cash").value=0;
    	document.getElementById("checkNo").readOnly=true;
    	document.getElementById("fromdatepicker").readOnly=true;
    	document.getElementById("checkAmt").readOnly=true;
    	document.getElementById("bankName").readOnly=true;
    
    	$("#currencyTable").show();
    	}
    else  if(payMode==1)
    	{
    	document.getElementById("collectedAmt").value=0;
    	calRemainingAmt(0);
    	document.getElementById("checkAmt").value=0;
    	var currencyListSize=parseInt($("#currencyListSize").val());
    	for(var i=0;i<currencyListSize;i++)
		{
    		  document.getElementById("qty"+i).value=0;
    		  document.getElementById("currTotal"+i).value=0;
    		  $('#currencyTotal'+i).html(0);
		}
    	document.getElementById("totCurrAmt").value=0;
    	document.getElementById("cash").value=0;
    	document.getElementById("checkNo").readOnly=false;
    	document.getElementById("fromdatepicker").readOnly=false;
    	document.getElementById("checkAmt").readOnly=false;
    	document.getElementById("bankName").readOnly=false;
    
    	$("#currencyTable").hide();
    	}
    else  if(payMode==3)
    	{
    	document.getElementById("collectedAmt").value=0;
    	calRemainingAmt(0);
    	document.getElementById("checkAmt").value=0;
    	var currencyListSize=parseInt($("#currencyListSize").val());
    	for(var i=0;i<currencyListSize;i++)
		{
    		  document.getElementById("qty"+i).value=0;
    		  document.getElementById("currTotal"+i).value=0;
    		  $('#currencyTotal'+i).html(0);
		}
    	document.getElementById("totCurrAmt").value=0;
    	document.getElementById("cash").value=0;
    	document.getElementById("checkNo").readOnly=false;
    	document.getElementById("fromdatepicker").readOnly=false;
    	document.getElementById("checkAmt").readOnly=false;
    	document.getElementById("bankName").readOnly=false;
    
    	$("#currencyTable").show();
    	
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
	if(RetQtyValidation(key)==true)
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
	else
		{
		var returnQty=parseFloat($("#returnQty"+key).val());
		onReturnQty(returnQty,key);
		}
}
function onLeakageQty(qty,key)
{
	if(lekQtyValidation(key)==true)
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
	else
	{
	var leakageQty=parseFloat($("#leakageQty"+key).val());
	onLeakageQty(leakageQty,key);
	}

}

function RetQtyValidation(key)
{
	var returnQty=parseFloat($("#returnQty"+key).val());
	var leakageQty=parseFloat($("#leakageQty"+key).val());
	var billQty=parseInt($("#billQty"+key).val()); 
	var ret=true;
	
	if((returnQty+leakageQty)>billQty)
		{
		ret=false;
		alert("Your Bill Qty is " + billQty );
		document.getElementById("returnQty"+key).value=0;
		}
	
     return ret;
}

function lekQtyValidation(key)
{
	var returnQty=parseFloat($("#returnQty"+key).val());
	var leakageQty=parseFloat($("#leakageQty"+key).val());
	var billQty=parseInt($("#billQty"+key).val()); 
	var ret=true;
	
	if((returnQty+leakageQty)>billQty)
		{
		ret=false;
		alert("Your Bill Qty is " + billQty );
		document.getElementById("leakageQty"+key).value=0;
		}
	
     return ret;
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
	if(receivedCrates>remCrates)
		{
		document.getElementById("recCreatesQty").value=0;
		calBalanceCrates(0);
		}
	else
		{
		var totalBalance=(remCrates-receivedCrates);
		document.getElementById("cratesBalQty").value=totalBalance;
		}
	
}
function calCurrency(qty,index)
{
	var CurrencyTotal=0;
	var payMode = document.getElementById("payMode").value;

	var currencyListSize=parseInt($("#currencyListSize").val());
	var currValue=parseInt($("#currValue"+index).val());
	var total=currValue*qty;
	$('#currencyTotal'+index).html(total);
	document.getElementById("currTotal"+index).value=total;
	for(var i=0;i<currencyListSize;i++)
		{
		   var curr=parseInt($('#currTotal'+i).val());
		   CurrencyTotal=CurrencyTotal+curr;
		   
		}
	document.getElementById("totCurrAmt").value=(CurrencyTotal);
	document.getElementById("cash").value=(CurrencyTotal);

	if(payMode==2)
		{
		document.getElementById("collectedAmt").value=(CurrencyTotal);
		calRemainingAmt(CurrencyTotal);
		}
	else if(payMode==3)
		{
		var checkAmt=parseInt($("#checkAmt").val());
		document.getElementById("collectedAmt").value=(checkAmt+CurrencyTotal);
		calRemainingAmt(checkAmt+CurrencyTotal);
		}
}
</script>
</body>
</html>
