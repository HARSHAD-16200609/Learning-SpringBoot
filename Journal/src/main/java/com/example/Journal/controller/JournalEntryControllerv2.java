package com.example.Journal.controller;

import com.example.Journal.entity.JournalEntry;
import com.example.Journal.entity.User;
import com.example.Journal.service.JournalEntryService;
import com.example.Journal.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/journal")
@Slf4j
@Tag(name = "Journal APIs")
public class JournalEntryControllerv2 {

    @Autowired
    private JournalEntryService journalentryservice;

    @Autowired
    private UserService userService;

    @GetMapping("/list")
    public ResponseEntity<List<JournalEntry>> getAllJournalsofUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String Username = authentication.getName();
        User user = userService.findByUsername(Username);

        List<JournalEntry> journals = user.getJournals();
        log.info("Fetched {} journal entries for user {}", journals.size(), Username);
        return new ResponseEntity(journals, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<JournalEntry> createJournal(@RequestBody JournalEntry journal) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String Username = authentication.getName();
            journalentryservice.saveJournal(journal, Username);
            log.info("Journal created successfully for user {}", Username);
            return new ResponseEntity<>(journal, HttpStatus.CREATED);
        } catch (Exception e) {
            log.error("Failed to create journal for user ", e);
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("id/{journalId}")
    public ResponseEntity<JournalEntry> getJournal(@PathVariable ObjectId journalId) {
        try {
            Optional<JournalEntry> journal = journalentryservice.getJournal(journalId);
            if (journal.isPresent()) {
                return new ResponseEntity<>(journal.get(), HttpStatus.OK);
            }
            log.warn("Journal not found for id {}", journalId);
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            // only works if the given id is an objectid
            // strictly just that it is not present in our
            // database if random string theren 400 status

        } catch (Exception e) {
            log.warn("Unauthorized Request",e);
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

    }

    @DeleteMapping("id/{journalId}")
    public ResponseEntity<JournalEntry> DeleteJournal(@PathVariable ObjectId journalId) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String username = authentication.getName();
            journalentryservice.DeleteJournal(journalId, username);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            log.error("Failed to delete journal {} for user ", journalId, e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("id/{journalId}")
    public ResponseEntity<JournalEntry> UpdateJournal(@PathVariable ObjectId journalId,
            @RequestBody JournalEntry newEntry) {
        try {
            JournalEntry old = journalentryservice.getJournal(journalId).orElse(null);

            if (old != null) {
                old.setTitle(newEntry.getTitle() != null && !newEntry.getTitle().equals("") ? newEntry.getTitle()
                        : old.getTitle());
                old.setContent(
                        newEntry.getContent() != null && !newEntry.getContent().equals("") ? newEntry.getContent()
                                : old.getContent());
            }
            journalentryservice.saveJournal(old);
            return new ResponseEntity<>(old, HttpStatus.OK);
        } catch (Exception e) {
            log.error("ERRROR OCCCURED UPDATING",e);
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

    }
}
