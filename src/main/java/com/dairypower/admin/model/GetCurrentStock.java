package com.dairypower.admin.model;
 
public class GetCurrentStock {
	
	 
	private int itemId; 
	private String itemName; 
	private int openingStock;  
	private int totalPurchase; 
	private int totalSale; 
	private int returnQty; 
	private int hubReturnQty;

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

	public int getOpeningStock() {
		return openingStock;
	}

	public void setOpeningStock(int openingStock) {
		this.openingStock = openingStock;
	}

	public int getTotalPurchase() {
		return totalPurchase;
	}

	public void setTotalPurchase(int totalPurchase) {
		this.totalPurchase = totalPurchase;
	}

	public int getTotalSale() {
		return totalSale;
	}

	public void setTotalSale(int totalSale) {
		this.totalSale = totalSale;
	}

	public int getReturnQty() {
		return returnQty;
	}

	public void setReturnQty(int returnQty) {
		this.returnQty = returnQty;
	}

	public int getHubReturnQty() {
		return hubReturnQty;
	}

	public void setHubReturnQty(int hubReturnQty) {
		this.hubReturnQty = hubReturnQty;
	}

	@Override
	public String toString() {
		return "GetCurrentStock [itemId=" + itemId + ", itemName=" + itemName + ", openingStock=" + openingStock
				+ ", totalPurchase=" + totalPurchase + ", totalSale=" + totalSale + ", returnQty=" + returnQty
				+ ", hubReturnQty=" + hubReturnQty + "]";
	}

	 

}
