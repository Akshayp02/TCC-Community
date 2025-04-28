package in.cm.delete_for_me.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import in.cm.delete_for_me.service.DeleteForMeService;

@RestController
@RequestMapping("/api/messages")
public class DeleteForMeController {
	
	    @Autowired
	    private DeleteForMeService messageService;
	    //private DeleteForEveryoneService deleteForEveryoneService;

	    @DeleteMapping("/deleteForMe/{messageId}")
	    public String deleteMessageForMe(@PathVariable Long messageId,@RequestParam String userId) {
	        return messageService.deleteMessageForMe(messageId, userId);
	    }

}
