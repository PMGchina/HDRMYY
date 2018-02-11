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
import com.beamofsoul.springboot.entity.QuestionnaireUser;
import com.beamofsoul.springboot.management.util.CommonConvertUtils;
import com.beamofsoul.springboot.management.util.PageUtils;
import com.beamofsoul.springboot.service.QuestionnaireUserService;

@Controller
@RequestMapping("/admin/questionnaireUser")
public class QuestionnaireUserController extends BaseAbstractController {

	@Resource
	private QuestionnaireUserService questionnaireUserService;
	
	@PreAuthorize("authenticated and hasPermission('questionnaireUser','questionnaireUser:list')")
	@RequestMapping(value = "/adminList")
	public String adminList() {
		return "questionnaire_user/admin_questionnaire_user_list";
	}
	
	@PreAuthorize("authenticated and hasPermission('questionnaireUser','questionnaireUser:add')")
	@RequestMapping(value = "/singleAdd", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject addSingle(@RequestBody QuestionnaireUser questionnaireUser) {
		return newInstance("created",questionnaireUserService.create(questionnaireUser));
	}
	
	@PreAuthorize("authenticated and hasPermission('questionnaireUser','questionnaireUser:list')")
	@RequestMapping(value = "questionnaireUsersByPage", method = RequestMethod.POST, produces = PRODUCES_APPLICATION_JSON)
	@ResponseBody
	public JSONObject getPageableQuestionnaireUsers(@RequestBody Map<String, Object> map) {
		return newInstance(questionnaireUserService.findAll(PageUtils.parsePageable(map)));
	}
	
	@RequestMapping(value = "single", method = RequestMethod.POST, produces = PRODUCES_APPLICATION_JSON)
	@ResponseBody
	public JSONObject getSingleJSONObject(@RequestBody Map<String, Object> map) {
		return newInstance("obj",questionnaireUserService.findById(Long.valueOf(map.get("id").toString())));
	}
	
	@PreAuthorize("authenticated and hasPermission('questionnaireUser','questionnaireUser:update')")
	@RequestMapping(value = "singleUpdate", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject updateSingle(@RequestBody QuestionnaireUser questionnaireUser) {
		return newInstance("updated",questionnaireUserService.update(questionnaireUser));
	}
	
	@PreAuthorize("authenticated and hasPermission('questionnaireUser','questionnaireUser:delete')")
	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	@ResponseBody
	public JSONObject delete(@RequestBody String ids) {
		return newInstance("count",questionnaireUserService
				.delete(CommonConvertUtils.convertToLongArray(ids)));
	}
}
