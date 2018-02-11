<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width,initial-scale=1">
		<title>个人中心</title>

		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/BOOTSTRAP/css/bootstrap.min.css">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/web/front-comm.css">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/web/my-theme.css">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/web/front-footer.css">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/web/front-personal-center.css">
	</head>
	<body>
		<div class="container-fluid">
			<div class="page-header my-panel-success text-center"><span>个 人 中 心</span></div>

			<div id="adCarousel" class="carousel slide" data-ride="carousel">
				<div class="carousel-inner">
					<div class="item active"><img src="${pageContext.request.contextPath}/static/image/web/ad/ad1.jpg" alt=""></div>
					<div class="item"><img src="${pageContext.request.contextPath}/static/image/web/ad/ad2.jpg" alt=""></div>
					<div class="item"><img src="${pageContext.request.contextPath}/static/image/web/ad/ad3.jpg" alt=""></div>
					<div class="item"><img src="${pageContext.request.contextPath}/static/image/web/ad/ad4.jpg" alt=""></div>
					<div class="item"><img src="${pageContext.request.contextPath}/static/image/web/ad/ad5.jpg" alt=""></div>
					<div class="item"><img src="${pageContext.request.contextPath}/static/image/web/ad/ad6.jpg" alt=""></div>
				</div>
			</div>

			<div class="menulist">
				<a href="${pageContext.request.contextPath}/front/patient/index" class="list-group-item">
					<img src="${pageContext.request.contextPath}/static/image/web/logo.png" alt=""><strong>我的就诊卡</strong><span>&rsaquo;</span>
				</a>
				<a href="${pageContext.request.contextPath}/front/bookingAllMessage/index" class="list-group-item">
					<img src="${pageContext.request.contextPath}/static/image/web/logo.png" alt=""><strong>我的预约挂号信息</strong><span>&rsaquo;</span>
				</a>
				<a href="${pageContext.request.contextPath}/logout" class="list-group-item">
					<img src="${pageContext.request.contextPath}/static/image/web/logo.png" alt=""><strong>退出登录</strong><span>&rsaquo;</span>
				</a>
			</div>
		</div>

		<div class="navbar-fixed-bottom my-panel-default text-center my-footer">吉林同益科技技术支持</div>

		<script type="text/javascript" src="${pageContext.request.contextPath}/static/BOOTSTRAP/js/jquery-2.2.4.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/static/BOOTSTRAP/js/bootstrap.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/web/front-personal-center-biz.js"></script>
	</body>
</html>
