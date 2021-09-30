package com.example.kafkaproducerspringboot.config;

import com.irs.register.avro.taxpayer.TaxPayer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

@Configuration
public class MessagingConfigTaxPayer implements MessagingConfigPort<TaxPayer> {

    private KafkaProperties kafkaProperties;

    @Autowired
    public MessagingConfigTaxPayer(KafkaProperties kafkaProperties) {
        this.kafkaProperties = kafkaProperties;
    }

    @Bean(name = "taxpayerProducer")
    @Override
    public KafkaProducer<String, TaxPayer> configureProducer() {

        Properties properties = new Properties();

        properties.put("bootstrap.servers", kafkaProperties.getBootstrapServers());
        properties.put("acks", kafkaProperties.getAcksConfig());
        properties.put("retries", kafkaProperties.getRetriesConfig());
        properties.put("key.serializer", kafkaProperties.getKeySerializer());
        properties.put("value.serializer", kafkaProperties.getValueSerializer());
        properties.put("schema.registry.url", kafkaProperties.getSchemaRegistryUrl());

        return new KafkaProducer<>(properties);

    }
}
