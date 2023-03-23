package com.dineshvijay.saloonapi.service.impl;

import com.dineshvijay.saloonapi.dto.AvailableSlot;
import com.dineshvijay.saloonapi.dto.PaymentRequest;
import com.dineshvijay.saloonapi.dto.PaymentResponse;
import com.dineshvijay.saloonapi.dto.UpdatePayment;
import com.dineshvijay.saloonapi.entity.Payment;
import com.dineshvijay.saloonapi.entity.Slot;
import com.dineshvijay.saloonapi.entity.Ticket;
import com.dineshvijay.saloonapi.exception.SalonException;
import com.dineshvijay.saloonapi.repository.PaymentRepository;
import com.dineshvijay.saloonapi.repository.SlotRepository;
import com.dineshvijay.saloonapi.repository.TicketRepository;
import com.dineshvijay.saloonapi.service.PaymentService;
import com.dineshvijay.saloonapi.utils.ResponseWrapper;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.time.LocalDateTime;

@Service
@Slf4j
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository paymentRepository;
    private final SlotRepository slotRepository;

    private final TicketRepository ticketRepository;

    public PaymentServiceImpl(PaymentRepository paymentRepository, SlotRepository slotRepository, TicketRepository ticketRepository) {
        this.paymentRepository = paymentRepository;
        this.slotRepository = slotRepository;
        this.ticketRepository = ticketRepository;
    }

    public ResponseEntity<Object> savePayment(PaymentRequest paymentRequest) {
        return null;
    }

    public ResponseEntity<Object> initiatePayment(PaymentRequest paymentRequest) throws StripeException {
        var slot = slotRepository.getSelectedSlotForService(paymentRequest.getSalonServiceDetailID(), paymentRequest.getSlotID());
        log.info("SELECTED ID = {}", slot.getId());
        log.info("SELECTED Service = {}", slot.getSalonServiceDetail().getName());
        if(slot.getStatus() != 0){
            throw new SalonException("Slot not available", 1000, HttpStatus.OK);
        }
        log.info("slot available");
        int result = slotRepository.lockSlot(slot.getId(), Integer.valueOf(1), LocalDateTime.now());
        log.info("slot update = {}", result);
        Long price = Long.valueOf(slot.getSalonServiceDetail().getPrice().longValue());
        log.info("price = {}", price);
        var paymentIntent = createPaymentIntent(price);
        var payment = save(paymentRequest, slot, paymentIntent.getClientSecret());
        log.info("savePayment Pid => {}", payment.getPaymentID());
        log.info("savePayment slot service => {}", payment.getSlot().getSalonServiceDetail().getName());
        PaymentResponse paymentResponse = new PaymentResponse();
        paymentResponse.setSecretId(paymentIntent.getClientSecret());
        paymentResponse.setIntentId(paymentIntent.getId());
        paymentResponse.setSlot(getSlot(slot));
        paymentResponse.setPaymentID(payment.getPaymentID());
        paymentResponse.setAmount(payment.getAmount());
        paymentResponse.setCreatedTime(payment.getCreatedTime());
        ResponseWrapper responseWrapper = new ResponseWrapper(paymentResponse, 200, "success", HttpStatus.OK);
        return responseWrapper.wrap();
    }

    public ResponseEntity<Object> updatePaymentStatus(BigInteger paymentId, UpdatePayment payment) {
        var result = paymentRepository.findById(paymentId);
        log.info("savePayment slot service => {}", result.get().getSlot().getId());
        if(!result.isPresent() && !result.isEmpty()) {
            throw new SalonException("Payment details not found", 1001, HttpStatus.BAD_REQUEST);
        }
        int dbResult = paymentRepository.updatePaymentStatus(payment.getPaymentStatus(), LocalDateTime.now() ,paymentId);
        Integer slotStatus = payment.getPaymentStatus() == 1 ? 2 : 0;
        if(payment.getPaymentStatus() == 2) {
            int slotResult = slotRepository.resetSlot(result.get().getSlot().getId(), slotStatus, null);
            throw new SalonException("Payment Failed. pls try again.", 1002, HttpStatus.BAD_REQUEST);
        }
        int slotResult = slotRepository.confirmSlot(result.get().getSlot().getId(), slotStatus, LocalDateTime.now());
        Ticket ticket = saveTicket(result.get(), slotStatus);
        ResponseWrapper responseWrapper = new ResponseWrapper(ticket, 200, "success", HttpStatus.OK);
        return responseWrapper.wrap();
    }

    private PaymentIntent createPaymentIntent(Long price) throws StripeException {
        PaymentIntentCreateParams params =
                PaymentIntentCreateParams.builder()
                        .setAmount(price)
                        .setCurrency("inr")
                        .build();
        return PaymentIntent.create(params);
    }

    private Payment save(PaymentRequest request, AvailableSlot slot, String secretId) {
        Double price = slot.getSalonServiceDetail().getPrice().doubleValue();
        Payment payment = new Payment();
        payment.setSecretId(secretId);
        payment.setFirstName(request.getFirstName());
        payment.setLastName(request.getLastName());
        payment.setEmail(request.getEmail());
        payment.setPhoneNo(request.getPhoneNo());
        payment.setAmount(price);
        payment.setSalonServiceDetail(slot.getSalonServiceDetail());
        payment.setSlot(getSlot(slot));
        payment.setCreatedTime(LocalDateTime.now());
        payment.setStatus(Integer.valueOf(1));
        Payment dbResponse = paymentRepository.save(payment);
        return dbResponse;
    }

    private Ticket saveTicket(Payment payment, Integer status) {
        Ticket ticket = new Ticket();
        ticket.setTicketStatus(status);
        ticket.setPayment(payment);
        ticket.getPayment().getSlot().setSalonServiceDetail(payment.getSalonServiceDetail());
        return ticketRepository.save(ticket);
    }

    private Slot getSlot(AvailableSlot availableSlot) {
        var slot = new Slot();
        slot.setId(availableSlot.getId());
        slot.setSlotFor(availableSlot.getSlotFor());
        slot.setStylistName(availableSlot.getStylistName());
        slot.setStatus(availableSlot.getStatus());
        slot.setConfirmedAt(availableSlot.getConfirmedAt());
        slot.setLockedAt(availableSlot.getLockedAt());
        slot.setSalonServiceDetail(availableSlot.getSalonServiceDetail());
        return slot;
    }
}
