<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width,initial-scale=1">
		<title>出诊时间表</title>

		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/BOOTSTRAP/css/bootstrap.min.css">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/web/my-animate-3.5.1.min.css">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/web/my-theme.css">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/web/my-modal-acpd.css">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/web/front-footer.css">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/web/front-scheduling.css">
	</head>
	<body>
		<div class="container">
			<div class="page-header my-panel-warning text-center"><span>出 诊 时 间 表</span></div>

			<div class="row">
				<div class="col-xs-8">图片 姓名<small>职称</small> 科室</div>
				<div class="col-xs-4 text-center mySchedule">出诊日期</div>
			</div>

			<div class="btn-group col-xs-12">
				<button type="button" id="btn-back" class="btn my-panel-default col-xs-6" onclick="JavaScript:history.back(-1);">
					<strong class="glyphicon glyphicon-circle-arrow-left"></strong><span>返  回</span>
				</button>
				<button type="button" id="btn-down" class="btn my-panel-info col-xs-6" onclick="clickLookDown()">
					<strong class="glyphicon glyphicon-circle-arrow-down"></strong><span>往后看</span>
				</button>
			</div>
		</div>

		<div class="navbar-fixed-bottom my-panel-default text-center my-footer">吉林同益科技技术支持</div>

		<script type="text/javascript" src="${pageContext.request.contextPath}/static/BOOTSTRAP/js/jquery-2.2.4.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/static/BOOTSTRAP/js/bootstrap.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/web/my-modal-utils.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/web/my-modal-acpd.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/web/front-scheduling-biz.js"></script>

		<!-- Modal alert confirm -->
		<%@include file="../front_modal_acpd.html" %>
	</body>
</html>
