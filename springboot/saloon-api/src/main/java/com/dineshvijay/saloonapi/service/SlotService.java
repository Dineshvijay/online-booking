package com.dineshvijay.saloonapi.service;

import com.dineshvijay.saloonapi.dto.AvailableSlot;
import com.dineshvijay.saloonapi.entity.Slot;
import org.springframework.http.ResponseEntity;

import java.math.BigInteger;
import java.util.List;

public interface SlotService {
     ResponseEntity<Object> getAvailableSlots(int salonServiceId, String formattedDate);
     ResponseEntity<Object> updateSlotStatus(int slotId, int status);
}
