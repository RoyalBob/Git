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
	
	<title>TypePost | Mr.li's Blog</title>

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
				<c:forEach items="${articles }" var="article">
				<c:if test="${empty article.image }">							
				<article class="post">
					<header class="entry-header">
 						<h1 class="entry-title"><a onclick="readArticle(${article.id});" href="javascript:void(0)" rel="bookmark">${article.title }</a></h1>
 						<div class="entry-meta"> 
 							<span class="posted-on"><fmt:formatDate value="${article.createtime}" pattern="yyyy-MM-dd" /></span>			
 						</div>
					</header> 
					<div class="entry-content"> 
						<p>${article.content }
						<a onclick="readArticle(${article.id});" href="javascript:void(0)" class="more-link">阅读全文&#8230;</a></p>
					</div> 
				</article>
 				</c:if>
 				<c:if test="${!empty article.image }">
 				<article class="post">
					<header class="entry-header">
 						<h1 class="entry-title"><a onclick="readArticle(${article.id});" href="javascript:void(0)" rel="bookmark">${article.title }</a></h1>
 						<div class="entry-meta"> 
 							<span class="posted-on"><fmt:formatDate value="${article.createtime}" pattern="yyyy-MM-dd" /></span>			
 						</div>
					</header> 
					<div class="entry-content"> 
						<p><img alt="" src="${article.image }"></p>
						<p>${article.content }
						<a onclick="readArticle(${article.id});" href="javascript:void(0)" class="more-link">阅读全文&#8230;</a></p>
					</div> 
				</article>
				</c:if>
				</c:forEach>
			</div> 
		</div>
	    <input id="typeId" type="hidden" value="${typeId }" />
		<center>
			<ul id="typeBlogPage" class="pagination">
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
	</div>	<!-- /container -->
</main>

<%@ include file="footer.jsp" %>
<%@ include file="commonJS.jsp" %>
</body>
</html>
