package com.gt.giulianotrincavelli.kafka.listener;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class MessageListener {

    @KafkaListener(topics = "test_topic",
            containerFactory = "messageKafkaListenerContainerFactory",
            autoStartup = "true",
            groupId = "group_id",
            id = "1")
    public void consume(String message,
                        @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition,
                        @Header(KafkaHeaders.OFFSET) int offsets,
                        Acknowledgment acknowledgment) {

        log.info("Event received: {}", message);
        log.info("Processing message in partition: {} and offset: {}", partition, offsets);
        acknowledgment.acknowledge();
    }
}
