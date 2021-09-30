package com.example.kafkaproducerspringboot.config;

import org.apache.kafka.clients.producer.ProducerRecord;

public interface MessagingPort<T> {

    String topic();

    ProducerRecord<String, T> createProducerRecord(T t);

    void send(CommonDTO commonDTO);
}
