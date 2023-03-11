package com.dineshvijay.saloonapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "salon_service_detail")
public class SalonServiceDetail {
    @Id
    private BigInteger id;
    private String description;
    private String name;
    private BigInteger price;
    @Column(name = "time_in_minutes")
    private int time;
    @JsonIgnore
    @OneToMany
    @JoinTable(name = "slot_available_services",
            joinColumns = @JoinColumn(name = "available_services_id"),
            inverseJoinColumns = @JoinColumn(name = "slot_id"))
    private List<Slot> slots;
}
