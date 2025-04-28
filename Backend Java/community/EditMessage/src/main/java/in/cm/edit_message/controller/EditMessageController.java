package in.cm.edit_message.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import in.cm.edit_message.model.EditMessage;
import in.cm.edit_message.service.EditMessageService;

@RestController
@RequestMapping("/api/messages")
public class EditMessageController {

    @Autowired
    private EditMessageService messageService;

    @PostMapping("/write")
    public EditMessage sendMessage(@RequestBody EditMessage message) {
        return messageService.saveMessage(message);
    }

    @PutMapping("/{id}/edit")
    public EditMessage editMessage(@PathVariable Long id, @RequestParam String newContent) {
        return messageService.updateMessage(id, newContent);
    }

    @GetMapping("/{id}")
    public EditMessage getMessage(@PathVariable Long id) {
        return messageService.getMessageById(id).orElse(null);
    }
}
