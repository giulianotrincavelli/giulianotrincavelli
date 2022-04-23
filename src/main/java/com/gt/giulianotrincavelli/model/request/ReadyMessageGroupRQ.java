package com.gt.giulianotrincavelli.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ReadyMessageGroupRQ {
    private final String contact;
    @JsonProperty("message_key")
    private final String messageKey;
    private final String group;
}
