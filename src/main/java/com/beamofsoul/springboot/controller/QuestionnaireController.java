package com.beamofsoul.springboot.controller;

import static com.beamofsoul.springboot.management.util.JSONUtils.newInstance;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.websocket.server.PathParam;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.beamofsoul.springboot.entity.Question;
import com.beamofsoul.springboot.entity.Questionnaire;
import com.beamofsoul.springboot.entity.User;
import com.beamofsoul.springboot.management.mvc.Attribute;
import com.beamofsoul.springboot.management.mvc.CurrentUser;
import com.beamofsoul.springboot.management.mvc.GetResponseBody;
import com.beamofsoul.springboot.management.mvc.PostResponseBody;
import com.beamofsoul.springboot.management.util.CommonConvertUtils;
import com.beamofsoul.springboot.management.util.PageUtils;
import com.beamofsoul.springboot.service.QuestionService;
import com.beamofsoul.springboot.service.QuestionnaireService;

@Controller
@RequestMapping("/admin/questionnaire")
public class QuestionnaireController extends BaseAbstractController {

	@Resource
	private QuestionnaireService questionnaireService;
	
	@Resource
	private QuestionService questionService;
	
	@PreAuthorize("authenticated and hasPermission('questionnaire','questionnaire:list')")
	@RequestMapping(value = "/adminList")
	public String adminList() {
		return "questionnaire/admin_questionnaire_list";
	}
	
	@PreAuthorize("authenticated and hasPermission('questionnaire','questionnaire:add')")
	@RequestMapping(value = "/singleAdd", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject addSingle(@RequestBody Questionnaire questionnaire) {
		return newInstance("created",questionnaireService.create(questionnaire));
	}
	
	@RequestMapping(value = "/addWhole", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject addWhole(@Attribute String name, @Attribute String description, @Attribute String questions, @CurrentUser User user) {
		return newInstance("created",questionnaireService.create(name,description,questions,user));
	}
	
	@PreAuthorize("authenticated and hasPermission('questionnaire','questionnaire:list')")
	@RequestMapping(value = "questionnairesByPage", method = RequestMethod.POST, produces = PRODUCES_APPLICATION_JSON)
	@ResponseBody
	public JSONObject getPageableQuestionnaires(@RequestBody Map<String, Object> map) {
		return newInstance(questionnaireService.findAll(PageUtils.parsePageable(map)));
	}
	
	@RequestMapping(value = "single", method = RequestMethod.POST, produces = PRODUCES_APPLICATION_JSON)
	@ResponseBody
	public JSONObject getSingleJSONObject(@RequestBody Map<String, Object> map) {
		return newInstance("obj",questionnaireService.findById(Long.valueOf(map.get("id").toString())));
	}
	
	@PreAuthorize("authenticated and hasPermission('questionnaire','questionnaire:update')")
	@RequestMapping(value = "singleUpdate", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject updateSingle(@RequestBody Questionnaire questionnaire) {
		return newInstance("updated",questionnaireService.update(questionnaire));
	}
	
	@PreAuthorize("authenticated and hasPermission('questionnaire','questionnaire:delete')")
	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	@ResponseBody
	public JSONObject delete(@RequestBody String ids) {
		return newInstance("count",questionnaireService.delete(CommonConvertUtils.convertToLongArray(ids)));
	}
	
	@GetResponseBody("allUnpublished")
	public JSONObject allUnpublished() {
		return newInstance("all",questionnaireService.findAllUnpublished());
	}
	
	@PostResponseBody("publish")
	public JSONObject publish(@Attribute Long... ids) {
		return newInstance("count",questionnaireService.publish(ids));
	}
	
	@PostResponseBody("unpublish")
	public JSONObject unpublish(@Attribute Long... ids) {
		return newInstance("count",questionnaireService.unpublish(ids));
	}
	
	@PostResponseBody("available")
	public JSONObject available(@Attribute Long... ids) {
		return newInstance("count",questionnaireService.available(ids));
	}
	
	@PostResponseBody("unavailable")
	public JSONObject unavailable(@Attribute Long... ids) {
		return newInstance("count",questionnaireService.unavailable(ids));
	}
	
	@GetResponseBody("analysis")
	public JSONObject analysis(@PathParam(value = "id") Long id) {
		Questionnaire questionnaire = questionnaireService.findById(id);
		JSONObject jsonObject = newInstance("questionnaire", questionnaire);
		List<Question> questions = questionService.findAll(id);
		jsonObject.put("questions", questions);
		jsonObject.put("pieChartData", questionService.getChartData(questions,questionnaire));
		return jsonObject;
	}
	
	@PostResponseBody("checkDeletedIds")
	public JSONObject checkDeletedIds(@Attribute Long... ids) {
		return newInstance("isPublished",questionnaireService.isPublished(ids));
	}
	
	@PostResponseBody("checkUpdatedIds")
	public JSONObject checkUpdatedIds(@Attribute Long id) {
		return newInstance("isAnswered",questionnaireService.isAnswered(id));
	}
}
