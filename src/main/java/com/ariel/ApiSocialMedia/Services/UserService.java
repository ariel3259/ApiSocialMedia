package com.ariel.ApiSocialMedia.Services;

import com.ariel.ApiSocialMedia.Model.Users;
import com.ariel.ApiSocialMedia.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    @Autowired
    private UserRepository ur;

    @Autowired
    private PasswordEncoder encoder;

    public boolean save(Users user){
    	if(ur.findByUsername(user.getUsername()) != null) return false;
    	String passwordHashed = encoder.encode(user.getPassword());
    	user.setPassword(passwordHashed);
    	ur.save(user);
    	return true;
    }
    
    public Users getByUsername(String username){
    	return ur.findByUsername(username);
    }

    public boolean delete(long id){
        if(ur.getById(id) == null) return false;
        ur.deleteById(id);
    	return true;
    }
}
