package com.gt.giulianotrincavelli.controller;

import com.gt.giulianotrincavelli.controller.request.ContactRQ;
import com.gt.giulianotrincavelli.controller.response.ContactRS;
import com.gt.giulianotrincavelli.service.ContactService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/api/contacts")
@Slf4j
public class ContactController {
    @Autowired
    private ContactService service;

    @PostMapping("/save")
    ResponseEntity<ContactRS> save(@RequestBody ContactRQ contact) {
        ContactRS contactRS = service.save(contact);

        return status(HttpStatus.OK).body(contactRS);
    }
}
