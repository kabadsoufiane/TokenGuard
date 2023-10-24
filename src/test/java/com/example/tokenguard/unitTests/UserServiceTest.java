package com.example.tokenguard.unitTests;

import com.example.tokenguard.domain.User;
import com.example.tokenguard.repository.UserRepository;
import com.example.tokenguard.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    public void testFindByEmailAndPassword() {

        User user = new User();

        user.setEmail("example@example.com");
        user.setPassword("password");

        when(userRepository.findByEmail("example@example.com")).thenReturn(user);

        User result = userService.findByEmailAndPassword("test@example.com", "password");
        assertNotNull(result);
        assertEquals("test@example.com", result.getEmail());
    }
}
