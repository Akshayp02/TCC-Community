package in.cm.pin_unpin.service;

import java.util.List;

import in.cm.pin_unpin.model.Message;

public interface MessageService {
    Message saveMessage(Message message);
    Message pinMessage(Long id);
    Message unpinMessage(Long id);
    List<Message> getPinnedMessages();
}