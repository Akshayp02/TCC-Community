package com.codingcult.Group_service.Models.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GroupValidationResponse {
    private boolean groupExists;
    private boolean isMember;

    public boolean isGroupExists() {
        return groupExists;
    }

    public void setGroupExists(boolean groupExists) {
        this.groupExists = groupExists;
    }

    public boolean isMember() {
        return isMember;
    }

    public void setMember(boolean member) {
        isMember = member;
    }

}
