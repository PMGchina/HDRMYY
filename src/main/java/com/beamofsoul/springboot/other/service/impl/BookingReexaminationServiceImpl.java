package com.beamofsoul.springboot.other.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.beamofsoul.springboot.management.util.CollectionUtils;
import com.beamofsoul.springboot.other.entity.BookingReexamination;
import com.beamofsoul.springboot.other.entity.ReexaminationItem;
import com.beamofsoul.springboot.other.entity.dto.BookingReexaminationDTO;
import com.beamofsoul.springboot.other.entity.query.QBookingReexamination;
import com.beamofsoul.springboot.other.repository.BookingReexaminationRepository;
import com.beamofsoul.springboot.other.service.BookingReexaminationService;
import com.beamofsoul.springboot.other.service.ReexaminationItemService;
import com.beamofsoul.springboot.service.impl.BaseAbstractServiceImpl;
import com.querydsl.core.types.Predicate;

@Service("bookingReexaminationService")
public class BookingReexaminationServiceImpl extends BaseAbstractServiceImpl implements BookingReexaminationService {

	@Autowired
	private BookingReexaminationRepository bookingReexaminationRepository;
	
	@Autowired
	private ReexaminationItemService reexaminationItemService;

	@Override
	public BookingReexamination create(BookingReexamination bookingReexamination) {
		return bookingReexaminationRepository.save(bookingReexamination);
	}

	@Override
	public BookingReexamination update(BookingReexamination bookingReexamination) {
		BookingReexamination originalBookingReexamination = bookingReexaminationRepository.findOne(bookingReexamination.getId());
		BeanUtils.copyProperties(bookingReexamination, originalBookingReexamination);
		return bookingReexaminationRepository.save(originalBookingReexamination);
	}

	@Override
	@Transactional
	public long delete(Long... ids) {
		return bookingReexaminationRepository.deleteByIds(ids);
	}

	@Override
	public BookingReexamination findById(Long id) {
		return bookingReexaminationRepository.findOne(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<BookingReexamination> findAll() {
		return bookingReexaminationRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Page<BookingReexamination> findAll(Pageable pageable) {
		return bookingReexaminationRepository.findAll(pageable);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<BookingReexamination> findAll(Pageable pageable, Predicate predicate) {
		return bookingReexaminationRepository.findAll(predicate, pageable);
	}
	
	@Override
	public List<BookingReexaminationDTO> converTotBookingReexaminationDTO(List<BookingReexamination> bookingReexaminations) {
		List<BookingReexaminationDTO> dtoList = new ArrayList<BookingReexaminationDTO>();
		if (CollectionUtils.isNotBlank(bookingReexaminations)) {
			int sizeOfBookings = bookingReexaminations.size();
			Set<Long> setOfReexaminationItemIds = new HashSet<Long>(sizeOfBookings);
			bookingReexaminations.stream().forEach(e -> {
				setOfReexaminationItemIds.addAll(Arrays.asList(e.getChosenReexaminationItems()));
			});
			
			List<ReexaminationItem> listOfItems = reexaminationItemService.findByIds(setOfReexaminationItemIds.toArray(new Long[] {}));
			Map<Long, ReexaminationItem> mapOfItems = listOfItems.stream().collect(Collectors.toMap(ReexaminationItem::getId, (e) -> e));
			
			bookingReexaminations.stream().forEach(e -> {
				dtoList.add(new BookingReexaminationDTO(e, getItemObjects(mapOfItems, e.getChosenReexaminationItems())));
			});
		}
		return dtoList;
	}
	
	private List<ReexaminationItem> getItemObjects(Map<Long, ReexaminationItem> map, Long[] ids) {
		List<ReexaminationItem> objects = new ArrayList<>(ids.length);
		map.forEach((id, object) -> {
			if (ArrayUtils.contains(ids, id)) {
				objects.add(object);
			}
		});
		return objects;
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<BookingReexamination> findAllAvailableAndUnprocessed(String medicalCard) {
		QBookingReexamination $ = new QBookingReexamination("BookingReexamination");
		return bookingReexaminationRepository.findByPredicate($.available.eq(true).and($.process.eq(false)).and($.targetTime.after(new Date())).and($.medicalCard.eq(medicalCard)));
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<BookingReexamination> findAllAvailableAndProcessed(String medicalCard) {
		return findAllByAvailableAndProcessed(medicalCard, true, true);
	}

	@Override
	@Transactional(readOnly = true)
	public List<BookingReexamination> findAllUnavailableAndUnprocessed(String medicalCard) {
		return findAllByAvailableAndProcessed(medicalCard, false, false);
	}
	
	private List<BookingReexamination> findAllByAvailableAndProcessed(String medicalCard, Boolean available, Boolean process) {
		QBookingReexamination $ = new QBookingReexamination("BookingReexamination");
		return bookingReexaminationRepository.findByPredicate($.available.eq(available).and($.process.eq(process)).and($.medicalCard.eq(medicalCard)));
	}
	
	@Override
	@Transactional("otherTransactionManager")
	public Long updateAvailable(Long id, boolean available) {
		QBookingReexamination $ = new QBookingReexamination("BookingReexamination");
		return bookingReexaminationRepository.update($.available, available, $.id.eq(id));
	}
}

