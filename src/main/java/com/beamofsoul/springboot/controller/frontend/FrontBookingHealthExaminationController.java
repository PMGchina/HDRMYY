package com.beamofsoul.springboot.controller.frontend;

import static com.beamofsoul.springboot.management.util.JSONUtils.newInstance;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.beamofsoul.springboot.controller.BaseAbstractController;
import com.beamofsoul.springboot.entity.User;
import com.beamofsoul.springboot.management.mvc.CurrentUser;
import com.beamofsoul.springboot.other.entity.BookingHealthExamination;
import com.beamofsoul.springboot.other.entity.Patient;
import com.beamofsoul.springboot.other.service.BookingHealthExaminationService;
import com.beamofsoul.springboot.other.service.HealthExaminationItemService;
import com.beamofsoul.springboot.other.service.HealthExaminationPackageService;
import com.beamofsoul.springboot.other.service.PatientService;
import com.beamofsoul.springboot.service.HealthExaminationExpertService;

@Controller
@RequestMapping("/front/bookingHealthExamination")
public class FrontBookingHealthExaminationController extends BaseAbstractController {

	@Resource
	private BookingHealthExaminationService bookingHealthExaminationService;

	@Resource
	private HealthExaminationPackageService healthExaminationPackageService;

	@Resource
	private HealthExaminationItemService healthExaminationItemService;

	@Resource
	private HealthExaminationExpertService healthExaminationExpertService;

	@Resource
	private PatientService patientService;

	@RequestMapping(value = "index")
	public String bookingHealthExamination() {
		return "booking_health_examination/front_booking_health_examination";
	}

	@RequestMapping(value = "package/load", method = RequestMethod.GET)
	@ResponseBody
	public JSONObject loadHealthExaminationPackage() {
		return newInstance("all", healthExaminationPackageService.findAllAvailable());
	}

	@RequestMapping(value = "item/load", method = RequestMethod.GET)
	@ResponseBody
	public JSONObject loadHealthExaminationItem() {
		return newInstance("all", healthExaminationItemService.findAllAvailable());
	}

	@RequestMapping(value = "expert/load", method = RequestMethod.GET)
	@ResponseBody
	public JSONObject loadHealthExaminationExpert() {
		return newInstance("all", healthExaminationExpertService.findAll());
	}

	@RequestMapping(value = "getCard", method = RequestMethod.GET)
	@ResponseBody
	public JSONObject getCurrentUser(@CurrentUser User user) {
		if (user == null) {
			return newInstance("card", "");
		} else {
			Patient patient = patientService.findByMedicalCard(user.getUsername());
			return newInstance("card", patient == null ? "" : patient.getMedicalCard());
		}
	}

	@RequestMapping(value = "add", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject addBookingHealthExamination(@RequestBody BookingHealthExamination booking) {
		return newInstance("creat", bookingHealthExaminationService.create(booking).getId());
	}
}
