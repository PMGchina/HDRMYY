<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
   <head>
      <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
      <meta http-equiv="X-UA-Compatible" content="IE=edge">
      <meta name="viewport" content="width=device-width,initial-scale=1">
      <title>检验单/验血结果查询</title>

      <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/BOOTSTRAP/css/bootstrap.min.css">
      <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/web/my-theme.css">
      <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/BOOTSTRAP-DATE-PICKER/css/bootstrap-datetimepicker.min.css">
      <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/web/my-animate-3.5.1.min.css">
      <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/web/my-modal-acpd.css">
      <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/web/front-footer.css">
      <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/web/front-checkreport.css">
   </head>
   <body>
      <div class="container">
         <div class="page-header my-panel-default text-center">
            <span>检验单/验血结果查询</span>
         </div>
         <!-- 选择日期 -->
         <div class="row my-date-row">
            <div class="form-group col-xs-9">
               <div class="input-group date form_date" data-date="" data-date-format="yyyy-mm-dd">
                  <input id="startDate" class="form-control" size="10" type="text" value="" readonly>
                  <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
               </div>
            </div>
            <div class="form-group col-xs-3">
               <button type="button" id="btn-select-date" class="btn my-panel-info" onclick="clickSearchBtn()">搜 索</button>
            </div>
         </div>
         <!--检验单
         <div class="panel panel-warning">
            <div class="panel-heading">
               <a data-toggle="collapse" data-parent="#accordion" href="#collapse1">
                  <h4 class="panel-title">检验单-1</h4>
               </a>
            </div>
            <div id="collapse1" class="panel-collapse collapse in">
               <div class="row">
                  <div class="col-xs-2 my-label">姓名:</div>
                  <div class="col-xs-4">肖学林</div>
                  <div class="col-xs-2 my-label">性别:</div>
                  <div class="col-xs-4">男</div>
               </div>
               <hr>
               <div class="row my-panel-warning">
                  <div class="col-xs-2">英文名</div>
                  <div class="col-xs-2">中文名</div>
                  <div class="col-xs-2">结  果</div>
                  <div class="col-xs-1">态</div>
                  <div class="col-xs-2">单  位</div>
                  <div class="col-xs-3">参考区</div>
               </div>
               <hr>
               <div class="row">
                  <div class="col-xs-2">Cu</div>
                  <div class="col-xs-2">铜</div>
                  <div class="col-xs-2">10.8</div>
                  <div class="col-xs-1"></div>
                  <div class="col-xs-2">umol/L</div>
                  <div class="col-xs-3">8.5-39.5</div>
               </div>
            </div>
         </div>
         -->

         <div class="btn-group" id="btn-fn">
            <button type="button" class="btn my-panel-default col-xs-6" onclick="javascript:history.back(-1);">
               <strong class="glyphicon glyphicon-chevron-left"></strong><span>返  回</span>
            </button>
            <button type="button" class="btn my-panel-default col-xs-6" onclick="requestSend()">
               <span>请求发送</span><strong class="glyphicon glyphicon-refresh"></strong>
            </button>
         </div>
      </div>

      <div class="navbar-fixed-bottom my-panel-default text-center my-footer">吉林同益科技技术支持</div>

      <script type="text/javascript" src="${pageContext.request.contextPath}/static/BOOTSTRAP/js/jquery-2.2.4.min.js"></script>
      <script type="text/javascript" src="${pageContext.request.contextPath}/static/BOOTSTRAP/js/bootstrap.min.js"></script>
      <script type="text/javascript" src="${pageContext.request.contextPath}/static/BOOTSTRAP-DATE-PICKER/js/bootstrap-datetimepicker.min.js"></script>
      <script type="text/javascript" src="${pageContext.request.contextPath}/static/BOOTSTRAP-DATE-PICKER/js/bootstrap-datetimepicker.zh-CN.js"></script>
      <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/web/my-modal-utils.js"></script>
      <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/web/my-modal-acpd.js"></script>
      <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/web/front-checkreport-biz.js"></script>

      <!-- Modal alert confirm -->
      <%@include file="../front_modal_acpd.html" %>
   </body>
</html>
