package com.dineshvijay.saloonapi.entity;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;


@ConfigurationProperties(prefix = "salon")
@Configuration
@Data
public class SalonDetails {
    private String name;
    private String address;
    private String city;
    private String state;
    private Long zipcode;
    private String phone;

}
