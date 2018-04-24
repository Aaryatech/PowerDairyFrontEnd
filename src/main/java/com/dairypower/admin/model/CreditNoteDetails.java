package com.dairypower.admin.model;



public class CreditNoteDetails {

    private int crnDetailId;
	
    private int crnHeaderId;
	
    private int batchId;

    private String packDate;
	
    private int itemId;
    
    private String itemName;
	
    private int qty;
	
    private float rate;

	public int getCrnDetailId() {
		return crnDetailId;
	}

	public int getCrnHeaderId() {
		return crnHeaderId;
	}

	public int getBatchId() {
		return batchId;
	}

	public String getPackDate() {
		return packDate;
	}

	public int getItemId() {
		return itemId;
	}

	public String getItemName() {
		return itemName;
	}

	public int getQty() {
		return qty;
	}

	public float getRate() {
		return rate;
	}

	public void setCrnDetailId(int crnDetailId) {
		this.crnDetailId = crnDetailId;
	}

	public void setCrnHeaderId(int crnHeaderId) {
		this.crnHeaderId = crnHeaderId;
	}

	public void setBatchId(int batchId) {
		this.batchId = batchId;
	}

	public void setPackDate(String packDate) {
		this.packDate = packDate;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public void setRate(float rate) {
		this.rate = rate;
	}

	@Override
	public String toString() {
		return "CreditNoteDetails [crnDetailId=" + crnDetailId + ", crnHeaderId=" + crnHeaderId + ", batchId=" + batchId
				+ ", packDate=" + packDate + ", itemId=" + itemId + ", itemName=" + itemName + ", qty=" + qty
				+ ", rate=" + rate + "]";
	}
    
    
}
