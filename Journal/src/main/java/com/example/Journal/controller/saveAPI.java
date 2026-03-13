package com.example.Journal.controller;

import com.example.Journal.Repository.configJournalApp;
import com.example.Journal.cache.AppCache;
import com.example.Journal.entity.JournalEntry;
import com.example.Journal.entity.configjournalappEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("cache")
public class saveAPI {

    @Autowired
    AppCache appCache;

    @PostMapping("saveAPI")
    public ResponseEntity<configjournalappEntity> saveAPI(@RequestBody configjournalappEntity cfgae) {
        try {
            if (cfgae.getKey() == null || cfgae.getValue() == null) {
                return new ResponseEntity<>(cfgae, HttpStatus.BAD_REQUEST);
            }
            configjournalappEntity cfg = appCache.save(cfgae);
            if (cfg != null) {
                return new ResponseEntity<>(cfg, HttpStatus.CREATED);
            }
            return new ResponseEntity<>(cfg, HttpStatus.BAD_REQUEST);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
