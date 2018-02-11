package com.beamofsoul.springboot.controller.frontend;

import com.alibaba.fastjson.JSONObject;
import com.beamofsoul.springboot.controller.BaseAbstractController;
import com.beamofsoul.springboot.entity.User;
import com.beamofsoul.springboot.management.mvc.CurrentUser;
import com.beamofsoul.springboot.other.service.CheckReportRequestService;
import com.beamofsoul.springboot.other.service.CheckReportService;
import java.util.Date;
import javax.annotation.Resource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import static com.beamofsoul.springboot.management.util.JSONUtils.newInstance;
import java.util.Map;

@Controller
@RequestMapping("/front/checkReport")
public class FrontCheckReportController extends BaseAbstractController {

	@Resource
	private CheckReportService checkReportService;
	@Resource
	private CheckReportRequestService checkReportRequestService;

	@PreAuthorize("authenticated")
	@RequestMapping(value = "index")
	public String index() {
		return "checkreport/front_checkreport";
	}

	@PreAuthorize("authenticated")
	@RequestMapping(value = "search", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject searchCheckReportByFromOneDay(@RequestBody Map<String, Date> map, @CurrentUser User user) {
		return newInstance("search", checkReportService.findByMedicalCardAndOneday(map.get("oneday"), user.getUsername()));
	}

	@PreAuthorize("authenticated")
	@RequestMapping(value = "requestsend", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject requestSend(@CurrentUser User user) {
		if (user != null) {
			return newInstance("id", checkReportRequestService.updateByMedicalCard(user.getUsername()).getId());
		} else {
			return newInstance("id", 0);
		}
	}
}
