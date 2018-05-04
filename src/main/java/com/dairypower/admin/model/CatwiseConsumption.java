package com.dairypower.admin.model;

public class CatwiseConsumption {

	private int billDetailId;
	
	private int billTempId;

	private int itemId;
	
	private int itemCatId;

	private int billQty;

	private int returnQty;

	private int distLeakageQty;

	private float cgstPer;

	private float igstPer;

	private float sgstPer;

	private float rate;

	private String itemName;

	private int expireQty;
	
	private int leakageQty;
	
	
	public int getExpireQty() {
		return expireQty;
	}

	public int getLeakageQty() {
		return leakageQty;
	}

	public void setExpireQty(int expireQty) {
		this.expireQty = expireQty;
	}

	public void setLeakageQty(int leakageQty) {
		this.leakageQty = leakageQty;
	}

	public int getBillDetailId() {
		return billDetailId;
	}

	public int getItemCatId() {
		return itemCatId;
	}

	public void setBillDetailId(int billDetailId) {
		this.billDetailId = billDetailId;
	}

	public void setItemCatId(int itemCatId) {
		this.itemCatId = itemCatId;
	}

	public int getBillTempId() {
		return billTempId;
	}

	public void setBillTempId(int billTempId) {
		this.billTempId = billTempId;
	}

	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public int getBillQty() {
		return billQty;
	}

	public void setBillQty(int billQty) {
		this.billQty = billQty;
	}

	public int getReturnQty() {
		return returnQty;
	}

	public int getDistLeakageQty() {
		return distLeakageQty;
	}

	public void setDistLeakageQty(int distLeakageQty) {
		this.distLeakageQty = distLeakageQty;
	}

	public void setReturnQty(int returnQty) {
		this.returnQty = returnQty;
	}

	public float getCgstPer() {
		return cgstPer;
	}

	public void setCgstPer(float cgstPer) {
		this.cgstPer = cgstPer;
	}

	public float getIgstPer() {
		return igstPer;
	}

	public void setIgstPer(float igstPer) {
		this.igstPer = igstPer;
	}

	public float getSgstPer() {
		return sgstPer;
	}

	public void setSgstPer(float sgstPer) {
		this.sgstPer = sgstPer;
	}

	public float getRate() {
		return rate;
	}

	public void setRate(float rate) {
		this.rate = rate;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	@Override
	public String toString() {
		return "CatwiseConsumption [billDetailId=" + billDetailId + ", billTempId=" + billTempId + ", itemId=" + itemId
				+ ", itemCatId=" + itemCatId + ", billQty=" + billQty + ", returnQty=" + returnQty + ", distLeakageQty="
				+ distLeakageQty + ", cgstPer=" + cgstPer + ", igstPer=" + igstPer + ", sgstPer=" + sgstPer + ", rate="
				+ rate + ", itemName=" + itemName + ", expireQty=" + expireQty + ", leakageQty=" + leakageQty + "]";
	}
    
}



