package com.example.Journal.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheck {

    @GetMapping("/HealthCheck")
    public String healthCheck(){
        return "OK Harshad";
    }

}
