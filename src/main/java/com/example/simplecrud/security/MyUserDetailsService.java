package com.example.simplecrud.security;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    	//hard coded user for demo
    	if(username.equals("user")) {
    		return new User("user", "user",Arrays.asList(new SimpleGrantedAuthority("ROLE_USER")));
        } else if (username.equals("admin")) {
            return new User("admin", "admin", Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN")));
        } else {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        
    }
}
