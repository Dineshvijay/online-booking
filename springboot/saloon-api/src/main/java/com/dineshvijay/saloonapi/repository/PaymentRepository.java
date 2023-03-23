package com.dineshvijay.saloonapi.repository;

import com.dineshvijay.saloonapi.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.math.BigInteger;
import java.time.LocalDateTime;

public interface PaymentRepository extends JpaRepository<Payment, BigInteger> {

    @Transactional
    @Modifying
    @Query(value = "UPDATE Payment p SET p.status = :status, p.updatedTime= :updatedAt WHERE p.id = :paymentId")
    int updatePaymentStatus(@Param("status") Integer status, @Param("updatedAt")LocalDateTime updatedAt, @Param("paymentId") BigInteger paymentId);

    
}
