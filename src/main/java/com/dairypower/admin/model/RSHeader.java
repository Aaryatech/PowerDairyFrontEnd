package com.dairypower.admin.model;

import java.util.List;

public class RSHeader {
	 
	private int rsHeaderId;  
	private String rsName;  
	private int isUsed;
	List<RsDetail> rsDetailList;
	
	public List<RsDetail> getRsDetailList() {
		return rsDetailList;
	}
	public void setRsDetailList(List<RsDetail> rsDetailList) {
		this.rsDetailList = rsDetailList;
	}
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
