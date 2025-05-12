package com.thecodingcult.chat_service.model;

import lombok.Getter;

@Getter
public enum MessageStatus {
    SENT("Single grey tick"),
    DELIVERED("Double grey tick"),
    READ("Double blue tick");

    private final String description;

    MessageStatus(String description) {
        this.description = description;
    }

}