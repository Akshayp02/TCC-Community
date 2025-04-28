package in.cm.delete_for_everyone.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.cm.delete_for_everyone.model.DeleteForEveryoneModel;
import in.cm.delete_for_everyone.service.DeleteForEveryoneService;

@RestController
@RequestMapping("/api/messages")
public class DeleteForEveryoneController {

    @Autowired
    private DeleteForEveryoneService service;
    
    @DeleteMapping("/deleteForEveryone/{messageId}")
    public ResponseEntity<String> deleteMessageForEveryone(@PathVariable("messageId")Long messageId) {
        return ResponseEntity.ok("Message with ID " + messageId + " deleted for everyone! ");
    }
}
