package com.gt.giulianotrincavelli.controller.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
@Builder
public class ReadyMessageGroupRS {
    private final ContactRS creator;
    private final GroupRS group;
    private final MessageRS message;
    private final List<String> readers;
}
