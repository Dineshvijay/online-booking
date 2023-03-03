package com.dineshvijay.saloonapi.service.impl;

import com.dineshvijay.saloonapi.entity.SalonServiceDetail;
import com.dineshvijay.saloonapi.repository.SalonServiceDetailRepository;
import com.dineshvijay.saloonapi.service.SalonServiceDetailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class SaloonServiceDetailServiceImpl implements SalonServiceDetailService {

    private final SalonServiceDetailRepository repository;
    public SaloonServiceDetailServiceImpl(SalonServiceDetailRepository repository) {
        this.repository = repository;
    }
    @Override
    public List<SalonServiceDetail> availableServices() {
        log.info("SaloonServiceDetailServiceImpl called");
        return repository.findAll();
    }
}
