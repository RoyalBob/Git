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
				<h3 class="widget-title">Recent articles</h3>
				<div class="widget-body">
					<c:forEach items="${recentArticles }" var="recentArticle">
						<a href="javascript:void(0)" onclick="readArticle(${recentArticle.id});">${recentArticle.title }</a><br>
					</c:forEach>
				</div>
			</div>

			<div class="col-md-3 widget">
				<h3 class="widget-title">Tags</h3>
				<div class="widget-body">
					<ul class="tagArticleUl tags">
		                <c:forEach items="${tags }" var="tag">
		                  <li value="${tag.id }"><a href="javascript:void(0)" class="btn btn-primary">${tag.name }</a></li>
		                </c:forEach>
	                </ul>
				</div>
			</div>

		</div> <!-- /row of widgets -->
	</div>
</footer>

<footer id="underfooter">
	<div class="container">
		<div class="row">
			<div class="col-md-6 widget">
				<div class="widget-body">
					<p>京 ICP 备 15009209号-1</p>
				</div>
			</div>
			<div class="col-md-6 widget">
				<div class="widget-body">
					<p class="text-right">
						Designed and Developed by Mr.li<br> 
						Copyright © 2015 mrlimrli.com. All Rights Reserved.</p>
				</div>
			</div>
		</div> <!-- /row of widgets -->
	</div>
</footer>