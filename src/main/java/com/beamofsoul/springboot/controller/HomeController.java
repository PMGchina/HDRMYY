package com.beamofsoul.springboot.controller;

import static com.beamofsoul.springboot.management.util.ResponseUtils.getHtmlWriter;
import static com.beamofsoul.springboot.management.util.ResponseUtils.writeJavascript;

import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.beamofsoul.springboot.management.util.Callback;
import com.beamofsoul.springboot.management.util.Constants;
import com.beamofsoul.springboot.management.util.ImageUtils;

@Controller
public class HomeController {

   @RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
   public ModelAndView index(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
      return new ModelAndView("index");
   }

   @RequestMapping(value = "/admin/adminIndex", method = RequestMethod.GET)
   public ModelAndView adminIndex(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
      return new ModelAndView("admin_index");
   }

   @RequestMapping(value = "/admin/adminIndexContent", method = RequestMethod.GET)
   public ModelAndView adminIndexContent() {
      return new ModelAndView("backend/admin_backend_index_content");
   }

   @RequestMapping(value = "/login", method = RequestMethod.GET)
   public String login(HttpSession session, Map<String, Object> map) {
      if (session.getAttribute(Constants.CURRENT_USER) != null) {
         return "redirect:/index";
      }
      return "redirect:/index";	//login
   }

   @RequestMapping(value = "/logout", method = RequestMethod.GET)
   public String logout(HttpSession session, Map<String, Object> map) {
      session.invalidate();
      return "index";
   }

   @RequestMapping(value = "/expired", method = RequestMethod.GET)
   public String expired(Map<String, Object> map) {
      map.put("expired", "该账号已经在其他地方重新登录");
      return "redirect:/index";	//login
   }

   @RequestMapping(value = "/anonymous", method = RequestMethod.GET)
   public String anonymousLogin() {
      return "redirect:/index";	//admin_index
   }

   /**
    * @Title: uploadImage
    * @Description: 使用ckfinder向服务器上传单一图片
    * @param upload - 图片文件
    * @param callback -
    * 上传成功后javascript回调方法所需所需参数，不同浏览器可能会有不同参数，后台无需修改，相应时传递给javascript即可
    * @param request - HttpServletRequest
    * @param response - HttpServletResponse
    * @return void - 原则上不返回任何实质内容
    */
   @RequestMapping(value = "/uploadImage", method = RequestMethod.POST)
   public void uploadImage(@RequestParam(value = "upload", required = false) MultipartFile upload, @RequestParam("CKEditorFuncNum") String callback, HttpServletRequest request, HttpServletResponse response) {
      try (PrintWriter out = getHtmlWriter(response)) {
         // 如果上传的文件不存在或内容为空，直接响应并提示请选择要上传的文件
         if (ImageUtils.isEmptyDuringUploading(upload, (e) -> writeJavascript(out, e[0].toString().replace(Callback.CALLBACK_PLACEHOLDER, callback)))) {
            return;
         }
         // 序列化上传的图片
         ImageUtils.uploadImage(upload, (e) -> writeJavascript(out, e[0].toString().replace(Callback.CALLBACK_PLACEHOLDER, callback)));
      } catch (Exception e) {
         e.printStackTrace();
      }
   }
}
