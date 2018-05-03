<%@page
contentType="text/html; charset=ISO8859_1"%>
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
 

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
  
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Bill PDF</title>

</head>
<body>
 	<c:forEach items="${billHeaderList}" var="billHeaderRes" varStatus="count">
					<h6 align="center">INVOICE</h6>
<table width="100%" border="0"  cellpadding="0" cellspacing="0" style="border-left:1px solid #313131;border-right:1px solid #313131;border-top:1px solid #313131;">
  <tr>
    <td colspan="2" width="15%" style=" padding:10px;color:#FFF; font-size:15px;">
<img src="${pageContext.request.contextPath}/resources/images/monginislogo.png" alt="Dairy Power" style="width:88px;height:81px;"/>
</td>

    <td width="80%" colspan="6" style="border-left:1px solid #313131; padding:1px;color:#000; font-size:15px; text-align:center">
    <h3 style="color:#0099e6; font-size:25px; text-align:center; margin:0px;">Dairy Power Ltd.</h3>
    <hr></hr>
   <p style="color:#000; font-size:11px; text-align:center;margin:0px;">Regd.Office: Shop No.3,Samarth Heights,Opp.Sudarshan Lwans,Indira Nagar,Nashik-422 009</p>
  <hr></hr>
     <p style="color:#000; font-size:11px; text-align:center;margin:0px;">Factory: Gat No.555,At Songaon,Post Saikheda,Tal.Niphad,Dist.Nashik. 422 210</br>
     Phone: 7774015160<br></br>
     Email: dairypowerindiasales@gmail.com
     </p>
  <br></br>
 </td>
    <td colspan="3" width="20%" style="border-left:1px solid #313131; padding:10px;color:#FFF; font-size:15px;">
   	<p style="color:#000; font-size:11px; text-align:center;margin:0px;">Accounts Copy</p> 
    </td>
    
  </tr>
 <tr>
    <td width="100%" colspan="11" style="border-top:1px solid #313131;padding:2px;color:#FFF; ">
  <p style="color:#000; font-size:11px; text-align:;left;margin:0px;">  Subject to Nashik Jurisdiction <center><b>INVOICE NOTE</b></center></p> 
    </td>
    </tr>
 
  <tr>
    <td width="50%" colspan="6" style="border-top:1px solid #313131;padding:8px;color:#FFF; font-size:14px;">
        <p style="color:#000; font-size:15px; text-align:;left;margin:0px;"><b>To: &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ${billHeaderRes.custName}</b>&nbsp;&nbsp;&nbsp;&nbsp; </p>
<!--         <p style="color:#000; font-size:13px; text-align:left;margin:0px;"></p>
 -->        <p style="color:#000; font-size:12px; text-align:left;margin:0px;"> #&nbsp;&nbsp;Phone: &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ${billHeaderRes.custMobNo}</p>
        <p style="color:#000; font-size:12px; text-align:left;margin:0px;">#&nbsp;&nbsp;Address:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ${billHeaderRes.custAddress} &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;GSTIN:  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ${billHeaderRes.custGstNo}</p>
    </td>

    <td width="50%" colspan="5" style="border-top:1px solid #313131;border-left:1px solid #313131; padding:8px;color:#FFF; font-size:15px;">
        <p style="color:#000; font-size:13px; text-align:;left;margin:0px;"> Bill No: &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>${billHeaderRes.billId}</b></p>
        <p style="color:#000; font-size:13px; text-align:left;margin:0px;"> Date: &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>${billHeaderRes.billDate}</b></p>
        <p style="color:#000; font-size:13px; text-align:left;margin:0px;"> Route: &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>${billHeaderRes.custRoot}</b></p>

    </td>
  </tr>

      </table>
      
      <table width="100%" border="0"  cellpadding="0" cellspacing="0" style="border-top:1px solid #313131;border-right:1px solid #313131">
  <tr>
    <td rowspan="2"  width="2%"  style="border-bottom:1px solid #313131; border-bottom:1px solid #313131;border-left:1px solid #313131; padding:5px;color:#000; font-size:10px;">No.</td>
    <td align="left" width="36%" rowspan="2" style="border-bottom:1px solid #313131; border-left:1px solid #313131; padding:15px;color:#000; font-size:10px;text-align: left">Item Decription</td>
   <td align="center" width="8%" rowspan="2" style="border-bottom:1px solid #313131; border-left:1px solid #313131; padding:0.2px;color:#000; font-size:10px;">HSN Code</td>
    <td align="center" width="8%" rowspan="2" style="border-bottom:1px solid #313131; border-left:1px solid #313131; padding:0.2px;color:#000; font-size:10px;">Batch No.</td>
 
     
 <td align="center" width="6%" rowspan="2" style=" border-bottom:1px solid #313131; border-left:1px solid #313131; padding:10px;color:#000; font-size:10px;">Qty</td>
    <td align="center" width="6%" rowspan="2" style="border-bottom:1px solid #313131; border-left:1px solid #313131; padding:10px;color:#000; font-size:10px;">UOM </td>
    <td align="center" width="6%" rowspan="2" style="border-bottom:1px solid #313131; border-left:1px solid #313131; padding:10px;color:#000; font-size:10px;">Rate</td>
       <td align="center" width="10%" colspan="2" style="border-left:1px solid #313131; padding:10px;color:#000; font-size:10px; text-align:center;"> CGST</td>
    <td align="center" width="10%" colspan="2" style="border-left:1px solid #313131; padding:10px;color:#000; font-size:10px;text-align:center;">SGST</td>
    <td align="center" width="10%" rowspan="2" style="border-bottom:1px solid #313131; border-left:1px solid #313131; padding:10px;color:#000; font-size:10px;">Amount</td>


 
  </tr>
  <tr>
    <td align="center" style="border-top:1px solid #313131;border-left:1px solid #313131;border-bottom:1px solid #313131; padding:4px;color:#000; font-size:10px;">Rate% </td>
    <td align="center" style="border-top:1px solid #313131;border-left:1px solid #313131;border-bottom:1px solid #313131;  padding:4px;color:#000; font-size:10px;">Amount</td>
    <td align="center" style="border-top:1px solid #313131;border-left:1px solid #313131;border-bottom:1px solid #313131; padding:4px;color:#000; font-size:10px;">Rate%</td>
    <td align="center" style="border-top:1px solid #313131;border-left:1px solid #313131;border-bottom:1px solid #313131; padding:4px;color:#000; font-size:10px;">Amount</td>

 
  </tr>
 
  <c:set var = "totalQty" value = "0"/>
   <c:set var = "totalAmt" value = "0"/>
    <c:set var = "totalCgst" value = "0"/>
      <c:set var = "totalSgst" value = "0"/>
    
     
      <c:forEach items="${billHeaderRes.billDetailReportList}" var="billDetails" varStatus="count">
   <c:set var = "billQty" value = "${billDetails.billQty-(billDetails.returnQty+billDetails.distLeakageQty)}"/>
  <tr>
    <td  style="border-left:1px solid #313131; padding:3px 5px;color:#000; font-size:10px;">${count.index+1}</td>
    <td style="border-left:1px solid #313131;  padding:3px 5px;color:#000; font-size:10px;">${billDetails.itemName}</td>
    <td align="left" style="border-left:1px solid #313131;  padding:3px 5px;color:#000; font-size:10px;">${billDetails.hsnCode}</td>
        <td align="left" style="border-left:1px solid #313131;  padding:3px 5px;color:#000; font-size:10px;">${billDetails.batchNo}</td>
    
    
    <td align="right" style="border-left:1px solid #313131; padding:3px 5px;color:#000;font-size:10px;"><fmt:formatNumber type="number"
								maxFractionDigits="2" minFractionDigits="2" value="${billQty}"/></td>
			  <c:set var = "totalQty" value = "${totalQty+billQty}"/>					
    <td align="center" style="border-left:1px solid #313131; padding:3px 5px;color:#000; font-size:10px;">${billDetails.uom} </td>
     <c:set var = "taxPer" value = "${billDetails.cgstPer+billDetails.sgstPer}"/>	
     <c:set var = "baseRate" value = "${((billDetails.rate)*100)/(100+taxPer)}"/>	
      <c:set var = "taxableAmt" value = "${baseRate*billQty}"/>	
        <c:set var = "cgstRs" value = "${(taxableAmt*billDetails.cgstPer)/100}"/>	
          <c:set var = "sgstRs" value = "${(taxableAmt*billDetails.sgstPer)/100}"/>	
    
    <td align="right" style="border-left:1px solid #313131; padding:3px 4px;color:#000; font-size:10px;"><fmt:formatNumber type="number"
								maxFractionDigits="2" minFractionDigits="2" value="${baseRate}"/></td>
    <td align="right" style="border-left:1px solid #313131; padding:3px 4px;color:#000;font-size:10px;"><fmt:formatNumber type="number"
								maxFractionDigits="2" minFractionDigits="2" value="${billDetails.cgstPer}"/></td>
								   <c:set var = "totalAmt" value = "${totalAmt+taxableAmt}"/>
    <td align="right" style="border-left:1px solid #313131; padding:3px 5px;color:#000; font-size:10px;"><fmt:formatNumber type="number"
								maxFractionDigits="2" minFractionDigits="2" value="${cgstRs}"/></td>
    <td align="right" style="border-left:1px solid #313131; padding:3px 5px;color:#000; font-size:10px;"><fmt:formatNumber type="number"
								maxFractionDigits="2" minFractionDigits="2" value="${billDetails.sgstPer}"/></td>
								  <c:set var = "totalCgst" value = "${totalCgst+cgstRs}"/>
    <td align="right" style="border-left:1px solid #313131; padding:3px 5px;color:#000;font-size:10px;"><fmt:formatNumber type="number"
								maxFractionDigits="2" minFractionDigits="2" value="${sgstRs}"/></td>
    <td align="right" style="border-left:1px solid #313131; padding:3px 5px;color:#000;font-size:10px;"><fmt:formatNumber type="number"
								maxFractionDigits="2" minFractionDigits="2" value="${taxableAmt}"/></td>
						 <c:set var = "totalSgst" value = "${totalSgst+sgstRs}"/>
								
	 		  
  </tr>
  </c:forEach>
   <tr>
    <td  align="left" style="border-top:1px solid #313131;border-left:1px solid #313131;border-bottom:1px solid #313131; padding:4px;color:#000; font-size:0px;">-</td>
    <td align="left" style="border-top:1px solid #313131;border-left:1px solid #313131;border-bottom:1px solid #313131; padding:4px;color:#000; font-size:12px;"><b>Total</b></td>
   
       <td align="center" style="border-top:1px solid #313131;border-left:1px solid #313131;border-bottom:1px solid #313131; padding:4px;color:#000; font-size:0px;">-</td>
    <td align="center" style="border-top:1px solid #313131;border-left:1px solid #313131;border-bottom:1px solid #313131; padding:4px;color:#000; font-size:0px;">-</td>
    <td align="right" style="border-top:1px solid #313131;border-left:1px solid #313131;border-bottom:1px solid #313131; padding:4px;color:#000; font-size:12px;"><b><fmt:formatNumber type="number"
								maxFractionDigits="2" minFractionDigits="2" value="${totalQty}"/></b></td>
    <td align="center" style="border-top:1px solid #313131;border-left:1px solid #313131;border-bottom:1px solid #313131; padding:4px;color:#000; font-size:0px;">-</td>
    <td align="center" style="border-top:1px solid #313131;border-left:1px solid #313131;border-bottom:1px solid #313131; padding:4px;color:#000; font-size:0px;">-</td>
     <td align="center" style="border-top:1px solid #313131;border-left:1px solid #313131;border-bottom:1px solid #313131; padding:4px;color:#000; font-size:0px;">-</td>
 
 <td align="right" style="border-top:1px solid #313131;border-left:1px solid #313131;border-bottom:1px solid #313131; padding:4px;color:#000; font-size:12px;"><b><fmt:formatNumber type="number"
								maxFractionDigits="2" minFractionDigits="2" value="${totalCgst}"/></b></td>
								    <td align="center" style="border-top:1px solid #313131;border-left:1px solid #313131;border-bottom:1px solid #313131; padding:4px;color:#000; font-size:0px;">-</td>
								
    <td align="right" style="border-top:1px solid #313131;border-left:1px solid #313131;border-bottom:1px solid #313131; padding:4px;color:#000; font-size:12px;"><b><fmt:formatNumber type="number"
								maxFractionDigits="2" minFractionDigits="2" value="${totalSgst}"/></b></td>
   
	<td align="right" style="border-top:1px solid #313131;border-left:1px solid #313131;border-bottom:1px solid #313131; padding:4px;color:#000; font-size:12px;"><b><fmt:formatNumber type="number"
								maxFractionDigits="2" minFractionDigits="2" value="${totalAmt}"/></b></td>
	  				
  </tr> 
   <tr>
   
    <td align="right" style="border-left:1px solid #313131;border-bottom:1px solid #313131; padding:4px;color:#000; font-size:0px;">-</td>
        <td align="right" style="border-left:1px solid #313131;border-bottom:1px solid #313131; padding:4px;color:#000; font-size:0px;">-</td>
           <td align="right" style="border-left:1px solid #313131;border-bottom:1px solid #313131; padding:4px;color:#000; font-size:0px;">-</td>
           <td align="right" style="border-left:1px solid #313131;border-bottom:1px solid #313131; padding:4px;color:#000; font-size:0px;">-</td>
       <td align="center" style="border-left:1px solid #313131;border-bottom:1px solid #313131; padding:4px;color:#000; font-size:0px;">-</td>
   
    <td style="border-bottom:1px solid #313131; font-size:0px;">-</td><td style="border-bottom:1px solid #313131;padding:4px;color:#000; font-size:0px;">-</td><td style="border-bottom:1px solid #313131;font-size:0px;">-</td><td style="border-bottom:1px solid #313131;padding:4px;color:#000; font-size:0px;">-</td><td style="border-bottom:1px solid #313131;font-size:0px;">-</td><td style="border-bottom:1px solid #313131;font-size:12px;"><b>Total:</b></td>
    <td align="right" style="border-left:1px solid #313131;border-bottom:1px solid #313131; padding:4px;color:#000; font-size:12px;"><b><fmt:formatNumber type="number"
								maxFractionDigits="2" minFractionDigits="2" value="${totalAmt+totalCgst+totalSgst}"/></b></td>
  </tr>
</table>

    
  <table width="100%" border="0"  cellpadding="0" cellspacing="0" style="border-right:1px solid #313131;">
  
 
    
  <tr>
    <td colspan="8" width="60%" style="border-left:1px solid #313131; padding:8px;color:#000; font-size:12px;">
     <p style="color:#000; font-size:12px; text-align:left;margin:0px;"></p>
</td>
    <td colspan="4" width="40%" rowspan="2" style="border-left:1px solid #313131; padding:8px;color:#000;font-size:12px;">Total:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    <b><fmt:formatNumber type="number"
								maxFractionDigits="2" minFractionDigits="2" value="${totalAmt+totalCgst+totalSgst}"/></b>
    </td>
  </tr>
  
    <tr>
    <td colspan="2" width="12%"  style="text-align:center; border-top:1px solid #313131;border-left:1px solid #313131; padding:8px;color:#000; font-size:12px;">Crates O/p Bal <br></br><b>${billHeaderRes.cratesOpBal} </b></td>
      <td colspan="2" width="13%"  style="text-align:center; border-top:1px solid #313131;border-left:1px solid #313131; padding:8px;color:#000; font-size:12px;">Crates Received <br></br><b>${billHeaderRes.cratesReceived}</b></td>
        <td colspan="2" width="17%"  style="text-align:center; border-top:1px solid #313131;border-left:1px solid #313131; padding:8px;color:#000; font-size:12px;">No. Of Crates Issued<br></br> <b>${billHeaderRes.cratesIssued}</b></td>
          <td colspan="2" width="13%"  style="text-align:center; border-top:1px solid #313131;border-left:1px solid #313131; padding:8px;color:#000; font-size:12px;">Crates Clos_Bal<br></br> <b>${billHeaderRes.cratesClBal}</b></td>
  </tr>
  
    
  <tr>
    <td colspan="8" width="60%"  style="border-top:1px solid #313131;border-left:1px solid #313131; padding:8px;color:#000; font-size:15px;">&nbsp;

</td>
    <td colspan="4" width="60%" style="border-top:1px solid #313131;border-left:1px solid #313131; padding:8px;color:#000;font-size:15px;">     
    <p style="color:#000; font-size:11px; text-align:left;margin:0px;">Continue...</p></td>
  </tr>
  
  <tr>
    <td colspan="8"  width="60%" style="border-bottom:1px solid #313131;border-top:1px solid #313131;border-left:1px solid #313131; padding:10px;color:#000; font-size:11px;">
     <p style="color:#000; font-size:11px; text-align:center;margin:0px;">Receiver's Signature</p>
</td>
    <td  align="center" colspan="4" width="40%" style="border-bottom:1px solid #313131;border-top:1px solid #313131;border-left:1px solid #313131; padding:10px;color:#000;font-size:11px;">Authorised Signature</td>
  </tr>
  
</table>
<div style="page-break-after: always;"></div>
 </c:forEach> 

</body>
</html>