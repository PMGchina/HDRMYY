package com.beamofsoul.springboot.other.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.beamofsoul.springboot.other.entity.BookingRegistration;
import com.querydsl.core.types.Predicate;

public interface BookingRegistrationService {

	BookingRegistration create(BookingRegistration bookingRegistration);

	BookingRegistration findById(Long id);

	List<BookingRegistration> findAll();

	Page<BookingRegistration> findAll(Pageable pageable);

	Page<BookingRegistration> findAll(Pageable pageable, Predicate predicate);

	List<BookingRegistration> findAllAvailableAndUnprocessed(String medicalCard);

	List<BookingRegistration> findAllAvailableAndProcessed(String medicalCard);

	List<BookingRegistration> findAllUnavailableAndUnprocessed(String medicalCard);

	Long updateAvailable(Long id, boolean available);

	List countInPeriodByDepartmentCode(String startDate, String endDate);

	List countInPeriodByDoctorCode(String startDate, String endDate);
}
