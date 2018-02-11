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
import com.beamofsoul.springboot.entity.MessageBoard;
import com.beamofsoul.springboot.management.util.CommonConvertUtils;
import com.beamofsoul.springboot.management.util.PageUtils;
import com.beamofsoul.springboot.service.MessageBoardService;

@Controller
@RequestMapping("/admin/messageBoard")
public class MessageBoardController extends BaseAbstractController {

	@Resource
	private MessageBoardService messageBoardService;
	
	@PreAuthorize("authenticated and hasPermission('messageBoard','messageBoard:list')")
	@RequestMapping(value = "/adminList")
	public String adminList() {
		return "message_board/admin_message_board_list";
	}
	
	@PreAuthorize("authenticated and hasPermission('messageBoard','messageBoard:add')")
	@RequestMapping(value = "/singleAdd", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject addSingle(@RequestBody MessageBoard messageBoard) {
		return newInstance("created",messageBoardService.create(messageBoard));
	}
	
	@PreAuthorize("authenticated and hasPermission('messageBoard','messageBoard:list')")
	@RequestMapping(value = "messageBoardsByPage", method = RequestMethod.POST, produces = PRODUCES_APPLICATION_JSON)
	@ResponseBody
	public JSONObject getPageableMessageBoards(@RequestBody Map<String, Object> map) {
		return newInstance(messageBoardService.findAll(PageUtils.parsePageable(map)));
	}
	
	@RequestMapping(value = "single", method = RequestMethod.POST, produces = PRODUCES_APPLICATION_JSON)
	@ResponseBody
	public JSONObject getSingleJSONObject(@RequestBody Map<String, Object> map) {
		return newInstance("obj",messageBoardService.findById(Long.valueOf(map.get("id").toString())));
	}
	
	@PreAuthorize("authenticated and hasPermission('messageBoard','messageBoard:update')")
	@RequestMapping(value = "singleUpdate", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject updateSingle(@RequestBody MessageBoard messageBoard) {
		return newInstance("updated",messageBoardService.update(messageBoard));
	}
	
	@PreAuthorize("authenticated and hasPermission('messageBoard','messageBoard:delete')")
	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	@ResponseBody
	public JSONObject delete(@RequestBody String ids) {
		return newInstance("count",messageBoardService
				.delete(CommonConvertUtils.convertToLongArray(ids)));
	}
}
