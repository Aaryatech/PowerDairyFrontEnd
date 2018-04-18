package com.dairypower.admin.model;


public class BillDetail {


	private int billDetailId; 
	 
	private int billTempId;
	
	private String batchNo;
	
	private int itemId; 
	
	private String itemName; 
	
	private float rate;
	
	private int billQty;
	
	private int returnQty;
	
	private int distLeakageQty;
	
	private float cgstPer;
	
	private float sgstPer;
	
	private float igstPer;

	
	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public int getBillDetailId() {
		return billDetailId;
	}

	public void setBillDetailId(int billDetailId) {
		this.billDetailId = billDetailId;
	}

	public int getBillTempId() {
		return billTempId;
	}

	public void setBillTempId(int billTempId) {
		this.billTempId = billTempId;
	}

	public String getBatchNo() {
		return batchNo;
	}

	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}

	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public float getRate() {
		return rate;
	}

	public void setRate(float rate) {
		this.rate = rate;
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

	public void setReturnQty(int returnQty) {
		this.returnQty = returnQty;
	}

	public int getDistLeakageQty() {
		return distLeakageQty;
	}

	public void setDistLeakageQty(int distLeakageQty) {
		this.distLeakageQty = distLeakageQty;
	}

	public float getCgstPer() {
		return cgstPer;
	}

	public void setCgstPer(float cgstPer) {
		this.cgstPer = cgstPer;
	}

	public float getSgstPer() {
		return sgstPer;
	}

	public void setSgstPer(float sgstPer) {
		this.sgstPer = sgstPer;
	}

	public float getIgstPer() {
		return igstPer;
	}

	public void setIgstPer(float igstPer) {
		this.igstPer = igstPer;
	}

	@Override
	public String toString() {
		return "BillDetail [billDetailId=" + billDetailId + ", billTempId=" + billTempId + ", batchNo=" + batchNo
				+ ", itemId=" + itemId + ", rate=" + rate + ", billQty=" + billQty + ", returnQty=" + returnQty
				+ ", distLeakageQty=" + distLeakageQty + ", cgstPer=" + cgstPer + ", sgstPer=" + sgstPer + ", igstPer="
				+ igstPer + "]";
	}
	
	
	
}
