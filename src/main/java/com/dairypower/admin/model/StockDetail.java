package com.dairypower.admin.model;
 
public class StockDetail  {

	 
	private int stockDetailId; 
	private int stockHeaderId; 
	private int itemId; 
	private int closingQty;
	private int opStock;
	
	public int getStockDetailId() {
		return stockDetailId;
	}

	public void setStockDetailId(int stockDetailId) {
		this.stockDetailId = stockDetailId;
	}

	public int getStockHeaderId() {
		return stockHeaderId;
	}

	public void setStockHeaderId(int stockHeaderId) {
		this.stockHeaderId = stockHeaderId;
	}

	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public int getClosingQty() {
		return closingQty;
	}

	public void setClosingQty(int closingQty) {
		this.closingQty = closingQty;
	}

	public int getOpStock() {
		return opStock;
	}

	public void setOpStock(int opStock) {
		this.opStock = opStock;
	}

	@Override
	public String toString() {
		return "StockDetail [stockDetailId=" + stockDetailId + ", stockHeaderId=" + stockHeaderId + ", itemId=" + itemId
				+ ", closingQty=" + closingQty + ", opStock=" + opStock + "]";
	}
	
	
	

}
