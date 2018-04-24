package com.dairypower.admin.model;

import java.sql.Date;

public class VehicleWiseReport {
	
private int tVehId;
	
	private int vehId;

	private Date date;

	private int inKms;

	private int outKm;

	private String custName;

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

	@Override
	public String toString() {
		return "VehiclewiseReport [vehId=" + vehId + ", tVehId=" + tVehId + ", date=" + date + ", inKms=" + inKms
				+ ", outKm=" + outKm + ", custName=" + custName + "]";
	}


}
