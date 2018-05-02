package com.dairypower.admin.model;

import java.util.List;

public class CreditNoteHeader{
	
    private int crnHeaderId;
    
    private int billTempId;

    private String crnId;

    private String crnDate;

    private String crnDatetime;

    private int custId;
    	
	private float grandTotal;

    private String remarks;

	List<CreditNoteDetail> creditNoteDetailList;
	
	
	public float getGrandTotal() {
		return grandTotal;
	}

	public void setGrandTotal(float grandTotal) {
		this.grandTotal = grandTotal;
	}

	public int getBillTempId() {
		return billTempId;
	}

	public void setBillTempId(int billTempId) {
		this.billTempId = billTempId;
	}

	public List<CreditNoteDetail> getCreditNoteDetailList() {
		return creditNoteDetailList;
	}

	public void setCreditNoteDetailList(List<CreditNoteDetail> creditNoteDetailList) {
		this.creditNoteDetailList = creditNoteDetailList;
	}

	public int getCrnHeaderId() {
		return crnHeaderId;
	}

	public void setCrnHeaderId(int crnHeaderId) {
		this.crnHeaderId = crnHeaderId;
	}

	public String getCrnId() {
		return crnId;
	}

	public void setCrnId(String crnId) {
		this.crnId = crnId;
	}
	public String getCrnDate() {
		return crnDate;
	}

	public void setCrnDate(String crnDate) {
		this.crnDate = crnDate;
	}

	public String getCrnDatetime() {
		return crnDatetime;
	}

	public void setCrnDatetime(String crnDatetime) {
		this.crnDatetime = crnDatetime;
	}

	public int getCustId() {
		return custId;
	}

	public void setCustId(int custId) {
		this.custId = custId;
	}


	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	@Override
	public String toString() {
		return "CreditNoteHeader [crnHeaderId=" + crnHeaderId + ", crnId=" + crnId + ", crnDate=" + crnDate
				+ ", crnDatetime=" + crnDatetime + ", custId=" + custId + ",remarks="
				+ remarks + "]";
	}

}
