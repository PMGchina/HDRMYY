package com.beamofsoul.springboot.controller.frontend;

import static com.beamofsoul.springboot.management.util.JSONUtils.newInstance;

import javax.annotation.Resource;

import com.alibaba.fastjson.JSONObject;
import com.beamofsoul.springboot.controller.BaseAbstractController;
import com.beamofsoul.springboot.service.DepartmentConstructionService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/front/departmentConstruction")
public class FrontDepartmentConstructionController extends BaseAbstractController {

	@Resource
	private DepartmentConstructionService departmentConstructionService;

	@RequestMapping(value = "index")
	public String index() {
		return "department_construction/front_department_construction";
	}

	@RequestMapping(value = "departmentConstruction", method = RequestMethod.GET)
	@ResponseBody
	public JSONObject loadDepartmentConstruction() {
		return newInstance("all", departmentConstructionService.findAll());
	}
}
