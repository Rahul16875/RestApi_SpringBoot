package org.rahul.demo.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.rahul.demo.entity.JournalEntry;
import org.rahul.demo.entity.User;
// import org.rahul.demo.entity.User;
import org.rahul.demo.repository.JournalEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


@Component
public class JournalEntryService {
    
    @Autowired
    private JournalEntryRepository journalEntryRepository;

    @Autowired 
    private UserService userService;
    
    @Transactional

    //  this annotation means ya to pura code operate hoga ya agar khi fail hota h to jitna execute hua h wapa roll back ho jayega. 
    //  we have put here this annotation because if sometimes there is a entry which is going to save in journalEntry bt due to some error this is not saving in User so we are putting this @transactional notaion to avoid this.

    public void saveEntry(JournalEntry journalEntry, String userName){
        
        try{
        User user = userService.findByUserName(userName);
        journalEntry.setDate(LocalDateTime.now());
        JournalEntry saved =  journalEntryRepository.save(journalEntry);
        // .save is predefined function made in repository which will automatically save entries.

        user.getJournalEntries().add(saved);
        userService.saveUser(user);

    }   catch(Exception e){
        throw new RuntimeException("An error occured while saving the entry.",e);
    }
}


    public void saveEntry(JournalEntry journalEntry){
        journalEntryRepository.save(journalEntry);
    }

    public List<JournalEntry> getAll(){
        return journalEntryRepository.findAll();
        // .findAll() is predefined function made in repository which will automaticallty show all entries.
    }

    public Optional<JournalEntry> findById(ObjectId id){
        return journalEntryRepository.findById(id);
    }

    public void deleteById(ObjectId id, String userName){
        User user = userService.findByUserName(userName);
        user.getJournalEntries().removeIf(x -> x.getId().equals(id));
        userService.saveNewUser(user);
        journalEntryRepository.deleteById(id);

    }

    // public List<JournalEntry> findByUserName(String userName){
    //     return 
    // }
}


// controller ---> service ---> repository.

