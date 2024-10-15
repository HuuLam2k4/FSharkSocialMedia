package com.system.fsharksocialmedia.controllers;

import com.system.fsharksocialmedia.models.MessageModel;
import com.system.fsharksocialmedia.services.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {
    @Autowired
    private ChatService chatService;

    //    @MessageMapping("/chat.sendMessage")
//    @SendTo("/topic/public")
//    public MessageModel sendMessage(
//            @Payload MessageModel chatMessage) {
//        return chatMessage;
//    }
//    @MessageMapping("/chat.addUser")
//    @SendTo("/topic/public")
//    public MessageModel addUser(
//            @Payload MessageModel chatMessage,
//            SimpMessageHeaderAccessor headerAccessor
//    ) {
//        headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
//        return chatMessage;
//    }
    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public MessageModel sendMessage(@Payload MessageModel chatMessage) {
        // Lưu tin nhắn vào cơ sở dữ liệu thông qua service
        chatService.saveMessage(chatMessage);
        return chatMessage;
    }

    // Thêm người dùng vào chat
    @MessageMapping("/chat.addUser")
    @SendTo("/topic/public")
    public MessageModel addUser(@Payload MessageModel chatMessage, SimpMessageHeaderAccessor headerAccessor) {
        headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
        return chatMessage;
    }
}