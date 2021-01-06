package com.sboot.security;

public enum ApplicationUserPermission {
    CUSTOMER_READ("customer:read"),
    CUSTOMER_WRITE("customer:write"),
    SHOAPKEEPER_READ("shoapkeeper:read"),
    SHOAPKEEPER_WRITE("shoapkeeper:write");
    
    private final String permission;


	ApplicationUserPermission(String permission) {
		this.permission = permission;
	}


	public String getPermission() {
		return permission;
	}
	
	
    
    
}
