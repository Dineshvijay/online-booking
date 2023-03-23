package com.dineshvijay.saloonapi.controller;

import com.dineshvijay.saloonapi.dto.PaymentRequest;
import com.dineshvijay.saloonapi.dto.UpdatePayment;
import com.dineshvijay.saloonapi.service.PaymentService;
import com.stripe.exception.StripeException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;

@RestController
@RequestMapping("api/payment")
@CrossOrigin({"http://localhost:3000", "http://192.168.101.5:3000"})
@Slf4j
@Api(tags = {"Payment service"})
public class PaymentController {

    private final PaymentService service;
    public PaymentController(PaymentService service) {
        this.service = service;
    }

    @ApiOperation(value = "InitiatePaymentAPI")
    @PostMapping(path = "/initiate", produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> createPaymentIntent(@RequestBody PaymentRequest paymentRequest) throws StripeException {
        log.info("PaymentRequest = {}", paymentRequest.getFirstName());
        return service.initiatePayment(paymentRequest);
    }

    @ApiOperation(value = "VerifyPaymentAndConfirmSlotAPI")
    @PutMapping(path = "/confirm/{paymentId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object>confirmBooking(@PathVariable BigInteger paymentId, @RequestBody UpdatePayment payment) {
        log.info("ConfirmBooking paymentId = {}", paymentId);
        return service.updatePaymentStatus(paymentId, payment);
    }
}
