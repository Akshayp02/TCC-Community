package com.thecodingcult.chat_service.Service;

import com.thecodingcult.chat_service.Entity.ChatMessage;
import com.thecodingcult.chat_service.Repository.ChatMessageRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ChatService {

    private final ChatMessageRepository chatMessageRepository;

    public ChatService(ChatMessageRepository chatMessageRepository) {
        this.chatMessageRepository = chatMessageRepository;
    }

    public ChatMessage saveMessage(ChatMessage message) {
        message.setTimestamp(LocalDateTime.now());
        return chatMessageRepository.save(message);
    }

    public List<ChatMessage> getMessagesForGroup(String groupId) {
        return chatMessageRepository.findByGroupIdOrderByTimestampAsc(groupId);
    }

    public ChatMessage editMessage(Long messageId, ChatMessage updatedMessage, String username) {
        ChatMessage existingMessage = chatMessageRepository.findById(messageId)
                .orElseThrow(() -> new IllegalArgumentException("Message not found"));
        if (!existingMessage.getSender().equals(username)) {
            throw new SecurityException("You can only edit your own messages");
        }
        existingMessage.setContent(updatedMessage.getContent());
        existingMessage.setTimestamp(LocalDateTime.now());
        return chatMessageRepository.save(existingMessage);
    }

    public void deleteMessage(Long messageId, String username) {
        ChatMessage existingMessage = chatMessageRepository.findById(messageId)
                .orElseThrow(() -> new IllegalArgumentException("Message not found"));
        if (!existingMessage.getSender().equals(username)) {
            throw new SecurityException("You can only delete your own messages");
        }
        chatMessageRepository.delete(existingMessage);
    }
}