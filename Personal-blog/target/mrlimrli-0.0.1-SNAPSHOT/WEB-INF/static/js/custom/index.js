$(document).ready(function () {
	//跳转
	$(".all").click(function(){
		location.href = "/blog";
	});
	$(".message").click(function(){
		location.href = "/message";
	});
	$(".about").click(function(){
		location.href = "/about";
	});
	
	//按类型文章
	$(".typeArticleUl li").click(function(){
		var typeArticleId = this.value;
		location.href="/blog/type/" + typeArticleId;
	});
	
	//按标签文章
	$(".tagArticleUl li").click(function(){
		var tagArticleId = this.value;
		location.href="/blog/tag/" + tagArticleId;
	});
	
	//所有文章分页
	$("#blogPage li").click(function(){
		var pageNum = this.value;
		location.href = "/blog/" + pageNum;
	});
	
	//类型文章分页
	$("#typeBlogPage li").click(function(){
		var pageNum = this.value;
		var typeId = $('#typeId').val();
		location.href = "/blog/type/" + typeId + "/" + pageNum;
	});
	
	//标签分页
	$("#tagBlogPage li").click(function(){
		var pageNum = this.value;
		var tagId = $('#tagId').val();
		location.href = "/blog/tag/" + tagId + "/" + pageNum;
	});
	
	//留言分页
	$("#messagePage li").click(function(){
		var pageNum = this.value;
		location.href = "/message/" + pageNum;
	});
	
	  // 评论表单
	  jQuery("#commentForm").validate({
	    highlight: function(element) {
	      jQuery(element).closest('.form-group').removeClass('has-success').addClass('has-error');
	    },
	    success: function(element) {
	      jQuery(element).closest('.form-group').removeClass('has-error');
	    },
	    rules: {  
            name: { required: true, maxlength: 18 },  
            comment: { required: true, maxlength: 500 }  
        },  
        messages: {  
            "name": {  
                required: "称呼不能为空",  
                maxlength:"最多输入18个字符"
            },  
            "comment": {  
                required:"评论内容不能为空",
                maxlength:"最多输入500个字符"
            }  
        },
        submitHandler: function(form) {
        	var articleId = $('#articleId').val();
        	var name = $('#name').val();
        	var email = $('#email').val();
        	var comment = $('#comment').val();
        	$.ajax({
    			type:"post",
    			url:"/addComment",
    			async:false,
    			data:{
    				articleId:articleId,
    				name:name,
    				email:email,
    				comment:comment
    			},success:function(data){
    				alert(data.msg);
    				location.href=data.url;
    			}
    		});
        } 
	  });
	  
	  // 留言表单
	  jQuery("#messageForm").validate({
	    highlight: function(element) {
	      jQuery(element).closest('.form-group').removeClass('has-success').addClass('has-error');
	    },
	    success: function(element) {
	      jQuery(element).closest('.form-group').removeClass('has-error');
	    },
	    rules: {  
            name: { required: true, maxlength: 18 },
            email: { maxlength: 18 },
            homepage: { maxlength: 50 },
            content: { required: true, maxlength: 500 }  
        },  
        messages: {  
            "name": {  
                required: "称呼不能为空",  
                maxlength:"最多输入18个字符"
            },  
            "email": {  
            	maxlength:"最多输入18个字符"
            },  
            "homepage": {  
            	maxlength:"最多输入50个字符"
            },  
            "content": {  
                required:"留言内容不能为空",
                maxlength:"最多输入500个字符"
            }  
        },
        submitHandler: function(form) {
        	var name = $('#name').val();
        	var email = $('#email').val();
        	var homepage = $('#homepage').val();
        	var content = $('#content').val();
        	$.ajax({
    			type:"post",
    			url:"/addMessage",
    			async:false,
    			data:{
    				name:name,
    				email:email,
    				homepage:homepage,
    				content:content
    			},success:function(data){
    				alert(data.msg);
    				location.href=data.url;
    			}
    		});
        } 
	  });
	  
});
//文章详情
function readArticle(articleId) {
	location.href="/blog/detail/" + articleId;
}
