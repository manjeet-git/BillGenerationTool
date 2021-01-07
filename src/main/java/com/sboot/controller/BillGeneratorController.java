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

import com.sboot.custom.exception.BillIdNotFoundException;
import com.sboot.dto.BillGenerationDto;
import com.sboot.entity.Bill;
import com.sboot.service.BillGenerationService;

@RestController
@RequestMapping("/api/bill")
public class BillGeneratorController {
	
	@Autowired
	public BillGenerationService billGenService;
	
	@GetMapping("/all-bills")
	public ResponseEntity<List<BillGenerationDto>> getBills(){
		return new ResponseEntity<List<BillGenerationDto>>(billGenService.getAllBills(),HttpStatus.OK);
	}
	
	@GetMapping("/bill-by-bill_id/{billId}")
	public ResponseEntity<BillGenerationDto> getBillById(@PathVariable int billId){
		try {
		BillGenerationDto dto=billGenService.getAllBillsByBillId(billId);
		return new ResponseEntity<BillGenerationDto>(dto,HttpStatus.OK);
		}catch (Exception e) {
			throw new BillIdNotFoundException("Related Bill id information is existing ");
			
		}
		
			
		
			
	}
	
	@PostMapping(value="/save-bill",consumes = {MediaType.APPLICATION_JSON_VALUE},produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<BillGenerationDto> saveBillInfo( @RequestBody  Bill bill){
		System.out.println("Controller : "+bill);
		return new ResponseEntity<BillGenerationDto>(billGenService.persistBillInfo(bill),HttpStatus.ACCEPTED);
	}
	


}
