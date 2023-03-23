package com.dineshvijay.saloonapi.repository;

import com.dineshvijay.saloonapi.entity.Ticket;
import com.dineshvijay.saloonapi.service.TicketService;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

public interface TicketRepository extends JpaRepository<Ticket, BigInteger> {

}
