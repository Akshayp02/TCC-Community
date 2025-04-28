package com.codingcult.Group_service.Repository;


import com.codingcult.Group_service.Entity.Group;
import com.codingcult.Group_service.Entity.GroupMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupMemberRepository extends JpaRepository<GroupMember, Long> {

    // Get all members of a group
    List<GroupMember> findByGroup(Group group);

    // Get all groups a user is a member of
    List<GroupMember> findByUserId(Long userId);

    // Check if a user is already a member of a group
    boolean existsByGroupAndUserId(Group group, Long userId);

    // Optional: Find specific membership
    GroupMember findByGroupAndUserId(Group group, Long userId);

    boolean existsByGroupAndUsername(Group group, String username);


}
