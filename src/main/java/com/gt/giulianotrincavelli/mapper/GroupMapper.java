package com.gt.giulianotrincavelli.mapper;

import com.gt.giulianotrincavelli.model.Group;
import com.gt.giulianotrincavelli.controller.request.GroupRQ;
import com.gt.giulianotrincavelli.controller.response.GroupRS;
import org.springframework.stereotype.Component;

@Component
public class GroupMapper {

    public GroupRS toResponse(Group group) {
        return GroupRS.builder().name(group.getName()).description(group.getDescription()).build();
    }

    public Group toEntity(GroupRQ groupRQ) {
        return Group.builder().name(groupRQ.getName()).description(groupRQ.getDescription()).build();
    }
}
