package com.example.Journal.cache;

import com.example.Journal.Repository.configJournalApp;
import com.example.Journal.entity.configjournalappEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Map;

@Component
public class AppCache {

    @Autowired
    configJournalApp configjournalApp;
    public Map<String, String> appcache;

    public configjournalappEntity save(configjournalappEntity cfge) {

        cfge.setDate(LocalDateTime.now());

        configjournalApp.save(cfge);
        return cfge;
    }

    public void init() {
        appcache = null;
    }

}
