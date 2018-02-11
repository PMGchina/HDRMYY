package com.beamofsoul.springboot.other.entity;

import com.beamofsoul.springboot.entity.BaseAbstractEntity;
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
 * @Description 经验报告单明细
 */
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "T_CHECK_REPORT_DETAIL")
public class CheckReportDetail extends BaseAbstractEntity {

	private static final long serialVersionUID = 0L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	protected Long id;
	/**
	 * 英文名称
	 */
	@Column(name = "english_name", length = 20)
	private String englishName;
	/**
	 * 中午名称
	 */
	@Column(name = "chinese_name", length = 20)
	private String chineseName;
	/**
	 * 结果
	 */
	@Column(name = "check_result")
	private String checkResult;
	/**
	 * 状态
	 */
	@Column(name = "check_state", length = 20)
	private String checkState;
	/**
	 * 单位
	 */
	@Column(length = 20)
	private String unit;
	/**
	 * 参考区间
	 */
	@Column(name = "reference_range", length = 20)
	private String referenceRange;
}
