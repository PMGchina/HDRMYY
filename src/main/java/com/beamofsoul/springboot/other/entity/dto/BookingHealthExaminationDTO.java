package com.beamofsoul.springboot.other.entity.dto;

import java.util.Date;
import java.util.List;

import com.beamofsoul.springboot.other.entity.BookingHealthExamination;
import com.beamofsoul.springboot.other.entity.HealthExaminationItem;
import com.beamofsoul.springboot.other.entity.HealthExaminationPackage;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor

public class BookingHealthExaminationDTO  {

	private Long id;
	private String name;
	private Boolean gender;
	private Date birthday;
	private String identity;
	private String phoneNumber;
	private List<HealthExaminationPackage> chosenExaminationPackages;
	private List<HealthExaminationItem> chosenExaminationItems;
	private Double totalAmount;
	private Date targetTime;
	private Date createDate;
	
	public BookingHealthExaminationDTO(BookingHealthExamination bhe, List<HealthExaminationPackage> chosenExaminationPackages, List<HealthExaminationItem> chosenExaminationItems) {
		this.id = bhe.getId();
		this.name = bhe.getName();
		this.gender = bhe.getGender();
		this.birthday = bhe.getBirthday();
		this.identity = bhe.getIdentity();
		this.phoneNumber = bhe.getPhoneNumber();
		this.chosenExaminationPackages = chosenExaminationPackages;
		this.chosenExaminationItems = chosenExaminationItems;
		this.totalAmount = bhe.getTotalAmount();
		this.targetTime = bhe.getTargetTime();
		this.createDate = bhe.getCreateDate();
	}
}
