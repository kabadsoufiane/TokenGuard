package com.example.tokenguard.unitTests;

import com.example.tokenguard.domain.User;
import com.example.tokenguard.repository.UserRepository;
import com.example.tokenguard.service.UserService;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Mock
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    @Test
    public void testFindByEmailAndPassword() {
        User user = new User();
        user.setEmail("test@example.com");

        // Encoding the password before saving the user
        String encodedPassword = bCryptPasswordEncoder.encode("password");
        user.setPassword(encodedPassword);

        userRepository.save(user);
        assertNotNull(user);

        when(userRepository.findByEmail(anyString())).thenReturn(user);

        User result = userService.findByEmailAndPassword("test@example.com", "password");
        assertNotNull(result);
        assertEquals("test@example.com", result.getEmail());
    }

}
