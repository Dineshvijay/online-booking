package com.dineshvijay.saloonapi.controller;

import com.dineshvijay.saloonapi.entity.SalonServiceDetail;
import com.dineshvijay.saloonapi.service.SalonServiceDetailService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/services")
@CrossOrigin({"http://localhost:3000", "http://192.168.101.5:3000"})
@Api(tags = {"salon services"})
public class SalonServiceDetailController {

    private final SalonServiceDetailService salonServiceDetailService;

    public SalonServiceDetailController(SalonServiceDetailService salonServiceDetailService) {
        this.salonServiceDetailService = salonServiceDetailService;
    }

    @ApiOperation(value = "RetrieveAvailableSalonServiceAPI")
    @GetMapping("/retrieveAvailableSalonServices")
    public ResponseEntity<Object> getAvailableServices() {
        return salonServiceDetailService.getSalonService();
    }

}
