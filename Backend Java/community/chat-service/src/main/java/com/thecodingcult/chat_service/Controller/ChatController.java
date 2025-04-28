package com.thecodingcult.chat_service.Controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

import com.thecodingcult.chat_service.Entity.ChatMessage;
import com.thecodingcult.chat_service.Service.ChatService;

@RestController
@RequestMapping("/api/chat")
public class ChatController {

    private final ChatService chatService;

    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @PostMapping("/send")
    public ResponseEntity<ChatMessage> sendMessage
            (@RequestBody ChatMessage message,
             HttpServletRequest requests) {
        String username = (String) requests.getAttribute("username"); // Extracted from JWT
        message.setSender(username);
        ChatMessage saved = chatService.saveMessage(message);
        return ResponseEntity.ok(saved);
    }

    @PutMapping("/edit/{messageId}")
    public ResponseEntity<ChatMessage> editMessage(
            @PathVariable Long messageId,
            @RequestBody ChatMessage updatedMessage,
            HttpServletRequest request) {
        String username = (String) request.getAttribute("username"); // Extracted from JWT
        ChatMessage editedMessage = chatService.editMessage(messageId, updatedMessage, username);
        return ResponseEntity.ok(editedMessage);
    }

    @DeleteMapping("/delete/{messageId}")
    public ResponseEntity<Void> deleteMessage(
            @PathVariable Long messageId,
            HttpServletRequest request) {
        String username = (String) request.getAttribute("username"); // Extracted from JWT
        chatService.deleteMessage(messageId, username);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/group/{groupId}")
    public ResponseEntity<List<ChatMessage>> getGroupMessages(@PathVariable String groupId) {
        return ResponseEntity.ok(chatService.getMessagesForGroup(groupId));
    }
}