package com.beamofsoul.springboot.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @ClassName MessageBoard
 * @Description 便民服务(留言板)实体类
 * @author MingshuJian
 * @Date 2017年7月25日 下午1:51:59
 * @version 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "T_MESSAGE_BOARD")
public class MessageBoard extends BaseAbstractEntity  {
	
	private static final long serialVersionUID = 5564676007648499661L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	protected Long id;
	
	@Column(length = 100)
	private String title;
	
	@Column(length = 512)
	private String content;
	
    @Column(name = "medical_card", length = 20)
    private String medicalCard;
	
	private Boolean available;
}
