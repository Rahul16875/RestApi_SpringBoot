package org.rahul.demo.repository;

import org.bson.types.ObjectId;
import org.rahul.demo.entity.JournalEntry;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface JournalEntryRepository extends MongoRepository<JournalEntry,ObjectId> {

}
