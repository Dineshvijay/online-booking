package com.dineshvijay.saloonapi.service;

import com.dineshvijay.saloonapi.dto.PaymentRequest;
import com.dineshvijay.saloonapi.dto.UpdatePayment;
import com.stripe.exception.StripeException;
import org.springframework.http.ResponseEntity;

import java.math.BigInteger;

public interface PaymentService {
    ResponseEntity<Object> savePayment(PaymentRequest paymentRequest);
    ResponseEntity<Object> initiatePayment(PaymentRequest paymentRequest) throws StripeException;

    ResponseEntity<Object>updatePaymentStatus(BigInteger paymentId, UpdatePayment payment);
}
