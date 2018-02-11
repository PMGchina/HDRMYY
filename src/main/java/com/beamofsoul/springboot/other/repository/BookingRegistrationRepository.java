package com.beamofsoul.springboot.other.repository;

import org.springframework.stereotype.Repository;

import com.beamofsoul.springboot.other.entity.BookingRegistration;
import com.beamofsoul.springboot.management.repository.BaseMultielementRepository;
import java.util.List;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface BookingRegistrationRepository extends BaseMultielementRepository<BookingRegistration, Long> {

	@Query(value = "select c.name,b.allcount,b.avacount from "
			+ "(select a.department_code,count(a.department_code) as allcount,count(case when a.process=1 then a.department_code end) as avacount from T_BOOKING_REGISTRATION a "
			+ "where a.target_time between ?1 and ?2 and a.available=1 group by a.department_code) b "
			+ "left join T_DEPARTMENT c on c.code=b.department_code", nativeQuery = true)
	List countInPeriodByDepartmentCode(String startDate, String endDate);

	@Query(value = "select c.name,b.allcount,b.avacount from "
			+ "(select a.doctor_code,count(a.doctor_code) as allcount,count(case when a.process=1 then a.doctor_code end) as avacount from T_BOOKING_REGISTRATION a "
			+ "where a.target_time between ?1 and ?2 and a.doctor_code is not null and a.available=1 group by a.doctor_code) b "
			+ "left join T_DOCTOR c on c.code=b.doctor_code", nativeQuery = true)
	List countInPeriodByDoctorCode(String startDate, String endDate);
}
