package com.ariel.ApiSocialMedia.Controllers;

import com.ariel.ApiSocialMedia.Model.Users;
import com.ariel.ApiSocialMedia.Services.UserService;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService service;


    @PostMapping
    public ResponseEntity<String> postUser(@Valid @RequestBody Users user) {
    	if(!service.save(user)) return ResponseEntity.status(400).body("User already exists");
    	return ResponseEntity.status(200).body("Saved user");
    }

    @PutMapping()
    public ResponseEntity<String> putUser(@Valid @RequestBody Users user){
    	if(!service.update(user)) return ResponseEntity.status(400).body("User doesn't exists");
    	return ResponseEntity.status(200).body("Modified User");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@Valid @PathVariable("id") long id){
        if(!service.delete(id)) return ResponseEntity.status(400).body("The user doesn't exists");
    	return ResponseEntity.status(200).body("Deleted user");
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidErrors(MethodArgumentNotValidException ex){
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errors.put(fieldName, message);
        });
        return errors;
    }
 }
