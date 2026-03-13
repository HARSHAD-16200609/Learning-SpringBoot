package com.example.Journal.controller;

import com.example.Journal.cache.AppCache;
import com.example.Journal.entity.User;
import com.example.Journal.service.UserService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class adminController {
    @Autowired
    UserService userService;

    @Autowired
    AppCache appcache;

    @GetMapping("/allUsers")
    public ResponseEntity<?> getAllUserS() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String Username = authentication.getName();
        User user = userService.findByUsername(Username);

        if (user.getRoles().contains("ADMIN")) {
            List<User> users = userService.getAllUsers();
            if (users != null && !users.isEmpty()) {
                return new ResponseEntity<>(users, HttpStatus.OK);
            } else
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);

    }

    @GetMapping("clear-app-cache")
    public void clearCache() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String Username = authentication.getName();
        User user = userService.findByUsername(Username);

        if (user.getRoles().contains("ADMIN")) {
            appcache.init();
        }
    }
}
