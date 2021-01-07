package com.sboot.service;

import java.util.List;

import com.sboot.dto.BillGenerationDto;
import com.sboot.dto.CustomerDto;
import com.sboot.entity.Customer;

public interface CustomerService {
	
	// This method is for getting the all information about the all bills
	public List<CustomerDto> getAllCustomers();
	
	//This method for getting the bill information by Bill id ;
	public CustomerDto getAllCustomersByCustomerId(int customerId);
	
	//This method for persisting the bill info
	public CustomerDto persistCustomerInfo(Customer customer);

}
