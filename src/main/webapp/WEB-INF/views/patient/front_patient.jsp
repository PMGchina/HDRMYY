<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width,initial-scale=1">
		<title>我的就诊卡</title>

		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/BOOTSTRAP/css/bootstrap.min.css">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/web/my-theme.css">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/web/front-footer.css">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/web/front-patient.css">
	</head>
	<body>
		<div class="container">
			<div class="page-header my-panel-warning text-center"><span>我 的 就 诊 卡</span></div>

			<div id="barCode"><img src="" alt=""></div>
			<div id="qrCode"><img src="" alt=""></div>

			<button type="button" id="btn-back" class="btn my-panel-default btn-lg btn-block" onclick="JavaScript:history.back(-1);">
				<strong class="glyphicon glyphicon-circle-arrow-left"></strong><span>返  回</span>
			</button>
		</div>

		<div class="navbar-fixed-bottom my-panel-default text-center my-footer">吉林同益科技技术支持</div>

		<script type="text/javascript" src="${pageContext.request.contextPath}/static/BOOTSTRAP/js/jquery-2.2.4.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/static/BOOTSTRAP/js/bootstrap.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/web/front-patient-biz.js"></script>
	</body>
</html>
