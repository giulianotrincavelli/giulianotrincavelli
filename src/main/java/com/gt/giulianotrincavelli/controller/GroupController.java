package com.gt.giulianotrincavelli.controller;

import com.gt.giulianotrincavelli.controller.request.ContactGroupRQ;
import com.gt.giulianotrincavelli.controller.request.GroupRQ;
import com.gt.giulianotrincavelli.controller.response.GroupRS;
import com.gt.giulianotrincavelli.service.GroupService;
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
@RequestMapping("/api/groups")
@Slf4j
public class GroupController {
    @Autowired
    private GroupService service;

    @PostMapping("/save")
    ResponseEntity<GroupRS> save(@RequestBody GroupRQ groupRQ) {
        GroupRS groupRS = service.save(groupRQ);

        return status(HttpStatus.OK).body(groupRS);
    }

    @PostMapping("/add-contact")
    ResponseEntity<GroupRS> add(@RequestBody ContactGroupRQ contactGroupRQ) {
        GroupRS groupRS = service.add(contactGroupRQ);

        return status(HttpStatus.OK).body(groupRS);
    }
}
