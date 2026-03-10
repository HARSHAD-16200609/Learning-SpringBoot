package com.example.Journal.controller;

import com.example.Journal.apiResponse.quoteResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class QuoteService {
    private static String URL = "https://dummyjson.com/quotes/random";
    @Autowired
    RestTemplate restTemplate;

    quoteResponse getQuote() {

        ResponseEntity<quoteResponse> quoteRes = restTemplate.exchange(URL, HttpMethod.GET, null, quoteResponse.class);
        quoteResponse quoteResponse = quoteRes.getBody();

        return quoteResponse;
    }
}
