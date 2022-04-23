package com.gt.giulianotrincavelli.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ContactRQ {
    private final String name;
    private final String phone;
}
