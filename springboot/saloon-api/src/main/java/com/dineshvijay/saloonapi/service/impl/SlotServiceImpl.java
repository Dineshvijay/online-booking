package com.dineshvijay.saloonapi.service.impl;

import com.dineshvijay.saloonapi.dto.AvailableSlot;
import com.dineshvijay.saloonapi.entity.Slot;
import com.dineshvijay.saloonapi.exception.ApiRequestException;
import com.dineshvijay.saloonapi.repository.SlotRepository;
import com.dineshvijay.saloonapi.service.SlotService;
import com.dineshvijay.saloonapi.utils.ResponseWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.swing.text.DateFormatter;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

@Service
@Slf4j
public class SlotServiceImpl implements SlotService {

    private final SlotRepository repository;
    public SlotServiceImpl(SlotRepository repository) {
        this.repository = repository;
    }

    public ResponseEntity<Object> getAvailableSlots(int salonServiceId, String formattedDate) {
        try {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate localDate = LocalDate.parse(formattedDate, dtf);
            LocalDateTime localDateTime = LocalDateTime.of(localDate, LocalTime.of(0,0));
            BigInteger id = BigInteger.valueOf(salonServiceId);
            log.info("salonServiceId = {} and formattedDate ={}", salonServiceId, formattedDate);
            var slots = repository.getAvailableSlotForService(id, localDateTime);
            var responseWrapper = new ResponseWrapper(slots, 200, "success", HttpStatus.OK);
            return responseWrapper.wrap();
        } catch (Exception e) {
            throw new ApiRequestException(e.getMessage(), 999, HttpStatus.BAD_REQUEST);
        }
    }

}
