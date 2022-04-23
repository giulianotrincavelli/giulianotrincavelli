package com.gt.giulianotrincavelli.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class ContactRS {
    private final String name;
    private final String phone;
}
