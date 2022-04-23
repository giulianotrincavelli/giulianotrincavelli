package com.gt.giulianotrincavelli.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class GroupRQ {
    private final String name;
    private final String description;
}
