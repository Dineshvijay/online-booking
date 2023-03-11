package com.dineshvijay.saloonapi.service;

import com.dineshvijay.saloonapi.entity.SalonServiceDetail;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface SalonServiceDetailService {

    ResponseEntity<Object> getSalonService();
}
