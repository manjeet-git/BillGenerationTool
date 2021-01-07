package com.sboot.dto;

import java.io.Serializable;

public class BillGenerationDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5308330710214926177L;
	
	private int billId;
	private String goodsName;
	private float price;
	private int cutomerId;
	
	
	public int getBillId() {
		return billId;
	}
	public void setBillId(int billId) {
		this.billId = billId;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public int getCutomerId() {
		return cutomerId;
	}
	public void setCutomerId(int cutomerId) {
		this.cutomerId = cutomerId;
	}
	@Override
	public String toString() {
		return "BillGenerationDto [billId=" + billId + ", goodsName=" + goodsName + ", price=" + price + ", cutomerId="
				+ cutomerId + "]";
	}
	
	
	
	
}
