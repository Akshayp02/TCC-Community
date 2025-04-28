package in.cm.forward_msg.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.cm.forward_msg.model.Message;
import in.cm.forward_msg.repository.MessageRepository;

import java.util.Optional;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    public Message sendMessage(Message message) {
        message.setForwarded(false);
        return messageRepository.save(message);
    }

    public Message forwardMessage(Long messageId, Long newReceiverId) {
        Optional<Message> original = messageRepository.findById(messageId);
        if (original.isPresent()) {
            Message forward = new Message();
            forward.setSenderId(original.get().getSenderId());
            forward.setReceiverId(newReceiverId);
            forward.setContent(original.get().getContent());
            forward.setForwarded(true);
            return messageRepository.save(forward);
        } else {
            throw new RuntimeException("Original message not found");
        }
    }
}