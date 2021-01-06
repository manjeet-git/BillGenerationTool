package com.sboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sboot.entity.Bill;

@Repository
public interface BillGenRepository extends JpaRepository<Bill,Integer>{

}
