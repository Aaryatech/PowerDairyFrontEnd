package com.dairypower.admin.model;

public class ItemWisePurchaseReport {

	private int itemId;
	private String itemName;
	private float total;

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
