package in.cm.hide_message.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import in.cm.hide_message.entity.HideMessageEntity;
import in.cm.hide_message.service.HideMessageService;

@RestController
@RequestMapping("/api/messages")
public class HideMessageController {

	@Autowired
    private HideMessageService messageService;

    @PostMapping("/send")
    public HideMessageEntity sendMessage(@RequestBody HideMessageEntity message) {
        return messageService.sendMessage(message);
    }

    @PutMapping("/hide/{messageId}")
    public ResponseEntity<String> hiddenMessageForUser(@PathVariable Long messageId, @RequestParam String username) {
        messageService.hideMessageForUser(messageId, username);
        return ResponseEntity.ok("Message hidden for user: " + username);
    }
}
