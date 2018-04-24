package com.dairypower.admin.model;


public class TVehicle{


	private int tVehId;
	
	private int vehId;
	
	private int billTempId;
	
	private int inKms;
	
	private int outKm;
	
	private String remark;
	
	private int entryBy;
	
	private String date;
	
	private String datetime;
	
	private String driverName;

	
	
	public int getOutKm() {
		return outKm;
	}

	public void setOutKm(int outKm) {
		this.outKm = outKm;
	}

	public int gettVehId() {
		return tVehId;
	}

	public int getVehId() {
		return vehId;
	}

	public int getBillTempId() {
		return billTempId;
	}

	public int getInKms() {
		return inKms;
	}

	public String getRemark() {
		return remark;
	}

	public int getEntryBy() {
		return entryBy;
	}
	public String getDate() {
		return date;
	}

	public String getDatetime() {
		return datetime;
	}

	public String getDriverName() {
		return driverName;
	}

	public void settVehId(int tVehId) {
		this.tVehId = tVehId;
	}

	public void setVehId(int vehId) {
		this.vehId = vehId;
	}

	public void setBillTempId(int billTempId) {
		this.billTempId = billTempId;
	}

	public void setInKms(int inKms) {
		this.inKms = inKms;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public void setEntryBy(int entryBy) {
		this.entryBy = entryBy;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}

	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}

	@Override
	public String toString() {
		return "TVehicle [tVehId=" + tVehId + ", vehId=" + vehId + ", billTempId=" + billTempId + ", inKms=" + inKms
				+ ", remark=" + remark + ", entryBy=" + entryBy + ", date=" + date + ", datetime=" + datetime
				+ ", driverName=" + driverName + "]";
	}
	
}
