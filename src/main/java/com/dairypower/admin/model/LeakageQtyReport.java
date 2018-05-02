package com.dairypower.admin.model;
 

public class LeakageQtyReport {
	
	 
	private int crnDetailId; 
	private String itemName; 
	private int itemId;  
	private int custId; 
	private String custName; 
	private int leakageQty; 
	private int expireQty;
	public int getCrnDetailId() {
		return crnDetailId;
	}
	public void setCrnDetailId(int crnDetailId) {
		this.crnDetailId = crnDetailId;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	public int getCustId() {
		return custId;
	}
	public void setCustId(int custId) {
		this.custId = custId;
	}
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	public int getLeakageQty() {
		return leakageQty;
	}
	public void setLeakageQty(int leakageQty) {
		this.leakageQty = leakageQty;
	}
	public int getExpireQty() {
		return expireQty;
	}
	public void setExpireQty(int expireQty) {
		this.expireQty = expireQty;
	}
	@Override
	public String toString() {
		return "LeakageQtyReport [crnDetailId=" + crnDetailId + ", itemName=" + itemName + ", itemId=" + itemId
				+ ", custId=" + custId + ", custName=" + custName + ", leakageQty=" + leakageQty + ", expireQty="
				+ expireQty + "]";
	}
	
	

}
