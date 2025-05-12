package com.codingcult.Group_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GroupValidationResponse {
    private boolean groupExists;
    private boolean isMember;

}
