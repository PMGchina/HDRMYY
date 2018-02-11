<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width,initial-scale=1">
		<title>预约挂号</title>

		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/BOOTSTRAP/css/bootstrap.min.css">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/web/my-animate-3.5.1.min.css">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/web/front-comm.css">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/web/my-theme.css">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/web/my-modal-list-group.css">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/web/my-modal-acpd.css">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/web/front-footer.css">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/web/front-booking-registration.css">
	</head>
	<body>
		<div class="container-fluid">
			<h2>&loz;预约挂号说明:</h2>
			<div class="row">
				<div class="col-xs-1 text-right">1.</div>
				<div class="col-xs-11">
					<p>本院微信挂号时间为8:00-10:30 13:00-15:00。</p>
				</div>
			</div>
			<div class="row" style="color:#ff5c10">
				<div class="col-xs-1 text-right">2.</div>
				<div class="col-xs-11">
					<p>请及时到医院自助机或窗口进行挂号付费。如过号，系统会自动取消先前的挂号号序，
						并再自动推送一个新的号序；如再次过号，取消当天微信挂号，请至医院现场挂号。</p>
				</div>
			</div>
			<div class="row">
				<div class="col-xs-1 text-right">3.</div>
				<div class="col-xs-11">
					<p>本院“上午”门诊的挂号付费截止时间为11:00，“下午”及“全天”门诊的挂号付费截止时间为16:00。</p>
				</div>
			</div>
			<button type="button" id="btn-guize" class="btn progress-bar-info btn-lg btn-block glyphicon glyphicon-check" onclick="loadDepartment()">&nbsp;我 知 道 了</button>
		</div>
		<div class="navbar-fixed-bottom my-panel-default text-center my-footer">吉林同益科技技术支持</div>

		<script type="text/javascript" src="${pageContext.request.contextPath}/static/BOOTSTRAP/js/jquery-2.2.4.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/static/BOOTSTRAP/js/bootstrap.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/web/my-modal-utils.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/web/my-modal-acpd.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/web/front-booking-registration-biz.js"></script>

		<div class="modal fade" id="departmentModal" tabindex="-1" role="dialog"
			  aria-labelledby="departmentModalLabel" aria-hidden="true"
			  data-backdrop="static" data-keyboard="false" onclick="modalAnimation('shake', 'department')">
			<div class="modal-dialog" id="departmentDialog">
				<div class="modal-content">
					<div class="modal-header my-panel-success text-center">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true" onclick="backInDepartment()"><span>&times;</span></button>
						<span class="glyphicon glyphicon-user modal-header-icon"></span>
						<h3 class="modal-header-title" id="departmentModalLabel">预 约 科 室</h3>
					</div>
					<div class="modal-body">
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-xs btn-info pull-left" onclick="backInDepartment()">
							<span class="glyphicon glyphicon-arrow-left"></span>&nbsp;上一步
						</button>
					</div>
				</div>
			</div>
		</div>
		<script>
			$('#departmentModal').on('shown.bs.modal', function () {
				centerModals('department');
			});
			$('#departmentModal').on('hidden.bs.modal', function () {
				$('#departmentDialog').removeClass('shake animated');
			});
		</script>

		<div class="modal fade" id="doctorModal" tabindex="-1" role="dialog" aria-labelledby="doctorModalLabel"
			  aria-hidden="true" data-backdrop="static" data-keyboard="false" onclick="modalAnimation('shake', 'doctor')">
			<div class="modal-dialog" id="doctorDialog">
				<div class="modal-content">
					<div class="modal-header my-panel-warning text-center">
						<button type="button" class="close" aria-hidden="true" onclick="backInDoctor()"><span>&times;</span></button>
						<span class="glyphicon glyphicon-user modal-header-icon"></span>
						<h3 class="modal-header-title" id="doctorModalLabel">预 约 医 生</h3>
					</div>
					<div class="modal-body">
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-xs btn-info pull-left" onclick="backInDoctor()">
							<span class="glyphicon glyphicon-arrow-left"></span>&nbsp;上一步
						</button>
						<button type="button" class="btn btn-xs btn-success pull-right" onclick="skipInDoctor()">
							<span class="glyphicon glyphicon-arrow-right"></span>&nbsp;跳 过
						</button>
					</div>
				</div>
			</div>
		</div>
		<script>
			$('#doctorModal').on('shown.bs.modal', function () {
				centerModals('doctor');
			});
			$('#doctorModal').on('hidden.bs.modal', function () {
				$('#doctorDialog').removeClass('shake animated');
			});
		</script>

		<div class="modal fade" id="datetimeModal" tabindex="-1" role="dialog" aria-labelledby="datetimeModalLabel"
			  aria-hidden="true" data-backdrop="static" data-keyboard="false" onclick="modalAnimation('shake', 'datetime')">
			<div class="modal-dialog" id="datetimeDialog">
				<div class="modal-content">
					<div class="modal-header my-panel-warning text-center">
						<button type="button" class="close" aria-hidden="true" onclick="backInTime()"><span>&times;</span></button>
						<span class="glyphicon glyphicon-user modal-header-icon"></span>
						<h3 class="modal-header-title" id="datetimeModalLabel">预 约 时 间</h3>
					</div>
					<div class="modal-body">
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-xs btn-info pull-left" onclick="backInTime()">
							<span class="glyphicon glyphicon-arrow-left"></span>&nbsp;上一步
						</button>
						<button type="button" class="btn btn-xs btn-success pull-right" onclick="submitBooking()">
							<span class="glyphicon glyphicon-ok"></span>&nbsp;提 交
						</button>
					</div>
				</div>
			</div>
		</div>
		<script>
			$('#datetimeModal').on('shown.bs.modal', function () {
				centerModals('datetime');
			});
			$('#datetimeModal').on('hidden.bs.modal', function () {
				$('#datetimeDialog').removeClass('shake animated');
			});
		</script>

		<!-- Modal alert confirm -->
		<%@include file="../front_modal_acpd.html" %>
	</body>
</html>
