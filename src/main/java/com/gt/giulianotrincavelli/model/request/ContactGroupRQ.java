package com.gt.giulianotrincavelli.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ContactGroupRQ {
    private final String phone;
    private final String group;
}
