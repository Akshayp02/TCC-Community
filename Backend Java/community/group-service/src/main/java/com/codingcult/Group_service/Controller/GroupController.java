package com.codingcult.Group_service.controller;

import com.codingcult.Group_service.dto.AddMemberRequest;
import com.codingcult.Group_service.dto.GroupCreateRequest;
import com.codingcult.Group_service.dto.GroupValidationResponse;
import com.codingcult.Group_service.model.Group;
import com.codingcult.Group_service.model.GroupMember;
import com.codingcult.Group_service.service.GroupService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;
import java.util.List;


@RestController
@RequestMapping("/api/groups")
public class GroupController {

    @Autowired(required = true)
    private GroupService groupService;

    // 1. Create a new group
    @PostMapping("/create")
    public Group createGroup(HttpServletRequest request, @Valid @RequestBody GroupCreateRequest groupRequest) {
        String username = (String) request.getAttribute("username");
        return groupService.createGroup(
                groupRequest.getGroupName(),
                username,
                groupRequest.getCreatedByUserId(),
                groupRequest.getDescription()
        );
    }

    // 2. Get a group by ID
    @GetMapping("/{groupId}")
    public Group getGroupById(@PathVariable Long groupId) {
        return groupService.getGroupById(groupId);
    }

   // 3. Get all groups created by a user
   @GetMapping("/user/groups")
   public List<Group> getGroupsByUser(HttpServletRequest request) {
       String username = (String) request.getAttribute("username");
       return groupService.getGroupsByUser(username);
   }

    // 4. Add a member to a group
    @PostMapping("/{groupId}/add-member")
    public ResponseEntity<String> addGroupMember(
            @PathVariable Long groupId,
            @RequestBody AddMemberRequest request,
            @RequestHeader("Authorization") String token) {

        groupService.addMemberToGroup(
                groupId,
                request.getUserId(),
                request.getUsername(),
                request.isAdmin() // or true/false depending on your logic
        );

        return ResponseEntity.ok("Member added successfully");

    }
    // 5. Remove a member from a group
    @DeleteMapping("/{groupId}/members/{userId}")
    public ResponseEntity<String> removeMemberFromGroup(@PathVariable Long groupId, @PathVariable Long userId) {
        groupService.removeMemberFromGroup(groupId, userId);
        return ResponseEntity.ok("Member removed successfully");
    }

    // 6. Get all members of a group
    @GetMapping("/{groupId}/members")
    public List<GroupMember> getGroupMembers(@PathVariable Long groupId) {
        return groupService.getMembersOfGroup(groupId);
    }

    // 7. Delete a group
    @DeleteMapping("/{groupId}")
    public ResponseEntity<String> deleteGroup(@PathVariable Long groupId) {
        groupService.deleteGroup(groupId);
        return ResponseEntity.ok("Group deleted successfully");
    }

    // 8. Validate group existence and membership
    @GetMapping("/{groupId}/members/{username}")
    public ResponseEntity<GroupValidationResponse> validateGroupAndMember(
            @PathVariable Long groupId,
            @PathVariable String username) {
        boolean groupExists = groupService.doesGroupExist(groupId);
        boolean isMember = groupExists && groupService.isUserInGroup(groupId, username);
        return ResponseEntity.ok(new GroupValidationResponse(groupExists, isMember));
    }

    // 9. Update group details
    @PutMapping("/{groupId}")
    public Group updateGroup(@PathVariable Long groupId, @RequestBody GroupCreateRequest request) {
        return groupService.updateGroup(groupId, request.getGroupName(), request.getDescription());
    }

    @GetMapping("/my-groups")
    public List<Group> getMyGroups(HttpServletRequest request) {
        String username = (String) request.getAttribute("username");
        return groupService.getGroupsByUsername(username);
    }

}