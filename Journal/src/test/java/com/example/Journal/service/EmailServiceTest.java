package com.example.Journal.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EmailServiceTest {

@Autowired
    EmailService emailService;

@Test
  public void TestEmailService(){
      emailService.sendEmail("diwateujjwala@gmail.com","Testing Purpose", "Hii   this email is sent to test email service");
  }

}
