package com.dineshvijay.saloonapi.dto;

import com.dineshvijay.saloonapi.entity.SalonServiceDetail;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AvailableSlot {
    private BigInteger id;
    private LocalDateTime slotFor;
    private Integer status;
    private String stylistName;
    private SalonServiceDetail salonServiceDetail;
}
