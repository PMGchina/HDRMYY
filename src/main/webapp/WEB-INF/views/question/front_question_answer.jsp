<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width,initial-scale=1">
		<title>答案提交</title>

		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/BOOTSTRAP/css/bootstrap.min.css">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/web/my-animate-3.5.1.min.css">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/web/my-theme.css">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/web/my-modal-list-group.css">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/web/my-modal-acpd.css">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/web/front-footer.css">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/web/front-question-answer.css">
	</head>
	<body>
		<div class="container">
			<div class="newQuesArea"></div>

			<div class="makeQuesArea">
				<div class="makeQuesBtns">
					<button type="button" class="btn btn-success" onclick="JavaScript:history.back(-1);"><span>取 消</span></button>
					<button type="button" class="btn btn-success" onclick="submitAnswer()"><span>提 交</span></button>
				</div>
			</div>

		</div>

		<div class="navbar-fixed-bottom my-panel-default text-center my-footer">吉林同益科技技术支持</div>

		<script type="text/javascript" src="${pageContext.request.contextPath}/static/BOOTSTRAP/js/jquery-2.2.4.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/static/BOOTSTRAP/js/bootstrap.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/web/my-modal-utils.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/web/my-modal-acpd.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/web/front-question-answer-biz.js"></script>

		<div class="modal fade" id="answerModal" tabindex="-1" role="dialog" aria-labelledby="answerModalLabel"
			  aria-hidden="true" data-backdrop="static" data-keyboard="false" onclick="modalAnimation('shake', 'answer')">
			<div class="modal-dialog" id="answerDialog">
				<div class="modal-content">
					<div class="modal-header my-panel-success text-center">
						<button type="button" class="close" aria-hidden="true" data-dismiss="modal" onclick="JavaScript:history.back(-1);"><span>&times;</span></button>
						<span class="glyphicon glyphicon-user modal-header-icon"></span>
						<h3 class="modal-header-title" id="answerModalLabel">问 卷 回 答</h3>
					</div>
					<div class="modal-body">
					</div>
				</div>
			</div>
		</div>
		<script>
			$('#answerModal').on('shown.bs.modal', function () {
				centerModals('answer');
			});
			$('#answerModal').on('hidden.bs.modal', function () {
				$('#answerDialog').removeClass('shake animated');
			});
		</script>

		<!-- Modal alert confirm -->
		<%@include file="../front_modal_acpd.html" %>
	</body>
</html>
