package in.cm.singleGrey.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.cm.singleGrey.model.MessageStatus;
import in.cm.singleGrey.model.SingleGreyModel;

@RestController
@RequestMapping("/api/message/status/single-grey")
public class SingleTickController {
	@PostMapping("/send")
    public ResponseEntity<SingleGreyModel> sendMessage(@RequestBody SingleGreyModel message) {
        // Log or process the message here
		if (message.isInternetAvailable()) {
	        message.setStatus(MessageStatus.SENT);
	    } else {
	        message.setStatus(MessageStatus.PENDING);
	    }
        System.out.println("Received message: " + message.getContent() + ", Status: " + message.getStatus());

        return ResponseEntity.ok(message);
    }

}
