<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../include_admin_taglib.html"%>
<!DOCTYPE HTML>
<html>
  <body>
    <tags:subtitle primary="科室建设管理" secondary="列表" hr="true" />
    <tags:buttons security="departmentConstruction" />
    <div class="am-g">
      <div class="am-u-sm-12">
        <div id="tableContainer"></div>
        <tags:pagination sizes="10,20"/>
      </div>
    </div>

    <%@ include file="../include_list_required.html"%>

    <%@ include file="admin_department_construction_add.html"%>
    <%@ include file="admin_department_construction_update.html"%>
    <%@ include file="admin_department_construction_copy.html"%>

    <script src="${pageContext.request.contextPath }/static/js/business/department-construction-biz.js"></script>
    <%@ include file="../include_list_required_ckeditor-scripts.html" %>
  </body>
</html>
