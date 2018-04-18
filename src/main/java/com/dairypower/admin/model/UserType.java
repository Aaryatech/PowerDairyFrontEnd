package com.dairypower.admin.model;

public class UserType{
	
	
	private int userTypeId;

	private String userType;
	
	private int isUsed;

	public int getUserTypeId() {
		return userTypeId;
	}

	public void setUserTypeId(int userTypeId) {
		this.userTypeId = userTypeId;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public int getIsUsed() {
		return isUsed;
	}

	public void setIsUsed(int isUsed) {
		this.isUsed = isUsed;
	}

	@Override
	public String toString() {
		return "UserType [userTypeId=" + userTypeId + ", userType=" + userType + ", isUsed=" + isUsed + "]";
	}

}
