package com.example.demo.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.model.Privilege;
import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository repository;
    
    @Autowired
    RoleRepository roleRepository;
   
    
	@Override
	public User findOneById(Long id) {
		// TODO Auto-generated method stub
		return repository.findOneById(id);
	}

	@Override
	public User saveUser(User user) {
		// TODO Auto-generated method stub
		return repository.save(user);
	}

	@Override
	public void removeUser(Long id) {
		// TODO Auto-generated method stub
		repository.deleteById(id);
	}
	@Override
	public User findUserByMail( String mail) {
		// TODO Auto-generated method stub
		System.out.println("Usao u findUserbyMail");
		return repository.findOneByEmail(mail);
	}

	@Override
	public UserDetails loadUserByUsername(String mail)
			throws UsernameNotFoundException {
		 User user = repository.findOneByEmail(mail);
	       
	 
	        return user;
	}

	@Override
	public List<User> getAll() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}
	
private Collection<? extends GrantedAuthority> getAuthorities(Collection<Role> roles) {
		
		return getGrantedAuthorities(getPrivileges(roles));
	
	}

private List<String> getPrivileges(Collection<Role> roles) {
	  
    List<String> privileges = new ArrayList<>();
    List<Privilege> collection = new ArrayList<>();
    for (Role role : roles) {
        collection.addAll(role.getPrivileges());
    }
    for (Privilege item : collection) {
        privileges.add(item.getName());
    }
    return privileges;
}

private List<GrantedAuthority> getGrantedAuthorities(List<String> privileges) {
    List<GrantedAuthority> authorities = new ArrayList<>();
    for (String privilege : privileges) {
        authorities.add(new SimpleGrantedAuthority(privilege));
    }
    return authorities;
}

}
