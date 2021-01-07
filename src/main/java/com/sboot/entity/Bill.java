package com.sboot.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table
public class Bill implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5861905273353026858L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="bill_id")
	private int billId;
	
	@Column(name="goods_name")
	//@NotNull(message = "Goods name should not be null")
	private String goodsName;
	
	@Column(name="price")
	private float price;
	
	@Column(name="customer_id")
	private int cutomerId;
	
	public int getBillId() {
		return billId;
	}
	public void setBillId(int billId) {
		this.billId = billId;
	}
	public String getGoods() {
		return goodsName;
	}
	public void setGoods(String goodsName) {
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
		return "Bill [billId=" + billId + ", goods=" + goodsName + ", price=" + price + ", cutomerId=" + cutomerId + "]";
	}
	
	
	
	
}
