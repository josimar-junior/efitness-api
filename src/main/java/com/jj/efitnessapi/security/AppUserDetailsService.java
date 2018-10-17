package com.jj.efitnessapi.security;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.jj.efitnessapi.model.SystemUser;
import com.jj.efitnessapi.repository.SystemUserRepository;

@Service
public class AppUserDetailsService implements UserDetailsService {

	@Autowired
	private SystemUserRepository systemUserRepository;
	
	@Override
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {

		Optional<SystemUser> userOptional = systemUserRepository.findByLogin(login);
		SystemUser user = userOptional.orElseThrow(() -> new UsernameNotFoundException("Login or password is invalid"));
		
		return new UserLoggedIn(user, getPermissions(user));
	}

	private Collection<? extends GrantedAuthority> getPermissions(SystemUser user) {
		Set<SimpleGrantedAuthority> authorities = new HashSet<>();
		authorities.add(new SimpleGrantedAuthority("ROLE_" + user.getProfile().toString().toUpperCase()));
		return authorities;
	}

}
