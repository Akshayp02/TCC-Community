package com.thecodingcult.chat_service.dto;

import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

public class MessageDTO {
    @Getter
    private Long groupId;
    @Getter
    private Long senderId;
    @Getter
    private String content;
    @Getter
    private String type;
    private MultipartFile attachment;
    private Long replyTo;

    public String getAttachmentUrl() {
        return attachment != null ? attachment.getOriginalFilename() : null;
    }
}
