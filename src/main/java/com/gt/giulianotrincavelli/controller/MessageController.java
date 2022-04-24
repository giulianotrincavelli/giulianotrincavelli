package com.gt.giulianotrincavelli.controller;

import com.gt.giulianotrincavelli.controller.request.MessageRQ;
import com.gt.giulianotrincavelli.controller.response.MessageRS;
import com.gt.giulianotrincavelli.service.MessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;

import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/api/messages")
@Slf4j
public class MessageController {
    @Autowired
    private MessageService service;

    @PostMapping("/save")
    ResponseEntity<MessageRS> save(@RequestBody MessageRQ messageRQ) {
        MessageRS messageRS = service.save(messageRQ);

        return status(HttpStatus.OK).body(messageRS);
    }
}
