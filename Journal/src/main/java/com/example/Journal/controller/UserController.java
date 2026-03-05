package com.example.Journal.controller;

import com.example.Journal.Repository.UserRepository;
import com.example.Journal.entity.User;
import com.example.Journal.service.JournalEntryService;
import com.example.Journal.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
private PasswordEncoder passwordEncoder;
    @Autowired
    private UserService userService;

    @PostMapping("auth/login")
    public String login() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String Username = authentication.getName();
        User optionalUser = userService.findByUsername(Username);

        if (optionalUser == null) {
            return "Invalid credentials";
        }
            return "Login Successful";
    }

    @PostMapping("auth/register")
    public String Register(@RequestBody User req) {
        try {

            userService.save(req);
            return "Registration Successful";
        } catch (Exception e) {
            return "Exception : " + e + " has occurred";
        }
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("me")
    public ResponseEntity<User> getUserByUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String Username = authentication.getName();
        User user = userService.findByUsername(Username);

        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/updateUser")
    public ResponseEntity<?> updatePassword(@RequestBody User user) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String Username = authentication.getName();
        User userInDb = userService.findByUsername(Username);

        if (userInDb != null) {
            userInDb.setPassword(user.getPassword());
            userInDb.setUsername(user.getUsername());
            userService.save(userInDb);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/delUser")
    public ResponseEntity<?> deleteUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User userInDb = userService.findByUsername(username);
        if (userInDb != null) {
            userService.DelUser(userInDb.getID());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
