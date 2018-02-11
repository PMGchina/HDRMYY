package com.beamofsoul.springboot.other.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.beamofsoul.springboot.other.entity.BookingHealthExamination;
import com.beamofsoul.springboot.other.entity.dto.BookingHealthExaminationDTO;
import com.querydsl.core.types.Predicate;

public interface BookingHealthExaminationService {

	BookingHealthExamination create(BookingHealthExamination bookingHealthExamination);
	BookingHealthExamination update(BookingHealthExamination bookingHealthExamination);
	long delete(Long... ids);

	BookingHealthExamination findById(Long id);

	List<BookingHealthExamination> findAll();
	Page<BookingHealthExamination> findAll(Pageable pageable);
	Page<BookingHealthExamination> findAll(Pageable pageable, Predicate predicate);
	
	List<BookingHealthExamination> findAllAvailableAndUnprocessed(String identity);
	List<BookingHealthExamination> findAllAvailableAndProcessed(String identity);
	List<BookingHealthExamination> findAllUnavailableAndUnprocessed(String identity);

	List<BookingHealthExaminationDTO> converTotBookingHealthExaminationDTO(List<BookingHealthExamination> bookingHealthExaminations);
}
