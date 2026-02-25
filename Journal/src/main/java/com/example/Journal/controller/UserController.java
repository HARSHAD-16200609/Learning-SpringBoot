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

        if (optionalUser == null) {
            return "Invalid credentials";
        }

        if (optionalUser.getPassword().equals(req.getPassword())) {
            return "Login Successful";
        }

        return "Invalid credentials";
    }

    @PostMapping("/register")
    public String Register(@RequestBody User req) {
        try {
            userService.Register(req);
            return "Registration Successful";
        } catch (Exception e) {
            return "Exception : " + e + " has occurred";
        }
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{username}")
    public ResponseEntity<User> getUserByUsername(@PathVariable String username) {
        User user = userService.findByUsername(username);
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping
    public ResponseEntity<?> updatePassword(@RequestBody User user) {
        User userInDb = userService.findByUsername(user.getUsername());
        if (userInDb != null) {
            userInDb.setPassword(user.getPassword());
            userService.Register(userInDb);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{username}")
    public ResponseEntity<?> deleteUser(@PathVariable String username) {
        User userInDb = userService.findByUsername(username);
        if (userInDb != null) {
            userService.DelUser(userInDb.getID());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
