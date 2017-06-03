$(document).ready(function () {
	$("#login").click(function(){
		$.post(
	      'login',
	      {
	        username:$('#username').val(),
	        password:$('#password').val()
	      },
	      function (data) //回传函数
	      {
	    	  
	      }
	    );
	});
});