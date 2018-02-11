<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../include_admin_taglib.html"%>
<!DOCTYPE HTML>
<html>
  <body>
    <tags:subtitle primary="问卷管理" secondary="列表" hr="true" />
    <tags:buttons security="questionnaire" />
    <div id="customInlineButtons" hidden="true">
      <button type="button" class="am-btn am-btn-default" id="publishButton">
        <span class="am-icon-bell"></span> 发布
      </button>
      <button type="button" class="am-btn am-btn-default" id="unpublishButton">
        <span class="am-icon-bell-slash"></span> 取消发布
      </button>
      <button type="button" class="am-btn am-btn-default" id="availableButton">
        <span class="am-icon-eye"></span> 设为可用
      </button>
      <button type="button" class="am-btn am-btn-default" id="unavailableButton">
        <span class="am-icon-eye-slash"></span> 设为不可用
      </button>
    </div>
    <div class="am-g">
      <div class="am-u-sm-12">
        <div id="tableContainer"></div>
        <tags:pagination sizes="10,20"/>
      </div>
    </div>

    <%@ include file="../include_list_required.html"%>

    <%@ include file="admin_questionnaire_add.html"%>
    <%@ include file="admin_questionnaire_update.html"%>
    <%@ include file="admin_questionnaire_copy.html"%>
    <%@ include file="admin_questionnaire_analysis.html"%>

    <script src="${pageContext.request.contextPath }/static/js/business/questionnaire-biz.js"></script>
    <%@ include file="../include_list_required_ckeditor-scripts.html" %>
	<script class="hchart-file" src="${pageContext.request.contextPath }/static/HIGHCHARTS/js/highcharts.js"></script>
	<script class="hchart-file" src="${pageContext.request.contextPath }/static/HIGHCHARTS/js/highcharts-zh_CN.js"></script>
  </body>
</html>
