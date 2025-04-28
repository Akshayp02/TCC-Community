package com.codingcult.Group_service.Controller;


import com.codingcult.Group_service.Entity.Group;
import com.codingcult.Group_service.Entity.GroupMember;
import com.codingcult.Group_service.Models.DTO.GroupCreateRequest;
import com.codingcult.Group_service.Models.DTO.GroupValidationResponse;
import com.codingcult.Group_service.Repository.GroupRepository;
import com.codingcult.Group_service.Service.GroupService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/groups")
public class GroupController {

    @Autowired
    private GroupService groupService;

    @Autowired
    private GroupRepository groupRepository;

    // 1. Create a new group
    @PostMapping("/create")
    public Group createGroup(
            HttpServletRequest requests,
            @RequestBody GroupCreateRequest request) {
        String username = (String) requests.getAttribute("username");

        return groupService.createGroup(
                request.getGroupName(),
                username,
                request.getCreatedByUserId(),
                request.getDescription()
        );
    }

    // 2. Get a group by ID
    @GetMapping("/{groupId}")
    public Group getGroupById(@PathVariable Long groupId) {
        return groupService.getGroupById(groupId);
    }

    // 3. Get all groups created by a user
    @GetMapping("/user/{userId}")
    public List<Group> getGroupsByUser(@PathVariable Long userId) {
        return groupService.getGroupsByUser(userId);
    }

    // 4. Add a member to a group
    @PostMapping("/{groupId}/members")
    public GroupMember addMember(@PathVariable Long groupId,
                                 @RequestParam Long userId,
                                 @RequestParam String username, // Accept username
                                 @RequestParam(defaultValue = "false") boolean isAdmin) {
        return groupService.addMemberToGroup(groupId, userId, username, isAdmin);
    }

    // 5. Remove a member from a group
    @DeleteMapping("/{groupId}/members/{userId}")
    public String removeMember(@PathVariable Long groupId,
                               @PathVariable Long userId) {
        groupService.removeMemberFromGroup(groupId, userId);
        return "Member removed successfully";
    }

    // 6. Get all members of a group
    @GetMapping("/{groupId}/members")
    public List<GroupMember> getGroupMembers(@PathVariable Long groupId) {
        return groupService.getMembersOfGroup(groupId);
    }

    // 7. Delete a group
    @DeleteMapping("/{groupId}")
    public String deleteGroup(@PathVariable Long groupId) {
        groupService.deleteGroup(groupId);
        return "Group deleted successfully";
    }


    @GetMapping("/{groupId}/members/{username}")
    public ResponseEntity<GroupValidationResponse> validateGroupAndMember(
            @PathVariable Long groupId,
            @PathVariable String username
    ) {
        boolean groupExists = groupRepository.existsById(groupId);
        boolean isMember = groupExists && groupService.isUserInGroup(groupId, username); // check only if group exists

        GroupValidationResponse response = new GroupValidationResponse(groupExists, isMember);
        return ResponseEntity.ok(response);
    }


//    // group-service does member of group is exist
//    @GetMapping("/api/groups/{groupId}/members/{username}")
//    public ResponseEntity<Boolean> isUserMemberOfGroup(
//            @PathVariable Long groupId,
//            @PathVariable String username
//    ) {
//        boolean isMember = groupService.isMember(groupId, username);
//        return ResponseEntity.ok(isMember);
//    }
//
//    // group-service does group is exist
//    @GetMapping("/api/groups/{groupId}/exists")
//    public ResponseEntity<Boolean> doesGroupExist(@PathVariable Long groupId) {
//        boolean exists = groupService.doesGroupExist(groupId);
//        return ResponseEntity.ok(exists);
//    }

    private String extractUsernameFromToken() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName(); // Assumes the username is stored in the token's "name" field
    }
}
