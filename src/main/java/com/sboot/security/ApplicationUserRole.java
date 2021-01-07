package com.sboot.security;

import static com.sboot.security.ApplicationUserPermission.CUSTOMER_READ;
import static com.sboot.security.ApplicationUserPermission.CUSTOMER_WRITE;
import static com.sboot.security.ApplicationUserPermission.SHOAPKEEPER_READ;
import static com.sboot.security.ApplicationUserPermission.SHOAPKEEPER_WRITE;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.google.common.collect.Sets;

public enum ApplicationUserRole {
CUSTOMER(Sets.newHashSet()),
SHOAPKEEPER(Sets.newHashSet(SHOAPKEEPER_READ,CUSTOMER_READ)),
ADMIN(Sets.newHashSet(CUSTOMER_READ ,CUSTOMER_WRITE,SHOAPKEEPER_READ,SHOAPKEEPER_WRITE));

private final Set<ApplicationUserPermission> permissions;

    ApplicationUserRole(Set<ApplicationUserPermission> permissions) {
	this.permissions = permissions;
    }

	public Set<ApplicationUserPermission> getPermissions() {
		return permissions;
	}

    public Set<SimpleGrantedAuthority> getGrantedAuthorities(){
    	Set<SimpleGrantedAuthority> permissions=getPermissions().stream()
    			.map(permission->new SimpleGrantedAuthority(permission.getPermission()))
    			.collect(Collectors.toSet());
    	
    	permissions.add(new SimpleGrantedAuthority("ROLE_"+this.name()));
    	return permissions;
    }

}
