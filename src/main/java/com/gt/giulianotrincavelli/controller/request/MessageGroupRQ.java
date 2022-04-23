package com.gt.giulianotrincavelli.controller.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class MessageGroupRQ {
    private final String contact;
    private final String message;
    private final String group;
}
