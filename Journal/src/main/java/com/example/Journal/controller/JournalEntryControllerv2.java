package com.example.Journal.controller;

import com.example.Journal.entity.JournalEntry;
import com.example.Journal.service.JournalEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/journal")
public class JournalEntryControllerv2 {

    @Autowired
    private JournalEntryService journalentryservice;

    @GetMapping("/list")
    public List<JournalEntry> getRequsetBody() {

        return journalentryservice.getAllJournals();

    }

    @PostMapping("/create")
    public JournalEntry createJournal(@RequestBody JournalEntry journal) {
        journal.setDate(LocalDateTime.now());
        journalentryservice.saveJournal(journal);
        return journal;
    }

    @GetMapping("id/{journalId}")
    public JournalEntry getJournal(@PathVariable ObjectId journalId) {

        return journalentryservice.getJournal(journalId).orElse(null);

    }

    @DeleteMapping("id/{journalId}")
    public String DeleteJournal(@PathVariable ObjectId journalId) {
  journalentryservice.DeleteJournal(journalId);

        return "Sucess";
    }

    @PutMapping("id/{journalId}")
    public JournalEntry UpdateJournal(@PathVariable ObjectId journalId, @RequestBody JournalEntry newEntry) {
        JournalEntry old = journalentryservice.getJournal(journalId).orElse(null);

        if( old != null){
            old.setTitle(newEntry.getTitle() !=null && !newEntry.getTitle().equals("") ? newEntry.getTitle() : old.getTitle());
            old.setContent(newEntry.getContent() !=null && !newEntry.getContent().equals("") ? newEntry.getContent() : old.getContent());
        }
        journalentryservice.saveJournal(old);
        return old;

    }
}
