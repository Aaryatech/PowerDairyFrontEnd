package com.dairypower.admin.model;

public class BillWisePurchaseReport {

	private String poId;

	private String poDate;

	private int cratesReceivedQty;

	private float poTotal;

	private int poRemarks;

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

	public int getPoRemarks() {
		return poRemarks;
	}

	public void setPoRemarks(int poRemarks) {
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
				+ cratesReceivedQty + ", poTotal=" + poTotal + ", poRemarks=" + poRemarks + "]";
	}

}
