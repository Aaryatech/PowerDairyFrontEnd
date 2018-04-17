package com.dairypower.admin.model;
 
public class GetItem  {

	 
	private int itemId; 
	private String itemCode; 
	private String itemName; 
	private int itemCatId; 
	private String catName; 
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
	private String uomName; 
	private int isUsed;
	public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	public String getItemCode() {
		return itemCode;
	}
	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public int getItemCatId() {
		return itemCatId;
	}
	public void setItemCatId(int itemCatId) {
		this.itemCatId = itemCatId;
	}
	public String getCatName() {
		return catName;
	}
	public void setCatName(String catName) {
		this.catName = catName;
	}
	public float getPurchaseRate() {
		return purchaseRate;
	}
	public void setPurchaseRate(float purchaseRate) {
		this.purchaseRate = purchaseRate;
	}
	public int getMinQty() {
		return minQty;
	}
	public void setMinQty(int minQty) {
		this.minQty = minQty;
	}
	public int getMaxQty() {
		return maxQty;
	}
	public void setMaxQty(int maxQty) {
		this.maxQty = maxQty;
	}
	public int getHubExpDays() {
		return hubExpDays;
	}
	public void setHubExpDays(int hubExpDays) {
		this.hubExpDays = hubExpDays;
	}
	public int getRetailExpDays() {
		return retailExpDays;
	}
	public void setRetailExpDays(int retailExpDays) {
		this.retailExpDays = retailExpDays;
	}
	public float getSgstPer() {
		return sgstPer;
	}
	public void setSgstPer(float sgstPer) {
		this.sgstPer = sgstPer;
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
	public String getHsnCode() {
		return hsnCode;
	}
	public void setHsnCode(String hsnCode) {
		this.hsnCode = hsnCode;
	}
	public int getUomId() {
		return uomId;
	}
	public void setUomId(int uomId) {
		this.uomId = uomId;
	}
	public String getUomName() {
		return uomName;
	}
	public void setUomName(String uomName) {
		this.uomName = uomName;
	}
	public int getIsUsed() {
		return isUsed;
	}
	public void setIsUsed(int isUsed) {
		this.isUsed = isUsed;
	}
	@Override
	public String toString() {
		return "GetItem [itemId=" + itemId + ", itemCode=" + itemCode + ", itemName=" + itemName + ", itemCatId="
				+ itemCatId + ", catName=" + catName + ", purchaseRate=" + purchaseRate + ", minQty=" + minQty
				+ ", maxQty=" + maxQty + ", hubExpDays=" + hubExpDays + ", retailExpDays=" + retailExpDays
				+ ", sgstPer=" + sgstPer + ", cgstPer=" + cgstPer + ", igstPer=" + igstPer + ", hsnCode=" + hsnCode
				+ ", uomId=" + uomId + ", uomName=" + uomName + ", isUsed=" + isUsed + "]";
	} 
	
	
	
	
}
