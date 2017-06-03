<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta name="keywords" content="黎先生,Mr.li,博客,黎先生的博客,Mr.li's Blog" />
	<meta name="author" content="黎先生,Mr.li">
	
	<link rel="shortcut icon" href="/static/assets/images/favicon.ico" type="image/x-icon" />
	
	<title>Message | Mr.li's Blog</title>

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
 						<h1 class="entry-title">留言</h1>
					</header> 
					<div class="entry-content">
						<p class="text-center">欢迎大家在这里留言。每条留言我都会仔细阅读，并尽量回复。</p>
                   		<p class="text-center">你如果填写了邮箱地址，当留言被回复时，将会给你发送邮件通知。</p>
                   		<p class="text-center">广告内容、无意义灌水等内容将会被果断删除。</p>
					</div> 
				</article>
			</div> 
		</div>

		<div class="row">
			<div class="col-sm-8 col-sm-offset-2">
				<div id="comments">	
					<h3 class="comments-title">${amount } 条留言</h3>
					<a href="#comment-form" class="leave-comment">Leave a Comment</a>
					<ol class="comments-list">
						<c:forEach items="${messagesAndReply }" var="messageAndReply">
						<li class="comment">
							<div>
								<img src="/static/assets/images/user.png" alt="Avatar" class="avatar">
								<div class="comment-meta">
									<span class="author">${messageAndReply.name }</span>
									<span class="date"><fmt:formatDate value="${messageAndReply.message_createtime }" pattern="yyyy-MM-dd HH:mm" /></span>
								</div>
								<div class="comment-body">${messageAndReply.message }</div>
							</div>
							<c:if test="${!empty messageAndReply.reply }">
							<ul class="children">
								<li class="comment">
									<div>
										<img src="/static/assets/images/head.jpg" class="avatar">
										<div class="comment-meta">
											<span class="author">Mr.li 回复 ${messageAndReply.name }</span>
											<span class="date"><fmt:formatDate value="${messageAndReply.reply_createtime }" pattern="yyyy-MM-dd HH:mm" /></span>
										</div><!-- .comment-meta -->
										<div class="comment-body">${messageAndReply.reply }</div>
									</div>
								</li>
							</ul>
							</c:if>
						</li>
						</c:forEach>
					</ol>
					
					<div class="clearfix"></div>

					<nav id="comment-nav-below" class="comment-navigation clearfix" role="navigation">
						<center>
							<ul id="messagePage" class="pagination">
								<c:if test="${pageCount <= pageParam }">
									<c:if test="${pageNum == 1 }">
										<li value="1" class="disabled"><a href="javascript:void(0);">&laquo;</a></li>
									</c:if>
									<c:if test="${pageNum != 1 }">
										<li value="1"><a href="javascript:void(0);">&laquo;</a></li>
									</c:if>
									<c:forEach begin="1" end="${pageCount }" var="i">
										<c:if test="${pageNum == i }">
											<li value="${i }" class="active"><a href="javascript:void(0)">${i }</a></li>
										</c:if>
										<c:if test="${pageNum != i }">
											<li value="${i }"><a href="javascript:void(0)">${i }</a></li>
										</c:if>
									</c:forEach>
									<c:if test="${pageNum == pageCount }">
						             	<li value="${pageCount }" class="disabled"><a href="javascript:void(0);">&raquo;</a></li>
						            </c:if>
						            <c:if test="${pageNum != pageCount }">
						             	<li value="${pageCount }"><a href="javascript:void(0);">&raquo;</a></li>
						            </c:if>
								</c:if>
								<c:if test="${pageCount > pageParam }">
									<c:if test="${pageNum <= pageParam/2 + 1 }">
										<c:if test="${pageNum == 1 }">
											<li value="1" class="disabled"><a href="javascript:void(0);">&laquo;</a></li>
										</c:if>
										<c:if test="${pageNum != 1 }">
											<li value="1"><a href="javascript:void(0);">&laquo;</a></li>
										</c:if>
										<c:forEach begin="1" end="${pageParam }" var="i">
											<c:if test="${pageNum == i }">
												<li value="${i }" class="active"><a href="javascript:void(0)">${i }</a></li>
											</c:if>
											<c:if test="${pageNum != i }">
												<li value="${i }"><a href="javascript:void(0)">${i }</a></li>
											</c:if>
										</c:forEach>
										<li value="${pageCount }"><a href="javascript:void(0);">&raquo;</a></li>
									</c:if>
									<c:if test="${pageNum > (pageParam/2 + 1) && pageNum < (pageCount - pageParam/2) }">
										<li value="1" class="active"><a href="javascript:void(0);">&laquo;</a></li>
										<li value="${pageNum - 2 }"><a href="javascript:void(0)">${pageNum - 2 }</a></li>
										<li value="${pageNum - 1 }"><a href="javascript:void(0)">${pageNum - 1 }</a></li>
										<li value="${pageNum }" class="active"><a href="javascript:void(0)">${pageNum }</a></li>
										<li value="${pageNum + 1 }"><a href="javascript:void(0)">${pageNum + 1 }</a></li>
										<li value="${pageNum + 2 }"><a href="javascript:void(0)">${pageNum + 2 }</a></li>
										<li value="${pageCount }" class="active"><a href="javascript:void(0);">&raquo;</a></li>
									</c:if>
									<c:if test="${pageNum >= (pageCount - pageParam/2) }">
										<li value="1"><a href="javascript:void(0);">&laquo;</a></li>
										<c:forEach begin="${pageCount - 4 }" end="${pageCount }" var="i">
											<c:if test="${pageNum == i }">
												<li value="${i }" class="active"><a href="javascript:void(0)">${i }</a></li>
											</c:if>
											<c:if test="${pageNum != i }">
												<li value="${i }"><a href="javascript:void(0)">${i }</a></li>
											</c:if>
										</c:forEach>
										<c:if test="${pageNum == pageCount }">
							             	<li value="${pageCount }" class="disabled"><a href="javascript:void(0);">&raquo;</a></li>
							            </c:if>
							            <c:if test="${pageNum != pageCount }">
							             	<li value="${pageCount }"><a href="javascript:void(0);">&raquo;</a></li>
							            </c:if>
									</c:if>
								</c:if>
							</ul>
						</center>
					</nav>

					<div id="respond">
						<h3 id="reply-title">留个言吧！</h3>
						<form id="messageForm">
							<div class="form-group">
								<label for="inputName">称呼 <i class="text-danger">*</i></label>
								<input id="name" name="name" type="text" class="form-control" placeholder="这项是必填的哟...">
							</div>
							<div class="form-group">
								<label for="inputEmail">邮箱</label>
								<input id="email" name="email" type="text" class="form-control" placeholder="邮箱...">
							</div>
							<div class="form-group">
								<label for="inputWeb">主页或微博</label>
								<input id="homepage" name="homepage" type="text" class="form-control" placeholder="http://">
							</div>
							<div class="form-group">
								<label for="inputComment">内容 <i class="text-danger">*</i></label>
								<textarea id="content" name="content" class="form-control" rows="6" placeholder="这项是必填的哟..."></textarea>
							</div>
							<div class="row">
								<div class="col-md-8">
									<button type="reset" class="btn btn-action">清空</button>
								</div>
								<div class="col-md-4 text-right">
  									<button type="submit" class="btn btn-action">留言</button>
								</div>
						</form>
					</div> <!-- /respond -->
				</div>
			</div>
		</div> <!-- /row comments -->
		<div class="clearfix"></div>

	</div>	<!-- /container -->

</main><br>

<%@ include file="footer.jsp" %>

<!-- JavaScript libs are placed at the end of the document so the pages load faster -->
<script src="http://cdn.bootcss.com/jquery/1.11.1/jquery.min.js"></script>
<script src="/static/js/jquery.validate.min.js"></script>
<script src="http://netdna.bootstrapcdn.com/bootstrap/3.0.0/js/bootstrap.min.js"></script>
<script src="/static/assets/js/template.js"></script>
<script src="/static/js/custom/index.min.js"></script>
</body>
</html>
