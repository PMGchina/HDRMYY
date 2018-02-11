<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
   <head>
		<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width,initial-scale=1">
		<title>桦甸人民医院-主页</title>

		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/BOOTSTRAP/css/bootstrap.min.css">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/web/my-animate-3.5.1.min.css">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/web/front-comm.css">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/web/my-theme.css">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/web/my-modal-form.css">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/web/my-modal-acpd.css">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/web/front-footer.css">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/web/index.css">
   </head>
   <body style="padding-bottom:30px">
		<%@ include file="include_header.html"%>

		<table class="table table-condensed text-center">
			<tbody>
				<tr>
					<td colspan="3" style="padding:0 0 1px 0">
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
					</td>
				</tr>
				<tr class="my-panel-default">
					<td>
						<sec:authorize access="isAuthenticated()">
							<a href="${pageContext.request.contextPath}/front/bookingRegistration/doctor">预约挂号</a>
						</sec:authorize>
						<c:if test="${sessionScope.CURRENT_USER==null}">
							<a href="javascript:viod(0)" data-toggle="modal" data-target="#loginModal">预约挂号</a>
						</c:if>
					</td>
					<td>
						<sec:authorize access="isAuthenticated()">
							<a href="${pageContext.request.contextPath}/front/bookingRegistration/expert">主任预约</a>
						</sec:authorize>
						<c:if test="${sessionScope.CURRENT_USER==null}">
							<a href="javascript:viod(0)" data-toggle="modal" data-target="#loginModal">主任预约</a>
						</c:if>
					</td>
					<td>
						<a href="${pageContext.request.contextPath}/front/scheduling/index">出诊<br>时间表</a>
					</td>
				</tr>
				<tr class="my-panel-success">
					<td>
						<sec:authorize access="isAuthenticated()">
							<a href="${pageContext.request.contextPath}/front/bookingReexamination/index">定期复查</a>
						</sec:authorize>
						<c:if test="${sessionScope.CURRENT_USER==null}">
							<a href="javascript:void(0)" data-toggle="modal" data-target="#loginModal">定期复查</a>
						</c:if>
					</td>
					<td>
						<a href="${pageContext.request.contextPath}/front/bookingHealthExamination/index">体检服务</a>
					</td>
					<td>
						<a href="${pageContext.request.contextPath}/front/healthExaminationExpert/index">体检<br>专家组成员</a>
					</td>
				</tr>
				<tr class="my-panel-warning">
					<td><a href="${pageContext.request.contextPath}/front/directorMien/index">主任风采</a></td>
					<td><a href="${pageContext.request.contextPath}/front/departmentConstruction/index">科室建设</a></td>
					<td><a href="${pageContext.request.contextPath}/front/doctor/index">医生专家<br>名录<br></a></td>
				</tr>
				<tr class="my-panel-danger">
					<td>
						<sec:authorize access="isAuthenticated()">
							<a href="${pageContext.request.contextPath}/front/checkReport/index">检验单<br>结果查询</a>
							</sec:authorize>
							<c:if test="${sessionScope.CURRENT_USER==null}">
							<a href="javascript:viod(0)" data-toggle="modal" data-target="#loginModal">检验单<br>结果查询</a>
							</c:if>
					</td>
					<td colspan="2">
						<sec:authorize access="isAuthenticated()">
							<a href="javascript:viod(0)">检查>结果查询</a>
						</sec:authorize>
						<c:if test="${sessionScope.CURRENT_USER==null}">
							<a href="javascript:viod(0)" data-toggle="modal" data-target="#loginModal">检查结果查询</a>
						</c:if>
					</td>
				</tr>
				<tr class="my-panel-info">
					<td><a href="${pageContext.request.contextPath}/front/healthEducation/index">健康宣教</a></td>
					<td><a href="javascript:void(0)">签约<br>个人医师</a></td>
					<td><a href="javascript:void(0)">在线支付</a></td>
				</tr>
				<tr class="progress-bar-info">
					<td><a href="${pageContext.request.contextPath}/front/seekingTreatStrategy/index">就医攻略</a></td>
					<td><a href="${pageContext.request.contextPath}/front/guidingTreat/index">门诊导医</a></td>
					<td><a href="${pageContext.request.contextPath}/front/inpatientService/index">住院<br>服务向导</a></td>
				</tr>
				<tr class="progress-bar-success">
					<td><a href="${pageContext.request.contextPath}/front/hospitalEvent/index">院内<br>大事件</a></td>
					<td><a href="${pageContext.request.contextPath}/front/messageBoard/index">便民服务</a></td>
					<td>
						<sec:authorize access="isAuthenticated()">
							<a href="${pageContext.request.contextPath}/front/questions/answer">满意度<br>调查</a>
							</sec:authorize>
							<c:if test="${sessionScope.CURRENT_USER==null}">
							<a href="javascript:viod(0)" data-toggle="modal" data-target="#loginModal">满意度<br>调查</a>
							</c:if>
					</td>
				</tr>
				<tr class="progress-bar-warning">
					<td>
						<sec:authorize access="isAuthenticated()">
							<a href="${pageContext.request.contextPath}/front/personalCenter/menu">个人中心</a>
						</sec:authorize>
						<c:if test="${sessionScope.CURRENT_USER==null}">
							<a href="javascript:viod(0)" data-toggle="modal" data-target="#loginModal">个人中心</a>
						</c:if>
					</td>
					<td>
						<sec:authorize access="hasAnyRole('ADMIN','MANAGER')">
							<a href="${pageContext.request.contextPath}/front/sysManagement/menu">医院<br>管理人员</a>
							</sec:authorize>
							<sec:authorize access="isAuthenticated() and !hasAnyRole('ADMIN','MANAGER')">
							<a href="javascript:mdDialog('提示：','您无权限访问！');">医院<br>管理人员</a>
							</sec:authorize>
							<c:if test="${sessionScope.CURRENT_USER==null}">
							<a href="javascript:viod(0)" data-toggle="modal" data-target="#loginModal">医院<br>管理人员</a>
							</c:if>
					</td>
					<td><a href="javascript:void(0)">免费WIFI</a></td>
				</tr>
			</tbody>
		</table>

		<div class="navbar-fixed-bottom my-panel-default text-center my-footer">吉林同益科技技术支持</div>

		<script type="text/javascript" src="${pageContext.request.contextPath}/static/BOOTSTRAP/js/jquery-2.2.4.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/static/BOOTSTRAP/js/bootstrap.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/web/my-modal-utils.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/web/my-modal-acpd.js"></script>

		<!-- Modal login -->
		<%@include file="front_modal_login.html" %>
		<!-- Modal alert confirm -->
		<%@include file="front_modal_acpd.html" %>

		<script>
			$(function () {
				var useragent = navigator.userAgent;
				if (/Android|webOS|iPhone|iPod|BlackBerry/i.test(useragent)) {
					$('.my-top').css('display', 'none');
					if (useragent.match(/MicroMessenger/i) != 'MicroMessenger') {
						alert('使用微信内置浏览器访问本页面！');
						//强行关闭当前页面
						//var opened = window.open('about:blank', '_self');
						//opened.opener = null;
						//opened.close();
					}
				}
			});
			$(document).ready(function () {
				$(document).ready(function () {
					$("td").click(function () {
						$(this).hide();
						$(this).fadeIn('slow', 'swing');
					});
				});
				if ($('#loginModal').css("display") === "none") {
					var html = $('#loginModal').find('.alert').html() || "";
					if (html.length > 0) {
						$('#loginModal').modal('show');
					}
				}
			});
		</script>
	</body>
</html>
