package com.dairypower.admin.model;


public class BillWisePurchaseReport {

	private String poId;

	private String poDate;

	private int cratesReceivedQty;

	private float poTotal;

	private String poRemarks;
	
	private int poHeaderId;
	private int shortNo;
	private int extraNo;
	private int userId;
	private int poLeakageQty;
	private String userName;

	
	public int getPoHeaderId() {
		return poHeaderId;
	}

	public int getShortNo() {
		return shortNo;
	}

	public int getExtraNo() {
		return extraNo;
	}

	public int getUserId() {
		return userId;
	}

	public int getPoLeakageQty() {
		return poLeakageQty;
	}

	public String getUserName() {
		return userName;
	}

	public void setPoHeaderId(int poHeaderId) {
		this.poHeaderId = poHeaderId;
	}

	public void setShortNo(int shortNo) {
		this.shortNo = shortNo;
	}

	public void setExtraNo(int extraNo) {
		this.extraNo = extraNo;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public void setPoLeakageQty(int poLeakageQty) {
		this.poLeakageQty = poLeakageQty;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPoId() {
		return poId;
	}

	public void setPoId(String poId) {
		this.poId = poId;
	}

	public String getPoDate() {
		return poDate;
	}

	public void setPoDate(String poDate) {
		this.poDate = poDate;
	}

	public int getCratesReceivedQty() {
		return cratesReceivedQty;
	}

	public void setCratesReceivedQty(int cratesReceivedQty) {
		this.cratesReceivedQty = cratesReceivedQty;
	}

	public String getPoRemarks() {
		return poRemarks;
	}

	public void setPoRemarks(String poRemarks) {
		this.poRemarks = poRemarks;
	}

	public float getPoTotal() {
		return poTotal;
	}

	public void setPoTotal(float poTotal) {
		this.poTotal = poTotal;
	}

	@Override
	public String toString() {
		return "BillWisePurchaseReport [poId=" + poId + ", poDate=" + poDate + ", cratesReceivedQty="
				+ cratesReceivedQty + ", poTotal=" + poTotal + ", poRemarks=" + poRemarks + ", poHeaderId=" + poHeaderId
				+ ", shortNo=" + shortNo + ", extraNo=" + extraNo + ", userId=" + userId + ", poLeakageQty="
				+ poLeakageQty + ", userName=" + userName + "]";
	}
    
}
