package com.dineshvijay.saloonapi.repository;

import com.dineshvijay.saloonapi.dto.AvailableSlot;
import com.dineshvijay.saloonapi.entity.Slot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface SlotRepository extends JpaRepository<Slot, BigInteger> {
    @Query(value = "SELECT new com.dineshvijay.saloonapi.dto.AvailableSlot(sl.id, sl.slotFor, sl.status, sl.stylistName, sl.confirmedAt, sl.lockedAt, s) FROM SalonServiceDetail s JOIN s.slots sl WHERE s.id = :serviceId AND Date(sl.slotFor) = :date AND sl.status = 0")
    List<AvailableSlot>getAvailableSlotForService(@Param("serviceId") BigInteger serviceId, @Param("date")LocalDateTime date);

    @Query(value = "SELECT new com.dineshvijay.saloonapi.dto.AvailableSlot(sl.id, sl.slotFor, sl.status, sl.stylistName, sl.confirmedAt, sl.lockedAt, s) FROM SalonServiceDetail s JOIN s.slots sl WHERE s.id = :serviceId AND sl.id = :slotId")
    AvailableSlot getSelectedSlotForService(@Param("serviceId") BigInteger serviceId, @Param("slotId")BigInteger slotId);

    @Transactional
    @Modifying
    @Query(value = "UPDATE Slot sl SET sl.status = :status, sl.lockedAt = :lockedAt WHERE sl.id = :slotId")
    int resetSlot(@Param("slotId") BigInteger slotId, @Param("status") Integer status, @Param("lockedAt") LocalDateTime lockedAt);

    @Transactional
    @Modifying
    @Query(value = "UPDATE Slot sl SET sl.status = :status, sl.lockedAt = :lockedAt WHERE sl.id = :slotId")
    int lockSlot(@Param("slotId") BigInteger slotId, @Param("status") Integer status, @Param("lockedAt") LocalDateTime lockedAt);

    @Transactional
    @Modifying
    @Query(value = "UPDATE Slot sl SET sl.status = :status, sl.confirmedAt = :confirmedAt WHERE sl.id = :slotId")
    int confirmSlot(@Param("slotId") BigInteger slotId, @Param("status") Integer status, @Param("confirmedAt") LocalDateTime confirmedAt);

}
