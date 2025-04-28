package in.cm.pin_unpin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import in.cm.pin_unpin.model.Message;
import in.cm.pin_unpin.service.MessageService;

import java.util.List;

@RestController
@RequestMapping("/api/messages")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @PostMapping("/send")
    public Message sendMessage(@RequestBody Message message) {
        return messageService.saveMessage(message);
    }

    @PutMapping("/pin/{id}")
    public Message pinMessage(@PathVariable Long id) {
        return messageService.pinMessage(id);
    }

    @PutMapping("/unpin/{id}")
    public Message unpinMessage(@PathVariable Long id) {
        return messageService.unpinMessage(id);
    }

    @GetMapping("/pinned")
    public List<Message> getPinnedMessages() {
        return messageService.getPinnedMessages();
    }
}