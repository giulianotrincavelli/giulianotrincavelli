package com.gt.giulianotrincavelli.controller;

import com.gt.giulianotrincavelli.controller.request.MessageGroupRQ;
import com.gt.giulianotrincavelli.controller.request.ReadyMessageGroupRQ;
import com.gt.giulianotrincavelli.controller.response.FullMessageGroupRS;
import com.gt.giulianotrincavelli.controller.response.MessageGroupRS;
import com.gt.giulianotrincavelli.controller.response.ReadyMessageGroupRS;
import com.gt.giulianotrincavelli.service.MessageGroupService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

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
