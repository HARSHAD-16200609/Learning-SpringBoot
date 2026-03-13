package com.example.Journal.entity;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Document("config_journal_app")
@Data
@NoArgsConstructor
@Component
public class configjournalappEntity {
    @Id
    private ObjectId Id;
    @NonNull
    private String key;
    private String value;
    private LocalDateTime date;
}
