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
  <link rel="shortcut icon" href="images/favicon.png" type="image/png">
  <link href="/static/css/style.default.css" rel="stylesheet">
  <title>黎先生 · 博客 · 留言详情</title>
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
        <h2><i class="fa fa-envelope"></i> 留言 <span>留言详情</span></h2>
        <div class="breadcrumb-wrapper">
          <span class="label">导航:</span>
          <ol class="breadcrumb">
            <li>后台</li>
            <li><a href="javascript:history.go(-1);">上一级</a></li>
            <li class="active">阅读留言</li>
          </ol>
        </div>
      </div>
      <div class="contentpanel panel-email">
        <div class="row">
          <div class="col-sm-12">
            <div class="panel panel-default panel-blog">
              <div class="panel-body">
                <h4 class="blogsingle-title">${message.message }</h4>
                <ul class="blog-meta">
                  <li>by: ${message.name }</li>
                  <li>${message.email }</li>
                  <li>${message.homepage }</li>
                </ul>
                <c:if test="${empty messageReply.reply }">
	              <button id="replyBt" value="${message.id }" class="btn btn-primary btn-xs mb10 pull-right" data-toggle="modal" data-target="#myModal">回复</button>
	            </c:if>
              </div><!-- panel-body -->
            </div><!-- panel -->
            <div class="mb30"></div>
            <ul class="media-list comment-list">
              <c:if test="${!empty messageReply.reply }">
	            <li class="media">
		          <div class="media-body">
		            <h4>黎先生 <small class="text-muted">回复 </small> ${message.name }</h4> 
		            <small class="text-muted">时间：<fmt:formatDate value="${messageReply.createtime }" pattern="yyyy-MM-dd HH:mm" /></small>
		            <p>${messageReply.reply }</p>
	              </div>
		        </li><!-- media -->
	          </c:if>
	        </ul>
          </div>
        </div><!-- mainpanel -->
      </section>
      
     <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
					<h4 class="modal-title" id="myModalLabel">回复</h4>
				</div>
				<form>
					<div class="modal-body">
						<textarea id="replyMessage" placeholder="内容" rows="5" class="form-control"></textarea>
						<div class="mb10"></div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
						<button id="saveReplyBt" type="button" class="btn btn-primary">确定</button>
					</div>
				</form>
			</div><!-- modal-content -->
		 </div><!-- modal-dialog -->
	  </div>

      <%@ include file="commonJS.jsp" %>
      <script src="/static/js/jquery-migrate-1.2.1.min.js"></script>
      <script src="/static/js/jquery.sparkline.min.js"></script>
      <script src="/static/js/toggles.min.js"></script>

	  <script src="/static/js/custom/readMessage.js"></script>
	  
    </body>
</html>