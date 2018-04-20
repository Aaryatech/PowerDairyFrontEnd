package com.dairypower.admin.model;

import com.fasterxml.jackson.annotation.JsonFormat;

public class MfgReturn  {
	
	 
	private int tReturnId; 
	private String date; 
	private String datetime; 
	private String batchId; 
	private int itemId; 
	private int returnQty; 
	private int entryBy; 
	private String remark;
	private int cratesReturnQty;
	
	
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
	public int getReturnQty() {
		return returnQty;
	}
	public void setReturnQty(int returnQty) {
		this.returnQty = returnQty;
	}
	public int getEntryBy() {
		return entryBy;
	}
	public void setEntryBy(int entryBy) {
		this.entryBy = entryBy;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	public int getCratesReturnQty() {
		return cratesReturnQty;
	}
	public void setCratesReturnQty(int cratesReturnQty) {
		this.cratesReturnQty = cratesReturnQty;
	}
	@Override
	public String toString() {
		return "MfgReturn [tReturnId=" + tReturnId + ", date=" + date + ", datetime=" + datetime + ", batchId="
				+ batchId + ", itemId=" + itemId + ", returnQty=" + returnQty + ", entryBy=" + entryBy + ", remark="
				+ remark + ", cratesReturnQty=" + cratesReturnQty + "]";
	}
	
 
	
	
	
}
