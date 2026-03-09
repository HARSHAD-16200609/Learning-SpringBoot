package com.example.Journal.service;
import com.example.Journal.Repository.UserRepository;
import com.example.Journal.entity.User;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Disabled
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

    @ParameterizedTest
    @CsvSource({
            "1,2,3",
            "5,6,8",
            "5,2,8",
            "2,9,4",
            "1,4,5"
    })
    public void parameterizedTest(int a , int b , int expected){
        assertEquals(expected,a+b); // result 2 pass 3 fail

    }
    @ParameterizedTest
    @CsvSource({
            "Askelaad..",
            "Askelaad.",
            "harshad",
            "admin"
    })
    public void findUsers(String username){
        Optional<User> userOptional = userRepository.findByUsername(username);
        assertTrue(userOptional.isPresent());
        System.out.println(userOptional.isPresent());
        User user = userOptional.get();
        assertFalse(user.getJournals().isEmpty());
    }

}