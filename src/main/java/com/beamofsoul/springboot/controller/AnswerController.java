package com.beamofsoul.springboot.controller;

import static com.beamofsoul.springboot.management.util.JSONUtils.newInstance;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.beamofsoul.springboot.entity.Answer;
import com.beamofsoul.springboot.management.mvc.Attribute;
import com.beamofsoul.springboot.management.mvc.CurrentUserId;
import com.beamofsoul.springboot.management.util.CommonConvertUtils;
import com.beamofsoul.springboot.management.util.PageUtils;
import com.beamofsoul.springboot.service.AnswerService;

@Controller
@RequestMapping("/admin/answer")
public class AnswerController extends BaseAbstractController {

	@Resource
	private AnswerService answerService;

	@PreAuthorize("authenticated and hasPermission('answer','answer:list')")
	@RequestMapping(value = "/adminList")
	public String adminList() {
		return "answer/admin_answer_list";
	}

	@PreAuthorize("authenticated and hasPermission('answer','answer:add')")
	@RequestMapping(value = "/singleAdd", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject addSingle(@RequestBody Answer answer) {
		return newInstance("created", answerService.create(answer));
	}

	@PreAuthorize("authenticated and hasPermission('answer','answer:list')")
	@RequestMapping(value = "answersByPage", method = RequestMethod.POST, produces = PRODUCES_APPLICATION_JSON)
	@ResponseBody
	public JSONObject getPageableAnswers(@RequestBody Map<String, Object> map) {
		return newInstance(answerService.findAll(PageUtils.parsePageable(map)));
	}

	@RequestMapping(value = "single", method = RequestMethod.POST, produces = PRODUCES_APPLICATION_JSON)
	@ResponseBody
	public JSONObject getSingleJSONObject(@RequestBody Map<String, Object> map) {
		return newInstance("obj", answerService.findById(Long.valueOf(map.get("id").toString())));
	}

	@PreAuthorize("authenticated and hasPermission('answer','answer:update')")
	@RequestMapping(value = "singleUpdate", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject updateSingle(@RequestBody Answer answer) {
		return newInstance("updated", answerService.update(answer));
	}

	@PreAuthorize("authenticated and hasPermission('answer','answer:delete')")
	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	@ResponseBody
	public JSONObject delete(@RequestBody String ids) {
		return newInstance("count", answerService
				.delete(CommonConvertUtils.convertToLongArray(ids)));
	}

	@RequestMapping(value = "/addWhole", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject addWhole(
			@Attribute("questionnaireId") Long questionnaireId,
			@Attribute("answers") String answers,
			@Attribute("departmentName") String departmentName,
			@CurrentUserId Long userId) {
		return newInstance("created", answerService.create(answers, questionnaireId, departmentName, userId));
	}
}
