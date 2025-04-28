package in.cm.forward_msg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import in.cm.forward_msg.model.Message;
import in.cm.forward_msg.service.MessageService;

@RestController
@RequestMapping("/api/messages")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @PostMapping("/send")
    public Message sendMessage(@RequestBody Message message) {
        return messageService.sendMessage(message);
    }

    @PostMapping("/forward/{messageId}/to/{receiverId}")
    public Message forwardMessage(@PathVariable Long messageId, @PathVariable Long receiverId) {
        return messageService.forwardMessage(messageId, receiverId);
    }
}
