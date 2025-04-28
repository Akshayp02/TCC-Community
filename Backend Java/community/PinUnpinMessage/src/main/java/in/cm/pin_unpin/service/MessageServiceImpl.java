package in.cm.pin_unpin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.cm.pin_unpin.model.Message;
import in.cm.pin_unpin.repository.MessageRepository;

import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageRepository messageRepo;

    @Override
    public Message saveMessage(Message message) {
        return messageRepo.save(message);
    }

    @Override
    public Message pinMessage(Long id) {
        Message msg = messageRepo.findById(id).orElseThrow();
        msg.setPinned(true);
        return messageRepo.save(msg);
    }

    @Override
    public Message unpinMessage(Long id) {
        Message msg = messageRepo.findById(id).orElseThrow();
        msg.setPinned(false);
        return messageRepo.save(msg);
    }

    @Override
    public List<Message> getPinnedMessages() {
        return messageRepo.findByPinnedTrue();
    }
}