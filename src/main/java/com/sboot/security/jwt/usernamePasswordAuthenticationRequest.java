package com.sboot.security.jwt;

public class usernamePasswordAuthenticationRequest {

	private String username;
	private String password;
	
	
	public usernamePasswordAuthenticationRequest() {

	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	@Override
	public String toString() {
		return "usernamePasswordAuthenticationRequest [username=" + username + ", password=" + password + "]";
	}
	
	
	
	
}
