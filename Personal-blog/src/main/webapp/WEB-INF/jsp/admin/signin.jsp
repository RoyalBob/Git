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
  <title>黎先生 · 博客 · 登陆</title>
</head>
<body class="signin">
  <section>
    <div class="signinpanel">
      <div class="row">
        <div class="col-md-7">
          <div class="signin-info">
            <div class="logopanel">
              <h1>
                <span>[</span> 黎先生博客后台 <span>]</span>
              </h1>
            </div>
            <!-- logopanel -->
            <div class="mb20"></div>
            <h5>
              <strong>欢迎来到博客后台管理系统</strong>
            </h5>
            <div class="row">
              <div class="col-xs-6">
                <div class="panel panel-warning panel-alt widget-today">
                  <div class="panel-heading text-center">
                    <i class="fa fa-calendar-o"></i>
                  </div>
                  <div class="panel-body text-center">
                    <h3 class="today">${date} </h3>
                  </div>
                  <!-- panel-body -->
                </div>
                <!-- panel -->
              </div>
            </div>
            <div class="mb20"></div>
          </div>
          <!-- signin0-info -->
        </div>
        <!-- col-sm-7 -->
        <div class="col-md-5">
          <form method="post" action="login">
            <h4 class="nomargin">登录</h4>
            <p class="mt5 mb20">登录以进入系统</p>
            <input type="text" id="username" name="username"  class="form-control uname" placeholder="用户名" />
            <input type="password" id="password" name="password" class="form-control pword" placeholder="密码"  />
            <button type="submit" id="login" class="btn btn-success btn-block">登 录</button>
          </form>
        </div>
        <!-- col-sm-5 -->
      </div>
      <!-- row -->
      <div class="signup-footer">
        <div class="pull-left">&copy; 2014. All Rights Reserved. mrlimrli.com</div>
      </div>
      <!-- signin -->
      </div>
    </section>

    <%@ include file="commonJS.jsp" %>
    <script src="/static/js/jquery-migrate-1.2.1.min.js"></script>
    <script src="/static/js/jquery.sparkline.min.js"></script>
    <script src="/static/js/toggles.min.js"></script>
    <script src="js/custom/signin.js"></script>
    
  </body>
  </html>