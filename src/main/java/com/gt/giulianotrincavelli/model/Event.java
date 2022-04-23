package com.gt.giulianotrincavelli.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Event {
    private String message;
    private String phone;
    private String group;
}
