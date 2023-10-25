package com.example.tokenguard.unitTests;
import com.example.tokenguard.domain.User;
import com.example.tokenguard.repository.UserRepository;
import com.example.tokenguard.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Test
    public void testFindByEmailAndPassword() {
        // Create a user with a known email and password
        String userEmail = "test@example.com";
        String userPassword = "testPassword";
        User user = new User();
        user.setEmail(userEmail);
        user.setPassword("hashedPassword");

        // Mock the userRepository to return the user when findByEmail is called
        when(userRepository.findByEmail(userEmail)).thenReturn(user);

        // Mock the bCryptPasswordEncoder to return true when matches is called
        when(bCryptPasswordEncoder.matches(userPassword, user.getPassword())).thenReturn(true);

        // Call the method under test
        User foundUser = userService.findByEmailAndPassword(userEmail, userPassword);

        // Assert that the method returns the user
        assertEquals(user, foundUser);

        // Mock the bCryptPasswordEncoder to return false when matches is called
        when(bCryptPasswordEncoder.matches(userPassword, user.getPassword())).thenReturn(false);

        // Call the method under test with the wrong password
        foundUser = userService.findByEmailAndPassword(userEmail, "wrongPassword");

        // Assert that the method returns null when the password is incorrect
        assertNull(foundUser);

        // Mock the userRepository to return null when findByEmail is called
        when(userRepository.findByEmail(userEmail)).thenReturn(null);

        // Call the method under test with an unknown email
        foundUser = userService.findByEmailAndPassword("unknown@example.com", userPassword);

        // Assert that the method returns null when the email is unknown
        assertNull(foundUser);
    }
}
