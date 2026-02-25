package com.example.Journal.service;

import com.example.Journal.Repository.JournalEntryRepository;
import com.example.Journal.entity.JournalEntry;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserService {
    @Autowired
    private JournalEntryRepository JournalEntryRepository;

    public void saveJournal(JournalEntry journal) {
        JournalEntryRepository.save(journal);
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
