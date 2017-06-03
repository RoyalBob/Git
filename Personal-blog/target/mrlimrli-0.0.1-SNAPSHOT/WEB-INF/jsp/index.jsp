<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta name="keywords" content="黎先生,Mr.li,博客,黎先生的博客,Mr.li's Blog" />
	<meta name="author" content="黎先生,Mr.li">
	
	<link rel="shortcut icon" href="/static/assets/images/favicon.ico" type="image/x-icon" />
	
	<title>黎先生的博客 · Mr.li's Blog</title>

	<%@ include file="commonCSS.jsp" %>
</head>
<body class="home">

<header id="header">
	<div id="head" class="parallax" parallax-speed="2">
		<h1 id="logo" class="text-center">
			<img class="img-circle" src="/static/assets/images/head.jpg" alt="">
			<span class="title">hey！I'm Mr.li</span>
			<span class="tagline">一个具有工匠精神的攻城师<br>
				<a href="mailto:i@mrlimrli.com">i@mrlimrli.com</a></span>
		</h1>
	</div>
	<%@ include file="navbar.jsp" %>
</header>

<main id="main">
	<div class="container">
		<div class="row section topspace">
			<div class="col-md-12">
				<p class="lead text-center text-muted">攻城师，爱生活，爱自由，也爱代码世界很大，却已不再疯狂，如果我可以，只愿简单生活。</p>
			</div>
		</div> <!-- / section -->
		
		<div class="row section featured topspace">
			<h2 class="section-title"><span>Recommend Articles</span></h2>
			<div class="row">
				<c:forEach items="${recomArticles }" var="recomArticle">
				<div class="col-sm-6 col-md-3">
					<h3 class="text-center">${recomArticle.title }</h3>
					<p>${recomArticle.content }</p>
					<p class="text-center"><a href="javascript:void(0)" onclick="readArticle(${recomArticle.id});" class="btn btn-action">阅读</a></p>
				</div>
				</c:forEach>
			</div>
		</div>
	
		<div class="row section recentworks topspace">
			<h2 class="section-title"><span>History</span></h2>
			<div class="thumbnails recentworks row">
				
				<div class="col-xs-12 col-sm-6 col-md-4 col-lg-4">
					<a class="thumbnail" href="javascript:void(0)">
						<span class="img">
							<img src="/static/assets/images/index201505.png" alt="">
							<span class="cover"><span class="more">第一版：首页</span></span>
						</span>
						<span class="title">用了一个类似锁屏的样式，通过点击下方“我的博客”按钮进入博客。<br>PS：很丑有木有</span>
					</a>
				</div>
				
				<div class="col-xs-12 col-sm-6 col-md-4 col-lg-4">
					<a class="thumbnail" href="javascript:void(0)">
						<span class="img">
							<img src="/static/assets/images/blog201505.png" alt="">
							<span class="cover"><span class="more">第一版：博客列表</span></span>
						</span>
						<span class="title">将博客列表设计成一个一个色块，通过js自适应高度和宽度</span>
					</a>
				</div>
				
				<div class="col-xs-12 col-sm-6 col-md-4 col-lg-4">
					<a class="thumbnail" href="javascript:void(0)">
						<span class="img">
							<img src="/static/assets/images/single201505.png" alt="">
							<span class="cover"><span class="more">第一版：文章详情</span></span>
						</span>
						<span class="title">整体布局分为3块，文章放到左侧，导航和右侧分类、最近文章固定位置</span>
					</a>
				</div>
			</div>
		</div> <!-- /section -->
	</div>	<!-- /container -->

</main>

<%@ include file="footer.jsp" %>
<%@ include file="commonJS.jsp" %>
</body>
</html>
