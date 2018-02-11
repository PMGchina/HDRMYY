package com.beamofsoul.springboot.controller.frontend;

import static com.beamofsoul.springboot.management.util.JSONUtils.newInstance;

import javax.annotation.Resource;

import com.alibaba.fastjson.JSONObject;
import com.beamofsoul.springboot.controller.BaseAbstractController;
import com.beamofsoul.springboot.service.HealthEducationService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/front/healthEducation")
public class FrontHealthEducationController extends BaseAbstractController {

   @Resource
   private HealthEducationService healthEducationService;

   @RequestMapping(value = "index")
   public String index() {
      return "health_education/front_health_education";
   }

   @RequestMapping(value = "load", method = RequestMethod.GET)
   @ResponseBody
   public JSONObject loadHealthEducation() {
      return newInstance("all", healthEducationService.findAllAvailable());
   }
}
