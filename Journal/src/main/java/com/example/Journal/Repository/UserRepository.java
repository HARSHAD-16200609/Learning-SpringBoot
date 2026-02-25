package com.example.Journal.Repository;

import com.example.Journal.entity.JournalEntry;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<JournalEntry, ObjectId> {

}
