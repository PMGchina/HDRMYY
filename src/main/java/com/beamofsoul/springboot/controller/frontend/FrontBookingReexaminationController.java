package com.beamofsoul.springboot.controller.frontend;

import static com.beamofsoul.springboot.management.util.JSONUtils.newInstance;

import javax.annotation.Resource;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.beamofsoul.springboot.controller.BaseAbstractController;
import com.beamofsoul.springboot.entity.User;
import com.beamofsoul.springboot.management.mvc.CurrentUser;
import com.beamofsoul.springboot.other.entity.BookingReexamination;
import com.beamofsoul.springboot.other.entity.Patient;
import com.beamofsoul.springboot.other.service.BookingReexaminationService;
import com.beamofsoul.springboot.other.service.PatientService;
import com.beamofsoul.springboot.other.service.ReexaminationItemService;

@Controller
@RequestMapping("/front/bookingReexamination")
public class FrontBookingReexaminationController extends BaseAbstractController {

	@Resource
	private BookingReexaminationService bookingReexaminationService;

	@Resource
	private ReexaminationItemService reexaminationItemService;

	@Resource
	private PatientService patientService;

	@PreAuthorize("authenticated")
	@RequestMapping(value = "index")
	public String bookingHealthExamination() {
		return "booking_reexamination/front_booking_reexamination";
	}

	@RequestMapping(value = "item/load", method = RequestMethod.GET)
	@ResponseBody
	public JSONObject loadReexaminationItem() {
		return newInstance("all", reexaminationItemService.findAllAvailable());
	}

	//@PreAuthorize("authenticated")
	@RequestMapping(value = "add", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject addBookingReexamination(@RequestBody BookingReexamination booking, @CurrentUser User user) {
		Patient patient = patientService.findByMedicalCard(user.getUsername());
		booking.setMedicalCard(patient == null ? "" : patient.getMedicalCard());
		return newInstance("creat", bookingReexaminationService.create(booking).getId());
	}
}
