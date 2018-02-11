<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width,initial-scale=1">
		<title>问卷发布</title>

		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/BOOTSTRAP/css/bootstrap.min.css">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/web/my-animate-3.5.1.min.css">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/web/front-comm.css">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/web/my-theme.css">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/web/my-theme.css">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/web/my-modal-acpd.css">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/web/front-footer.css">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/web/front-question-publish.css">
	</head>
	<body>
		<div class="container-fluid">
			<div class="page-header my-panel-primary text-center"><span>问 卷 发 布</span></div>

			<ul class="list-group">
				<li class="list-group-item">
					<strong>问卷1</strong><span>&rsaquo;</span>
				</li>
			</ul>

			<button type="button" id="btn-back" class="btn my-panel-default btn-block" onclick="JavaScript:history.back(-1);">
				<strong class="glyphicon glyphicon-circle-arrow-left"></strong><span>返  回</span>
			</button>
		</div>

		<div class="navbar-fixed-bottom my-panel-default text-center my-footer">吉林同益科技技术支持</div>

		<script type="text/javascript" src="${pageContext.request.contextPath}/static/BOOTSTRAP/js/jquery-2.2.4.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/static/BOOTSTRAP/js/bootstrap.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/web/my-modal-utils.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/web/my-modal-acpd.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/web/front-question-publish-biz.js"></script>

		<!-- Modal alert confirm -->
		<%@include file="../front_modal_acpd.html" %>
	</body>
</html>
