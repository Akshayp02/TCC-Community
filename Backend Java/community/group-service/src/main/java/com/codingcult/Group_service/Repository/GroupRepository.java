package com.codingcult.Group_service.repository;

import com.codingcult.Group_service.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {

    // Get all groups created by a specific username (group creator)
    List<Group> findByUsername(String username);

    // Optional: Search for groups by partial name (case insensitive)
    List<Group> findByGroupNameContainingIgnoreCase(String groupName);
}
