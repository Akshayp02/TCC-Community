package com.thecodingcult.chat_service.controller;

import com.thecodingcult.chat_service.dto.ChatMessageResponseDTO;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import com.thecodingcult.chat_service.model.ChatMessage;
import com.thecodingcult.chat_service.service.ChatService;

@RestController
@RequestMapping("/api/chat")
public class ChatController {

    private final ChatService chatService;

    @Autowired
    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }



    @PostMapping("/send")
    public ResponseEntity<ChatMessageResponseDTO> sendMessage(
            @RequestBody ChatMessage message,
            HttpServletRequest requests) {
        String username = (String) requests.getAttribute("username"); // Extracted from JWT
        message.setSender(username);
        ChatMessage savedMessage = chatService.saveMessage(message);

        // Map ChatMessage to ChatMessageResponseDTO
        ChatMessageResponseDTO responseDTO = new ChatMessageResponseDTO();
        responseDTO.setId(savedMessage.getId());
        responseDTO.setSender(savedMessage.getSender());
        responseDTO.setContent(savedMessage.getContent());
        responseDTO.setGroupId(savedMessage.getGroupId());
        responseDTO.setStatus(savedMessage.getStatus());
        responseDTO.setTimestamp(savedMessage.getTimestamp());

        return ResponseEntity.ok(responseDTO);
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