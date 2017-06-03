<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta name="keywords" content="${article.title }" />
	<meta name="author" content="黎先生,Mr.li">
	
	<link rel="shortcut icon" href="/static/assets/images/favicon.ico" type="image/x-icon" />
	
	<title>Single | Mr.li's Blog</title>

	<%@ include file="commonCSS.jsp" %>
</head>
<body>

<header id="header">
	<div id="head" class="parallax" parallax-speed="1">
		<h1 id="logo" class="text-center">
			<span class="title">Mr.li's Blog</span>
			<span class="tagline">攻城师，爱生活，爱自由，也爱代码。世界很大，却已不再疯狂，如果我可以，只愿简单生活.<br>
		</h1>
	</div>
	<%@ include file="navbar.jsp" %>
</header>

<main id="main">

	<div class="container">
		
		<div class="row topspace">
			<div class="col-sm-8 col-sm-offset-2">
															
 				<article class="post">
					<header class="entry-header">
 						<h1 class="entry-title">${article.title }</h1>
						<div class="entry-meta"> 
 							<span class="posted-on"><fmt:formatDate value="${article.createtime}" pattern="yyyy-MM-dd HH:mm" /></span>			
 						</div> 
					</header> 
					<div class="entry-content">${article.content }</div> 
				</article>

			</div> 
		</div> <!-- /row post  -->

		<div class="row">
			<div class="col-sm-8 col-sm-offset-2">
				<div id="comments">	
					<h3 class="comments-title">${commentAmount } 条评论</h3>
					<a href="#comment-form" class="leave-comment">评论一下吧</a>
					
					<ol class="comments-list">
						<c:forEach items="${commentsAndReply }" var="commentAndReply">
						<li class="comment">
							<div>
								<img src="/static/assets/images/user.png" alt="Avatar" class="avatar">
								<div class="comment-meta">
									<span class="author">${commentAndReply.nickname }</span>
									<span class="date"><fmt:formatDate value="${commentAndReply.comment_createtime }" pattern="yyyy-MM-dd HH:mm" /></span>
								</div>
								<div class="comment-body">${commentAndReply.comment }</div>
							</div>
							<c:if test="${!empty commentAndReply.reply }">
							<ul class="children">
								<li class="comment">
									<div>
										<img src="/static/assets/images/head.jpg" class="avatar">
										<div class="comment-meta">
											<span class="author">Mr.li 回复 ${commentAndReply.nickname }</span>
											<span class="date"><fmt:formatDate value="${commentAndReply.reply_createtime }" pattern="yyyy-MM-dd HH:mm" /></span>
										</div><!-- .comment-meta -->
										<div class="comment-body">${commentAndReply.reply }</div>
									</div>
								</li>
							</ul>
							</c:if>
						</li>
						</c:forEach>
					</ol>
					
					<div class="clearfix"></div>

					<div id="respond">
						<h3 id="reply-title">评论一下吧</h3>
						<p class="text-muted">为防止广告等不良内容，评论经过审核才会显示</p>
						<form id="commentForm">
							<div class="form-group">
								<label for="inputName">称呼 <i class="text-danger">*</i></label>
								<input id="name" name="name" type="text" class="form-control" placeholder="这项是必填的哟">
							</div>
							<div class="form-group">
								<label for="inputEmail">邮箱 </label>
								<input id="email" name="email" type="text" class="form-control" placeholder="邮箱...">
							</div>
							<input id="articleId" type="hidden" value="${article.id }">
							<div class="form-group">
								<label for="inputComment">内容 <i class="text-danger">*</i></label>
								<textarea id="comment" name="comment" class="form-control" rows="6" placeholder="这项是必填的哟"></textarea>
							</div>
							<div class="row">
								<div class="col-md-8">
									<button type="reset" class="btn btn-action">清空</button>
								</div>
								<div class="col-md-4 text-right">
  									<button type="submit" class="btn btn-action">评论</button>
								</div>
						</form>
					</div> <!-- /respond -->
				</div>
			</div>
		</div> <!-- /row comments -->
		<div class="clearfix"></div>
		<br>
	</div>	<!-- /container -->

</main>

<%@ include file="footer.jsp" %>

<script src="http://cdn.bootcss.com/jquery/1.11.1/jquery.min.js"></script>
<script src="/static/js/jquery.validate.min.js"></script>
<script src="http://netdna.bootstrapcdn.com/bootstrap/3.0.0/js/bootstrap.min.js"></script>
<script src="/static/assets/js/template.js"></script>
<script src="/static/js/custom/index.min.js"></script>
</body>
</html>
