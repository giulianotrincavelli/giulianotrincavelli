package com.gt.giulianotrincavelli.mapper;

import com.gt.giulianotrincavelli.controller.response.ContactRS;
import com.gt.giulianotrincavelli.model.Contact;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.stereotype.Component;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@Component
public class ContactMapperTest {

    private final ContactMapper target = new ContactMapper();

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void toResponse_OK() {
        Contact contact = mock(Contact.class);

        when(contact.getName()).thenReturn("Giuliano Trincavelli");
        when(contact.getPhone()).thenReturn("1122508506");

        final ContactRS result = target.toResponse(contact);

        assertEquals(result.getName(), "Giuliano Trincavelli");
        assertEquals(result.getPhone(), "1122508506");
    }
}
