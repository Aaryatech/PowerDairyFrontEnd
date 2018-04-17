package com.dairypower.admin.model;

import com.fasterxml.jackson.annotation.JsonFormat;

public class PoDetail  {

	 
	private int poDetailId; 
	private int poHeaderId; 
	private String batchNo; 
	private String packingDate; 
	private String mfgDate; 
	private int itemId; 
	private int itemQty; 
	private int shortNo; 
	private int extraNo; 
	private int poLeakageQty;
	private float rate;
	private int isUsed;
	
	public int getPoDetailId() {
		return poDetailId;
	}
	public void setPoDetailId(int poDetailId) {
		this.poDetailId = poDetailId;
	}
	public int getPoHeaderId() {
		return poHeaderId;
	}
	public void setPoHeaderId(int poHeaderId) {
		this.poHeaderId = poHeaderId;
	}
	public String getBatchNo() {
		return batchNo;
	}
	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}
	@JsonFormat(locale = "hi",timezone = "Asia/Kolkata", pattern = "dd-MM-yyyy")
	public String getPackingDate() {
		return packingDate;
	}
	public void setPackingDate(String packingDate) {
		this.packingDate = packingDate;
	}
	public String getMfgDate() {
		return mfgDate;
	}
	public void setMfgDate(String mfgDate) {
		this.mfgDate = mfgDate;
	}
	public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	public int getItemQty() {
		return itemQty;
	}
	public void setItemQty(int itemQty) {
		this.itemQty = itemQty;
	}
	public int getShortNo() {
		return shortNo;
	}
	public void setShortNo(int shortNo) {
		this.shortNo = shortNo;
	}
	public int getExtraNo() {
		return extraNo;
	}
	public void setExtraNo(int extraNo) {
		this.extraNo = extraNo;
	}
	public int getPoLeakageQty() {
		return poLeakageQty;
	}
	public void setPoLeakageQty(int poLeakageQty) {
		this.poLeakageQty = poLeakageQty;
	}
	
	public float getRate() {
		return rate;
	}
	public void setRate(float rate) {
		this.rate = rate;
	}
	
	public int getIsUsed() {
		return isUsed;
	}
	public void setIsUsed(int isUsed) {
		this.isUsed = isUsed;
	}
	@Override
	public String toString() {
		return "PoDetail [poDetailId=" + poDetailId + ", poHeaderId=" + poHeaderId + ", batchNo=" + batchNo
				+ ", packingDate=" + packingDate + ", mfgDate=" + mfgDate + ", itemId=" + itemId + ", itemQty="
				+ itemQty + ", shortNo=" + shortNo + ", extraNo=" + extraNo + ", poLeakageQty=" + poLeakageQty
				+ ", rate=" + rate + ", isUsed=" + isUsed + "]";
	}

	 
	
}
