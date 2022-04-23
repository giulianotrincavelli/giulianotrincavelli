package com.gt.giulianotrincavelli.controller;

import com.gt.giulianotrincavelli.model.request.MessageGroupRQ;
import com.gt.giulianotrincavelli.model.request.ReadyMessageGroupRQ;
import com.gt.giulianotrincavelli.model.response.FullMessageGroupRS;
import com.gt.giulianotrincavelli.model.response.MessageGroupRS;
import com.gt.giulianotrincavelli.model.response.ReadyMessageGroupRS;
import com.gt.giulianotrincavelli.service.MessageGroupService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/api/messages-group")
@Slf4j
public class MessageGroupController {
    @Autowired
    private MessageGroupService service;

    @GetMapping()
    ResponseEntity<FullMessageGroupRS> get(String messageKey) {
        FullMessageGroupRS fullMessageGroupRS = service.getByMessageKey(messageKey);

        return status(HttpStatus.OK).body(fullMessageGroupRS);
    }

    @PostMapping("/mark-as-ready")
    ResponseEntity<ReadyMessageGroupRS> markAsReady(@RequestBody ReadyMessageGroupRQ readyMessageGroupRQ) {
        ReadyMessageGroupRS readyMessageGroup = service.markAsReady(readyMessageGroupRQ);

        return status(HttpStatus.OK).body(readyMessageGroup);
    }

    @PostMapping("/save")
    ResponseEntity<MessageGroupRS> save(@RequestBody MessageGroupRQ messageGroupRQ) {
        MessageGroupRS messageGroupRS = service.save(messageGroupRQ);

        return status(HttpStatus.OK).body(messageGroupRS);
    }
}
