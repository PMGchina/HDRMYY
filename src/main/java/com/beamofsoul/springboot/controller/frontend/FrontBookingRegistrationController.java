package com.beamofsoul.springboot.controller.frontend;

import static com.beamofsoul.springboot.management.util.JSONUtils.newInstance;

import com.alibaba.fastjson.JSONObject;
import com.beamofsoul.springboot.controller.BaseAbstractController;
import com.beamofsoul.springboot.entity.User;
import com.beamofsoul.springboot.management.mvc.CurrentUser;
import com.beamofsoul.springboot.other.entity.BookingRegistration;
import com.beamofsoul.springboot.other.service.BookingRegistrationService;
import com.beamofsoul.springboot.other.service.DepartmentService;
import com.beamofsoul.springboot.other.service.DoctorService;
import com.beamofsoul.springboot.other.service.SchedulingService;
import javax.annotation.Resource;
import javax.websocket.server.PathParam;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/front/bookingRegistration")
public class FrontBookingRegistrationController extends BaseAbstractController {

	@Resource
	private DepartmentService departmentService;

	@Resource
	private DoctorService doctorService;

	@Resource
	private SchedulingService schedulingService;

	@Resource
	private BookingRegistrationService bookingRegistrationService;

	@PreAuthorize("authenticated")
	@RequestMapping(value = "doctor")
	public String bookingRegistration() {
		return "booking_registration/front_booking_registration";
	}

	@PreAuthorize("authenticated")
	@RequestMapping(value = "expert")
	public String bookingExpert() {
		return "booking_registration/front_booking_expert";
	}

	@RequestMapping(value = "department/load", method = RequestMethod.GET)
	@ResponseBody
	public JSONObject loadDepartment() {
		return newInstance("department", departmentService.findAllAvailable());
	}

	@RequestMapping(value = "doctor/load", method = RequestMethod.GET)
	@ResponseBody
	public JSONObject loadDoctorByDepartmentCode(@PathParam(value = "code") String code) {
		return newInstance("doctor", doctorService.findAllNormalDoctorsByDepartmentCode(code));
	}

	@RequestMapping(value = "expert/load", method = RequestMethod.GET)
	@ResponseBody
	public JSONObject loadExpertByDepartmentCode(@PathParam(value = "code") String code) {
		return newInstance("doctor", doctorService.findAllExpertDoctorsByDepartmentCode(code));
	}

	@RequestMapping(value = "schedule/load", method = RequestMethod.GET)
	@ResponseBody
	public JSONObject loadScheduleByDoctorCode(@PathParam(value = "code") String code) {
		return newInstance("schedule", schedulingService.findAllByDoctorCode(code));
	}

	@RequestMapping(value = "bookingRegistration/add", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject addBookingRegistration(@RequestBody BookingRegistration bookingRegistration, @CurrentUser User user) {
		bookingRegistration.setMedicalCard(user.getUsername());
		return newInstance("creat", bookingRegistrationService.create(bookingRegistration).getId());
	}

}
