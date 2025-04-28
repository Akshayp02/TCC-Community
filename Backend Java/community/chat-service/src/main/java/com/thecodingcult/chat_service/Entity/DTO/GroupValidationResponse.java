package com.thecodingcult.chat_service.Entity.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GroupValidationResponse implements Serializable {
    private static final long serialVersionUID = 1L;

    private boolean groupExists;
    private boolean isMember;
}