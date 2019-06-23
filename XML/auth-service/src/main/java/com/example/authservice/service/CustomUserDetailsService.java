package com.example.authservice.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.authservice.model.Permission;
import com.example.authservice.model.Role;
import com.example.authservice.model.User;
import com.example.authservice.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService{

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String arg0)
			throws UsernameNotFoundException {
		User user = userRepository.findByEmail(arg0);
		
		if (user == null) {
			throw new UsernameNotFoundException(String.format("No user found with email '%s'.", arg0));
		} else {
			return user;
		}
	}
	
	private Collection<? extends GrantedAuthority> getAuthorities(Role role) {
		return getGrantedAuthorities(getPermissions(role));
	}
	
	private Collection<Permission> getPermissions(Role role) {
        return role.getPermission();
    }
	
	private List<GrantedAuthority> getGrantedAuthorities(Collection<Permission> permissions) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (Permission p : permissions) {
            authorities.add(new SimpleGrantedAuthority(p.getName()));
        }
        return authorities;
    }

}
