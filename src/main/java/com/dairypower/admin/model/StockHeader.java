package com.dairypower.admin.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

public class StockHeader  {
	
 
	private int stockHeaderId; 
	private String date; 
	private int status; 
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

	@Override
	public String toString() {
		return "StockHeader [stockHeaderId=" + stockHeaderId + ", date=" + date + ", status=" + status
				+ ", stockDetailList=" + stockDetailList + "]";
	}
	
	
}
