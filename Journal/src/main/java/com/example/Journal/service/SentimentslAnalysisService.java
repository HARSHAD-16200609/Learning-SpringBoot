package com.example.Journal.service;

import org.springframework.stereotype.Service;

@Service
public class SentimentslAnalysisService {

    public String getSentiment(String text){
         if(text.equals(""))
         {
             return "Good";
        }
        return "Bad";
    }


}
