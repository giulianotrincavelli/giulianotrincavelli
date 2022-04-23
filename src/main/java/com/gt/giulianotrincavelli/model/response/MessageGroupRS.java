package com.gt.giulianotrincavelli.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class MessageGroupRS {
    private final ContactRS contact;
    private final GroupRS group;
    private final MessageRS message;
}
