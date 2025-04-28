package com.thecodingcult.chat_service.Entity.DTO;

import org.springframework.web.multipart.MultipartFile;

public class MessageDTO {
    private Long groupId;
    private Long senderId;
    private String content;
    private String type;
    private MultipartFile attachment;
    private Long replyTo;
}
