package com.dineshvijay.saloonapi.service.impl;

import com.dineshvijay.saloonapi.dto.Salon;
import com.dineshvijay.saloonapi.dto.TicketDetails;
import com.dineshvijay.saloonapi.entity.SalonDetails;
import com.dineshvijay.saloonapi.exception.SalonException;
import com.dineshvijay.saloonapi.repository.TicketRepository;
import com.dineshvijay.saloonapi.service.TicketService;
import com.dineshvijay.saloonapi.utils.ResponseWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

@Service
public class TicketServiceImpl implements TicketService {
    private final TicketRepository repository;
    @Autowired
    private SalonDetails salonDetails;
    public TicketServiceImpl(TicketRepository repository) {
        this.repository = repository;
    }

    public ResponseEntity<Object> getTicket(BigInteger ticketId) {
        var ticket = repository.findById(ticketId);
        if(!ticket.isPresent()) {
            throw new SalonException("Invalid ticket id", 1005, HttpStatus.BAD_REQUEST);
        }
        var ticketDetails = new TicketDetails();
        ticketDetails.setSalonDetails(getSalon());
        ticketDetails.setTicket(ticket.get());
        ResponseWrapper obj = new ResponseWrapper(ticketDetails, 200, "success", HttpStatus.OK);
        return obj.wrap();
    }

    private Salon getSalon() {
        var salon = new Salon();
        salon.setName(salonDetails.getName());
        salon.setAddress(salonDetails.getAddress());
        salon.setCity(salonDetails.getCity());
        salon.setPhone(salonDetails.getPhone());
        salon.setZipcode(salonDetails.getZipcode());
        salon.setState(salonDetails.getState());
        return salon;
    }
}
