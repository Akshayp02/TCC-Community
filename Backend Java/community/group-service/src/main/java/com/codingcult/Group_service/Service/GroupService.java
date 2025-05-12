package com.codingcult.Group_service.service;


import com.codingcult.Group_service.model.Group;
import com.codingcult.Group_service.model.GroupMember;
import org.springframework.stereotype.Service;

import java.util.List;

public interface GroupService {

    Group createGroup(String groupName, String username, Long createdByUserId, String description);

    Group getGroupById(Long groupId);

    List<Group> getGroupsByUser(String username);

   GroupMember addMemberToGroup(Long groupId, Long userId, String username, boolean isAdmin);

    void removeMemberFromGroup(Long groupId, Long userId);

    List<GroupMember> getMembersOfGroup(Long groupId);

    void deleteGroup(Long groupId);

    boolean isMember(Long groupId, String username);

    boolean doesGroupExist(Long groupId);

    boolean isUserInGroup(Long groupId, String username);

    Group updateGroup(Long groupId, String groupName, String description);

    List<Group> getGroupsByUser(Long userId);

    List<Group> getGroupsByUsername(String username);


}
