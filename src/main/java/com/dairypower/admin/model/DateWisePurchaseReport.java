package com.dairypower.admin.model;

public class DateWisePurchaseReport {

	private int poHeaderId;
	private String Day;
	private String poDate;
	private float poTotal;
	private int cratesReceivedQty;

	public int getPoHeaderId() {
		return poHeaderId;
	}

	public void setPoHeaderId(int poHeaderId) {
		this.poHeaderId = poHeaderId;
	}

	public String getDay() {
		return Day;
	}

	public void setDay(String day) {
		Day = day;
	}

	public String getPoDate() {
		return poDate;
	}

	public void setPoDate(String poDate) {
		this.poDate = poDate;
	}

	public float getPoTotal() {
		return poTotal;
	}

	public void setPoTotal(float poTotal) {
		this.poTotal = poTotal;
	}

	public int getCratesReceivedQty() {
		return cratesReceivedQty;
	}

	public void setCratesReceivedQty(int cratesReceivedQty) {
		this.cratesReceivedQty = cratesReceivedQty;
	}

	@Override
	public String toString() {
		return "DateWisePurchaseReport [poHeaderId=" + poHeaderId + ", Day=" + Day + ", poDate=" + poDate + ", poTotal="
				+ poTotal + ", cratesReceivedQty=" + cratesReceivedQty + "]";
	}

}
