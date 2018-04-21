package com.dairypower.admin.model;

import java.util.List;

public class GetBillHeader{
	
	private int billTempId;
	
	private int billId;
	
	private String billDate;
	
	private int custId;
	
	private String custName;
	
	private int vehId;
	
	private String vehName;
	
	private float collectedAmt;
	
	private float grandTotal;
	
	private float outstandingAmt;
	
	private int collectionPaymode;
	
	private int cratesOpBal;
	
	private int cratesIssued;
	
	private int cratesReceived;
	
	private int cratesClBal;

	private String remarks;
	
	private int isSettled;

 
	List<GetBillDetail> billDetailList;
	
	
	public float getGrandTotal() {
		return grandTotal;
	}

	public void setGrandTotal(float grandTotal) {
		this.grandTotal = grandTotal;
	}

	
	public List<GetBillDetail> getBillDetailList() {
		return billDetailList;
	}

	public void setBillDetailList(List<GetBillDetail> billDetailList) {
		this.billDetailList = billDetailList;
	}

	public String getBillDate() {
		return billDate;
	}

	public void setBillDate(String billDate) {
		this.billDate = billDate;
	}

	public int getBillTempId() {
		return billTempId;
	}

	public void setBillTempId(int billTempId) {
		this.billTempId = billTempId;
	}

	public int getBillId() {
		return billId;
	}

	public void setBillId(int billId) {
		this.billId = billId;
	}

	public int getCustId() {
		return custId;
	}

	public void setCustId(int custId) {
		this.custId = custId;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public int getVehId() {
		return vehId;
	}

	public void setVehId(int vehId) {
		this.vehId = vehId;
	}

	public String getVehName() {
		return vehName;
	}

	public void setVehName(String vehName) {
		this.vehName = vehName;
	}

	public float getCollectedAmt() {
		return collectedAmt;
	}

	public void setCollectedAmt(float collectedAmt) {
		this.collectedAmt = collectedAmt;
	}

	public float getOutstandingAmt() {
		return outstandingAmt;
	}

	public void setOutstandingAmt(float outstandingAmt) {
		this.outstandingAmt = outstandingAmt;
	}

	public int getCollectionPaymode() {
		return collectionPaymode;
	}

	public void setCollectionPaymode(int collectionPaymode) {
		this.collectionPaymode = collectionPaymode;
	}

	public int getCratesOpBal() {
		return cratesOpBal;
	}

	public void setCratesOpBal(int cratesOpBal) {
		this.cratesOpBal = cratesOpBal;
	}

	public int getCratesIssued() {
		return cratesIssued;
	}

	public void setCratesIssued(int cratesIssued) {
		this.cratesIssued = cratesIssued;
	}

	public int getCratesReceived() {
		return cratesReceived;
	}

	public void setCratesReceived(int cratesReceived) {
		this.cratesReceived = cratesReceived;
	}

	public int getCratesClBal() {
		return cratesClBal;
	}

	public void setCratesClBal(int cratesClBal) {
		this.cratesClBal = cratesClBal;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public int getIsSettled() {
		return isSettled;
	}

	public void setIsSettled(int isSettled) {
		this.isSettled = isSettled;
	}

	@Override
	public String toString() {
		return "GetBillHeader [billTempId=" + billTempId + ", billId=" + billId + ", billDate=" + billDate + ", custId="
				+ custId + ", custName=" + custName + ", vehId=" + vehId + ", vehName=" + vehName + ", collectedAmt="
				+ collectedAmt + ", outstandingAmt=" + outstandingAmt + ", collectionPaymode=" + collectionPaymode
				+ ", cratesOpBal=" + cratesOpBal + ", cratesIssued=" + cratesIssued + ", cratesRecieved="
				+ cratesReceived + ", cratesClBal=" + cratesClBal + ", remarks=" + remarks + ", isSettled=" + isSettled
				+ "]";
	}
	
	

}
