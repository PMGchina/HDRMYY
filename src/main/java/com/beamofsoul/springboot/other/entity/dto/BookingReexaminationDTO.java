package com.beamofsoul.springboot.other.entity.dto;

import java.util.Date;
import java.util.List;

import com.beamofsoul.springboot.other.entity.BookingReexamination;
import com.beamofsoul.springboot.other.entity.ReexaminationItem;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor

public class BookingReexaminationDTO  {

	private Long id;
	private String medicalCard;
	private List<ReexaminationItem> chosenReexaminationItems;
	private Double totalAmount;
	private Date targetTime;
	private Date createDate;
	
	public BookingReexaminationDTO(BookingReexamination br, List<ReexaminationItem> chosenReexaminationItems) {
		this.id = br.getId();
		this.medicalCard = br.getMedicalCard();
		this.chosenReexaminationItems = chosenReexaminationItems;
		this.totalAmount = br.getTotalAmount();
		this.targetTime = br.getTargetTime();
		this.createDate = br.getCreateDate();
	}
}
