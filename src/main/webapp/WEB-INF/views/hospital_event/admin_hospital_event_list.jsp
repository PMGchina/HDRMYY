<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../include_admin_taglib.html"%>
<!DOCTYPE HTML>
<html>
  <body>
    <tags:subtitle primary="院内大事件管理" secondary="列表" hr="true" />
    <tags:buttons security="hospitalEvent" />
    <div class="am-g">
      <div class="am-u-sm-12">
        <div id="tableContainer"></div>
        <tags:pagination sizes="10,20"/>
      </div>
    </div>

    <%@ include file="../include_list_required.html"%>

    <%@ include file="admin_hospital_event_add.html"%>
    <%@ include file="admin_hospital_event_update.html"%>
    <%@ include file="admin_hospital_event_copy.html"%>

    <script src="${pageContext.request.contextPath }/static/js/business/hospital-event-biz.js"></script>
    <%@ include file="../include_list_required_ckeditor-scripts.html" %>
  </body>
</html>
