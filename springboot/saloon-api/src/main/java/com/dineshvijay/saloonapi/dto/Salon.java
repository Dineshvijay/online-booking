package com.dineshvijay.saloonapi.dto;

import lombok.Data;

@Data
public class Salon {
    private String name;
    private String address;
    private String city;
    private String state;
    private Long zipcode;
    private String phone;
}
