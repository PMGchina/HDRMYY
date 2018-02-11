package com.beamofsoul.springboot.controller.frontend;

import static com.beamofsoul.springboot.management.util.JSONUtils.newInstance;

import java.util.Map;
import javax.annotation.Resource;
import javax.websocket.server.PathParam;

import com.alibaba.fastjson.JSONObject;
import com.beamofsoul.springboot.controller.BaseAbstractController;
import com.beamofsoul.springboot.entity.User;
import com.beamofsoul.springboot.management.control.JSON;
import com.beamofsoul.springboot.management.mvc.Attribute;
import com.beamofsoul.springboot.management.mvc.CurrentUser;
import com.beamofsoul.springboot.other.entity.Department;
import com.beamofsoul.springboot.other.entity.Doctor;
import com.beamofsoul.springboot.other.entity.HealthExaminationItem;
import com.beamofsoul.springboot.other.entity.HealthExaminationPackage;
import com.beamofsoul.springboot.other.entity.ReexaminationItem;
import com.beamofsoul.springboot.other.service.BookingHealthExaminationService;
import com.beamofsoul.springboot.other.service.BookingReexaminationService;
import com.beamofsoul.springboot.other.service.BookingRegistrationService;
import com.beamofsoul.springboot.other.service.DepartmentService;
import com.beamofsoul.springboot.other.service.DoctorService;
import com.beamofsoul.springboot.other.service.HealthExaminationItemService;
import com.beamofsoul.springboot.other.service.HealthExaminationPackageService;
import com.beamofsoul.springboot.other.service.ReexaminationItemService;
import java.text.ParseException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/front/bookingAllMessage")
public class FrontBookingAllMessageController extends BaseAbstractController {

	@Resource
	private BookingRegistrationService bookingRegistrationService;
	@Resource
	private DepartmentService departmentService;
	@Resource
	private DoctorService doctorService;

	@Resource
	private BookingHealthExaminationService bookingHealthExaminationService;
	@Resource
	private HealthExaminationPackageService healthExaminationPackageService;
	@Resource
	private HealthExaminationItemService healthExaminationItemService;

	@Resource
	private BookingReexaminationService bookingReexaminationService;
	@Resource
	private ReexaminationItemService reexaminationItemService;

	@PreAuthorize("authenticated")
	@RequestMapping(value = "index")
	public String index() throws ParseException {
		return "booking_all_message/front_booking_all_message";
	}

	/**
	 * @Description 预约挂号的预约信息
	 * @param user
	 * @return
	 */
	@PreAuthorize("authenticated")
	@RequestMapping(value = "registration/UnProcessed", method = RequestMethod.GET)
	@ResponseBody
	public JSONObject loadRegistrationAvailableUnprocessed(@CurrentUser User user) {
		return newInstance("all", bookingRegistrationService.findAllAvailableAndUnprocessed(user.getUsername()));
	}

//	@PreAuthorize("authenticated")
//	@RequestMapping(value = "registration/Processed", method = RequestMethod.GET)
//	@ResponseBody
//	public JSONObject loadRegistrationAvailableProcessed(@CurrentUser User user) {
//		return newInstance("all", bookingRegistrationService.findAllAvailableAndProcessed(user.getUsername()));
//	}
	@PreAuthorize("authenticated")
	@RequestMapping(value = "registration/Canceled", method = RequestMethod.GET)
	@ResponseBody
	public JSONObject loadRegistrationUnavailableUnprocessed(@CurrentUser User user) {
		return newInstance("all", bookingRegistrationService.findAllUnavailableAndUnprocessed(user.getUsername()));
	}

	@RequestMapping(value = "registration/department", method = RequestMethod.POST)
	@ResponseBody
	@JSON(include = "code,name", type = Department.class)
	public JSONObject loadDepartment(@RequestBody Map<String, String[]> map) {
		return newInstance("department", departmentService.findByCodes(map.get("codes")));
	}

	@RequestMapping(value = "registration/doctor", method = RequestMethod.POST)
	@ResponseBody
	@JSON(include = "code,name,title", type = Doctor.class)
	public JSONObject loadDoctor(@RequestBody Map<String, String[]> map) {
		return newInstance("doctor", doctorService.findByCodes(map.get("codes")));
	}

	@RequestMapping(value = "registration/cancelRecover", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject cancelRegistration(@Attribute Long id, @Attribute Boolean available) {
		return newInstance("count", bookingRegistrationService.updateAvailable(id, available));
	}

	/**
	 * @Description 定期复查的预约信息
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "reexamination/UnProcessed", method = RequestMethod.GET)
	@ResponseBody
	public JSONObject loadReexaminationAvailableUnprocessed(@CurrentUser User user) {
		return newInstance("all", bookingReexaminationService.findAllAvailableAndUnprocessed(user.getUsername()));
	}

//	@RequestMapping(value = "reexamination/Processed", method = RequestMethod.GET)
//	@ResponseBody
//	public JSONObject loadReexaminationAvailableProcessed(@CurrentUser User user) {
//		return newInstance("all", bookingReexaminationService.findAllAvailableAndProcessed(user.getUsername()));
//	}
	@RequestMapping(value = "reexamination/Canceled", method = RequestMethod.GET)
	@ResponseBody
	public JSONObject loadReexaminationUnavailableUnprocessed(@CurrentUser User user) {
		return newInstance("all", bookingReexaminationService.findAllUnavailableAndUnprocessed(user.getUsername()));
	}

	@RequestMapping(value = "reexamination/item", method = RequestMethod.POST)
	@ResponseBody
	@JSON(include = "id,code,name", type = ReexaminationItem.class)
	public JSONObject loadReexaminationItem(@RequestBody Map<String, Long[]> map) {
		return newInstance("item", reexaminationItemService.findByIds(map.get("ids")));
	}

	@RequestMapping(value = "reexamination/cancelRecover", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject cancelReexaminationItem(@Attribute Long id, @Attribute Boolean available) {
		return newInstance("count", bookingReexaminationService.updateAvailable(id, available));
	}

	/**
	 * @Description 体检项目的预约信息
	 * @param identity 需前台输入
	 * @return
	 */
	@RequestMapping(value = "healthExamination/UnProcessed", method = RequestMethod.GET)
	@ResponseBody
	public JSONObject loadHealthExaminationAvailableUnprocessed(@PathParam(value = "identity") String identity) {
		System.out.println("identity===" + identity);

		return newInstance("all", bookingHealthExaminationService.findAllAvailableAndUnprocessed(identity));
	}

//	@RequestMapping(value = "healthExamination/Processed", method = RequestMethod.GET)
//	@ResponseBody
//	public JSONObject loadHealthExaminationAvailableProcessed(@PathParam(value = "identity") String identity) {
//		return newInstance("all", bookingHealthExaminationService.findAllAvailableAndProcessed(identity));
//	}
	@RequestMapping(value = "healthExamination/Canceled", method = RequestMethod.GET)
	@ResponseBody
	public JSONObject loadHealthExaminationUnavailableUnprocessed(@PathParam(value = "identity") String identity) {
		return newInstance("all", bookingHealthExaminationService.findAllUnavailableAndUnprocessed(identity));
	}

	@RequestMapping(value = "healthExamination/package", method = RequestMethod.POST)
	@ResponseBody
	@JSON(include = "id,name", type = HealthExaminationPackage.class)
	public JSONObject loadHealthExaminationPackage(@RequestBody Map<String, Long[]> map) {
		return newInstance("package", healthExaminationPackageService.findByIds(map.get("ids")));
	}

	@RequestMapping(value = "healthExamination/item", method = RequestMethod.POST)
	@ResponseBody
	@JSON(include = "id,name", type = HealthExaminationItem.class)
	public JSONObject loadHealthExaminationItem(@RequestBody Map<String, Long[]> map) {
		return newInstance("item", healthExaminationItemService.findByIds(map.get("ids")));
	}
}
