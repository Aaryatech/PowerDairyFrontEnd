package com.dairypower.admin.model;

import java.util.Date;
import java.util.List;


public class BillHeaderReport {

	private int billTempId;
	
	private int billId;
	
	private String billDate;
	
	private int custId;
	
	private String custName;
	
	private String custMobNo;
	
	private String custRoot;
	
	private String custAddress;
	
	private String custGstNo;
	
	private int vehId;
	
	private String vehName;
	
	private float collectedAmt;
	
	private float outstandingAmt;
	
	private int collectionPaymode;
	
	private int cratesOpBal;
	
	private int cratesIssued;
	
	private int cratesReceived;
	
	private int cratesClBal;

	private String remarks;
	
	private int isSettled;
	
	private float grandTotal;

	
	List<BillDetailReport> billDetailReportList;
	
	
	public List<BillDetailReport> getBillDetailReportList() {
		return billDetailReportList;
	}

	public void setBillDetailReportList(List<BillDetailReport> billDetailReportList) {
		this.billDetailReportList = billDetailReportList;
	}

	public int getBillTempId() {
		return billTempId;
	}

	public int getBillId() {
		return billId;
	}

	public String getBillDate() {
		return billDate;
	}

	public int getCustId() {
		return custId;
	}

	public String getCustName() {
		return custName;
	}

	public String getCustMobNo() {
		return custMobNo;
	}

	public String getCustRoot() {
		return custRoot;
	}

	public String getCustAddress() {
		return custAddress;
	}

	public String getCustGstNo() {
		return custGstNo;
	}

	public int getVehId() {
		return vehId;
	}

	public String getVehName() {
		return vehName;
	}

	public float getCollectedAmt() {
		return collectedAmt;
	}

	public float getOutstandingAmt() {
		return outstandingAmt;
	}

	public int getCollectionPaymode() {
		return collectionPaymode;
	}

	public int getCratesOpBal() {
		return cratesOpBal;
	}

	public int getCratesIssued() {
		return cratesIssued;
	}

	public int getCratesReceived() {
		return cratesReceived;
	}

	public int getCratesClBal() {
		return cratesClBal;
	}

	public String getRemarks() {
		return remarks;
	}

	public int getIsSettled() {
		return isSettled;
	}

	public float getGrandTotal() {
		return grandTotal;
	}

	public void setBillTempId(int billTempId) {
		this.billTempId = billTempId;
	}

	public void setBillId(int billId) {
		this.billId = billId;
	}

	public void setBillDate(String billDate) {
		this.billDate = billDate;
	}

	public void setCustId(int custId) {
		this.custId = custId;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public void setCustMobNo(String custMobNo) {
		this.custMobNo = custMobNo;
	}

	public void setCustRoot(String custRoot) {
		this.custRoot = custRoot;
	}

	public void setCustAddress(String custAddress) {
		this.custAddress = custAddress;
	}

	public void setCustGstNo(String custGstNo) {
		this.custGstNo = custGstNo;
	}

	public void setVehId(int vehId) {
		this.vehId = vehId;
	}

	public void setVehName(String vehName) {
		this.vehName = vehName;
	}

	public void setCollectedAmt(float collectedAmt) {
		this.collectedAmt = collectedAmt;
	}

	public void setOutstandingAmt(float outstandingAmt) {
		this.outstandingAmt = outstandingAmt;
	}

	public void setCollectionPaymode(int collectionPaymode) {
		this.collectionPaymode = collectionPaymode;
	}

	public void setCratesOpBal(int cratesOpBal) {
		this.cratesOpBal = cratesOpBal;
	}

	public void setCratesIssued(int cratesIssued) {
		this.cratesIssued = cratesIssued;
	}

	public void setCratesReceived(int cratesReceived) {
		this.cratesReceived = cratesReceived;
	}

	public void setCratesClBal(int cratesClBal) {
		this.cratesClBal = cratesClBal;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public void setIsSettled(int isSettled) {
		this.isSettled = isSettled;
	}

	public void setGrandTotal(float grandTotal) {
		this.grandTotal = grandTotal;
	}

	@Override
	public String toString() {
		return "BillHeaderReport [billTempId=" + billTempId + ", billId=" + billId + ", billDate=" + billDate
				+ ", custId=" + custId + ", custName=" + custName + ", custMobNo=" + custMobNo + ", custRoot="
				+ custRoot + ", custAddress=" + custAddress + ", custGstNo=" + custGstNo + ", vehId=" + vehId
				+ ", vehName=" + vehName + ", collectedAmt=" + collectedAmt + ", outstandingAmt=" + outstandingAmt
				+ ", collectionPaymode=" + collectionPaymode + ", cratesOpBal=" + cratesOpBal + ", cratesIssued="
				+ cratesIssued + ", cratesReceived=" + cratesReceived + ", cratesClBal=" + cratesClBal + ", remarks="
				+ remarks + ", isSettled=" + isSettled + ", grandTotal=" + grandTotal + "]";
	}

	
	
}
