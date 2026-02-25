package com.example.Journal.service;

import com.example.Journal.Repository.JournalEntryRepository;
import com.example.Journal.Repository.UserRepository;
import com.example.Journal.entity.User;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public void save(User user) {
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
