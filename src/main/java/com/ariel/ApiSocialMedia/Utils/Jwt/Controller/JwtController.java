package com.ariel.ApiSocialMedia.Utils.Jwt.Controller;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ariel.ApiSocialMedia.Utils.Jwt.TokenManager;
import com.ariel.ApiSocialMedia.Utils.Jwt.Model.JwtRequest;
import com.ariel.ApiSocialMedia.Utils.Jwt.Model.JwtResponse;

@RestController
@RequestMapping("/login")
public class JwtController {
    
    @Autowired
    private UserDetailsService service;

    @Autowired
    private TokenManager tokenManager;
    
    @Autowired
    private AuthenticationManager authManager;

    @PostMapping
    public ResponseEntity<JwtResponse> auth(@Valid @RequestBody JwtRequest request){
        try{
            authManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        }
        catch(DisabledException ex){
            return ResponseEntity.status(400).body(new JwtResponse("DISABLED"));
        }catch(BadCredentialsException ex){
            return ResponseEntity.status(400).body(new JwtResponse("BAD_CREDENTIALS"));
        }
        UserDetails user = service.loadUserByUsername(request.getUsername());
        String token = tokenManager.generateToken(user);
        return ResponseEntity.status(200).body(new JwtResponse(token));
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
