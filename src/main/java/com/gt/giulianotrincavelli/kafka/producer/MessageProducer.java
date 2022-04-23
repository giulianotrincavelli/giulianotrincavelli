package com.gt.giulianotrincavelli.kafka.producer;

import com.gt.giulianotrincavelli.model.Event;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@RequiredArgsConstructor
@Component
@Slf4j
public class MessageProducer {

    private final KafkaTemplate kafkaTemplate;

    public void sendMessage(Event event, String topic) throws Exception {
        try {
            log.info("Prepare for send event {}", event.toString());

            ProducerRecord record = new ProducerRecord(topic, null, null, event);

            kafkaTemplate.send(record).get(10, TimeUnit.SECONDS);
        } catch (Exception ex) {
            throw ex;
        }
    }
}
