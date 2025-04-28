package in.cm.hide_message.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.cm.hide_message.entity.HiddenMessageEntity;
import in.cm.hide_message.entity.HideMessageEntity;
import in.cm.hide_message.repository.HiddenMessageRepository;
import in.cm.hide_message.repository.HideMessageRepo;

@Service
public class HideMessageService {
	
	@Autowired
    private HideMessageRepo messageRepository;
	
	@Autowired
	private HiddenMessageRepository hiddenMessageRepository;

    public HideMessageEntity sendMessage(HideMessageEntity message) {
        return messageRepository.save(message);
    }

    public void hideMessage(Long messageId, String username) {
        Optional<HideMessageEntity> optional = messageRepository.findById(messageId);
        if (optional.isPresent()) {
            HideMessageEntity message = optional.get();
            if (username.equals(message.getSender())) {
                message.setHiddenBySender(true);
            } else if (username.equals(message.getReceiver())) {
                message.setHiddenByReceiver(true);
            }
            messageRepository.save(message);
        }
    }
    
    public void hideMessageForUser(Long messageId, String username) {
        if (!hiddenMessageRepository.existsByMessageIdAndUsername(messageId, username)) {
            HiddenMessageEntity hidden = new HiddenMessageEntity();
            hidden.setMessageId(messageId);
            hidden.setUsername(username);
            hiddenMessageRepository.save(hidden);
        }
    }
    
    //To visible the message
    public List<HiddenMessageEntity> getVisibleMessagesForUser(String username) {
        List<HiddenMessageEntity> allMessages = hiddenMessageRepository.findAll();
        List<HiddenMessageEntity> hiddenMessages = hiddenMessageRepository.findByUsername(username);

        Set<Long> hiddenIds = hiddenMessages.stream()
                .map(HiddenMessageEntity::getMessageId)
                .collect(Collectors.toSet());

        return allMessages.stream()
                .filter(msg -> !hiddenIds.contains(msg.getId()))
                .collect(Collectors.toList());
    }

}
