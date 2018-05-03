package com.dairypower.admin.model;



public class BillDetailReport {

	private int billDetailId;
	
	private int billTempId;
	
	private String batchNo;
	
	private String hsnCode;
	
	private int uomId;
	
	private String uom;
	
	private int itemId;
	
	private String itemName;
	
	private float rate;
	
	private int billQty;
	
	private int returnQty;
	
	private int distLeakageQty;
	
	private float cgstPer;
	
	private float sgstPer;
	
	private float igstPer;

	private int poDetailId;

	public int getBillDetailId() {
		return billDetailId;
	}

	public int getBillTempId() {
		return billTempId;
	}

	public String getBatchNo() {
		return batchNo;
	}

	public String getHsnCode() {
		return hsnCode;
	}

	public int getUomId() {
		return uomId;
	}

	public String getUom() {
		return uom;
	}

	public int getItemId() {
		return itemId;
	}

	public String getItemName() {
		return itemName;
	}

	public float getRate() {
		return rate;
	}

	public int getBillQty() {
		return billQty;
	}

	public int getReturnQty() {
		return returnQty;
	}

	public int getDistLeakageQty() {
		return distLeakageQty;
	}

	public float getCgstPer() {
		return cgstPer;
	}

	public float getSgstPer() {
		return sgstPer;
	}

	public float getIgstPer() {
		return igstPer;
	}

	public int getPoDetailId() {
		return poDetailId;
	}

	public void setBillDetailId(int billDetailId) {
		this.billDetailId = billDetailId;
	}

	public void setBillTempId(int billTempId) {
		this.billTempId = billTempId;
	}

	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}

	public void setHsnCode(String hsnCode) {
		this.hsnCode = hsnCode;
	}

	public void setUomId(int uomId) {
		this.uomId = uomId;
	}

	public void setUom(String uom) {
		this.uom = uom;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public void setRate(float rate) {
		this.rate = rate;
	}

	public void setBillQty(int billQty) {
		this.billQty = billQty;
	}

	public void setReturnQty(int returnQty) {
		this.returnQty = returnQty;
	}

	public void setDistLeakageQty(int distLeakageQty) {
		this.distLeakageQty = distLeakageQty;
	}

	public void setCgstPer(float cgstPer) {
		this.cgstPer = cgstPer;
	}

	public void setSgstPer(float sgstPer) {
		this.sgstPer = sgstPer;
	}

	public void setIgstPer(float igstPer) {
		this.igstPer = igstPer;
	}

	public void setPoDetailId(int poDetailId) {
		this.poDetailId = poDetailId;
	}

	@Override
	public String toString() {
		return "BillDetailReport [billDetailId=" + billDetailId + ", billTempId=" + billTempId + ", batchNo=" + batchNo
				+ ", hsnCode=" + hsnCode + ", uomId=" + uomId + ", uom=" + uom + ", itemId=" + itemId + ", itemName="
				+ itemName + ", rate=" + rate + ", billQty=" + billQty + ", returnQty=" + returnQty
				+ ", distLeakageQty=" + distLeakageQty + ", cgstPer=" + cgstPer + ", sgstPer=" + sgstPer + ", igstPer="
				+ igstPer + ", poDetailId=" + poDetailId + "]";
	}
	
	
}
