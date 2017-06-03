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
  <link href="/static/css/helvetica.css.html" rel="stylesheet">
  <title>黎先生 · 博客 · 文章 · 类型</title>
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
        <h2><i class="fa fa-files-o"></i>文章<span>所有文章</span></h2>
        <div class="breadcrumb-wrapper">
          <span class="label">导航:</span>
          <ol class="breadcrumb">
            <li>后台</li>
            <li>文章</li>
            <li><a href="javascript:history.go(-1);">上一级</a></li>
            <li class="active">类型</li>
          </ol>
        </div>
      </div>
      <div class="contentpanel panel-email">
        <div class="row">
          <div class="col-sm-3 col-lg-2">
            <h5 class="subtitle">类型</h5>
            <ul class="typeUl nav nav-pills nav-stacked nav-email mb20">
              <c:forEach items="${types }" var="type" >
              <li value="${type.id }"><a href="javascript:void(0)"><i class="glyphicon glyphicon-flag"></i> ${type.name }</a></li>
              </c:forEach>
            </ul>
            <h5 class="subtitle">标签</h5>
            <ul class="tagUl nav nav-pills nav-stacked nav-email mb20">
              <c:forEach items="${tags }" var="tag" >
              <li value="${tag.id }"><a href="javascript:void(0)"><i class="glyphicon glyphicon-tag"></i> ${tag.name }</a></li>
              </c:forEach>
            </ul>
          </div><!-- col-sm-3 -->
          
          <div class="col-sm-9 col-lg-10">
            <div class="panel panel-default">
              <div class="panel-body">
                <div class="pull-right">
                  <div class="btn-group mr10">
                    <input id="typeIdBt" type="hidden" value="${typeId }" />
                    <button id="removeTypeBt" class="btn btn-sm btn-white tooltips" type="button" data-toggle="tooltip" title="移出此分类"><i class="glyphicon glyphicon-remove"></i></button>
                    <button class="trashBt btn btn-sm btn-white tooltips" type="button" data-toggle="tooltip" title="移到废纸篓"><i class="glyphicon glyphicon-trash"></i></button>
                  </div>
                  <div class="btn-group">
                    <input id="pageNum" type="hidden" value="${pageNum }" />
                    <input id="pageCount" type="hidden" value="${pageCount }" />
                    <c:if test="${pageNum == 1 }">
                      <button id="typePre" class="btn btn-sm btn-white disabled" type="button"><i class="glyphicon glyphicon-chevron-left"></i></button>
                    </c:if>
                    <c:if test="${pageNum != 1 }">
                      <button id="typePre" class="btn btn-sm btn-white" type="button"><i class="glyphicon glyphicon-chevron-left"></i></button>
                    </c:if>
                    <c:if test="${pageNum == pageCount }">
                      <button id="typeNext" class="btn btn-sm btn-white disabled" type="button"><i class="glyphicon glyphicon-chevron-right"></i></button>
                    </c:if>
                    <c:if test="${pageNum != pageCount }">
                      <button id="typeNext" class="btn btn-sm btn-white" type="button"><i class="glyphicon glyphicon-chevron-right"></i></button>
                    </c:if>
                  </div>
                </div><!-- pull-right -->
                <h5 class="subtitle mb5">按类型查看文章</h5>
                <p class="text-muted">每页显示${pageSize }篇，共${amount }篇</p>
                <div class="table-responsive">
                  <table class="table table-email">
                    <tbody>
                      <c:forEach items="${articleTypeDTOs }" var="article" >
                      <tr class="unread">
                        <td>
                          <div class="ckbox ckbox-success">
                            <input type="checkbox" name="checkbox" id="checkbox${article.id }" value="${article.id }">
                            <label for="checkbox${article.id }"></label>
                          </div>
                        </td>
                        <td>
                        </td>
                        <td onclick="readArticle(${article.id});">
                          <div class="media">
                            <div class="media-body">
                              <span class="media-meta pull-right"><fmt:formatDate value="${article.createtime }" pattern="yyyy-MM-dd HH:mm" /></span>
                              <h4 class="text-primary">${article.title }</h4>
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