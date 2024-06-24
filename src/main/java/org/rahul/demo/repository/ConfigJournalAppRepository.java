package org.rahul.demo.repository;

import org.bson.types.ObjectId;
import org.rahul.demo.entity.ConfigJournalAppEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ConfigJournalAppRepository extends MongoRepository<ConfigJournalAppEntity, ObjectId> {
    
}
