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
import com.beamofsoul.springboot.other.entity.BookingHealthExamination;
import com.beamofsoul.springboot.other.service.BookingHealthExaminationService;
import com.beamofsoul.springboot.other.service.HealthExaminationPackageService;

@Controller
@RequestMapping("/admin/bookingHealthExamination")
public class BookingHealthExaminationController extends BaseAbstractController {

   @Resource
   private BookingHealthExaminationService bookingHealthExaminationService;

   @Resource
   private HealthExaminationPackageService healthExaminationPackageService;

   @PreAuthorize("authenticated and hasPermission('bookingHealthExamination','bookingHealthExamination:list')")
   @RequestMapping(value = "/adminList")
   public String adminList() {
      return "booking_health_examination/admin_booking_health_examination_list";
   }

   @PreAuthorize("authenticated and hasPermission('bookingHealthExamination','bookingHealthExamination:add')")
   @RequestMapping(value = "/singleAdd", method = RequestMethod.POST)
   @ResponseBody
   public JSONObject addSingle(@RequestBody BookingHealthExamination bookingHealthExamination) {
      return newInstance("created", bookingHealthExaminationService.create(bookingHealthExamination));
   }

   @PreAuthorize("authenticated and hasPermission('bookingHealthExamination','bookingHealthExamination:list')")
   @RequestMapping(value = "bookingHealthExaminationsByPage", method = RequestMethod.POST, produces = PRODUCES_APPLICATION_JSON)
   @ResponseBody
   public JSONObject getPageableBookingHealthExaminations(@RequestBody Map<String, Object> map) {
      Page<BookingHealthExamination> pageOfBookings = bookingHealthExaminationService.findAll(PageUtils.parsePageable(map));
      JSONObject json = newInstance(pageOfBookings);
      json.put("dtos", bookingHealthExaminationService.converTotBookingHealthExaminationDTO(pageOfBookings.getContent()));
      return json;
   }

   @RequestMapping(value = "single", method = RequestMethod.POST, produces = PRODUCES_APPLICATION_JSON)
   @ResponseBody
   public JSONObject getSingleJSONObject(@RequestBody Map<String, Object> map) {
      return newInstance("obj", bookingHealthExaminationService.findById(Long.valueOf(map.get("id").toString())));
   }

   @PreAuthorize("authenticated and hasPermission('bookingHealthExamination','bookingHealthExamination:update')")
   @RequestMapping(value = "singleUpdate", method = RequestMethod.POST)
   @ResponseBody
   public JSONObject updateSingle(@RequestBody BookingHealthExamination bookingHealthExamination) {
      return newInstance("updated", bookingHealthExaminationService.update(bookingHealthExamination));
   }

   @PreAuthorize("authenticated and hasPermission('bookingHealthExamination','bookingHealthExamination:delete')")
   @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
   @ResponseBody
   public JSONObject delete(@RequestBody String ids) {
      return newInstance("count", bookingHealthExaminationService
            .delete(CommonConvertUtils.convertToLongArray(ids)));
   }
}
