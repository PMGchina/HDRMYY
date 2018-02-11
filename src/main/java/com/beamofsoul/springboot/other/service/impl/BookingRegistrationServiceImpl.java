package com.beamofsoul.springboot.other.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.beamofsoul.springboot.other.entity.BookingRegistration;
import com.beamofsoul.springboot.other.entity.query.QBookingRegistration;
import com.beamofsoul.springboot.other.repository.BookingRegistrationRepository;
import com.beamofsoul.springboot.other.service.BookingRegistrationService;
import com.beamofsoul.springboot.service.impl.BaseAbstractServiceImpl;
import com.querydsl.core.types.Predicate;

@Service("bookingRegistrationService")
public class BookingRegistrationServiceImpl extends BaseAbstractServiceImpl implements BookingRegistrationService {

	@Autowired
	private BookingRegistrationRepository bookingRegistrationRepository;

	@Override
	public BookingRegistration create(BookingRegistration bookingRegistration) {
		return bookingRegistrationRepository.save(bookingRegistration);
	}

	@Override
	public BookingRegistration findById(Long id) {
		return bookingRegistrationRepository.findOne(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<BookingRegistration> findAll() {
		return bookingRegistrationRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Page<BookingRegistration> findAll(Pageable pageable) {
		return bookingRegistrationRepository.findAll(pageable);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<BookingRegistration> findAll(Pageable pageable, Predicate predicate) {
		return bookingRegistrationRepository.findAll(predicate, pageable);
	}

	@Override
	@Transactional(readOnly = true)
	public List<BookingRegistration> findAllAvailableAndUnprocessed(String medicalCard) {
		QBookingRegistration $ = new QBookingRegistration("BookingRegistration");
		return bookingRegistrationRepository.findByPredicate($.available.eq(true).and($.process.eq(false)).and($.targetTime.after(new Date())).and($.medicalCard.eq(medicalCard)));
	}

	@Override
	@Transactional(readOnly = true)
	public List<BookingRegistration> findAllAvailableAndProcessed(String medicalCard) {
		return findAllByAvailableAndProcessed(medicalCard, true, true);
	}

	@Override
	@Transactional(readOnly = true)
	public List<BookingRegistration> findAllUnavailableAndUnprocessed(String medicalCard) {
		return findAllByAvailableAndProcessed(medicalCard, false, false);
	}

	private List<BookingRegistration> findAllByAvailableAndProcessed(String medicalCard, Boolean available, Boolean process) {
		QBookingRegistration $ = new QBookingRegistration("BookingRegistration");
		return bookingRegistrationRepository.findByPredicate($.available.eq(available).and($.process.eq(process)).and($.medicalCard.eq(medicalCard)));
	}

	@Override
	@Transactional("otherTransactionManager")
	public Long updateAvailable(Long id, boolean available) {
		QBookingRegistration $ = new QBookingRegistration("BookingRegistration");
		return bookingRegistrationRepository.update($.available, available, $.id.eq(id));
	}

	@Override
	public List countInPeriodByDepartmentCode(String startDate, String endDate) {
		return bookingRegistrationRepository.countInPeriodByDepartmentCode(startDate, endDate);
	}

	@Override
	public List countInPeriodByDoctorCode(String startDate, String endDate) {
		return bookingRegistrationRepository.countInPeriodByDoctorCode(startDate, endDate);
	}
}
