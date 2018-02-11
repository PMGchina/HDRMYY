package com.beamofsoul.springboot.controller;

import static com.beamofsoul.springboot.management.util.JSONUtils.newInstance;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.beamofsoul.springboot.management.util.CommonConvertUtils;
import com.beamofsoul.springboot.management.util.PageUtils;
import com.beamofsoul.springboot.other.entity.BookingReexamination;
import com.beamofsoul.springboot.other.service.BookingReexaminationService;

@Controller
@RequestMapping("/admin/bookingReexamination")
public class BookingReexaminationController extends BaseAbstractController {

	@Resource
	private BookingReexaminationService bookingReexaminationService;
	
	@PreAuthorize("authenticated and hasPermission('bookingReexamination','bookingReexamination:list')")
	@RequestMapping(value = "/adminList")
	public String adminList() {
		return "booking_reexamination/admin_booking_reexamination_list";
	}
	
	@PreAuthorize("authenticated and hasPermission('bookingReexamination','bookingReexamination:add')")
	@RequestMapping(value = "/singleAdd", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject addSingle(@RequestBody BookingReexamination bookingReexamination) {
		return newInstance("created",bookingReexaminationService.create(bookingReexamination));
	}
	
	@PreAuthorize("authenticated and hasPermission('bookingReexamination','bookingReexamination:list')")
	@RequestMapping(value = "bookingReexaminationsByPage", method = RequestMethod.POST, produces = PRODUCES_APPLICATION_JSON)
	@ResponseBody
	public JSONObject getPageableBookingReexaminations(@RequestBody Map<String, Object> map) {
		Page<BookingReexamination> pageOfBookings = bookingReexaminationService.findAll(PageUtils.parsePageable(map));
		JSONObject json = newInstance(pageOfBookings);
		json.put("dtos", bookingReexaminationService.converTotBookingReexaminationDTO(pageOfBookings.getContent()));
		return json;
	}
	
	@RequestMapping(value = "single", method = RequestMethod.POST, produces = PRODUCES_APPLICATION_JSON)
	@ResponseBody
	public JSONObject getSingleJSONObject(@RequestBody Map<String, Object> map) {
		return newInstance("obj",bookingReexaminationService.findById(Long.valueOf(map.get("id").toString())));
	}
	
	@PreAuthorize("authenticated and hasPermission('bookingReexamination','bookingReexamination:update')")
	@RequestMapping(value = "singleUpdate", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject updateSingle(@RequestBody BookingReexamination bookingReexamination) {
		return newInstance("updated",bookingReexaminationService.update(bookingReexamination));
	}
	
	@PreAuthorize("authenticated and hasPermission('bookingReexamination','bookingReexamination:delete')")
	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	@ResponseBody
	public JSONObject delete(@RequestBody String ids) {
		return newInstance("count",bookingReexaminationService
				.delete(CommonConvertUtils.convertToLongArray(ids)));
	}
}
