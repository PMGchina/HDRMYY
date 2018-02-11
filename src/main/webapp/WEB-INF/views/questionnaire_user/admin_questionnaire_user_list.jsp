<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../include_admin_taglib.html"%>
<!DOCTYPE HTML>
<html>
  <body>
    <tags:subtitle primary="问卷用户关联管理" secondary="列表" hr="true" />
<%--     <tags:buttons security="questionnaireUser" include="invert,delete"/> --%>
    <div class="am-g">
      <div class="am-u-sm-12">
        <div id="tableContainer"></div>
        <tags:pagination sizes="10,20"/>
      </div>
    </div>
    
    <%@ include file="../include_list_required.html"%>

    <script src="${pageContext.request.contextPath }/static/js/business/questionnaire-user-biz.js"></script>
  </body>
</html>
