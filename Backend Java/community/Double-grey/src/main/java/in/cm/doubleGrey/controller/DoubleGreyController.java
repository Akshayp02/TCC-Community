package in.cm.doubleGrey.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.cm.doubleGrey.model.DoubleGreyModel;
import in.cm.doubleGrey.model.MessageStatusDouble;

@RestController
@RequestMapping("/api/message/status/double-grey")
public class DoubleGreyController {
	
	
	    @PostMapping("/receive")
	    public ResponseEntity<DoubleGreyModel> receiveMessage(@RequestBody DoubleGreyModel message) {
	        // Simulate marking as DELIVERED (Double Gray Tick)
	        if(message.isInternetAvailable()) {
	        	message.setStatus(MessageStatusDouble.DELIVERED);
	        }else {
	        	message.setStatus(MessageStatusDouble.PENDING);
	        }
	        return ResponseEntity.ok(message);
	    }
}
