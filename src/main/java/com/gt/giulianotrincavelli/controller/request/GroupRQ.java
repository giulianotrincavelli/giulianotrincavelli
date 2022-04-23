package com.gt.giulianotrincavelli.controller.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class GroupRQ {
    private final String name;
    private final String description;
}
