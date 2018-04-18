package com.dairypower.admin.model;


public class RsDetail{

	
	private int rsDetailId;
	
	private int rsHeaderId;
	
	private int itemId;
	
	private float rate;

	public int getRsDetailId() {
		return rsDetailId;
	}

	public void setRsDetailId(int rsDetailId) {
		this.rsDetailId = rsDetailId;
	}

	public int getRsHeaderId() {
		return rsHeaderId;
	}

	public void setRsHeaderId(int rsHeaderId) {
		this.rsHeaderId = rsHeaderId;
	}

	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public float getRate() {
		return rate;
	}

	public void setRate(float rate) {
		this.rate = rate;
	}

	@Override
	public String toString() {
		return "RsDetail [rsDetailId=" + rsDetailId + ", rsHeaderId=" + rsHeaderId + ", itemId=" + itemId + ", rate="
				+ rate + "]";
	}
    
	
}
