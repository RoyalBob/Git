$(document).ready(function () {
	
	//保存
	$("#saveTypeBt").click(function(){
		var typeName = $('#typeName').val();
		if (typeName.length == 0) {
			alert("请输入类型名字");
			return;
		}
		$.ajax({
			type:"post",
			url:"/admin/saveType",
			async:false,
			data:{
				typeName:typeName
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
	$("#updateTypeBt").click(function(){
		var newTypeName = $('#newTypeName').val();
		var typeId = $('#typeId').val();
		if (newTypeName.length == 0) {
			alert("请输入类型名字");
			return;
		}
		$.ajax({
			type:"post",
			url:"/admin/updateType",
			async:false,
			data:{
				typeId:typeId,
				typeName:newTypeName
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
	$("#delTypeBt").click(function(){
		var objArray = document.getElementsByName("checkbox");
		var typeIds="";
		for (i=0 ; i<objArray.length ; i++) {
		  if (objArray[i].checked == true) {
			  typeIds += objArray[i].value + ",";
		  }
		}
		if (typeIds == "") {
		  alert("还没有选中任何类型");
		  return;
		}
		if (confirm("此操作不可恢复，确定要删除吗？")) {
			$.ajax({
				type:"post",
				url:"/admin/delType",
				async:false,
				data:{
					typeIds:typeIds
				},success:function(data){
					if(data.code == "200"){
						alert("成功删除" + data.setTotal + "个类型");
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
	$("#addTypeBt").click(function(){
		location.href = "/admin/toAddType";
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
function editType(typeId) {
	location.href="/admin/editType/" + typeId;
}