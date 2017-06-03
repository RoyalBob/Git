$(document).ready(function () {
	//保存文章
	$("#save").click(function(){
		var title = $("#title").val();
		var content = CKEDITOR.instances.textAreaCK.getData();
		if (title == "" || title == null) {
			alert("标题不能为空");
			return;
		}
		if (content == "" || content == null) {
			alert("内容不能为空");
			return;
		}
		$.ajax({
			type:"post",
			url:"/admin/saveArticle",
			async:false,
			data:{
				title:title,
				content:content
			},success:function(data){
				if(data.code == "200"){
					location.href = data.url;
				} else if(data.code == "324") {
					location.href = data.url;
				} else{
					alert(data.msg);
				}
			},error:function(){
				alert("失败");
			}
		});
	});
	
	//发布文章
	$("#publish").click(function(){
		var title = $("#title").val();
		var content = CKEDITOR.instances.textAreaCK.getData();
		if (title == "" || title == null) {
			alert("标题不能为空");
			return;
		}
		if (content == "" || content == null) {
			alert("内容不能为空");
			return;
		}
		$.ajax({
			type:"post",
			url:"/admin/publishArticle",
			async:false,
			data:{
				title:title,
				content:content
			},success:function(data){
				if(data.code == "200"){
					location.href = data.url;
				} else if(data.code == "324") {
					location.href = data.url;
				} else{
					alert(data.msg);
				}
			},error:function(){
				alert("失败");
			}
		});
	});
	
	//编辑文章
	$(".editBt").click(function(){
		var objArray = document.getElementsByName("checkbox");
		var articleId="";
		for (i=0 ; i<objArray.length ; i++) {
		  if (objArray[i].checked == true) {
			  articleId = objArray[i].value;
		  }
		}
		if (articleId.length == 0) {
			alert("还未选中任何文章");
			return;
		} else {
			location.href = "/admin/toEditArticle/" + articleId;
		}
	});
	
	//保存编辑文章
	$("#editSave").click(function(){
		var articleId = $("#articleId").val();
		var title = $("#title").val();
		var content = CKEDITOR.instances.textAreaCK.getData();
		if (title == "" || title == null) {
			alert("标题不能为空");
			return;
		}
		if (content == "" || content == null) {
			alert("内容不能为空");
			return;
		}
		$.ajax({
			type:"post",
			url:"/admin/editArticle",
			async:false,
			data:{
				articleId:articleId,
				title:title,
				content:content,
				isPublish:0
			},success:function(data){
				if(data.code == "200"){
					location.href = data.url;
				}else{
					alert(data.msg);
				}
			},error:function(){
				alert("失败");
			}
		});
	});
	
	//发布编辑文章
	$("#editPublish").click(function(){
		var articleId = $("#articleId").val();
		var title = $("#title").val();
		var content = CKEDITOR.instances.textAreaCK.getData();
		if (title == "" || title == null) {
			alert("标题不能为空");
			return;
		}
		if (content == "" || content == null) {
			alert("内容不能为空");
			return;
		}
		$.ajax({
			type:"post",
			url:"/admin/editArticle",
			async:false,
			data:{
				articleId:articleId,
				title:title,
				content:content,
				isPublish:1
			},success:function(data){
				if(data.code == "200"){
					alert(data.msg);
					location.href = data.url;
				}else{
					alert(data.msg);
				}
			},error:function(){
				alert("失败");
			}
		});
	});
	
	//设置为推荐文章
	$("#recomBt").click(function(){
		var objArray = document.getElementsByName("checkbox");
		var articleIds="";
		for (i=0 ; i<objArray.length ; i++) {
		  if (objArray[i].checked == true) {
			  articleIds += objArray[i].value + ",";
		  }
		}
		if (articleIds == "") {
		  alert("还没有选中任何文章");
		  return;
		}
		$.ajax({
			type:"post",
			url:"/admin/setRecomrticle",
			async:false,
			data:{
				articleIds:articleIds
			},success:function(data){
				if(data.code == "200"){
					alert("成功设置" + data.setTotal + "篇文章为推荐文章");
					location.href = data.url;
				}else{
					alert(data.msg);
				}
			},error:function(){
				alert("失败");
			}
		});
	});
	
	//取消推荐文章
	$("#cancelRecomBt").click(function(){
		var objArray = document.getElementsByName("checkbox");
		var articleIds="";
		for (i=0 ; i<objArray.length ; i++) {
		  if (objArray[i].checked == true) {
			  articleIds += objArray[i].value + ",";
		  }
		}
		if (articleIds == "") {
		  alert("还没有选中任何文章");
		  return;
		}
		$.ajax({
			type:"post",
			url:"/admin/cancelRecomArticle",
			async:false,
			data:{
				articleIds:articleIds
			},success:function(data){
				if(data.code == "200"){
					alert("成功取消" + data.cancelTotal + "篇文章为推荐文章");
					location.href = data.url;
				}else{
					alert(data.msg);
				}
			},error:function(){
				alert("失败");
			}
		});
	});
	
	//文章移到废纸篓
	$(".trashBt").click(function(){
		var objArray = document.getElementsByName("checkbox");
		var articleIds="";
		for (i=0 ; i<objArray.length ; i++) {
		  if (objArray[i].checked == true) {
			  articleIds += objArray[i].value + ",";
		  }
		}
		if (articleIds == "") {
		  alert("还没有选中任何文章");
		  return;
		}
		if (confirm("移到废纸篓将删除文章标签、类型和推荐，确定要移到废纸篓吗？")) {
			$.ajax({
				type:"post",
				url:"/admin/addTrashArticle",
				async:false,
				data:{
					articleIds:articleIds
				},success:function(data){
					if(data.code == "200"){
						alert("成功移动" + data.delTotal + "篇文章到废纸篓");
						location.href = data.url;
					}else{
						alert(data.msg);
					}
				},error:function(){
					alert("失败");
				}
			});
		}
		
	});
	
	//移出废纸篓
	$("#cancelTrashBt").click(function(){
		var objArray = document.getElementsByName("checkbox");
		var articleIds="";
		for (i=0 ; i<objArray.length ; i++) {
		  if (objArray[i].checked == true) {
			  articleIds += objArray[i].value + ",";
		  }
		}
		if (articleIds == "") {
		  alert("还没有选中任何文章");
		  return;
		}
		$.ajax({
			type:"post",
			url:"/admin/cancelTrashArticle",
			async:false,
			data:{
				articleIds:articleIds
			},success:function(data){
				if(data.code == "200"){
					alert("成功移出" + data.cancelTotal + "篇文章");
					location.href = data.url;
				}else{
					alert(data.msg);
				}
			},error:function(){
				alert("失败");
			}
		});
	});
	
	//彻底删除文章
	$("#deleteBt").click(function(){
		var objArray = document.getElementsByName("checkbox");
		var articleIds="";
		for (i=0 ; i<objArray.length ; i++) {
		  if (objArray[i].checked == true) {
			  articleIds += objArray[i].value + ",";
		  }
		}
		if (articleIds == "") {
		  alert("还没有选中任何文章");
		  return;
		}
		$.ajax({
			type:"post",
			url:"/admin/delTrashArticle",
			async:false,
			data:{
				articleIds:articleIds
			},success:function(data){
				if(data.code == "200"){
					alert("彻底删除" + data.delTotal + "篇文章");
					location.href = data.url;
				}else{
					alert(data.msg);
				}
			},error:function(){
				alert("失败");
			}
		});
	});
	
	//改变文章状态
	$("#postBt").click(function(){
		var objArray = document.getElementsByName("checkbox");
		var articleIds="";
		for (i=0 ; i<objArray.length ; i++) {
		  if (objArray[i].checked == true) {
			  articleIds += objArray[i].value + ",";
		  }
		}
		if (articleIds == "") {
		  alert("还没有选中任何文章");
		  return;
		}
		$.ajax({
			type:"post",
			url:"/admin/changeArticleToPost",
			async:false,
			data:{
				articleIds:articleIds
			},success:function(data){
				if(data.code == "200"){
					alert("成功发布" + data.changeTotal + "篇文章");
					location.href = data.url;
				}else{
					alert(data.msg);
				}
			},error:function(){
				alert("失败");
			}
		});
	});
	
	//设置文章标签
	$("#addTagUl li").click(function(){
		var objArray = document.getElementsByName("checkbox");
		var articleIds="";
		for (i=0 ; i<objArray.length ; i++) {
		  if (objArray[i].checked == true) {
			  articleIds += objArray[i].value + ",";
		  }
		}
		if (articleIds == "") {
		  alert("还没有选中任何文章");
		  return;
		}
		var tagId = this.value;
		$.ajax({
			type:"post",
			url:"/admin/setArticleTag",
			async:false,
			data:{
				articleIds:articleIds,
				tagId:tagId
			},success:function(data){
				if(data.code == "200"){
					alert(data.msg);
					location.href = data.url;
				}else{
					alert(data.msg);
				}
			},error:function(){
				alert("失败");
			}
		});
	});
	
	//设置文章类型
	$("#addTypeUl li").click(function(){
		var objArray = document.getElementsByName("checkbox");
		var articleIds="";
		for (i=0 ; i<objArray.length ; i++) {
		  if (objArray[i].checked == true) {
			  articleIds += objArray[i].value + ",";
		  }
		}
		if (articleIds == "") {
		  alert("还没有选中任何文章");
		  return;
		}
		var typeId = this.value;
		$.ajax({
			type:"post",
			url:"/admin/setArticleType",
			async:false,
			data:{
				articleIds:articleIds,
				typeId:typeId
			},success:function(data){
				if(data.code == "200"){
					alert(data.msg);
					location.href = data.url;
				}else{
					alert(data.msg);
				}
			},error:function(){
				alert("失败");
			}
		});
	});
	
	//发布页和未发布页跳转
	$("#post li").click(function(){
		var key = this.value;
		if (key == 1) {
			location.href = "/admin/publishedArticle/1";
		}
		if (key == 2) {
			location.href = "/admin/unPublishArticle/1";
		}
	});
	
	//将文章移出某个类型
	$("#removeTypeBt").click(function(){
		var objArray = document.getElementsByName("checkbox");
		var articleIds="";
		for (i=0 ; i<objArray.length ; i++) {
		  if (objArray[i].checked == true) {
			  articleIds += objArray[i].value + ",";
		  }
		}
		if (articleIds == "") {
		  alert("还没有选中任何文章");
		  return;
		}
		var typeId = $("#typeIdBt").attr("value");
		$.ajax({
			type:"post",
			url:"/admin/removeArticleType",
			async:false,
			data:{
				typeId:typeId,
				articleIds:articleIds
			},success:function(data){
				if(data.code == "200"){
					location.href = data.url;
				}else{
					alert(data.msg);
				}
			},error:function(){
				alert("失败");
			}
		});
	});
	
	//将文章移出某个标签
	$("#removeTagBt").click(function(){
		var objArray = document.getElementsByName("checkbox");
		var articleIds="";
		for (i=0 ; i<objArray.length ; i++) {
		  if (objArray[i].checked == true) {
			  articleIds += objArray[i].value + ",";
		  }
		}
		if (articleIds == "") {
		  alert("还没有选中任何文章");
		  return;
		}
		var tagId = $("#tagIdBt").attr("value");
		$.ajax({
			type:"post",
			url:"/admin/removeArticleTag",
			async:false,
			data:{
				tagId:tagId,
				articleIds:articleIds
			},success:function(data){
				if(data.code == "200"){
					location.href = data.url;
				}else{
					alert(data.msg);
				}
			},error:function(){
				alert("失败");
			}
		});
	});
	
	//复选框
    jQuery('.ckbox input').click(function(){
      var t = jQuery(this);
      if(t.is(':checked')){
        t.closest('tr').addClass('selected');
      } else {
        t.closest('tr').removeClass('selected');
      }
    });
    
    //按标签获取文章
	$(".tagUl li").click(function(){
		var tagId = this.value;
		location.href = "/admin/getArticleByTag/" + tagId + "/1";
	});
	
	//按类型获取文章
	$(".typeUl li").click(function(){
		var typeId = this.value;
		location.href = "/admin/getArticleByType/" + typeId + "/1";
	});
	
	//已发布文章上一页
	$("#publishedPre").click(function(){
		var pageNum = $('#pageNum').val();
		var pageCount = $('#pageCount').val();
		if (pageNum < 1) {
			alert("查询页数不能小于1");
			return;
		}
		pageNum = parseInt(pageNum) - 1;
		location.href = "/admin/publishedArticle/" + pageNum;
	});
	
	//已发布文章下一页
	$("#publishedNext").click(function(){
		var pageNum = $('#pageNum').val();
		var pageCount = $('#pageCount').val();
		if (pageNum > pageCount) {
			alert("查询页数不能大于总页数");
			return;
		}
		pageNum = parseInt(pageNum) + 1;
		location.href = "/admin/publishedArticle/" + pageNum;
	});
	
	//未发布文章上一页
	$("#unPublishPre").click(function(){
		var pageNum = $('#pageNum').val();
		var pageCount = $('#pageCount').val();
		if (pageNum < 1) {
			alert("查询页数不能小于1");
			return;
		}
		pageNum = parseInt(pageNum) - 1;
		location.href = "/admin/unPublishArticle/" + pageNum;
	});
	
	//未发布文章下一页
	$("#unPublishNext").click(function(){
		var pageNum = $('#pageNum').val();
		var pageCount = $('#pageCount').val();
		if (pageNum > pageCount) {
			alert("查询页数不能大于总页数");
			return;
		}
		pageNum = parseInt(pageNum) + 1;
		location.href = "/admin/unPublishArticle/" + pageNum;
	});
	
	//类型文章上一页
	$("#typePre").click(function(){
		var pageNum = $('#pageNum').val();
		var pageCount = $('#pageCount').val();
		var typeId = $('#typeIdBt').val();
		if (pageNum < 1) {
			alert("查询页数不能小于1");
			return;
		}
		pageNum = parseInt(pageNum) - 1;
		location.href = "/admin/getArticleByType/" + typeId + "/" + pageNum;
	});
	
	//类型文章下一页
	$("#typeNext").click(function(){
		var pageNum = $('#pageNum').val();
		var pageCount = $('#pageCount').val();
		var typeId = $('#typeIdBt').val();
		if (pageNum > pageCount) {
			alert("查询页数不能大于总页数");
			return;
		}
		pageNum = parseInt(pageNum) + 1;
		location.href = "/admin/getArticleByType/" + typeId + "/" + pageNum;
	});
	
	//标签文章上一页
	$("#tagPre").click(function(){
		var pageNum = $('#pageNum').val();
		var pageCount = $('#pageCount').val();
		var tagId = $('#tagIdBt').val();
		if (pageNum < 1) {
			alert("查询页数不能小于1");
			return;
		}
		pageNum = parseInt(pageNum) - 1;
		location.href = "/admin/getArticleByTag/" + tagId + "/" + pageNum;
	});
	
	//标签文章下一页
	$("#tagNext").click(function(){
		var pageNum = $('#pageNum').val();
		var pageCount = $('#pageCount').val();
		var tagId = $('#tagIdBt').val();
		if (pageNum > pageCount) {
			alert("查询页数不能大于总页数");
			return;
		}
		pageNum = parseInt(pageNum) + 1;
		location.href = "/admin/getArticleByTag/" + tagId + "/" + pageNum;
	});
    
});

//文章详情
function readArticle(articleId) {
	location.href="/admin/getArticle/" + articleId;
}