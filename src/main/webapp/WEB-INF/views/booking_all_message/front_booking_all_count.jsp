<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
   <head>
      <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
      <meta http-equiv="X-UA-Compatible" content="IE=edge">
      <meta name="viewport" content="width=device-width,initial-scale=1">
      <title>预约信息统计</title>

      <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/BOOTSTRAP/css/bootstrap.min.css">
      <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/BOOTSTRAP-DATE-PICKER/css/bootstrap-datetimepicker.min.css">
      <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/web/my-animate-3.5.1.min.css">
      <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/web/my-theme.css">
      <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/web/my-modal-list-group.css">
      <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/web/my-modal-acpd.css">
      <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/web/front-footer.css">
      <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/web/front-booking-all-count.css">
   </head>
   <body>
      <div class="container">
         <div class="page-header my-panel-warning text-center"><span>预 约 信 息 统 计</span></div>
         <!-- 选择日期 -->
         <div class="row">
            <div class="form-group col-xs-6">
               <div class="select-date-lable">选择：查询起始日期</div>
               <div id="form_date_1" class="input-group date form_date col-xs-12" data-date="" data-date-format="yyyy-mm-dd">
                  <input id="startDate" class="form-control" size="10" type="text" value="" readonly>
                  <!--<span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>-->
                  <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
               </div>
            </div>
            <div class="form-group col-xs-6">
               <div class="select-date-lable">选择：查询结束日期</div>
               <div id="form_date_2" class="input-group date form_date col-xs-12" data-date="" data-date-format="yyyy-mm-dd">
                  <input id="endDate" class="form-control" size="10" type="text" value="" readonly>
                  <!--<span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>-->
                  <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
               </div>
            </div>
         </div>

         <!-- Tab -->
         <ul id="myTab" class="nav nav-tabs my-panel-success">
            <li><a href="#ghDpt" data-toggle="tab">挂号科室</a></li>
            <li><a href="#ghDoc" data-toggle="tab">挂号医生</a></li>
         </ul>

         <div id="myTabContent" class="tab-content">
            <!-- 期间科室挂号所有预约统计 -->
            <div class="tab-pane fade in active" id="ghDpt">
               <div class="panel panel-info">
                  <div class="panel-heading">
                     <h3 class="panel-title text-center">挂号科室统计</h3>
                  </div>
                  <div class="panel-body">
                     <div class="row">
								<!--
                        <div class="col-xs-4 text-center my-col-option">科室名称1</div>
                        <div class="col-xs-8 my-col-progbar">
                           <div class="progress progress-striped active">
                              <div class="progress-bar progress-bar-info" role="progressbar" style="width:80%">111次80%</div>
                           </div>
                           <div class="progress">
                              <div class="progress-bar progress-bar-success" role="progressbar" style="width:60%">60%</div>
                           </div>
                        </div>
								-->
                     </div>
                     <div class="row">
                     </div>
                  </div>
               </div>
            </div>

            <!-- 期间医生挂号所有预约统计 -->
            <div class="tab-pane fade" id="ghDoc">
               <div class="panel panel-info">
                  <div class="panel-heading">
                     <h3 class="panel-title text-center">挂号医生统计</h3>
                  </div>
                  <div class="panel-body">
                     <div class="row">
                     </div>
                  </div>
               </div>
            </div>
         </div>

         <div class="btn-group" id="btn-fn">
            <button type="button" class="btn my-panel-default col-xs-6" onclick="javaScript:history.back(-1);">
               <strong class="glyphicon glyphicon-circle-arrow-left"></strong><span>返  回</span>
            </button>
            <button type="button" class="btn my-panel-info col-xs-6" onclick="refreshRecord()">
               <span>刷 新</span><strong class="glyphicon glyphicon-refresh"></strong>
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
      <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/web/front-booking-all-count-biz.js"></script>

      <!-- Modal alert confirm -->
      <%@include file="../front_modal_acpd.html" %>
   </body>
</html>
