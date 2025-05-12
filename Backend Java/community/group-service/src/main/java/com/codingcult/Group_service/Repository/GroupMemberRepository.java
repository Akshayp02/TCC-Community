package com.codingcult.Group_service.repository;

import com.codingcult.Group_service.model.Group;
import com.codingcult.Group_service.model.GroupMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupMemberRepository extends JpaRepository<GroupMember, Long> {

    // Get all members of a group
    List<GroupMember> findByGroup(Group group);

    // Get all groups a user is part of (used for mapping GroupMember -> Group)
    List<GroupMember> findByUserId(Long userId);

    // Check if user is already a member of a group
    boolean existsByGroupAndUserId(Group group, Long userId);

    // Check if user is a member of the group by username
    boolean existsByGroupAndUsername(Group group, String username);

    // Get a specific member of a group
    GroupMember findByGroupAndUserId(Group group, Long userId);

    // Get all group memberships by username
    List<GroupMember> findByUsername(String username);
}
