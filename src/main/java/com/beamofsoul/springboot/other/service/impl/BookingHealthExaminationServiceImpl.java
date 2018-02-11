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
import com.beamofsoul.springboot.other.entity.BookingHealthExamination;
import com.beamofsoul.springboot.other.entity.HealthExaminationItem;
import com.beamofsoul.springboot.other.entity.HealthExaminationPackage;
import com.beamofsoul.springboot.other.entity.dto.BookingHealthExaminationDTO;
import com.beamofsoul.springboot.other.entity.query.QBookingHealthExamination;
import com.beamofsoul.springboot.other.repository.BookingHealthExaminationRepository;
import com.beamofsoul.springboot.other.service.BookingHealthExaminationService;
import com.beamofsoul.springboot.other.service.HealthExaminationItemService;
import com.beamofsoul.springboot.other.service.HealthExaminationPackageService;
import com.beamofsoul.springboot.service.impl.BaseAbstractServiceImpl;
import com.querydsl.core.types.Predicate;

@Service("bookingHealthExaminationService")
public class BookingHealthExaminationServiceImpl extends BaseAbstractServiceImpl implements BookingHealthExaminationService {

	@Autowired
	private BookingHealthExaminationRepository bookingHealthExaminationRepository;
	
	@Autowired
	private HealthExaminationPackageService healthExaminationPackageService;
	
	@Autowired
	private HealthExaminationItemService healthExaminationItemService;

	@Override
	public BookingHealthExamination create(BookingHealthExamination bookingHealthExamination) {
		return bookingHealthExaminationRepository.save(bookingHealthExamination);
	}

	@Override
	public BookingHealthExamination update(BookingHealthExamination bookingHealthExamination) {
		BookingHealthExamination originalBookingHealthExamination = bookingHealthExaminationRepository.findOne(bookingHealthExamination.getId());
		BeanUtils.copyProperties(bookingHealthExamination, originalBookingHealthExamination);
		return bookingHealthExaminationRepository.save(originalBookingHealthExamination);
	}

	@Override
	@Transactional
	public long delete(Long... ids) {
		return bookingHealthExaminationRepository.deleteByIds(ids);
	}

	@Override
	public BookingHealthExamination findById(Long id) {
		return bookingHealthExaminationRepository.findOne(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<BookingHealthExamination> findAll() {
		return bookingHealthExaminationRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Page<BookingHealthExamination> findAll(Pageable pageable) {
		return bookingHealthExaminationRepository.findAll(pageable);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<BookingHealthExamination> findAll(Pageable pageable, Predicate predicate) {
		return bookingHealthExaminationRepository.findAll(predicate, pageable);
	}

	@Override
	public List<BookingHealthExaminationDTO> converTotBookingHealthExaminationDTO(List<BookingHealthExamination> bookingHealthExaminations) {
		List<BookingHealthExaminationDTO> dtoList = new ArrayList<BookingHealthExaminationDTO>();
		if (CollectionUtils.isNotBlank(bookingHealthExaminations)) {
			int sizeOfBookings = bookingHealthExaminations.size();
			Set<Long> setOfHealthExaminationPackageIds = new HashSet<Long>(sizeOfBookings);
			Set<Long> setOfHealthExaminationItemIds = new HashSet<Long>(sizeOfBookings);
			bookingHealthExaminations.stream().forEach(e -> {
				setOfHealthExaminationPackageIds.addAll(Arrays.asList(e.getChosenExaminationPackages()));
				setOfHealthExaminationItemIds.addAll(Arrays.asList(e.getChosenExaminationItems()));
			});
			
			List<HealthExaminationPackage> listOfPackages = healthExaminationPackageService.findByIds(setOfHealthExaminationPackageIds.toArray(new Long[] {}));
			List<HealthExaminationItem> listOfItems = healthExaminationItemService.findByIds(setOfHealthExaminationItemIds.toArray(new Long[] {}));
			Map<Long, HealthExaminationPackage> mapOfPackages = listOfPackages.stream().collect(Collectors.toMap(HealthExaminationPackage::getId, (e) -> e));
			Map<Long, HealthExaminationItem> mapOfItems = listOfItems.stream().collect(Collectors.toMap(HealthExaminationItem::getId, (e) -> e));
			
			bookingHealthExaminations.stream().forEach(e -> {
				dtoList.add(new BookingHealthExaminationDTO(
						e, 
						getPackageObjects(mapOfPackages, e.getChosenExaminationPackages()),
						getItemObjects(mapOfItems, e.getChosenExaminationItems())
				));
			});
		}
		return dtoList;
	}
	
	private List<HealthExaminationPackage> getPackageObjects(Map<Long, HealthExaminationPackage> map, Long[] ids) {
		List<HealthExaminationPackage> objects = new ArrayList<>(ids.length); 
		map.forEach((id, object) -> {
			if (ArrayUtils.contains(ids, id)) {
				objects.add(object);
			}
		});
		return objects;
	}
	
	private List<HealthExaminationItem> getItemObjects(Map<Long, HealthExaminationItem> map, Long[] ids) {
		List<HealthExaminationItem> objects = new ArrayList<>(ids.length);
		map.forEach((id, object) -> {
			if (ArrayUtils.contains(ids, id)) {
				objects.add(object);
			}
		});
		return objects;
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<BookingHealthExamination> findAllAvailableAndUnprocessed(String identity) {
		QBookingHealthExamination $ = new QBookingHealthExamination("BookingHealthExamination"); 
		return bookingHealthExaminationRepository.findByPredicate($.available.eq(true).and($.process.eq(false)).and($.targetTime.after(new Date())).and($.identity.eq(identity)));
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<BookingHealthExamination> findAllAvailableAndProcessed(String identity) {
		return findAllByAvailableAndProcessed(identity, true, true);
	}

	@Override
	@Transactional(readOnly = true)
	public List<BookingHealthExamination> findAllUnavailableAndUnprocessed(String identity) {
		return findAllByAvailableAndProcessed(identity, false, false);
	}
	
	private List<BookingHealthExamination> findAllByAvailableAndProcessed(String identity, Boolean available, Boolean process) {
		QBookingHealthExamination $ = new QBookingHealthExamination("BookingHealthExamination");
		return bookingHealthExaminationRepository.findByPredicate($.available.eq(available).and($.process.eq(process)).and($.identity.eq(identity)));
	}
}
