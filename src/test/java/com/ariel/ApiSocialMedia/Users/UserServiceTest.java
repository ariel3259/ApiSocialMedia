package com.ariel.ApiSocialMedia.Users;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
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
public class UserServiceTest {
	
	@InjectMocks
	private UserService service;
	
	@Mock
	private UserRepository repositoryMock;
	
	@Mock
	private PasswordEncoder encodeMock;
	
	private Users user = new Users(1, "Ariel", "Santangelo", "arielS", "1234", true);
	
	@Test
	public void SAVE() {
		when(repositoryMock.getByUsernameAndState(user.getUsername(), user.getState())).thenReturn(null);
		when(repositoryMock.save(user)).thenReturn(user);
		boolean result = service.save(user);
		assertTrue(result);
	}
	
	@Test
	public void SAVE_FAIL() {
		when(repositoryMock.getByUsernameAndState(user.getUsername(), true)).thenReturn(user);
		boolean result = service.save(user);
		assertFalse(result);
	}
	
	@Test
	public void GET_ONE_BY_USERNAME() {
		when(repositoryMock.findByUsernameAndState(user.getUsername(), true)).thenReturn(user);
		Users userFound = service.getByUsername(user.getUsername());
		assertNotNull(userFound);
		assertEquals(user, userFound);
	}
	
	@Test
	 public void GET_ONE_BY_USERNAME_NULL() {
		when(repositoryMock.findByUsernameAndState(user.getUsername(), true)).thenReturn(null);
		Users userFound = service.getByUsername(user.getUsername());
		assertNull(userFound);
		assertNotEquals(user, userFound);
	}
}
