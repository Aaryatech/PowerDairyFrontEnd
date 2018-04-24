package com.dairypower.admin.model;

public class ReturnStockUpdate {

	
	private int poDetailId;

    private int returnQty;

	public int getPoDetailId() {
		return poDetailId;
	}

	public int getReturnQty() {
		return returnQty;
	}

	public void setPoDetailId(int poDetailId) {
		this.poDetailId = poDetailId;
	}

	public void setReturnQty(int returnQty) {
		this.returnQty = returnQty;
	}

	@Override
	public String toString() {
		return "ReturnStockUpdate [poDetailId=" + poDetailId + ", returnQty=" + returnQty + "]";
	}
    
    
}
