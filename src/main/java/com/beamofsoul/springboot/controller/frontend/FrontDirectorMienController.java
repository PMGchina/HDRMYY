package com.beamofsoul.springboot.controller.frontend;

import static com.beamofsoul.springboot.management.util.JSONUtils.newInstance;

import com.alibaba.fastjson.JSONObject;
import com.beamofsoul.springboot.controller.BaseAbstractController;
import com.beamofsoul.springboot.service.DirectorService;
import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/front/directorMien")
public class FrontDirectorMienController extends BaseAbstractController {

	@Resource
	private DirectorService directorService;

	@RequestMapping(value = "index")
	public String index() {
		return "director/front_director_mien";
	}

	@RequestMapping(value = "director", method = RequestMethod.GET)
	@ResponseBody
	public JSONObject loadDirectorMien() {
		return newInstance("all", directorService.findAll());
	}
}
