package com.example.Journal.service;

import com.example.Journal.Repository.UserRepository;
import com.example.Journal.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@Disabled
public class UserServiceDetailsimplTest {

    @InjectMocks
    private UserServiceDetailsimpl userServiceDetailsimpl;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this); // Initialize the mocks before each test
    }

    @Test
    public void testLoadUserByUsername_UserFound() {
        // Arrange
        User mockUser = new User();
        mockUser.setUsername("harshad");
        mockUser.setPassword("password123");
        mockUser.setRoles(Collections.singletonList("USER")); // Create lists for roles

        // Define what the mocked repository should return
        when(userRepository.findByUsername("harshad")).thenReturn(Optional.of(mockUser));

        // Act
        UserDetails userDetails = userServiceDetailsimpl.loadUserByUsername("harshad");

        // Assert
        assertNotNull(userDetails);
        assertEquals("harshad", userDetails.getUsername());
        assertEquals("password123", userDetails.getPassword());
    }

    @Test
    public void testLoadUserByUsername_UserNotFound() {
        // Arrange: Make the repository return an empty optional
        when(userRepository.findByUsername(anyString())).thenReturn(Optional.empty());

        // Act & Assert: Verify that the exception is thrown
        assertThrows(UsernameNotFoundException.class, () -> {
            userServiceDetailsimpl.loadUserByUsername("unknown_user");
        });
    }
}
