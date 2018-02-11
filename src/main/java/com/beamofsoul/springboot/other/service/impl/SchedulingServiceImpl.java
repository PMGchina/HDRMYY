package com.beamofsoul.springboot.other.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.beamofsoul.springboot.other.entity.Scheduling;
import com.beamofsoul.springboot.other.entity.query.QScheduling;
import com.beamofsoul.springboot.other.repository.SchedulingRepository;
import com.beamofsoul.springboot.other.service.SchedulingService;
import com.beamofsoul.springboot.service.impl.BaseAbstractServiceImpl;
import com.querydsl.core.types.Predicate;

@Service("schedulingService")
public class SchedulingServiceImpl extends BaseAbstractServiceImpl implements SchedulingService {

	@Autowired
	private SchedulingRepository schedulingRepository;

	@Override
	public Scheduling findById(Long id) {
		return schedulingRepository.findOne(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Scheduling> findAll() {
		return schedulingRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Scheduling> findAllByDoctorCode(String code) {
		QScheduling scheduling = new QScheduling("Scheduling");
		return schedulingRepository.findByPredicate(scheduling.doctorCode.eq(code).and(scheduling.workday.after(new Date())));
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Scheduling> findAll(Pageable pageable) {
		return schedulingRepository.findAll(pageable);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Scheduling> findAll(Pageable pageable, Predicate predicate) {
		return schedulingRepository.findAll(predicate, pageable);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Scheduling> findAllByDate(Date date) {
		QScheduling $ = new QScheduling("Scheduling");
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, 1);
		return schedulingRepository.findByPredicate(
			 $.workday.year().eq(calendar.get(Calendar.YEAR))
				  .and($.workday.month().eq(calendar.get(Calendar.MONTH)))
				  .and($.workday.dayOfMonth().eq(calendar.get(Calendar.DAY_OF_MONTH))));
	}
}
