<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
  <meta name="description" content="">
  <meta name="author" content="">
  <link rel="shortcut icon" href="images/favicon.png" type="image/png">
  <link href="/static/css/style.default.css" rel="stylesheet">
  <script src="/static/js/custom/leftPanel.js"></script>
  <title>黎先生 · 博客后台</title>
</head>

<body>
  <!-- Preloader -->
  <div id="preloader">
    <div id="status"><i class="fa fa-spinner fa-spin"></i></div>
  </div>
  <section>
    <%@ include file="leftPanel.jsp" %>
    <div class="mainpanel">
      <%@ include file="header.jsp" %>
      <div class="pageheader">
        <h2><i class="fa fa-home"></i> 桌面 </h2>
        <div class="breadcrumb-wrapper">
          <span class="label">导航:</span>
          <ol class="breadcrumb">
            <li>后台</li>
            <li class="active">桌面</li>
          </ol>
        </div>
      </div>
      <div class="contentpanel">
        <div class="row">
          <div class="col-sm-6 col-md-3">
            <div class="panel panel-success panel-stat">
              <div class="panel-heading">
                <div class="stat">
                  <div class="row">
                    <div class="col-xs-4">
                      <img src="/static/images/is-user.png" alt="" />
                    </div>
                    <div class="col-xs-8">
                      <small class="stat-label">今日访问量</small>
                      <h1>${viewsToday }</h1>
                    </div>
                  </div><!-- row -->
                  <div class="mb15"></div>
                </div><!-- stat -->
              </div><!-- panel-heading -->
            </div><!-- panel -->
          </div><!-- col-sm-6 -->
          <div class="col-sm-6 col-md-3">
            <div class="panel panel-warning panel-stat">
              <div class="panel-heading">
                <div class="stat">
                  <div class="row">
                    <div class="col-xs-4">
                      <img src="/static/images/is-user.png" alt="" />
                    </div>
                    <div class="col-xs-8">
                      <small class="stat-label">历史访问量</small>
                      <h1>${viewsHistory }</h1>
                    </div>
                  </div><!-- row -->
                  <div class="mb15"></div>
                </div><!-- stat -->
              </div><!-- panel-heading -->
            </div><!-- panel -->
          </div><!-- col-sm-6 -->
          <div class="col-sm-6 col-md-3">
            <div class="panel panel-danger panel-stat">
              <div class="panel-heading">
                <div class="stat">
                  <div class="row">
                    <div class="col-xs-4">
                      <img src="/static/images/is-document.png" alt="" />
                    </div>
                    <div class="col-xs-8">
                      <small class="stat-label">今日评论量</small>
                      <h1>${todayCommentCount }</h1>
                    </div>
                  </div><!-- row -->
                  <div class="mb15"></div>
                </div><!-- stat -->
              </div><!-- panel-heading -->
            </div><!-- panel -->
          </div><!-- col-sm-6 -->
          <div class="col-sm-6 col-md-3">
            <div class="panel panel-primary panel-stat">
              <div class="panel-heading">
                <div class="stat">
                  <div class="row">
                    <div class="col-xs-4">
                      <img src="/static/images/is-document.png" alt="" />
                    </div>
                    <div class="col-xs-8">
                      <small class="stat-label">今日留言量</small>
                      <h1>${todayMsgCount }</h1>
                    </div>
                  </div><!-- row -->
                  <div class="mb15"></div>
                </div><!-- stat -->
              </div><!-- panel-heading -->
            </div><!-- panel -->
          </div><!-- col-sm-6 -->
        </div>
      </div><!-- contentpanel -->
    </div><!-- mainpanel -->
  </section>
  <div style="text-align:center;">
    <p>&copy; 2014. All Rights Reserved.mrlimrli.com</p>
  </div>

  <%@ include file="commonJS.jsp" %>
  <script src="/static/js/jquery-migrate-1.2.1.min.js"></script>
  <script src="/static/js/jquery.sparkline.min.js"></script>
  <script src="/static/js/toggles.min.js"></script>
  <script src="/static/js/dashboard.js"></script>
  
</body>
</html>