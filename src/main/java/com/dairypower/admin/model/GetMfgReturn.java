package com.dairypower.admin.model;

import com.fasterxml.jackson.annotation.JsonFormat;

public class GetMfgReturn {
	 
	private int tReturnId; 
	private String date; 
	private String datetime; 
	private String batchId; 
	private int itemId; 
	private String itemName; 
	private int itemReturnQy; 
	private int cratesReturnQty; 
	private int entryBy; 
	private String userName; 
	private String remark;
	public int gettReturnId() {
		return tReturnId;
	}
	public void settReturnId(int tReturnId) {
		this.tReturnId = tReturnId;
	}
	@JsonFormat(locale = "hi",timezone = "Asia/Kolkata", pattern = "dd-MM-yyyy")
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getDatetime() {
		return datetime;
	}
	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}
	public String getBatchId() {
		return batchId;
	}
	public void setBatchId(String batchId) {
		this.batchId = batchId;
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
	public int getItemReturnQy() {
		return itemReturnQy;
	}
	public void setItemReturnQy(int itemReturnQy) {
		this.itemReturnQy = itemReturnQy;
	}
	public int getCratesReturnQty() {
		return cratesReturnQty;
	}
	public void setCratesReturnQty(int cratesReturnQty) {
		this.cratesReturnQty = cratesReturnQty;
	}
	public int getEntryBy() {
		return entryBy;
	}
	public void setEntryBy(int entryBy) {
		this.entryBy = entryBy;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	

}
