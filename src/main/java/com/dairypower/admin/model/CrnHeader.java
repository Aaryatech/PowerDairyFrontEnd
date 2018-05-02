package com.dairypower.admin.model;

import java.util.List;

public class CrnHeader{

    private int crnHeaderId;
	
    private int billTempId;
	
    private String crnId;
	
    private String crnDate;
	
    private String crnDatetime;
	
    private int custId;
	
	private String custName;
	
	private float grandTotal;
	
    private String remarks;

	List<CreditNoteDetails> creditNoteDetailList;

	
	public float getGrandTotal() {
		return grandTotal;
	}

	public void setGrandTotal(float grandTotal) {
		this.grandTotal = grandTotal;
	}

	public int getCrnHeaderId() {
		return crnHeaderId;
	}

	public int getBillTempId() {
		return billTempId;
	}

	public String getCrnId() {
		return crnId;
	}

	public String getCrnDate() {
		return crnDate;
	}

	public String getCrnDatetime() {
		return crnDatetime;
	}

	public int getCustId() {
		return custId;
	}

	public String getCustName() {
		return custName;
	}
	public String getRemarks() {
		return remarks;
	}

	public List<CreditNoteDetails> getCreditNoteDetailList() {
		return creditNoteDetailList;
	}

	public void setCrnHeaderId(int crnHeaderId) {
		this.crnHeaderId = crnHeaderId;
	}

	public void setBillTempId(int billTempId) {
		this.billTempId = billTempId;
	}

	public void setCrnId(String crnId) {
		this.crnId = crnId;
	}

	public void setCrnDate(String crnDate) {
		this.crnDate = crnDate;
	}

	public void setCrnDatetime(String crnDatetime) {
		this.crnDatetime = crnDatetime;
	}

	public void setCustId(int custId) {
		this.custId = custId;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public void setCreditNoteDetailList(List<CreditNoteDetails> creditNoteDetailList) {
		this.creditNoteDetailList = creditNoteDetailList;
	}

	@Override
	public String toString() {
		return "CrnHeader [crnHeaderId=" + crnHeaderId + ", billTempId=" + billTempId + ", crnId=" + crnId
				+ ", crnDate=" + crnDate + ", crnDatetime=" + crnDatetime + ", custId=" + custId + ", custName="
				+ custName + ", remarks=" + remarks + ", creditNoteDetailList="
				+ creditNoteDetailList + "]";
	}
	
	
}
