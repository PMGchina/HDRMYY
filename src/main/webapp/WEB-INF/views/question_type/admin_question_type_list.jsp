<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../include_admin_taglib.html"%>
<!DOCTYPE HTML>
<html>
  <body>
    <tags:subtitle primary="问题类型管理" secondary="列表" hr="true" />
    <tags:buttons security="questionType" />
    <div class="am-g">
      <div class="am-u-sm-12">
        <div id="tableContainer"></div>
        <tags:pagination sizes="10,20"/>
      </div>
    </div>

    <%@ include file="../include_list_required.html"%>

    <%@ include file="admin_question_type_add.html"%>
    <%@ include file="admin_question_type_update.html"%>
    <%@ include file="admin_question_type_copy.html"%>

    <script src="${pageContext.request.contextPath }/static/js/business/question-type-biz.js"></script>
  </body>
</html>
