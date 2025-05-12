package com.thecodingcult.chat_service.dto;

import com.thecodingcult.chat_service.model.MessageStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class MessageResponseDTO {
    // Getters and setters
    private Long id;
    private Long groupId;
    private String content;
    private MessageStatus status;
    private LocalDateTime timestamp;

}