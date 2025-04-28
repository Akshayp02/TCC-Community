////package com.thecodingcult.chat_service.Service;
////
////import com.thecodingcult.chat_service.Entity.DTO.GroupValidationResponse;
////import org.springframework.beans.factory.annotation.Value;
////import org.springframework.http.ResponseEntity;
////import org.springframework.stereotype.Service;
////import org.springframework.web.client.RestTemplate;
////
//////@Service
//////public class GroupServiceClient {
//////
//////    @Value("${group.service.url}") // e.g. http://localhost:8081
//////    private String groupServiceUrl;
//////
//////    private final RestTemplate restTemplate = new RestTemplate();
//////
//////    public boolean isUserInGroup(Long groupId, String username) {
//////        String url = groupServiceUrl + "/api/groups/" + groupId + "/members/" + username;
//////        try {
//////            ResponseEntity<Boolean> response = restTemplate.getForEntity(url, Boolean.class);
//////            return Boolean.TRUE.equals(response.getBody());
//////        } catch (Exception e) {
//////            System.out.println("Error checking group membership: " + e.getMessage());
//////            return false;
//////        }
//////    }
////import com.thecodingcult.chat_service.Entity.DTO.GroupValidationResponse;
////import org.springframework.stereotype.Service;
////import org.springframework.web.client.RestTemplate;
////
////@Service
////public class GroupServiceClient {
////
////    private final RestTemplate restTemplate;
////
////    public GroupServiceClient(RestTemplate restTemplate) {
////        this.restTemplate = restTemplate;
////    }
////
////    public GroupValidationResponse validateGroupAndUser(Long groupId, String username) {
////        String url = "http://localhost:8081/api/groups/validate?groupId=" + groupId + "&username=" + username;
////        return restTemplate.getForObject(url, GroupValidationResponse.class);
////    }
////
////    public boolean doesGroupExist(Long groupId) {
////        String url = groupServiceUrl + "/api/groups/" + groupId + "/exists";
////        try {
////            ResponseEntity<Boolean> response = restTemplate.getForEntity(url, Boolean.class);
////            return Boolean.TRUE.equals(response.getBody());
////        } catch (Exception e) {
////            System.out.println("Error checking group existence: " + e.getMessage());
////            return false;
////        }
////    }
////
////    public GroupValidationResponse validateGroupAndUser(Long groupId, String username) {
////        String url = groupServiceUrl + "/api/groups/" + groupId + "/members/" + username;
////        try {
////            ResponseEntity<GroupValidationResponse> response = restTemplate.getForEntity(url, GroupValidationResponse.class);
////            return response.getBody();
////        } catch (Exception e) {
////            System.out.println("Error validating group/user: " + e.getMessage());
////            return new GroupValidationResponse(false, false);
////        }
////    }
////}
//
//package com.thecodingcult.chat_service.Service;
//
//import com.thecodingcult.chat_service.Entity.DTO.GroupValidationResponse;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.cache.annotation.Cacheable;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Service;
//import org.springframework.web.client.RestTemplate;
//
//@Service
//public class GroupServiceClient {
//
//    @Value("${group.service.url}") // Inject the group service URL from application properties
//    private String groupServiceUrl;
//
//    private final RestTemplate restTemplate;
//
//    public GroupServiceClient(RestTemplate restTemplate) {
//        this.restTemplate = restTemplate;
//    }
//
//    @Cacheable(value = "groupValidation", key = "#groupId + '-' + #username")
//    public GroupValidationResponse validateGroupAndUser(Long groupId, String username) {
//        String url = groupServiceUrl + "/api/groups/validate?groupId=" + groupId + "&username=" + username;
//        try {
//            return restTemplate.getForObject(url, GroupValidationResponse.class);
//        } catch (Exception e) {
//            System.out.println("Error validating group/user: " + e.getMessage());
//            return new GroupValidationResponse(false, false);
//        }
//    }
//
//    @Cacheable(value = "groupExistence", key = "#groupId")
//    public boolean doesGroupExist(Long groupId) {
//        String url = groupServiceUrl + "/api/groups/" + groupId + "/exists";
//        try {
//            ResponseEntity<Boolean> response = restTemplate.getForEntity(url, Boolean.class);
//            return Boolean.TRUE.equals(response.getBody());
//        } catch (Exception e) {
//            System.out.println("Error checking group existence: " + e.getMessage());
//            return false;
//        }
//    }
//}