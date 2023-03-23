package com.dineshvijay.saloonapi.dto;

import lombok.Data;

import java.math.BigInteger;

@Data
public class PaymentRequest {
    private BigInteger slotID;
    private BigInteger salonServiceDetailID;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNo;
}
