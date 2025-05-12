package com.example.service;

import com.example.model.Community;
import com.example.repository.CommunityRepository;
import org.springframework.stereotype.Service;

import java.util.List;


import com.example.model.Community;
import com.example.repository.CommunityRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommunityService {

    private final CommunityRepository communityRepository;

    public CommunityService(CommunityRepository communityRepository) {
        this.communityRepository = communityRepository;
    }

    public Community createCommunity(Community community) {
        return communityRepository.save(community);
    }

    public List<Community> getAllCommunities() {
        return communityRepository.findAll();
    }

    public Community getCommunityById(Long id) {
        return communityRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Community not found"));
    }

    public Community updateCommunity(Long id, Community updatedData) {
        Community existing = communityRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Community not found"));

        existing.setName(updatedData.getName());
        existing.setDescription(updatedData.getDescription());
        existing.setType(updatedData.getType());
        existing.setTags(updatedData.getTags());

        return communityRepository.save(existing);
    }

    public void deleteCommunity(Long id) {
        if (!communityRepository.existsById(id)) {
            throw new RuntimeException("Community not found");
        }
        communityRepository.deleteById(id);
    }

    public void joinCommunity(Long communityId, String username) {
        Community community = communityRepository.findById(communityId)
                .orElseThrow(() -> new RuntimeException("Community not found"));

        if (!community.getMembers().contains(username)) {
            community.getMembers().add(username);
            communityRepository.save(community);
        }
    }

    public void leaveCommunity(Long communityId, String username) {
        Community community = communityRepository.findById(communityId)
                .orElseThrow(() -> new RuntimeException("Community not found"));

        if (community.getMembers().contains(username)) {
            community.getMembers().remove(username);
            communityRepository.save(community);
        }
    }

    public void addGroupToCommunity(Long communityId, Long groupId) {
        Community community = communityRepository.findById(communityId)
                .orElseThrow(() -> new RuntimeException("Community not found"));

        if (!community.getGroupIds().contains(groupId)) {
            community.getGroupIds().add(groupId);
            communityRepository.save(community);
        }
    }

    public void removeGroupFromCommunity(Long communityId, Long groupId) {
        Community community = communityRepository.findById(communityId)
                .orElseThrow(() -> new RuntimeException("Community not found"));

        if (community.getGroupIds().contains(groupId)) {
            community.getGroupIds().remove(groupId);
            communityRepository.save(community);
        }
    }

    public boolean existsById(Long id) {
        return communityRepository.existsById(id);
    }
}

//
//@Service
//public class CommunityService {
//
//    private final CommunityRepository communityRepository;
//
//    public CommunityService(CommunityRepository communityRepository) {
//        this.communityRepository = communityRepository;
//    }
//
//    public Community createCommunity(Community community) {
//        return communityRepository.save(community);
//    }
//
//    public List<Community> getAllCommunities() {
//        return communityRepository.findAll();
//    }
//
//    public Community getCommunityById(Long id) {
//        return communityRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("Community not found"));
//    }
//
//    public Community updateCommunity(Long id, Community community) {
//        Community existingCommunity = communityRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("Community not found"));
//
//        existingCommunity.setName(community.getName());
//        existingCommunity.setDescription(community.getDescription());
//        existingCommunity.setType(community.getType());
//        existingCommunity.setTags(community.getTags());
//
//        return communityRepository.save(existingCommunity);
//    }
//
//    public void deleteCommunity(Long id) {
//        if (!communityRepository.existsById(id)) {
//            throw new RuntimeException("Community not found");
//        }
//        communityRepository.deleteById(id);
//    }
//
//    public void joinCommunity(Long communityId, String username) {
//        Community community = communityRepository.findById(communityId)
//                .orElseThrow(() -> new RuntimeException("Community not found"));
//
//        if (!community.getMembers().contains(username)) {
//            community.getMembers().add(username);
//            communityRepository.save(community);
//        }
//    }
//
//    public void leaveCommunity(Long communityId, String username) {
//        Community community = communityRepository.findById(communityId)
//                .orElseThrow(() -> new RuntimeException("Community not found"));
//
//        if (community.getMembers().contains(username)) {
//            community.getMembers().remove(username);
//            communityRepository.save(community);
//        }
//    }
//
//    public void addGroupToCommunity(Long communityId, Long groupId) {
//        Community community = communityRepository.findById(communityId)
//                .orElseThrow(() -> new RuntimeException("Community not found"));
//
//        if (!community.getGroupIds().contains(groupId)) {
//            community.getGroupIds().add(groupId);
//            communityRepository.save(community);
//        }
//    }
//
//    public void removeGroupFromCommunity(Long communityId, Long groupId) {
//        Community community = communityRepository.findById(communityId)
//                .orElseThrow(() -> new RuntimeException("Community not found"));
//
//        if (community.getGroupIds().contains(groupId)) {
//            community.getGroupIds().remove(groupId);
//            communityRepository.save(community);
//        }
//    }
//}
