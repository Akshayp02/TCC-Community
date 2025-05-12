package com.thecodingcult.chat_service.service;

import com.thecodingcult.chat_service.dto.MessageDTO;
import com.thecodingcult.chat_service.dto.MessageResponseDTO;
import com.thecodingcult.chat_service.model.Message;
import com.thecodingcult.chat_service.model.MessageStatus;
import org.springframework.stereotype.Service;

@Service
public class SendMessageService {

    public MessageResponseDTO sendMessage(MessageDTO dto) {
        Message message = new Message();
        message.setSenderId(dto.getSenderId());
        message.setGroupId(dto.getGroupId());
        message.setContent(dto.getContent());
        message.setType(dto.getType());
        message.setAttachmentUrl(dto.getAttachmentUrl());
        message.setTimestamp(java.time.LocalDateTime.now());
        message.setStatus(MessageStatus.SENT);

        // Save the message to the database (repository logic not shown)

        // Map to DTO
        MessageResponseDTO responseDTO = new MessageResponseDTO();
        responseDTO.setId(message.getId());
        responseDTO.setGroupId(message.getGroupId());
        responseDTO.setContent(message.getContent());
        responseDTO.setStatus(message.getStatus());
        responseDTO.setTimestamp(message.getTimestamp());

        return responseDTO;
    }
}