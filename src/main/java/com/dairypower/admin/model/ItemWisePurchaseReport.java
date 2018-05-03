package com.dairypower.admin.model;

public class ItemWisePurchaseReport {

	private int itemId;
	private String itemName;
	private float total;
    private int itemQty;
    private int shortNo;
    private int extraNo;
    private int poLeakageQty;
    
    
	public int getItemQty() {
		return itemQty;
	}

	public int getShortNo() {
		return shortNo;
	}

	public int getExtraNo() {
		return extraNo;
	}

	public int getPoLeakageQty() {
		return poLeakageQty;
	}

	public void setItemQty(int itemQty) {
		this.itemQty = itemQty;
	}

	public void setShortNo(int shortNo) {
		this.shortNo = shortNo;
	}

	public void setExtraNo(int extraNo) {
		this.extraNo = extraNo;
	}

	public void setPoLeakageQty(int poLeakageQty) {
		this.poLeakageQty = poLeakageQty;
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

	public float getTotal() {
		return total;
	}

	public void setTotal(float total) {
		this.total = total;
	}

	@Override
	public String toString() {
		return "ItemwisePurchasereport [itemId=" + itemId + ", itemName=" + itemName + ", total=" + total + "]";
	}

}
