package com.system.fsharksocialmedia.controllers;

import com.system.fsharksocialmedia.dtos.ConversationDto;
import com.system.fsharksocialmedia.dtos.MessageDto;
import com.system.fsharksocialmedia.models.ConversationModel;
import com.system.fsharksocialmedia.models.MessageModel;
import com.system.fsharksocialmedia.services.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/chat")
public class ChatController {
    @Autowired
    private ChatService chatService;
    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    // Gửi tin nhắn
    @PostMapping("/messages")
    public ResponseEntity<MessageDto> sendMessage(@RequestBody MessageModel messageModel) {
        MessageDto sentMessage = chatService.sendMessage(messageModel);
        messagingTemplate.convertAndSend("/topic/messages", sentMessage);
        return ResponseEntity.ok(sentMessage);
    }

    // Lấy tin nhắn theo ID cuộc trò chuyện
    @GetMapping("/conversations/{conversationId}/messages")
    public ResponseEntity<List<MessageDto>> getMessagesByConversationId(@PathVariable Integer conversationId) {
        List<MessageDto> messages = chatService.getMessagesByConversationId(conversationId);
        return ResponseEntity.ok(messages);
    }

    // Tạo cuộc trò chuyện
    @PostMapping("/conversations")
    public ResponseEntity<ConversationDto> createConversation(@RequestBody ConversationModel conversationModel) {
        ConversationDto createdConversation = chatService.createConversation(conversationModel);
        return ResponseEntity.ok(createdConversation);
    }
}