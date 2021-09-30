package com.example.kafkaproducerspringboot;

import com.example.kafkaproducerspringboot.config.CommonDTO;
import lombok.Data;

@Data
public class TaxpayerDTO implements CommonDTO {

    private String name;

    private String document;

    @Override
    public String getType() {
        return "TaxPayerDTO";
    }

}