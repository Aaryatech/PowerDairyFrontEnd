package com.dairypower.admin.model;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Vehicle {
	 
	private int vehId;  
	private String vehName; 
	private int vehType; 
	private int vehOpKms; 
	private String vehOpKmsDate; 
	private int isUsed;
	public int getVehId() {
		return vehId;
	}
	public void setVehId(int vehId) {
		this.vehId = vehId;
	}
	public String getVehName() {
		return vehName;
	}
	public void setVehName(String vehName) {
		this.vehName = vehName;
	}
	public int getVehType() {
		return vehType;
	}
	public void setVehType(int vehType) {
		this.vehType = vehType;
	}
	public int getVehOpKms() {
		return vehOpKms;
	}
	public void setVehOpKms(int vehOpKms) {
		this.vehOpKms = vehOpKms;
	}
	@JsonFormat(locale = "hi",timezone = "Asia/Kolkata", pattern = "dd-MM-yyyy")
	public String getVehOpKmsDate() {
		return vehOpKmsDate;
	}
	public void setVehOpKmsDate(String vehOpKmsDate) {
		this.vehOpKmsDate = vehOpKmsDate;
	}
	public int getIsUsed() {
		return isUsed;
	}
	public void setIsUsed(int isUsed) {
		this.isUsed = isUsed;
	}
	@Override
	public String toString() {
		return "Vehicle [vehId=" + vehId + ", vehName=" + vehName + ", vehType=" + vehType + ", vehOpKms=" + vehOpKms
				+ ", vehOpKmsDate=" + vehOpKmsDate + ", isUsed=" + isUsed + "]";
	}
	
	

}
