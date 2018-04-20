package com.dairypower.admin.model;

public class Currency {

	
	private int currId;
	
	private String description;
	
	private float currencyValue;
	
	private int isUsed;
	
	public int getCurrId() {
		return currId;
	}

	public String getDescription() {
		return description;
	}

	public float getCurrencyValue() {
		return currencyValue;
	}

	public void setCurrId(int currId) {
		this.currId = currId;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setCurrencyValue(float currencyValue) {
		this.currencyValue = currencyValue;
	}

	
	
	public int getIsUsed() {
		return isUsed;
	}

	public void setIsUsed(int isUsed) {
		this.isUsed = isUsed;
	}

	@Override
	public String toString() {
		return "Currency [currId=" + currId + ", description=" + description + ", currencyValue=" + currencyValue
				+ ", isUsed=" + isUsed + "]";
	}

	
	
}
