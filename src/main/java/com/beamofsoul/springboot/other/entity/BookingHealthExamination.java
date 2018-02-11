package com.beamofsoul.springboot.other.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.beamofsoul.springboot.entity.BaseAbstractEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @ClassName BookingHealthExamination
 * @Description 预约体检实体类
 * @author MingshuJian
 * @Date 2017年8月23日 上午11:17:59
 * @version 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "T_BOOKING_HEALTH_EXAMINATION")
public class BookingHealthExamination extends BaseAbstractEntity  {

	private static final long serialVersionUID = 1393459261644060803L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	protected Long id;
	
	@Column(name = "name", length = 20)
	private String name;
	
	@Column(name = "gender")
	private Boolean gender;
	
	@Column(name = "birthday")
	private Date birthday;
	
	@Column(name = "identity")
	private String identity;
	
	@Column(name = "phone_number", length = 20)
	private String phoneNumber;
	
	@Column(name = "chosen_examination_packages")
	private Long[] chosenExaminationPackages;
	
	@Column(name = "chosen_examination_items")
	private Long[] chosenExaminationItems;
	
	@Column(name = "target_time")
	private Date targetTime;
	
	@Column(name = "total_amount")
	private Double totalAmount;
	
	private Boolean available;
	
	private Boolean process;
}
