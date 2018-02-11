package com.beamofsoul.springboot.controller.frontend;

import static com.beamofsoul.springboot.management.util.JSONUtils.newInstance;

import javax.annotation.Resource;

import com.alibaba.fastjson.JSONObject;
import com.beamofsoul.springboot.controller.BaseAbstractController;
import com.beamofsoul.springboot.entity.MessageBoard;
import com.beamofsoul.springboot.entity.User;
import com.beamofsoul.springboot.management.mvc.CurrentUser;
import com.beamofsoul.springboot.service.MessageBoardService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/front/messageBoard")
public class FrontMessageBoardController extends BaseAbstractController {

	@Resource
	private MessageBoardService messageBoardService;

	@RequestMapping(value = "index")
	public String index() {
		return "message_board/front_message_board";
	}

	@RequestMapping(value = "load", method = RequestMethod.GET)
	@ResponseBody
	public JSONObject loadMessageBoard() {
		return newInstance("all", messageBoardService.findAll());
	}

	@RequestMapping(value = "add", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject addMessageBoard(@RequestBody MessageBoard messageBoard, @CurrentUser User user) {
		if (user != null) {
			messageBoard.setMedicalCard(user.getUsername());
		}
		return newInstance("creat", messageBoardService.create(messageBoard).getId());
	}
}


