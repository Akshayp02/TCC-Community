package com.thecodingcult.chat_service.Service;

import com.thecodingcult.chat_service.Entity.DTO.MessageDTO;
import com.thecodingcult.chat_service.Entity.Message;
import org.springframework.stereotype.Service;

@Service
public class SendMessageService {

    public Message sendMessage(MessageDTO dto) {
        // Return a dummy message for testing
        Message dummyMessage = new Message();
        dummyMessage.setId(1L);
        dummyMessage.setContent("This is a test message");
        dummyMessage.setSenderId(123L);
        dummyMessage.setGroupId(456L);
        return dummyMessage;
    }
}