package com.sboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sboot.entity.Carendential;

@Repository
public interface CarendentialRepository extends JpaRepository<Carendential, String>{

}
