package com.beamofsoul.springboot.controller;

import static com.beamofsoul.springboot.management.util.JSONUtils.newInstance;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONObject;
import com.beamofsoul.springboot.management.mvc.PostResponseBody;
import com.beamofsoul.springboot.management.util.PageUtils;
import com.beamofsoul.springboot.other.service.ReexaminationItemService;

@Controller
@RequestMapping("/admin/reexaminationItem")
public class ReexaminationItemController extends BaseAbstractController {

	@Resource
	private ReexaminationItemService reexaminationItemService;
	
	@PreAuthorize("authenticated and hasPermission('reexaminationItem','reexaminationItem:list')")
	@RequestMapping(value = "/adminList")
	public String adminList() {
		return "reexamination_item/admin_reexamination_item_list";
	}
	
	@PreAuthorize("authenticated and hasPermission('reexaminationItem','reexaminationItem:list')")
	@PostResponseBody("reexaminationItemsByPage")
	public JSONObject getPageableReexaminationItems(@RequestBody Map<String, Object> map) {
		return newInstance(reexaminationItemService.findAll(PageUtils.parsePageable(map)));
	}
}
