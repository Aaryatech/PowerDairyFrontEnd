package com.dairypower.admin.model;

import java.util.List;
 

import com.fasterxml.jackson.annotation.JsonFormat;

public class StockHeader  {
	
 
	private int stockHeaderId; 
	private String date; 
	private int status; 
	private int cratesOpQty; 
	private int cratesCloseQty;
    List<StockDetail> stockDetailList;
	
	
	public List<StockDetail> getStockDetailList() {
		return stockDetailList;
	}

	public void setStockDetailList(List<StockDetail> stockDetailList) {
		this.stockDetailList = stockDetailList;
	}

	public int getStockHeaderId() {
		return stockHeaderId;
	}

	public void setStockHeaderId(int stockHeaderId) {
		this.stockHeaderId = stockHeaderId;
	}
	@JsonFormat(locale = "hi",timezone = "Asia/Kolkata", pattern = "dd-MM-yyyy")
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getCratesOpQty() {
		return cratesOpQty;
	}

	public void setCratesOpQty(int cratesOpQty) {
		this.cratesOpQty = cratesOpQty;
	}

	public int getCratesCloseQty() {
		return cratesCloseQty;
	}

	public void setCratesCloseQty(int cratesCloseQty) {
		this.cratesCloseQty = cratesCloseQty;
	}

	@Override
	public String toString() {
		return "StockHeader [stockHeaderId=" + stockHeaderId + ", date=" + date + ", status=" + status
				+ ", cratesOpQty=" + cratesOpQty + ", cratesCloseQty=" + cratesCloseQty + ", stockDetailList="
				+ stockDetailList + "]";
	}

	 
	
}
