package com.thecodingcult.chat_service.service;

import com.thecodingcult.chat_service.model.ChatMessage;
import com.thecodingcult.chat_service.model.MessageStatus;
import com.thecodingcult.chat_service.exception.UnauthorizedActionException;
import com.thecodingcult.chat_service.repository.ChatMessageRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ChatService {

    private final ChatMessageRepository chatMessageRepository;

    public ChatService(ChatMessageRepository chatMessageRepository) {
        this.chatMessageRepository = chatMessageRepository;
    }

    public ChatMessage saveMessage(ChatMessage message) {
        if (message == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Message cannot be null");
        }
        message.setStatus(MessageStatus.SENT);
        return chatMessageRepository.save(message);
    }

    public List<ChatMessage> getMessagesForGroup(String groupId) {
        if (groupId == null || groupId.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Group ID cannot be null or empty");
        }
        return chatMessageRepository.findByGroupIdOrderByTimestampAsc(groupId);
    }

    public ChatMessage editMessage(Long messageId, ChatMessage updatedMessage, String username) {
        if (updatedMessage == null || updatedMessage.getContent() == null || updatedMessage.getContent().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Updated message content cannot be null or empty");
        }
        ChatMessage existingMessage = chatMessageRepository.findById(messageId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Message not found"));
        if (!existingMessage.getSender().equals(username)) {
            throw new UnauthorizedActionException("You can only edit your own messages");
        }
        existingMessage.setContent(updatedMessage.getContent());
        existingMessage.setTimestamp(LocalDateTime.now());
        return chatMessageRepository.save(existingMessage);
    }

    public void deleteMessage(Long messageId, String username) {
        ChatMessage existingMessage = chatMessageRepository.findById(messageId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Message not found"));
        if (!existingMessage.getSender().equals(username)) {
            throw new UnauthorizedActionException("You can only delete your own messages");
        }
        chatMessageRepository.delete(existingMessage);
    }
}