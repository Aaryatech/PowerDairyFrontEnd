package com.dairypower.admin.model;
 

public class Uom {
	
	private int uomId;  
	private String uomName; 
	private String uomDescription; 
	private int isUsed;
	public int getUomId() {
		return uomId;
	}
	public void setUomId(int uomId) {
		this.uomId = uomId;
	}
	public String getUomName() {
		return uomName;
	}
	public void setUomName(String uomName) {
		this.uomName = uomName;
	}
	public String getUomDescription() {
		return uomDescription;
	}
	public void setUomDescription(String uomDescription) {
		this.uomDescription = uomDescription;
	}
	public int getIsUsed() {
		return isUsed;
	}
	public void setIsUsed(int isUsed) {
		this.isUsed = isUsed;
	}
	@Override
	public String toString() {
		return "Uom [uomId=" + uomId + ", uomName=" + uomName + ", uomDescription=" + uomDescription + ", isUsed="
				+ isUsed + "]";
	}
	
	

}
