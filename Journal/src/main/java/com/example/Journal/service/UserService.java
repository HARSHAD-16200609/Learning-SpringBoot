package com.example.Journal.service;

import com.example.Journal.Repository.JournalEntryRepository;
import com.example.Journal.Repository.UserRepository;
import com.example.Journal.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
@Slf4j // now no need to write the code to instantiate the logger
public class UserService {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;



    // Use for new user registration only - encodes password
    public void save(User user) throws Exception {
        try{
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRoles(Arrays.asList("USER"));
            userRepository.save(user);
        }
      catch(Exception e){
            log.error("ERROR occured for user {}",user.getUsername(),e);    // {} are placeholders we can insert data into it here the username is inserted in the log along with the error
          throw new Exception(e);
      }
    }

    // Use for updating existing users - does NOT re-encode password
    public void saveUserForUpdate(User user) {
        userRepository.save(user);
    }



    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }

    public void DelUser(ObjectId Id) {
        userRepository.deleteById(Id);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
