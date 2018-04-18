package com.dairypower.admin.model;


public class GetUser{

	private int userId; 
	
	private String userName;
	
	private String userMobNo;
	
	private int userTypeId;

	private String userType;
	
	private int isUsed;

	
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserMobNo() {
		return userMobNo;
	}

	public void setUserMobNo(String userMobNo) {
		this.userMobNo = userMobNo;
	}

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
		return "GetUser [userId=" + userId + ", userName=" + userName + ", userMobNo=" + userMobNo + ", userTypeId="
				+ userTypeId + ", userType=" + userType + ", isUsed=" + isUsed + "]";
	}
	
	
}
