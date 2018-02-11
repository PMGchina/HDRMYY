<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width,initial-scale=1">
		<title>我的预约信息</title>

		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/BOOTSTRAP/css/bootstrap.min.css">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/web/my-animate-3.5.1.min.css">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/web/my-theme.css">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/web/my-modal-list-group.css">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/web/my-modal-acpd.css">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/web/front-footer.css">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/web/front-booking-all-message.css">
	</head>
	<body>
		<div class="container">
			<div class="page-header my-panel-warning text-center"><span>我 的 预 约 信 息</span></div>

			<ul id="myTab" class="nav nav-tabs">
				<li class="dropdown active">
					<a href="#" id="myTabDrop1" class="dropdown-toggle" data-toggle="dropdown">挂号<b class="caret"></b></a>
					<ul class="dropdown-menu" role="menu" aria-labelledby="myTabDrop1">
						<li onclick="triggerLoad(this)"><a href="#ghUnProcessed" tabindex="-1" data-toggle="tab">未就诊</a></li>
						<li onclick="triggerLoad(this)"><a href="#ghCanceled" tabindex="-1" data-toggle="tab">已取消</a></li>
					</ul>
				</li>
				<li class="dropdown">
					<a href="#" id="myTabDrop2" class="dropdown-toggle" data-toggle="dropdown">复查<b class="caret"></b></a>
					<ul class="dropdown-menu" role="menu" aria-labelledby="myTabDrop2">
						<li onclick="triggerLoad(this)"><a href="#fcUnProcessed" tabindex="-1" data-toggle="tab">未复查</a></li>
						<li onclick="triggerLoad(this)"><a href="#fcCanceled" tabindex="-1" data-toggle="tab">已取消</a></li>
					</ul>
				</li>
				<li class="dropdown">
					<a href="#" id="myTabDrop3" class="dropdown-toggle" data-toggle="dropdown">体检<b class="caret"></b></a>
					<ul class="dropdown-menu" role="menu" aria-labelledby="myTabDrop3">
						<li onclick="triggerLoad(this)"><a href="#tjUnProcessed" tabindex="-1" data-toggle="tab">未体检</a></li>
						<li onclick="triggerLoad(this)"><a href="#tjCanceled" tabindex="-1" data-toggle="tab">已取消</a></li>
					</ul>
				</li>
			</ul>

			<div id="myTabContent" class="tab-content">
				<div class="tab-pane fade in active" id="ghUnProcessed">
					<div class="my-panel-info text-center myHead">挂号预约 (未就诊)</div>
				</div>
				<div class="tab-pane fade" id="ghCanceled">
					<div class="my-panel-info text-center myHead">挂号预约 (已取消)</div>
				</div>

				<div class="tab-pane fade" id="fcUnProcessed">
					<div class="my-panel-info text-center myHead">复查预约 (未复查)</div>
				</div>
				<div class="tab-pane fade" id="fcCanceled">
					<div class="my-panel-info text-center myHead">复查预约 (已取消)</div>
				</div>

				<div class="tab-pane fade" id="tjUnProcessed">
					<div class="my-panel-info text-center myHead">体检预约 (未体检)</div>
				</div>
				<div class="tab-pane fade" id="tjCanceled">
					<div class="my-panel-info text-center myHead">体检预约 (已取消)</div>
				</div>
			</div>

			<button type="button" id="btn-back" class="btn my-panel-default btn-lg btn-block" onclick="JavaScript:history.back(-1);">
				<strong class="glyphicon glyphicon-circle-arrow-left"></strong><span>返  回</span>
			</button>
		</div>

		<div class="navbar-fixed-bottom my-panel-default text-center my-footer">吉林同益科技技术支持</div>

		<script type="text/javascript" src="${pageContext.request.contextPath}/static/BOOTSTRAP/js/jquery-2.2.4.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/static/BOOTSTRAP/js/bootstrap.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/web/my-modal-utils.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/web/my-modal-acpd.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/web/front-booking-all-message-biz.js"></script>

		<div class="modal fade" id="identityModal" tabindex="-1" role="dialog" aria-labelledby="identityModalLabel"
			  aria-hidden="true" data-backdrop="static" data-keyboard="false" onclick="modalAnimation('shake', 'identity')">
			<div class="modal-dialog" id="identityDialog">
				<div class="modal-content">
					<div class="modal-header my-panel-warning text-center">
						<button type="button" class="close" aria-hidden="true" data-dismiss="modal"><span>&times;</span></button>
						<span class="glyphicon glyphicon-user modal-header-icon"></span>
						<h3 class="modal-header-title" id="identityModalLabel">用身份证查体检预约</h3>
					</div>
					<div class="modal-body">
						<form class="form-horizontal" role="form">
							<div class="form-group" onclick="javascript:modalSwitch = false;">
								<label for="identity" class="col-xs-3 text-right">身份证:</label>
								<div class="col-xs-9">
									<input type="hidden" id="ahref" value="">
									<input type="text" class="form-control" id="identity" value="" placeholder="请输入身份证号码...">
								</div>
							</div>
						</form>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-xs btn-success" onclick="aftertrInput()">
							<span class="glyphicon glyphicon-ok"></span>&nbsp;确 定
						</button>
					</div>
				</div>
			</div>
		</div>
		<script>
			$('#identityModal').on('shown.bs.modal', function () {
				centerModals('identity');
			});
			$('#identityModal').on('hidden.bs.modal', function () {
				$('#identityDialog').removeClass('shake animated');
			});
		</script>

		<!-- Modal alert confirm -->
		<%@include file="../front_modal_acpd.html" %>
	</body>
</html>
