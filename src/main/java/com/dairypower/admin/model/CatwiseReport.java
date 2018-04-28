package com.dairypower.admin.model;

public class CatwiseReport {

	
	private int itemCatId;
	
	private int itemId;
	
	private String catName;
	
	private int billQty;
	
	private float rate;
	
	private int returnQty;
	
	private int leakageQty;
	
	private float igstPer;
	
	private float cgstPer;
	
	private float sgstPer;

	public int getItemCatId() {
		return itemCatId;
	}

	public int getItemId() {
		return itemId;
	}

	public String getCatName() {
		return catName;
	}

	public int getBillQty() {
		return billQty;
	}

	public float getRate() {
		return rate;
	}

	public int getReturnQty() {
		return returnQty;
	}

	public int getLeakageQty() {
		return leakageQty;
	}

	public float getIgstPer() {
		return igstPer;
	}

	public float getCgstPer() {
		return cgstPer;
	}

	public float getSgstPer() {
		return sgstPer;
	}

	public void setItemCatId(int itemCatId) {
		this.itemCatId = itemCatId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public void setCatName(String catName) {
		this.catName = catName;
	}

	public void setBillQty(int billQty) {
		this.billQty = billQty;
	}

	public void setRate(float rate) {
		this.rate = rate;
	}

	public void setReturnQty(int returnQty) {
		this.returnQty = returnQty;
	}

	public void setLeakageQty(int leakageQty) {
		this.leakageQty = leakageQty;
	}

	public void setIgstPer(float igstPer) {
		this.igstPer = igstPer;
	}

	public void setCgstPer(float cgstPer) {
		this.cgstPer = cgstPer;
	}

	public void setSgstPer(float sgstPer) {
		this.sgstPer = sgstPer;
	}

	@Override
	public String toString() {
		return "CatwiseReport [itemCatId=" + itemCatId + ", itemId=" + itemId + ", catName=" + catName + ", billQty="
				+ billQty + ", rate=" + rate + ", returnQty=" + returnQty + ", leakageQty=" + leakageQty + ", igstPer="
				+ igstPer + ", cgstPer=" + cgstPer + ", sgstPer=" + sgstPer + "]";
	}

    
	
}
