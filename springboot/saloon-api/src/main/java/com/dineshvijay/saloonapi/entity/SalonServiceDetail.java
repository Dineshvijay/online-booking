package com.dineshvijay.saloonapi.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigInteger;

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

}
