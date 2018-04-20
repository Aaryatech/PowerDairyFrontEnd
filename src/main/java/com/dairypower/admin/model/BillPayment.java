package com.dairypower.admin.model;



public class BillPayment{
	
	private int payId;
	
	private int billId;
	
	private String tranId;
	
	private int currId;
	
	private int qty;
	
	private float totalAmt;

	public int getPayId() {
		return payId;
	}

	public void setPayId(int payId) {
		this.payId = payId;
	}

	public int getBillId() {
		return billId;
	}

	public void setBillId(int billId) {
		this.billId = billId;
	}

	public String getTranId() {
		return tranId;
	}

	public void setTranId(String tranId) {
		this.tranId = tranId;
	}

	public int getCurrId() {
		return currId;
	}

	public void setCurrId(int currId) {
		this.currId = currId;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public float getTotalAmt() {
		return totalAmt;
	}

	public void setTotalAmt(float totalAmt) {
		this.totalAmt = totalAmt;
	}

	@Override
	public String toString() {
		return "BillPayment [payId=" + payId + ", billId=" + billId + ", tranId=" + tranId + ", currId=" + currId
				+ ", qty=" + qty + ", totalAmt=" + totalAmt + "]";
	}
	
	

}
