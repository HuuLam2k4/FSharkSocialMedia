package com.system.fsharksocialmedia.repositories;

import com.system.fsharksocialmedia.entities.Conversation;
import com.system.fsharksocialmedia.entities.Message;
import com.system.fsharksocialmedia.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Integer> {
    List<Message> findByConversation(Conversation conversation);
    List<Message> findByUsersrc(User user);
}