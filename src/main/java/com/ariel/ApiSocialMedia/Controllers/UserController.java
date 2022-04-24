package com.ariel.ApiSocialMedia.Controllers;

import com.ariel.ApiSocialMedia.Model.User;
import com.ariel.ApiSocialMedia.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService us;

    @GetMapping
    public ResponseEntity<List<User>> getUsers(){
        List<User> users = us.getUsers();
        return ResponseEntity.status(200).body(users);
    }

    @PostMapping
    public ResponseEntity<Integer> postUser(@RequestBody User user){
        if(user.getName().isEmpty() || user.getLastName().isEmpty() || user.getAddress().isEmpty()) return ResponseEntity.status(400).body(4);
        return ResponseEntity.status(201).body(us.saveUser(user));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Integer> putUser(@RequestBody User user, @PathVariable("id") int id){
        if(user.getName().isEmpty() || user.getLastName().isEmpty() || user.getAddress().isEmpty()) return ResponseEntity.status(400).body(4);
        return ResponseEntity.status(201).body(us.updateUser(user, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Integer> deleteUser(@PathVariable("id") int id){
        return ResponseEntity.status(201).body(us.deleteUser(id));
    }
 }
