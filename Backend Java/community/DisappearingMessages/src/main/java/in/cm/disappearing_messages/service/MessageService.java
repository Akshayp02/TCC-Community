package in.cm.disappearing_messages.service;

import in.cm.disappearing_messages.model.Message;
import in.cm.disappearing_messages.model.Message.ExpirationTimer;
import in.cm.disappearing_messages.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class MessageService {

    private final MessageRepository messageRepository;

    @Value("${file.upload-dir}")
    private String uploadDir;

    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    // Save a text message
    public Message save(Message message) {
        // You can set the type for text message here if it's not already set
        if (message.getType() == null) {
            message.setType("TEXT"); // Set type as TEXT for text messages
        }
        return messageRepository.save(message);
    }

    // Save a photo/video file
    public Message saveMediaMessage(MultipartFile file, ExpirationTimer timer) throws IOException {
        String originalName = file.getOriginalFilename();
        String extension = (originalName != null) ? originalName.substring(originalName.lastIndexOf('.') + 1) : "";
        String uuid = UUID.randomUUID().toString();
        String fileName = uuid + "_" + originalName;
        Path filePath = Paths.get(uploadDir + File.separator + fileName);

        // Create directories if they don’t exist
        Files.createDirectories(filePath.getParent());

        // Copy file to path
        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        // Detect media type based on file extension
        String mediaType = detectMediaType(extension);

        Message msg = new Message();
        msg.setContent(fileName);
        msg.setType(mediaType);
        msg.setFilePath(filePath.toString());
        msg.setTimer(timer);
        msg.setExpiration();
        return messageRepository.save(msg);
    }

    // Determine file/media type
    private String detectMediaType(String ext) {
        ext = ext.toLowerCase();
        switch (ext) {
            case "jpg":
            case "jpeg":
            case "png":
            case "gif":
            case "webp":
                return "PHOTO";
            case "mp4":
            case "avi":
            case "mkv":
            case "mov":
                return "VIDEO";
            default:
                return "UNKNOWN";
        }
    }

    // Get one message by ID
    public Optional<Message> getMessage(Long id) {
        return messageRepository.findById(id);
    }

    // Get all messages
    public List<Message> getAllMessages() {
        return messageRepository.findAll();
    }

    // Delete expired messages and files
    public void deleteExpiredMessages() {
    	List<Message> all = messageRepository.findAll();

    	for (Message message : all) {
    	    if (LocalDateTime.now().isAfter(message.getExpiresAt())) {
    	        // Delete file
    	        File file = new File(message.getFilePath());
    	        if (file.exists()) {
    	            file.delete();
    	        }
    	        // Delete DB entry
    	        messageRepository.deleteById(message.getId());
    	    }
    	}
    }

    public void deleteMessageById(Long id) {
        Optional<Message> optionalMessage = messageRepository.findById(id);
        if (optionalMessage.isPresent()) {
            Message message = optionalMessage.get();

            // Step 1: Delete the file from disk
            File file = new File(message.getFilePath());
            if (file.exists()) {
                if (file.delete()) {
                    System.out.println("Deleted file from disk: " + file.getAbsolutePath());
                } else {
                    System.out.println("Failed to delete file from disk: " + file.getAbsolutePath());
                }
            }

            // Step 2: Delete from database
            messageRepository.deleteById(id);
            System.out.println("Deleted from database with ID: " + id);
        } else {
            throw new RuntimeException("Message not found with ID: " + id);
        }
    }
    
    public Message getMessageById(Long id) {
        return messageRepository.findById(id).orElse(null); // Fetch message by ID
    }

    public void saveMessage(Message message) {
        messageRepository.save(message); // Save updated message to the database
    }
}
