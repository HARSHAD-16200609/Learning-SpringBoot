package com.example.Journal.Repository;


import com.example.Journal.entity.configjournalappEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface configJournalApp extends MongoRepository<configjournalappEntity, ObjectId> {

}
