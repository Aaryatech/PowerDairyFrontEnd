package com.dairypower.admin.model;


public class BillPayment{
	
	private int payId;
	
	private int billId;
	
	private String bankName;
	
	private String chequeNo;
	
	private String chequeDate;
	
	private float chequeAmt;
	
	private int payMode;

	private String tranId;
	
	private int currId;
	
	private int qty;
	
	private float totalAmt;

	
	public String getChequeDate() {
		return chequeDate;
	}

	public void setChequeDate(String chequeDate) {
		this.chequeDate = chequeDate;
	}

	public String getBankName() {
		return bankName;
	}

	public String getChequeNo() {
		return chequeNo;
	}

	public float getChequeAmt() {
		return chequeAmt;
	}

	public int getPayMode() {
		return payMode;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public void setChequeNo(String chequeNo) {
		this.chequeNo = chequeNo;
	}

	public void setChequeAmt(float chequeAmt) {
		this.chequeAmt = chequeAmt;
	}

	public void setPayMode(int payMode) {
		this.payMode = payMode;
	}

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
		return "BillPayment [payId=" + payId + ", billId=" + billId + ", bankName=" + bankName + ", chequeNo="
				+ chequeNo + ", chequeAmt=" + chequeAmt + ", payMode=" + payMode + ", tranId=" + tranId + ", currId="
				+ currId + ", qty=" + qty + ", totalAmt=" + totalAmt + "]";
	}

	
}
