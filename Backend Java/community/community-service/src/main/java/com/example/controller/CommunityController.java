package com.example.controller;

import com.example.model.Community;
import com.example.service.CommunityService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.util.JwtUtil;

import java.util.List;


import com.example.model.Community;
import com.example.service.CommunityService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/communities")
public class CommunityController {

    private final CommunityService communityService;

    @Autowired
    public CommunityController(CommunityService communityService) {
        this.communityService = communityService;
    }

    @PostMapping
    public ResponseEntity<Community> createCommunity(@RequestBody Community community) {
        Community createdCommunity = communityService.createCommunity(community);
        return ResponseEntity.ok(createdCommunity);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Community>> getAllCommunities() {
        return ResponseEntity.ok(communityService.getAllCommunities());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Community> getCommunityById(@PathVariable Long id) {
        return ResponseEntity.ok(communityService.getCommunityById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Community> updateCommunity(@PathVariable Long id, @RequestBody Community community) {
        return ResponseEntity.ok(communityService.updateCommunity(id, community));
    }

  @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCommunity(@PathVariable Long id) {
        if (!communityService.existsById(id)) {
            return ResponseEntity.ok("Community already deleted with id: " + id);
        }
        communityService.deleteCommunity(id);
        return ResponseEntity.ok("Community deleted successfully with id: " + id);
    }

    @PostMapping("/{communityId}/join")
    public ResponseEntity<String> joinCommunity(@PathVariable Long communityId, HttpServletRequest request) {
        String username = (String) request.getAttribute("username");
        communityService.joinCommunity(communityId, username);
        return ResponseEntity.ok("Joined community with ID: " + communityId);
    }

    @PostMapping("/{communityId}/leave")
    public ResponseEntity<String> leaveCommunity(@PathVariable Long communityId, HttpServletRequest request) {
        String username = (String) request.getAttribute("username");
        communityService.leaveCommunity(communityId, username);
        return ResponseEntity.ok("Left community with ID: " + communityId);
    }

    @PostMapping("/{communityId}/groups")
    public ResponseEntity<String> addGroupToCommunity(@PathVariable Long communityId, @RequestBody Long groupId) {
        communityService.addGroupToCommunity(communityId, groupId);
        return ResponseEntity.ok("Added group with ID: " + groupId + " to community with ID: " + communityId);
    }

    @DeleteMapping("/{communityId}/groups/{groupId}")
    public ResponseEntity<String> removeGroupFromCommunity(@PathVariable Long communityId, @PathVariable Long groupId) {
        communityService.removeGroupFromCommunity(communityId, groupId);
        return ResponseEntity.ok("Removed group with ID: " + groupId + " from community with ID: " + communityId);
    }
}

//
//@RestController
//@RequestMapping("/api/communities")
//public class CommunityController {
//    private final CommunityService communityService;
//
//    @Autowired
//
//    public CommunityController(CommunityService communityService) {
//        this.communityService = communityService;
//    }
//
//    @PostMapping
//    public ResponseEntity<Community> createCommunity(@RequestBody Community community) {
//        Community createdCommunity = communityService.createCommunity(community);
//        return ResponseEntity.ok(createdCommunity);
//    }
//
//    @GetMapping("/all")
//    public ResponseEntity<List<Community>> getAllCommunities() {
//        List<Community> communities = communityService.getAllCommunities();
//        return ResponseEntity.ok(communities);
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<Community> getCommunityById(@PathVariable Long id) {
//        Community community = communityService.getCommunityById(id);
//        return ResponseEntity.ok(community);
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<Community> updateCommunity(@PathVariable Long id, @RequestBody Community community) {
//        Community updatedCommunity = communityService.updateCommunity(id, community);
//        return ResponseEntity.ok(updatedCommunity);
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<String> deleteCommunity(@PathVariable Long id) {
//        communityService.deleteCommunity(id);
//        return ResponseEntity.ok("Community deleted successfully" + " with id: " + id);
//    }
////
////    //todo: add methods for joining and leaving communities with role assigning
////    @PostMapping("/{communityId}/join")
////    public ResponseEntity<String> joinCommunity(@PathVariable Long communityId) {
////        // Logic to join the community
////        return ResponseEntity.ok("Joined community with ID: " + communityId);
////    }
////
////    //todo: add methods for joining and leaving communities
////    @PostMapping("/{communityId}/leave")
////    public ResponseEntity<String> leaveCommunity(@PathVariable Long communityId) {
////        // Logic to leave the community
////        return ResponseEntity.ok("Left community with ID: " + communityId);
////    }
////
////    //todo: add groups and remove groups
////    @PostMapping("/{communityId}/groups")
////    public ResponseEntity<String> addGroupToCommunity(@PathVariable Long communityId, @RequestBody String groupName) {
////        // Logic to add a group to the community
////        return ResponseEntity.ok("Added group " + groupName + " to community with ID: " + communityId);
////    }
////    @DeleteMapping("/{communityId}/groups/{groupId}")
////    public ResponseEntity<String> removeGroupFromCommunity(@PathVariable Long communityId, @PathVariable Long groupId) {
////        // Logic to remove a group from the community
////        return ResponseEntity.ok("Removed group with ID: " + groupId + " from community with ID: " + communityId);
////    }
//
//
//    @PostMapping("/{communityId}/join")
//    public ResponseEntity<String> joinCommunity(@PathVariable Long communityId, @RequestHeader("Authorization") String authHeader, HttpServletRequest requests) {
//        String username = (String) requests.getAttribute("username");
//        communityService.joinCommunity(communityId, username);
//        return ResponseEntity.ok("Joined community with ID: " + communityId);
//    }
//
//    @PostMapping("/{communityId}/leave")
//    public ResponseEntity<String> leaveCommunity(@PathVariable Long communityId, @RequestHeader("Authorization") String authHeader, HttpServletRequest requests) {
//        String username = (String) requests.getAttribute("username");
//        communityService.leaveCommunity(communityId, username);
//        return ResponseEntity.ok("Left community with ID: " + communityId);
//    }
//
//    @PostMapping("/{communityId}/groups")
//    public ResponseEntity<String> addGroupToCommunity(@PathVariable Long communityId, @RequestBody Long groupId) {
//        communityService.addGroupToCommunity(communityId, groupId);
//        return ResponseEntity.ok("Added group with ID: " + groupId + " to community with ID: " + communityId);
//    }
//
//
//    @DeleteMapping("/{communityId}/groups/{groupId}")
//    public ResponseEntity<String> removeGroupFromCommunity(@PathVariable Long communityId, @PathVariable Long groupId) {
//        communityService.removeGroupFromCommunity(communityId, groupId);
//        return ResponseEntity.ok("Removed group with ID: " + groupId + " from community with ID: " + communityId);
//    }
//}
