package com.dairypower.admin.model;


public class CustomerwiseReport {

	
	private int billTempId;

	private int custId;
	
	private int billId;
	
	private int inKms;
	
	private int outKm;

	private String billDate;

	private String custName;

	private float collectedAmt;

	private float outstandingAmt;
	
	private int collectionPaymode;
	
	private int cratesOpBal;

	private int cratesIssued;


	private int crates_received;
	
	private float grandTotal;

	public int getBillTempId() {
		return billTempId;
	}

	public void setBillTempId(int billTempId) {
		this.billTempId = billTempId;
	}

	public int getCustId() {
		return custId;
	}

	public void setCustId(int custId) {
		this.custId = custId;
	}

	public int getBillId() {
		return billId;
	}

	public void setBillId(int billId) {
		this.billId = billId;
	}

	public int getInKms() {
		return inKms;
	}

	public void setInKms(int inKms) {
		this.inKms = inKms;
	}

	public int getOutKm() {
		return outKm;
	}

	public void setOutKm(int outKm) {
		this.outKm = outKm;
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


	public int getCrates_received() {
		return crates_received;
	}

	public void setCrates_received(int crates_received) {
		this.crates_received = crates_received;
	}

	public float getGrandTotal() {
		return grandTotal;
	}

	public void setGrandTotal(float grandTotal) {
		this.grandTotal = grandTotal;
	}

	@Override
	public String toString() {
		return "CustomerwiseReport [billTempId=" + billTempId + ", custId=" + custId + ", billId=" + billId + ", inKms="
				+ inKms + ", outKm=" + outKm + ", billDate=" + billDate + ", custName=" + custName + ", collectedAmt="
				+ collectedAmt + ", outstandingAmt=" + outstandingAmt + ", collectionPaymode=" + collectionPaymode
				+ ", cratesOpBal=" + cratesOpBal + ", cratesIssued=" + cratesIssued  
				+ ", crates_received=" + crates_received + ", grandTotal=" + grandTotal + "]";
	}
	
	

}
