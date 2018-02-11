<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width,initial-scale=1">
		<title>问卷编辑</title>

		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/BOOTSTRAP/css/bootstrap.min.css">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/web/my-animate-3.5.1.min.css">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/web/front-comm.css">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/web/my-theme.css">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/web/my-modal-list-group.css">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/web/my-modal-acpd.css">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/web/front-footer.css">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/web/front-question-edit.css">
	</head>
	<body>
		<div class="container-fluid">
			<div class="newQuesArea"></div>

			<div class="makeQuesArea">
				<div class="dropdown">
					<button type="button" class="btn btn-info btn-md dropdown-toggle" data-toggle="dropdown">
						请选题类<span class="glyphicon glyphicon-chevron-down"></span>
					</button>
					<ul class="dropdown-menu" role="menu">
						<li onclick="makeQues(1, '单选题')">单选题</li>
					</ul>
				</div>
				<div class="makeQuesBtns">
					<button type="button" class="btn btn-success" onclick="JavaScript:history.back(-1);"><span>取 消</span></button>
					<button type="button" class="btn btn-success" onclick="submitQuestionnaire()"><span>提 交</span></button>
				</div>
			</div>

			<!--单选-->
			<div class="danx hide">
				<textarea class="inputText inputTextarea" placeholder=""></textarea>
				<div class="container items">
					<div class="row text-center itemsIn">
						<div class="col-xs-1 my-col"><input type="radio" class="ctrlbox" value=""></div>
						<div class="col-xs-6 my-col"><input type="text" class="optionText" value="" placeholder="请输入选项名..."></div>
						<div class="col-xs-3 my-col"><label class="itemFill"><input type="checkbox" value=""><span>填空</span></label></div>
						<div class="col-xs-2 my-col"><a href="javascript:void(0)" class="delItem">删除</a></div>
					</div>
				</div>
				<div class="container">
					<div class="row">
						<div class="col-xs-1 my-col"></div>
						<div class="col-xs-6 my-col"><a href="javascript:void(0)" class="addItem"><span>增加选项</span></a></div>
						<div class="col-xs-5 text-center my-col"><label class="mustAnswer"><input type="checkbox" value=""><span>必答题</span></label></div>
					</div>
				</div>
				<div class="editOverBtns">
					<button type="button" class="btn btn-info cancel">取 消</button>
					<button type="button" class="btn btn-success finish">完 成</button>
				</div>
			</div>

			<!--多选-->
			<div class="duox hide">
				<textarea class="inputText inputTextarea" placeholder=""></textarea>
				<div class="container items">
					<div class="row text-center itemsIn">
						<div class="col-xs-1 my-col"><input type="checkbox" class="ctrlbox" value=""></div>
						<div class="col-xs-6 my-col"><input type="text" class="optionText" value="" placeholder="请输入选项名..."></div>
						<div class="col-xs-3 my-col"><label class="itemFill"><input type="checkbox" value=""><span>填空</span></label></div>
						<div class="col-xs-2 my-col"><a href="javascript:void(0)" class="delItem">删除</a></div>
					</div>
				</div>
				<div class="container">
					<div class="row">
						<div class="col-xs-1 my-col"></div>
						<div class="col-xs-6 my-col"><a href="javascript:void(0)" class="addItem"><span>增加选项</span></a></div>
						<div class="col-xs-5 text-center my-col"><label class="mustAnswer"><input type="checkbox" value=""><span>必答题</span></label></div>
					</div>
				</div>
				<div class="editOverBtns">
					<button type="button" class="btn btn-info cancel">取 消</button>
					<button type="button" class="btn btn-success finish">完 成</button>
				</div>
			</div>

			<!--问答-->
			<div class="wend hide">
				<textarea class="inputText inputTextarea" style="margin-bottom:-5px" placeholder=""></textarea>
				<label class="mustAnswer" style="margin:0 0 0 43%"><input type="checkbox" value=""><span>必答题</span></label>
				<div class="editOverBtns">
					<button type="button" class="btn btn-info cancel">取 消</button>
					<button type="button" class="btn btn-success finish">完 成</button>
				</div>
			</div>

			<!--评分-->
			<div class="pfen hide">
				<textarea class="inputText inputTextarea" style="margin-bottom:-5px" placeholder=""></textarea>
				<label class="mustAnswer" style="margin:0 0 0 43%"><input type="checkbox" value=""><span>必答题</span></label>
				<div class="editOverBtns">
					<button type="button" class="btn btn-info cancel">取 消</button>
					<button type="button" class="btn btn-success finish">完 成</button>
				</div>
			</div>

			<!--矩阵-->
			<div class="juzn hide">
				<textarea class="inputText inputTextarea" placeholder=""></textarea>
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tbody>
						<tr>
							<td><div class="juznTableLR">&nbsp;左标题</div>
							<td>
								<div class="juznTableLR">
									&nbsp;选项类型：
									<input type="radio" name="xz" value="0" checked="checked" class="danORduo"><span>单选</span>
									<input type="radio" name="xz" value="1" class="danORduo"><span>多选</span>
								</div>
							</td>
						</tr>
						<tr>
							<td style="width:30%;vertical-align:top"><textarea class="leftTextarea" placeholder="填写格式：TV1,TV2,TV3"></textarea></td>
							<td>
								<div class="items">
									<div class="itemsIn">
										<input type="text" class="juznOptionMark" value="" placeholder="请输入选项名...">
										<label class="juznItemFill"><input type="checkbox" value="" disabled="disabled"><span>填空</span></label>
										<a href="javascript:void(0)" class="juznItemDel">删除</a>
									</div>
								</div>
								<a href="javascript:void(0)" class="juznAddItem"><span>增加选项</span></a>
								<label class="juznMustAnswer"><input type="checkbox" value=""><span>必答题</span></label>
							</td>
						</tr>
					</tbody>
				</table>
				<div class="editOverBtns">
					<button type="button" class="btn btn-info cancel">取 消</button>
					<button type="button" class="btn btn-success finish">完 成</button>
				</div>
			</div>

		</div>

		<div class="navbar-fixed-bottom my-panel-default text-center my-footer">吉林同益科技技术支持</div>

		<script type="text/javascript" src="${pageContext.request.contextPath}/static/BOOTSTRAP/js/jquery-2.2.4.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/static/BOOTSTRAP/js/bootstrap.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/web/my-modal-utils.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/web/my-modal-acpd.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/web/front-question-edit-biz.js"></script>

		<div class="modal fade" id="editModal" tabindex="-1" role="dialog" aria-labelledby="editModalLabel"
			  aria-hidden="true" data-backdrop="static" data-keyboard="false" onclick="modalAnimation('shake', 'edit')">
			<div class="modal-dialog" id="editDialog">
				<div class="modal-content">
					<div class="modal-header my-panel-warning text-center">
						<button type="button" class="close" aria-hidden="true" data-dismiss="modal" onclick="JavaScript:history.back(-1);"><span>&times;</span></button>
						<span class="glyphicon glyphicon-user modal-header-icon"></span>
						<h3 class="modal-header-title" id="editModalLabel">问 卷 编 辑</h3>
					</div>
					<div class="modal-body">
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-xs btn-info" data-dismiss="modal" onclick="JavaScript:history.back(-1);">
							<span class="glyphicon glyphicon-off"></span>&nbsp;关 闭
						</button>
						<button type="button" class="btn btn-xs btn-success" data-dismiss="modal">
							<span class="glyphicon glyphicon-book"></span>&nbsp;新建问卷
						</button>
					</div>
				</div>
			</div>
		</div>
		<script>
			$('#editModal').on('shown.bs.modal', function () {
				centerModals('edit');
			});
			$('#editModal').on('hidden.bs.modal', function () {
				$('#editDialog').removeClass('shake animated');
			});
		</script>

		<!-- Modal alert confirm -->
		<%@include file="../front_modal_acpd.html" %>
	</body>
</html>
