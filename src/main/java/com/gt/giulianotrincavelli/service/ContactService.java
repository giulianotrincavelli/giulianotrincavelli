package com.gt.giulianotrincavelli.service;

import com.gt.giulianotrincavelli.mapper.ContactMapper;
import com.gt.giulianotrincavelli.model.Contact;
import com.gt.giulianotrincavelli.controller.request.ContactRQ;
import com.gt.giulianotrincavelli.controller.response.ContactRS;
import com.gt.giulianotrincavelli.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ContactService {

    @Autowired
    private ContactRepository repository;

    @Autowired
    private ContactMapper mapper;

    public ContactRS save(ContactRQ contactRQ) {
        Contact contact = repository.save(mapper.toEntity(contactRQ));

        return mapper.toResponse(contact);
    }

    public Optional<Contact> find(String phone) {
        return repository.findByPhone(phone);
    }
}
