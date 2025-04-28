package in.cm.delete_for_me.service;

import in.cm.delete_for_me.model.DeleteForMeModel;
import in.cm.delete_for_me.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DeleteForMeService {

    @Autowired
    private DeleteForMeRepository messageRepository;
    

    public String deleteMessageForMe(Long messageId, String userId) {
        Optional<DeleteForMeModel> optionalMessage = messageRepository.findById(messageId);

        if (optionalMessage.isPresent()) {
        	DeleteForMeModel message = optionalMessage.get();

            if (message.getSenderId().equals(userId)) {
                message.setDeletedForSender(true);
            } else if (message.getReceiverId().equals(userId)) {
                message.setDeletedForReceiver(true);
            } else {
                return "User not authorized to delete this message";
            }

            messageRepository.save(message);
            return "Message deleted for user: " + userId;
        } else {
            return "Message not found";
        }
    }
}