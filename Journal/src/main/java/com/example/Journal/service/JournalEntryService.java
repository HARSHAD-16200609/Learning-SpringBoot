package com.example.Journal.service;

import com.example.Journal.Repository.JournalEntryRepository;
import com.example.Journal.entity.JournalEntry;
import com.example.Journal.entity.User;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class JournalEntryService {
    @Autowired
    private JournalEntryRepository JournalEntryRepository;
    @Autowired
    private UserService userService;

    public void saveJournal(JournalEntry journal,String username) {
        User user = userService.findByUsername(username);
        journal.setDate(LocalDateTime.now());
        JournalEntry saved = JournalEntryRepository.save(journal);
         user.getJournals().add(saved);
         userService.save(user);
    }
    public void saveJournal(JournalEntry journal) {
        JournalEntry saved = JournalEntryRepository.save(journal);
    }

    public List<JournalEntry> getAllJournals() {
        return JournalEntryRepository.findAll();
    }
    public Optional<JournalEntry> getJournal(ObjectId Id) {
        return JournalEntryRepository.findById(Id);
    }
    public void DeleteJournal(ObjectId Id) {
         JournalEntryRepository.deleteById(Id);
    }

}
