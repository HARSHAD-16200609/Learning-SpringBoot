package com.example.Journal.cache;

import com.example.Journal.Repository.configJournalApp;
import com.example.Journal.entity.configjournalappEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;
import jakarta.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class AppCache {

    @Autowired
    configJournalApp configjournalApp;
    public Map<String, String> APP_CACHE;

    public configjournalappEntity save(configjournalappEntity cfge) {

        cfge.setDate(LocalDateTime.now());

        configjournalApp.save(cfge);
        return cfge;
    }

    @PostConstruct
    public void init() {
        APP_CACHE = new HashMap<>();
        List<configjournalappEntity> all = configjournalApp.findAll();
        for (configjournalappEntity entity : all) {
            APP_CACHE.put(entity.getKey(), entity.getValue());
        }
    }

}
