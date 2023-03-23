package com.dineshvijay.saloonapi.dto;

import com.dineshvijay.saloonapi.entity.SalonServiceDetail;
import com.dineshvijay.saloonapi.entity.Slot;
import lombok.Data;

import javax.persistence.ManyToOne;
import java.math.BigInteger;
import java.time.LocalDateTime;

@Data
public class PaymentResponse {
    private BigInteger paymentID;
    private Double amount;
    private int status;
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;
    private String intentId;
    private String secretId;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNo;
    private Slot slot;
}
