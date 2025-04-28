package in.cm.edit_message.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.cm.edit_message.model.EditMessage;
import in.cm.edit_message.repository.EditMessageRepository;

import java.util.Optional;

@Service
public class EditMessageService {

    @Autowired
    private EditMessageRepository messageRepository;

    public EditMessage saveMessage(EditMessage message) {
        return messageRepository.save(message);
    }

    public Optional<EditMessage> getMessageById(Long id) {
        return messageRepository.findById(id);
    }

    public EditMessage updateMessage(Long id, String newContent) {
    	EditMessage message = messageRepository.findById(id).orElseThrow(() -> new RuntimeException("Message not found"));
        message.setContent(newContent);
        message.setEdited(true);
        return messageRepository.save(message);
    }
}