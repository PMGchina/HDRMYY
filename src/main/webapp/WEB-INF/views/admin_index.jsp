<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE HTML>
<html class="no-js fixed-layout">
	<head>
		<title>后台管理首页页面</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width,initial-scale=1">
		<meta name="renderer" content="webkit">
		<meta http-equiv="cache-control" content="max-age=0" />
		<meta http-equiv="cache-control" content="no-cache, must-revalidate" />
		<meta http-equiv="expires" content="0" />
		<meta http-equiv="pragma" content="no-cache" />
	</head>
	<body data-type="index">
		<%@ include file="include_browser_notice.html"%>
		<%@ include file="include_admin_header.html"%>

		<div class="am-cf admin-main" disabled>
			<%@ include file="include_admin_sidebar.html"%>

			<div id="backDrop" class="modal-backdrop" style="background-color: rgba(0, 0, 0, 0.6); display: none"></div>

			<!-- content start -->
			<div class="admin-content">
				<div class="admin-content-body indexContentBody"></div>
			</div>
			<!-- content end -->
		</div>

		<%@ include file="include_admin_footer.html"%>
	</body>
</html>
