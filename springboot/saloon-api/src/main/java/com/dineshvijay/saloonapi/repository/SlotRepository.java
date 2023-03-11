package com.dineshvijay.saloonapi.repository;

import com.dineshvijay.saloonapi.dto.AvailableSlot;
import com.dineshvijay.saloonapi.entity.Slot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;

public interface SlotRepository extends JpaRepository<Slot, BigInteger> {
    @Query(value = "SELECT new com.dineshvijay.saloonapi.dto.AvailableSlot(sl.id, sl.slotFor, sl.status, sl.stylistName, s) FROM SalonServiceDetail s JOIN s.slots sl WHERE s.id = :serviceId AND Date(sl.slotFor) = :date")
    List<AvailableSlot>getAvailableSlotForService(@Param("serviceId") BigInteger serviceId, @Param("date")LocalDateTime date);

    @Query("SELECT s.stylistName FROM SalonServiceDetail sa JOIN sa.slots s")
    List<String> getStylistName();

}
