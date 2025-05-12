package com.thecodingcult.chat_service.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Data
@Getter
@Setter
@Entity
public class Message {
    @Id
    @GeneratedValue
    private Long id;
    private Long groupId;
    private Long senderId;
    private String content;
    private String type; // TEXT, FILE, IMAGE, VOICE, etc.
    private String attachmentUrl;

    // Updated to accept MessageStatus
    // Updated to return MessageStatus
    @Enumerated(EnumType.STRING)
    private MessageStatus status; // Add status field

    private LocalDateTime timestamp;

    @ManyToOne
    private Message replyTo;

}