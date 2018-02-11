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
import com.beamofsoul.springboot.entity.Question;
import com.beamofsoul.springboot.management.mvc.Attribute;
import com.beamofsoul.springboot.management.mvc.PostResponseBody;
import com.beamofsoul.springboot.management.util.CommonConvertUtils;
import com.beamofsoul.springboot.management.util.PageUtils;
import com.beamofsoul.springboot.service.QuestionService;

@Controller
@RequestMapping("/admin/question")
public class QuestionController extends BaseAbstractController {

	@Resource
	private QuestionService questionService;
	
	@PreAuthorize("authenticated and hasPermission('question','question:list')")
	@RequestMapping(value = "/adminList")
	public String adminList() {
		return "question/admin_question_list";
	}
	
	@PreAuthorize("authenticated and hasPermission('question','question:add')")
	@RequestMapping(value = "/singleAdd", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject addSingle(@RequestBody Question question) {
		return newInstance("created",questionService.create(question));
	}
	
	@PreAuthorize("authenticated and hasPermission('question','question:list')")
	@RequestMapping(value = "questionsByPage", method = RequestMethod.POST, produces = PRODUCES_APPLICATION_JSON)
	@ResponseBody
	public JSONObject getPageableQuestions(@RequestBody Map<String, Object> map) {
		return newInstance(questionService.findAll(PageUtils.parsePageable(map)));
	}
	
	@RequestMapping(value = "questionsByQuestionnaire", method = RequestMethod.POST, produces = PRODUCES_APPLICATION_JSON)
	@ResponseBody
	public JSONObject getQuestionsUnderQuestionnaire(@Attribute Long questionnaireId) {
		return newInstance(questionService.findAll(questionnaireId));
	}
	
	@RequestMapping(value = "single", method = RequestMethod.POST, produces = PRODUCES_APPLICATION_JSON)
	@ResponseBody
	public JSONObject getSingleJSONObject(@RequestBody Map<String, Object> map) {
		return newInstance("obj",questionService.findById(Long.valueOf(map.get("id").toString())));
	}
	
	@PreAuthorize("authenticated and hasPermission('question','question:update')")
	@RequestMapping(value = "singleUpdate", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject updateSingle(@RequestBody Question question) {
		return newInstance("updated",questionService.update(question));
	}
	
	@PreAuthorize("authenticated and hasPermission('question','question:delete')")
	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	@ResponseBody
	public JSONObject delete(@RequestBody String ids) {
		return newInstance("count",questionService
				.delete(CommonConvertUtils.convertToLongArray(ids)));
	}
	
	@PostResponseBody("checkDeletedIds")
	public JSONObject checkDeletedIds(@Attribute Long... ids) {
		return newInstance("isPublished",questionService.isPublished(ids));
	}
}
