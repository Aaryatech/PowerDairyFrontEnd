package com.dairypower.admin.model;


public class BillwiseConsumption {

	private int billTempId;

	private int billId;

	private String billDate;

	private String custName;

	private float collectedAmt;

	private float outstandingAmt;

	private int collectionPaymode;

	private int cratesOpBal;

	private int cratesIssued;

	private int cratesReceived;

	private int cratesClBal;

	private float grandTotal;

	private String remarks;

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

	public String getBillDate() {
		return billDate;
	}

	public void setBillDate(String billDate) {
		this.billDate = billDate;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
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

	public float getGrandTotal() {
		return grandTotal;
	}

	public void setGrandTotal(float grandTotal) {
		this.grandTotal = grandTotal;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	@Override
	public String toString() {
		return "BillwiseConsumption [billTempId=" + billTempId + ", billId=" + billId + ", billDate=" + billDate
				+ ", custName=" + custName + ", collectedAmt=" + collectedAmt + ", outstandingAmt=" + outstandingAmt
				+ ", collectionPaymode=" + collectionPaymode + ", cratesOpBal=" + cratesOpBal + ", cratesIssued="
				+ cratesIssued + ", cratesReceived=" + cratesReceived + ", cratesClBal=" + cratesClBal + ", grandTotal="
				+ grandTotal + ", remarks=" + remarks + "]";
	}
	
	

}
