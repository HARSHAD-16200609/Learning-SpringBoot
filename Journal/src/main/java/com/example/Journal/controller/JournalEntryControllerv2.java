package com.example.Journal.controller;
import com.example.Journal.JournalEntry;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/journal")
public class JournalEntryControllerv2 {


    @GetMapping("/list")
    public List<JournalEntry> getRequsetBody(){
        return null;
    }

    @PostMapping("/create")
    public String createJournal(@RequestBody JournalEntry journal){

        return null;
    }

    @GetMapping("id/{journalId}")
    public JournalEntry getJournal(@PathVariable Long journalId) {

      return null;


    }
    @DeleteMapping ("id/{journalId}")
    public String DeleteJournal(@PathVariable Long journalId) {

        return null;
    }

    @PutMapping ("id/{journalId}")
    public JournalEntry UpdateJournal(@PathVariable Long journalId,@RequestBody JournalEntry newEntry) {

        return null;

    }
}





