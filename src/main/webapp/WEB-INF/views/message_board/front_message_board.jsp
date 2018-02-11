<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width,initial-scale=1">
		<title>便民服务</title>

		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/BOOTSTRAP/css/bootstrap.min.css">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/web/my-animate-3.5.1.min.css">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/web/my-theme.css">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/web/my-modal-list-group.css">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/web/my-modal-acpd.css">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/web/front-footer.css">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/web/front-message-board.css">
	</head>
	<body>
		<div class="container">
			<div class="page-header my-panel-warning text-center"><span>便 民 服 务</span></div>

			<div class="btn-group col-xs-12">
				<button type="button" id="btn-back" class="btn my-panel-default col-xs-4" onclick="JavaScript:history.back(-1);">
					<strong class="glyphicon glyphicon-circle-arrow-left"></strong><span>返  回</span>
				</button>
				<button type="button" id="btn-refresh" class="btn my-panel-info col-xs-4" onclick="refreshContent()">
					<strong class="glyphicon glyphicon-refresh"></strong><span>刷 新</span>
				</button>
				<button type="button" id="btn-creat" class="btn my-panel-warning col-xs-4" onclick="creatContent()">
					<strong class="glyphicon glyphicon-credit-card"></strong><span>创 建</span>
				</button>
			</div>
		</div>

		<div class="navbar-fixed-bottom my-panel-default text-center my-footer">吉林同益科技技术支持</div>

		<script type="text/javascript" src="${pageContext.request.contextPath}/static/BOOTSTRAP/js/jquery-2.2.4.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/static/BOOTSTRAP/js/bootstrap.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/web/my-modal-utils.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/web/my-modal-acpd.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/web/front-message-board-biz.js"></script>

		<div class="modal fade" id="inputModal" tabindex="-1" role="dialog" aria-labelledby="inputModalLabel"
			  aria-hidden="true" data-backdrop="static" data-keyboard="false" onclick="modalAnimation('shake', 'input')">
			<div class="modal-dialog" id="inputDialog">
				<div class="modal-content">
					<div class="modal-header my-panel-warning text-center">
						<button type="button" class="close" aria-hidden="true" data-dismiss="modal"><span>&times;</span></button>
						<h3 class="modal-header-title" id="inputModalLabel">请输入提交内容</h3>
					</div>
					<div class="modal-body">
						<form class="form-horizontal" role="form">
							<div class="form-group" onclick="javascript:modalSwitch = false;">
								<label for="title" class="col-xs-2 text-right">标题:</label>
								<div class="col-xs-10">
									<input type="text" class="form-control" id="title" value="" placeholder="请输入标题...">
								</div>
							</div>
							<div class="form-group col-xs-12" onclick="javascript:modalSwitch = false;">
								<textarea class="form-control" id="content" rows="6" placeholder="请输入内容..."></textarea>
							</div>
						</form>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-xs btn-default" data-dismiss="modal">
							<span class="glyphicon glyphicon-off"></span>&nbsp;取 消
						</button>
						<button type="button" class="btn btn-xs btn-success" onclick="submitContent()">
							<span class="glyphicon glyphicon-log-in"></span>&nbsp;提 交
						</button>
					</div>
				</div>
			</div>
		</div>
		<script>
			$('#inputModal').on('shown.bs.modal', function () {
				centerModals('input');
			});
			$('#inputModal').on('hidden.bs.modal', function () {
				$('#inputDialog').removeClass('shake animated');
			});
		</script>

		<!-- Modal alert confirm -->
		<%@include file="../front_modal_acpd.html" %>
	</body>
</html>
