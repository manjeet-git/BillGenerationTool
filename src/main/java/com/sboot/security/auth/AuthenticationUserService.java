package com.sboot.security.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationUserService implements UserDetailsService{

	
	private final ApplicationUserDao applicationUserDao;
	
	@Autowired
	public AuthenticationUserService(ApplicationUserDao applicationUserDao) {
		this.applicationUserDao = applicationUserDao;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		return applicationUserDao.selectApplicationUserByUsername(username)
				.orElseThrow(()->new UsernameNotFoundException("username with name "+username+" is not found"));
	}

	
}
