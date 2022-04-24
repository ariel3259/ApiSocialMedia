package com.ariel.ApiSocialMedia.Services;

import com.ariel.ApiSocialMedia.Model.User;
import com.ariel.ApiSocialMedia.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository ur;

    public List<User> getUsers(){
        return ur.getAllUsers();
    }

    public int saveUser(User user){
        return ur.saveUser(user);
    }

    public int updateUser(User user, int id){
        return ur.updateUser(user, id);
    }

    public int deleteUser(int id){
        return ur.deleteUser(id);
    }
}
