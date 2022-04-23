package com.gt.giulianotrincavelli.service;

import com.gt.giulianotrincavelli.kafka.producer.MessageProducer;
import com.gt.giulianotrincavelli.mapper.MessageGroupMapper;
import com.gt.giulianotrincavelli.model.*;
import com.gt.giulianotrincavelli.controller.request.MessageGroupRQ;
import com.gt.giulianotrincavelli.controller.request.ReadyMessageGroupRQ;
import com.gt.giulianotrincavelli.controller.response.FullMessageGroupRS;
import com.gt.giulianotrincavelli.controller.response.MessageGroupRS;
import com.gt.giulianotrincavelli.controller.response.ReadyMessageGroupRS;
import com.gt.giulianotrincavelli.repository.MessageGroupRepository;
import com.gt.giulianotrincavelli.repository.ReadyMessageGroupRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class MessageGroupService {

    @Autowired
    private MessageProducer messageProducer;

    @Autowired
    private MessageGroupRepository messageGroupRepository;

    @Autowired
    private ReadyMessageGroupRepository readyMessageGroupRepository;

    @Autowired
    private ContactService contactService;
    @Autowired
    private GroupService groupService;
    @Autowired
    private MessageService messageService;

    @Autowired
    private MessageGroupMapper mapper;

    public FullMessageGroupRS getByMessageKey(String messageKey) {
        Optional<Message> messageOptional = messageService.find(messageKey);

        if (messageOptional.isEmpty()) {
            log.error("Message {} not found", messageKey);

            throw new InternalError();
        }

        Message message = messageOptional.get();

        Optional<MessageGroup> messageGroupOptional = messageGroupRepository.findByMessage(message);

        if (messageGroupOptional.isEmpty()) {
            log.error("Message {} not found", messageKey);

            throw new InternalError();
        }

        MessageGroup messageGroup = messageGroupOptional.get();

        return mapper.toFullMessageGroupRS(messageGroup);
    }

    public ReadyMessageGroupRS markAsReady(ReadyMessageGroupRQ readyMessageGroupRQ) {
        String contactRQ = readyMessageGroupRQ.getContact();
        String groupRQ = readyMessageGroupRQ.getGroup();
        String messageKey = readyMessageGroupRQ.getMessageKey();

        Optional<Contact> contactOptional = contactService.find(contactRQ);
        Optional<Group> groupOptional = groupService.find(groupRQ);
        Optional<Message> messageOptional = messageService.find(messageKey);

        if (contactOptional.isEmpty() || groupOptional.isEmpty() || messageOptional.isEmpty()) {
            log.error("Contact {} or group {} or message {} not found", contactRQ, groupRQ, messageKey);

            throw new InternalError();
        }

        Contact contact = contactOptional.get();
        Group group = groupOptional.get();
        Message message = messageOptional.get();

        Optional<MessageGroup> messageGroupOptional = messageGroupRepository.findByContactAndGroupAndMessage(contact, group, message);

        if (messageGroupOptional.isEmpty()) {
            log.error("Message group not found");

            throw new InternalError();
        }

        MessageGroup messageGroup = messageGroupOptional.get();

        Optional<ReadyMessageGroup> readyMessageGroupOptional = readyMessageGroupRepository.findByContactAndMessageGroup(contact, messageGroup);

        if (readyMessageGroupOptional.isPresent()) {
            log.error("Message {} of group {} has already for contact {}", messageKey, group.getName(), contact.getPhone());

            throw new InternalError();
        }

        ReadyMessageGroup readyMessageGroup = ReadyMessageGroup.builder()
                .messageGroup(messageGroup)
                .contact(contact)
                .build();

        ReadyMessageGroup savedReadyMessageGroup = readyMessageGroupRepository.save(readyMessageGroup);

        return mapper.toReadyMessageGroupRS(savedReadyMessageGroup);
    }

    public MessageGroupRS save(MessageGroupRQ messageGroupRQ) {
        String contactRQ = messageGroupRQ.getContact();
        String groupRQ = messageGroupRQ.getGroup();

        Optional<Contact> contactOptional = contactService.find(contactRQ);
        Optional<Group> groupOptional = groupService.find(groupRQ);

        if (contactOptional.isEmpty() || groupOptional.isEmpty()) {
            log.error("Contact {} or group {} not found", contactRQ, groupRQ);

            throw new InternalError();
        }

        Contact contact = contactOptional.get();
        Group group = groupOptional.get();

        List<Contact> groupContacts = group.getContacts().stream().map(ContactGroup::getContact).toList();

        if (!groupContacts.contains(contact)) {
            log.error("Contact {} not in group {}", contactRQ, groupRQ);

            throw new InternalError();
        }

        Message message = Message.builder().message(messageGroupRQ.getMessage()).key(UUID.randomUUID().toString()).build();

        Message savedMessage = messageService.save(message);

        MessageGroup messageGroup = MessageGroup.builder()
                .message(savedMessage)
                .group(group)
                .contact(contact)
                .build();

        MessageGroup savedMessageGroup = messageGroupRepository.save(messageGroup);

        // Se manda notificacion cuando hay un mensaje nuevo creado en un grupo
        Event event = Event.builder()
                .group(group.getName())
                .message(message.getMessage())
                .phone(contact.getPhone()).build();

        try {
            //messageProducer.sendMessage(event, "test_topic");
        } catch (Exception e) {
            log.error("Cannot send notification");
        }

        return mapper.toResponse(savedMessageGroup);
    }
}
