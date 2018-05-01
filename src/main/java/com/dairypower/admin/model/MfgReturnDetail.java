package com.dairypower.admin.model;
 

public class MfgReturnDetail {
	
	 
	private int tReturnDetailId; 
	private int tReturnId; 
	private String batchNo; 
	private int itemId; 
	private int itemReturnQty; 
	private String itemName;
	private int poDetailId;
	public int gettReturnDetailId() {
		return tReturnDetailId;
	}
	public void settReturnDetailId(int tReturnDetailId) {
		this.tReturnDetailId = tReturnDetailId;
	}
	public int gettReturnId() {
		return tReturnId;
	}
	public void settReturnId(int tReturnId) {
		this.tReturnId = tReturnId;
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
	public int getItemReturnQty() {
		return itemReturnQty;
	}
	public void setItemReturnQty(int itemReturnQty) {
		this.itemReturnQty = itemReturnQty;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public int getPoDetailId() {
		return poDetailId;
	}
	public void setPoDetailId(int poDetailId) {
		this.poDetailId = poDetailId;
	}
	@Override
	public String toString() {
		return "MfgReturnDetail [tReturnDetailId=" + tReturnDetailId + ", tReturnId=" + tReturnId + ", batchNo="
				+ batchNo + ", itemId=" + itemId + ", itemReturnQty=" + itemReturnQty + ", itemName=" + itemName
				+ ", poDetailId=" + poDetailId + "]";
	} 
	
	

}
