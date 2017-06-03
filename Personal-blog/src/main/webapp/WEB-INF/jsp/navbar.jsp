<nav class="navbar navbar-default navbar-sticky">
	<div class="container-fluid">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#navbar"> <span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span> </button>
		</div>
		<div id="navbar" class="navbar-collapse collapse">
			<ul class="nav navbar-nav">
				<li class="active"><a href="http://www.mrlimrli.com">Home </a></li>
				<li class="all active"><a href="javascript:vodi(0)">All </a></li>
				<li class="dropdown">
					<a href="javascript:void(0)" class="dropdown-toggle" data-toggle="dropdown">Type <b class="caret"></b></a>
					<ul class="typeArticleUl dropdown-menu">
						<c:forEach items="${types }" var="type">
						<li value="${type.id }"><a href="javascript:void(0)">${type.name }</a></li>
						</c:forEach>
					</ul>
				</li>
				<li><a class="message" href="javascript:void(0)">Message </a></li>
				<li><a class="about" href="javascript:void(0)">About </a></li>
			</ul>
		</div>	
	</div>	
</nav>