package com.dineshvijay.saloonapi.entity;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

@Data
public class SalonDetails {
    @Value("${salon.name}")
    private String name;
    @Value("${salon.address}")
    private String address;
    @Value("${salon.city}")
    private String city;
    @Value("${salon.state}")
    private String state;
    @Value("${salon.zipcode}")
    private Long zipcode;
    @Value("${salon.phone}")
    private String phone;
}
