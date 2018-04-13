package com.dairypower.admin.model;
 
public class ItemCategory  {

	 
	private int catId; 
	private String catName; 
	private int isUsed;

	public int getCatId() {
		return catId;
	}

	public void setCatId(int catId) {
		this.catId = catId;
	}

	public String getCatName() {
		return catName;
	}

	public void setCatName(String catName) {
		this.catName = catName;
	}

	public int getIsUsed() {
		return isUsed;
	}

	public void setIsUsed(int isUsed) {
		this.isUsed = isUsed;
	}

	@Override
	public String toString() {
		return "ItemCategory [catId=" + catId + ", catName=" + catName + ", isUsed=" + isUsed + "]";
	}
	
}
