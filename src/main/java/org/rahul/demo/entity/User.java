package org.rahul.demo.entity;
import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.mongodb.lang.NonNull;

import lombok.Data;

@Document(collection = "users")
@Data
public class User {

    @Id
    private ObjectId id;

    @Indexed(unique = true)
    @NonNull
    private String userName;
//  yha par we want to create unique indexes for each username but this will not create automaticaaly so we have written manual on application.properties file. 

    @NonNull
    private String password;

    @DBRef
    private List<JournalEntry> journalEntries = new ArrayList<>();

    private List<String> roles;
// @DBRef:
// This annotation tells Spring Data MongoDB that journalEntries is a reference to documents in another collection, rather than embedded documents. It will store a reference to the JournalEntry documents, typically their IDs, in the journalEntries field.

// private List<JournalEntry> journalEntries = new ArrayList<>();:
// This declares a private field named journalEntries, which is a list of JournalEntry objects. It initializes the list as an empty ArrayList.


}