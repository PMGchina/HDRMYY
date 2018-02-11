package com.beamofsoul.springboot.controller.frontend;

import static com.beamofsoul.springboot.management.util.JSONUtils.newInstance;

import javax.annotation.Resource;

import com.alibaba.fastjson.JSONObject;
import com.beamofsoul.springboot.controller.BaseAbstractController;
import com.beamofsoul.springboot.management.control.JSON;
import com.beamofsoul.springboot.other.entity.Department;
import com.beamofsoul.springboot.other.entity.Doctor;
import com.beamofsoul.springboot.other.service.DepartmentService;
import com.beamofsoul.springboot.other.service.DoctorService;
import com.beamofsoul.springboot.other.service.SchedulingService;
import java.util.Date;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/front/scheduling")
public class FrontSchedulingController extends BaseAbstractController {

	@Resource
	private SchedulingService schedulingService;

	@Resource
	private DoctorService doctorService;

	@Resource
	private DepartmentService departmentService;

	@RequestMapping(value = "index")
	public String index() {
		return "scheduling/front_scheduling";
	}

	@RequestMapping(value = "load", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject loadScheduling(Date date) {
		return newInstance("all", schedulingService.findAllByDate(date));
	}

	@RequestMapping(value = "doctor", method = RequestMethod.GET)
	@ResponseBody
	@JSON(include = "id,code,name,title,image,departmentCode,expert", type = Doctor.class)
	public JSONObject loadDoctor() {
		return newInstance("all", doctorService.findAllAvailable());
	}

	@RequestMapping(value = "department", method = RequestMethod.GET)
	@ResponseBody
	@JSON(include = "code,name", type = Department.class)
	public JSONObject loadDepartment() {
		return newInstance("all", departmentService.findAllAvailable());
	}
}
