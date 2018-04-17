package com.dairypower.admin.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

public class PoHeader {

	 
	private int poHeaderId; 
	private int poId; 
	private String poDate; 
	private String poDatetime;  
	private int cratesRecievedQty; 
	private String poRemarks; 
	private int userId; 
	private float poTotal;
	List<PoDetail> poDetailList;
	public int getPoHeaderId() {
		return poHeaderId;
	}
	public void setPoHeaderId(int poHeaderId) {
		this.poHeaderId = poHeaderId;
	}
	public int getPoId() {
		return poId;
	}
	public void setPoId(int poId) {
		this.poId = poId;
	}
	@JsonFormat(locale = "hi",timezone = "Asia/Kolkata", pattern = "dd-MM-yyyy")
	public String getPoDate() {
		return poDate;
	}
	public void setPoDate(String poDate) {
		this.poDate = poDate;
	}
	public String getPoDatetime() {
		return poDatetime;
	}
	public void setPoDatetime(String poDatetime) {
		this.poDatetime = poDatetime;
	}
	public int getCratesRecievedQty() {
		return cratesRecievedQty;
	}
	public void setCratesRecievedQty(int cratesRecievedQty) {
		this.cratesRecievedQty = cratesRecievedQty;
	}
	public String getPoRemarks() {
		return poRemarks;
	}
	public void setPoRemarks(String poRemarks) {
		this.poRemarks = poRemarks;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public List<PoDetail> getPoDetailList() {
		return poDetailList;
	}
	public void setPoDetailList(List<PoDetail> poDetailList) {
		this.poDetailList = poDetailList;
	}
	
	public float getPoTotal() {
		return poTotal;
	}
	public void setPoTotal(float poTotal) {
		this.poTotal = poTotal;
	}
	@Override
	public String toString() {
		return "PoHeader [poHeaderId=" + poHeaderId + ", poId=" + poId + ", poDate=" + poDate + ", poDatetime="
				+ poDatetime + ", cratesRecievedQty=" + cratesRecievedQty + ", poRemarks=" + poRemarks + ", userId="
				+ userId + ", poTotal=" + poTotal + ", poDetailList=" + poDetailList + "]";
	}
	
    
	 
	
	
}
