package com.example.Journal.controller;

import com.example.Journal.apiResponse.weatherResponse;
import com.example.Journal.entity.User;
import com.example.Journal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    WeatherService weatherService;
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
            if (user.getUsername() != null && !user.getUsername().isBlank()) {
                userInDb.setUsername(user.getUsername());
            }
            if (user.getPassword() != null && !user.getPassword().isBlank()) {
                userInDb.setPassword(passwordEncoder.encode(user.getPassword()));
            }
            userService.saveUserForUpdate(userInDb);
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

    @GetMapping("/greeting")
    public ResponseEntity<?> greeting() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User userInDb = userService.findByUsername(username);
        weatherResponse res = weatherService.getWeather("mumbai");
        if (userInDb != null && res != null) {
            String weatherDescription = "";
            if (res.getWeather() != null && !res.getWeather().isEmpty()) {
                weatherDescription = res.getWeather().get(0).getDescription();
            }
            return new ResponseEntity<>("Hii " + username + " Today's weather is " + weatherDescription, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
