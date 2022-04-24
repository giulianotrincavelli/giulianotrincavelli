package com.gt.giulianotrincavelli.service;

import com.gt.giulianotrincavelli.controller.request.ContactRQ;
import com.gt.giulianotrincavelli.controller.response.ContactRS;
import com.gt.giulianotrincavelli.mapper.ContactMapper;
import com.gt.giulianotrincavelli.model.Contact;
import com.gt.giulianotrincavelli.repository.ContactRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ContactServiceTest {

    @Mock
    private ContactRepository contactRepository;
    @Mock
    private ContactMapper contactMapper;

    private ContactService target;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
        target = new ContactService(contactRepository, contactMapper);
    }

    @Test
    void save_OK() {
        ContactRQ contactRQ = mock(ContactRQ.class);
        Contact contact = mock(Contact.class);
        when(contactMapper.toEntity(contactRQ)).thenReturn(contact);
        when(contactRepository.save(contact)).thenReturn(contact);
        ContactRS contactRS = mock(ContactRS.class);
        when(contactMapper.toResponse(contact)).thenReturn(contactRS);

        final ContactRS result = target.save(contactRQ);
        assertEquals(result, contactRS);
    }
}
