package com.dairypower.admin.model;
 

public class CustomerType {
	 
	private int custTypeId; 
	private String custTypeName; 
	private int isUsed;
	public int getCustTypeId() {
		return custTypeId;
	}
	public void setCustTypeId(int custTypeId) {
		this.custTypeId = custTypeId;
	}
	public String getCustTypeName() {
		return custTypeName;
	}
	public void setCustTypeName(String custTypeName) {
		this.custTypeName = custTypeName;
	}
	public int getIsUsed() {
		return isUsed;
	}
	public void setIsUsed(int isUsed) {
		this.isUsed = isUsed;
	}
	@Override
	public String toString() {
		return "CustomerType [custTypeId=" + custTypeId + ", custTypeName=" + custTypeName + ", isUsed=" + isUsed + "]";
	}
	
	

}
