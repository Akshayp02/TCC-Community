package com.codingcult.Group_service.Service;


import com.codingcult.Group_service.Entity.Group;
import com.codingcult.Group_service.Entity.GroupMember;

import java.util.List;

public interface GroupService {

    Group createGroup(String groupName, String username, Long createdByUserId, String description);

    Group getGroupById(Long groupId);

    List<Group> getGroupsByUser(Long userId);

    GroupMember addMemberToGroup(Long groupId, Long userId, String username, boolean isAdmin);

    void removeMemberFromGroup(Long groupId, Long userId);

    List<GroupMember> getMembersOfGroup(Long groupId);

    void deleteGroup(Long groupId);

    boolean isMember(Long groupId, String username);

    boolean doesGroupExist(Long groupId);

    boolean isUserInGroup(Long groupId, String username);
}
