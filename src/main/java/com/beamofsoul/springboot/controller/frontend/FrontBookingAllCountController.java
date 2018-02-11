package com.beamofsoul.springboot.controller.frontend;

import com.alibaba.fastjson.JSONObject;
import com.beamofsoul.springboot.controller.BaseAbstractController;
import static com.beamofsoul.springboot.management.util.JSONUtils.newInstance;
import com.beamofsoul.springboot.other.service.BookingHealthExaminationService;
import com.beamofsoul.springboot.other.service.BookingReexaminationService;
import com.beamofsoul.springboot.other.service.BookingRegistrationService;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/front/bookingAllCount")
public class FrontBookingAllCountController extends BaseAbstractController {

	@Resource
	private BookingRegistrationService bookingRegistrationService;
	@Resource
	private BookingHealthExaminationService bookingHealthExaminationService;
	@Resource
	private BookingReexaminationService bookingReexaminationService;

	@PreAuthorize("authenticated")
	@RequestMapping(value = "index")
	public String index() {
		return "booking_all_message/front_booking_all_count";
	}

	//@PreAuthorize("authenticated")
	@RequestMapping(value = "registration/department", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject countRegistrationByDepartment(@RequestBody Map<String, String> map) {
		return newInstance("count", bookingRegistrationService.countInPeriodByDepartmentCode(map.get("startDate"), map.get("endDate")));
	}

	//@PreAuthorize("authenticated")
	@RequestMapping(value = "registration/doctor", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject countRegistrationByDoctor(@RequestBody Map<String, String> map) {
		return newInstance("count", bookingRegistrationService.countInPeriodByDoctorCode(map.get("startDate"), map.get("endDate")));
	}
}
