package com.beamofsoul.springboot.other.repository;

import org.springframework.stereotype.Repository;

import com.beamofsoul.springboot.other.entity.BookingReexamination;
import com.beamofsoul.springboot.management.repository.BaseMultielementRepository;

@Repository
public interface BookingReexaminationRepository extends BaseMultielementRepository<BookingReexamination, Long> {

}
