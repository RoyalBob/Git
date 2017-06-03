<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
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
  
  <title>黎先生 · 博客 · 发布文章</title>
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
        <h2><i class="fa fa-edit"></i>文章<span>发布文章</span></h2>
        <div class="breadcrumb-wrapper">
          <span class="label">导航:</span>
          <ol class="breadcrumb">
            <li>后台</li>
            <li><a href="javascript:history.go(-1);">上一级</a></li>
            <li class="active">发布文章</li>
          </ol>
        </div>
      </div>
      <div class="contentpanel panel-email">
        <div class="row">
          <div class="col-sm-11 col-lg-12">
            <div class="panel panel-default">
              <div class="panel-body">
                <div class="form-group">
                  <input type="text" id="title" placeholder="标题..." class="form-control" />
                </div>
                <div class="form-group">
                  <label>内容：（1、若内容中有图片，需手动在数据库中设置图片路径，并在源码中图片属性增加class="img-responsive"；2、若无图片，色块颜色在数据库中设置）</label>
                </div>
                <textarea id="textAreaCK" placeholder="内容..." class="ckeditor" cols="20" rows="30"></textarea>
              </div><!-- panel-body -->
              <div class="panel-footer">
                <button id="save" class="btn btn-primary">保存</button>
                <button id="publish" class="btn btn-primary">发布</button>
              </div>
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
  <script src="/static/js/ckeditor/ckeditor.js"></script>
  <script src="/static/js/custom/article.js"></script>
  
</body>
</html>