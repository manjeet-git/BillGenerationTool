package com.sboot.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

@SuppressWarnings("deprecation")
@Entity
@Table
public class Customer implements Serializable {

	private static final long serialVersionUID = 4022847851285898104L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="customer_id")
	private int customerId;
	
	@Column(name="customer_name")
	@NotNull(message = "Customer name should not be null")
	@NotEmpty(message = "Customer name shouldn't empty")
	private String customerName;
	
	@Column(name="address")
	@NotEmpty(message = "Address must be needed.")
	@NotNull(message = "Address should not be null")
	private String address;
	
	@Column(name="mobile")
	@Size(min = 10, max = 12)
	@NotNull(message = "mobile should not be null")
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
		return "Customer [customerId=" + customerId + ", customerName=" + customerName + ", address=" + address
				+ ", mobile=" + mobile + "]";
	}
	
	
}
