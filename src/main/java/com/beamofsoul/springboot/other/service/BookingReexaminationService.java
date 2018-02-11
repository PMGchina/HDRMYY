package com.beamofsoul.springboot.other.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.beamofsoul.springboot.other.entity.BookingReexamination;
import com.beamofsoul.springboot.other.entity.dto.BookingReexaminationDTO;
import com.querydsl.core.types.Predicate;

public interface BookingReexaminationService {

	BookingReexamination create(BookingReexamination bookingReexamination);
	BookingReexamination update(BookingReexamination bookingReexamination);
	long delete(Long... ids);

	BookingReexamination findById(Long id);

	List<BookingReexamination> findAll();
	Page<BookingReexamination> findAll(Pageable pageable);
	Page<BookingReexamination> findAll(Pageable pageable, Predicate predicate);
	
	List<BookingReexamination> findAllAvailableAndUnprocessed(String medicalCard);
	List<BookingReexamination> findAllAvailableAndProcessed(String medicalCard);
	List<BookingReexamination> findAllUnavailableAndUnprocessed(String medicalCard);
	
	List<BookingReexaminationDTO> converTotBookingReexaminationDTO(List<BookingReexamination> bookingReexaminations);
	Long updateAvailable(Long id, boolean available);
}
