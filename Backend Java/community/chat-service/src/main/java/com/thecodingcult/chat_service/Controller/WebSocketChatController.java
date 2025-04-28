package com.thecodingcult.chat_service.Controller;

import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.security.Principal;

import com.thecodingcult.chat_service.Entity.ChatMessage;
import com.thecodingcult.chat_service.Service.ChatService;

@Controller
public class WebSocketChatController {

    private final SimpMessagingTemplate messagingTemplate;
    private final ChatService chatService;

    public WebSocketChatController(SimpMessagingTemplate messagingTemplate, ChatService chatService) {
        this.messagingTemplate = messagingTemplate;
        this.chatService = chatService;
    }

    @MessageMapping("/chat/{groupId}")
    public void sendMessage(@DestinationVariable String groupId, ChatMessage chatMessage, Principal principal) {
        chatMessage.setSender(principal.getName()); // Extracted from JWT
        chatMessage.setGroupId(groupId);
        ChatMessage saved = chatService.saveMessage(chatMessage);
        messagingTemplate.convertAndSend("/topic/group/" + groupId, saved);
    }
}
