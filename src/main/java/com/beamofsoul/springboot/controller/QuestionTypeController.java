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
import com.beamofsoul.springboot.entity.QuestionType;
import com.beamofsoul.springboot.management.mvc.Attribute;
import com.beamofsoul.springboot.management.mvc.PostResponseBody;
import com.beamofsoul.springboot.management.util.CommonConvertUtils;
import com.beamofsoul.springboot.management.util.PageUtils;
import com.beamofsoul.springboot.service.QuestionTypeService;

@Controller
@RequestMapping("/admin/questionType")
public class QuestionTypeController extends BaseAbstractController {

	@Resource
	private QuestionTypeService questionTypeService;
	
	@PreAuthorize("authenticated and hasPermission('questionType','questionType:list')")
	@RequestMapping(value = "/adminList")
	public String adminList() {
		return "question_type/admin_question_type_list";
	}
	
	@PreAuthorize("authenticated and hasPermission('questionType','questionType:add')")
	@RequestMapping(value = "/singleAdd", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject addSingle(@RequestBody QuestionType questionType) {
		return newInstance("created",questionTypeService.create(questionType));
	}
	
	@PreAuthorize("authenticated and hasPermission('questionType','questionType:list')")
	@RequestMapping(value = "questionTypesByPage", method = RequestMethod.POST, produces = PRODUCES_APPLICATION_JSON)
	@ResponseBody
	public JSONObject getPageableQuestionTypes(@RequestBody Map<String, Object> map) {
		return newInstance(questionTypeService.findAll(PageUtils.parsePageable(map)));
	}
	
	@RequestMapping(value = "single", method = RequestMethod.POST, produces = PRODUCES_APPLICATION_JSON)
	@ResponseBody
	public JSONObject getSingleJSONObject(@RequestBody Map<String, Object> map) {
		return newInstance("obj",questionTypeService.findById(Long.valueOf(map.get("id").toString())));
	}
	
	@PreAuthorize("authenticated and hasPermission('questionType','questionType:update')")
	@RequestMapping(value = "singleUpdate", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject updateSingle(@RequestBody QuestionType questionType) {
		return newInstance("updated",questionTypeService.update(questionType));
	}
	
	@PreAuthorize("authenticated and hasPermission('questionType','questionType:delete')")
	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	@ResponseBody
	public JSONObject delete(@RequestBody String ids) {
		return newInstance("count",questionTypeService
				.delete(CommonConvertUtils.convertToLongArray(ids)));
	}
	
	@RequestMapping(value = "all", method = RequestMethod.GET, produces = PRODUCES_APPLICATION_JSON)
	@ResponseBody
	public JSONObject getAll() {
		return newInstance("all",questionTypeService.findAll());
	}
	
	@PostResponseBody("checkDeletedIds")
	public JSONObject checkDeletedIds(@Attribute Long... ids) {
		return newInstance("isPublished",questionTypeService.isPublished(ids));
	}
}
