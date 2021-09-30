package com.example.kafkaproducerspringboot.config;

import org.apache.kafka.clients.producer.KafkaProducer;

public interface MessagingConfigPort<T> {

    KafkaProducer<String, T> configureProducer();
}
