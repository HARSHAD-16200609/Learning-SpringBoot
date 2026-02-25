package com.example.Journal.controller;

import com.example.Journal.entity.JournalEntry;
import com.example.Journal.entity.User;
import com.example.Journal.service.JournalEntryService;
import com.example.Journal.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/journal")
public class JournalEntryControllerv2 {

    @Autowired
    private JournalEntryService journalentryservice;

    @Autowired
    private UserService userService;

    @GetMapping("/list/{username}")
    public ResponseEntity<List<JournalEntry>> getAllJournalsofUser(@PathVariable String username) {

        User user = userService.findByUsername(username);

        List<JournalEntry> journals = user.getJournals();

        return new ResponseEntity(journals,HttpStatus.OK);
    }

    @PostMapping("/create/{username}")
    public ResponseEntity<JournalEntry> createJournal(@RequestBody JournalEntry journal,@PathVariable String username) {
      try{
          journalentryservice.saveJournal(journal,username);
          return new ResponseEntity<>(journal,HttpStatus.CREATED);
      } catch (Exception e) {
          return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
      }
    }

    @GetMapping("id/{journalId}")
    public ResponseEntity<JournalEntry> getJournal(@PathVariable ObjectId journalId) {
try{
    Optional<JournalEntry> journal = journalentryservice.getJournal(journalId);
    if(journal.isPresent()){
        return  new ResponseEntity<>(journal.get(), HttpStatus.OK);
    }
    return new ResponseEntity<>(null,HttpStatus.NOT_FOUND); // only works if the given id is an objectid strictly just that it is not present in our database if random string theren 400 status

}
catch(Exception e){
    return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
}

    }

    @DeleteMapping("id/{journalId}/{username}")
    public ResponseEntity<?> DeleteJournal(@PathVariable ObjectId journalId,@PathVariable String username) {
        try{
            Optional<JournalEntry> journal = journalentryservice.getJournal(journalId);
            User user = userService.findByUsername(username);

            user.getJournals().remove(journalId);
            userService.save(user);
            if (journal.isPresent()) {
                journalentryservice.DeleteJournal(journalId);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catch(Exception e ){
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PutMapping("id/{journalId}")
    public ResponseEntity<JournalEntry> UpdateJournal(@PathVariable ObjectId journalId, @RequestBody JournalEntry newEntry) {
     try{
         JournalEntry old = journalentryservice.getJournal(journalId).orElse(null);

         if( old != null){
             old.setTitle(newEntry.getTitle() !=null && !newEntry.getTitle().equals("") ? newEntry.getTitle() : old.getTitle());
             old.setContent(newEntry.getContent() !=null && !newEntry.getContent().equals("") ? newEntry.getContent() : old.getContent());
         }
         journalentryservice.saveJournal(old);
         return new ResponseEntity<>(old,HttpStatus.OK);
     }
     catch(Exception e){
         return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
     }

    }
}
