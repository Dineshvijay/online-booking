package com.dineshvijay.saloonapi.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigInteger;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "slot")
public class Slot {

    @Id
    private BigInteger id;
    @Column(name = "confirmed_at")
    private LocalDateTime confirmedAt;
    @Column(name = "locked_at")
    private LocalDateTime lockedAt;
    @Column(name = "slot_for")
    private LocalDateTime slotFor;
    private Integer status;
    @Column(name="stylist_name")
    private String stylistName;
    @Column(name="selected_service_id")
    private BigInteger selectedServiceId;
    @ManyToOne
    @JoinColumn(name="available_services_id")
    private SalonServiceDetail salonServiceDetail;
}
