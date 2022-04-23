package com.gt.giulianotrincavelli.service;

import com.gt.giulianotrincavelli.mapper.MessageMapper;
import com.gt.giulianotrincavelli.model.Message;
import com.gt.giulianotrincavelli.model.request.MessageRQ;
import com.gt.giulianotrincavelli.model.response.MessageRS;
import com.gt.giulianotrincavelli.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MessageService {

    @Autowired
    private MessageRepository repository;

    @Autowired
    private MessageMapper mapper;

    public Optional<Message> find(String messageKey) {
        return repository.findByKey(messageKey);
    }

    public MessageRS save(MessageRQ messageRQ) {
        Message message = repository.save(mapper.toEntity(messageRQ));

        return mapper.toResponse(message);
    }

    public Message save(Message message) {
        return repository.save(message);
    }
}
