package com.sboot.dto;

import java.io.Serializable;

public class CustomerDto implements Serializable{
	
    /**
	 * 
	 */
	private static final long serialVersionUID = -2748941913307707208L;
	
	private int customerId;
	private String customerName;
	private String address;
	private String mobile;
	
	
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	@Override
	public String toString() {
		return "CustomerDto [customerId=" + customerId + ", customerName=" + customerName + ", address=" + address
				+ ", mobile=" + mobile + "]";
	}
	
	
}