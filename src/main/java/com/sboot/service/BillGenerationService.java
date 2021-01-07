package com.sboot.service;

import java.util.List;

import com.sboot.dto.BillGenerationDto;
import com.sboot.entity.Bill;

public interface BillGenerationService {

	// This method is for getting the all information about the all bills
	public List<BillGenerationDto> getAllBills();
	
	//This method for getting the bill information by Bill id ;
	public BillGenerationDto getAllBillsByBillId(int billId);
	
	//This method for persisting the bill info
	public BillGenerationDto persistBillInfo(Bill bill);
}
