package com.example.kafkaproducerspringboot;

import com.example.kafkaproducerspringboot.config.CommonDTO;
import com.example.kafkaproducerspringboot.config.MessagingPort;
import com.irs.register.avro.taxpayer.TaxPayer;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class TaxpayerService implements MessagingPort<TaxPayer> {

    private KafkaProducer<String, TaxPayer> producer;

    @Autowired
    public TaxpayerService(@Qualifier("taxpayerProducer") KafkaProducer<String, TaxPayer> producer) {
        this.producer = producer;
    }

    @Override
    public String topic() {
        return "taxpayer-avro";
    }

    @Override
    public ProducerRecord<String, TaxPayer> createProducerRecord(TaxPayer taxPayer) {

        return new ProducerRecord<>(this.topic(), taxPayer);

    }

    @Override
    public void send(CommonDTO taxpayerDTO) {


        TaxPayer taxPayer = TaxPayer.newBuilder().setName(((TaxpayerDTO) taxpayerDTO).getName())
                .setDocument(((TaxpayerDTO) taxpayerDTO).getDocument()).setSituation(false).build();


        producer.send(this.createProducerRecord(taxPayer), (rm, ex) -> {
            if (ex == null) {
                log.info("Data sent with success!!!");
            } else {
                log.error("Fail to send message", ex);
            }
        });

        producer.flush();
        producer.close();

    }

}
