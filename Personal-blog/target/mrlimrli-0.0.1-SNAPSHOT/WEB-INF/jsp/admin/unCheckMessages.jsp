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
  <link href="/static/css/helvetica.css.html" rel="stylesheet">
  <title>黎先生 · 博客 · 留言</title>
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
        <h2><i class="fa fa-envelope"></i>留言</h2>
        <div class="breadcrumb-wrapper">
          <span class="label">导航:</span>
          <ol class="breadcrumb">
            <li>后台</li>
            <li><a href="javascript:history.go(-1);">上一级</a></li>
            <li class="active">未审核留言</li>
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
                    <button class="passBt btn btn-sm btn-white tooltips" type="button" data-toggle="tooltip" title="通过审核"><i class="glyphicon glyphicon-ok-sign"></i></button>
                    <button class="unPassBt btn btn-sm btn-white tooltips" type="button" data-toggle="tooltip" title="不通过审核"><i class="glyphicon glyphicon-remove-sign"></i></button>
                    <button class="delBt btn btn-sm btn-white tooltips" type="button" data-toggle="tooltip" title="删除"><i class="glyphicon glyphicon-trash"></i></button>
                  </div>
                  <div class="btn-group mr10">
                    <button data-toggle="dropdown" class="btn btn-sm btn-white dropdown-toggle tooltips" type="button" title="分类查看">
                      <i class="glyphicon glyphicon-th-list"></i>
                      <span class="caret"></span>
                    </button>
                    <ul class="messagesType dropdown-menu">
                      <li value="3"><a href="javascript:void(0)"><i class="glyphicon glyphicon-exclamation-sign"></i> 未审核</a></li>
                      <li value="1"><a href="javascript:void(0)"><i class="glyphicon glyphicon-ok-sign"></i> 已通过审核</a></li>
                      <li value="2"><a href="javascript:void(0)"><i class="glyphicon glyphicon-remove-sign"></i> 未通过审核</a></li>
                    </ul>
                  </div>
                  <div class="btn-group">
                    <input id="pageNum" type="hidden" value="${pageNum }" />
                    <input id="pageCount" type="hidden" value="${pageCount }" />
                    <c:if test="${pageNum == 1 }">
                      <button id="unCheckMessagePre" class="btn btn-sm btn-white disabled" type="button"><i class="glyphicon glyphicon-chevron-left"></i></button>
                    </c:if>
                    <c:if test="${pageNum != 1 }">
                      <button id="unCheckMessagePre" class="btn btn-sm btn-white" type="button"><i class="glyphicon glyphicon-chevron-left"></i></button>
                    </c:if>
                    <c:if test="${pageNum == pageCount }">
                      <button id="unCheckMessageNext" class="btn btn-sm btn-white disabled" type="button"><i class="glyphicon glyphicon-chevron-right"></i></button>
                    </c:if>
                    <c:if test="${pageNum != pageCount }">
                      <button id="unCheckMessageNext" class="btn btn-sm btn-white" type="button"><i class="glyphicon glyphicon-chevron-right"></i></button>
                    </c:if>
                  </div>
                </div><!-- pull-right -->
                <h5 class="subtitle mb5">未审核留言</h5>
                <p class="text-muted">每页显示${pageSize }条，共${amount }条</p>
                <div class="table-responsive">
                  <table class="table table-email">
                    <tbody>
                      <c:forEach items="${messages }" var="message" >
                      <tr class="unread">
                        <td>
                          <div class="ckbox ckbox-success">
                            <input type="checkbox" name="checkbox" id="checkbox${message.id }" value="${message.id }">
                            <label for="checkbox${message.id }"></label>
                          </div>
                        </td>
                        <td>
                        </td>
                        <td onclick="readMessage(${message.id});">
                          <div class="media">
                            <div class="media-body">
                              <span class="media-meta pull-right"><fmt:formatDate value="${message.createtime }" pattern="yyyy-MM-dd HH:mm" /></span>
                              <h4 class="text-primary">${message.name }</h4>
                              <small class="text-muted"></small>
                              <p class="email-summary">${message.message }</p>
                            </div>
                          </div>
                        </td>
                      </tr>
                      </c:forEach>
                    </tbody>
                  </table>
                </div><!-- table-responsive -->
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
  <script src="/static/js/custom/messages.js"></script>
  
</body>
</html>