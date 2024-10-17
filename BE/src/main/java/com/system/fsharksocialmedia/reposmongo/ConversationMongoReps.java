package com.system.fsharksocialmedia.reposmongo;

import com.system.fsharksocialmedia.documents.ConversationMongo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ConversationMongoReps extends MongoRepository<ConversationMongo, Integer> {
}
