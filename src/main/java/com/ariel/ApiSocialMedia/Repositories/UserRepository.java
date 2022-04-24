package com.ariel.ApiSocialMedia.Repositories;

import com.ariel.ApiSocialMedia.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepository {

    @Autowired
    private JdbcTemplate jdbc;

    public List<User> getAllUsers(){
        String query = "SELECT * FROM `users`";
        List<User> allUsers = jdbc.query(query, BeanPropertyRowMapper.newInstance(User.class));
        return allUsers;
    }

    public int saveUser( User user){
        String query = "INSERT INTO `users`(`name`, `last_name`, `address`) VALUES (?, ?, ?)";
        Object[] params = {user.getName(), user.getLastName(), user.getAddress()};
        int result = jdbc.update(query, params);
        return result;
    }

    public int updateUser(User user, int id){
        String query = "UPDATE `users` SET `name` = ?, `last_name` = ?, `address` = ? WHERE id = ?";
        Object[] params = {user.getName(), user.getLastName(), user.getAddress(), id};
        int result = jdbc.update(query, params);
        return result;
    }

    public int deleteUser(int id){
        String query = "DELETE FROM `users` WHERE `id` = ?";
        Object[] params = {id};
        int result = jdbc.update(query, params);
        return result;
    }
}
