package com.gt.giulianotrincavelli.mapper;

import com.gt.giulianotrincavelli.model.MessageGroup;
import com.gt.giulianotrincavelli.model.ReadyMessageGroup;
import com.gt.giulianotrincavelli.controller.response.FullMessageGroupRS;
import com.gt.giulianotrincavelli.controller.response.MessageGroupRS;
import com.gt.giulianotrincavelli.controller.response.ReadyMessageGroupRS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MessageGroupMapper {

    @Autowired
    private GroupMapper groupMapper;

    @Autowired
    private ContactMapper contactMapper;

    @Autowired
    private MessageMapper messageMapper;

    public MessageGroupRS toResponse(MessageGroup messageGroup) {
        return MessageGroupRS.builder()
                .message(messageMapper.toResponse(messageGroup.getMessage()))
                .group(groupMapper.toResponse(messageGroup.getGroup()))
                .contact(contactMapper.toResponse(messageGroup.getContact()))
                .build();
    }

    public FullMessageGroupRS toFullMessageGroupRS(MessageGroup messageGroup) {
        List<String> phones = messageGroup.getReadyMessageGroups().stream().map(m -> m.getContact().getPhone()).toList();

        return FullMessageGroupRS.builder()
                .group(groupMapper.toResponse(messageGroup.getGroup()))
                .creator(contactMapper.toResponse(messageGroup.getContact()))
                .message(messageMapper.toResponse(messageGroup.getMessage()))
                .readers(phones)
                .build();
    }

    public ReadyMessageGroupRS toReadyMessageGroupRS(ReadyMessageGroup readyMessageGroup) {
        return ReadyMessageGroupRS.builder()
                .creator(contactMapper.toResponse(readyMessageGroup.getContact()))
                .group(groupMapper.toResponse(readyMessageGroup.getMessageGroup().getGroup()))
                .message(messageMapper.toResponse(readyMessageGroup.getMessageGroup().getMessage()))
                .readers(List.of(readyMessageGroup.getContact().getPhone()))
                .build();

    }
}
