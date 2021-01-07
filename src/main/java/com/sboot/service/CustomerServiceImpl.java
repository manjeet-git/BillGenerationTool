package com.sboot.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sboot.dto.BillGenerationDto;
import com.sboot.dto.CustomerDto;
import com.sboot.entity.Bill;
import com.sboot.entity.Customer;
import com.sboot.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	CustomerRepository repository;
	
	@Override
	public List<CustomerDto> getAllCustomers() {
		List<Customer> customers=repository.findAll();
		System.out.println(customers);
		List<CustomerDto> customerDtos=converterEntityToDto(customers);
		System.out.println(customerDtos);
		return customerDtos;
	}

	@Override
	public CustomerDto getAllCustomersByCustomerId(int customerId) {
		// TODO Auto-generated method stub
				Optional<Customer> opCustomer=repository.findById(customerId);
				Customer customer=opCustomer.get();
				CustomerDto dto=EntityToDto(customer);
			return dto;
	}

	@Override
	public CustomerDto persistCustomerInfo(Customer customer) {
		// TODO Auto-generated method stub
				System.out.println("serviceImpl : "+customer);
				Customer customer1=repository.save(customer);
				return EntityToDto(customer1);
	}
	
	public CustomerDto EntityToDto(Customer customer){
		CustomerDto dto=new CustomerDto();
		BeanUtils.copyProperties(customer,dto);
	return dto;
}
	
	public List<CustomerDto> converterEntityToDto(List<Customer> customers){
		List<CustomerDto> customersdto=new ArrayList<>();
		for(Customer customer:customers) {
			CustomerDto dto=new CustomerDto();
			BeanUtils.copyProperties(customer,dto);
			System.out.println("Single dto values : "+dto);
			customersdto.add(dto);
		}
		return customersdto;
	}

}
