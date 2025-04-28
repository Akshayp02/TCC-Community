package com.codingcult.Group_service.Service;


import com.codingcult.Group_service.Entity.Group;
import com.codingcult.Group_service.Entity.GroupMember;
import com.codingcult.Group_service.Repository.GroupMemberRepository;
import com.codingcult.Group_service.Repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GroupServiceImpl implements GroupService {

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private GroupMemberRepository groupMemberRepository;

    @Override
    public Group createGroup(String groupName, String username, Long createdByUserId, String description) {
        Group group = new Group();
        group.setGroupName(groupName);
        group.setUsername(username);
        group.setDescription(description); // Set a default description
        group.setCreatedByUserId(createdByUserId);
        Group savedGroup = groupRepository.save(group);

        // Add creator as admin member
        GroupMember creatorMember = new GroupMember();
        creatorMember.setGroup(savedGroup);
        creatorMember.setUsername(username); // Set the username of the creator
        creatorMember.setUserId(createdByUserId);
        creatorMember.setAdmin(true);
        groupMemberRepository.save(creatorMember);

        return savedGroup;
    }


    @Override
    public Group getGroupById(Long groupId) {
        return groupRepository.findById(groupId)
                .orElseThrow(() -> new RuntimeException("Group not found with ID: " + groupId));
    }

    @Override
    public List<Group> getGroupsByUser(Long userId) {
        return groupRepository.findByCreatedByUserId(userId);
    }

    public GroupMember addMemberToGroup(Long groupId, Long userId, String username, boolean isAdmin) {
        Group group = groupRepository.findById(groupId)
                .orElseThrow(() -> new IllegalArgumentException("Group not found"));

        GroupMember member = new GroupMember();
        member.setGroup(group);
        member.setUserId(userId);
        member.setUsername(username); // Set the username
        member.setAdmin(isAdmin);

        return groupMemberRepository.save(member);
    }

    @Override
    public void removeMemberFromGroup(Long groupId, Long userId) {
        Group group = getGroupById(groupId);
        GroupMember member = groupMemberRepository.findByGroupAndUserId(group, userId);
        if (member != null) {
            groupMemberRepository.delete(member);
        } else {
            throw new RuntimeException("User is not a member of this group.");
        }
    }

    @Override
    public List<GroupMember> getMembersOfGroup(Long groupId) {
        Group group = getGroupById(groupId);
        return groupMemberRepository.findByGroup(group);
    }

    @Override
    public void deleteGroup(Long groupId) {
        Group group = getGroupById(groupId);
        groupRepository.delete(group);
    }


    @Override
    public boolean isMember(Long groupId, String username) {
        Group group = getGroupById(groupId);
        return groupMemberRepository.existsByGroupAndUsername(group, username);
    }

    @Override
    public boolean doesGroupExist(Long groupId) {
        return groupRepository.existsById(groupId);
    }

    @Override
    public boolean isUserInGroup(Long groupId, String username) {
        Group group = getGroupById(groupId); // Ensure the group exists
        return groupMemberRepository.existsByGroupAndUsername(group, username);
    }


}
