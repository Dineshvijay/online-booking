package com.dineshvijay.saloonapi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigInteger;
import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "payment")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private BigInteger paymentID;
    private Double amount;
    private int status;
    @Column(name = "created_time")
    private LocalDateTime createdTime;
    @Column(name = "updated_time")
    private LocalDateTime updatedTime;
    private String intentId;
    @Column(name = "client_secret")
    private String secretId;
    private String firstName;
    private String lastName;
    private String email;
    @Column(name = "phone_number")
    private String phoneNo;
    @OneToOne
    @JoinColumn(name = "slot_id")
    private Slot slot;
    @OneToOne
    @JoinColumn(name = "selected_service_id")
    private SalonServiceDetail salonServiceDetail;
}
