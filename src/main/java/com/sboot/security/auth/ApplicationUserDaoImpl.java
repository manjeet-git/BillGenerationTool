package com.sboot.security.auth;

import static com.sboot.security.ApplicationUserRole.ADMIN;
import static com.sboot.security.ApplicationUserRole.CUSTOMER;
import static com.sboot.security.ApplicationUserRole.SHOAPKEEPER;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import com.sboot.entity.Carendential;
import com.sboot.repository.CarendentialRepository;

@Repository
public class ApplicationUserDaoImpl implements ApplicationUserDao{

	
	@Autowired
	CarendentialRepository repository;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Override
	public Optional<ApplicationUser> selectApplicationUserByUsername(String username) {
		// TODO Auto-generated method stub
		return getApplicationUsers(username);
	}
	
	
	@SuppressWarnings("unchecked")
	public Optional<ApplicationUser> getApplicationUsers(String username){
		Optional<Carendential> carendentials=repository.findById(username);
		Carendential carenden=carendentials.get();
		System.out.println("======================================="+carenden);
		Set<? extends GrantedAuthority> authorities=null;
		if(carenden!=null) {
		if(carenden.getRole().equalsIgnoreCase("customer")) {
			authorities=(Set<? extends GrantedAuthority>) CUSTOMER.getGrantedAuthorities();
		}else if(carenden.getRole().equalsIgnoreCase("shoapkeeper")) {
			authorities=(Set<? extends GrantedAuthority>) SHOAPKEEPER.getGrantedAuthorities();
		}else if(carenden.getRole().equalsIgnoreCase("admin")){
			authorities=(Set<? extends GrantedAuthority>) ADMIN.getGrantedAuthorities();
		}
		
		}
		
		ApplicationUser user= new ApplicationUser(authorities,
				                                 passwordEncoder.encode(carenden.getPassword()), 
				                                 carenden.getUsername(),
				                                 true,
				                                 true,
				                                 true,
				                                 true);
		System.out.println("users  ====  "+user);
		
		return Optional.of(user);
		
		
	}

}
