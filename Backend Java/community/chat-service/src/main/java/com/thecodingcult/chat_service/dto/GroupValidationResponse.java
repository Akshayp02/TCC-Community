package com.thecodingcult.chat_service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GroupValidationResponse implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private boolean groupExists;
    private boolean isMember;
}