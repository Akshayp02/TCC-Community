package in.cm.disappearing_messages.controller;

import in.cm.disappearing_messages.model.Message;
import in.cm.disappearing_messages.model.*;
import in.cm.disappearing_messages.repository.MessageRepository;
import in.cm.disappearing_messages.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/messages")
public class MessageController {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private MessageService messageService;

    // POST method to create a new message
    @PostMapping(value = "/create", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> createMessage(
            @RequestParam(value = "file", required = false) MultipartFile file,
            @RequestParam("mediaType") String mediaType,
            @RequestParam("timer") String timer,
            @RequestParam(value = "content", required = false) String content) {

        Message message = new Message();
        message.setType(mediaType.toUpperCase());
        message.setTimer(Message.ExpirationTimer.valueOf(timer.toUpperCase()));
        message.setExpiration();

        if (mediaType.equalsIgnoreCase("text")) {
            message.setContent(content);
        } else if (file != null) {
            try {
                String uploadDir = "D:/Coding cult/Contacts Module/DisappearingMessages/uploads";
                File dir = new File(uploadDir);
                if (!dir.exists()) dir.mkdirs();

                String filePath = uploadDir + "/" + file.getOriginalFilename();
                file.transferTo(new File(filePath));
                message.setContent(file.getOriginalFilename());
                message.setFilePath(filePath);
            } catch (IOException e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Upload failed.");
            }
        }

        Message saved = messageRepository.save(message);
        return ResponseEntity.ok(saved); // Return full object including ID
    }

    // GET method to retrieve the expiry of a message
    @GetMapping("/{id}/expiry")
    public ResponseEntity<?> getMessageExpiry(@PathVariable Long id) {
        Message message = messageService.getMessageById(id); 
        if (message == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Message not found");
        }
        return ResponseEntity.ok(message.getExpiresAt()); // Return the expiry date or status
    }

    // PUT method to update the expiry of a message
    @PutMapping("/{id}/expiry")
    public ResponseEntity<?> updateMessageExpiry(@PathVariable Long id, @RequestBody Message expiryDate) {
        Message message = messageService.getMessageById(id);
        if (message == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Message not found");
        }

        message.setExpiryDate(expiryDate.getExpiryDate()); // Update the expiry date
        messageService.saveMessage(message); // Save changes to the database
        return ResponseEntity.ok("Message expiry updated");
    }

    // GET method to retrieve all messages
    @GetMapping("/all")
    public List<Message> getAllMessages() {
        return messageRepository.findAll(); // Logic to return all messages
    }
}
