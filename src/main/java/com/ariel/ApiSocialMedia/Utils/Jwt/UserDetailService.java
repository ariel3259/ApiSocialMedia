package com.ariel.ApiSocialMedia.Utils.Jwt;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ariel.ApiSocialMedia.Model.Users;
import com.ariel.ApiSocialMedia.Services.UserService;

@Service
public class UserDetailService implements UserDetailsService {

    @Autowired
    private UserService service;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = service.getByUsername(username);
        if (user == null) throw new UsernameNotFoundException("The user doesn't exists");
        return new User(user.getUsername(), user.getPassword(), Arrays.asList());
    }
    
}
