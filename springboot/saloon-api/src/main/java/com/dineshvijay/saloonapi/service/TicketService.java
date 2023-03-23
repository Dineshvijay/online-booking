package com.dineshvijay.saloonapi.service;

import org.springframework.http.ResponseEntity;

import java.math.BigInteger;

public interface TicketService {

    ResponseEntity<Object>getTicket(BigInteger ticketId);
}
