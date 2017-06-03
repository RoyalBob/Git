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

  <title>黎先生 · 博客 · 文章废纸篓</title>

  <link href="/static/css/style.default.css" rel="stylesheet">
  <link href="/static/css/helvetica.css.html" rel="stylesheet">
</head>

<body>
  <div id="preloader">
    <div id="status"><i class="fa fa-spinner fa-spin"></i></div>
  </div>
  <section>
    <%@ include file="leftPanel.jsp" %>
    <div class="mainpanel">
      <%@ include file="header.jsp" %>
      <div class="pageheader">
        <h2><i class="glyphicon glyphicon-trash"></i>文章<span>废纸篓</span></h2>
        <div class="breadcrumb-wrapper">
          <span class="label">导航:</span>
          <ol class="breadcrumb">
            <li>后台</li>
            <li><a href="javascript:history.go(-1);">上一级</a></li>
            <li class="active">废纸篓</li>
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
                    <button id="cancelTrashBt" class="btn btn-sm btn-white tooltips" type="button" data-toggle="tooltip" title="取消删除"><i class="fa fa-rotate-left"></i></button>
                  	<button id="deleteBt" class="btn btn-sm btn-white tooltips" type="button" data-toggle="tooltip" title="彻底删除"><i class="fa fa-trash-o"></i></button>
                  </div>
                </div><!-- pull-right -->
                <h5 class="subtitle mb5">废纸篓</h5>
                <p class="text-muted">共${amount }篇（若“取消删除”，需重新设置标签和类型）</p>
                <div class="table-responsive">
                  <table class="table table-email">
                    <tbody>
                      <c:forEach items="${articles }" var="article" >
                      <tr class="unread">
                        <td>
                          <div class="ckbox ckbox-success">
                            <input type="checkbox" name="checkbox" id="checkbox${article.id }" value="${article.id }" />
                            <label for="checkbox${article.id }" />
                          </div>
                        </td>
                        <td>
                        </td>
                        <td onclick="readArticle(${article.id});">
                          <div class="media">
                            <div class="media-body">
                              <span class="media-meta pull-right"><fmt:formatDate value="${article.createtime }" pattern="yyyy-MM-dd HH:mm" /></span>
                              <h4 class="text-primary">${article.title }</4>
                              <small class="text-muted"></small>
                              <p class="email-summary">${article.content }</p>
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
  <script src="/static/js/custom/article.js"></script>

</body>
</html>
