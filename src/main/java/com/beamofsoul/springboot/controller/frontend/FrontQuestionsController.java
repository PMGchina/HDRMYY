package com.beamofsoul.springboot.controller.frontend;

import static com.beamofsoul.springboot.management.util.JSONUtils.newInstance;

import java.util.List;

import javax.annotation.Resource;
import javax.websocket.server.PathParam;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.beamofsoul.springboot.controller.BaseAbstractController;
import com.beamofsoul.springboot.entity.Question;
import com.beamofsoul.springboot.entity.Questionnaire;
import com.beamofsoul.springboot.entity.User;
import com.beamofsoul.springboot.management.control.JSON;
import com.beamofsoul.springboot.management.mvc.Attribute;
import com.beamofsoul.springboot.management.mvc.CurrentUser;
import com.beamofsoul.springboot.management.mvc.CurrentUserId;
import com.beamofsoul.springboot.other.entity.Department;
import com.beamofsoul.springboot.other.service.DepartmentService;
import com.beamofsoul.springboot.service.AnswerService;
import com.beamofsoul.springboot.service.QuestionService;
import com.beamofsoul.springboot.service.QuestionTypeService;
import com.beamofsoul.springboot.service.QuestionnaireService;
import org.springframework.security.access.prepost.PreAuthorize;

@Controller
@RequestMapping("/front/questions")
public class FrontQuestionsController extends BaseAbstractController {

	@Resource
	private QuestionnaireService questionnaireService;

	@Resource
	private QuestionService questionService;

	@Resource
	private QuestionTypeService questionTypeService;

	@Resource
	private AnswerService answerService;

	@Resource
	private DepartmentService departmentService;

	//@PreAuthorize("authenticated and hasPermission('question','question:list')")
	@RequestMapping(value = "edit")
	public String editQuestions() {
		return "question/front_question_edit";
	}

	//@PreAuthorize("authenticated and hasPermission('answer','answer:list')")
	@RequestMapping(value = "answer")
	public String answerQuestions() {
		return "question/front_question_answer";
	}

	//@PreAuthorize("authenticated and hasPermission('answer','answer:list')")
	@RequestMapping(value = "analysis")
	public String analysisQuestions() {
		return "question/front_question_analysis";
	}

	//@PreAuthorize("authenticated and hasPermission('answer','answer:list')")
	@RequestMapping(value = "publish")
	public String publishQuestions() {
		return "question/front_question_publish";
	}

	@RequestMapping(value = "questionType", method = RequestMethod.GET)
	@ResponseBody
	public JSONObject getQuestionType() {
		return newInstance("all", questionTypeService.findAll());
	}

	@PreAuthorize("authenticated and hasPermission('questionnaire','questionnaire:add')")
	@RequestMapping(value = "questionnaireEdit", method = RequestMethod.GET)
	@ResponseBody
	public JSONObject getQuestionnaireForEdit() {
		return newInstance("all", questionnaireService.findAllUnpublishedAndAvailable());
	}

	@PreAuthorize("authenticated and hasPermission('questionnaire','questionnaire:add')")
	@RequestMapping(value = "questionnairePublish", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject publishQuestionnaire(@Attribute Long id) {
		return newInstance("count", questionnaireService.publish(id));
	}

	@RequestMapping(value = "questionnaireAnswer", method = RequestMethod.GET)
	@ResponseBody
	public JSONObject getQuestionnaireForAnswer() {
		return newInstance("all", questionnaireService.findAllPublishedAndAvailable());
	}

	@PreAuthorize("authenticated and hasPermission('questionnaire','questionnaire:add')")
	@RequestMapping(value = "/addWhole", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject addWhole(@Attribute String name, @Attribute String description, @Attribute String questions, @CurrentUser User user) {
		return newInstance("created", questionnaireService.create(name, description, questions, user));
	}

	@PreAuthorize("authenticated and hasPermission('questionnaire','questionnaire:add')")
	@RequestMapping(value = "updateWhole", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject updateWhole(@Attribute Long id, @Attribute String name, @Attribute String description, @Attribute String questions) {
		return newInstance("update", questionnaireService.update(id, name, description, questions));
	}

	@PreAuthorize("authenticated")	//检查是否登录状态
	@RequestMapping(value = "questionLoad", method = RequestMethod.POST)
	//@PostResponseBody("questionLoad")
	@ResponseBody
	public JSONObject loadQuestions(@Attribute Long questionnaireId) {
		return newInstance("load", questionService.findAll(questionnaireId));
	}

	//@PreAuthorize("authenticated and hasPermission('question','question:delete')")
	@RequestMapping(value = "questionDelete", method = RequestMethod.DELETE)
	@ResponseBody
	public JSONObject deleteQuestionsById(@Attribute Long questionId) {
		return newInstance("count", questionService.delete(questionId));
	}

	//@PreAuthorize("authenticated and hasPermission('answer','answer:list')")
	@RequestMapping(value = "answerAdd", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject addAnswer(@Attribute String answers, @Attribute Long questionnaireId,
			@Attribute String departmentName, @CurrentUserId Long userId) {
		return newInstance("count", answerService.create(answers, questionnaireId, departmentName, userId));
	}

	@RequestMapping(value = "answerCheck", method = RequestMethod.POST)
	//@PostResponseBody("answerCheck")
	public JSONObject checkAnswered(
			@Attribute Long questionnaireId,
			@Attribute String departmentName,
			@CurrentUserId Long userId) {
		return newInstance("check", answerService.checkAnswered(questionnaireId, departmentName, userId));
	}

	@RequestMapping(value = "analysisLoad", method = RequestMethod.GET)
	@ResponseBody
	public JSONObject loadAnalysis(@PathParam(value = "id") Long id) {
		Questionnaire questionnaire = questionnaireService.findById(id);
		JSONObject jo = newInstance("questionnaire", questionnaire);
		List<Question> questions = questionService.findAll(id);
		jo.put("questions", questions);
		jo.put("pieChartData", questionService.getChartData(questions, questionnaire));
		return jo;
	}

	@RequestMapping(value = "answerDepartment", method = RequestMethod.POST)
	@ResponseBody
	@JSON(include = "id,name", type = Department.class)
	public JSONObject loadAlldDepartment() {
		return newInstance("all", departmentService.findAllAvailable());
	}

	//	@RequestMapping(value = "load", method = RequestMethod.POST)
	//	@ResponseBody
	//	public JSONObject getQuestionsUnderQuestionnaire(@RequestBody JSONObject json) {
	//		return newInstance("load", questionService.findAll(json.getLong("questionnaireId")));
	//	}
}
