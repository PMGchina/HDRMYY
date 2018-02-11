package com.beamofsoul.springboot.entity.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SingleAnswerStatistics {

	private String content;
	private long count;
	private long total;
	private double percentage;
	
	public SingleAnswerStatistics(String content, long count) {
		this.content = content;
		this.count = count;
	}
	
	public SingleAnswerStatistics(String content, long count, long total) {
		this.content = content;
		this.count = count;
		this.setTotal(total);
	}
	
	public void setTotal(long total) {
		this.total = total;
		this.percentage = count * 100 / total;
	}
}
