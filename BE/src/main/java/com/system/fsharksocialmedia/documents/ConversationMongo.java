package com.system.fsharksocialmedia.documents;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Document(collection = "conversations")
public class ConversationMongo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    String sender;
    String recipient;
    String content;
    Instant time;
}
