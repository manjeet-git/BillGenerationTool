package com.sboot.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sboot.custom.exception.CustomerNotFoundException;
import com.sboot.dto.CustomerDto;
import com.sboot.entity.Customer;
import com.sboot.service.CustomerService;

@RestController
@RequestMapping("/api/cutomer")
public class CustomerController {
	
	@Autowired
	public CustomerService customerService;
	
	@GetMapping("/all-customers")
	public ResponseEntity<List<CustomerDto>> getCustomers(){
		return new ResponseEntity<List<CustomerDto>>(customerService.getAllCustomers(),HttpStatus.OK);
	}
	
	@GetMapping("/customer-by-Customer_id/{customerId}")
	public ResponseEntity<CustomerDto> getCustomerById(@PathVariable int customerId){
		try {
		CustomerDto dto=customerService.getAllCustomersByCustomerId(customerId);
		return new ResponseEntity<CustomerDto>(dto,HttpStatus.OK);
		}catch (Exception e) {
			throw new CustomerNotFoundException("Related Customer id information is existing ");
			
		}		
			
	}
	
	
	
	@PostMapping(value="/save-customer",consumes = {MediaType.APPLICATION_JSON_VALUE},produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<CustomerDto> saveCustomerInfo( @Valid @RequestBody   Customer customer){
		System.out.println("Controller : "+customer);
		return new ResponseEntity<CustomerDto>(customerService.persistCustomerInfo(customer),HttpStatus.ACCEPTED);
	}
	


	
	

}
