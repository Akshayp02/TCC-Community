package com.thecodingcult.chat_service.controller;

import com.thecodingcult.chat_service.model.MessageStatus;
import com.thecodingcult.chat_service.service.MessageStatusService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/messages")
public class MessageController {

    private final MessageStatusService messageStatusService;

    public MessageController(MessageStatusService messageStatusService) {
        this.messageStatusService = messageStatusService;
    }

    @PutMapping("/{id}/status")
    public String updateMessageStatus(@PathVariable Long id, @RequestParam MessageStatus status) {
        messageStatusService.updateMessageStatus(id, status);
        if (status != null) {
            return "✅ if status is valid" + status;
        } else {
            return "❌ if status is not valid";
        }
    }
}


//package com.thecodingcult.chat_service.Controller;
//
//import com.thecodingcult.chat_service.Entity.DTO.GroupValidationResponse;
//import com.thecodingcult.chat_service.Entity.DTO.MessageRequest;
//import com.thecodingcult.chat_service.Service.GroupServiceClient;
//import com.thecodingcult.chat_service.Service.SendMessageService;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.validation.Valid;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//@RestController
//@RequestMapping("/api/v1")
//public class MessageController {
//
//    private static final Logger logger = LoggerFactory.getLogger(MessageController.class);
//
//    private final SendMessageService msgService;
//
//    @Autowired
//    private GroupServiceClient groupServiceClient;
//
//    public MessageController(SendMessageService msgService) {
//        this.msgService = msgService;
//    }
//
//    @PostMapping("/send")
//    public ResponseEntity<?> sendMessage(
//            @Valid @RequestBody MessageRequest messageRequest,
//            HttpServletRequest request
//    ) {
//        String username = (String) request.getAttribute("username");
//
//        if (username == null || username.isEmpty()) {
//            logger.error("Username is missing in the request.");
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
//                    .body(new ErrorResponse("Unauthorized: Username is missing."));
//        }
//
//        logger.info("Processing message send request for user: {}", username);
//
//        // Validate group and user membership
//        GroupValidationResponse validation = groupServiceClient.validateGroupAndUser(messageRequest.getGroupId(), username);
//
//        // Check if the group exists
//        if (!validation.isGroupExists()) {
//            logger.warn("Group with ID {} does not exist.", messageRequest.getGroupId());
//            return ResponseEntity.status(HttpStatus.NOT_FOUND)
//                    .body(new ErrorResponse("Group does not exist."));
//        }
//
//        // Check if the user is a member of the group
//        if (!validation.isMember()) {
//            logger.warn("User {} is not a member of group ID {}.", username, messageRequest.getGroupId());
//            return ResponseEntity.status(HttpStatus.FORBIDDEN)
//                    .body(new ErrorResponse("User is not a member of this group."));
//        }
//
//        // Save or broadcast the message here
//        logger.info("Message sent successfully by user: {}", username);
//        return ResponseEntity.ok(new SuccessResponse("Message sent successfully.", username));
//    }
//
//    // Error response class
//    static class ErrorResponse {
//        private String error;
//
//        public ErrorResponse(String error) {
//            this.error = error;
//        }
//
//        public String getError() {
//            return error;
//        }
//
//        public void setError(String error) {
//            this.error = error;
//        }
//    }
//
//    // Success response class
//    static class SuccessResponse {
//        private String message;
//        private String username;
//
//        public SuccessResponse(String message, String username) {
//            this.message = message;
//            this.username = username;
//        }
//
//        public String getMessage() {
//            return message;
//        }
//
//        public void setMessage(String message) {
//            this.message = message;
//        }
//
//        public String getUsername() {
//            return username;
//        }
//
//        public void setUsername(String username) {
//            this.username = username;
//        }
//    }
//}//    @PostMapping("/send")
////    public ResponseEntity<?> sendMessage(
////            @RequestParam Long groupId,
////            @RequestParam String message,
////            HttpServletRequest request
////    ) {
////        String username = (String) request.getAttribute("username");
////
////        // Validate group and user membership
////        GroupValidationResponse validation = groupServiceClient.validateGroupAndUser(groupId, username);
////
////        // Check if the group exists
////        if (!validation.isGroupExists()) {
////            return ResponseEntity.status(HttpStatus.NOT_FOUND)
////                    .body("❌ Group does not exist.");
////        }
////
////        // Check if the user is a member of the group
////        if (!validation.isMember()) {
////            return ResponseEntity.status(HttpStatus.FORBIDDEN)
////                    .body(username + "❌ User is not a member of this group.");
////        }
////
////        // Save or broadcast the message here
////        return ResponseEntity.ok("✅ Message sent successfully by " + username);
////    }
