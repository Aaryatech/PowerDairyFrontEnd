package com.dairypower.admin.model;


public class Item {

	private int itemId;
	 
	private String itemCode;
	
	private String itemName;
	
	private int itemCatId;
	
	private float purchaseRate;
	
	private int minQty;
	
	private int maxQty;
	
	private int hubExpDays;
	
	private int retailExpDays;
	
	private float sgstPer;
	
	private float cgstPer;
	
	private float igstPer;
	
	private String hsnCode;
	
	private int uomId;
	
	private int isUsed;

	public int getItemId() {
		return itemId;
	}

	public String getItemCode() {
		return itemCode;
	}

	public String getItemName() {
		return itemName;
	}

	public int getItemCatId() {
		return itemCatId;
	}

	public float getPurchaseRate() {
		return purchaseRate;
	}

	public int getMinQty() {
		return minQty;
	}

	public int getMaxQty() {
		return maxQty;
	}

	public int getHubExpDays() {
		return hubExpDays;
	}

	public int getRetailExpDays() {
		return retailExpDays;
	}

	public float getSgstPer() {
		return sgstPer;
	}

	public float getCgstPer() {
		return cgstPer;
	}

	public float getIgstPer() {
		return igstPer;
	}

	public String getHsnCode() {
		return hsnCode;
	}

	public int getUomId() {
		return uomId;
	}

	public int getIsUsed() {
		return isUsed;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public void setItemCatId(int itemCatId) {
		this.itemCatId = itemCatId;
	}

	public void setPurchaseRate(float purchaseRate) {
		this.purchaseRate = purchaseRate;
	}

	public void setMinQty(int minQty) {
		this.minQty = minQty;
	}

	public void setMaxQty(int maxQty) {
		this.maxQty = maxQty;
	}

	public void setHubExpDays(int hubExpDays) {
		this.hubExpDays = hubExpDays;
	}

	public void setRetailExpDays(int retailExpDays) {
		this.retailExpDays = retailExpDays;
	}

	public void setSgstPer(float sgstPer) {
		this.sgstPer = sgstPer;
	}

	public void setCgstPer(float cgstPer) {
		this.cgstPer = cgstPer;
	}

	public void setIgstPer(float igstPer) {
		this.igstPer = igstPer;
	}

	public void setHsnCode(String hsnCode) {
		this.hsnCode = hsnCode;
	}

	public void setUomId(int uomId) {
		this.uomId = uomId;
	}

	public void setIsUsed(int isUsed) {
		this.isUsed = isUsed;
	}

	@Override
	public String toString() {
		return "Item [itemId=" + itemId + ", itemCode=" + itemCode + ", itemName=" + itemName + ", itemCatId="
				+ itemCatId + ", purchaseRate=" + purchaseRate + ", minQty=" + minQty + ", maxQty=" + maxQty
				+ ", hubExpDays=" + hubExpDays + ", retailExpDays=" + retailExpDays + ", sgstPer=" + sgstPer
				+ ", cgstPer=" + cgstPer + ", igstPer=" + igstPer + ", hsnCode=" + hsnCode + ", uomId=" + uomId
				+ ", isUsed=" + isUsed + "]";
	}
	
	
	
}
