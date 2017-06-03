$(document).ready(function () {
	$('#admin').click(function(){
		location.href='/admin'
	});
	
	$('#postArticle').click(function(){
		location.href='/admin/postArticle'
	});
	
	$('#allArticle').click(function(){
		location.href='/admin/publishedArticle/1'
	});
	
	$('#recomArticle').click(function(){
		location.href='/admin/recomArticle'
	});
	
	$('#recentArticle').click(function(){
		location.href='/admin/recentArticle'
	});
	
	$('#trashArticle').click(function(){
		location.href='/admin/trashArticle'
	});
	
	$('#articleComment').click(function(){
		location.href='/admin/unCheckComments/1'
	});
	
	$('#message').click(function(){
		location.href='/admin/unCheckMessages/1'
	});
	
	$('#tag').click(function(){
		location.href='/admin/tag'
	});
	
	$('#type').click(function(){
		location.href='/admin/type'
	});
	
	$('#about').click(function(){
		location.href='/admin/announcement'
	});
});
