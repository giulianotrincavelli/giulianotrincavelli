package com.gt.giulianotrincavelli.controller.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class MessageRS {
    private final String message;
}
