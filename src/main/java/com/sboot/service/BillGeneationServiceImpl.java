package com.sboot.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sboot.dto.BillGenerationDto;
import com.sboot.entity.Bill;
import com.sboot.repository.BillGenRepository;

@Service
public class BillGeneationServiceImpl implements BillGenerationService{

	@Autowired
	BillGenRepository repository;
	
	@Override
	public List<BillGenerationDto> getAllBills() {
		List<Bill> billGen=repository.findAll();
		System.out.println(billGen);
		List<BillGenerationDto> billsGenDtos=converterEntityToDto(billGen);
		System.out.println(billsGenDtos);
		return billsGenDtos;
	}
	
	
	@Override
	public BillGenerationDto getAllBillsByBillId(int billId) {
		// TODO Auto-generated method stub
		Optional<Bill> opBill=repository.findById(billId);
		Bill bill=opBill.get();
	    BillGenerationDto dto=EntityToDto(bill);
	return dto;
	}
	
	
	@Override
	public BillGenerationDto persistBillInfo(Bill bill) {
		// TODO Auto-generated method stub
		System.out.println("serviceImpl : "+bill);
		  Bill billGen=repository.save(bill);
		return EntityToDto(billGen);
	}
	
	public List<BillGenerationDto> converterEntityToDto(List<Bill> bills){
		List<BillGenerationDto> billGenDtos=new ArrayList<>();
		for(Bill bill:bills) {
			BillGenerationDto dto=new BillGenerationDto();
			BeanUtils.copyProperties(bill,dto);
			System.out.println("Single dto values : "+dto);
			billGenDtos.add(dto);
		}
		return billGenDtos;
	}


	public BillGenerationDto EntityToDto(Bill bill){
			BillGenerationDto dto=new BillGenerationDto();
			BeanUtils.copyProperties(bill,dto);
		return dto;
	}


	
	

}
