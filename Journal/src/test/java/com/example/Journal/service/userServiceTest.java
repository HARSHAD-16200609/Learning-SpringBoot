package com.example.Journal.service;

import com.example.Journal.Repository.UserRepository;
import com.example.Journal.entity.User;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class userServiceTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testFindByUsername() {
        Optional<User> userOptional = userRepository.findByUsername("Askelaad..");

        assertTrue(userOptional.isPresent()); // Ensure the user was found
        User user = userOptional.get(); // Extract the actual User object
        assertNotNull(user);// Optional test to ensure the extracted user object is indeed not null
        assertTrue(!user.getJournals().isEmpty());
    }
}