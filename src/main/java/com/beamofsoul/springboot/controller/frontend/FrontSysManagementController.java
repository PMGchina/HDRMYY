package com.beamofsoul.springboot.controller.frontend;

import com.beamofsoul.springboot.controller.BaseAbstractController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/front/sysManagement")
public class FrontSysManagementController extends BaseAbstractController {

	@RequestMapping(value = "/menu")
	public String menu() {
		return "sysmanagement/front_sys_management";
	}
}
