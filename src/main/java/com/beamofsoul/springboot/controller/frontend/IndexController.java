package com.beamofsoul.springboot.controller.frontend;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/front")
public class IndexController {

	@RequestMapping(value = "/index")
	public String index() {
		return "front_index";
	}
//	@GetMapping(value = "index")
//	public ModelAndView index() {
//		return new ModelAndView("front_index");
//	}
}
