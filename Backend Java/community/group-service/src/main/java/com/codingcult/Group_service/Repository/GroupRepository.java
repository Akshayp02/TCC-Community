package com.codingcult.Group_service.Repository;


import com.codingcult.Group_service.Entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {

    // Find groups by user ID (created by user)
    List<Group> findByCreatedByUserId(Long userId);

    // Optional: Search group by name (if needed)
    List<Group> findByGroupNameContainingIgnoreCase(String groupName);


}
