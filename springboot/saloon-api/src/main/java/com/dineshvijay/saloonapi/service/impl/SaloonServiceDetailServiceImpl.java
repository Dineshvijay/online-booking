package com.dineshvijay.saloonapi.service.impl;

import com.dineshvijay.saloonapi.entity.SalonServiceDetail;
import com.dineshvijay.saloonapi.repository.SalonServiceDetailRepository;
import com.dineshvijay.saloonapi.service.SalonServiceDetailService;
import com.dineshvijay.saloonapi.utils.ResponseWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Object> getSalonService() {
        log.info("SaloonServiceDetailServiceImpl called");
        List<SalonServiceDetail> salonServiceDetails = repository.findAll();
        ResponseWrapper responseWrapper = new ResponseWrapper(salonServiceDetails, 200, "success", HttpStatus.OK);
        return responseWrapper.wrap();
    }
}
