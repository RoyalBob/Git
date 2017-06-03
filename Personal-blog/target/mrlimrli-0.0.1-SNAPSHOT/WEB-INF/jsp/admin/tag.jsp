<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
  <meta name="description" content="">
  <meta name="author" content="">
  <link href="/static/css/style.default.css" rel="stylesheet">
  <link href="/static/css/helvetica.css.html" rel="stylesheet">
  <title>黎先生 · 博客 · 标签</title>
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
        <h2><i class="fa fa-tags"></i>标签</h2>
        <div class="breadcrumb-wrapper">
          <span class="label">导航:</span>
          <ol class="breadcrumb">
            <li>后台</li>
            <li class="active">标签</li>
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
                    <button id="addTagBt" class="btn btn-sm btn-white tooltips" type="button" data-toggle="tooltip" title="添加"><i class="glyphicon glyphicon-plus-sign"></i></button>
                    <button id="delTagBt" class="btn btn-sm btn-white tooltips" type="button" data-toggle="tooltip" title="删除"><i class="glyphicon glyphicon-trash"></i></button>
                  </div>
                </div><!-- pull-right -->
                <h5 class="subtitle mb5">所有标签</h5>
                <div class="table-responsive">
                  <table class="table table-email">
                    <tbody>
                      <c:forEach items="${tags }" var="tag" >
                      <tr class="unread">
                        <td>
                          <div class="ckbox ckbox-success">
                            <input type="checkbox" name="checkbox" id="checkbox${tag.id }" value="${tag.id }">
                            <label for="checkbox${tag.id }"></label>
                          </div>
                        </td>
                        <td>
                        </td>
                        <td onclick="editTag(${tag.id});">
                          <div class="media">
                            <div class="media-body">
                              <a class="btn btn-success btn-sm">${tag.name }</a>
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
  <script src="/static/js/custom/tag.js"></script>
  
</body>
</html>