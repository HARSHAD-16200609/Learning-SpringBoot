package com.example.Journal.Scheduler;

import com.example.Journal.Repository.userRepositoryImpl;
import com.example.Journal.cache.AppCache;
import com.example.Journal.entity.JournalEntry;
import com.example.Journal.entity.User;
import com.example.Journal.enums.SentimentEnums;
import com.example.Journal.service.EmailService;
import com.example.Journal.service.SentimentslAnalysisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    @Scheduled(cron = "0 0 9 * * SUN")
    public void fetchUsersAndSendSAMail() {
        try {
            List<User> users = userRepositoryimpl.getUserforSA();
            for (User usr : users) {
                List<JournalEntry> journalEntries = usr.getJournals();
                if (journalEntries != null) {
                    List<SentimentEnums> Sentiments = journalEntries.stream()
                            .filter(x -> x.getDate().isAfter(LocalDateTime.now().minus(7, ChronoUnit.DAYS)))
                            .map(x -> x.getSentiment()).collect(Collectors.toList());

                    Map<SentimentEnums, Integer> sentimentCounts = new HashMap<>();
                    for (SentimentEnums sentiment : Sentiments) {
                        if (sentiment != null)
                            sentimentCounts.put(sentiment, sentimentCounts.getOrDefault(sentiment, 0) + 1);
                    }
                    SentimentEnums mostFrequentSentiment = null;
                    int maxCount = 0;
                    for (Map.Entry<SentimentEnums, Integer> entry : sentimentCounts.entrySet()) {
                        if (entry.getValue() > maxCount) {
                            maxCount = entry.getValue();
                            mostFrequentSentiment = entry.getKey();
                        }
                    }
                    if(mostFrequentSentiment != null){
                        emailService.sendEmail(usr.getEmail(), "Sentiment for last 7 Days",mostFrequentSentiment.toString() );

                    }

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
