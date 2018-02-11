<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../include_admin_taglib.html"%>
<!DOCTYPE HTML>
<html>
  <body>
    <link rel="stylesheet" href="${pageContext.request.contextPath }/static/AMAZEUI-CROPPER/css/amazeui.cropper.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath }/static/css/business/user-biz.css">
    
    <tags:subtitle primary="用户管理" secondary="列表" hr="true" />
    <tags:buttons security="user"/>
    <div class="am-g">
      <div class="am-u-sm-12">
        <div id="tableContainer"></div>
        <tags:pagination sizes="10,20"/>
      </div>
    </div>

    <%@ include file="../include_list_required.html"%>

    <%@ include file="admin_user_add.html"%>
    <%@ include file="admin_user_update.html"%>
    <%@ include file="admin_user_copy.html"%>

    <script src="${pageContext.request.contextPath }/static/js/business/user-biz.js"></script>
    <script src="${pageContext.request.contextPath }/static/js/utils/image-utils.js"></script>
    <script class="acropper-file" src="${pageContext.request.contextPath }/static/AMAZEUI-CROPPER/js/cropper.min.js"></script>
  </body>
</html>