$(document).ready(function () {
	
	//保存
	$("#saveTagBt").click(function(){
		var tagName = $('#tagName').val();
		if (tagName.length == 0) {
			alert("请输入标签名字");
			return;
		}
		$.ajax({
			type:"post",
			url:"/admin/saveTag",
			async:false,
			data:{
				tagName:tagName
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
	
	//修改
	$("#updateTagBt").click(function(){
		var newTagName = $('#newTagName').val();
		var tagId = $('#tagId').val();
		if (newTagName.length == 0) {
			alert("请输入标签名字");
			return;
		}
		$.ajax({
			type:"post",
			url:"/admin/updateTag",
			async:false,
			data:{
				tagId:tagId,
				tagName:newTagName
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
	
	//删除
	$("#delTagBt").click(function(){
		var objArray = document.getElementsByName("checkbox");
		var tagIds="";
		for (i=0 ; i<objArray.length ; i++) {
		  if (objArray[i].checked == true) {
			  tagIds += objArray[i].value + ",";
		  }
		}
		if (tagIds == "") {
		  alert("还没有选中任何标签");
		  return;
		}
		if (confirm("此操作不可恢复，确定要删除吗？")) {
			$.ajax({
				type:"post",
				url:"/admin/delTag",
				async:false,
				data:{
					tagIds:tagIds
				},success:function(data){
					if(data.code == "200"){
						alert("成功删除" + data.setTotal + "个标签");
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
	
	//跳转
	$("#addTagBt").click(function(){
		location.href = "/admin/toAddTag";
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
    
});

//标签详情
function editTag(tagId) {
	location.href="/admin/editTag/" + tagId;
}