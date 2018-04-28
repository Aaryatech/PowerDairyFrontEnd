package com.dairypower.admin.model;

import java.sql.Date;

public class VehicleWiseReport {
	
    private int tVehId;
	
	private int vehId;

	private String vehName;

	private Date date;

	private int inKms;

	private int outKm;
	
	private int totalKm;

	private String custName;

	
	
	public String getVehName() {
		return vehName;
	}

	public void setVehName(String vehName) {
		this.vehName = vehName;
	}

	public int getVehId() {
		return vehId;
	}

	public void setVehId(int vehId) {
		this.vehId = vehId;
	}

	public int gettVehId() {
		return tVehId;
	}

	public void settVehId(int tVehId) {
		this.tVehId = tVehId;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getInKms() {
		return inKms;
	}

	public void setInKms(int inKms) {
		this.inKms = inKms;
	}

	public int getOutKm() {
		return outKm;
	}

	public void setOutKm(int outKm) {
		this.outKm = outKm;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}
	
	

	public int getTotalKm() {
		return totalKm;
	}

	public void setTotalKm(int totalKm) {
		this.totalKm = totalKm;
	}

	@Override
	public String toString() {
		return "VehicleWiseReport [tVehId=" + tVehId + ", vehId=" + vehId + ", date=" + date + ", inKms=" + inKms
				+ ", outKm=" + outKm + ", totalKm=" + totalKm + ", custName=" + custName + "]";
	}

	
}
