<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
  <meta name="description" content="">
  <meta name="author" content="">
  <link href="/static/css/style.default.css" rel="stylesheet">
  <title>黎先生 · 博客 · 公告</title>
  
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
        <h2><i class="fa fa-envelope"></i> 公告 </h2>
        <div class="breadcrumb-wrapper">
          <span class="label">导航:</span>
          <ol class="breadcrumb">
            <li>后台</li>
            <li class="active">公告</li>
          </ol>
        </div>
      </div>
      
      <div class="contentpanel panel-email">
        <div class="row">
          <div class="col-sm-11 col-lg-12">
            <div class="panel panel-default">
              <div class="panel-body">
                <div class="pull-right">
                  <div class="btn-group mr10">
                    <c:if test="${empty announcement }">
                    <button id="addAnnouncementBt" class="btn btn-sm btn-white tooltips" type="button" data-toggle="tooltip" title="添加"><i class="glyphicon glyphicon-plus-sign"></i></button>
                  </c:if>
                  <c:if test="${!empty announcement }">
                  <input id="announcementId" type="hidden" value="${announcement.id }">
                  <button id="editAnnouncementBt" class="btn btn-sm btn-white tooltips" type="button" data-toggle="tooltip" title="修改"><i class="glyphicon glyphicon-edit"></i></button>
                  <button id="delAnnouncementBt" class="btn btn-sm btn-white tooltips" type="button" data-toggle="tooltip" title="删除"><i class="glyphicon glyphicon-trash"></i></button>
                </c:if>
              </div>
            </div><!-- pull-right -->
            <div class="btn-group mr10">
            </div>
            <div class="read-panel">
              <div class="media">
                <div class="media-body">
                  <span class="media-meta pull-right"><fmt:formatDate value="${announcement.createtime }" pattern="yyyy-MM-dd HH:mm" /></span>
                  <h4 class="text-primary">黎先生</h4>
                  <small class="text-muted">email: i@mrlimrli.com</small>
                </div>
              </div><!-- media -->
              <p>${announcement.content }</p>
              <br />
            </div><!-- read-panel -->
          </div><!-- panel-body -->
        </div><!-- panel -->
      </div><!-- col-sm-9 --> 
    </div><!-- row -->
  </div>
</div><!-- mainpanel -->
</section>

<%@ include file="commonJS.jsp" %>
<script src="/static/js/jquery-migrate-1.2.1.min.js"></script>
<script src="/static/js/jquery.sparkline.min.js"></script>
<script src="/static/js/toggles.min.js"></script>
<script src="/static/js/custom/announcement.js"></script>

</body>
</html>