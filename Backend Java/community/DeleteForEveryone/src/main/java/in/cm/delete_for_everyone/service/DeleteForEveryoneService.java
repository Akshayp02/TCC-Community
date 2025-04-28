package in.cm.delete_for_everyone.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.cm.delete_for_everyone.model.DeleteForEveryoneModel;
import in.cm.delete_for_everyone.repository.DeleteForEveryoneRepository;

import java.util.Optional;

@Service
public class DeleteForEveryoneService {

    @Autowired
    private DeleteForEveryoneRepository repository;

    public String deleteMessageForEveryone(Long messageId) {
        Optional<DeleteForEveryoneModel> message = repository.findById(messageId);

        if (message.isPresent()) {
            repository.deleteById(messageId);
            return "Message deleted for everyone.";
        } else {
            return "Message not found.";
        }
    }
}