package com.dairypower.admin.model;



public class DatewiseSaleReport{


	private int billTempId;
	
	private int billId;

	private String billDate;

	private float collectedAmt;

	private float outstandingAmt;
		
	private int cratesOpBal;

	private int cratesIssued;


	private int cratesReceived;
	
	private float grandTotal;

	public int getBillTempId() {
		return billTempId;
	}

	public int getBillId() {
		return billId;
	}

	public String getBillDate() {
		return billDate;
	}

	public float getCollectedAmt() {
		return collectedAmt;
	}

	public float getOutstandingAmt() {
		return outstandingAmt;
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

	public void setCollectedAmt(float collectedAmt) {
		this.collectedAmt = collectedAmt;
	}

	public void setOutstandingAmt(float outstandingAmt) {
		this.outstandingAmt = outstandingAmt;
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

	public void setGrandTotal(float grandTotal) {
		this.grandTotal = grandTotal;
	}

	@Override
	public String toString() {
		return "DatewiseSaleReport [billTempId=" + billTempId + ", billId=" + billId + ", billDate=" + billDate
				+ ", collectedAmt=" + collectedAmt + ", outstandingAmt=" + outstandingAmt + ", cratesOpBal="
				+ cratesOpBal + ", cratesIssued=" + cratesIssued + ", cratesReceived=" + cratesReceived
				+ ", grandTotal=" + grandTotal + "]";
	}
	
	
}
