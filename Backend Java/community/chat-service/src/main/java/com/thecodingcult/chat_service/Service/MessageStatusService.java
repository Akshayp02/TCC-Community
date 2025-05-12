package com.thecodingcult.chat_service.service;

import com.thecodingcult.chat_service.model.Message;
import com.thecodingcult.chat_service.model.MessageStatus;
import com.thecodingcult.chat_service.repository.MessageRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MessageStatusService {

    private final MessageRepository messageRepository;

    public MessageStatusService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public void updateMessageStatus(Long messageId, MessageStatus status) {
        Optional<Message> optionalMessage = messageRepository.findById(messageId);
        if (optionalMessage.isPresent()) {
            Message message = optionalMessage.get();
            message.setStatus(status); // Pass the enum directly
            messageRepository.save(message); // Save updated status
        }
    }
}