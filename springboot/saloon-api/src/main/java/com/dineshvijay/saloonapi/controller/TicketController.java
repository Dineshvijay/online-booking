package com.dineshvijay.saloonapi.controller;

import com.dineshvijay.saloonapi.service.TicketService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;

@RestController
@RequestMapping("/api/tickets")
@CrossOrigin({"http://localhost:3000", "http://192.168.101.5:3000"})
@Api(tags = {"Tickets"})
public class TicketController {

    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @ApiOperation(value = "VerifyTickets")
    @GetMapping("/{ticketId}")
    public ResponseEntity<Object> fetchTicket(@PathVariable BigInteger ticketId) {
        return ticketService.getTicket(ticketId);
    }
}
