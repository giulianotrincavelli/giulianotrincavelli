package com.gt.giulianotrincavelli.mapper;

import com.gt.giulianotrincavelli.model.Contact;
import com.gt.giulianotrincavelli.controller.request.ContactRQ;
import com.gt.giulianotrincavelli.controller.response.ContactRS;
import org.springframework.stereotype.Component;

@Component
public class ContactMapper {

    public ContactRS toResponse(Contact contact) {
        return ContactRS.builder().name(contact.getName()).phone(contact.getPhone()).build();
    }

    public Contact toEntity(ContactRQ contactRQ) {
        return Contact.builder().name(contactRQ.getName()).phone(contactRQ.getPhone()).build();
    }
}
