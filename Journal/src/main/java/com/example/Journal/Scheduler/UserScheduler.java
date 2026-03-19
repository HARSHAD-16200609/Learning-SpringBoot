package com.example.Journal.Scheduler;

import com.example.Journal.Repository.userRepositoryImpl;
import com.example.Journal.cache.AppCache;
import com.example.Journal.entity.JournalEntry;
import com.example.Journal.entity.User;
import com.example.Journal.service.EmailService;
import com.example.Journal.service.SentimentslAnalysisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Slf4j
public class UserScheduler {

    @Autowired
    EmailService emailService;
    @Autowired
    userRepositoryImpl userRepositoryimpl;
    @Autowired
    SentimentslAnalysisService sentimentslAnalysisService;
    @Autowired
    AppCache appCache;

    @Scheduled(cron = "0 * * * * ?")
    public void fetchUsersAndSendSAMail() {
        try {
            List<User> users = userRepositoryimpl.getUserforSA();
            for (User usr : users) {
                List<JournalEntry> journalEntries = usr.getJournals();
                if (journalEntries != null) {
                    List<String> filteredEntries = journalEntries.stream()
                            .filter(x -> x.getDate().isAfter(LocalDateTime.now().minus(7, ChronoUnit.DAYS)))
                            .map(x -> x.getContent()).collect(Collectors.toList());
                    String Entry = String.join(" ", filteredEntries);
                    String sentiment = sentimentslAnalysisService.getSentiment(Entry);
                    emailService.sendEmail(usr.getEmail(), "Sentiment for last 7 Days", sentiment);
                    System.out.println("Email Sent");
                }
            }
        } catch (Exception e) {
            log.error("Error occured while fetching users for sentimental anaysis and sending email", e);
        }

    }

    @Scheduled(cron = "0 0/5 * 1/1 * ?")
    public void clearCache(){
        appCache.init();
    }

}
