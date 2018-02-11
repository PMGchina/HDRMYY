package com.beamofsoul.springboot.management.schedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import lombok.extern.slf4j.Slf4j;

/**
 * @Description 自定义定时任务器
 */
@Slf4j
@Component
public class CustomJobs {

	public static final long ONE_SECOND = 1000;
	public static final long ONE_MINUTE = 60 * ONE_SECOND;

	@Autowired

	/**
	 * @Description: 定期查询并更新记录
	 */
	@Scheduled(fixedRate = ONE_SECOND)
	public void queryXXXAndReflush() {
	}
}
