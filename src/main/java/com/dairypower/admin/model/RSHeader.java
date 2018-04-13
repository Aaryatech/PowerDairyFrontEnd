package com.dairypower.admin.model;
 

public class RSHeader {
	 
	private int rsHeaderId;  
	private String rsName;  
	private int isUsed;
	public int getRsHeaderId() {
		return rsHeaderId;
	}
	public void setRsHeaderId(int rsHeaderId) {
		this.rsHeaderId = rsHeaderId;
	}
	public String getRsName() {
		return rsName;
	}
	public void setRsName(String rsName) {
		this.rsName = rsName;
	}
	public int getIsUsed() {
		return isUsed;
	}
	public void setIsUsed(int isUsed) {
		this.isUsed = isUsed;
	}
	@Override
	public String toString() {
		return "RSHeader [rsHeaderId=" + rsHeaderId + ", rsName=" + rsName + ", isUsed=" + isUsed + "]";
	}
	
	

}
