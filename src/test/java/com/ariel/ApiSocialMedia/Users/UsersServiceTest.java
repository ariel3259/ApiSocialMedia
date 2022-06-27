package com.ariel.ApiSocialMedia.Users;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import com.ariel.ApiSocialMedia.Model.Users;
import com.ariel.ApiSocialMedia.Repositories.UserRepository;
import com.ariel.ApiSocialMedia.Services.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UsersServiceTest {
    
    @InjectMocks
    private UserService service;

    @Mock
    private UserRepository repository;

    @Mock
    private PasswordEncoder encoder;

    private final Users user = new Users(1l, "Jon", "Doe", "Xx_jony_xX", "1234", true);

    @Test
    public void GET_BY_USERNAME_NOT_NULL(){
        when(repository.findByUsername(user.getUsername())).thenReturn(user);
        Users userFound = service.getByUsername(user.getUsername());
        assertNotNull(userFound);
        assertEquals(user, userFound);
    }

    @Test
    public void GET_BY_USERNAME_NULL(){
        when(repository.findByUsername(user.getUsername())).thenReturn(null);
        Users userFound = service.getByUsername(user.getUsername());
        assertNull(userFound);
    }

    @Test
    public void SAVE(){
        when(repository.findByUsername(user.getUsername())).thenReturn(null);
        when(repository.save(user)).thenReturn(user);
        boolean result = service.save(user);
        assertTrue(result);
    }

    @Test
    public void SAVE_WITH_AN_EXISTING_USER(){
        when(repository.findByUsername(user.getUsername())).thenReturn(user);
        boolean result = service.save(user);
        assertFalse(result);
    }

    @Test
    public void DELETE(){
        when(repository.getById(user.getId())).thenReturn(user);
        boolean result = service.delete(user.getId());
        assertTrue(result);
    }

    @Test
    public void DELETE_WITH_NO_USER(){
        when(repository.getById(user.getId())).thenReturn(null);
        boolean result = service.delete(user.getId());
        assertFalse(result);
    }
}
