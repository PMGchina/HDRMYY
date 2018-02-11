<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width,initial-scale=1">
		<title>答案情况</title>

		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/BOOTSTRAP/css/bootstrap.min.css">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/web/my-animate-3.5.1.min.css">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/web/my-theme.css">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/web/my-modal-list-group.css">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/web/front-footer.css">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/web/front-question-analysis.css">
	</head>
	<body>
		<div class="container-fluid">
			<div class="panel panel-primary" id="questionnaire">
				<div class="panel-heading text-center">
					<a data-toggle="collapse" data-parent="#accordion" href="#collapse1"><span>问卷题目</span></a>
				</div>
				<div id="collapse1" class="panel-collapse collapse">
					<div class="panel-body">说明</div>
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
		<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/web/front-question-analysis-biz.js"></script>

		<div class="modal fade" id="analysisModal" tabindex="-1" role="dialog" aria-labelledby="analysisModalLabel"
			  aria-hidden="true" data-backdrop="static" data-keyboard="false" onclick="modalAnimation('shake', 'analysis')">
			<div class="modal-dialog" id="analysisDialog">
				<div class="modal-content">
					<div class="modal-header my-panel-success text-center">
						<button type="button" class="close" aria-hidden="true" data-dismiss="modal" onclick="JavaScript:history.back(-1);"><span>&times;</span></button>
						<span class="glyphicon glyphicon-user modal-header-icon"></span>
						<h3 class="modal-header-title" id="analysisModalLabel">问 卷 回 答</h3>
					</div>
					<div class="modal-body">
					</div>
				</div>
			</div>
		</div>
		<script>
			$('#analysisModal').on('shown.bs.modal', function () {
				centerModals('analysis');
			});
			$('#analysisModal').on('hidden.bs.modal', function () {
				$('#analysisDialog').removeClass('shake animated');
			});
		</script>
	</body>
</html>
