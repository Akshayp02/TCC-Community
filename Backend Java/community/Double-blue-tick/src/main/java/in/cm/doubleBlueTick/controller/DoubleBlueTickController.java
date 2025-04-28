package in.cm.doubleBlueTick.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.cm.doubleBlueTick.model.DoubleBlueTickModel;
import in.cm.doubleBlueTick.model.MessageStatusBlue;


@RestController
@RequestMapping("/api/message/status/double-blue")
public class DoubleBlueTickController {
	

	 @PostMapping("/seen")
	    public ResponseEntity<DoubleBlueTickModel> seenMessage(@RequestBody DoubleBlueTickModel message) {
	        // Mark the message as READ (Double Blue Tick)
		 if (message.isInternetAvailable()) {
		        message.setStatus(MessageStatusBlue.READ);
		    } else {
		        message.setStatus(MessageStatusBlue.PENDING);
		    }
	        return ResponseEntity.ok(message);
	    }

}
