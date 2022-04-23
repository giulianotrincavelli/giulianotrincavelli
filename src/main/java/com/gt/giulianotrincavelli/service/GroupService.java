package com.gt.giulianotrincavelli.service;

import com.gt.giulianotrincavelli.mapper.GroupMapper;
import com.gt.giulianotrincavelli.model.Contact;
import com.gt.giulianotrincavelli.model.ContactGroup;
import com.gt.giulianotrincavelli.model.Group;
import com.gt.giulianotrincavelli.model.request.ContactGroupRQ;
import com.gt.giulianotrincavelli.model.request.GroupRQ;
import com.gt.giulianotrincavelli.model.response.GroupRS;
import com.gt.giulianotrincavelli.repository.ContactGroupRepository;
import com.gt.giulianotrincavelli.repository.GroupRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class GroupService {

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private ContactGroupRepository contactGroupRepository;
    @Autowired
    private ContactService contactService;

    @Autowired
    private GroupMapper mapper;

    public GroupRS save(GroupRQ groupRQ) {
        Group group = groupRepository.save(mapper.toEntity(groupRQ));

        return mapper.toResponse(group);
    }

    public GroupRS add(ContactGroupRQ contactGroupRQ) {
        String groupName = contactGroupRQ.getGroup();
        String phone = contactGroupRQ.getPhone();

        Optional<Contact> contactOptional = contactService.find(phone);
        Optional<Group> groupOptional = groupRepository.findByName(groupName);

        if (contactOptional.isEmpty() || groupOptional.isEmpty()) {
            log.error("Contact {} or group {} not found", phone, groupName);

            throw new InternalError();
        }

        Contact contact = contactOptional.get();
        Group group = groupOptional.get();

        List<Contact> groupContacts = group.getContacts().stream().map(ContactGroup::getContact).toList();

        if (groupContacts.contains(contact)) {
            log.error("Contact {} is has already in group {}", phone, groupName);

            throw new InternalError();
        } else {
            ContactGroup contactGroup = ContactGroup.builder()
                    .contact(contact)
                    .group(group)
                    .build();

            contactGroupRepository.save(contactGroup);

            return mapper.toResponse(group);
        }
    }

    public Optional<Group> find(String name) {
        return groupRepository.findByName(name);
    }
}
