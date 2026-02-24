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
public class JournalEntryController {
    private Map<Long,JournalEntry> Journals= new HashMap<>();

@GetMapping("/list")
    public List<JournalEntry> getRequsetBody(){
  return new ArrayList<>(Journals.values());
    }

    @PostMapping("/create")
    public String createJournal(@RequestBody JournalEntry journal){
       Journals.put(journal.getId(),journal);
return "Sucess";
    }

    @GetMapping("id/{journalId}")
    public JournalEntry getJournal(@PathVariable Long journalId) {
    JournalEntry entry = Journals.get(journalId);
        if(entry == null) return null;
return entry;

    }
    @DeleteMapping ("id/{journalId}")
    public String DeleteJournal(@PathVariable Long journalId) {

    Journals.remove(journalId);
if(Journals.get(journalId) == null) return "Sucess";
return "Failed to Delete";
    }

    @PutMapping ("id/{journalId}")
    public JournalEntry UpdateJournal(@PathVariable Long journalId,@RequestBody JournalEntry newEntry) {

        Journals.put(journalId,newEntry);
        JournalEntry Entry =Journals.get(journalId);

        return Entry;

    }
}





