package org.rahul.demo.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.bson.types.ObjectId;
import org.rahul.demo.entity.JournalEntry;
import org.rahul.demo.entity.User;
import org.rahul.demo.service.JournalEntryService;
import org.rahul.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SuppressWarnings("unused")

@RestController
@RequestMapping("/journal")
public class JournalEntryControllerv2 {
    
    @Autowired
    private JournalEntryService journalEntryService;

    @Autowired
    private UserService userService;

    // @GetMapping("{userName}")
    @GetMapping
    public ResponseEntity<?> getAllJournalEntriesOfUser(){

    // public ResponseEntity<?> getAll(){  
        
        // journal entry service me getAll method banaya jo findAll function se sari entries find kr rha. usi ko yha controller me call kiya. 

        Authentication authentication = (Authentication) SecurityContextHolder.getContext().getAuthentication();

        String userName = authentication.getName();
        
        User user = userService.findByUserName(userName);

        // List<JournalEntry> all = journalEntryService.getAll();

        List<JournalEntry> all = user.getJournalEntries();

        if(all != null && !all.isEmpty()){
            return new ResponseEntity<>(all, HttpStatus.OK);
        } 
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

     @PostMapping
    //  public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry myEntry, @PathVariable String userName){

     public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry myEntry){

        //  journal entry service me saveEntry ka method banaya jo .save function use krke entry save kr rha usi ko yha call kiya. 
        try{
            // User user = userService.findByUserName(userName);
            Authentication authentication = (Authentication) SecurityContextHolder.getContext().getAuthentication();
            String userName = authentication.getName();

            journalEntryService.saveEntry(myEntry,userName);
            return new ResponseEntity<>(myEntry,HttpStatus.CREATED);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

     }

    @GetMapping("id/{myId}")  
    public ResponseEntity<JournalEntry> getJournalEntryById(@PathVariable ObjectId myId){       

        Authentication authentication = (Authentication) SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User user = userService.findByUserName(userName);
        List<JournalEntry> collect = user.getJournalEntries().stream().filter(x -> x.getId().equals(myId)).collect(Collectors.toList());

        if(!collect.isEmpty()){
            Optional<JournalEntry> journalEntry = journalEntryService.findById(myId);
            if(journalEntry.isPresent()){
                return new ResponseEntity<>(journalEntry.get(),HttpStatus.OK);
            }
        }
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        
    }   

    @DeleteMapping("id/{userName}/{myId}")  
    public ResponseEntity<?> deleteJournalEntryById(@PathVariable ObjectId myId, @PathVariable String userName){   
        journalEntryService.deleteById(myId,userName);    
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @PutMapping("id/{userName}/{id}")  
    public ResponseEntity<?> updateEntryById(
        @PathVariable ObjectId id,
        @RequestBody JournalEntry newEntry,
        @PathVariable String userName){

        JournalEntry old = journalEntryService.findById(id).orElse(null);
        if (old != null) {
             old.setTitle(newEntry.getTitle() != null && !newEntry.getTitle().equals("") ? newEntry.getTitle() : old.getTitle());

             old.setContent(newEntry.getContent() != null && !newEntry.getContent().equals("") ? newEntry.getContent() : old.getContent());
             journalEntryService.saveEntry(old);
             return new ResponseEntity<>(old,HttpStatus.OK);
             
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);   
    }

}
