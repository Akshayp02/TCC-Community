package com.codingcult.Group_service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GroupCreateRequest {

    @NotBlank(message = "Username is required")
    private String username;

    @NotBlank(message = "Group name is required")
    @Size(min = 3, max = 100, message = "Group name must be between 3 and 100 characters")
    private String groupName;

    @Size(max = 250, message = "Description must be under 250 characters")
    private String description;

    @NotNull(message = "Created by user ID is required")
    private Long createdByUserId;

    @NotBlank(message = "Created by username is required")
    private String createdByUsername;
}
