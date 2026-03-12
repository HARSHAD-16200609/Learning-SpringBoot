package com.example.Journal.service;

import com.example.Journal.Repository.JournalEntryRepository;
import com.example.Journal.entity.JournalEntry;
import com.example.Journal.entity.User;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class JournalEntryService {
    @Autowired
    private JournalEntryRepository JournalEntryRepository;
    @Autowired
    private UserService userService;

    @Transactional
    public void saveJournal(JournalEntry journal, String username) {
        try {
            User user = userService.findByUsername(username);
            journal.setDate(LocalDateTime.now());
            JournalEntry saved = JournalEntryRepository.save(journal);
            user.getJournals().add(saved);
            userService.saveUserForUpdate(user);
        } catch (Exception e) {
            System.out.println(e);
            throw new RuntimeException(e);
        }
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

    @Transactional
    public void DeleteJournal(ObjectId id, String username) {
        JournalEntryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Journal not found"));
        User user = userService.findByUsername(username);
        user.getJournals().removeIf(j -> j.getId().equals(id));
        userService.saveUserForUpdate(user);
        JournalEntryRepository.deleteById(id);
    }

    public void DeleteJournal(ObjectId Id) {
        JournalEntryRepository.deleteById(Id);
    }

}
