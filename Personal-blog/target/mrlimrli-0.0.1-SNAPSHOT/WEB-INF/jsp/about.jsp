<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<meta name="viewport"    content="width=device-width, initial-scale=1.0">
	<meta name="keywords" content="黎先生,Mr.li,博客,黎先生的博客,Mr.li's Blog" />
	<meta name="author" content="黎先生,Mr.li">
	
	<link rel="shortcut icon" href="/static/assets/images/favicon.ico" type="image/x-icon" />
	
	<title>About | Mr.li's Blog</title>

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
 						<h1 class="entry-title">关于</h1>
					</header> 
					<div class="entry-content">
						${announcement.content }
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
		</div> <!-- /row comments -->
		<div class="clearfix"></div>

	</div>	<!-- /container -->

</main><br>

<%@ include file="footer.jsp" %>
<%@ include file="commonJS.jsp" %>
</body>
</html>
