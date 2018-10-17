package com.jj.efitnessapi.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

import com.jj.efitnessapi.model.SystemUser;

public class UserLoggedIn extends org.springframework.security.core.userdetails.User {

	private static final long serialVersionUID = -1905575041761766731L;

	private SystemUser systemUser;
	
	public UserLoggedIn(SystemUser systemUser, Collection<? extends GrantedAuthority> authorities) {
		super(systemUser.getLogin(), systemUser.getPassword(), authorities);
		this.systemUser = systemUser;
	}

	public SystemUser getSystemUser() {
		return systemUser;
	}
}
