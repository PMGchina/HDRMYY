<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width,initial-scale=1">
		<title>体检服务</title>

		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/BOOTSTRAP/css/bootstrap.min.css">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/web/my-animate-3.5.1.min.css">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/web/front-comm.css">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/web/my-theme.css">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/web/front-footer.css">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/web/front-booking-health-examination.css">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/web/my-modal-acpd.css">
	</head>
	<body>
		<div class="container-fluid">
			<h2>&loz;预约体检说明:</h2>
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
			<button type="button" id="btn-guize" class="btn progress-bar-info btn-lg btn-block glyphicon glyphicon-check" 
					  onclick="loadPackages()">&nbsp;我 知 道 了</button>
		</div>
		<div class="navbar-fixed-bottom my-panel-default text-center my-footer">吉林同益科技技术支持</div>

		<script type="text/javascript" src="${pageContext.request.contextPath}/static/BOOTSTRAP/js/jquery-2.2.4.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/static/BOOTSTRAP/js/bootstrap.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/web/my-modal-utils.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/web/my-modal-acpd.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/web/front-booking-health-examination-biz.js"></script>
		<script type="text/javascript">var requestPath = "${pageContext.request.contextPath}";</script>

		<div class="modal fade" id="packageModal" tabindex="-1" role="dialog"
			  aria-labelledby="packageModalLabel" aria-hidden="true"
			  data-backdrop="static" data-keyboard="false" onclick="modalAnimation('shake', 'package')">
			<div class="modal-dialog" id="packageDialog">
				<div class="modal-content">
					<div class="modal-header my-panel-success text-center">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true" onclick="backInPackages()"><span>&times;</span></button>
						<span class="glyphicon glyphicon-user modal-header-icon"></span>
						<h3 class="modal-header-title" id="packageModalLabel">选择 体检套餐</h3>
					</div>
					<div class="modal-body">
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-xs btn-info pull-left" onclick="backInPackages()">
							<span class="glyphicon glyphicon-arrow-left"></span>&nbsp;上一步
						</button>
						<button type="button" class="btn btn-xs btn-success pull-right" onclick="nextInPackages()">
							<span class="glyphicon glyphicon-arrow-right"></span>&nbsp;下一步
						</button>
					</div>
				</div>
			</div>
		</div>
		<script>
			$('#packageModal').on('shown.bs.modal', function () {
				centerModals('package');
			});
			$('#packageModal').on('hidden.bs.modal', function () {
				$('#packageDialog').removeClass('shake animated');
			});
		</script>

		<div class="modal fade" id="itemModal" tabindex="-1" role="dialog" aria-labelledby="itemModalLabel"
			  aria-hidden="true" data-backdrop="static" data-keyboard="false" onclick="modalAnimation('shake', 'item')">
			<div class="modal-dialog" id="itemDialog">
				<div class="modal-content">
					<div class="modal-header my-panel-warning text-center">
						<button type="button" class="close" aria-hidden="true" onclick="backInItem()"><span>&times;</span></button>
						<span class="glyphicon glyphicon-user modal-header-icon"></span>
						<h3 class="modal-header-title" id="itemModalLabel">选择 体检单项</h3>
					</div>
					<div class="modal-body">
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-xs btn-info pull-left" onclick="backInItem()">
							<span class="glyphicon glyphicon-arrow-left"></span>&nbsp;上一步
						</button>
						<button type="button" class="btn btn-xs btn-success pull-right" onclick="nextInItem()">
							<span class="glyphicon glyphicon-arrow-right"></span>&nbsp;下一步
						</button>
					</div>
				</div>
			</div>
		</div>
		<script>
			$('#itemModal').on('shown.bs.modal', function () {
				centerModals('item');
			});
			$('#itemModal').on('hidden.bs.modal', function () {
				$('#itemDialog').removeClass('shake animated');
			});
		</script>

		<div class="modal fade" id="timeModal" tabindex="-1" role="dialog" aria-labelledby="timeModalLabel"
			  aria-hidden="true" data-backdrop="static" data-keyboard="false" onclick="modalAnimation('shake', 'time')">
			<div class="modal-dialog" id="timeDialog">
				<div class="modal-content">
					<div class="modal-header my-panel-warning text-center">
						<button type="button" class="close" aria-hidden="true" onclick="backInSelectTime()"><span>&times;</span></button>
						<span class="glyphicon glyphicon-user modal-header-icon"></span>
						<h3 class="modal-header-title" id="timeModalLabel">预约 体检日期</h3>
					</div>
					<div class="modal-body">
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-xs btn-info pull-left" onclick="backInSelectTime()">
							<span class="glyphicon glyphicon-arrow-left"></span>&nbsp;上一步
						</button>
						<button type="button" class="btn btn-xs btn-success pull-right" onclick="nextInSelectTime()">
							<span class="glyphicon glyphicon-arrow-right"></span>&nbsp;下一步
						</button>
					</div>
				</div>
			</div>
		</div>
		<script>
			$('#timeModal').on('shown.bs.modal', function () {
				centerModals('time');
			});
			$('#timeModal').on('hidden.bs.modal', function () {
				$('#timeDialog').removeClass('shake animated');
			});
		</script>

		<div class="modal fade" id="infoModal" tabindex="-1" role="dialog" aria-labelledby="infoModalLabel"
			  aria-hidden="true" data-backdrop="static" data-keyboard="false" onclick="modalAnimation('shake', 'info')">
			<div class="modal-dialog" id="infoDialog">
				<div class="modal-content">
					<div class="modal-header my-panel-warning text-center">
						<button type="button" class="close" aria-hidden="true" onclick="backInInputInfo()"><span>&times;</span></button>
						<span class="glyphicon glyphicon-user modal-header-icon"></span>
						<h3 class="modal-header-title" id="infoModalLabel">输入所需的个人信息</h3>
					</div>
					<div class="modal-body">
						<form class="form-horizontal" role="form">
							<div class="form-group" onclick="javascript:modalSwitch=false;">
								<label for="name" class="col-xs-3 text-right">姓  名:</label>
								<div class="col-xs-7">
									<input type="text" class="form-control" id="name" value="" placeholder="姓名或就诊卡号...">
								</div>
							</div>
							<div class="form-group" onclick="javascript:modalSwitch=false;">
								<label for="gender" class="col-xs-3 text-right">性  别:</label>
								<div class="col-xs-9">
									<label class="col-xs-2 radio-inline"><input type="radio" name="gender" value="1" checked>男</label>
									<label class="col-xs-2 radio-inline"><input type="radio" name="gender" value="0">女</label>
								</div>
							</div>
							<div class="form-group" onclick="javascript:modalSwitch=false;">
								<label for="birthday" class="col-xs-3 text-right">出生日:</label>
								<div class="col-xs-7">
									<input type="date" class="form-control" id="birthday" value="">
								</div>
							</div>
							<div class="form-group" onclick="javascript:modalSwitch=false;">
								<label for="identity" class="col-xs-3 text-right">身份证:</label>
								<div class="col-xs-9">
									<input type="text" class="form-control" id="identity" value="" placeholder="身份证号码...">
								</div>
							</div>
							<div class="form-group" onclick="javascript:modalSwitch=false;">
								<label for="phoneNumber" class="col-xs-3 text-right">手  机:</label>
								<div class="col-xs-9">
									<input type="text" class="form-control" id="phoneNumber" value="" placeholder="手机号码...">
								</div>
							</div>
						</form>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-xs btn-info pull-left" onclick="backInInputInfo()">
							<span class="glyphicon glyphicon-arrow-left"></span>&nbsp;上一步
						</button>
						<button type="button" class="btn btn-xs btn-success pull-right" onclick="beforeSubmitBooking()">
							<span class="glyphicon glyphicon-ok"></span>&nbsp;提 交
						</button>
					</div>
				</div>
			</div>
		</div>
		<script>
			$('#infoModal').on('shown.bs.modal', function () {
				centerModals('info');
			});
			$('#infoModal').on('hidden.bs.modal', function () {
				$('#infoDialog').removeClass('shake animated');
			});
		</script>

		<!-- Modal alert confirm -->
		<%@include file="../front_modal_acpd.html" %>
	</body>
</html>
