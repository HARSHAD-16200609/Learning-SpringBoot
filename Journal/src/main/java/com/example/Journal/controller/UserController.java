package com.example.Journal.controller;

import com.example.Journal.Repository.UserRepository;
import com.example.Journal.entity.User;
import com.example.Journal.service.JournalEntryService;
import com.example.Journal.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public String login(@RequestBody User req) {

        User optionalUser = userService.findByUsername(req.getUsername());

        if (optionalUser ==null) {
            return "Invalid credentials";
        }

        if (optionalUser.getPassword().equals(req.getPassword())) {
            return "Login Successful";
        }

        return "Invalid credentials";
    }
}
