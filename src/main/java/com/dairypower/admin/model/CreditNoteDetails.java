package com.dairypower.admin.model;



public class CreditNoteDetails {

    private int crnDetailId;
	
    private int crnHeaderId;
	
    private int batchId;

    private String packDate;
	
    private int itemId;
    
    private String itemName;
	
    private int scrapType;
	
    private int leakageQty;
	
    private int expireQty;
	
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

	

	public int getScrapType() {
		return scrapType;
	}

	public int getLeakageQty() {
		return leakageQty;
	}

	public int getExpireQty() {
		return expireQty;
	}

	public void setScrapType(int scrapType) {
		this.scrapType = scrapType;
	}

	public void setLeakageQty(int leakageQty) {
		this.leakageQty = leakageQty;
	}

	public void setExpireQty(int expireQty) {
		this.expireQty = expireQty;
	}

	public void setRate(float rate) {
		this.rate = rate;
	}

	@Override
	public String toString() {
		return "CreditNoteDetails [crnDetailId=" + crnDetailId + ", crnHeaderId=" + crnHeaderId + ", batchId=" + batchId
				+ ", packDate=" + packDate + ", itemId=" + itemId + ", itemName=" + itemName + ", scrapType="
				+ scrapType + ", leakageQty=" + leakageQty + ", expireQty=" + expireQty + ", rate=" + rate + "]";
	}

    
}
