package com.beamofsoul.springboot.controller;

import static com.beamofsoul.springboot.management.util.JSONUtils.newInstance;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.beamofsoul.springboot.management.util.PageUtils;
import com.beamofsoul.springboot.other.entity.BookingRegistration;
import com.beamofsoul.springboot.other.service.BookingRegistrationService;

@Controller
@RequestMapping("/admin/bookingRegistration")
public class BookingRegistrationController extends BaseAbstractController {

   @Resource
   private BookingRegistrationService bookingRegistrationService;

   @PreAuthorize("authenticated and hasPermission('bookingRegistration','bookingRegistration:list')")
   @RequestMapping(value = "/adminList")
   public String adminList() {
      return "booking_registration/admin_booking_registration_list";
   }

   @PreAuthorize("authenticated and hasPermission('bookingRegistration','bookingRegistration:add')")
   @RequestMapping(value = "/singleAdd", method = RequestMethod.POST)
   @ResponseBody
   public JSONObject addSingle(@RequestBody BookingRegistration bookingRegistration) {
      return newInstance("created", bookingRegistrationService.create(bookingRegistration));
   }

   @PreAuthorize("authenticated and hasPermission('bookingRegistration','bookingRegistration:list')")
   @RequestMapping(value = "bookingRegistrationsByPage", method = RequestMethod.POST, produces = PRODUCES_APPLICATION_JSON)
   @ResponseBody
   public JSONObject getPageableBookingRegistrations(@RequestBody Map<String, Object> map) {
      return newInstance(bookingRegistrationService.findAll(PageUtils.parsePageable(map)));
   }

   @RequestMapping(value = "single", method = RequestMethod.POST, produces = PRODUCES_APPLICATION_JSON)
   @ResponseBody
   public JSONObject getSingleJSONObject(@RequestBody Map<String, Object> map) {
      return newInstance("obj", bookingRegistrationService.findById(Long.valueOf(map.get("id").toString())));
   }
}
