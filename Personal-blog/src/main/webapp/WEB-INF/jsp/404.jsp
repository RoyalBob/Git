<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<meta name="viewport"    content="width=device-width, initial-scale=1.0">
	<meta name="description" content="">
	<meta name="author"      content="">
	
	<link rel="shortcut icon" href="/static/assets/images/favicon.ico" type="image/x-icon" />
	
	<title>404 | Mr.li's Blog</title>

	<link rel="shortcut icon" href="assets/images/gt_favicon.png">
	<%@ include file="commonCSS.jsp" %>
</head>
<body>

<header id="header">
	<%@ include file="header.jsp" %>
	<%@ include file="navbar.jsp" %>
</header>

<main id="main">

	<div class="container">
		
		<div class="row topspace">
			<div class="col-sm-8 col-sm-offset-2">
															
 				<article class="post">
					<header class="entry-header">
 						<h1 class="entry-title">404</h1>
					</header> 
					<div class="entry-content">
						<center>
							<h5>你要访问的页面不存在或者已被移除！</h5>
						</center>
					</div> 
				</article>

			</div> 
		</div>

		<div class="row">
			<div class="col-sm-8 col-sm-offset-2">
				<div id="comments">	
					<div class="clearfix"></div>
					<div id="respond">
					</div> <!-- /respond -->
				</div>
			</div>
		</div>
		<div class="clearfix"></div>

	</div>

</main><br>

<footer id="footer">
	<div class="container">
		<div class="row">
			<div class="col-md-3 widget">
				<h3 class="widget-title">Friend's blog links</h3>
				<div class="widget-body">
					<p>
					Sefa's Blog: <br>
					<a href="http://gaoxuefeng.com" target="_blank">http://gaoxuefeng.com</a><br>
					Dandy's Blog: <br>
					<a href="http://blog.dandyweng.com/" target="_blank">http://blog.dandyweng.com/</a><br>
					</p>	
				</div>
			</div>

			<div class="col-md-3 widget">
				<h3 class="widget-title">Follow me</h3>
				<div class="widget-body">
					<p class="follow-me-icons">
						<a href="http://weibo.com/mrlimrli" target="_blank"><i class="fa fa-weibo fa-2"></i></a>
						<a href="https://github.com/mrlimrli" target="blank"><i class="fa fa-github fa-2"></i></a>
						<a href="mailto:i@mrlimrli.com"><i class="fa fa-envelope fa-2"></i></a>
					</p>
				</div>
			</div>

			<div class="col-md-3 widget">
				<h3 class="widget-title">SITE INFO</h3>
				<div class="widget-body">
					<p>京 ICP 备 15009209号-1</p>
				</div>
			</div>

			<div class="col-md-3 widget">
				<h3 class="widget-title">Copyright</h3>
				<div class="widget-body">
					<p class="text-left">
						Designed and Developed by Mr.li<br> 
						Copyright © 2015 mrlimrli.com. All Rights Reserved.</p>
				</div>
			</div>

		</div> <!-- /row of widgets -->
	</div>
</footer>

<%@ include file="commonJS.jsp" %>
</body>
</html>
