package com.dineshvijay.saloonapi.repository;

import com.dineshvijay.saloonapi.entity.SalonServiceDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface SalonServiceDetailRepository extends JpaRepository<SalonServiceDetail, BigInteger> {

}
