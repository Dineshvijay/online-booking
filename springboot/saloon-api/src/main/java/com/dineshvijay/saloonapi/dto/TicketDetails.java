package com.dineshvijay.saloonapi.dto;

import com.dineshvijay.saloonapi.entity.Payment;
import com.dineshvijay.saloonapi.entity.SalonDetails;
import com.dineshvijay.saloonapi.entity.Ticket;
import lombok.Data;

import java.math.BigInteger;

@Data
public class TicketDetails {
    private Salon salonDetails;
    private Ticket ticket;
}
