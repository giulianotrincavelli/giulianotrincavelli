package com.gt.giulianotrincavelli.mapper;

import com.gt.giulianotrincavelli.model.Message;
import com.gt.giulianotrincavelli.controller.request.MessageRQ;
import com.gt.giulianotrincavelli.controller.response.MessageRS;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class MessageMapper {

    public MessageRS toResponse(Message message) {
        return MessageRS.builder().message(message.getMessage()).build();
    }

    public Message toEntity(MessageRQ messageRQ) {
        return Message.builder().message(messageRQ.getMessage()).key(UUID.randomUUID().toString()).build();
    }
}
