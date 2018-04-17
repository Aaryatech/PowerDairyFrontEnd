package com.dairypower.admin.model;

import java.util.List;

public class GetPoHeader  {

 
	private int poHeaderId; 
	private int poId; 
	private String poDate; 
	private String poDatetime; 
	private String poRemarks; 
	private int userId; 
	private String userName; 
	private int cratesRecievedQty; 
	private float poTotal;
	List<GetPoDetail> poDetailList;
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
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getCratesRecievedQty() {
		return cratesRecievedQty;
	}
	public void setCratesRecievedQty(int cratesRecievedQty) {
		this.cratesRecievedQty = cratesRecievedQty;
	}
	public List<GetPoDetail> getPoDetailList() {
		return poDetailList;
	}
	public void setPoDetailList(List<GetPoDetail> poDetailList) {
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
		return "GetPoHeader [poHeaderId=" + poHeaderId + ", poId=" + poId + ", poDate=" + poDate + ", poDatetime="
				+ poDatetime + ", poRemarks=" + poRemarks + ", userId=" + userId + ", userName=" + userName
				+ ", cratesRecievedQty=" + cratesRecievedQty + ", poTotal=" + poTotal + ", poDetailList=" + poDetailList
				+ "]";
	}
	
	
	 
}
