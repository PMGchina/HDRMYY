<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width,initial-scale=1">
		<title>主任风采</title>

		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/BOOTSTRAP/css/bootstrap.min.css">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/web/front-comm.css">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/web/my-theme.css">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/web/my-modal-photo.css">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/web/front-footer.css">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/web/front-director-mien.css">
	</head>
	<body>
		<div class="container-fluid">
			<div class="page-header my-panel-primary text-center">
				<span>主 任 风 采</span>
			</div>

			<button type="button" id="btn-back" class="btn my-panel-default btn-lg btn-block" onclick="JavaScript:history.back(-1);">
				<strong class="glyphicon glyphicon-circle-arrow-left"></strong><span>返  回</span>
			</button>
		</div>
		<div class="navbar-fixed-bottom my-panel-default text-center my-footer">吉林同益科技技术支持</div>

		<script type="text/javascript" src="${pageContext.request.contextPath}/static/BOOTSTRAP/js/jquery-2.2.4.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/static/BOOTSTRAP/js/bootstrap.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/web/my-modal-utils.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/web/front-director-mien-biz.js"></script>

		<div class="modal fade" id="photoModal" tabindex="-1" role="dialog" aria-labelledby="photoModalLabel"
			  aria-hidden="true">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header my-panel-success">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
							<span class="modal-header-close">&times;</span>
						</button>
						<h3 class="modal-header-title" id="photoModalLabel">照片</h3>
					</div>
					<div class="modal-body">
					</div>
				</div>
			</div>
		</div>
		<script>
						  $('#photoModal').on('shown.bs.modal', function () {
							  centerModals('photo');
						  });
		</script>
	</body>
</html>
