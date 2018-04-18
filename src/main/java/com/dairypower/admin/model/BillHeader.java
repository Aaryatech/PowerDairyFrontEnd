package com.dairypower.admin.model;

import java.util.List;

public class BillHeader{
	

	private int billTempId;
	
	private int billId;
	
	private String billDate;
	
	private int custId;
	
	private int vehId;
	
	private float collectedAmt;
	
	private float outstandingAmt;
	
	private int collectionPaymode;
	
	private int cratesOpBal;
	
	private int cratesIssued;
	
	private int cratesReceived;
	
	private int cratesClBal;

	private String remarks;
	
	private int isSettled;
	
	List<BillDetail> billDetailList;
	
	

	public List<BillDetail> getBillDetailList() {
		return billDetailList;
	}

	public void setBillDetailList(List<BillDetail> billDetailList) {
		this.billDetailList = billDetailList;
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
	public String getBillDate() {
		return billDate;
	}

	public void setBillDate(String billDate) {
		this.billDate = billDate;
	}

	public int getCustId() {
		return custId;
	}

	public void setCustId(int custId) {
		this.custId = custId;
	}

	public int getVehId() {
		return vehId;
	}

	public void setVehId(int vehId) {
		this.vehId = vehId;
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
		return "BillHeader [billTempId=" + billTempId + ", billId=" + billId + ", billDate=" + billDate + ", custId="
				+ custId + ", vehId=" + vehId + ", collectedAmt=" + collectedAmt + ", outstandingAmt=" + outstandingAmt
				+ ", collectionPaymode=" + collectionPaymode + ", cratesOpBal=" + cratesOpBal + ", cratesIssued="
				+ cratesIssued + ", cratesReceived=" + cratesReceived + ", cratesClBal=" + cratesClBal + ", remarks="
				+ remarks + ", isSettled=" + isSettled + "]";
	}

    
	
	

}
