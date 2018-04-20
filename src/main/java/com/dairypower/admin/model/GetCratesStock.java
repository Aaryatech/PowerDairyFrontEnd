package com.dairypower.admin.model;
 

public class GetCratesStock {
	 
	private int id; 
	private int cratesOpQty; 
	private int cratesReceivedQtyBypurchase;  
	private int cratesIssued; 
	private int cratesReceivedBycustomer; 
	private int cratesReturnQtyTomfg;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCratesOpQty() {
		return cratesOpQty;
	}
	public void setCratesOpQty(int cratesOpQty) {
		this.cratesOpQty = cratesOpQty;
	}
	public int getCratesReceivedQtyBypurchase() {
		return cratesReceivedQtyBypurchase;
	}
	public void setCratesReceivedQtyBypurchase(int cratesReceivedQtyBypurchase) {
		this.cratesReceivedQtyBypurchase = cratesReceivedQtyBypurchase;
	}
	public int getCratesIssued() {
		return cratesIssued;
	}
	public void setCratesIssued(int cratesIssued) {
		this.cratesIssued = cratesIssued;
	}
	public int getCratesReceivedBycustomer() {
		return cratesReceivedBycustomer;
	}
	public void setCratesReceivedBycustomer(int cratesReceivedBycustomer) {
		this.cratesReceivedBycustomer = cratesReceivedBycustomer;
	}
	public int getCratesReturnQtyTomfg() {
		return cratesReturnQtyTomfg;
	}
	public void setCratesReturnQtyTomfg(int cratesReturnQtyTomfg) {
		this.cratesReturnQtyTomfg = cratesReturnQtyTomfg;
	}
	@Override
	public String toString() {
		return "GetCratesStock [id=" + id + ", cratesOpQty=" + cratesOpQty + ", cratesReceivedQtyBypurchase="
				+ cratesReceivedQtyBypurchase + ", cratesIssued=" + cratesIssued + ", cratesReceivedBycustomer="
				+ cratesReceivedBycustomer + ", cratesReturnQtyTomfg=" + cratesReturnQtyTomfg + "]";
	}
	
	

}
