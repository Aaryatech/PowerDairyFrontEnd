package com.dairypower.admin.model;
 
public class GetPoDetail {

	 
	private int poDetailId; 
	private int poHeaderId; 
	private String batchNo; 
	private String packingDate; 
	private String mfgDate; 
	private int itemId; 
	private String itemName; 
	private int itemQty; 
	private int shortNo; 
	private int extraNo; 
	private int poLeakageQty;
	private float rate;
	private int isUsed;
	private int balance;
	
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
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
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
	
	public int getBalance() {
		return balance;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}
	@Override
	public String toString() {
		return "GetPoDetail [poDetailId=" + poDetailId + ", poHeaderId=" + poHeaderId + ", batchNo=" + batchNo
				+ ", packingDate=" + packingDate + ", mfgDate=" + mfgDate + ", itemId=" + itemId + ", itemName="
				+ itemName + ", itemQty=" + itemQty + ", shortNo=" + shortNo + ", extraNo=" + extraNo
				+ ", poLeakageQty=" + poLeakageQty + ", rate=" + rate + ", isUsed=" + isUsed + ", balance=" + balance
				+ "]";
	}
	
	

 
	
}
