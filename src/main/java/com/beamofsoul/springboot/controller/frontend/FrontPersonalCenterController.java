package com.beamofsoul.springboot.controller.frontend;

import com.beamofsoul.springboot.controller.BaseAbstractController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/front/personalCenter")
public class FrontPersonalCenterController extends BaseAbstractController {

	@RequestMapping(value = "/menu")
	public String menu() {
		return "user/front_personal_center";
	}
}
