package com.beamofsoul.springboot.controller;

import static com.beamofsoul.springboot.management.util.JSONUtils.newInstance;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.beamofsoul.springboot.entity.User;
import com.beamofsoul.springboot.management.util.CommonConvertUtils;
import com.beamofsoul.springboot.management.util.PageUtils;
import com.beamofsoul.springboot.management.util.UserUtils;
import com.beamofsoul.springboot.service.UserService;

@Controller
@RequestMapping("/admin/user")
public class UserController extends BaseAbstractController {

    @Resource
    private UserService userService;

    @PreAuthorize("authenticated and hasPermission('user','user:list')")
    @RequestMapping(value = "/adminList")
    public String adminList() {
        return "user/admin_user_list";
    }

    @PreAuthorize("authenticated and hasPermission('user','user:add')")
    @RequestMapping(value = "/singleAdd", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject addSingle(@RequestBody User user) {
        return newInstance("created", userService.create(user));
    }

    @PreAuthorize("authenticated and hasPermission('user','user:list')")
    @RequestMapping(value = "usersByPage", method = RequestMethod.POST, produces = PRODUCES_APPLICATION_JSON)
    @ResponseBody
    public JSONObject getPageableUsers(@RequestBody Map<String, Object> map) {
//		map.put("predicateContent", "{status : 1, password : '123456'}");
//		new SimplePredicateBuilder(User.class).build(map)
        return newInstance(userService.findAll(PageUtils.parsePageable(map)));
    }

    @RequestMapping(value = "single", method = RequestMethod.POST, produces = PRODUCES_APPLICATION_JSON)
    @ResponseBody
    public JSONObject getSingleJSONObject(@RequestBody Map<String, Object> map) {
        return newInstance("obj", userService.findById(Long.valueOf(map.get("id").toString())));
    }

    @PreAuthorize("authenticated and hasPermission('user','user:update')")
    @RequestMapping(value = "singleUpdate", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject updateSingle(@RequestBody User user) {
        return newInstance("updated", userService.update(user));
    }

    @PreAuthorize("authenticated and hasPermission('user','user:delete')")
    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    @ResponseBody
    public JSONObject delete(@RequestBody String ids) {
        return newInstance("count", userService
            .delete(CommonConvertUtils.convertToLongArray(ids)));
    }

    @RequestMapping(value = "/checkUsernameUnique", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject checkUsernameUnique(@RequestBody Map<String, Object> map) {
        String username = map.get("data").toString();
        Long userId = map.containsKey("id") ? Long.valueOf(map.get("id").toString()) : null;
        return newInstance("isUnique", userService.checkUsernameUnique(username, userId));
    }

    @RequestMapping(value = "/checkNicknameUnique", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject checkNicknameUnique(@RequestBody Map<String, Object> map) {
        String nickname = map.get("data").toString();
        Long userId = map.containsKey("id") ? Long.valueOf(map.get("id").toString()) : null;
        return newInstance("isUnique", userService.checkNicknameUnique(nickname, userId));
    }

    @RequestMapping(value = "/isUsed", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject isUsed(@RequestBody String objectIds) {
        return newInstance("isUsed", userService.isUsed(objectIds));
    }

    @RequestMapping(value = "/changePassword", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject changePassword(@RequestBody Map<String, String> map, HttpSession session) {
        boolean changed = userService.changePassword(UserUtils.getLongUserId(session), map.get("currentPassword"), map.get("latestPassword"));
        if (changed) {
            UserUtils.getTraditionalUser(session).setPassword(map.get("latestPassword"));
        }
        return newInstance("changed", changed);
    }
}
