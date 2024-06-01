// package org.rahul.demo.controller;

// import java.util.ArrayList;
// import java.util.HashMap;
// import java.util.List;
// import java.util.Map;

// import org.rahul.demo.entity.JournalEntry;
// import org.springframework.web.bind.annotation.DeleteMapping;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.PutMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;

// // @SuppressWarnings("unused")

// @RestController
// @RequestMapping("/_journal")
// public class JournalEntryController {
    
//     private Map<Long, JournalEntry> journalEntries = new HashMap<>();

//     // @GetMapping("/abc")
//     @GetMapping     // by this we will get all entries.
//     public List<JournalEntry> getAll(){  //localhost:8080/journal GET
//         return new ArrayList<>(journalEntries.values());
//     }

//     // now above method ka path journal/abc hoga.

//     // @GetMapping("/abcd")
//     // public List<JournalEntry> getAlls(){
//     //     return new ArrayList<>(journalEntries.values());
//     // }
    
//      // now above method ka path journal/abcd hoga.

//      @PostMapping   // we can add entries
//      public boolean createEntry(@RequestBody JournalEntry myEntry){   //localhost:8080/journal POST
//         journalEntries.put(myEntry.getId(), myEntry);
//         return true;
//      }

//     @GetMapping("id/{myId}")  // we can see entires by uniques id.
//     public JournalEntry getJournalEntryById(@PathVariable Long myId){       //localhost:8080/journal/id/{id_number_which we want to get}

//             return journalEntries.get(myId);  
//     }   

//     @DeleteMapping("id/{myId}")  // We can delete unique entry 
//     public JournalEntry deleteJournalEntryById(@PathVariable Long myId){       //localhost:8080/journal/id/{id_number_which we want to delete}
    
//             journalEntries.remove(myId);  
//             return journalEntries.remove(myId);  
//     }


//     @PutMapping("id/{id}")  // we can update unique entry by id by @PutMapping
//     public JournalEntry updateEntryById(@PathVariable Long id, @RequestBody JournalEntry myEntry){
//         journalEntries.put(id, myEntry);
//         return journalEntries.put(id, myEntry);
//     }

// }
